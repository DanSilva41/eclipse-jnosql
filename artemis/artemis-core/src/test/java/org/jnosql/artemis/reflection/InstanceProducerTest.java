/*
 *  Copyright (c) 2018 Otávio Santana and others
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

import jakarta.nosql.mapping.InstanceProducer;
import org.jnosql.artemis.CDIExtension;
import org.jnosql.artemis.model.Zipcode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;


@ExtendWith(CDIExtension.class)
public class InstanceProducerTest {

    @Inject
    private InstanceProducer instanceProducer;

    @Test
    public void shouldCreateNewInstance() {

        Zipcode zipcode = instanceProducer.create(Zipcode.class);
        Assertions.assertNotNull(zipcode);
        Assertions.assertTrue(zipcode instanceof Zipcode);
    }

}
