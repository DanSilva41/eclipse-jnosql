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
package org.eclipse.jnosql.mapping.column.configuration;

import jakarta.nosql.Settings;
import jakarta.nosql.column.ColumnFamilyManagerFactory;
import org.eclipse.jnosql.mapping.configuration.ConfigurationException;
import jakarta.nosql.tck.test.CDIExtension;
import org.eclipse.microprofile.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CDIExtension
class ColumnFamilyManagerFactoryConverterTest {

    @Inject
    private Config config;

    @Test
    public void shouldReturnErrorWhenThereIsNoProvider() {
        final String prefix = UUID.randomUUID().toString();
        System.setProperty(prefix, prefix);
        System.setProperty(prefix + ".settings.key", "value");
        System.setProperty(prefix + ".settings.key2", "value2");
        Assertions.assertThrows(NoSuchElementException.class, () -> config.getValue(prefix, ColumnFamilyManagerFactory.class) );

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
        Assertions.assertThrows(ConfigurationException.class, () -> config.getValue(prefix, ColumnFamilyManagerFactory.class) );

        System.clearProperty(prefix);
        System.clearProperty(prefix + ".settings.key");
        System.clearProperty(prefix + ".settings.key2");
        System.clearProperty(prefix + ".provider");
    }

    @Test
    public void shouldReturnManagerFactory() {
        final String prefix = UUID.randomUUID().toString();
        System.setProperty(prefix, prefix);
        System.setProperty(prefix + ".settings.key", "value");
        System.setProperty(prefix + ".settings.key2", "value2");
        System.setProperty(prefix + ".provider", ColumnConfigurationMock.class.getName());
        final ColumnFamilyManagerFactory managerFactory = config.getValue(prefix, ColumnFamilyManagerFactory.class);

        final ColumnConfigurationMock.ColumnFamilyManagerFactoryMock factoryMock = ColumnConfigurationMock.ColumnFamilyManagerFactoryMock.class.cast(managerFactory);
        final Settings settings = factoryMock.getSettings();

        assertEquals(2, settings.size());
        assertEquals(settings.get("key").get(), "value");
        assertEquals(settings.get("key2").get(), "value2");

        assertNotNull(managerFactory);
        System.clearProperty(prefix);
        System.clearProperty(prefix + ".settings.key");
        System.clearProperty(prefix + ".settings.key2");
        System.clearProperty(prefix + ".provider");
    }
}