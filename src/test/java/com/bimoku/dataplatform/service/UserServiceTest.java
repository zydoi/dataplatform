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
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.dto.SimilarUserDTO;
import com.bimoku.dataplatform.entity.dto.UserDTO;
import com.bimoku.dataplatform.entity.type.Action;
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
		userDao.save(EntityGenerator.generateUser("User 3"));
	}
	
	@Test
	public void shouldCreateAUser() {
		UserDTO dto = new UserDTO();
		dto.setName("Test");
		userService.create(dto);
		assertEquals(4, userDao.findAll().size());
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
		assertEquals(0, userService.findFollowersByName("User 1", 1, 1).size());
		
		userService.unfollow("User 2", "User 1");
		assertEquals(0, userService.findFollowersByName("User 1").size());
		assertEquals(0, userService.findFollowersByName("User 1", 0, 1).size());
	}
	
	@Test
	public void shouldCollectBook() {
		Book book = bookDao.save(EntityGenerator.generateBook("1"));
		userService.collectBook("User 1", book.getIsbn(), CollectionStatus.READ);
		assertEquals(1, userDao.findByName("User 1").getCollectedBooks().size());
		assertEquals(1, book.getCollectCount());
		userService.collectBook("User 1", book.getIsbn(), CollectionStatus.READ);
		assertEquals(1, userDao.findByName("User 1").getCollectedBooks().size());
		assertEquals(1, book.getCollectCount());
		
		book = bookDao.save(EntityGenerator.generateBook("2"));
		userService.collectBook("User 1", book.getIsbn(), CollectionStatus.READ);
		assertEquals(2, userService.getBookShelfInfo("User 1").getReadCount());
	}
	
	@Test
	public void shouldLikeABook() {
		Book book = bookDao.save(EntityGenerator.generateBook("1"));
		userService.likeBook("User 1", book.getIsbn());
		assertEquals(1, book.getLikeCount());
		userService.likeBook("User 1", book.getIsbn());
		assertEquals(1, book.getLikeCount());
		assertEquals(Action.LIKE, userService.getUserAction("User 1", 0, 1).get(0).getAction());
	}
	
	@Test
	public void shouldSearchABook() {
		Book book = bookDao.save(EntityGenerator.generateBook("1"));
		userService.searchedBook("User 1", book.getIsbn());
		assertEquals(1, userDao.findByName("User 1").getSearchBooks().size());
		userService.clearSearchHistory("User 1");
		assertEquals(0, userDao.findByName("User 1").getSearchBooks().size());
	}
	
	@Test
	public void shouldGetSimilarPeople() {
		Book book1 = bookDao.save(EntityGenerator.generateBook("1"));
		Book book2 = bookDao.save(EntityGenerator.generateBook("2"));
		userService.likeBook("User 1", book1.getIsbn());
		userService.likeBook("User 1", book2.getIsbn());
		userService.likeBook("User 2", book1.getIsbn());
		userService.likeBook("User 2", book2.getIsbn());
		userService.likeBook("User 3", book1.getIsbn());
		userDao.flush();
		
		List<SimilarUserDTO> similars = userService.findSimilarPeople("User 1", 0, 10);
		assertEquals(2, similars.size());
	}
}
