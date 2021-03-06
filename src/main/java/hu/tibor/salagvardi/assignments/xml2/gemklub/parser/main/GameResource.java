package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.main;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Game;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser.GameParser;

public class GameResource extends ServerResource{
	private static Logger logger = LoggerFactory.getLogger(GameResource.class);
	
	@Get("xml|json")
	public Game represent(){
		String gameTitle = getAttribute("gametitle");
		Game game = null;
		
		try {
			GameParser gameParser = new GameParser();
			String uri = "http://www.gemklub.hu/"+ gameTitle + ".html";
			game = gameParser.parseGamePage(uri);
		} catch (Exception e) {
			logger.error("{} cause: {}",e.getMessage(),e.getCause());
			throw new ResourceException(404);
		}
		
		return game;
	}
}
