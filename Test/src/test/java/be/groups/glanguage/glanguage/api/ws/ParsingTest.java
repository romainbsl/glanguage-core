package be.groups.glanguage.glanguage.api.ws;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.action.standard.AsStandard;
import be.groups.glanguage.glanguage.api.business.analysis.byaccj.SlangTab;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaIn;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalString;
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
	public void testParseInteger() {
		String formulaString = "0";
		Response response = target("/glanguage/parse/" + formulaString).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		AbstractFormula formula = response.readEntity(FormulaTerminalInteger.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaTerminalInteger);
		assertEquals(0, formula.getValue());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testParseDouble() {
		String formulaString = "0.0";
		Response response = target("/glanguage/parse/" + formulaString).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		AbstractFormula formula = response.readEntity(FormulaTerminalNumeric.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaTerminalNumeric);
		assertEquals(0.0, formula.getValue());
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testParseString() {
		String formulaString = "\"\"";
		Response response = target("/glanguage/parse/" + formulaString).request().get();
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
		Response response = target("/glanguage/parse/" + formulaString).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		FormulaGet formula = response.readEntity(FormulaGet.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaGet);
	}
	
	@Category(WsTestCategory.class)
	@Test
	public void testParseIn() {
		String formulaString = "r1 in (2 ; 3)";
		Response response = target("/glanguage/parse/" + formulaString).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		FormulaIn formula = response.readEntity(FormulaIn.class);
		assertNotNull(formula);
		assertTrue("Formula object type unexcpeted " + formula.getClass(), formula instanceof FormulaIn);
	}
	
}
