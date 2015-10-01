/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.activation.DataSource;

/**
 *
 * @author user test
 */
public class ConnPoolAuthorDAO implements AuthorDAOPlan {

    private final DBAccessorPlan dataBase;
    private final DataSource dataSource;

    public ConnPoolAuthorDAO(DataSource ds, DBAccessorPlan db) {
        this.dataBase = db;
        this.dataSource = ds;
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

}
