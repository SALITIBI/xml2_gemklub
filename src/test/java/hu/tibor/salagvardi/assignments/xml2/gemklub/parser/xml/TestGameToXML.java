package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.CommunityAward;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Game;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Interval;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Price;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.util.JAXBUtil;

public class TestGameToXML {
	private Game game;
	@Before
	public void init(){
		game = new Game();
		
	}
	@Test
	public void test1() throws JAXBException, IOException {
		game.setUri("uri");
		game.setTitle("Star Wars");
		game.setTheme("Scifi");
		List<String> styles = new ArrayList<>();
		styles.add("Style 1");
		styles.add("Style 2");
		game.setStyles(styles);
		game.setShortDescription("This is a short description.");
		game.setDetailedDescription("This is a more detailed description.");
		game.setPrice(new Price(new Double(5000),"HUF"));
		game.setSalePrice(new Price(new Double(4999),"HUF"));
		List<CommunityAward> communityAwards = new ArrayList<>();
		CommunityAward communityAward1 = new CommunityAward();
		communityAward1.setCount(5);
		communityAward1.setTitle("Award no.1");
		communityAwards.add(communityAward1);
		CommunityAward communityAward2 = new CommunityAward();
		communityAward2.setCount(5);
		communityAward2.setTitle("Award no.2");
		communityAwards.add(communityAward2);
		game.setCommunityAwards(communityAwards);
		game.setPublisher("John Doe");
		List<String> categories = new ArrayList<>();
		categories.add("Category no.1");
		categories.add("Category no.2");
		game.setCategories(categories);
		game.setAverageRating(new Double(4));
		game.setArrivalDateInDays(3);
		game.setPlayerCount(new Interval(4,6));
		game.setSuggestedAgeGroup(new Interval(3,99));
		game.setGameTimeInMinutes(new Interval(30,50));
		String actual = JAXBUtil.toXML(game);
		System.out.println(actual);
	}

}
