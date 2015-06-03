package com.librarymanagementsystem.web.rest;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.librarymanagementsystem.domain.Book;
import com.librarymanagementsystem.exceptions.BookNotFoundException;
import com.librarymanagementsystem.helper.CaptchaHelper;
import com.librarymanagementsystem.service.BookService;

import java.util.List;

import javax.validation.Valid;

/**
 * Rest Service for Book Entity.
 * @author furkanzumrut
 *
 */
@RestController
@RequestMapping("/rest/book")
public class BookResource {

	private static final Logger log = LoggerFactory.getLogger(BookResource.class);
	 
    private final BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * @return It returns all books in our database.
     */
    @RequestMapping("/")
    public List<Book> all() {
        return bookService.findAll();
    }

    
    /**
     * 
     * @param bookId
     * @return It returns book object according to id.
     */
    @RequestMapping("{bookId}")
    public ResponseEntity<Book> get(@PathVariable("bookId") String bookId) throws BookNotFoundException{
    	Book book;
    	try {
        	book = bookService.get(bookId);
        	if(book == null) throw new BookNotFoundException();
		} catch (BookNotFoundException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<Book>(book,HttpStatus.OK);
    }

    /**
     * 
     * @param book
     * @return It returns saved object.
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseEntity<Book> save(@RequestBody @Valid Book book) {
    	try {
    		bookService.save(book);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<Book>(book,HttpStatus.OK);
    }

    /**
     * 
     * @param bookId
     * @return It returns true if validation is successful.
     */
    
    @RequestMapping(value = "validate", method = RequestMethod.POST)
    public ResponseEntity<Boolean> validateCaptcha(@RequestBody String resp) {
        Boolean valid = false;
    	try {
			valid = new CaptchaHelper().validateCaptcha(resp);
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
    	
    	if(valid) {return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);}
    	else {return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);}
    }
    
    /**
     * 
     * @param bookId
     * @return It deletes a book in database according to given id.
     */
    @RequestMapping(value = "delete/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("bookId") String bookId) {
        try {
			bookService.delete(bookId);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
		}
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    /**
     * 
     * @param book 
     * @param bookId Book will be updated according to bookId
     * @return It returns book object if it saves object.
     */
    @RequestMapping(value = "update/{bookId}",method = RequestMethod.PUT)
    public ResponseEntity<Book> update(@PathVariable("bookId") String bookId, @RequestBody @Valid Book book) {
    	try {
    		bookService.save(book);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<Book>(book,HttpStatus.OK);
    }  

}
