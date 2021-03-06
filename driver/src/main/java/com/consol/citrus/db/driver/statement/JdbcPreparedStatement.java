/*
 *  Copyright 2006-2019 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.consol.citrus.db.driver.statement;

import com.consol.citrus.db.driver.JdbcConnection;
import com.consol.citrus.db.driver.data.CitrusBlob;
import com.consol.citrus.db.driver.data.CitrusClob;
import com.consol.citrus.db.driver.utils.LobUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.client.HttpClient;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class JdbcPreparedStatement extends JdbcStatement implements PreparedStatement {

    /** The prepared statement to be executed */
    private final String preparedStatement;

    /** The parameters to add to the statement */
    private StatementParameters parameters = new StatementParameters();

    /** A list of parameter sets for batch execution purposes */
    private final List<StatementParameters> batchParameters = new LinkedList<>();

    private LobUtils lobUtils = new LobUtils();

    private StatementComposer statementComposer = new StatementComposer();

    public JdbcPreparedStatement(final HttpClient httpClient,
                          final String preparedStatement,
                          final String serverUrl,
                          final JdbcConnection connection) {
        super(httpClient, serverUrl, connection);
        this.preparedStatement = preparedStatement;
    }

    JdbcPreparedStatement(final HttpClient httpClient,
                          final String preparedStatement,
                          final String serverUrl,
                          final JdbcConnection connection,
                          final LobUtils lobUtils,
                          final StatementParameters statementParameters) {
        this(httpClient, preparedStatement, serverUrl, connection);
        this.lobUtils = lobUtils;
        this.parameters = statementParameters;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return super.executeQuery(composeStatement());
    }

    @Override
    public int executeUpdate() throws SQLException {
        return super.executeUpdate(composeStatement());
    }

    @Override
    public void setNull(final int parameterIndex, final int sqlType) {
        setParameter(parameterIndex, null);
    }

    @Override
    public void setBoolean(final int parameterIndex, final boolean x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setByte(final int parameterIndex, final byte x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setShort(final int parameterIndex, final short x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setInt(final int parameterIndex, final int x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setLong(final int parameterIndex, final long x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setFloat(final int parameterIndex, final float x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setDouble(final int parameterIndex, final double x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(final int parameterIndex, final BigDecimal x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setString(final int parameterIndex, final String x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setBytes(final int parameterIndex, final byte[] x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setDate(final int parameterIndex, final Date x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setTime(final int parameterIndex, final Time x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setTimestamp(final int parameterIndex, final Timestamp x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x, final int length) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setUnicodeStream(final int parameterIndex, final InputStream x, final int length) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x, final int length) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void clearParameters() {
        parameters.clear();
    }

    @Override
    public void setObject(final int parameterIndex, final Object x, final int targetSqlType) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setObject(final int parameterIndex, final Object x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public boolean execute() throws SQLException {
        return super.execute(composeStatement());
    }

    @Override
    public void addBatch() {
        batchParameters.add(new StatementParameters(parameters));
        parameters.clear();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        final ArrayList<Integer> arrayList = new ArrayList<>();
        for (final StatementParameters statementParameters : batchParameters){
            execute(statementComposer.composeStatement(preparedStatement, statementParameters));
            arrayList.add(getUpdateCount());
        }
        return ArrayUtils.toPrimitive(arrayList.toArray(new Integer[0]));
    }

    @Override
    public void addBatch(final String sql) throws SQLException {
        throw new SQLException("Adding batch statements is not allowed on prepared statements");
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader, final int length) throws SQLException {
        throw new SQLException(createFunctionNotSupportedMessage("setCharacterStream"));
    }

    @Override
    public void setRef(final int parameterIndex, final Ref x) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setRef'");
    }

    @Override
    public void setBlob(final int parameterIndex, final Blob x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setClob(final int parameterIndex, final Clob x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setArray(final int parameterIndex, final Array x) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setArray'");
    }

    @Override
    public ResultSetMetaData getMetaData() {
        return resultSet.getMetaData();
    }

    @Override
    public void setDate(final int parameterIndex, final Date x, final Calendar cal) {
        setParameter(parameterIndex, x.toString());
    }

    @Override
    public void setTime(final int parameterIndex, final Time x, final Calendar cal) {
        setParameter(parameterIndex, x.getTime());
    }

    @Override
    public void setTimestamp(final int parameterIndex, final Timestamp x, final Calendar cal) {
        setParameter(parameterIndex, x.getTime());
    }

    @Override
    public void setNull(final int parameterIndex, final int sqlType, final String typeName) {
        setParameter(parameterIndex, null);
    }

    @Override
    public void setURL(final int parameterIndex, final URL x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() {
        return null;
    }

    @Override
    public void setRowId(final int parameterIndex, final RowId x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setNString(final int parameterIndex, final String x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setNCharacterStream(final int parameterIndex, final Reader value, final long length) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setNCharacterStream'");
    }

    @Override
    public void setNClob(final int parameterIndex, final NClob value) throws SQLException {
        throw new SQLException(createFunctionNotSupportedMessage("setNClob"));
    }

    @Override
    public void setClob(final int parameterIndex, final Reader reader, final long length) throws SQLException {
        if(lobUtils.fitsInInt(length)){
            final CitrusClob citrusClob = lobUtils.createClobFromReader(reader, (int) length);
            setParameter(parameterIndex, citrusClob);
        }
    }

    @Override
    public void setBlob(final int parameterIndex, final InputStream inputStream, final long length) throws SQLException {
        if(lobUtils.fitsInInt(length)){
            final CitrusBlob citrusBlob = lobUtils.createBlobFromInputStream(inputStream, (int) length);
            setParameter(parameterIndex, citrusBlob);
        }
    }

    @Override
    public void setNClob(final int parameterIndex, final Reader reader, final long length) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setNClob'");
    }

    @Override
    public void setSQLXML(final int parameterIndex, final SQLXML xmlObject) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setSQLXML'");
    }

    @Override
    public void setObject(final int parameterIndex, final Object x, final int targetSqlType, final int scaleOrLength) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x, final long length) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setAsciiStream'");
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x, final long length) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setBinaryStream'");
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader, final long length) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setCharacterStream'");
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x) {
        setParameter(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setBinaryStream'");
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setCharacterStream'");
    }

    @Override
    public void setNCharacterStream(final int parameterIndex, final Reader value) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setNCharacterStream'");
    }

    @Override
    public void setClob(final int parameterIndex, final Reader reader) throws SQLException {
        final CitrusClob citrusClob = lobUtils.createClobFromReader(reader, -1);
        setParameter(parameterIndex, citrusClob);
    }

    @Override
    public void setBlob(final int parameterIndex, final InputStream inputStream) throws SQLException {
        final CitrusBlob citrusClob = lobUtils.createBlobFromInputStream(inputStream, -1);
        setParameter(parameterIndex, citrusClob);
    }

    @Override
    public void setNClob(final int parameterIndex, final Reader reader) throws SQLException {
        throw new SQLException("Not supported JDBC prepared statement function 'setNClob'");
    }

    void setParameter(final int parameterIndex, final Object value){
        parameters.setParameter(parameterIndex, value);
    }

    void setParameter(final String parameterName, final Object value){
        parameters.setParameter(parameterName, value);
    }

    String composeStatement() {
        return statementComposer.composeStatement(preparedStatement, parameters);
    }

    StatementParameters getParameters() {
        return parameters;
    }

    private String createFunctionNotSupportedMessage(final String methodName) {
        return String.format("Not supported JDBC prepared statement function '%s'", methodName);
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof JdbcPreparedStatement)) return false;
        if (o.getClass().equals(JdbcStatement.class)) return false;
        final JdbcPreparedStatement that = (JdbcPreparedStatement) o;
        return Objects.equals(preparedStatement, that.preparedStatement) &&
                Objects.equals(parameters, that.parameters)&&
                Objects.equals(httpClient, that.httpClient) &&
                Objects.equals(serverUrl, that.serverUrl) &&
                Objects.equals(connection, that.connection) &&
                Objects.equals(batchStatements, that.batchStatements) &&
                Objects.equals(closed, that.closed) &&
                Objects.equals(updateCount, that.updateCount) &&
                Objects.equals(batchParameters, that.batchParameters);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), preparedStatement, parameters, batchParameters);
    }

    @Override
    public String toString() {
        return "JdbcPreparedStatement{" +
                "preparedStatement='" + preparedStatement + '\'' +
                ", parameters=" + parameters +
                ", resultSet=" + resultSet +
                ", batchParameters=" + batchParameters +
                "} " + super.toString();
    }
}

