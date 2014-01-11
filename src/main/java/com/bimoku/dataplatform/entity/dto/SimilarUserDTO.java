package com.bimoku.dataplatform.entity.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SimilarUser")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimilarUserDTO {
	
	private int sameBookCount;
	
	private String userName;

	public int getSameBookCount() {
		return sameBookCount;
	}

	public void setSameBookCount(int sameBookCount) {
		this.sameBookCount = sameBookCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
