package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;

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
	public void testGetIntegerValue() {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(primitive, Arrays.asList());
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(primitive, Arrays.asList());
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(primitive, Arrays.asList());
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(primitive, Arrays.asList());
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(primitive, Arrays.asList());
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		String primitive = "call";
		
		FormulaPrimitive formula = new FormulaPrimitive(primitive, Arrays.asList());
		
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
		
		FormulaPrimitive formula = new FormulaPrimitive(primitive, parameters);
		
		assertEquals("call(some_rule1; some_rule2)", formula.asText());
	}
	
}
