package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum Category {
	ALL(0,"Mind"), KIDS(1441,"Gyerek társasjátékok"), GIFT(1448,"Gyerekjátékok, ajándékok"), STRATEGY(1450,"Stratégiai társasjátékok"), FAMILY(1455,"Családi társasjátékok"), LOGIC_PUZZLE(1467,"Logikai játékok, Puzzle"), PARTY(1485,"Parti társasjátékok");
	private int uriCode;
	@XmlAttribute
	private String fullName;
	
	private Category(int uriCode, String fullName) {
		this.uriCode = uriCode;
		this.fullName = fullName;
	}
	public String getFullName() {
		return fullName;
	}
	
	public int getUriCode() {
		return uriCode;
	}
	
}
