package com.bimoku.dataplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bimoku.dataplatform.entity.CollectedBook;

public interface CollectedBookDao  extends JpaRepository<CollectedBook, Integer> {

}
