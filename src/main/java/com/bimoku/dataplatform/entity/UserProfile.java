package com.bimoku.dataplatform.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bimoku.dataplatform.entity.dto.UserProfileDTO;

@Embeddable
public class UserProfile {

	@Column(name = "USER_IMAGE")
	private String userImage;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "GENDER", columnDefinition = "TINYINT")
	private boolean gender;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "DOMAIN")
	private String domain;
	
	public UserProfile() {
	}
	
	public UserProfile(UserProfileDTO dto) {
		this.userImage = dto.getUserImage();
		this.gender = dto.getGender();
		this.email = dto.getEmail();
	}
	
	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
