/*
 *  Copyright (c) 2022 Contributors to the Eclipse Foundation
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.eclipse.jnosql.mapping.graph;

import jakarta.nosql.mapping.Converters;
import jakarta.nosql.mapping.EntityNotFoundException;
import org.eclipse.jnosql.mapping.reflection.EntityMetadata;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.jnosql.mapping.reflection.EntitiesMetadata;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

/**
 * A default implementation to GraphTraversalSourceOperation
 */
@GraphTraversalSourceOperation
@ApplicationScoped
class DefaultGraphTraversalSourceConverter extends AbstractGraphConverter {


    private EntitiesMetadata entities;

    private Converters converters;

    private Instance<GraphTraversalSourceSupplier> suppliers;


    @Inject
    DefaultGraphTraversalSourceConverter(EntitiesMetadata entities, Converters converters,
                                         Instance<GraphTraversalSourceSupplier> suppliers) {
        this.entities = entities;
        this.converters = converters;
        this.suppliers = suppliers;
    }

    DefaultGraphTraversalSourceConverter() {
    }

    @Override
    protected EntitiesMetadata getEntities() {
        return entities;
    }

    @Override
    protected Converters getConverters() {
        return converters;
    }

    @Override
    protected Graph getGraph() {
        throw new UnsupportedOperationException("GraphTraversalSource does not support graph instance");
    }

    @Override
    public <T> Vertex toVertex(T entity) {
        requireNonNull(entity, "entity is required");

        EntityMetadata mapping = getEntities().get(entity.getClass());
        String label = mapping.getName();

        List<FieldGraph> fields = mapping.getFields().stream()
                .map(f -> to(f, entity))
                .filter(FieldGraph::isNotEmpty).collect(toList());

        Optional<FieldGraph> id = fields.stream().filter(FieldGraph::isId).findFirst();

        final Function<Property, Vertex> findVertexOrCreateWithId = p -> {
            Iterator<Vertex> vertices = getTraversalSource().V(p.value());
            return vertices.hasNext() ? vertices.next() :
                    getTraversalSource().addV(label)
                            .property(org.apache.tinkerpop.gremlin.structure.T.id, p.value())
                            .next();
        };

        Vertex vertex = id.map(i -> i.toElement(getConverters()))
                .map(findVertexOrCreateWithId)
                .orElseGet(() -> getTraversalSource().addV(label).next());

        fields.stream().filter(FieldGraph::isNotId)
                .flatMap(f -> f.toElements(this, getConverters()).stream())
                .forEach(p -> vertex.property(p.key(), p.value()));

        mapping.getInheritance().ifPresent(i ->
                vertex.property(i.getDiscriminatorColumn(), i.getDiscriminatorValue()));

        return vertex;

    }

    @Override
    public Edge toEdge(EdgeEntity edge) {
        requireNonNull(edge, "vertex is required");
        Object id = edge.getId();
        final Iterator<Edge> edges = getTraversalSource().E(id);
        if (edges.hasNext()) {
            return edges.next();
        }
        throw new EntityNotFoundException("Edge does not found in the database with id: " + id);
    }

    private GraphTraversalSource getTraversalSource() {
        return suppliers.get().get();
    }
}
