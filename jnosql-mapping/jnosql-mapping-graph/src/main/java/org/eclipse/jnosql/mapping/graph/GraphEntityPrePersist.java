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

import org.apache.tinkerpop.gremlin.structure.Vertex;

import static java.util.Objects.requireNonNull;

/**
 * The interface represents the model before the {@link Vertex} be saved that  event will fired.
 */
public interface GraphEntityPrePersist {


    /**
     * The {@link Vertex}  before be saved
     *
     * @return the {@link Vertex} instance
     */
    Vertex getVertex();

    /**
     * Creates the  instance
     *
     * @param entity the entity
     * @return  instance
     * @throws NullPointerException when the entity is null
     */
    static GraphEntityPrePersist of(Vertex entity) {
        requireNonNull(entity, "Entity is required");
        return new DefaultGraphEntityPrePersist(entity);
    }
}
