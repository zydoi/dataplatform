package com.bimoku.dataplatform.service;

import static org.junit.Assert.assertEquals;

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
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.dto.UserDTO;
import com.bimoku.dataplatform.entity.type.CollectionStatus;
import com.bimoku.dataplatform.util.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private CollectedBookDao cBookDao;
	
	@Before
	public void setup() {
		userDao.save(EntityGenerator.generateUser("User 1"));
		userDao.save(EntityGenerator.generateUser("User 2"));
	}
	
	@Test
	public void shouldCreateAUser() {
		UserDTO dto = new UserDTO();
		dto.setName("Test");
		userService.create(dto);
		assertEquals(3, userDao.findAll().size());
	}
	
	@Test
	public void shouldGetAUserByName() {
		UserDTO user = userService.findByName("User 1");
		assertEquals("User 1", user.getName());
	}
	
	@Test
	public void shouldFollowAndUnfollow() {
		User u = userDao.findByName("User 1");
		assertEquals(0, u.getFollowers().size());
		
		userService.follow("User 2", "User 1");
		assertEquals(1, u.getFollowers().size());
		assertEquals(1, userService.findFollowersByName("User 1").size());
		
		userService.unfollow("User 2", "User 1");
		assertEquals(0, userService.findFollowersByName("User 1").size());
	}
	
	@Test
	public void shouldCollectBook() {
		Book book = bookDao.save(EntityGenerator.generateBook("1"));
		userService.collectBook("User 1", book.getIsbn(), CollectionStatus.READ);
		assertEquals(1, userDao.findByName("User 1").getCollectedBooks().size());
		assertEquals(1, cBookDao.findAll().size());
	}
}
