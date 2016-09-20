package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaUnaryMinus}
 * 
 * @author DUPIREFR
 */
public class FormulaUnaryMinusTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaUnaryMinus#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaUnaryMinus formula = new FormulaUnaryMinus();

		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaUnaryMinus#getIntegerValue()} when parameter exists
	 */
	@Test
	public void testGetIntegerValueParameterExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);

		assertEquals(Integer.valueOf(-1), formula.getIntegerValue());
	}

	/**
	 * Tests {@link FormulaUnaryMinus#getIntegerValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(null);

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);

		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaUnaryMinus#getNumericValue()} when parameter exists
	 */
	@Test
	public void testGetNumericValueParameterExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(childFormula.getNumericValue()).thenReturn(1.5);

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);

		assertEquals(Double.valueOf(-1.5), formula.getNumericValue());
	}

	/**
	 * Tests {@link FormulaUnaryMinus#getNumericValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(childFormula.getNumericValue()).thenReturn(null);

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);

		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaUnaryMinus#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaUnaryMinus#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);

		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaUnaryMinus#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);
		
		formula.getDurationValue();
	}

	/**
	 * Tests {@link FormulaUnaryMinus#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaUnaryMinus formula = new FormulaUnaryMinus();

		assertEquals("-", formula.operationAsText());
	}

	/**
	 * Tests {@link FormulaUnaryMinus#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.asText()).thenReturn("some_rule");

		FormulaUnaryMinus formula = new FormulaUnaryMinus(childFormula);

		assertEquals("- some_rule", formula.asText());
	}

}