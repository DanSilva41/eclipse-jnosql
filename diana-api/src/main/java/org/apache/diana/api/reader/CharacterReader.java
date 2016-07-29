/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.diana.api.reader;


import org.apache.diana.api.ReaderField;

import static java.lang.Character.MIN_VALUE;

/**
 * Class reader for {@link Character}
 *
 * @author Otávio Santana
 */
public final class CharacterReader implements ReaderField {

    @Override
    public boolean isCompatible(Class clazz) {
        return Character.class.equals(clazz);
    }

    @Override
    public <T> T read(Class<T> clazz, Object value) {
        if (Character.class.isInstance(value)) {
            return (T) value;
        }
        if (Number.class.isInstance(value)) {
            return (T) Character.valueOf((char) Number.class.cast(value).intValue());
        }

        if (value.toString().isEmpty()) {
            return (T) Character.valueOf(MIN_VALUE);
        }
        return (T) Character.valueOf(value.toString().charAt(0));
    }


}
