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
public class SearchResults {
	@XmlElementWrapper(name = "searchResultItems")
	@XmlElement(name = "searchResultItem")
	private List<SearchResultItem> searchResultItems;
	@XmlAttribute
	private Category category;
	@XmlAttribute
	private String keyword;
	
	public List<SearchResultItem> getSearchResultItems() {
		return searchResultItems;
	}
	public void setSearchResultItems(List<SearchResultItem> searchResultItems) {
		this.searchResultItems = searchResultItems;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "SearchResults [searchResultItems=" + searchResultItems + ", category=" + category + ", keyword=" + keyword + "]";
	}
	
	
}
