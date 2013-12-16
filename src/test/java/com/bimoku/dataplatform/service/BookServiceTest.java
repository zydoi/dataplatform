package com.bimoku.dataplatform.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.service.DataPlatformApplication;
import com.bimoku.dataplatform.util.DataGenerator;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class BookServiceTest {
	
	private HttpServer server;
	
	private Client client;
	
	private URI uri;
	
	@Before
	public void setup() throws IOException {
        uri = UriBuilder.fromUri("http://localhost/").port(9999).build();
        server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new DataPlatformApplication(), HttpHandler.class);
        server.createContext(uri.getPath(), handler);
        server.start();
        
        client = ClientBuilder.newClient();
        WebTarget bookTarget = client.target(uri.toString()).path("bookservice/book/1");
	}
	
	@Test
	public void shouldMarshallABook() throws JAXBException {
		BookDTO book = DataGenerator.generateBooks(1).get(0);
        JAXBContext context = JAXBContext.newInstance(BookDTO.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter stringWriter = new StringWriter();

        m.marshal(book, stringWriter);
        //System.out.println(stringWriter.toString());
	}
	
	@Test
	public void shouldGetABook() {
		WebTarget bookTarget = client.target(uri.toString()).path("bookservice/book/1");
        Builder request = bookTarget.request();
		Response response = request.get();
		assertEquals(200, response.getStatus());
		assertEquals("Book 0", response.readEntity(BookDTO.class).getName());
	}
	
	@Test
	public void shouldGetAPage() {
		WebTarget bookTarget = client.target(uri.toString()).path("bookservice/book/page").queryParam("start", 1).queryParam("size", 1);
        System.out.println(bookTarget.getUri().toString());
		Builder request = bookTarget.request();
		Response response = request.get();
		assertEquals(200, response.getStatus());
//		assertEquals("Book 0", response.readEntity(BookDTO.class).getName());
	}

	@After
	public void tearDown() {
		server.stop(0);
	}
}
