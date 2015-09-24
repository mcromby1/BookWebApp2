/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user test
 */
public interface DBAccessorPlan {

    /**
     *
     * @throws SQLException
     */
    void closeConnection() throws SQLException;

    /**
     *
     * @param tableName
     * @param primaryKeyFieldName
     * @param primaryKeyValue
     * @return
     * @throws SQLException
     */
    int deleteByPK(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws SQLException;

    /**
     *
     * @param tableName
     * @param primaryKeyFieldName
     * @param primaryKeyValue
     * @return
     * @throws SQLException
     */
    int deleteByPKPS(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws SQLException;

    /**
     *
     * @param tableName
     * @param columnName
     * @param value
     * @return
     * @throws SQLException
     */
    int deleteRecord(String tableName, String columnName, Object value) throws SQLException;

    /**
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    List<Map<String, Object>> findAllRecords(String tableName) throws SQLException;

    /**
     *
     * @param tableName
     * @param columnName
     * @param colValue
     * @return
     * @throws SQLException
     */
    int insertRecord(String tableName, List columnName, List colValue) throws SQLException;

    /**
     *
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;

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
    int updateRecord(String tableName, String columnName, String whereColName, Object colValue, Object whereValue) throws SQLException;

}
