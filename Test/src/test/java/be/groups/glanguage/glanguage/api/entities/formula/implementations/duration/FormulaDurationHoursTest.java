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
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaDurationHours}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationHoursTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationHours#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_HOURS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(childFormula));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(childFormula));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(childFormula));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(childFormula));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(childFormula.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(childFormula));
		
		assertEquals(Duration.ofHours(2L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertEquals("hours", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.asText()).thenReturn("some_rule");
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(childFormula));
		
		assertEquals("hours(some_rule)", formula.asText());
	}
	
}
