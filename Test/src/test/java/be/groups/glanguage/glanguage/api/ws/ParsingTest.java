package be.groups.glanguage.glanguage.api.ws;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class ParsingTest extends BaseJerseyResourceTest {
	
	@Test
	public void test() {
		String formulaString = "0";
		Response response = target("/glanguage/parse/" + formulaString).request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
}
