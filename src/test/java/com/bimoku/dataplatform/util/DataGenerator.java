package com.bimoku.dataplatform.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.AssociatedTagDao;
import com.bimoku.dataplatform.dao.BookDao;
import com.bimoku.dataplatform.dao.MessageDao;
import com.bimoku.dataplatform.dao.TagDao;
import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Message;
import com.bimoku.dataplatform.entity.Tag;
import com.bimoku.dataplatform.entity.User;

@Service
public class DataGenerator {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TagDao tagDao;
	
	@Autowired
	private AssociatedTagDao associatedTagDao;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void cleanUp() {
		messageDao.deleteAll();
		bookDao.deleteAll();
		userDao.deleteAll();
		associatedTagDao.deleteAll();
		tagDao.deleteAll();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void generateBooks(int n) {
		List<Book> books = EntityGenerator.generateBooks(n);
		bookDao.save(books);
		Tag tag = new Tag("Tag1");
		tagDao.save(tag);
		associatedTagDao.save(EntityGenerator.generateAssociatedTag(books.get(0), tag));
		associatedTagDao.save(EntityGenerator.generateAssociatedTag(books.get(1), tag));
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void generateTestData(int n) {
		User user = EntityGenerator.generateUser("User 2");
		userDao.save(user);
		user = EntityGenerator.generateUser("User 1");
		userDao.save(user);
		Book book1 = EntityGenerator.generateBook("1");
		Book book2 = EntityGenerator.generateBook("2");
		bookDao.save(book1);
		bookDao.save(book2);
		List<Message> messages = EntityGenerator.generateMessages(n);
		for (Message message : messages) {
			message.setUser(user);
			message.setBook(book1);
		}
		messageDao.save(messages);
		Tag tag1 = new Tag("Tag1");
		Tag tag2 = new Tag("Tag2");
		tagDao.save(tag1);
		tagDao.save(tag2);
		associatedTagDao.save(EntityGenerator.generateAssociatedTag(book1, tag1));
		associatedTagDao.save(EntityGenerator.generateAssociatedTag(book1, tag2));
		associatedTagDao.save(EntityGenerator.generateAssociatedTag(book2, tag1));
	}
	
}
