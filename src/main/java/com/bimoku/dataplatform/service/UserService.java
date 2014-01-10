package com.bimoku.dataplatform.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.BookDao;
import com.bimoku.dataplatform.dao.CollectedBookDao;
import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.CollectedBook;
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.UserProfile;
import com.bimoku.dataplatform.entity.dto.UserDTO;
import com.bimoku.dataplatform.entity.dto.UserProfileDTO;
import com.bimoku.dataplatform.entity.type.CollectionStatus;
import com.bimoku.dataplatform.util.DTOAssembler;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private CollectedBookDao cBookDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public UserDTO create(UserDTO dto) {
		User user = new User(dto);
		userDao.save(user);
		return DTOAssembler.assembleUserDTO(user);
	}
	
	/**
	 * User with the userName follows the user with the toFollowName
	 * @param userName the name of the follower 
	 * @param toFollowName the name of user who will be followed
	 * @return
	 */
	public User follow(String userName, String toFollowName) {
		User user = userDao.findByName(userName);
		User toFollow = userDao.findByName(toFollowName);
		user.getFollowings().add(toFollow);
		toFollow.getFollowers().add(user);
		return user;
	}
	
	/**
	 * User with the userName unfollows the user with the toUnfollowName
	 * @param userName the name of the follower 
	 * @param toUnfollowName the name of user who will be unfollowed
	 * @return
	 */
	public User unfollow(String userName, String toUnfollowName) {
		User user = userDao.findByName(userName);
		User toUnfollow = userDao.findByName(toUnfollowName);
		user.getFollowings().remove(toUnfollow);
		toUnfollow.getFollowers().remove(user);
		return user;
	}
	
	public User updateProfile(String name, UserProfileDTO userProfile) {
		User user = userDao.findByName(name);
		user.setUserProfile(new UserProfile(userProfile));
		return user;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDTO findById(int id) {
		return (UserDTO) DTOAssembler.assemble(userDao.findOne(id), new UserDTO());
	}
	
	public UserDTO findByName(String name) {
		UserDTO user = (UserDTO) DTOAssembler.assemble(userDao.findByName(name), new UserDTO());
		return user;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserDTO> findFollowersByName(String name) {
		User user = userDao.findByName(name);
		return DTOAssembler.assembleUserDTOs(user.getFollowers());
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserDTO> findFollowingsByName(String name) {
		User user = userDao.findByName(name);
		return DTOAssembler.assembleUserDTOs(user.getFollowings());
	}
	
	public void collectBook(String name, String isbn, CollectionStatus status) {
		User user = userDao.findByName(name);
		if(user == null) {
			return;
		}
		Book book = bookDao.findByIsbn(isbn);
		if(book == null) {
			return;
		}
		
		//Check whether this book has been collected by the user already.
		StringBuilder sb = new StringBuilder(256);
		sb.append("select c.book from User u join u.collectedBooks c ")
			.append("where u.name = :userName and c.book.isbn = :isbn");
		Query q = entityManager.createQuery(sb.toString());
		q.setParameter("userName", name);
		q.setParameter("isbn", isbn);
		
		if(q.getResultList().size() == 0) {
			user.getCollectedBooks().add(new CollectedBook(user, book, status));
			book.setCollectCount(book.getCollectCount() + 1);
		}
	}
	
	public void likeBook(String name, String isbn) {
		User user = userDao.findByName(name);
		if(user == null) {
			return;
		}
		Book book = bookDao.findByIsbn(isbn);
		if(book == null) {
			return;
		}
		if(!user.getLikeBooks().contains(book)) {
			book.setLikeCount(book.getLikeCount() + 1);
			user.getLikeBooks().add(book);		
		}
	}
	
	public void searchedBook(String name, String isbn) {
		User user = userDao.findByName(name);
		if(user == null) {
			return;
		}
		Book book = bookDao.findByIsbn(isbn);
		if(book == null) {
			return;
		}
		if(!user.getSearchBooks().contains(book)) {
			book.setCollectCount(book.getCollectCount() + 1);
			user.getSearchBooks().add(book);		
		}
	}
}
