package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.*;
import javax.sql.DataSource;

/**
 *
 * @author user test
 */
public class AuthorDAO implements AuthorDAOPlan {

    private DBAccessorPlan dataBase;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataSource dataSource;

    /**
     *
     * @param db
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     */
    public AuthorDAO(DBAccessorPlan db, String driverClass, String url, String userName, String password) {
        this.dataBase = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public AuthorDAO(DBAccessorPlan db, DataSource ds) {
        this.dataBase = db;
        this.dataSource = ds;
    }

    private void whatConnectionType() throws ClassNotFoundException, SQLException, Exception {
        if (dataSource == null) {
            dataBase.openConnection(driverClass, url, userName, password);
        } else if (dataSource != null) {
            dataBase.openConnection(dataSource);
        }
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Author> findAllAuthors() throws SQLException, ClassNotFoundException, Exception {
        whatConnectionType();
        List<Map<String, Object>> results;
        List<Author> authors = new ArrayList<>();

        results = dataBase.findAllRecords("author");

        for (Map a : results) {
            Author author = new Author();
            Object obj = a.get("author_id");
            author.setAuthorId(Integer.parseInt(obj.toString()));
            String name = a.get("author_name") == null ? "" : a.get("author_name").toString();
            author.setAuthorName(name);
            obj = a.get("date_added");
            Date dateAdded = (obj == null) ? new Date() : (Date) a.get("date_added");
            author.setDateCreated(dateAdded);
            authors.add(author);
        }
        dataBase.closeConnection();
        return authors;
    }

    /**
     *
     * @param pk
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public int deleteAuthor(int pk) throws SQLException, ClassNotFoundException, Exception {
        whatConnectionType();
        int recordDeleted = dataBase.deleteByPKPS("author", "author_id", pk);
        dataBase.closeConnection();
        return recordDeleted;
    }

    /**
     *
     * @param columns
     * @param values
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public void createNewAuthor(List columns, List values) throws SQLException, ClassNotFoundException, Exception {
        whatConnectionType();

        dataBase.insertRecord("author", columns, values);
        dataBase.closeConnection();

    }

    @Override
    public void updateAuthor(List columns, List values) throws SQLException, ClassNotFoundException, Exception {
        whatConnectionType();
        dataBase.updateRecord(userName, userName, userName, values, userName);
        dataBase.closeConnection();
    }

    /**
     *
     * @param pk
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public Author authorByPK(String pk) throws SQLException, ClassNotFoundException, Exception {
        whatConnectionType();
        Map<String, Object> record
                = dataBase.findRecordByPK("author", "author_id", pk);

        Author author = new Author();
        Object obj = record.get("author_id");
        author.setAuthorId(Integer.parseInt(obj.toString()));
        String name = record.get("author_name") == null ? "" : record.get("author_name").toString();
        author.setAuthorName(name);
        obj = record.get("date_added");
        Date dateAdded = (obj == null) ? new Date() : (Date) record.get("date_added");
        author.setDateCreated(dateAdded);

        dataBase.closeConnection();
        return author;
    }

    /**
     *
     * @param columnName
     * @param whereColName
     * @param colValue
     * @param whereValue
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public void updateAuthor(String columnName, String whereColName, Object colValue, Object whereValue) throws SQLException, ClassNotFoundException, Exception {
        whatConnectionType();
        dataBase.updateRecord("author", columnName, whereColName, colValue, whereValue);
    }

}
