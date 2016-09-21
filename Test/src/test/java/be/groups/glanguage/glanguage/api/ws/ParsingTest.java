package be.groups.glanguage.glanguage.api.ws;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.test.categories.WsTestCategory;

public class ParsingTest extends BaseJerseyResourceTest {
	
	@Category(WsTestCategory.class)
	@Test
	public void test() {
		String formulaString = "0";
		Response response = target("/glanguage/parse/" + formulaString).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
}
