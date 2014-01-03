package com.bimoku.dataplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bimoku.dataplatform.entity.dto.MessageDTO;

@Entity
@Table(name = "Message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "CONTENT", columnDefinition = "TEXT")
	@Lob
	private String content;

	@Column(name = "CREATE_AT")
	private Date createAt;

	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "WEBSITE_ID")
	private Website site;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Message parentComment;

	public Message() {
	}

	public Message(MessageDTO dto) {
		this.setContent(dto.getContent());
		this.setCreateAt(dto.getCreateAt());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Website getSite() {
		return site;
	}

	public void setSite(Website site) {
		this.site = site;
	}

	public Message getParentComment() {
		return parentComment;
	}

	public void setParentComment(Message parentComment) {
		this.parentComment = parentComment;
	}

}
