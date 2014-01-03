package com.bimoku.dataplatform.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BookRaw")
public class BookRaw {
	
	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "ID", referencedColumnName = "ID")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "WEBSITE_ID")
	private Website website;

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

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}
}
