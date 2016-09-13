package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

/**
 * Test class for {@link FormulaExist}
 * 
 * @author DUPIREFR
 */
public class FormulaExistTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaExist#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaExist formula = new FormulaExist();

		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaExist#getBooleanValue()} when parameter exists
	 */
	@Test
	public void testGetBooleanValueParameterExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getValue()).thenReturn("some_value");

		FormulaExist formula = new FormulaExist(childFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaExist#getBooleanValue()} when parameter doesn't exist
	 */
	@Test
	public void testGetBooleanValueParameterNotExist() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getValue()).thenReturn(null);

		FormulaExist formula = new FormulaExist(childFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaExist#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getValue()).thenReturn("some_value");

		FormulaExist formula = new FormulaExist(childFormula);

		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaExist#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getValue()).thenReturn("some_value");

		FormulaExist formula = new FormulaExist(childFormula);

		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaExist#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getValue()).thenReturn("some_value");

		FormulaExist formula = new FormulaExist(childFormula);

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaExist#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getValue()).thenReturn("some_value");

		FormulaExist formula = new FormulaExist(childFormula);

		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaExist#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getValue()).thenReturn("some_value");

		FormulaExist formula = new FormulaExist(childFormula);

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaExist#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaExist formula = new FormulaExist();

		assertEquals("?", formula.operationAsText());
	}

	/**
	 * Tests {@link FormulaExist#operationAsText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.asText()).thenReturn("some_rule");

		FormulaExist formula = new FormulaExist(childFormula);

		assertEquals("? some_rule", formula.asText());
	}

}
