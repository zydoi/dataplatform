package com.bimoku.dataplatform.entity.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bimoku.dataplatform.entity.Comment;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentDTO {

	@XmlElement(name = "USER")
	private UserDTO user;
	
	@XmlElement(name = "Content")
	private String content;
	
	@XmlElement(name = "CreateAt")
	private Date createAT;
	
	@XmlElement(name = "Site")
	private String site;
	
	public CommentDTO() {
	}
	
	public CommentDTO(Comment comment) {
		
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

	public Date getCreateAT() {
		return createAT;
	}

	public void setCreateAT(Date createAT) {
		this.createAT = createAT;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	
}
