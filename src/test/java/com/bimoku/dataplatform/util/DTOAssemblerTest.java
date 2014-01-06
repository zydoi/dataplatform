package com.bimoku.dataplatform.util;

import org.junit.Test;

import com.bimoku.dataplatform.entity.Author;
import com.bimoku.dataplatform.entity.Book;
import com.bimoku.dataplatform.entity.Message;
import com.bimoku.dataplatform.entity.Press;
import com.bimoku.dataplatform.entity.Tag;
import com.bimoku.dataplatform.entity.User;
import com.bimoku.dataplatform.entity.dto.AuthorDTO;
import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.entity.dto.BookDetailsDTO;
import com.bimoku.dataplatform.entity.dto.MessageDTO;
import com.bimoku.dataplatform.entity.dto.TagDTO;

public class DTOAssemblerTest {

	@Test
	public void test() {
		Book book = EntityGenerator.generateBooks(1).get(0);
		Press press = EntityGenerator.generatePress("P");
		Author author = EntityGenerator.generateAuthor("A");
		Message message = EntityGenerator.generateMessage("M");
		Tag tag = EntityGenerator.generateTag("T");
		User user = EntityGenerator.generateUser("U");

		book.setPress(press);
		book.getAuthors().add(author);
		book.getMessages().add(message);
		message.setBook(book);
		message.setUser(user);
		
		
		DTOAssembler.assemble(book, new BookDTO());
		DTOAssembler.assemble(book, new BookDetailsDTO());
		
		DTOAssembler.assemble(author, new AuthorDTO());
		
		DTOAssembler.assemble(message, new MessageDTO());
		
		DTOAssembler.assemble(tag, new TagDTO());
	}
}