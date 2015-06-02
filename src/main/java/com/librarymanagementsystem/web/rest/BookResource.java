package com.librarymanagementsystem.web.rest;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.librarymanagementsystem.domain.Book;
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


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    /**
     * 
     * @param bookId
     * @return It returns book object according to id.
     */
    @RequestMapping("{bookId}")
    public Book get(@PathVariable("bookId") String bookId) {
        return bookService.get(bookId);
    }

    /**
     * 
     * @param book
     * @return 
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Book save(@RequestBody @Valid Book book) {
    	
        return bookService.save(book);
    }

    /**
     * 
     * @param bookId
     * @return 
     */
    
    @RequestMapping(value = "validate", method = RequestMethod.POST)
    public ResponseEntity<Boolean> validateCaptcha(@RequestBody String resp) {
        Boolean valid = false;
    	try {
			valid = new CaptchaHelper().validateCaptcha(resp);
		} catch (Exception e) {
			
			e.printStackTrace();
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
        bookService.delete(bookId);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    /**
     * 
     * @param book
     * @return 
     */
    @RequestMapping(value = "update/{bookId}",method = RequestMethod.PUT)
    public Book update(@PathVariable("bookId") String bookId, @RequestBody @Valid Book book) {
    	
        return bookService.save(book);
    }  

}
