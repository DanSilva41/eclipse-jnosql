/*
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
 */
package org.eclipse.jnosql.mapping.document.configuration;

import jakarta.nosql.tck.test.CDIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@CDIExtension
public class DocumentIntegrationTest {

    @Inject
    private DocumentIntegration integration;

    @Test
    public void shouldCreateTemplate() {
        Assertions.assertNotNull(integration.getTemplate());
    }

    @Test
    public void shouldCreateManager() {
        Assertions.assertNotNull(integration.getManager());
    }

    @Test
    public void shouldCreateFactory() {
        Assertions.assertNotNull(integration.getFactory());
    }
}
