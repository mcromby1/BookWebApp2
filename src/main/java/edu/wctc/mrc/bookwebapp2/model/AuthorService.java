package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user test
 */
public class AuthorService {

    private AuthorDAO aDAO;

    public List<Author> getAllAuthors() throws SQLException {
        return aDAO.findAllAuthors();
    }

}
