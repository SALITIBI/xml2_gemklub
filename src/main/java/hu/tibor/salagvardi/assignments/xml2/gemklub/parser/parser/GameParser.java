package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.CommunityAward;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Game;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Interval;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Price;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.util.JAXBUtil;

public class GameParser {
	public Game parseGamePage(String uri) throws IOException {
		Document gamePage = Jsoup.connect(uri).userAgent("Mozilla").get();
		Game ret = parseGamePage(gamePage);
		ret.setUri(uri);
		return ret;
	}

	private Game parseGamePage(Document gamePage) throws IOException{
		Game ret = new Game();
		ret.setTitle(extractTitle(gamePage));
		ret.setTheme(extractTheme(gamePage));
		ret.setSuggestedAgeGroup(extractSuggestedAgeGroup(gamePage));
		ret.setStyles(extractStyles(gamePage));
		ret.setShortDescription(extractShortDescription(gamePage));
		ret.setSalePrice(extractSalePrice(gamePage));
		ret.setPublisher(extractPublisher(gamePage));
		ret.setPrice(extractRealPrice(gamePage));
		ret.setPlayerCount(extractPlayerCount(gamePage));
		ret.setGameTimeInMinutes(extractGameTimeInMinutes(gamePage));
		ret.setDetailedDescription(extractDetailedDescription(gamePage));
		ret.setCommunityAwards(extractCommunityAwards(gamePage));
		ret.setCategories(extractCategories(gamePage));
		ret.setAverageRating(extractAverageRating(gamePage));
		ret.setArrivalDateInDays(extractArrivalDateInDays(gamePage));
		return ret;
	}

	private Integer extractArrivalDateInDays(Document doc) throws IOException {
		String data = doc.select("div.product-informations > div.depo > a").first().text();

		Pattern pattern = Pattern.compile(".*?(\\d+).*?");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {
			throw new IOException("Malformed document");
		}
		return Integer.parseInt(matcher.group(1));
	}



	private Double extractAverageRating(Document doc) throws IOException {
		String data = doc.select("div.product-view > div.ratings > div.rating-stars > span.rating-average").first().text();
		Pattern pattern = Pattern.compile("\\(([\\d\\.]+?)\\)");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {
			throw new IOException("Malformed document");
		}
		return Double.parseDouble(matcher.group(1));
	}



	private List<CommunityAward> extractCommunityAwards(Document doc) {
		List<CommunityAward> ret = null;
		Elements communityAwardsElements = doc.select("div.advancedreviews-total-block > div.communtiy-awards > span.award");
		if(communityAwardsElements!= null && communityAwardsElements.size()!= 0){
			ret = new ArrayList<>();
			for (Element awardElement : communityAwardsElements) {
				String awardTitle = awardElement.select("span.award-label").first().text();
				Integer awardCount = Integer.parseInt(awardElement.select("span.award-count").first().text());
				ret.add(new CommunityAward(awardCount, awardTitle));
			}
		}
		return ret;
	}

	private String extractDetailedDescription(Document doc) {
		return doc.select("div.description>div.std").text().trim();
	}

