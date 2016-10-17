package be.groups.glanguage.glanguage.api.ws;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.test.categories.WsTestCategory;
import be.groups.marmota.test.TNSNames;

public class ParsingTest extends BaseJerseyResourceTest {
	
	/*
	 * Setups
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void test() {
		String formulaString = "0";
		Response response = target("/glanguage/parse/" + formulaString).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
}
