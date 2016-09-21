package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaNot}
 * 
 * @author DUPIREFR
 */
public class FormulaNotTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaNot#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaNot formula = new FormulaNot();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_NOT), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaNot#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaNot formula = new FormulaNot();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaNot#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);
		
		FormulaNot formula = new FormulaNot(null, childFormula);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);
		
		FormulaNot formula = new FormulaNot(null, childFormula);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);
		
		FormulaNot formula = new FormulaNot(null, childFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getBooleanValue()} when parameter exists
	 */
	@Test
	public void testGetBooleanValueParameterExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);
		
		FormulaNot formula = new FormulaNot(null, childFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaNot#getBooleanValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueParameterNotExist() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(null);
		
		FormulaNot formula = new FormulaNot(null, childFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);
		
		FormulaNot formula = new FormulaNot(null, childFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);
		
		FormulaNot formula = new FormulaNot(null, childFormula);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaNot#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaNot formula = new FormulaNot();
		
		assertEquals("not", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaNot#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.asText()).thenReturn("some_rule");
		
		FormulaNot formula = new FormulaNot(null, childFormula);
		
		assertEquals("not some_rule", formula.asText());
	}
	
}
