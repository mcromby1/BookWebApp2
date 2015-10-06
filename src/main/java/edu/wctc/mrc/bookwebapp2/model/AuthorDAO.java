package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author user test
 */
public class AuthorDAO implements AuthorDAOPlan {

    private final DBAccessorPlan dataBase;
    private final String driverClass;
    private final String url;
    private final String userName;
    private final String password;

    public AuthorDAO(DBAccessorPlan db, String driverClass, String url, String userName, String password) {
        this.dataBase = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public List<Author> findAllAuthors() throws SQLException, ClassNotFoundException {
        dataBase.openConnection(driverClass, url, userName, password);
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

    @Override
    public int deleteAuthor(int pk) throws SQLException, ClassNotFoundException {
        dataBase.openConnection(driverClass, url, userName, password);
        int recordDeleted = dataBase.deleteByPKPS("author", "author_id", pk);
        dataBase.closeConnection();
        return recordDeleted;
    }

    @Override
    public void createNewAuthor(List columns, List values) throws SQLException, ClassNotFoundException {
        dataBase.openConnection(driverClass, url, userName, password);

        dataBase.insertRecord("author", columns, values);
        dataBase.closeConnection();

    }

    @Override
    public void updateAuthor(List columns, List values) throws SQLException, ClassNotFoundException {
        dataBase.openConnection(driverClass, url, userName, password);
        dataBase.updateRecord(userName, userName, userName, values, userName);
        dataBase.closeConnection();
    }

    @Override
    public Author authorByPK(String pk) throws SQLException, ClassNotFoundException {
        dataBase.openConnection(driverClass, url, userName, password);
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

    @Override
    public void updateAuthor(String columnName, String whereColName, Object colValue, Object whereValue) throws SQLException, ClassNotFoundException {
        dataBase.openConnection(driverClass, url, userName, password);
        dataBase.updateRecord("author", columnName, whereColName, colValue, whereValue);
    }

}
