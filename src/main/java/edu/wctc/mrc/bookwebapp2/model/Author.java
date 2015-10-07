package edu.wctc.mrc.bookwebapp2.model;

import java.util.Date;

/**
 *
 * @author user test
 */
public class Author {

    private int authorId;
    private String authorName;
    private Date dateCreated;

    /**
     *
     */
    public Author() {
    }

    /**
     *
     * @param authorName
     */
    public Author(String authorName) {
        this.authorName = authorName;
    }

    /**
     *
     * @param authorName
     * @param date
     */
    public Author(String authorName, Date date) {
        this.authorName = authorName;
        this.dateCreated = date;
    }

    /**
     *
     * @param authorId
     * @param authorName
     * @param date
     */
    public Author(int authorId, String authorName, Date date) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateCreated = date;
    }

    /**
     *
     * @return
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     *
     * @param authorId
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     *
     * @return
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     *
     * @param authorName
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     *
     * @return
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date date) {
        this.dateCreated = date;
    }

}
