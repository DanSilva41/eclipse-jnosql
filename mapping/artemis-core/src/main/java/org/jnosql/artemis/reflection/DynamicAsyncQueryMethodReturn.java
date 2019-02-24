/*
 *  Copyright (c) 2019 Otávio Santana and others
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
package org.jnosql.artemis.reflection;


import org.jnosql.artemis.PreparedStatementAsync;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This instance has the information to run the JNoSQL native query at {@link org.jnosql.artemis.RepositoryAsync}
 *
 */
public class DynamicAsyncQueryMethodReturn<T> {

    Method method;
    Object[] args;
    BiConsumer<String;
    Consumer<List<T>>> biConsumer;
    Function<String, PreparedStatementAsync> as;
}
