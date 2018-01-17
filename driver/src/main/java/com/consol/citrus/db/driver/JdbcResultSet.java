/*
 * Copyright 2006-2017 the original author or authors.
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

import com.consol.citrus.db.driver.dataset.DataSet;
import com.consol.citrus.db.driver.data.Row;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * @author Christoph Deppisch
 */
public class JdbcResultSet implements java.sql.ResultSet {

    /** Remote ResultSet */
    private DataSet dataSet;

    //The current ResultSet data row
    private Row row;

    /**
     * Constructor using remote result set.
     */
    public JdbcResultSet(DataSet dataSet) throws SQLException {
        this.dataSet = dataSet;
    }

    @Override
    public boolean next() throws SQLException {
        try {
            row = dataSet.getNextRow();
        } catch(SQLException ex) {
            throw ex;
        } catch(Exception ex) {
            return false;
        }

        return row != null;
    }

    @Override
    public void close()	throws SQLException {
        dataSet.close();
    }

    public String getString(int columnIndex) throws SQLException {
        Object columnData = row.getValue(columnIndex-1);

        if (columnData == null) {
            return null;
        } else {
            return (String)columnData;
        }
    }

    public String getString(String columnName) throws SQLException {
        Object columnData = row.getValue(columnName);
        if (columnData == null) {
            return null;
        } else {
            return (String)columnData;
        }
    }

