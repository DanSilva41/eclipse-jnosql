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

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

/**
 *This instance has information to return at the dynamic query in Repository.
 */
public interface DynamicReturn {


    /**
     * The repository class type source.
     * @param <T> the repository type
     * @return The repository class type source.
     */
    <T> Class<T> typeClass();

    /**
     * The method source at the Repository
     * @return The method source at the Repository
     */
    Method getMethod();

    /**
     * Returns the result as single result
     * @param <T> the result type
     * @return the result as single result
     */
    <T> Optional<T> singleResult();

    /**
     * Returns the result as {@link List}
     * @param <T> the result type
     * @return the result as {@link List}
     */
    <T> List<T> list();


}
