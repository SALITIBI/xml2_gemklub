package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Game {
	@XmlAttribute
	private String gameId;
	@XmlAttribute
	private String uri;
	@XmlElement
	private String title;
	@XmlElement
	private String publisher;
	@XmlElement
	private String theme;
	@XmlElement
	private Double averageRating;
	@XmlElement
	private Integer arrivalDateInDays;
	@XmlElement
	private String shortDescription;
	@XmlElement
	private String detailedDescription;
	@XmlElement
	private Interval suggestedAgeGroup;
	@XmlElement
	private Interval gameTimeInMinutes;
	@XmlElement
	private Interval playerCount;
	@XmlElement
	private Price price;
	@XmlElement
	private Price salePrice;
	@XmlElementWrapper(name = "categories")
	@XmlElement(name = "category")
	private List<String> categories;
	@XmlElementWrapper(name = "styles")
	@XmlElement(name = "style")
	private List<String> styles;
	@XmlElementWrapper(name = "communityAwards")
	@XmlElement(name = "communityAward")
	private List<CommunityAward> communityAwards;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getArrivalDateInDays() {
		return arrivalDateInDays;
	}

	public void setArrivalDateInDays(Integer arrivalDateInDays) {
		this.arrivalDateInDays = arrivalDateInDays;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public Interval getSuggestedAgeGroup() {
		return suggestedAgeGroup;
	}

	public void setSuggestedAgeGroup(Interval suggestedAgeGroup) {
		this.suggestedAgeGroup = suggestedAgeGroup;
	}

	public Interval getGameTimeInMinutes() {
		return gameTimeInMinutes;
	}

	public void setGameTimeInMinutes(Interval gameTimeInMinutes) {
		this.gameTimeInMinutes = gameTimeInMinutes;
	}

	public Interval getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(Interval playerCount) {
		this.playerCount = playerCount;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Price getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Price salePrice) {
		this.salePrice = salePrice;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getStyles() {
		return styles;
	}

	public void setStyles(List<String> styles) {
		this.styles = styles;
	}

	public List<CommunityAward> getCommunityAwards() {
		return communityAwards;
	}

	public void setCommunityAwards(List<CommunityAward> communityAwards) {
		this.communityAwards = communityAwards;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", uri=" + uri + ", title=" + title + ", publisher=" + publisher + ", theme=" + theme
				+ ", averageRating=" + averageRating + ", arrivalDateInDays=" + arrivalDateInDays + ", shortDescription=" + shortDescription
				+ ", detailedDescription=" + detailedDescription + ", suggestedAgeGroup=" + suggestedAgeGroup + ", gameTimeInMinutes="
				+ gameTimeInMinutes + ", playerCount=" + playerCount + ", price=" + price + ", salePrice=" + salePrice + ", categories="
				+ categories + ", styles=" + styles + ", communityAwards=" + communityAwards + "]";
	}

	
}
