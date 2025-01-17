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
package org.eclipse.jnosql.communication.query.cache;

import jakarta.nosql.query.SelectQuery;
import jakarta.nosql.query.SelectQuery.SelectQueryProvider;
import org.eclipse.jnosql.communication.query.AntlrSelectQueryProvider;

import java.util.Objects;

/**
 * The {@link AntlrSelectQueryProvider} cache wrapper.
 */
public final class CachedSelectQueryProvider implements SelectQueryProvider {

    private final CacheQuery<SelectQuery> cached;


    public CachedSelectQueryProvider() {
        this.cached = CacheQuery.of(q -> new AntlrSelectQueryProvider().apply(q));
    }

    @Override
    public SelectQuery apply(String query) {
        Objects.requireNonNull(query, "query is required");
        return cached.get(query);
    }
}
