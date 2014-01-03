package com.bimoku.dataplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Book_Associate_Tag")
public class AssociatedTag {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "TAG_ID")
	private Tag tag;
	
	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	private Book book;
	
	@Column(name = "COUNT")
	private int count;

	public AssociatedTag() {
	}
	
	public AssociatedTag(Book book, Tag tag) {
		this.book = book;
		this.tag = tag;
		count = 1;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
