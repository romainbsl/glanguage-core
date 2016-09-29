package be.groups.glanguage.glanguage.api.ws;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.BeforeClass;

import be.groups.common.test.utils.Environment;

public class BaseJerseyResourceTest extends JerseyTest {

    @Override
    protected javax.ws.rs.core.Application configure() {
        enableTestProperties();
        return new Application();
    }

    @BeforeClass
    public static void setUpLyricsJersey() {
        Environment.setUp();
        System.setProperty("env", "dev");
    }

    protected void configureClient(ClientConfig config) {
        config.property("jersey.config.client.httpUrlConnection.setMethodWorkaround", Boolean.valueOf(true));
        super.configureClient(config);
    }

    protected void enableTestProperties() {
        this.enable("jersey.config.test.logging.enable");
        this.enable("jersey.config.test.logging.dumpEntity");
    }
}
