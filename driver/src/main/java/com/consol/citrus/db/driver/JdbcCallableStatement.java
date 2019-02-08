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

package com.consol.citrus.db.driver;

import com.consol.citrus.db.driver.data.Row;
import org.apache.http.client.HttpClient;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class JdbcCallableStatement extends JdbcPreparedStatement implements CallableStatement {

    private Row resultRow;

    public JdbcCallableStatement(final HttpClient httpClient,
                                 final String callableStatement,
                                 final String serverUrl,
                                 final JdbcConnection connection) {
        super(httpClient, callableStatement, serverUrl, connection);
    }

    @Override
    public void registerOutParameter(final int parameterIndex, final int sqlType) throws SQLException {
        setOutParameter(parameterIndex);
    }

    @Override
    public void registerOutParameter(final int parameterIndex, final int sqlType, final int scale) throws SQLException {
        registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public boolean wasNull() throws SQLException {
        final Row dataRow = getDataRow();
        if(dataRow == null){
            return false;
        }
        return Objects.isNull(dataRow.getLastValue());
    }

    @Override
    public String getString(final int parameterIndex) throws SQLException {
        return (String) getDataRow().getValue(parameterIndex-1, String.class);
    }

    @Override
    public boolean getBoolean(final int parameterIndex) throws SQLException {
        return (boolean) getDataRow().getValue(parameterIndex-1, boolean.class);
    }

    @Override
    public byte getByte(final int parameterIndex) throws SQLException {
        return (byte) getDataRow().getValue(parameterIndex-1, byte.class);
    }

    @Override
    public short getShort(final int parameterIndex) throws SQLException {
        return (short) getDataRow().getValue(parameterIndex-1, short.class);
    }

    @Override
    public int getInt(final int parameterIndex) throws SQLException {
        return (int) getDataRow().getValue(parameterIndex-1, int.class);
    }

    @Override
    public long getLong(final int parameterIndex) throws SQLException {
        return (long) getDataRow().getValue(parameterIndex-1, long.class);
    }

    @Override
    public float getFloat(final int parameterIndex) throws SQLException {
        return (float) getDataRow().getValue(parameterIndex-1, float.class);
    }

    @Override
    public double getDouble(final int parameterIndex) throws SQLException {
        return (double) getDataRow().getValue(parameterIndex-1, double.class);
    }

    @Override
    public BigDecimal getBigDecimal(final int parameterIndex, final int scale) throws SQLException {
        return getBigDecimal(parameterIndex).setScale(scale, RoundingMode.HALF_UP);
    }

    @Override
    public byte[] getBytes(final int parameterIndex) throws SQLException {
        return (byte[]) getDataRow().getValue(parameterIndex-1, byte[].class);
    }

    @Override
    public Date getDate(final int parameterIndex) throws SQLException {
        return (Date) getDataRow().getValue(parameterIndex-1, Date.class);
    }

    @Override
    public Time getTime(final int parameterIndex) throws SQLException {
        return (Time) getDataRow().getValue(parameterIndex-1, Time.class);
    }

    @Override
    public Timestamp getTimestamp(final int parameterIndex) throws SQLException {
        return (Timestamp) getDataRow().getValue(parameterIndex-1, Timestamp.class);
    }

    @Override
    public Object getObject(final int parameterIndex) throws SQLException {
        return getDataRow().getValue(parameterIndex-1, Object.class);
    }

    @Override
    public BigDecimal getBigDecimal(final int parameterIndex) throws SQLException {
        return (BigDecimal) getDataRow().getValue(parameterIndex-1, BigDecimal.class);
    }

    @Override
    public Object getObject(final int parameterIndex, final Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref getRef(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Blob getBlob(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Clob getClob(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Date getDate(final int parameterIndex, final Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(final int parameterIndex, final Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(final int parameterIndex, final Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public void registerOutParameter(final int parameterIndex, final int sqlType, final String typeName) throws SQLException {
        registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public void registerOutParameter(final String parameterName, final int sqlType) throws SQLException {
        setOutParameter(parameterName);
    }

    @Override
    public void registerOutParameter(final String parameterName, final int sqlType, final int scale) throws SQLException {
        registerOutParameter(parameterName, sqlType);
    }

    @Override
    public void registerOutParameter(final String parameterName, final int sqlType, final String typeName) throws SQLException {
        registerOutParameter(parameterName, sqlType);
    }

    @Override
    public URL getURL(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public void setURL(final String parameterName, final URL val) throws SQLException {
        setParameter(parameterName, val);
    }

    @Override
    public void setNull(final String parameterName, final int sqlType) throws SQLException {
        setParameter(parameterName, "null");
    }

    @Override
    public void setBoolean(final String parameterName, final boolean x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setByte(final String parameterName, final byte x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setShort(final String parameterName, final short x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setInt(final String parameterName, final int x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setLong(final String parameterName, final long x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setFloat(final String parameterName, final float x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setDouble(final String parameterName, final double x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setBigDecimal(final String parameterName, final BigDecimal x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setString(final String parameterName, final String x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setBytes(final String parameterName, final byte[] x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setDate(final String parameterName, final Date x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setTime(final String parameterName, final Time x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setTimestamp(final String parameterName, final Timestamp x) throws SQLException {
        setParameter(parameterName, x);
    }

    @Override
    public void setAsciiStream(final String parameterName, final InputStream x, final int length) throws SQLException {

    }

    @Override
    public void setBinaryStream(final String parameterName, final InputStream x, final int length) throws SQLException {

    }

    @Override
    public void setObject(final String parameterName, final Object x, final int targetSqlType, final int scale) throws SQLException {

    }

    @Override
    public void setObject(final String parameterName, final Object x, final int targetSqlType) throws SQLException {

    }

    @Override
    public void setObject(final String parameterName, final Object x) throws SQLException {

    }

    @Override
    public void setCharacterStream(final String parameterName, final Reader reader, final int length) throws SQLException {

    }

    @Override
    public void setDate(final String parameterName, final Date x, final Calendar cal) throws SQLException {

    }

    @Override
    public void setTime(final String parameterName, final Time x, final Calendar cal) throws SQLException {

    }

    @Override
    public void setTimestamp(final String parameterName, final Timestamp x, final Calendar cal) throws SQLException {

    }

    @Override
    public void setNull(final String parameterName, final int sqlType, final String typeName) throws SQLException {

    }

    @Override
    public String getString(final String parameterName) throws SQLException {
        return (String) getDataRow().getValue(parameterName, String.class);
    }

    @Override
    public boolean getBoolean(final String parameterName) throws SQLException {
        return (boolean) getDataRow().getValue(parameterName, boolean.class);
    }

    @Override
    public byte getByte(final String parameterName) throws SQLException {
        return (byte) getDataRow().getValue(parameterName, byte.class);
    }

    @Override
    public short getShort(final String parameterName) throws SQLException {
        return (short) getDataRow().getValue(parameterName, short.class);
    }

    @Override
    public int getInt(final String parameterName) throws SQLException {
        return (int) getDataRow().getValue(parameterName, int.class);
    }

    @Override
    public long getLong(final String parameterName) throws SQLException {
        return (long) getDataRow().getValue(parameterName, long.class);
    }

    @Override
    public float getFloat(final String parameterName) throws SQLException {
        return (float) getDataRow().getValue(parameterName, float.class);
    }

    @Override
    public double getDouble(final String parameterName) throws SQLException {
        return (double) getDataRow().getValue(parameterName, double.class);
    }

    @Override
    public byte[] getBytes(final String parameterName) throws SQLException {
        return (byte[]) getDataRow().getValue(parameterName, byte[].class);
    }

    @Override
    public Date getDate(final String parameterName) throws SQLException {
        return (Date) getDataRow().getValue(parameterName, Date.class);
    }

    @Override
    public Time getTime(final String parameterName) throws SQLException {
        return (Time) getDataRow().getValue(parameterName, Time.class);
    }

    @Override
    public Timestamp getTimestamp(final String parameterName) throws SQLException {
        return (Timestamp) getDataRow().getValue(parameterName, Timestamp.class);
    }

    @Override
    public Object getObject(final String parameterName) throws SQLException {
        return getDataRow().getValue(parameterName, Object.class);
    }

    @Override
    public BigDecimal getBigDecimal(final String parameterName) throws SQLException {
        return (BigDecimal) getDataRow().getValue(parameterName, BigDecimal.class);
    }

    @Override
    public Object getObject(final String parameterName, final Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref getRef(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Blob getBlob(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Clob getClob(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Array getArray(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Date getDate(final String parameterName, final Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time getTime(final String parameterName, final Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Timestamp getTimestamp(final String parameterName, final Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public URL getURL(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public RowId getRowId(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public RowId getRowId(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void setRowId(final String parameterName, final RowId x) throws SQLException {

    }

    @Override
    public void setNString(final String parameterName, final String value) throws SQLException {

    }

    @Override
    public void setNCharacterStream(final String parameterName, final Reader value, final long length) throws SQLException {

    }

    @Override
    public void setNClob(final String parameterName, final NClob value) throws SQLException {

    }

    @Override
    public void setClob(final String parameterName, final Reader reader, final long length) throws SQLException {

    }

    @Override
    public void setBlob(final String parameterName, final InputStream inputStream, final long length) throws SQLException {

    }

    @Override
    public void setNClob(final String parameterName, final Reader reader, final long length) throws SQLException {

    }

    @Override
    public NClob getNClob(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public NClob getNClob(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void setSQLXML(final String parameterName, final SQLXML xmlObject) throws SQLException {

    }

    @Override
    public SQLXML getSQLXML(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public SQLXML getSQLXML(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public String getNString(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public String getNString(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Reader getNCharacterStream(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Reader getNCharacterStream(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream(final int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream(final String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void setBlob(final String parameterName, final Blob x) throws SQLException {

    }

    @Override
    public void setClob(final String parameterName, final Clob x) throws SQLException {

    }

    @Override
    public void setAsciiStream(final String parameterName, final InputStream x, final long length) throws SQLException {

    }

    @Override
    public void setBinaryStream(final String parameterName, final InputStream x, final long length) throws SQLException {

    }

    @Override
    public void setCharacterStream(final String parameterName, final Reader reader, final long length) throws SQLException {

    }

    @Override
    public void setAsciiStream(final String parameterName, final InputStream x) throws SQLException {

    }

    @Override
    public void setBinaryStream(final String parameterName, final InputStream x) throws SQLException {

    }

    @Override
    public void setCharacterStream(final String parameterName, final Reader reader) throws SQLException {

    }

    @Override
    public void setNCharacterStream(final String parameterName, final Reader value) throws SQLException {

    }

    @Override
    public void setClob(final String parameterName, final Reader reader) throws SQLException {

    }

    @Override
    public void setBlob(final String parameterName, final InputStream inputStream) throws SQLException {

    }

    @Override
    public void setNClob(final String parameterName, final Reader reader) throws SQLException {

    }

    @Override
    public <T> T getObject(final int parameterIndex, final Class<T> type) throws SQLException {
        return null;
    }

    @Override
    public <T> T getObject(final String parameterName, final Class<T> type) throws SQLException {
        return null;
    }

    @Override
    public void registerOutParameter(final int parameterIndex, final SQLType sqlType){
        setOutParameter(parameterIndex);
    }

    @Override
    public void registerOutParameter(final int parameterIndex, final SQLType sqlType, final int scale){
        registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public void registerOutParameter(final int parameterIndex, final SQLType sqlType, final String typeName){
        registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public void registerOutParameter(final String parameterName, final SQLType sqlType){
        setOutParameter(parameterName);
    }

    @Override
    public void registerOutParameter(final String parameterName, final SQLType sqlType, final int scale){
        registerOutParameter(parameterName, sqlType);
    }

    @Override
    public void registerOutParameter(final String parameterName, final SQLType sqlType, final String typeName){
        registerOutParameter(parameterName, sqlType);
    }

    private void setOutParameter(final int parameterIndex) {
        setParameter(parameterIndex, "?");
    }

    private void setOutParameter(final String parameterName) {
        setParameter(parameterName, "?");
    }

    private Row getDataRow() throws SQLException {
        if(Objects.isNull(resultRow)){
            resultRow = dataSet.getNextRow();
        }
        return resultRow;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof JdbcCallableStatement)) return false;
        if (!super.equals(o)) return false;
        final JdbcCallableStatement that = (JdbcCallableStatement) o;
        return Objects.equals(resultRow, that.resultRow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultRow);
    }

    @Override
    public String toString() {
        return "JdbcCallableStatement{" +
                "resultRow=" + resultRow +
                ", dataSet=" + dataSet +
                '}';
    }
}
