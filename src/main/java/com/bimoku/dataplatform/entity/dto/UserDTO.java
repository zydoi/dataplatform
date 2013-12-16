package com.bimoku.dataplatform.entity.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bimoku.dataplatform.entity.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO {
	
	@XmlAttribute(name = "ID")
	private int id;
	
	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Avatar")
	private String avatar;
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
