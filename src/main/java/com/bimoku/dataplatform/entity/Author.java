package com.bimoku.dataplatform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bimoku.dataplatform.entity.dto.AuthorDTO;

@Entity
@Table(name = "Author")
public class Author {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "UUID")
	private String uuid;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "INTRO", columnDefinition = "TEXT")
	@Lob
	private String intro;

	@Column(name = "IS_TRANSLATOR", columnDefinition="BIT", length = 1)
	private boolean isTranslator;
	
	@ManyToMany(mappedBy = "authors")
	private Set<Book> book = new HashSet<Book>();

	public Author() {
	}
	
	public Author(AuthorDTO authorDTO) {
		this.name = authorDTO.getName();
		this.intro = authorDTO.getIntro();
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

	public boolean isTranslator() {
		return isTranslator;
	}

	public void setTranslator(boolean isTranslator) {
		this.isTranslator = isTranslator;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
