package com.bimoku.dataplatform.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.util.DataGenerator;


@Path(value = "/bookservice")
public class BookService {
	
	@Path(value = "/book/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BookDTO> getBooks() {
		return new ArrayList<BookDTO>();
	}
	
	@Path(value = "/book/{bookId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BookDTO getBook(@PathParam("bookId") int id) {
		System.out.println("Get Book ID.");
		return DataGenerator.generateBooks(1).get(0);
	}
	
	@Path(value = "/book/page")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BookDTO getBooks(@QueryParam("start")int start, @QueryParam("size") int size) {
		System.out.println("Get Book Pages start:" + start + " Size:" + size);
		return DataGenerator.generateBooks(10).get(0);
	}
	
}
