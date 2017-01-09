package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.main;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class GemklubApplication extends Application {
	@Override
	public Restlet createInboundRoot() {
		Router	router = new Router(getContext());
		router.setDefaultMatchingQuery(true);
		router.attach("/game/{gametitle}", GameResource.class);
		router.attach("/search?{query}", SearchResultsResource.class);
		return router;
	}
	public static void main(String[] args) throws Exception{
		Component component = new Component();
		component.getDefaultHost().attach("/gemklub", new GemklubApplication());
		Server	server = new Server(Protocol.HTTP, 8111, component);
		server.start();
	}
}
