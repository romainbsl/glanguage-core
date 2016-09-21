package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaFormatDate}
 * 
 * @author DUPIREFR
 */
public class FormulaFormatDateTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormatDate#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaFormatDate formula = new FormulaFormatDate();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(param1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param2.getStringValue()).thenReturn("yyyy-MM-dd");
		parameters.add(param2);
		
		FormulaFormatDate formula = new FormulaFormatDate(null, parameters);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(param1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param2.getStringValue()).thenReturn("yyyy-MM-dd");
		parameters.add(param2);
		
		FormulaFormatDate formula = new FormulaFormatDate(null, parameters);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(param1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param2.getStringValue()).thenReturn("yyyy-MM-dd");
		parameters.add(param2);
		
		FormulaFormatDate formula = new FormulaFormatDate(null, parameters);
		
		assertEquals("2015-01-10", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getStringValue()} with another format
	 */
	@Test
	public void testGetStringValueAnotherFormat() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(param1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param2.getStringValue()).thenReturn("dd/MM/yyyy");
		parameters.add(param2);
		
		FormulaFormatDate formula = new FormulaFormatDate(null, parameters);
		
		assertEquals("10/01/2015", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(param1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param2.getStringValue()).thenReturn("yyyy-MM-dd");
		parameters.add(param2);
		
		FormulaFormatDate formula = new FormulaFormatDate(null, parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(param1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param2.getStringValue()).thenReturn("yyyy-MM-dd");
		parameters.add(param2);
		
		FormulaFormatDate formula = new FormulaFormatDate(null, parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(param1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param2.getStringValue()).thenReturn("yyyy-MM-dd");
		parameters.add(param2);
		
		FormulaFormatDate formula = new FormulaFormatDate(null, parameters);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaFormatDate#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaFormatDate formula = new FormulaFormatDate();
		
		assertEquals("formatDate", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#asText()}
	 */
	@Test
	public void testAsText() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(param1.asText()).thenReturn("10/01/2015");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param2.asText()).thenReturn("yyyy-MM-dd");
		parameters.add(param2);
		
		FormulaFormatDate formula = new FormulaFormatDate(null, parameters);
		
		assertEquals("formatDate(10/01/2015; yyyy-MM-dd)", formula.asText());
	}
	
}
