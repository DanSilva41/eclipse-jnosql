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
package org.jnosql.diana.keyvalue.query;

import jakarta.nosql.Params;
import jakarta.nosql.QueryException;
import jakarta.nosql.Value;
import jakarta.nosql.keyvalue.KeyValueEntity;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParamsTest {


    @Test
    public void shouldAddParamter() {
        Params params = Params.newParams();
        Value name = params.add("name");
        assertNotNull(name);
        MatcherAssert.<List<String>>assertThat(params.getParametersNames(), containsInAnyOrder("name"));
    }

    @Test
    public void shouldNotUseValueWhenIsInvalid() {
        Params params = Params.newParams();
        Value name = params.add("name");
        assertThrows(QueryException.class, name::get);

        assertThrows(QueryException.class, () -> name.get(String.class));
    }

    @Test
    public void shouldSetParameter() {
        Params params = Params.newParams();
        Value name = params.add("name");
        KeyValueEntity entity = KeyValueEntity.of("name", name);
        params.bind("name", "Ada Lovelace");

        assertEquals("Ada Lovelace", entity.getValue());

        params.bind("name", "Diana");
        assertEquals("Diana", entity.getValue());
    }

}