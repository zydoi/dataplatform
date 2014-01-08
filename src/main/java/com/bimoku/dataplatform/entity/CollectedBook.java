package com.bimoku.dataplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bimoku.dataplatform.entity.type.CollectionStatus;

@Entity
@Table(name = "User_Collect_Book")
public class CollectedBook {

	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Column(name = "STATUS", columnDefinition = "TINYINT", length = 2)
	@Enumerated(EnumType.ORDINAL)
	private CollectionStatus collectionStatus;
	
	public CollectedBook() {
	}

	public CollectedBook(User user, Book book, CollectionStatus status) {
		this.user = user;
		this.book = book;
		this.collectionStatus = status;
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
	
	public CollectionStatus getCollectionStatus() {
		return collectionStatus;
	}
	
	public void setCollectionStatus(CollectionStatus collectionStatus) {
		this.collectionStatus = collectionStatus;
	}

}
