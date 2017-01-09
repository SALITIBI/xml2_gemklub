package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResultItem {
	@XmlElement
	private String title;
	@XmlElement
	private Price price;
	@XmlElement
	private Double rating;
	@XmlElement
	private Integer arrivalInDays;
	@XmlAttribute
	private String uri;
	@XmlAttribute
	private String gameId;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getArrivalInDays() {
		return arrivalInDays;
	}

	public void setArrivalInDays(Integer arrivalInDays) {
		this.arrivalInDays = arrivalInDays;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	@Override
	public String toString() {
		return "SearchResultItem [title=" + title + ", price=" + price + ", rating=" + rating + ", arrivalInDays=" + arrivalInDays
				+ ", uri=" + uri + ", gameId=" + gameId + "]";
	}
	
	
	
}
