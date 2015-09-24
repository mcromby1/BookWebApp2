package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author user test
 */
public class AuthorDAO {

    private MySQLDb dataBase;

    public List<Author> findAllAuthors() throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
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
        return authors;
    }

}
