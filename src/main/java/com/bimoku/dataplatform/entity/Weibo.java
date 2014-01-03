package com.bimoku.dataplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Weibo")
public class Weibo {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "ID")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "CONTEXT")
	private String context;

	@Column(name = "URL")
	private String url;

	@Column(name = "REPOST_COUNT")
	private int repostCount;

	@Column(name = "COMMENT_COUNT")
	private int commentCount;
	
	public Weibo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getRepostCount() {
		return repostCount;
	}

	public void setRepostCount(int repostCount) {
		this.repostCount = repostCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

}
