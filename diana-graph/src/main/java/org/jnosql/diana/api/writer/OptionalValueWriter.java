/*
 *
 *  Copyright (c) 2017 Otávio Santana and others
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

package org.jnosql.diana.api.writer;


import org.jnosql.diana.api.ValueWriter;

import java.util.Optional;

public final class OptionalValueWriter<T> implements ValueWriter<Optional<T>, T> {

    @Override
    public <T> boolean isCompatible(Class<T> clazz) {
        return Optional.class.equals(clazz);
    }


    @Override
    public T write(Optional<T> optional) {
        return optional.get();
    }
}
