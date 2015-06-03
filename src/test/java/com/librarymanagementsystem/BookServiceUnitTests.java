package com.librarymanagementsystem;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;

import com.librarymanagementsystem.Application;
import com.librarymanagementsystem.domain.Book;
import com.librarymanagementsystem.service.BookService;

import static org.mockito.Mockito.when;

/**
 * We are testing BookService methods here.
 * 
 * @author furkanzumrut
 *
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
public class BookServiceUnitTests {

	// Creating Mock object for BookService
	private BookService bookServiceMock;
	
	

	@Test
	public void testSave() {
		Book book1 = new Book("The Dark Tower", "Stephen King");
		bookServiceMock = mock(BookService.class);
		when(bookServiceMock.save(book1)).thenReturn(book1);

	}

	@Test
	public void testFindAll() {

		
	}

	@Test
	public void testFindByBookName() {
		// TODO: Do it later.
	}

	@Test
	public void testDeleteBook() {
		
		bookServiceMock = mock(BookService.class);
		//when(bookServiceMock.delete(bookId)).thenReturn(book1);
	}
}
