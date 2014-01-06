package com.bimoku.dataplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bimoku.dataplatform.entity.Website;

public interface WebsiteDao extends JpaRepository<Website, Integer> {

	public Website findByName(String name);
}
