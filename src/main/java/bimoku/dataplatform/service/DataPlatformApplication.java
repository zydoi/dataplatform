package bimoku.dataplatform.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;


public class DataPlatformApplication extends Application {

	private Set<Class<?>> classes;
	
	private Set<Object> singletons;

	
	public DataPlatformApplication() {
		classes = new HashSet<>();
		singletons = new HashSet<>();
	}

	@Override
	public Set<Class<?>> getClasses() {
		classes.add(BookService.class);
		return classes;

	}	

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
	
}
