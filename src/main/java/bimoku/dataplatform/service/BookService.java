package bimoku.dataplatform.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bimoku.dataplatform.entity.DTO.BookDTO;

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
		BookDTO book = new BookDTO();
		book.setId(id);
		book.setName("Test");
		return book;
	}
}
