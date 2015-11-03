package edu.wctc.mrc.bookwebapp2.service;

import edu.wctc.mrc.bookwebapp2.entity.Author;
import edu.wctc.mrc.bookwebapp2.repository.AuthorRepository;
import edu.wctc.mrc.bookwebapp2.repository.BookRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user test
 */
@Repository("authorService")
@Transactional(readOnly = true)
public class AuthorService {

    private transient final Logger LOG = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    public AuthorService() {
    }

    public List<Author> findAll() {
        return authorRepo.findAll();
    }

    public Author findById(String id) {
        return authorRepo.findOne(new Integer(id));
    }

    public Author findByIdWithBooks(String id) {
        return authorRepo.findByIdAndFetchBooksEagerly(Integer.parseInt(id));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void remove(Author author) {
        LOG.debug("Deleting author: " + author.getAuthorName());
        authorRepo.delete(author);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Author edit(Author author) {
        return authorRepo.saveAndFlush(author);
    }

}