	private Interval extractGameTimeInMinutes(Document doc) throws IOException {
		Interval ret = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label1");
		String data = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Játékidő")) {
				data = label.siblingElements().first().text().trim();
				break;
			}
		}
		if (data != null) {
			Pattern pattern = Pattern.compile("(.+?)\\s*perc");
			Matcher matcher = pattern.matcher(data);
			if (!matcher.matches()) {
				throw new IOException("Malformed document");
			}
			
			ret = extractInterval(matcher.group(1));
		}
		
		return ret;
	}

	private Interval extractPlayerCount(Document doc) throws IOException {
		Interval ret = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label1");
		String data = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Játékosok száma")) {
				data = label.siblingElements().first().text().trim();
				break;
			}
		}
		if (data != null) {
			ret = extractInterval(data);
		}
		return ret;
	}

	private String extractPublisher(Document doc) {
		Elements labels = doc.select("div.product-informations>ul>li>span.label2");
		String data = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Kiadó")) {
				data = label.siblingElements().first().text().trim();
				break;
			}
		}
		return data;
	}
	private Price extractRealPrice(Document doc) throws IOException{
		String data = doc.select("div.price-info > div.price-box > p.old-price > span.price").text();
		return extractPrice(data);
	}
	private Price extractPrice(String data) throws IOException {
		Pattern pattern = Pattern.compile("([\\d\\s\\xA0]+)[\\s\\xA0](Ft)");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {
			throw new IOException("Malformed document");
		}
		return new Price(extractPriceValue(matcher.group(1)), matcher.group(2));
	}

	private Price extractSalePrice(Document doc) throws IOException {
		String data = doc.select("div.price-info > div.price-box > p.special-price-8 > span.price").text();
		return extractPrice(data);
	}
	
	private Double extractPriceValue(String data){
		Pattern nonBreakableSpace = Pattern.compile("\\xA0");
		Matcher matcher2 = nonBreakableSpace.matcher(data);
		String value = matcher2.replaceAll("");
		return new Double(Double.parseDouble(value));
	}
	
	private String extractShortDescription(Document doc) {
		return doc.select("div.short-description>div.std").text().trim();
	}

	private List<String> extractStyles(Document doc) {
		List<String> ret = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label2");
		Elements styleElements = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Működés")) {
				styleElements = label.siblingElements().first().select("a");
				break;
			}
		}
		if (styleElements != null && styleElements.size() != 0) {
			ret = new ArrayList<>();
			for (Element styleElement : styleElements) {
				ret.add(styleElement.text().trim());
			}
		}

		return ret;
	}
	
	private List<String> extractCategories(Document doc) {
		List<String> ret = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label2");
		Elements styleElements = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Kategória")) {
				styleElements = label.siblingElements().first().select("a");
				break;
			}
		}
		if (styleElements != null && styleElements.size() != 0) {
			ret = new ArrayList<>();
			for (Element styleElement : styleElements) {
				ret.add(styleElement.text().trim());
			}
		}

		return ret;
	}

	private Interval extractSuggestedAgeGroup(Document doc) throws IOException {
		Interval ret = null;
		String data = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label1");
		for (Element label : labels) {
			if (label.text().trim().startsWith("Ajánlott életkor")) {
				data = label.siblingElements().first().text().trim();
				break;
			}
		}

		if (data != null) {
			ret = extractInterval(data);
		}

		return ret;
	}

	private String extractTheme(Document doc) {
		String ret = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label2");

		for (Element label : labels) {
			if (label.text().trim().startsWith("Téma")) {
				ret = label.siblingElements().first().text().trim();
				break;
			}
		}
		return ret;
	}
	private Interval extractInterval(String data) throws IOException{
		Pattern pattern = Pattern.compile("(\\d+)\\s*-\\s*(\\d+)");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {
			throw new IOException("Malformed document");
		}
		return new Interval(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));

	}
	private String extractTitle(Document doc) {
		return doc.select("div.product-name > h1").first().text().trim();
	}

	public static void main(String[] args) throws IOException, JAXBException {
		GameParser gp = new GameParser();
		System.out.println(JAXBUtil.toXML(gp.parseGamePage(
				"http://www.gemklub.hu/bakugan-harci-jargany-bakugan-mobile-assault-before-bakugan-deluxe-battle-gear.html")));
		System.out.println(
				JAXBUtil.toXML(gp.parseGamePage("http://www.gemklub.hu/star-wars-imperial-assault-lando-calrissian-ally-pack.html")));
		System.out.println(JAXBUtil.toXML(gp.parseGamePage("http://www.gemklub.hu/dobble-magyar-kiadas.html")));
		System.out.println(JAXBUtil.toXML(gp.parseGamePage("http://www.gemklub.hu/bang-magyar-kiadas.html")));
		System.out.println(JAXBUtil.toXML(gp.parseGamePage("http://www.gemklub.hu/timeline-star-wars-magyar-kiadas.html")));

	}
}
