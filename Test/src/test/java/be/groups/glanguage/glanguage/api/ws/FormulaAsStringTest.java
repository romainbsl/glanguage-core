package be.groups.glanguage.glanguage.api.ws;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

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
		Integer ruleSetVersionId = -900003;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), response.getStatus());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900000() {
		Integer formulaId = -900000;
		Integer ruleSetVersionId = -900002;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), response.getStatus());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900001() {
		Integer formulaId = -900001;
		Integer ruleSetVersionId = -900000;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), response.getStatus());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900002() {
		Integer formulaId = -900002;
		Integer ruleSetVersionId = -900002;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), response.getStatus());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900003() {
		Integer formulaId = -900003;
		Integer ruleSetVersionId = -900002;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("TRUE", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900004() {
		Integer formulaId = -900004;
		Integer ruleSetVersionId = -900002;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), response.getStatus());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900005() {
		Integer formulaId = -900005;
		Integer ruleSetVersionId = -900002;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), response.getStatus());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900006() {
		Integer formulaId = -900006;
		Integer ruleSetVersionId = -900003;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000 + 500", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900007() {
		Integer formulaId = -900007;
		Integer ruleSetVersionId = -900003;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("1000", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900008() {
		Integer formulaId = -900008;
		Integer ruleSetVersionId = -900003;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("500", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900009() {
		Integer formulaId = -900009;
		Integer ruleSetVersionId = -900003;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("r5 + r6", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900010() {
		Integer formulaId = -900010;
		Integer ruleSetVersionId = -900003;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("r5", response.readEntity(String.class));
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testFormlaString900011() {
		Integer formulaId = -900011;
		Integer ruleSetVersionId = -900003;
		LocalDateTime effectivityDate = LocalDateTime.now();
		Response response = target("/glanguage/formulaString/" + formulaId + "/" + ruleSetVersionId)
				.queryParam("effectivityDate", effectivityDate)
				.request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("r6", response.readEntity(String.class));
	}
	
}
