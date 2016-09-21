package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaDurationYears}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationYearsTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationYears#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationYears formula = new FormulaDurationYears();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_YEARS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationYears formula = new FormulaDurationYears();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(2015), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofDays(2 * 365));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getDurationValue()} with date parameter
	 */
	@Test
	public void testGetDurationValueDateParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		assertEquals(Duration.ofDays(2015 * 365), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getDurationValue()} with duration parameter
	 */
	@Test
	public void testGetDurationValueDurationParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofDays(2 * 365));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		assertEquals(Duration.ofDays(2 * 365), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationYears formula = new FormulaDurationYears();
		
		assertEquals("years", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.asText()).thenReturn("some_rule");
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(childFormula));
		
		assertEquals("years(some_rule)", formula.asText());
	}
	
}
