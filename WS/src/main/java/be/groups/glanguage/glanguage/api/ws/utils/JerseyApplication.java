package be.groups.glanguage.glanguage.api.ws.utils;

import org.glassfish.jersey.server.ResourceConfig;

import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jersey.listing.JerseyApiDeclarationProvider;
import com.wordnik.swagger.jersey.listing.JerseyResourceListingProvider;

public class JerseyApplication extends ResourceConfig {
	
	public JerseyApplication() {
        packages("be.groups.common.jaxrs");
        packages("be.groups.common.json");
        packages("be.groups.common.resource");
        packages("be.groups.common.ws");
        register(ApiListingResourceJSON.class);
        register(JerseyApiDeclarationProvider.class);
        register(JerseyResourceListingProvider.class);
        register(dynamicBindingClass());
    }

    protected Class<?> dynamicBindingClass() {
        return BaseDynamicBinding.class;
    }
}
