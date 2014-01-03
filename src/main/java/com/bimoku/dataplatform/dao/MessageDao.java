package com.bimoku.dataplatform.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bimoku.dataplatform.entity.Message;

public interface MessageDao extends JpaRepository<Message, Integer> {

	public List<Message> findByUserName(String userName, Pageable pageable);

	public List<Message> findByUserId(int userId, Pageable pageable);
	
	public List<Message> findByBookName(String bookName, Pageable pageable);

	public List<Message> findByBookIsbn(String isbn, Pageable pageable);
	
}
