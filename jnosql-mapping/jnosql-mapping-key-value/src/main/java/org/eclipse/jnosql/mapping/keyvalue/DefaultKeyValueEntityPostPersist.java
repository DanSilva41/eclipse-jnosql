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
package org.eclipse.jnosql.mapping.keyvalue;

import jakarta.nosql.keyvalue.KeyValueEntity;
import jakarta.nosql.mapping.keyvalue.KeyValueEntityPostPersist;

import java.util.Objects;

class DefaultKeyValueEntityPostPersist implements KeyValueEntityPostPersist {

    private final KeyValueEntity entity;

    DefaultKeyValueEntityPostPersist(KeyValueEntity entity) {
        this.entity = entity;
    }

    @Override
    public KeyValueEntity getEntity() {
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultKeyValueEntityPostPersist that = (DefaultKeyValueEntityPostPersist) o;
        return Objects.equals(entity, that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(entity);
    }

    @Override
    public String toString() {
        return  "DefaultKeyValueEntityPostPersist{" + "entity=" + entity +
                '}';
    }
}