    public float getFloat(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return 0;
        } else {
            String flotObj = row.getValue(columnIndex-1);
            return Float.valueOf(flotObj);
        }
    }

    public float getFloat(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return 0;
        } else {
            String flotObj = row.getValue(columnName);
            return Float.valueOf(flotObj);
        }
    }

    public int getInt(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return 0;
        } else {
            String currentObj = row.getValue(columnIndex-1);
            return Integer.valueOf(currentObj);
        }
    }

    public int getInt(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return 0;
        } else {
            String currentObj = row.getValue(columnName);
            return Integer.valueOf(currentObj);
        }
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1) == null) {
            return false;
        } else {
            String boolObj = row.getValue(columnIndex-1);
            return Boolean.valueOf(boolObj);
        }
    }

    public byte getByte(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return 0;
        } else {
            String byeObj = row.getValue(columnIndex-1);
            return Byte.valueOf(byeObj);
        }
    }

    public short getShort(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return 0;
        } else {
            String sortObj = row.getValue(columnIndex-1);
            return Short.valueOf(sortObj);
        }
    }

    public long getLong(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return 0;
        } else {
            String langObj = row.getValue(columnIndex-1);
            return Long.valueOf(langObj);
        }
    }

    public double getDouble(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return 0;
        } else {
            String dubObj = row.getValue(columnIndex-1);
            return Double.valueOf(dubObj);
        }
    }

    public BigDecimal getBigDecimal(int columnIndex,int scale) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        return row.getValue(columnIndex-1).getBytes();
    }

    public Date getDate(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        String datObj = row.getValue(columnIndex-1);
        return Date.valueOf(datObj);
    }

    public Time getTime(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        String timObj = row.getValue(columnIndex-1);
        return Time.valueOf(timObj);
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        String timstmpObj = row.getValue(columnIndex-1);
        return Timestamp.valueOf(timstmpObj);
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        byte[] byteArray = row.getValue(columnIndex-1).getBytes();
        return new ByteArrayInputStream(byteArray);
    }

    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        byte[] byteArray = row.getValue(columnIndex-1).getBytes();
        return new ByteArrayInputStream(byteArray);
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        byte[] byteArray = row.getValue(columnIndex-1).getBytes();
        return new ByteArrayInputStream(byteArray);
    }

    public Object getObject(int columnIndex) throws SQLException {
        return row.getValue(columnIndex-1);
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        String bigdStr = row.getValue(columnIndex-1);
        Long bigdObj = Long.valueOf(bigdStr);
        return BigDecimal.valueOf(bigdObj);
    }

    public boolean getBoolean(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return false;
        } else {
            String currentObj = row.getValue(columnName);
            return Boolean.valueOf(currentObj);
        }
    }

    public byte getByte(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return 0;
        } else {
            String byeObj = row.getValue(columnName);
            return Byte.valueOf(byeObj);
        }
    }

    public short getShort(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return 0;
        } else {
            String sortObj = row.getValue(columnName);
            return Short.valueOf(sortObj);
        }
    }

    public long getLong(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return 0;
        } else {
            String langObj = row.getValue(columnName);
            return Long.valueOf(langObj);
        }
    }

    public double getDouble(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return 0;
        } else {
            String dubObj = row.getValue(columnName);
            return Double.valueOf(dubObj);
        }
    }

    public BigDecimal getBigDecimal(String columnName,int scale) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public byte[] getBytes(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        } else {
            return row.getValue(columnName).getBytes();
        }
    }

    public Date getDate(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        }

        String dateObj = row.getValue(columnName);
        return Date.valueOf(dateObj);
    }

    public Time getTime(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        }

        String timObj = row.getValue(columnName);
        return Time.valueOf(timObj);
    }

    public Timestamp getTimestamp(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        }

        String timstmpObj = row.getValue(columnName);
        return Timestamp.valueOf(timstmpObj);
    }

    public Object getObject(String columnName) throws SQLException {
        return row.getValue(columnName);
    }

    public BigDecimal getBigDecimal(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        }

        String bigdStr = row.getValue(columnName);
        Long bigdObj = Long.valueOf(bigdStr);
        return BigDecimal.valueOf(bigdObj);
    }

    public InputStream getAsciiStream(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        }

        byte[] byteArray = row.getValue(columnName).getBytes();
        return new ByteArrayInputStream(byteArray);
    }

    public InputStream getUnicodeStream(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        }

        byte[] byteArray = row.getValue(columnName).getBytes();
        return new ByteArrayInputStream(byteArray);
    }

    public InputStream getBinaryStream(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        }

        byte[] byteArray = row.getValue(columnName).getBytes();
        return new ByteArrayInputStream(byteArray);
    }

    public SQLWarning getWarnings() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public void clearWarnings() throws SQLException {
    }

    public String getCursorName() throws SQLException {
        return "";
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return new JdbcResultSetMetaData(dataSet);
    }

    public int findColumn(String columnName) throws SQLException {
        return row.getColumns().indexOf(columnName);
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        if (row.getValue(columnIndex-1)==null) {
            return null;
        }

        byte[] byteArray = row.getValue(columnIndex-1).getBytes();
        return new InputStreamReader(new ByteArrayInputStream(byteArray));
    }

    public Reader getCharacterStream(String columnName) throws SQLException {
        if (row.getValue(columnName)==null) {
            return null;
        }

        byte[] byteArray = row.getValue(columnName).getBytes();
        return new InputStreamReader(new ByteArrayInputStream(byteArray));
    }

    public boolean isBeforeFirst() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public boolean isAfterLast() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public boolean isFirst() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public boolean isLast() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public void beforeFirst() throws SQLException {
    }

    public void afterLast() throws SQLException {
    }

    public boolean first() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public boolean last() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public int getRow() throws SQLException {
        return dataSet.getCursor() + 1;
    }

    public boolean absolute(int row) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public boolean relative(int rows) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public boolean previous() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public void setFetchDirection(int direction) throws SQLException {
    }

    public int getFetchDirection() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public void setFetchSize(int rows) throws SQLException {
    }

    public int getFetchSize() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public int getType() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public int getConcurrency() throws SQLException {
        throw new SQLException("Not Supported");
    }

    public boolean rowUpdated() throws SQLException {
        return dataSet.getRows().size() > 0;
    }

    public boolean rowInserted() throws SQLException {
        return dataSet.getRows().size() > 0;
    }

    public boolean rowDeleted() throws SQLException {
        return dataSet.getRows().size() > 0;
    }

    public void updateNull(int columnIndex) throws SQLException {
    }

    public void updateBoolean(int columnIndex,boolean x) throws SQLException {
    }

    public void updateByte(int columnIndex, byte x) throws SQLException {
    }

    public void updateShort(int columnIndex,short x) throws SQLException {
    }

    public void updateInt(int columnIndex,int x) throws SQLException {
    }

    public void updateLong(int columnIndex,long x) throws SQLException {
    }

    public void updateFloat(int columnIndex,float x) throws SQLException {
    }

    public void updateDouble(int columnIndex,double x) throws SQLException {
    }

    public void updateBigDecimal(int columnIndex,BigDecimal x) throws SQLException {
    }

    public void updateString(int columnIndex,String x) throws SQLException {
    }

    public void updateBytes(int columnIndex,byte[] x) throws SQLException {
    }

    public void updateDate(int columnIndex,Date x) throws SQLException {
    }

    public void updateTime(int columnIndex,Time x) throws SQLException {
    }

    public void updateTimestamp(int columnIndex,Timestamp x) throws SQLException {
    }

    public void updateBinaryStream(int columnIndex,InputStream x,int length) throws SQLException {
    }

    public void updateCharacterStream(int columnIndex,Reader x,int length) throws SQLException {
    }

    public void updateObject(int columnIndex,Object x,int scale) throws SQLException {
    }

    public void updateObject(int columnIndex,Object x) throws SQLException {
    }

    public void updateNull(String columnName) throws SQLException {
    }

    public void updateByte(String columnName, byte x) throws SQLException {
    }

    public void updateShort(String columnName, short x) throws SQLException {
    }

    public void updateInt(String columnName,int x) throws SQLException {
    }

    public void updateLong(String columnName,long x) throws SQLException {
    }

    public void updateFloat(String columnName, float x) throws SQLException {
    }

    public void updateDouble(String columnName,double x) throws SQLException {
    }

    public void updateBigDecimal(String columnName,BigDecimal x) throws SQLException {
    }

    public void updateString(String columnName,String x) throws SQLException {
    }

    public void updateBytes(String columnName,byte[] x) throws SQLException {
    }

    public void updateDate(String columnName,Date x) throws SQLException {
    }

    public void updateTime(String columnName, Time x) throws SQLException {
    }

    public void updateTimestamp(String columnName,Timestamp x) throws SQLException {
    }

    public void updateAsciiStream(String columnName,InputStream x,int length) throws SQLException {
    }

    public void updateBinaryStream(String columnName,InputStream x,int length) throws SQLException {
    }

    public void updateCharacterStream(String columnName,Reader reader,int length) throws SQLException {
    }

    public void updateObject(String columnName,Object x,int scale) throws SQLException {
    }

    public void updateObject(String columnName,Object x) throws SQLException {
    }

    public void insertRow() throws SQLException {
    }

    public void updateRow()throws SQLException {
    }

    public void deleteRow()  throws SQLException {
    }

    public void refreshRow()  throws SQLException {
    }

    public void cancelRowUpdates() throws SQLException {
    }

    public void moveToInsertRow() throws SQLException {
    }

    public void moveToCurrentRow() throws SQLException {
    }

    public Statement getStatement()  throws SQLException {
        throw new SQLException("Not Supported");
    }


    public Date getDate(int columnIndex,Calendar cal) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Date getDate(String columnName,Calendar cal) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Time getTime(int columnIndex,Calendar cal) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Time getTime(String columnName,Calendar cal) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Timestamp getTimestamp(int columnIndex,Calendar cal) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Timestamp getTimestamp(String columnName,Calendar cal) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException  {
    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException  {
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException  {
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException  {
    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException  {
    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException  {
    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException  {
    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException  {
    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException  {
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException  {
    }

    @Override
    public int getHoldability() throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException  {
    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException  {
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException  {
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException  {
    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException  {
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException  {
    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException  {
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException  {
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException  {
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException  {
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException  {
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException  {
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException  {
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException  {
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException  {
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException  {
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException  {
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException  {
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException  {
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException  {
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException  {
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException  {
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException  {
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException  {
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException  {
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException  {
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException  {
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException  {
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException  {
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException  {
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException  {
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException  {
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException  {
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public boolean wasNull()throws SQLException {
        throw new SQLException("Not Supported");
    }

    public void updateBoolean(String columnName, boolean x) throws SQLException {
    }


    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
    }

    public Object getObject(int i, Map map) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Ref getRef(int i) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Blob getBlob(int i) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Clob getClob(int i) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Array getArray(int i) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Object getObject(String colName, Map map) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Ref getRef(String colName) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Blob getBlob(String colName) throws SQLException {
        throw new SQLException("Not Supported");
    }

    public Clob getClob(String colName) throws SQLException {
        throw new SQLException("Not Supported");
    }
    public Array getArray(String colName) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException("Not Supported");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLException("Not Supported");
    }
}