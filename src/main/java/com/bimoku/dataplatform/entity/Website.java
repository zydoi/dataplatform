package com.bimoku.dataplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Website")
public class Website {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", columnDefinition = "SMALLINT")
	private int id;

	@Column(name = "UUID")
	private String uuid;

	@Column(name = "NAME")
	private String name;

	@Column(name = "IS_BOOKSTORE", columnDefinition = "BIT")
	private boolean isBookStore;
	
	public Website() {
	}

	public Website(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBookStore() {
		return isBookStore;
	}

	public void setBookStore(boolean isBookStore) {
		this.isBookStore = isBookStore;
	}
}
