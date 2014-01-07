package com.bimoku.dataplatform.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bimoku.dataplatform.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
	
	public List<Book> findByName(String name, Pageable pageable);
	
	public Book findByIsbn(String isbn);
	
	public List<Book> findByPressName(String press, Pageable pageable);
	
	public List<Book> findByAssociatedTagsTagName(String tag, Pageable pageable);
	
	public List<Book> findByIsSaleRankTrue(Pageable pageable);
	
	public List<Book> findByIsNewRankTrue(Pageable pageable);
	
	public List<Book> findByIsPromotionRankTrue(Pageable pageable);
	
	/**
	 * Support fuzzy universal search based on book name, author name and press name.
	 * @param input book name
	 * @param input2 author or press name
	 * @return
	 */
	@Query(value = "select b from Book b left join fetch b.press p "
			+ "left join fetch b.authors a where b.name like %:name% "
			+ "or a.name = :authorpress or p.name = :authorpress")
	@Deprecated
	public List<Book> universalSearch(@Param("name") String input, @Param("authorpress")String input2);
}
