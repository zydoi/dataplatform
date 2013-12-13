package bimoku.dataplatform.service;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

public class Test {
	public static void main(String[] args) throws IOException {
		HttpServer server = GrizzlyServerFactory.createHttpServer("http://localhost:9999");
		server.start();
	}
}
