package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaPrimitive}
 * 
 * @author DUPIREFR
 */
public class FormulaPrimitiveTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaPrimitive#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaPrimitive formula = new FormulaPrimitive();
		
		assertEquals(Integer.valueOf(FormulaType.Values.C_PRIMITIVE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaPrimitive#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaPrimitive formula = new FormulaPrimitive();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() throws GLanguageEvaluationException {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(null, primitive, Arrays.asList());
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageEvaluationException {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(null, primitive, Arrays.asList());
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageEvaluationException {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(null, primitive, Arrays.asList());
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageEvaluationException {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(null, primitive, Arrays.asList());
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageEvaluationException {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(null, primitive, Arrays.asList());
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageEvaluationException {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(null, primitive, Arrays.asList());
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#asText()}
	 */
	@Test
	public void testAsText() {
		String primitive = "call";
		
		AbstractFormula calls3Param1 = mock(AbstractFormula.class);
		when(calls3Param1.asText()).thenReturn("some_rule1");
		
		AbstractFormula calls3Param2 = mock(AbstractFormula.class);
		when(calls3Param2.asText()).thenReturn("some_rule2");
		
		List<AbstractFormula> parameters = Arrays.asList(calls3Param1, calls3Param2);
		
		FormulaPrimitive formula = new FormulaPrimitive(null, primitive, parameters);
		
		assertEquals("call(some_rule1; some_rule2)", formula.asText());
	}
	
}
