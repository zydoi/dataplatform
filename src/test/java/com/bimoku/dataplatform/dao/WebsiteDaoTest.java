package com.bimoku.dataplatform.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.entity.Website;
import com.bimoku.dataplatform.util.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class WebsiteDaoTest {
	
	@Autowired
	private WebsiteDao dao;
	
	@Before
	public void setup() {
		Website website = EntityGenerator.generateWebsite("Amazon");
		dao.save(website);
	}
	
	@Test
	public void shouldGetAWebsiteByName() {
		Website w = dao.findByName("Amazon");
		assertEquals("Amazon", w.getName());
	}
}
