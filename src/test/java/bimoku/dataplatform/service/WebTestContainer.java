package bimoku.dataplatform.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.spi.inject.ClientSide;

public class WebTestContainer {

//	@Test
	public void test() throws IOException {
//		URI uri = UriBuilder.fromUri("http://localhost/").port(9999).build();
//		HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new DataPlatformApplication(), HttpHandler.class);
//		HttpServer server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
//		
//		server.createContext(uri.getPath(), handler);
//		server.start();
		
		
	}
	
}
