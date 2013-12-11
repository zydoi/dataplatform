package bimoku.dataplatform.entity;

import javax.persistence.Entity;

@Entity
public class Author {
	
	private int id;
	
	private String uuid;
	
	private String name;
	
	private boolean isTranslator;
	
	public Author() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isTranslator() {
		return isTranslator;
	}

	public void setTranslator(boolean isTranslator) {
		this.isTranslator = isTranslator;
	}
	
	
}
