package be.groups.glanguage.glanguage.api.ws.utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import be.groups.common.ws.utils.filter.DisconnectFilter;
import be.groups.common.ws.utils.filter.LoggingFilter;
import be.groups.common.ws.utils.filter.MDCFilter;
import be.groups.common.ws.utils.filter.NoCacheFilter;
import be.groups.common.ws.utils.filter.OriginFilter;
import be.groups.common.ws.utils.filter.ReadBodyFilter;
import be.groups.common.ws.utils.filter.ReleaseFilter;
import be.groups.common.ws.utils.filter.StopWatchFilter;

public class BaseDynamicBinding implements DynamicFeature {
	
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		if (!getIgnoredMethods().contains(resourceInfo.getResourceMethod().getName())) {
			// Request filters
			requestFilters().forEach(context::register);

			// Response filters
			responseFilters().forEach(context::register);
		}
		context.register(OriginFilter.class);
	}

	protected Set<String> getIgnoredMethods() {
		Set<String> result = new HashSet<>();
		result.add("apply");
		result.add("resourceListing");
		result.add("apiDeclaration");
		result.add("getVersion");
		return result;
	}

	protected List<Class<?>> requestFilters() {
		List<Class<?>> result = new LinkedList<>();
		result.add(ReadBodyFilter.class);
		result.add(MDCFilter.class);
		result.add(StopWatchFilter.class);
		return result;
	}

	protected List<Class<?>> responseFilters() {
		List<Class<?>> result = new LinkedList<>();
		result.add(NoCacheFilter.class);
		result.add(DisconnectFilter.class);
		result.add(getLoggingFilterClass());
		result.add(ReleaseFilter.class);
		return result;
	}

	protected Class<?> getLoggingFilterClass() {
		return LoggingFilter.class;
	}
	
}
