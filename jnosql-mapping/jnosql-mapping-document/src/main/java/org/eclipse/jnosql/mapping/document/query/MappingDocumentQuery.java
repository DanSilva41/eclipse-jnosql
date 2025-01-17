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
package org.eclipse.jnosql.mapping.document.query;

import jakarta.nosql.Sort;
import jakarta.nosql.document.DocumentCondition;
import jakarta.nosql.document.DocumentQuery;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class MappingDocumentQuery implements DocumentQuery {
    private final List<Sort> sorts;
    private final long limit;
    private final long skip;
    private final DocumentCondition condition;
    private final String documentCollection;

    MappingDocumentQuery(List<Sort> sorts, long limit, long skip, DocumentCondition condition, String documentCollection) {
        this.sorts = sorts;
        this.limit = limit;
        this.skip = skip;
        this.condition = condition;
        this.documentCollection = documentCollection;
    }

    @Override
    public long getLimit() {
        return limit;
    }

    @Override
    public long getSkip() {
        return skip;
    }

    @Override
    public String getDocumentCollection() {
        return documentCollection;
    }

    @Override
    public Optional<DocumentCondition> getCondition() {
        return Optional.ofNullable(condition);
    }

    @Override
    public List<Sort> getSorts() {
        return sorts;
    }

    @Override
    public List<String> getDocuments() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentQuery)) {
            return false;
        }
        DocumentQuery that = (DocumentQuery) o;
        return limit == that.getLimit() &&
                skip == that.getSkip() &&
                Objects.equals(sorts, that.getSorts()) &&
                Objects.equals(condition, that.getCondition().orElse(null)) &&
                Objects.equals(documentCollection, that.getDocumentCollection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, skip, documentCollection, condition, sorts, Collections.emptyList());
    }

    @Override
    public String toString() {
        return "ArtemisDocumentQuery{" + "limit=" + limit +
                ", skip=" + skip +
                ", documentCollection='" + documentCollection + '\'' +
                ", condition=" + condition +
                ", sorts=" + sorts +
                ", documents=" + Collections.emptyList() +
                '}';
    }
}

