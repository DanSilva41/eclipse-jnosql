/*
 *  Copyright (c) 2022 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *  You may elect to redistribute this code under either of these licenses.
 *  Contributors:
 *  Otavio Santana
 */
package org.eclipse.jnosql.communication.query;

import jakarta.nosql.query.Condition;
import jakarta.nosql.query.JSONQueryValue;
import jakarta.nosql.query.UpdateQuery;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final class DefaultUpdateQuery implements UpdateQuery {

    private final String entity;

    private final List<Condition> conditions;

    private final JSONQueryValue value;

    DefaultUpdateQuery(String entity, List<Condition> conditions, JSONQueryValue value) {
        this.entity = entity;
        this.conditions = conditions;
        this.value = value;
    }

    @Override
    public String getEntity() {
        return entity;
    }

    @Override
    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    @Override
    public Optional<JSONQueryValue> getValue() {
        return Optional.ofNullable(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultUpdateQuery)) {
            return false;
        }
        DefaultUpdateQuery that = (DefaultUpdateQuery) o;
        return Objects.equals(entity, that.entity) &&
                Objects.equals(conditions, that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, conditions);
    }

    @Override
    public String toString() {
        if (conditions.isEmpty() && value != null) {
            return "update " + entity + ' ' + value;
        } else {
            return "update " + entity + " (" + conditions + ") ";
        }
    }
}
