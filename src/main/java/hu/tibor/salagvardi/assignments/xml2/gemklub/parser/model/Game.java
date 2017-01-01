package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model;

import java.util.List;

public class Game {
	private String title;
	private String publisher;
	private List<String> categories;
	private List<String> styles;
	private String theme;
	private double averageRating;
	private int arrivalDateInDays;
	private List<CommunityAward> communityAwards;
	private String shortDescription;
	private String detailedDescription;
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
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public int getArrivalDateInDays() {
		return arrivalDateInDays;
	}
	public void setArrivalDateInDays(int arrivalDateInDays) {
		this.arrivalDateInDays = arrivalDateInDays;
	}
	public List<CommunityAward> getCommunityAwards() {
		return communityAwards;
	}
	public void setCommunityAwards(List<CommunityAward> communityAwards) {
		this.communityAwards = communityAwards;
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
	
	
}
