package be.groups.glanguage.glanguage.api.ws.utils;

import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jersey.listing.JerseyApiDeclarationProvider;
import com.wordnik.swagger.jersey.listing.JerseyResourceListingProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {
	
	public JerseyApplication() {
        packages("be.groups.presta.backoffice.ws.provider");
        packages("be.groups.presta.backoffice.ws.exception");
        packages("be.groups.presta.backoffice.ws.filter");
        packages("be.groups.presta.backoffice.ws.resources.version");
        packages("be.groups.errorframework.webapplication.provider");
        register(ApiListingResourceJSON.class);
        register(JerseyApiDeclarationProvider.class);
        register(JerseyResourceListingProvider.class);
        register(userDynamicBinding());
    }

    private Class<?> userDynamicBinding() {
        return BaseDynamicBinding.class;
    }
}
