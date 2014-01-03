package com.bimoku.dataplatform.resource;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bimoku.dataplatform.entity.dto.MessageDTO;
import com.bimoku.dataplatform.service.BookService;
import com.bimoku.dataplatform.util.DataGenerator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring-context.xml")
@Transactional
public class MessageResourceTest extends JerseyTest {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private DataGenerator dataGenerator;

	
	protected Application configure() { 
		// Enable logging.
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        
		return new DataPlatformApplication().property("contextConfigLocation","classpath:test-spring-context.xml");
	}
	
	@Before
	public void setup() {
		dataGenerator.generateMessages(5);
	}
	
	@Test
	public void shouldGetMessagesByBook() {
		GenericType<List<MessageDTO>> listType = new GenericType<List<MessageDTO>>() {};
		List<MessageDTO> messages = (List<MessageDTO>) target("user/User/message").queryParam("start", 0).queryParam("size", 2).request().get(listType);
		assertEquals("User", messages.get(0).getUser().getName());
	}
	
	@Test
	public void shouldGetMessagesByUser() {
		GenericType<List<MessageDTO>> listType = new GenericType<List<MessageDTO>>() {};
		List<MessageDTO> messages = (List<MessageDTO>) target("book/0/message").queryParam("start", 0).queryParam("size", 2).request().get(listType);
		assertEquals("Book 0", messages.get(0).getBookName());
	}
	
	@After
	public void tearUp() {
		dataGenerator.cleanUp();
	}
}
