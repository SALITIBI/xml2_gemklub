package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Category;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.SearchResultItem;

public class GameSearch {
	private static final int TIMEOUT_IN_SECONDS = 60000;
	private static final String URI ="http://www.gemklub.hu/catalogsearch/result/";
	private static final int LIMIT = 100;
	private Integer limit;
	private List<SearchResultItem> searchResults;
	
	public GameSearch() {
		super();
		this.limit = LIMIT;
		this.searchResults = new ArrayList<>();
	}
	
	public GameSearch(Integer limit) {
		super();
		this.limit = limit;
		this.searchResults = new ArrayList<>();
	}
	
	public List<SearchResultItem> doSearch(String keyword, Category category) throws IOException{
		return doSearch(URI, keyword, category, limit);
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	private List<SearchResultItem> doSearch(String uri,String keyword, Category category, int remainingItems) throws IOException{
		Document doc = Jsoup.connect(uri).userAgent("Mozilla").timeout(TIMEOUT_IN_SECONDS).data("cat",String.valueOf(category.getUriCode()),"q",keyword).get();
		Elements productItemElements = doc.select("div.amshopby-page-container>div.category-products>div.row>div >div.product-item");
		Element toolbar = doc.select("div.toolbar > div.pages > ol > li > a.next").first();
		int itemCount;
		for (itemCount = 0; itemCount < Math.min(productItemElements.size(), remainingItems); itemCount++) {
			Element productItemElement = productItemElements.get(itemCount);
			Element productInfo = productItemElement.select("div.product-info").first();
			searchResults.add(new SearchResultParser().parseSearchResultItem(productInfo));
		}
		if(toolbar != null){
			String nextPageURI = toolbar.attr("href");
			if(remainingItems-itemCount > 0){
				doSearch(nextPageURI, keyword, category,remainingItems - itemCount);
			}
		}
		return searchResults;
	}
	public List<SearchResultItem> getSearchResults() {
		return searchResults;
	}

	public static void main (String[] args) throws IOException{
		GameSearch gs = new GameSearch(5);
		gs.doSearch("a", Category.GIFT);
		for (SearchResultItem result : gs.getSearchResults()) {
			System.out.println(result);
		}
	}
}
