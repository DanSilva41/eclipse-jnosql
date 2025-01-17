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

package org.eclipse.jnosql.communication.reader;

import jakarta.nosql.ValueReader;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to read and convert to {@link ZonedDateTime} type
 */
@SuppressWarnings("unchecked")
public class ZonedDateTimeReader implements ValueReader {

    @Override
    public boolean test(Class<?> type) {
        return ZonedDateTime.class.equals(type);
    }

    @Override
    public <T> T read(Class<T> type, Object value) {
        if (ZonedDateTime.class.isInstance(value)) {
            return (T) value;
        }

        if (Calendar.class.isInstance(value)) {
            return (T) ((Calendar) value).toInstant().atZone(ZoneId.systemDefault());
        }

        if (Date.class.isInstance(value)) {
            return (T) ((Date) value).toInstant().atZone(ZoneId.systemDefault());
        }

        if (Number.class.isInstance(value)) {
            return (T) new Date(((Number) value).longValue()).toInstant().atZone(ZoneId.systemDefault());
        }

        return (T) ZonedDateTime.parse(value.toString());
    }
}
