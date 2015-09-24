package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author user test
 */
public class AuthorDAO {

    private DBAccessorPlan dataBase;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDAO(DBAccessorPlan db, String driverClass, String url, String userName, String password) {
        this.dataBase = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

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
            author.setName(name);

            obj = a.get("date_added");
            Date dateAdded = (obj == null) ? new Date() : (Date) a.get("date_added");
            author.setDate(dateAdded);
            authors.add(author);
        }
        dataBase.closeConnection();
        return authors;
    }

}
