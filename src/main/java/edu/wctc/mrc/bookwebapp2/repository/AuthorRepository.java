/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp2.repository;

import edu.wctc.mrc.bookwebapp2.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author user test
 */
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {

    @Query("SELECT a FROM Author a JOIN FETCH a.bookset WHERE a.authorId = (:id)")
    public Author findByIdAndFetchBooksEagerly(@Param("id") Integer id);
    
    @Query("SELECT a.authorName FROM Author a")
    public Object[] finaAllWithNameOnly();
}
