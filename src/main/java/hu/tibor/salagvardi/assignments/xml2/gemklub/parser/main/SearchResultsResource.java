package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.main;


import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Category;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.SearchResults;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser.GameSearch;

public class SearchResultsResource extends ServerResource{
	private static Logger logger = LoggerFactory.getLogger(SearchResultsResource.class);
	@Get("xml|json")
	public SearchResults represent(){
		
		
		SearchResults searchResults = new SearchResults();
		try {
			Category category = Category.valueOf(getQueryValue("category"));
			String keyword = getQueryValue("keyword");
			String limitParameter = getQueryValue("limit");
			
			
			if (category == null || keyword == null) {
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Required parameter(s) 'keyword' and/or 'category' is missing");
			}
			searchResults.setCategory(category);
			searchResults.setKeyword(keyword);
			GameSearch gameSearch = null;
			if(limitParameter!=null){
				int limit = Integer.parseInt(limitParameter);
				gameSearch = new GameSearch(limit);
			}else{
				gameSearch = new GameSearch();
			}
			searchResults.setSearchResultItems(gameSearch.doSearch(keyword, category));
		} catch (Exception e) {
			logger.error("{} cause: {}",e.getMessage(),e.getCause());
			throw new ResourceException(404);
		}
		return searchResults;
	}
}
