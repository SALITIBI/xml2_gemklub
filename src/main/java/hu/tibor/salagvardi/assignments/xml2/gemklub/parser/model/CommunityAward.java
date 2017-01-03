package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommunityAward {
	@XmlAttribute
	private Integer count;
	@XmlValue
	private String title;
	
	public CommunityAward() {
	
	}
	
	public CommunityAward(Integer count, String title) {
		super();
		this.count = count;
		this.title = title;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
