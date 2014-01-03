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
import com.bimoku.dataplatform.dao.MessageDao;
import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Message;
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.util.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class MessageServiceTest {

	@Autowired
	private MessageService service;

	@Autowired 
	private MessageDao dao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Before
	public void setup() {
		User user = EntityGenerator.generateUser("User");
		userDao.save(user);
		Book book = EntityGenerator.generateBook("0");
		bookDao.save(book);
		List<Message> messages = EntityGenerator.generateMessages(5);
		for (Message message : messages) {
			message.setUser(user);
			message.setBook(book);
		}
		dao.save(messages);
	}

	@Test
	public void shouldGetMessagesByUser() {
		assertEquals(5, service.findByUserName("User", 0, 10).size());
	}

	@Test
	public void shouldGetMessagesByBook() {
		assertEquals(5, service.findByBookISBN("0", 0, 10).size());
	}
}
