package be.groups.glanguage.glanguage.api.ws;

import be.groups.common.test.utils.WsJerseyTest;

public class BaseJerseyResourceTest extends WsJerseyTest {

    @Override
    protected javax.ws.rs.core.Application configure() {
        enableTestProperties();
        return new be.groups.glanguage.glanguage.api.ws.Application();
    }
	
}
