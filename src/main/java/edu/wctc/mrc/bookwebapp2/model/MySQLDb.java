package edu.wctc.mrc.bookwebapp2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Matthew Cromby
 */
public class MySQLDb implements DBAccessorPlan {

    private Connection conn;

    /**
     *
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @param value
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteRecord(String tableName, String columnName, Object value) throws SQLException {
        String sql;
        if (value instanceof String) {
            sql = "Delete From " + tableName + " Where " + columnName + " = '" + value.toString() + "';";
        } else {
            sql = "Delete From " + tableName + " Where " + columnName + " = " + value.toString() + ";";
        }
        Statement stmt = conn.createStatement();
        int count = stmt.executeUpdate(sql);
//        System.out.println(count);
        closeConnection();

        return count;

    }

    /**
     *
     * @param tableName
     * @param primaryKeyFieldName
     * @param primaryKeyValue
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteByPK(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyFieldName + " = ";
        if (primaryKeyValue instanceof String) {
            sql += "'" + primaryKeyValue.toString() + "'";
        } else {
            sql += primaryKeyValue.toString();
        }

        Statement stmt = conn.createStatement();

        int recordsDeleted = stmt.executeUpdate(sql);
        closeConnection();
        return recordsDeleted;
    }

    /**
     *
     * @param tableName
     * @param primaryKeyFieldName
     * @param primaryKeyValue
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteByPKPS(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyFieldName + " = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setObject(1, primaryKeyValue);

        int recordsDeleted = pstmt.executeUpdate();
        closeConnection();
        return recordsDeleted;
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @param whereColName
     * @param colValue
     * @param whereValue
     * @return
     * @throws SQLException
     */
    @Override
    public int updateRecord(String tableName, String columnName, String whereColName, Object colValue, Object whereValue) throws SQLException {
        PreparedStatement pstmt = createUpdateStatement(tableName, columnName, whereColName);

        pstmt.setObject(1, colValue);
        pstmt.setObject(2, whereValue);

        System.out.println(pstmt.toString());
        int updateCount = pstmt.executeUpdate();
        closeConnection();
        return updateCount;
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @return
     * @throws SQLException
     */
    private PreparedStatement createUpdateStatement(String tableName, String columnName, String whereColName) throws SQLException {
        String sql = "Update " + tableName + " Set " + columnName + " = ? Where " + whereColName + " = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        System.out.println(sql);
        return pstmt;
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @param colValue
     * @return
     * @throws SQLException
     */
    @Override
    public int insertRecord(String tableName, List columnName, List colValue) throws SQLException {
        PreparedStatement pstmt = createInsertStatement(tableName, columnName);

        for (int i = 0; i < colValue.size(); i++) {
            int c = i + 1;
            pstmt.setObject(c, colValue.get(i));
        }

        int updateCount = pstmt.executeUpdate();
        closeConnection();
        return updateCount;
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @return
     * @throws SQLException
     */
    private PreparedStatement createInsertStatement(String tableName, List columnName) throws SQLException {
        StringBuffer sql = new StringBuffer("Insert Into " + tableName + " (");
        if (columnName.size() > 1) {
            for (Object cn : columnName) {
                sql.append(cn).append(", ");
            }
        } else {
            sql.append(columnName.get(0));
        }
        sql.append(") Values (");
        if (columnName.size() > 1) {
            for (Object columnName1 : columnName) {
                sql.append("?, ");
            }
        } else {
            sql.append("?");
        }
        sql.append(");");

        System.out.println(sql);
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        return pstmt;
    }

    /**
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> findAllRecords(String tableName) throws SQLException {
        List<Map<String, Object>> records = new ArrayList<>();
        String sql = "Select * From " + tableName;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println(columnCount);
        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
            records.add(record);
        }
        closeConnection();
        return records;
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/book";
        String userName = "root";
        String password = "admin";

        String tableName = "author";
        MySQLDb dB = new MySQLDb();
        dB.openConnection(driverClassName, url, userName, password);

//        dB.deleteRecord("author", "author_name", "Chris Bolton");
        List<String> colName = new ArrayList<>();
        List<String> colValue = new ArrayList<>();
        colName.add("author_name");
        colValue.add("Chris Bolton");

        dB.insertRecord(tableName, colName, colValue);
        dB.updateRecord(tableName, "date_created", "author_name", "2014-12-12", "Chris Bolton");
        List<Map<String, Object>> results;

        results = dB.findAllRecords("author");

        for (Map record : results) {
            System.out.println(record);
        }
        dB.closeConnection();

    }

}
