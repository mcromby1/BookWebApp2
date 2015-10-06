/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user test
 */
public interface AuthorDAOPlan {

    public int deleteAuthor(int pk) throws SQLException, ClassNotFoundException, Exception;

    public List<Author> findAllAuthors() throws SQLException, ClassNotFoundException, Exception;

    public void createNewAuthor(List columns, List values) throws SQLException, ClassNotFoundException, Exception;

    public void updateAuthor(List columns, List values) throws SQLException, ClassNotFoundException, Exception;

    public Author authorByPK(String pk) throws SQLException, ClassNotFoundException, Exception;

    public void updateAuthor(String columnName, String whereColName, Object colValue, Object whereValue) throws SQLException, ClassNotFoundException, Exception;

}
