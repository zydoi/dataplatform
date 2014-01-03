package com.bimoku.dataplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bimoku.dataplatform.entity.Press;

public interface PressDao extends JpaRepository<Press, Integer> {
	
	public Press findByName(String name);
}
