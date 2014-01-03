package com.bimoku.dataplatform.dao;

import org.springframework.data.repository.CrudRepository;

import com.bimoku.dataplatform.entity.Author;

public interface AuthorDao extends CrudRepository<Author, Integer>{

}
