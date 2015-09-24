package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user test
 */
public class AuthorService {

    private AuthorDAO aDAO;

    public AuthorService(AuthorDAO aDAO) {
        this.aDAO = aDAO;
    }

    public List<Author> getAllAuthors() throws SQLException, ClassNotFoundException {
        return aDAO.findAllAuthors();
    }

}
