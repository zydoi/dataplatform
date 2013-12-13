package bimoku.dataplatform.entity.DTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookDTO {

	@XmlAttribute
	private int id;
	
	@XmlElement(name = "UUID")
	private String uuid;
	
	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "UUID")
	private String author;
	
	@XmlElement(name = "Translator")
	private String translator;
	
	@XmlElement(name = "Press")
	private String press;
	
	@XmlElement(name = "Version")
	private String version;
	
	@XmlElement(name = "CoverPic")
	private String coverPic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	
}
