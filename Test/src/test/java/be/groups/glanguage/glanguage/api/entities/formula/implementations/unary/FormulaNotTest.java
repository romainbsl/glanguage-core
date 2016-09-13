package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import static org.junit.Assert.*;
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
		when(childFormula.getBooleanValue()).thenReturn(null);

		FormulaNot formula = new FormulaNot(childFormula);

		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaNot#getIntegerValue()}
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetIntegerValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getBooleanValue()).thenReturn(true);

		FormulaNot formula = new FormulaNot(childFormula);

		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaNot#getNumericValue()}
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetNumericValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getBooleanValue()).thenReturn(true);

		FormulaNot formula = new FormulaNot(childFormula);

		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaNot#getStringValue()}
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getBooleanValue()).thenReturn(true);

		FormulaNot formula = new FormulaNot(childFormula);

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaNot#getDateValue()}
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
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
	 * Tests {@link FormulaNot#operationAsText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.asText()).thenReturn("some_rule");

		FormulaNot formula = new FormulaNot(childFormula);

		assertEquals("not some_rule", formula.asText());
	}

}
