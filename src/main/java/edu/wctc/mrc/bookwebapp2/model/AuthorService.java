package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user test
 */
public class AuthorService {

    private final AuthorDAOPlan aDAO;

    public AuthorService(AuthorDAO aDAO) {
        this.aDAO = aDAO;
    }

    public List<Author> getAllAuthors() throws SQLException, ClassNotFoundException {
        return aDAO.findAllAuthors();
    }

    public void deleteAuthor(int pk) throws SQLException, ClassNotFoundException {
        aDAO.deleteAuthor(pk);
    }

    public void addAuthor(List columns, List values) throws SQLException, ClassNotFoundException {
        aDAO.createNewAuthor(columns, values);
    }

    public Author searchAuthorByID(String pk) throws SQLException, ClassNotFoundException {
        return aDAO.authorByPK(pk);
    }

    public void updateAuthor(String columnName, String whereColName, Object colValue, Object whereValue) throws SQLException, ClassNotFoundException {
        aDAO.updateAuthor(columnName, whereColName, colValue, whereValue);
    }

    public static void main(String[] args) {

    }

}
