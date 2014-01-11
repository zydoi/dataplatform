package com.bimoku.dataplatform.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bimoku.dataplatform.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	public User findByName(String name);
	
	public List<User> findByFollowersName(String name, Pageable pageable);
	
	public List<User> findByFollowingsName(String name, Pageable pageable);
}
