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
 * Test class for {@link FormulaDurationMonths}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationMonthsTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationMonths#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationMonths formula = new FormulaDurationMonths();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofDays(2 * 31));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDurationValue()} with date parameter
	 */
	@Test
	public void testGetDurationValueDateParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(childFormula.getDateValue()).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		assertEquals(Duration.ofDays(2 * 31), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDurationValue()} with duration parameter
	 */
	@Test
	public void testGetDurationValueDurationParameter() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofDays(2 * 31));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		assertEquals(Duration.ofDays(2 * 31), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationMonths formula = new FormulaDurationMonths();
		
		assertEquals("months", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.asText()).thenReturn("some_rule");
		
		FormulaDurationMonths formula = new FormulaDurationMonths(Arrays.asList(childFormula));
		
		assertEquals("months(some_rule)", formula.asText());
	}
	
}
