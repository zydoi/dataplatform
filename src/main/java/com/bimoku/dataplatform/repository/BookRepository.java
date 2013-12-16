package com.bimoku.dataplatform.repository;

import org.springframework.data.repository.CrudRepository;

import com.bimoku.dataplatform.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	

}
