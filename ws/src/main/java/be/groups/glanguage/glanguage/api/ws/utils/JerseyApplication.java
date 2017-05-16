package be.groups.glanguage.glanguage.api.ws.utils;

import org.glassfish.jersey.server.ResourceConfig;

import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jersey.listing.JerseyApiDeclarationProvider;
import com.wordnik.swagger.jersey.listing.JerseyResourceListingProvider;

public class JerseyApplication extends ResourceConfig {
	
	public JerseyApplication() {
            packages("be.groups.presta.backoffice.ws.provider");
            packages("be.groups.presta.backoffice.ws.exception");
            packages("be.groups.presta.backoffice.ws.filter");
            packages("be.groups.presta.backoffice.ws.resources.version");
            register(ApiListingResourceJSON.class);
            register(JerseyApiDeclarationProvider.class);
            register(JerseyResourceListingProvider.class);
    }
	
}
