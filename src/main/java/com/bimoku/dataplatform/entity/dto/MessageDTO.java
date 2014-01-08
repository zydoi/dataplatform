package com.bimoku.dataplatform.entity.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bimoku.dataplatform.entity.Message;

@XmlRootElement(name = "Comment")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageDTO {

	@XmlElement(name = "user")
	private UserDTO user;
	
	@XmlElement(name = "bookName")
	private String bookName;

	@XmlElement(name = "content")
	private String content;
	
	@XmlElement(name = "createAt")
	private Date createAt;
	
	@XmlElement(name = "site")
	private String site;
	
	public MessageDTO() {
	}
	
	public MessageDTO(Message comment) {
		
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	
}
