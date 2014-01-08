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
import com.bimoku.dataplatform.dao.CollectedBookDao;
import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.CollectedBook;
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.entity.type.CollectionStatus;
import com.bimoku.dataplatform.util.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class BookServiceTest {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private BookDao dao;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private CollectedBookDao cBookDao;
	
	@Before
	public void setup() {
		List<Book> books = EntityGenerator.generateBooks(EntityGenerator.N);
		dao.save(books);
		User user = EntityGenerator.generateUser("User");
		userDao.save(user);
		CollectedBook cBook = new CollectedBook(user, books.get(0), CollectionStatus.READING);
		cBookDao.save(cBook);
		user.getCollectedBooks().add(cBook);
		user.getLikeBooks().add(books.get(0));
		user.getSearchBooks().add(books.get(0));
	}
	
	@Test
	public void shouldGetABookByISBN() {
		BookDTO dto = service.findByIsbn("0");
		assertEquals("Book 0", dto.getName());
	}
	
	@Test
	public void shouldGetBooksOrderByPubPrice() {
	    List<BookDTO> books = service.findAll(0, 5, "DESC", "PubPrice");
		assertEquals(14, books.get(0).getPubPrice(), 0.01);
	}
	
	@Test
	public void shouldGetBooksViaUniversalSearch() {
		assertEquals(2, service.universalSearch("Book", 0, 2).size());
	}

	@Test
	public void shouldGetCollectedBooks() {
		assertEquals(1, service.findCollectedBooks("User", 0, 1).size());
	}
	
	@Test
	public void shouldGetLikeBooks() {
		assertEquals(1, service.findLikedBooks("User", 0, 1).size());
	}
	
	@Test
	public void shouldGetSearchedBooks() {
		assertEquals(1, service.findSearchedBooks("User", 0, 1).size());
	}
	
}
