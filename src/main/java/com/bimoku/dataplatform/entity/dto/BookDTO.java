package com.bimoku.dataplatform.entity.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookDTO {

	@XmlAttribute(name = "id")
	private int id;

	@XmlElement(name = "isbn")
	private String isbn;

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "coverPic")
	private String coverPic;

	@XmlElement(name = "pubPrice")
	private double pubPrice;

	@XmlElement(name = "commentCount")
	private int commentCount;

	@XmlElement(name = "likeCount")
	private int likeCount;

	@XmlElement(name = "messages")
	private List<MessageDTO> messages;

	public BookDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public double getPubPrice() {
		return pubPrice;
	}

	public void setPubPrice(double pubPrice) {
		this.pubPrice = pubPrice;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

}
