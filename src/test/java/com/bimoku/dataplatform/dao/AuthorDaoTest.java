package com.bimoku.dataplatform.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.dao.AuthorDao;
import com.bimoku.dataplatform.entity.Author;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class AuthorDaoTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private AuthorDao repository;
	
	@Before
	public void setup() {
        //repository = new JpaRepositoryFactory(entityManager).getRepository(AuthorRepository.class);
	}
	
	@Test
	public void shouldGetAnAuthor() {
		Author a = new Author();
		a.setId(1);
		a.setName("Tim");
		repository.save(a);
		assertEquals(a.getName(),repository.findAll().iterator().next().getName());
	}
}
