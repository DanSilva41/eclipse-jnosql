/*
 *
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
 *
 */
package org.eclipse.jnosql.communication.keyvalue;

import jakarta.nosql.Value;
import jakarta.nosql.keyvalue.KeyValueEntity;
import jakarta.nosql.keyvalue.KeyValueEntity.KeyValueEntityProvider;

import java.util.Objects;

public final class DefaultKeyValueEntityProvider implements KeyValueEntityProvider {

    @Override
    public KeyValueEntity apply(Object key, Object value) {
        Objects.requireNonNull(key, "key is required");
        Objects.requireNonNull(value, "value is required");
        return new DefaultKeyValueEntity(getKey(key), getValue(value));
    }

    private Object getKey(Object key) {
        if (key instanceof Value) {
            return Value.class.cast(key).get();
        }
        return key;
    }

    private Value getValue(Object value) {
        if (value instanceof Value) {
            return Value.class.cast(value);
        }
        return Value.of(value);
    }
}
