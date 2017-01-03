package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.xml;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Price;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.SearchResultItem;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.util.JAXBUtil;

public class TestSearchResultToXML {
	private SearchResultItem searchResultItem;
	@Before
	public void init(){
		searchResultItem = new SearchResultItem();
		
	}
	@Test
	public void test1() throws JAXBException, IOException {
		searchResultItem.setUri("uri");
		searchResultItem.setTitle("Star Wars");
		searchResultItem.setPrice(new Price(new Double(5000),"Ft"));
		searchResultItem.setArrivalInDays(3);
		
		String actual = JAXBUtil.toXML(searchResultItem);
		System.out.println(actual);
	}
}
