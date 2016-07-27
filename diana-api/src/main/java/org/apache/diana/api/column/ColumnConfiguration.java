package org.apache.diana.api.column;

import java.util.Map;

/**
 * The diana configuration to create a {@link ColumnFamilyManagerFactory}
 *
 * @author Otávio Santana
 */
public interface ColumnConfiguration {

    /**
     * Reads configuration from Map and creates a {@link ColumnFamilyManagerFactory} instance.
     *
     * @param configurations the configuration from {@link Map}
     * @return a {@link ColumnFamilyManagerFactory} instance
     */
    ColumnFamilyManagerFactory getManagerFactory(Map<String, String> configurations);

    /**
     * Reads configuration either from default configuration or a file defined by NoSQL provider and then creates a {@link ColumnFamilyManagerFactory} instance.
     *
     * @return a {@link ColumnFamilyManagerFactory}
     */
    ColumnFamilyManagerFactory getManagerFactory();
}
