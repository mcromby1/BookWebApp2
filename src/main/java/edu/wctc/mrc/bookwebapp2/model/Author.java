package edu.wctc.mrc.bookwebapp2.model;

import java.util.Date;

/**
 *
 * @author user test
 */
public class Author {

    private int authorId;
    private String name;
    private Date date;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
