package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Interval {
	@XmlAttribute
	private Integer lowerBound;
	@XmlAttribute
	private Integer upperBound;
	
	public Interval(){
		super();
	}

	public Interval(Integer lowerBound, Integer upperBound) {
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public Integer getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(Integer lowerBound) {
		this.lowerBound = lowerBound;
	}

	public Integer getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(Integer upperBound) {
		this.upperBound = upperBound;
	}
	
	
	
	
}
