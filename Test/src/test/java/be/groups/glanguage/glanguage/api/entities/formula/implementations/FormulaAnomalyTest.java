package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	 * Tests {@link FormulaAnomaly#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaAnomaly formula = new FormulaAnomaly();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_PUT_TEXT), formula.getDiscriminatorValue());
	}
	
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
		when(errorCode.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue(null)).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(null, parameters);
		
		assertEquals(Integer.valueOf(0), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue(null)).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(null, parameters);
		
		assertEquals(Double.valueOf(0), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue(null)).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(null, parameters);
		
		assertEquals("", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue(null)).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(null, parameters);
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue(null)).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(null, parameters);
		
		assertEquals(LocalDate.MIN, formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaAnomaly#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.getStringValue(null)).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(null, parameters);
		
		assertEquals(Duration.ZERO, formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaAnomaly#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula errorCode = mock(AbstractFormula.class);
		when(errorCode.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(errorCode.asText()).thenReturn("1");
		
		AbstractFormula errorMessage = mock(AbstractFormula.class);
		when(errorMessage.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(errorMessage.asText()).thenReturn("System error");
		
		List<AbstractFormula> parameters = Arrays.asList(errorCode, errorMessage);
		
		FormulaAnomaly formula = new FormulaAnomaly(null, parameters);
		
		assertEquals("putText(1; System error)", formula.asText());
	}
	
}
