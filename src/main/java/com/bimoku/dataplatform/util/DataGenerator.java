package com.bimoku.dataplatform.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.entity.dto.CommentDTO;
import com.bimoku.dataplatform.entity.dto.UserDTO;

public class DataGenerator {
	
	public static List<BookDTO> generateBooks(int n) {
		List<BookDTO> books = new ArrayList<BookDTO>();
		for(int i = 0; i < n; i++) {
			BookDTO book = new BookDTO();
			book.setId(i);
			book.setAuthor("Author" + i);
			book.setPress("Press " + i);
			book.setName("Book " + i);
			book.setUuid("UUID " + i);
			book.setCoverPic("http://img33.ddimg.cn/91/6/20740393-1_w.jpg");
			book.setTranslator("Translator" + i);
			book.setComments(generateComments(5));
			books.add(book);
		}
		return books;
	}
	
	public static List<UserDTO> generateUsers(int n) {
		List<UserDTO> users = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			UserDTO user = new UserDTO();
			user.setId(i);
			user.setName("User" + i);
			user.setAvatar("http://img33.dangdang.com/imghead/0/36/3101266678653-1_g.png");
			users.add(user);
		}
		return users;
	}

	public static List<CommentDTO> generateComments(int n) {
		List<CommentDTO> comments = new ArrayList<CommentDTO>();
		for (int i = 0; i < n; i++) {
			CommentDTO comment = new CommentDTO();
			comment.setContent("Test Comment " + i);
			comment.setCreateAT(new Date());
			comment.setSite("douban");
			comment.setUser(generateUsers(1).get(0));
			comments.add(comment);
		}
		return comments;
	}
}
