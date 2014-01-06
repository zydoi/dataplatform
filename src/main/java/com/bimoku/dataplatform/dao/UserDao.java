package com.bimoku.dataplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bimoku.dataplatform.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	public User findByName(String name);
}
