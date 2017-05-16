/*
 * Copyright 2017 Otavio Santana and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package org.jnosql.diana.api.column;


/**
 * {@link ColumnFamilyManager} factory.
 * When the application has finished using the column family manager factory, and/or at application shutdown,
 * the application should close the column family manager factory.
 *
 * @param <ASYNC> the {@link ColumnFamilyManagerAsync} type
 */
public interface ColumnFamilyManagerAsyncFactory <ASYNC extends ColumnFamilyManagerAsync> {
    /**
     * Creates a {@link ColumnFamilyManagerAsync} from database's name
     *
     * @param database a database name
     * @return a new {@link ColumnFamilyManagerAsync} instance
     * @throws UnsupportedOperationException when this operation is not supported
     *                                       throws {@link NullPointerException} when the database is null
     */
    ASYNC getAsync(String database) throws UnsupportedOperationException, NullPointerException;

    /**
     * closes a resource
     */
    void close();

}