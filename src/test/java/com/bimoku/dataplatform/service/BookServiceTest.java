package com.bimoku.dataplatform.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.BookDao;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.util.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class BookServiceTest {
	
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private BookDao bookDao;
	
	@Before
	public void setup() {
		List<Book> books = EntityGenerator.generateBooks(EntityGenerator.N);
		bookDao.save(books);
	}
	
	@Test
	public void shouldGetABookByISBN() {
		BookDTO dto = bookservice.findByIsbn("0");
		assertEquals("Book 0", dto.getName());
	}
	
	@Test
	public void shouldGetBooksOrderByPubPrice() {
	    List<BookDTO> books = bookservice.findAll(0, 2, "ASC", "PubPrice");
		assertEquals(2, books.size());
	}
}
