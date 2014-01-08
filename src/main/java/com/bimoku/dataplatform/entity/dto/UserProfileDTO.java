package com.bimoku.dataplatform.entity.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserProfile")
public class UserProfileDTO {
	
	@XmlElement(name = "gender")
	private boolean gender;
	
	@XmlElement(name = "userImage")
	private String userImage;
	
	@XmlElement(name = "email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	
}
