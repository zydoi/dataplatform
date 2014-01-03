package com.bimoku.dataplatform.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.entity.AssociatedTag;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Press;
import com.bimoku.dataplatform.entity.Tag;
import com.bimoku.dataplatform.util.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class BookDaoTest {
	
	private static final int N = 5;
	
	@Autowired
	private BookDao repository;
	
	@Autowired
	private PressDao pressRepo;
	
	@Autowired
	private TagDao tagRepo;
	
	@Before
	public void setup() {
		List<Book> books = EntityGenerator.generateBooks(N);
		Press press = EntityGenerator.generatePress("Press");
		pressRepo.save(press);
		Tag tag = EntityGenerator.generateTag("Novel");
		tagRepo.save(tag);
		
		for (Book book : books) {
			book.setPress(press);
			AssociatedTag aTag = new AssociatedTag(book, tag);
			book.getAssociatedTags().add(aTag);
		}
		repository.save(books);
	}
	
	@Test
	@Ignore
	public void shouldGetABookByID() {
		Book book = repository.findOne(0);
		assertNull(book);
	}
	
	@Test
	public void shouldGetABookPage() {
		Page<Book> page = repository.findAll(new PageRequest(0, 2));
		assertEquals(2, page.getSize());
		assertEquals(3, page.getTotalPages());
		assertEquals(2, page.getContent().size());
		assertEquals(10, page.getContent().get(0).getPubPrice(), 0.001);
	}
	
	@Test
	public void shouldGetASortedBookList() {
		List<Book> books = repository.findAll(new Sort(Direction.DESC, "PubPrice"));
		assertEquals(5, books.size());
		assertEquals(14, books.get(0).getPubPrice(), 0.001);
	}
	
	@Test
	public void shouldGetBookViaISBN() {
		Book book = repository.findByIsbn("0");
		assertEquals("Book 0", book.getName());
		
	}

	@Test
	public void shouldGetBooksOrderByPubPrice() {
	    List<Book> books = repository.findByPressName("Press", new PageRequest(0, 2));
		assertEquals(2, books.size());
	}
	
	@Test
	public void shouldGetBooksByTagName() {
		assertEquals(5, repository.findByAssociatedTagsTagName("Novel",  new PageRequest(0, 10)).size());
	}
	
	@Test
	public void shouldGetOnSaleBooks() {
		assertEquals(5, repository.findByIsSaleRankTrue(new PageRequest(0, 5)).size());
	}
	
}
