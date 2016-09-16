package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

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
	 * Tests {@link FormulaNot#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaNot formula = new FormulaNot();

		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaNot#getBooleanValue()} when parameter exists
	 */
	@Test
	public void testGetBooleanValueParameterExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);

		FormulaNot formula = new FormulaNot(childFormula);

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

		FormulaNot formula = new FormulaNot(childFormula);

		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaNot#getBooleanValue()} when parameter exist but has
	 * wrong type
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetBooleanValueParameterWrongType() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getStringValue()).thenReturn("some_value");

		FormulaNot formula = new FormulaNot(childFormula);

		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaNot#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);

		FormulaNot formula = new FormulaNot(childFormula);

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

		FormulaNot formula = new FormulaNot(childFormula);

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

		FormulaNot formula = new FormulaNot(childFormula);

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaNot#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);

		FormulaNot formula = new FormulaNot(childFormula);

		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaNot#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(childFormula.getBooleanValue()).thenReturn(true);

		FormulaNot formula = new FormulaNot(childFormula);

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
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

		FormulaNot formula = new FormulaNot(childFormula);

		assertEquals("not some_rule", formula.asText());
	}

}
