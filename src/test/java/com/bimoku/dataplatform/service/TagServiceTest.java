package com.bimoku.dataplatform.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.AssociatedTagDao;
import com.bimoku.dataplatform.dao.BookDao;
import com.bimoku.dataplatform.dao.TagDao;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Tag;
import com.bimoku.dataplatform.util.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class TagServiceTest {
	
	@Autowired
	private TagService service;
	
	@Autowired
	private TagDao dao;
	
	@Autowired
	private AssociatedTagDao aTagDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Before
	public void setup() {
		Tag tag1 = new Tag("Tag1");
		Tag tag2 = new Tag("Tag2");
		dao.save(tag1);
		dao.save(tag2);
		Book book1 = EntityGenerator.generateBook("1");
		Book book2 = EntityGenerator.generateBook("2");
		bookDao.save(book1);
		bookDao.save(book2);
		aTagDao.save(EntityGenerator.generateAssociatedTag(book1, tag1));
		aTagDao.save(EntityGenerator.generateAssociatedTag(book2, tag2));
		aTagDao.save(EntityGenerator.generateAssociatedTag(book2, tag1));
		book2.getAssociatedTags().iterator().next().setCount(3);
	}
	
	@Test
	public void shouldGetTagsByBook() {
		assertEquals(1, service.findTagsByBook("1").size());
		assertEquals(3, service.findTagsByBook("2").get(0).getCount());
	}
	
	@Test
	public void shouldGetPopularTags() {
		assertEquals("Tag1", service.getPopularTags().get(0).getName());
	}
}
