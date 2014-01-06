package com.bimoku.dataplatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bimoku.dataplatform.entity.AssociatedTag;

public interface AssociatedTagDao extends JpaRepository<AssociatedTag, Integer>{
	
	public AssociatedTag findByTagNameAndBookIsbn(String tagName, String isbn);
	
	public List<AssociatedTag> findByBookIsbnOrderByCountDesc(String isbn);
}
