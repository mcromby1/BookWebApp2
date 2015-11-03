package edu.wctc.mrc.bookwebapp2.repository;

import edu.wctc.mrc.bookwebapp2.entity.Book;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user test
 */
public interface BookRepository extends JpaRepository<Book, Integer>, Serializable {

}
