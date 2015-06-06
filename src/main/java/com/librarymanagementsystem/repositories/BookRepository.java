package com.librarymanagementsystem.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.librarymanagementsystem.domain.Book;

import java.util.List;

/**
 * Spring Data Repository for Book to add basic crud operations and for our new methods.
 * @author furkanzumrut
 *
 */
public interface BookRepository extends MongoRepository<Book, String> {

    
	/**
	 * 
	 * @param bookName
	 * @return It returns Book List according to BookName.
	 */
    List<Book> findByBookName(String bookName);
    
    /**
     * 
     * @param authorName
     * @return It returns Author List according to AuthorName.
     */
    List<Book> findByAuthorName(String authorName);
    
    

}
 