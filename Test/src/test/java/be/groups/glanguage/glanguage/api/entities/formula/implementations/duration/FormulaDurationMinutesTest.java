package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaDurationMinutes}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationMinutesTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationMinutes#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationMinutes formula = new FormulaDurationMinutes();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(120), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(childFormula));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(childFormula));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(childFormula));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(childFormula));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(childFormula));
		
		assertEquals(Duration.ofMinutes(120L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationMinutes formula = new FormulaDurationMinutes();
		
		assertEquals("minutes", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.asText()).thenReturn("some_rule");
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(childFormula));
		
		assertEquals("minutes(some_rule)", formula.asText());
	}
	
}
