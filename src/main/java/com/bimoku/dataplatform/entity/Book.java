package com.bimoku.dataplatform.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bimoku.dataplatform.entity.dto.BookDTO;
import com.bimoku.dataplatform.entity.dto.BookDetailsDTO;

@Entity
@Table(name = "Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "UUID")
	private String uuid;

	@Column(name = "NAME")
	private String name;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "Book_Author",
			joinColumns = @JoinColumn(name="BOOK_ID"),
			inverseJoinColumns = @JoinColumn(name="AUTHOR_ID"))
	private Set<Author> authors = new HashSet<Author>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "Book_Translator",
			joinColumns = @JoinColumn(name = "BOOK_ID"),
			inverseJoinColumns = @JoinColumn(name="TRANSLATOR_ID"))
	private Set<Author> translators = new HashSet<Author>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRESS_ID")
	private Press press;

	@Column(name = "VERSION_NUM")
	private String versionNum;

	@Column(name = "WANTREAD")
	private int wantRead;

	@Column(name = "READING")
	private int reading;

	@Column(name = "HASREAD")
	private int hasRead;

	@Column(name = "COVER_PIC")
	private String coverPic;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<AssociatedTag> associatedTags = new HashSet<AssociatedTag>();
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private Set<Message> messages = new HashSet<Message>();

	@Column(name = "OUTLINE", columnDefinition = "TEXT")
	@Lob
	private String outline;
	
	@Column(name = "DIRECTORY", columnDefinition = "TEXT")
	@Lob
	private String directory;

	@Column(name = "PUB_PRICE")
	private double pubPrice;

	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	@Column(name = "IS_SEARCH_RANK", columnDefinition = "BIT", length = 1)
	private boolean isSearchRank;

	@Column(name = "IS_SALE_RANK", columnDefinition = "BIT", length = 1)
	private boolean isSaleRank;

	@Column(name = "IS_PROMOTION_RANK", columnDefinition = "BIT", length = 1)
	private boolean isPromotionRank;

	@Column(name = "IS_NEW_RANK", columnDefinition = "BIT", length = 1)
	private boolean isNewRank;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private Set<BookRaw> bookRaws = new HashSet<BookRaw>();
	
	public Book() {
	}
	
	public Book(BookDTO dto) {
		this.setName(dto.getName());
		this.setIsbn(dto.getIsbn());
		this.setCoverPic(dto.getCoverPic());
		this.setPubPrice(dto.getPubPrice());
	}
	
	public Book(BookDetailsDTO dto) {
		this.setName(dto.getName());
		this.setIsbn(dto.getIsbn());
		this.setCoverPic(dto.getCoverPic());
		this.setPubPrice(dto.getPubPrice());
		this.setDirectory(dto.getDirectory());
		this.setOutline(dto.getOutline());
		this.setVersionNum(dto.getVersionNum());
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

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Set<Author> getTranslators() {
		return translators;
	}

	public void setTranslators(Set<Author> translators) {
		this.translators = translators;
	}

	public Press getPress() {
		return press;
	}

	public void setPress(Press press) {
		this.press = press;
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

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public double getPubPrice() {
		return pubPrice;
	}

	public void setPubPrice(double pubPrice) {
		this.pubPrice = pubPrice;
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

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public boolean isSaleRank() {
		return isSaleRank;
	}

	public void setSaleRank(boolean isSaleRank) {
		this.isSaleRank = isSaleRank;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public Set<AssociatedTag> getAssociatedTags() {
		return associatedTags;
	}

	public void setAssociatedTags(Set<AssociatedTag> associatedTags) {
		this.associatedTags = associatedTags;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<BookRaw> getBookRaws() {
		return bookRaws;
	}

	public void setBookRaws(Set<BookRaw> bookRaws) {
		this.bookRaws = bookRaws;
	}

}