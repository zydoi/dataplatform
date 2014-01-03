package com.bimoku.dataplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tag")
public class Tag {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	public Tag() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name; 
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
