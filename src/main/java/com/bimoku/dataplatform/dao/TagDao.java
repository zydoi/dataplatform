package com.bimoku.dataplatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bimoku.dataplatform.entity.Tag;

public interface TagDao extends JpaRepository<Tag, Integer>{
	
	@Query(value = "SELECT t.ID, t.NAME FROM Tag t LEFT "
			+ "JOIN Book_Associate_Tag bt ON t.ID = bt.TAG_ID LEFT JOIN Book b ON bt.BOOK_ID = b.ID "
			+ "GROUP BY t.ID "
			+ "ORDER BY count(b.ID) DESC limit 10", nativeQuery = true)
	public List<Tag> findPopularTags();
	
	
	public Tag findByName(String name);
}
