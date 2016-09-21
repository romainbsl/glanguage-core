package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaDate}
 * 
 * @author DUPIREFR
 */
public class FormulaDateTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDate#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDate formula = new FormulaDate();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_DATE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDate#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDate formula = new FormulaDate();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDate#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue()).thenReturn("11/09/1992");
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(parameter));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaDate#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue()).thenReturn("11/09/1992");
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(parameter));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDate#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue()).thenReturn("11/09/1992");
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(parameter));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDate#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue()).thenReturn("11/09/1992");
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(parameter));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDate#getDateValue()} with string parameter
	 */
	@Test
	public void testGetDateValueWithStringParam() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue()).thenReturn("11/09/1992");
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(parameter));
		
		assertEquals(LocalDate.of(1992, 9, 11), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaDate#getDateValue()} with integers parameters
	 */
	@Test
	public void testGetDateValueWithIntParams() {
		AbstractFormula dayParam = mock(AbstractFormula.class);
		when(dayParam.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(dayParam.getIntegerValue()).thenReturn(11);
		
		AbstractFormula monthParam = mock(AbstractFormula.class);
		when(monthParam.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(monthParam.getIntegerValue()).thenReturn(9);
		
		AbstractFormula yearParam = mock(AbstractFormula.class);
		when(yearParam.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(yearParam.getIntegerValue()).thenReturn(1992);
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(dayParam, monthParam, yearParam));
		
		assertEquals(LocalDate.of(1992, 9, 11), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaDate#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue()).thenReturn("11/09/1992");
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(parameter));
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaDate#asText()} with string parameter
	 */
	@Test
	public void testAsTextWithStringParam() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.asText()).thenReturn("11/09/1992");
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(parameter));
		
		assertEquals("date(11/09/1992)", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaDate#asText()} with integers parameters
	 */
	@Test
	public void testAsTextWithIntParams() {
		AbstractFormula dayParam = mock(AbstractFormula.class);
		when(dayParam.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(dayParam.asText()).thenReturn("11");
		
		AbstractFormula monthParam = mock(AbstractFormula.class);
		when(monthParam.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(monthParam.asText()).thenReturn("9");
		
		AbstractFormula yearParam = mock(AbstractFormula.class);
		when(yearParam.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(yearParam.asText()).thenReturn("1992");
		
		FormulaDate formula = new FormulaDate(null, Arrays.asList(dayParam, monthParam, yearParam));
		
		assertEquals("date(11; 9; 1992)", formula.asText());
	}
	
}
