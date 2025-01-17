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

import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.tck.test.CDIExtension;
import org.eclipse.jnosql.mapping.document.configuration.DocumentConfigurationMock.DocumentCollectionManagerMock;
import org.eclipse.jnosql.mapping.configuration.ConfigurationException;
import org.eclipse.microprofile.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.NoSuchElementException;
import java.util.UUID;

@CDIExtension
class DocumentCollectionManagerConverterTest {

    @Inject
    private Config config;

    @Test
    public void shouldReturnErrorWhenThereIsNoProvider() {
        final String prefix = UUID.randomUUID().toString();
        System.setProperty(prefix, prefix);
        System.setProperty(prefix + ".settings.key", "value");
        System.setProperty(prefix + ".settings.key2", "value2");
        Assertions.assertThrows(NoSuchElementException.class, () -> config.getValue(prefix, DocumentCollectionManager.class) );

        System.clearProperty(prefix);
        System.clearProperty(prefix + ".settings.key");
        System.clearProperty(prefix + ".settings.key2");
    }

    @Test
    public void shouldReturnErrorWhenThereIsInvalidProvider() {
        final String prefix = UUID.randomUUID().toString();
        System.setProperty(prefix, prefix);
        System.setProperty(prefix + ".settings.key", "value");
        System.setProperty(prefix + ".settings.key2", "value2");
        System.setProperty(prefix + ".provider", "java.lang.String");
        Assertions.assertThrows(ConfigurationException.class, () -> config.getValue(prefix, DocumentCollectionManager.class) );

        System.clearProperty(prefix);
        System.clearProperty(prefix + ".settings.key");
        System.clearProperty(prefix + ".settings.key2");
        System.clearProperty(prefix + ".provider");
    }

    @Test
    public void shouldReturnErrorWhenTableIsMissing() {
        final String prefix = UUID.randomUUID().toString();
        System.setProperty(prefix, prefix);
        System.setProperty(prefix + ".settings.key", "value");
        System.setProperty(prefix + ".settings.key2", "value2");
        System.setProperty(prefix + ".provider", DocumentConfigurationMock.class.getName());
        Assertions.assertThrows(NoSuchElementException.class, () -> config.getValue(prefix, DocumentCollectionManager.class) );

        System.clearProperty(prefix);
        System.clearProperty(prefix + ".settings.key");
        System.clearProperty(prefix + ".settings.key2");
        System.clearProperty(prefix + ".provider");
    }

    @Test
    public void shouldReturnManager() {
        final String prefix = UUID.randomUUID().toString();
        System.setProperty(prefix, prefix);
        System.setProperty(prefix + ".settings.key", "value");
        System.setProperty(prefix + ".settings.key2", "value2");
        System.setProperty(prefix + ".provider", DocumentConfigurationMock.class.getName());
        System.setProperty(prefix + ".database", "database");
        final DocumentCollectionManager manager = config.getValue(prefix, DocumentCollectionManager.class);
        final DocumentCollectionManagerMock managerMock = DocumentCollectionManagerMock.class.cast(manager);
        Assertions.assertEquals("database", managerMock.getDatabase());
        System.clearProperty(prefix);
        System.clearProperty(prefix + ".settings.key");
        System.clearProperty(prefix + ".settings.key2");
        System.clearProperty(prefix + ".provider");
        System.clearProperty(prefix + ".database");
    }
}