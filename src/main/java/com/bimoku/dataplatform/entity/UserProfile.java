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

}
