package com.librarymanagementsystem.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagementsystem.domain.Book;
import com.librarymanagementsystem.repositories.BookRepository;

import java.util.List;

/**
 * 
 * @author furkanzumrut
 *
 */
@Service
public class BookService {

	private final BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book get(String id) {
		return bookRepository.findOne(id);
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public void delete(String bookId) {
		bookRepository.delete(bookId);
	}

	public List<Book> findByBookName(String bookName) {
		return bookRepository.findByBookName(bookName);
	}
}
