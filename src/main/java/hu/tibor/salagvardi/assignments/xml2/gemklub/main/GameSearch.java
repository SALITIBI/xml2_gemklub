package hu.tibor.salagvardi.assignments.xml2.gemklub.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.SearchResultItem;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser.Category;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser.SearchResultParser;

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
	
	public void doSearch(String name, Category category) throws IOException{
		doSearch(URI, name, category, limit);
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	private void doSearch(String uri,String name, Category category, int remainingItems) throws IOException{
		Document doc = Jsoup.connect(uri).userAgent("Mozilla").timeout(TIMEOUT_IN_SECONDS).data("cat",String.valueOf(category.getUriCode()),"q",name).get();
		Elements productItemElements = doc.select("div.amshopby-page-container>div.category-products>div.row>div >div.product-item");
		int itemCount = productItemElements.size();
		Element toolbar = doc.select("div.toolbar > div.pages > ol > li > a.next").first();
		
		for (Element productItemElement : productItemElements) {
			Element productInfo = productItemElement.select("div.product-info").first();
			searchResults.add(new SearchResultParser().parseSearchResultItem(productInfo));
		}
		if(toolbar != null){
			String nextPageURI = toolbar.attr("href");
			if(remainingItems-itemCount > 0){
				doSearch(nextPageURI, name, category,remainingItems - itemCount);
			}
		}
		
	}
	public static void main (String[] args) throws IOException{
		GameSearch gs = new GameSearch();
		gs.doSearch("a", Category.GIFT);
	}
}
