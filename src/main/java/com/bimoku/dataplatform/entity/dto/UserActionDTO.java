package com.bimoku.dataplatform.entity.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.bimoku.dataplatform.entity.type.Action;

@XmlRootElement(name = "UserAction")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserActionDTO {
	
	private String userName;
	
	private BookDTO book;
	
	private String authorName;
	
	private Action action;
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BookDTO getBook() {
		return book;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
