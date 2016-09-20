package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaGet}
 * 
 * @author DUPIREFR
 */
public class FormulaGetTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaGet#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaGet formula = new FormulaGet();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaGet#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Tests {@link FormulaGet#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Tests {@link FormulaGet#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Tests {@link FormulaGet#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Tests {@link FormulaGet#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaReturnType returnType = FormulaReturnType.BOOLEAN;
		
		List<String> identifiers = Arrays.asList("call1", "call2", "call3");
		
		AbstractFormula calls3Param1 = mock(AbstractFormula.class);
		when(calls3Param1.asText()).thenReturn("some_rule1");
		
		AbstractFormula calls3Param2 = mock(AbstractFormula.class);
		when(calls3Param2.asText()).thenReturn("some_rule2");
		
		List<List<AbstractFormula>> parameters = Arrays.asList(Arrays.asList(), Arrays.asList(), Arrays.asList(calls3Param1, calls3Param2));
		
		FormulaGet formula = new FormulaGet(returnType, identifiers, parameters);
		
		assertEquals("get BOOLEAN call1().call2().call3(some_rule1; some_rule2)", formula.asText());
	}
	
}
