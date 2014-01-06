package com.bimoku.dataplatform.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.BookDao;
import com.bimoku.dataplatform.dao.MessageDao;
import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Message;
import com.bimoku.dataplatform.entity.User;

@Service
public class DataGenerator {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDao userDao;
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void generateBooks(int n) {
		List<Book> books = EntityGenerator.generateBooks(n);
		bookDao.save(books);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void cleanUp() {
		messageDao.deleteAll();
		bookDao.deleteAll();
		userDao.deleteAll();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void generateMessages(int n) {
		User user = EntityGenerator.generateUser("User 2");
		userDao.save(user);
		user = EntityGenerator.generateUser("User 1");
		userDao.save(user);
		Book book = EntityGenerator.generateBook("0");
		bookDao.save(book);
		List<Message> messages = EntityGenerator.generateMessages(n);
		for (Message message : messages) {
			message.setUser(user);
			message.setBook(book);
		}
		messageDao.save(messages);
	}
}
