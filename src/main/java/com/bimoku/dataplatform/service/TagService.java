package com.bimoku.dataplatform.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bimoku.dataplatform.dao.TagDao;

public class TagService {

	@Autowired
	private TagDao repository;
	
	public void getPopularTags(int n) {
		
	}
}
