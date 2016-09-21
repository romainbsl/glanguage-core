package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaStringLength}
 * 
 * @author DUPIREFR
 */
public class FormulaStringLengthTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaStringLength#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaStringLength formula = new FormulaStringLength();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_STRING_LENGTH), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaStringLength#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaStringLength formula = new FormulaStringLength();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaStringLength#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		parameters.add(string);
		
		FormulaStringLength formula = new FormulaStringLength(null, parameters);
		
		assertEquals(Integer.valueOf(8), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaStringLength#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		parameters.add(string);
		
		FormulaStringLength formula = new FormulaStringLength(null, parameters);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaStringLength#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		parameters.add(string);
		
		FormulaStringLength formula = new FormulaStringLength(null, parameters);
		
		assertEquals("8", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringLength#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		parameters.add(string);
		
		FormulaStringLength formula = new FormulaStringLength(null, parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaStringLength#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		parameters.add(string);
		
		FormulaStringLength formula = new FormulaStringLength(null, parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaStringLength#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		parameters.add(string);
		
		FormulaStringLength formula = new FormulaStringLength(null, parameters);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaStringLength#asText()}
	 */
	@Test
	public void testAsText() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule");
		parameters.add(string);
		
		FormulaStringLength formula = new FormulaStringLength(null, parameters);
		
		assertEquals("stringLength(some_rule)", formula.asText());
	}
	
}
