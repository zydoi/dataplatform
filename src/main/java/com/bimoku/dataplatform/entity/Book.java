package com.bimoku.dataplatform.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String isbn;
	
	private String uuid;
	
	private String name;
	
	private String author;

	private String translator;
	
	private String press;
	
	private String version;
	
	private int wantRead;
	
	private int reading;
	
	private int hasRead;
	
	private String coverPic;
	
	private String catelog;
	
	private String AuthorIntro;
	
	private String directory;
	
	private String outline;
	
	private double price;
	
	private double pubPrice;
	
	private String relationShip;
	
	private String allPrice;
	
	private boolean isLock;
	
	private Date updateTime;
	
	private boolean isSearchRank;
	
	private boolean isOnSaleRank;
	
	private boolean isPromotionRank;
	
	private boolean isNewRank;
	
	private boolean hasComment;

	public Book() {
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getWantRead() {
		return wantRead;
	}

	public void setWantRead(int wantRead) {
		this.wantRead = wantRead;
	}

	public int getReading() {
		return reading;
	}

	public void setReading(int reading) {
		this.reading = reading;
	}

	public int getHasRead() {
		return hasRead;
	}

	public void setHasRead(int hasRead) {
		this.hasRead = hasRead;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getCatelog() {
		return catelog;
	}

	public void setCatelog(String catelog) {
		this.catelog = catelog;
	}

	public String getAuthorIntro() {
		return AuthorIntro;
	}

	public void setAuthorIntro(String authorIntro) {
		AuthorIntro = authorIntro;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPubPrice() {
		return pubPrice;
	}

	public void setPubPrice(double pubPrice) {
		this.pubPrice = pubPrice;
	}

	public String getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}

	public String getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isSearchRank() {
		return isSearchRank;
	}

	public void setSearchRank(boolean isSearchRank) {
		this.isSearchRank = isSearchRank;
	}

	public boolean isOnSaleRank() {
		return isOnSaleRank;
	}

	public void setOnSaleRank(boolean isOnSaleRank) {
		this.isOnSaleRank = isOnSaleRank;
	}

	public boolean isPromotionRank() {
		return isPromotionRank;
	}

	public void setPromotionRank(boolean isPromotionRank) {
		this.isPromotionRank = isPromotionRank;
	}

	public boolean isNewRank() {
		return isNewRank;
	}

	public void setNewRank(boolean isNewRank) {
		this.isNewRank = isNewRank;
	}

	public boolean isHasComment() {
		return hasComment;
	}

	public void setHasComment(boolean hasComment) {
		this.hasComment = hasComment;
	}
}
