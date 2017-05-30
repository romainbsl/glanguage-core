package be.groups.glanguage.glanguage.api.ws;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaIn;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.WsTestCategory;
import be.groups.marmota.test.TNSNames;
import be.groups.presta.backoffice.test.base.Environment;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

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
	public void testParseInteger() throws GLanguageException {
		String formulaString = "0";
		Response response = target("/glanguage/parse").request().post(Entity.json(formulaString));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		AbstractFormula formula = response.readEntity(FormulaTerminalInteger.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaTerminalInteger);
		assertEquals(0, formula.getValue());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testParseDouble() throws GLanguageException {
		String formulaString = "0.0";
		Response response = target("/glanguage/parse").request().post(Entity.json(formulaString));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		AbstractFormula formula = response.readEntity(FormulaTerminalNumeric.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaTerminalNumeric);
		assertEquals(0.0, formula.getValue());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testParseString() throws GLanguageException {
		String formulaString = "\"\"";
		Response response = target("/glanguage/parse").request().post(Entity.json(formulaString));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		AbstractFormula formula = response.readEntity(FormulaTerminalString.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaTerminalString);
		assertEquals("", formula.getValue());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testParseGet() {
		String formulaString = "get string contrat.nature()";
		Response response = target("/glanguage/parse").request().post(Entity.json(formulaString));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		FormulaGet formula = response.readEntity(FormulaGet.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaGet);
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testParseIn() throws GLanguageException {
		String formulaString = "1 in (2 ; 3)";
		Response response = target("/glanguage/parse").request().post(Entity.json(formulaString));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		FormulaIn formula = response.readEntity(FormulaIn.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaIn);
		assertFalse(formula.getBooleanValue());
	}
	
}
