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

import jakarta.nosql.query.GetQuery;
import jakarta.nosql.query.GetQuery.GetQueryProvider;
import org.eclipse.jnosql.communication.query.AntlrGetQueryProvider;

import java.util.Objects;


/**
 * The {@link AntlrGetQueryProvider} cache wrapper.
 */
public final class CachedGetQueryProvider implements GetQueryProvider {

    private final CacheQuery<GetQuery> cached;


    public CachedGetQueryProvider() {
        this.cached = CacheQuery.of(q -> new AntlrGetQueryProvider().apply(q));
    }

    @Override
    public GetQuery apply(String query) {
        Objects.requireNonNull(query, "query is required");
        return cached.get(query);
    }
}
