package be.groups.glanguage.glanguage.api.ws;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.test.categories.WsTestCategory;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

public class FormulaAsStringTest extends BaseJerseyResourceTest {
	
	/*
	 * Setups
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();
		
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		
		if (!TransactionHelper.isActive()) {
			TransactionHelper.begin();
		}
	}
	
	@AfterClass
	public static void close() {
		if (TransactionHelper.isActive()) {
			TransactionHelper.rollback();
		}
	}
	
	/*
	 * Tests
	 */
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString0() {
		Integer formulaId = 0;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("10", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString1() {
		Integer formulaId = 1;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("10", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900000() {
		Integer formulaId = 900000;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000 + 500", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900001() {
		Integer formulaId = 900001;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000 + 500", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900002() {
		Integer formulaId = 900002;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000 + 500", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900003() {
		Integer formulaId = 900003;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("TRUE", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900004() {
		Integer formulaId = 900004;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000 + 500", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900005() {
		Integer formulaId = 900005;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000 + 500", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900006() {
		Integer formulaId = 900006;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000 + 500", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900007() {
		Integer formulaId = 900007;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000", response.readEntity(String.class));
	}

	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900008() {
		Integer formulaId = 900008;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("500", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900009() {
		Integer formulaId = 900009;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("900004 + 900005", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900010() {
		Integer formulaId = 900010;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("900004", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900011() {
		Integer formulaId = 900011;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("900005", response.readEntity(String.class));
	}
	
}
