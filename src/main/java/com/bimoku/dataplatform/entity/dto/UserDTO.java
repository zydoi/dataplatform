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
	
	@XmlElement(name = "uuid")
	private String uuid;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "userImage")
	private String userImage;
	
	@XmlElement(name = "description")
	private String description;
	
	@XmlElement(name = "domain")
	private String domain;
	
	@XmlElement(name = "follower_count")
	private String followerCount;
	
	@XmlElement(name = "following_count")
	private String followingCount;
	
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(String followerCount) {
		this.followerCount = followerCount;
	}

	public String getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(String followingCount) {
		this.followingCount = followingCount;
	}

}
