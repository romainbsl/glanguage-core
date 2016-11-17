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
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

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
	 * Tests {@link FormulaGet#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaGet formula = new FormulaGet();
		
		assertEquals(Integer.valueOf(FormulaType.Values.C_GET), formula.getDiscriminatorValue());
	}
	
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
		// TODO implement test
		fail("Not yet implemented");
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		// TODO implement test
		fail("Not yet implemented");
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		// TODO implement test
		fail("Not yet implemented");
	}
	
	/**
	 * Tests {@link FormulaGet#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		// TODO implement test
		fail("Not yet implemented");
	}
	
	/**
	 * Tests {@link FormulaGet#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		// TODO implement test
		fail("Not yet implemented");
	}
	
	/**
	 * Tests {@link FormulaGet#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		// TODO implement test
		fail("Not yet implemented");
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
		
		List<List<AbstractFormula>> parameters =
				Arrays.asList(Arrays.asList(), Arrays.asList(), Arrays.asList(calls3Param1, calls3Param2));
				
		FormulaGet formula = new FormulaGet(null, null, returnType, identifiers, parameters);
		
		assertEquals("get BOOLEAN call1.call2.call3(some_rule1; some_rule2)", formula.asText());
	}
	
}
