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

/**
 * Test class for {@link FormulaDurationDays}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationDaysTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationDays#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationDays formula = new FormulaDurationDays();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(10), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofDays(10L));
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(10), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDurationValue()} with date parameter
	 */
	@Test
	public void testGetDurationValueDateParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		assertEquals(Duration.ofDays(10L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDurationValue()} with duration parameter
	 */
	@Test
	public void testGetDurationValueDurationParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofDays(10L));
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		assertEquals(Duration.ofDays(10L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationDays formula = new FormulaDurationDays();
		
		assertEquals("days", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.asText()).thenReturn("some_rule");
		
		FormulaDurationDays formula = new FormulaDurationDays(Arrays.asList(childFormula));
		
		assertEquals("days(some_rule)", formula.asText());
	}
	
}
