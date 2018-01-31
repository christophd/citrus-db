/*
 * Copyright 2006-2018 the original author or authors.
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
 */

package com.consol.citrus.db.server.controller;

import com.consol.citrus.db.driver.dataset.DataSet;
import com.consol.citrus.db.server.JdbcServerException;

import java.util.Map;

/**
 * @author Christoph Deppisch
 */
public interface JdbcController {

    /**
     * Get connection from db server.
     * @param properties
     * @return
     * @throws JdbcServerException
     */
    void openConnection(Map<String, String> properties) throws JdbcServerException;

    /**
     * Create statement request.
     * @return
     * @throws JdbcServerException
     */
    void createStatement() throws JdbcServerException;

    /**
     * Close connection request.
     * @throws JdbcServerException
     */
    void closeConnection() throws JdbcServerException;

    /**
     * Create new prepared statement.
     * @param sql
     * @return
     * @throws JdbcServerException
     */
    void createPreparedStatement(String sql) throws JdbcServerException;

    /**
     * Execute query statement
     * @param sql
     * @throws JdbcServerException
     * @return
     */
    DataSet executeQuery(String sql) throws JdbcServerException;

    /**
     * Execute statement.
     * @param sql
     * @return
     * @throws JdbcServerException
     */
    void execute(String sql) throws JdbcServerException;

    /**
     * Execute update statement.
     * @param sql
     * @return
     * @throws JdbcServerException
     */
    int executeUpdate(String sql) throws JdbcServerException;

    /**
     * Close request.
     * @throws JdbcServerException
     */
    void closeStatement() throws JdbcServerException;

    /**
     * Sets whether the server is in a transaction state or not.
     * @param transactionState The boolean value whether the server is in transaction state.
     */
    void setTransactionState(boolean transactionState);


    /**
     * Gets the current transaction state of the server
     * @return The transaction state of the server
     */
    boolean getTransactionState();

    /**
     * Commits all statements in the current transaction
     */
    void commitStatements();

    /**
     * Rollback all statements since the current transaction has been started
     */
    void rollbackStatements();
}