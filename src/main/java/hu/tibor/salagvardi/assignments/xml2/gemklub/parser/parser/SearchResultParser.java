package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser;

import java.io.IOException;
import org.jsoup.nodes.Element;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Price;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.SearchResultItem;

public class SearchResultParser {
	private SearchResultItem searchResultItem;

	public SearchResultParser() {
		searchResultItem = new SearchResultItem();
	}
	
	

	public SearchResultItem parseSearchResultItem(Element element) throws IOException {
		Price price = ParserHelper.extractPrice(element.select("p.normal-price > span > span.price").first().text());
		searchResultItem.setPrice(price);
		Element titleAndURI = element.select("h3.prod-name > a").first();
		String title = titleAndURI.text();
		String uri = titleAndURI.attr("href");
		searchResultItem.setUri(uri);
		searchResultItem.setGameId(ParserHelper.extractIdFromURI(uri));
		searchResultItem.setTitle(title);
		Double rating = ParserHelper.extractAverageRating(element.select("div.ratings > div.rating-stars > span.rating-average").first().text());
		searchResultItem.setRating(rating);
		Element shipping = element.select("div.product-icons > ul.icon-list > li").first();
		Integer arrivalDateInDays = ParserHelper.extractArrivalDateInDays(shipping.select("span").first().attr("title"));
		searchResultItem.setArrivalInDays(arrivalDateInDays);
		return searchResultItem;
	}

}
