package be.groups.glanguage.glanguage.api.ws;

import be.groups.glanguage.glanguage.api.ws.utils.JerseyApplication;

public class Application extends JerseyApplication {
	
	public Application() {
		super();
		packages("be.groups.glanguage.glanguage.api.ws");
	}
	
}
