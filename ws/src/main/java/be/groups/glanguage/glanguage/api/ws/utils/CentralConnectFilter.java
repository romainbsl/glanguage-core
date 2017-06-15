package be.groups.glanguage.glanguage.api.ws.utils;

import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * Created by boissero on 6/14/2017.
 */
public class CentralConnectFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        JpaUtil.setCentralEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.CENTRAL_BE));
    }
}
