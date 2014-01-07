package com.bimoku.dataplatform.entity.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BookDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookDetailsDTO extends BookDTO {

	@XmlElement(name = "Author")
	private List<AuthorDTO> authors = new ArrayList<AuthorDTO>();
	
	@XmlElement(name = "Translator")
	private List<AuthorDTO> translators  = new ArrayList<AuthorDTO>();
	
	@XmlElement(name = "Press")
	private String press;
	
	@XmlElement(name = "Version")
	private String versionNum;
	
	@XmlElement(name = "CoverPic")
	private String coverPic;
	
	@XmlElement(name = "Outline")
	private String outline;
	
	@XmlElement(name = "Directory")
	private String directory;
	
	@XmlElement(name = "PubPrice")
	private double pubPrice;

	@XmlElement(name = "Message")
	private List<MessageDTO> messages = new ArrayList<MessageDTO>();
	
	@XmlElement(name = "Tag")
	private List<TagDTO> tags = new ArrayList<TagDTO>();

	@XmlElement(name = "Recommendation")
	private List<BookDTO> recommendations = new ArrayList<BookDTO>();
	
	
	public List<AuthorDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDTO> authors) {
		this.authors = authors;
	}

	public List<AuthorDTO> getTranslators() {
		return translators;
	}

	public void setTranslators(List<AuthorDTO> translators) {
		this.translators = translators;
	}
	
	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public List<TagDTO> getTags() {
		return tags;
	}
	
	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
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

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public double getPubPrice() {
		return pubPrice;
	}

	public void setPubPrice(double pubPrice) {
		this.pubPrice = pubPrice;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

	public List<BookDTO> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<BookDTO> recommendations) {
		this.recommendations = recommendations;
	}

}
