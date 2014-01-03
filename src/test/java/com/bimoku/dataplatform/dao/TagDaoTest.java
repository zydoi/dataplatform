package com.bimoku.dataplatform.dao;

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
import com.bimoku.dataplatform.dao.TagDao;
import com.bimoku.dataplatform.entity.AssociatedTag;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Tag;
import com.bimoku.dataplatform.util.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class TagDaoTest {
	
	private static final int N = 5;
	
	@Autowired
	private TagDao repository;
	
	@Autowired
	private BookDao bookRepo;
	
	@Before
	public void setup() {
		List<Book> books = EntityGenerator.generateBooks(N);
		int i = 0;
		Tag commonTag = EntityGenerator.generateTag("Tag " + i++);
		repository.save(commonTag);

		for (Book book : books) {
			Tag tag = EntityGenerator.generateTag("Tag" + i++);
			repository.save(tag);
			book.getAssociatedTags().add(new AssociatedTag(book, commonTag));
			book.getAssociatedTags().add(new AssociatedTag(book, tag));
		}
		
		bookRepo.save(books);
		repository.flush();
	}
	
	@Test
	public void shouldGetPopularTags() {
		assertEquals(6, repository.findPopularTags().size());
		assertEquals("Tag 0", repository.findPopularTags().get(0).getName());
	}
	
}
