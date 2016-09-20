package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaAnomaly}
 * 
 * @author DUPIREFR
 */
public class FormulaAnomalyTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaAnomaly#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaAnomaly formula = new FormulaAnomaly();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue()).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue()).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(parameters);
		
		assertEquals(Integer.valueOf(0), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue()).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue()).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(parameters);
		
		assertEquals(Double.valueOf(0), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue()).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue()).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(parameters);
		
		assertEquals("", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue()).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue()).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue()).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue()).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(parameters);
		
		assertEquals(LocalDate.MIN, formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue()).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue()).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(parameters);
		
		assertEquals(Duration.ZERO, formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaAnomaly#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.asText()).thenReturn("1");
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.asText()).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(parameters);
		
		assertEquals("putText(1; System error)", formula.asText());
	}
	
}
