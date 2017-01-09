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
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.util.JAXBUtil;

public class GameParser {
	private Game game;
	
	public GameParser() {
		super();
		game = new Game();
	}

	public Game parseGamePage(String uri) throws IOException {
		Document gamePage = Jsoup.connect(uri).userAgent("Mozilla").get();
		parseGamePage(gamePage);
		game.setUri(uri);
		game.setGameId(ParserHelper.extractIdFromURI(uri));
		return game;
	}

	private void parseGamePage(Document gamePage) throws IOException{		
		extractTitle(gamePage);
		extractTheme(gamePage);
		extractSuggestedAgeGroup(gamePage);
		extractStyles(gamePage);
		extractShortDescription(gamePage);
		extractSalePrice(gamePage);
		extractPublisher(gamePage);
		extractRealPrice(gamePage);
		extractPlayerCount(gamePage);
		extractGameTimeInMinutes(gamePage);
		extractDetailedDescription(gamePage);
		extractCommunityAwards(gamePage);
		extractCategories(gamePage);
		extractAverageRating(gamePage);
		extractArrivalDateInDays(gamePage);
	}

	private void extractArrivalDateInDays(Document doc) throws IOException {
		String data = doc.select("div.product-informations > div.depo > a").first().text();
		game.setArrivalDateInDays(ParserHelper.extractArrivalDateInDays(data));
	}

	private void extractAverageRating(Document doc) throws IOException {
		String data = doc.select("div.product-view > div.ratings > div.rating-stars > span.rating-average").first().text();
		game.setAverageRating(ParserHelper.extractAverageRating(data));
	}

	private void extractCommunityAwards(Document doc) {
		List<CommunityAward> communityAwards = null;
		Elements communityAwardsElements = doc.select("div.advancedreviews-total-block > div.communtiy-awards > span.award");
		if(communityAwardsElements!= null && communityAwardsElements.size()!= 0){
			communityAwards = new ArrayList<>();
			for (Element awardElement : communityAwardsElements) {
				String awardTitle = awardElement.select("span.award-label").first().text();
				Integer awardCount = Integer.parseInt(awardElement.select("span.award-count").first().text());
				communityAwards.add(new CommunityAward(awardCount, awardTitle));
			}
		}
		game.setCommunityAwards(communityAwards);
	}

	private void extractDetailedDescription(Document doc) {
		game.setDetailedDescription(doc.select("div.description>div.std").text().trim());
	}

	private void extractGameTimeInMinutes(Document doc) throws IOException {
		Interval gameTimeInMinutes = null;
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
			
			gameTimeInMinutes = ParserHelper.extractInterval(matcher.group(1));
		}
		
		game.setGameTimeInMinutes(gameTimeInMinutes);
	}

	private void extractPlayerCount(Document doc) throws IOException {
		Interval playerCount = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label1");
		String data = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Játékosok száma")) {
				data = label.siblingElements().first().text().trim();
				break;
			}
		}
		if (data != null) {
			playerCount = ParserHelper.extractInterval(data);
		}
		game.setPlayerCount(playerCount);
	}

	private void extractPublisher(Document doc) {
		Elements labels = doc.select("div.product-informations>ul>li>span.label2");
		String data = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Kiadó")) {
				data = label.siblingElements().first().text().trim();
				break;
			}
		}
		game.setPublisher(data);
	}
	private void extractRealPrice(Document doc) throws IOException{
		String data = doc.select("div.price-info > div.price-box > p.old-price > span.price").text();
		game.setPrice(ParserHelper.extractPrice(data));
	}
	
	private void extractSalePrice(Document doc) throws IOException {
		String data = doc.select("div.price-info > div.price-box > p.special-price-8 > span.price").text();
		game.setSalePrice( ParserHelper.extractPrice(data));
	}
	
	private void extractShortDescription(Document doc) {
		game.setShortDescription(doc.select("div.short-description>div.std").text().trim());
	}

	private void extractStyles(Document doc) {
		List<String> stlyes = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label2");
		Elements styleElements = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Működés")) {
				styleElements = label.siblingElements().first().select("a");
				break;
			}
		}
		if (styleElements != null && styleElements.size() != 0) {
			stlyes = new ArrayList<>();
			for (Element styleElement : styleElements) {
				stlyes.add(styleElement.text().trim());
			}
		}

		game.setStyles(stlyes);
	}
	
	private void extractCategories(Document doc) {
		List<String> categories = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label2");
		Elements styleElements = null;
		for (Element label : labels) {
			if (label.text().trim().startsWith("Kategória")) {
				styleElements = label.siblingElements().first().select("a");
				break;
			}
		}
		if (styleElements != null && styleElements.size() != 0) {
			categories = new ArrayList<>();
			for (Element styleElement : styleElements) {
				categories.add(styleElement.text().trim());
			}
		}

		game.setCategories(categories);
	}

	private void extractSuggestedAgeGroup(Document doc) throws IOException {
		Interval suggestedAgeGroup = null;
		String data = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label1");
		for (Element label : labels) {
			if (label.text().trim().startsWith("Ajánlott életkor")) {
				data = label.siblingElements().first().text().trim();
				break;
			}
		}

		if (data != null) {
			suggestedAgeGroup = ParserHelper.extractInterval(data);
		}

		game.setSuggestedAgeGroup(suggestedAgeGroup);
	}
	
	private void extractTitle(Document doc) {
		game.setTitle(doc.select("div.product-name > h1").first().text().trim());
	}
	
	private void extractTheme(Document doc) {
		String ret = null;
		Elements labels = doc.select("div.product-informations>ul>li>span.label2");

		for (Element label : labels) {
			if (label.text().trim().startsWith("Téma")) {
				ret = label.siblingElements().first().text().trim();
				break;
			}
		}
		game.setTheme(ret);
	}
	
	public Game getGame() {
		return game;
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
