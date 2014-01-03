package com.bimoku.dataplatform.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bimoku.dataplatform.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
	
	public List<Book> findByName(String name, Pageable pageable);
	
	public Book findByIsbn(String isbn);
	
	public List<Book> findByPressName(String press, Pageable pageable);
	
	public List<Book> findByAssociatedTagsTagName(String tag, Pageable pageable);
	
	public List<Book> findByIsSaleRankTrue(Pageable pageable);
	
	public List<Book> findByIsNewRankTrue(Pageable pageable);
	
	public List<Book> findByIsPromotionRankTrue(Pageable pageable);
}
