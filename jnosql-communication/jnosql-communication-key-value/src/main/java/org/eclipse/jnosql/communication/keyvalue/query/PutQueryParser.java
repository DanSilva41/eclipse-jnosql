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
package org.eclipse.jnosql.communication.keyvalue.query;

import jakarta.nosql.Params;
import jakarta.nosql.QueryException;
import jakarta.nosql.Value;
import jakarta.nosql.keyvalue.BucketManager;
import jakarta.nosql.keyvalue.KeyValueEntity;
import jakarta.nosql.keyvalue.KeyValuePreparedStatement;
import jakarta.nosql.query.PutQuery;
import jakarta.nosql.query.PutQuery.PutQueryProvider;

import java.time.Duration;
import java.util.Optional;
import java.util.stream.Stream;

final class PutQueryParser {

    private final PutQueryProvider provider;

    PutQueryParser() {
        this.provider = PutQuery.getProvider();
    }

    Stream<Value> query(String query, BucketManager manager) {

        PutQuery putQuery = provider.apply(query);
        Params params = Params.newParams();
        Value key = Values.getValue(putQuery.getKey(), params);
        Value value = Values.getValue(putQuery.getValue(), params);
        Optional<Duration> ttl = putQuery.getTtl();

        if (params.isNotEmpty()) {
            throw new QueryException("To run a query with a parameter use a PrepareStatement instead.");
        }

        KeyValueEntity entity = KeyValueEntity.of(key.get(), value.get());
        if (ttl.isPresent()) {
            manager.put(entity, ttl.get());
        } else {
            manager.put(entity);
        }
        return Stream.empty();
    }

    public KeyValuePreparedStatement prepare(String query, BucketManager manager) {
        PutQuery putQuery = provider.apply(query);
        Params params = Params.newParams();
        Value key = Values.getValue(putQuery.getKey(), params);
        Value value = Values.getValue(putQuery.getValue(), params);
        Optional<Duration> ttl = putQuery.getTtl();

        return DefaultKeyValuePreparedStatement.put(key, value, manager, params, ttl.orElse(null), query);
    }
}
