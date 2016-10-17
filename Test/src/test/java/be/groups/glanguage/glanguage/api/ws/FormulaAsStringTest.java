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
	public void testFormlaString900006() {
		Integer formulaId = 900006;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000 + 500", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900009() {
		Integer formulaId = 900009;
		Response response = target("/glanguage/formulaString/" + formulaId).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("900004 + 900005", response.readEntity(String.class));
	}
	
}
