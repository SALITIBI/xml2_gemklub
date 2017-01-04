package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public Game createGame() {
		return new Game();
	}
	public CommunityAward createCommunityAward() {
		return new CommunityAward();
	}
	public Interval createInterval() {
		return new Interval();
	}
	public Price createPrice() {
		return new Price();
	}
	public SearchResultItem createSearchResultItem() {
		return new SearchResultItem();
	}
	public SearchResults createSearchResults() {
		return new SearchResults();
	}
}
