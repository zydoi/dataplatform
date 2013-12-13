package bimoku.dataplatform.service;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import com.sun.net.httpserver.HttpServer;

import org.junit.Test;

public class WebTestContainer {

	@Test
	public void test() {
		URI uri = UriBuilder.fromUri("http://localhost/").port(9999).build();
		HttpServer server = HttpServer.create();
	}
}
