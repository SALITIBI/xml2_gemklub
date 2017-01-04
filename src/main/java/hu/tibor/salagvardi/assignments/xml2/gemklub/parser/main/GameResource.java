package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.main;

import java.io.IOException;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Game;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser.GameParser;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser.GameSearch;

public class GameResource extends ServerResource{
	@Get("xml|json")
	public Game represent(){
		String gameURI = getAttribute("gameuri");
		Game game = null;
		GameParser gameParser = new GameParser();
		try {
			String uri = "http://www.gemklub.hu/"+ gameURI + ".html";
			game = gameParser.parseGamePage(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return game;
	}
}
