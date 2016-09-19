package be.groups.glanguage.glanguage.api.ws;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

public class ParsingTest extends BaseJerseyResourceTest {
	
	@Test
	public void test() {
		String formulaString = "";
		Response response = target("/glanguage/parse/" + formulaString).request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
}
