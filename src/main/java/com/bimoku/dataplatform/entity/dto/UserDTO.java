package com.bimoku.dataplatform.entity.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO {
	
	@XmlAttribute(name = "id")
	private int id;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "userImage")
	private String userImage;
	
	public UserDTO() {
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

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

}
