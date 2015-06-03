package com.librarymanagementsystem;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;
import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import scala.annotation.meta.getter;

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
@IntegrationTest("server.port:8080")
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
	public void testGetById(){
		bookServiceMock = mock(BookService.class);
		when(bookServiceMock.get("556dd909e4b09ce64f6aceff")).thenReturn(new Book("The Dead Zone","Stephen King"));
	}
	
	@Test
	public void testDeleteBook() {
		bookServiceMock = mock(BookService.class);
		bookServiceMock.delete("556dd909e4b09ce64f6aceff");
		when(bookServiceMock.get("556dd909e4b09ce64f6aceff")).thenReturn(null);
		
	}
	
	@Test
	public void testRest() throws Exception{
	       ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
	                "http://localhost:" + "8080" + "/rest/book/", String.class);
	        assertEquals(HttpStatus.OK, entity.getStatusCode());
	        assertEquals(true, entity.hasBody());
	        assertEquals(true, entity.getBody().contains("bookName"));
         
	}
	
	@Test
	public void testWillGetMethodGive404WhenBookNotFound(){
	       ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
	                "http://localhost:" + "8080" + "/rest/book/get/11111", String.class);
	        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
	}
	
}
