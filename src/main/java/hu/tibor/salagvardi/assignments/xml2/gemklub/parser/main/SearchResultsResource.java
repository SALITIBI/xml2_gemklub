package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.main;

import java.io.IOException;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Category;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Game;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.SearchResults;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser.GameSearch;

public class SearchResultsResource extends ServerResource{
	@Get("xml|json")
	public SearchResults represent(){
		
		Category category = Category.valueOf(getQueryValue("category"));
		String keyword = getQueryValue("keyword");
		
		
		if (category == null || keyword == null) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Required parameter 'searchTerm' is missing");
		}
		SearchResults searchResults = new SearchResults();
		searchResults.setCategory(category);
		searchResults.setKeyword(keyword);
		GameSearch gameSearch = new GameSearch();
		try {
			searchResults.setSearchResultItems(gameSearch.doSearch(keyword, category));
		} catch (IOException e) {
			//TODO logger
		}
		return searchResults;
	}
}
