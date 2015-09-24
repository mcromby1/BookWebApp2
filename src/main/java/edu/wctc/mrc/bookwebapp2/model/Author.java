package edu.wctc.mrc.bookwebapp2.model;

import java.util.Date;

/**
 *
 * @author user test
 */
public class Author {

    private int authorId;
    private String authorName;
    private Date date;

    public Author() {
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(String authorName, Date date) {
        this.authorName = authorName;
        this.date = date;
    }

    public Author(int authorId, String authorName, Date date) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.date = date;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setName(String authorName) {
        this.authorName = authorName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
