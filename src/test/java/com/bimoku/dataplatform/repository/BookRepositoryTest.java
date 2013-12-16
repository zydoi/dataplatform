package com.bimoku.dataplatform.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

public class BookRepositoryTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private BookRepository repository;
	
	@Before
	public void setup() {
	}
	
	@Test
	public void test() {
		if(entityManager == null) {
			System.out.println("!!!!!!!!!!");
		}
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("repository-context.xml");
//		repository = (BookRepository) ctx.getBean(BookRepository.class);
//        repository = new JpaRepositoryFactory(entityManager).getRepository(BookRepository.class);
//		repository.findAll();
	}
	
	@After
	public void rollback() {
		
	}
}
