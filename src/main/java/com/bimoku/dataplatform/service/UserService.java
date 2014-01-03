package com.bimoku.dataplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.UserDao;
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.UserProfile;
import com.bimoku.dataplatform.entity.dto.UserDTO;
import com.bimoku.dataplatform.entity.dto.UserProfileDTO;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User create(UserDTO dto) {
		User user = new User(dto);
		userDao.save(user);
		return user;
	}
	
	public User follow(int userId, int toFollowId) {
		User user = userDao.findOne(userId);
		User toFollow = userDao.findOne(toFollowId);
		toFollow.getFollowers().add(user);
		return user;
	}
	
	public User updateProfile(int userId, UserProfileDTO userProfile) {
		User user = userDao.findOne(userId);
		user.setUserProfile(new UserProfile(userProfile));
		return user;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserDTO> getFollowers() {
		return null;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserDTO> getFollowings() {
		return null;
	}
	
}
