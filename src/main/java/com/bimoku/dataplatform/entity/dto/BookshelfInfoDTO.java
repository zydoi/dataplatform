package com.bimoku.dataplatform.entity.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BookShelf")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookshelfInfoDTO {
	
	private String userName;
	
	private int likeCount;
	
	private int readCount;

	private int wantedCount;
	
	private int readingCount;
	
	private int tbdCount;
	
	private int searchedCount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getWantedCount() {
		return wantedCount;
	}

	public void setWantedCount(int wantedCount) {
		this.wantedCount = wantedCount;
	}

	public int getReadingCount() {
		return readingCount;
	}

	public void setReadingCount(int readingCount) {
		this.readingCount = readingCount;
	}

	public int getTbdCount() {
		return tbdCount;
	}

	public void setTbdCount(int tbdCount) {
		this.tbdCount = tbdCount;
	}

	public int getSearchedCount() {
		return searchedCount;
	}

	public void setSearchedCount(int searchedCount) {
		this.searchedCount = searchedCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
}
