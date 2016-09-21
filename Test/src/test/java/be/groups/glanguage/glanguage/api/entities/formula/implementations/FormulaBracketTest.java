package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaBracket}
 * 
 * @author DUPIREFR
 */
public class FormulaBracketTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaBracket#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaBracket formula = new FormulaBracket();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_BRACKETS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaBracket formula = new FormulaBracket();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaBracket#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getNumericValue()).thenReturn(2.5);
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(Double.valueOf(2.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getStringValue()).thenReturn("some_value");
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals("some_value", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getBooleanValue()).thenReturn(true);
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getDurationValue()).thenReturn(Duration.ofDays(2L));
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(Duration.ofDays(2L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals("(some_rule)", formula.asText());
	}
	
}
