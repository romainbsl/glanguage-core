package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaSubString}
 * 
 * @author DUPIREFR
 */
public class FormulaSubStringTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaSubString#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaSubString formula = new FormulaSubString();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaSubString#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(3);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(9);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaSubString#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(3);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(9);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(3);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(9);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		assertEquals("special", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with negative begin index
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueNegativeIndex() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(-1);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(9);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with impossible index range (end index <
	 * begin index)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueImpossibleRange() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(3);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(2);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with end index too big
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueIndexTooBig() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(3);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(16);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaSubString#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(3);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(9);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaSubString#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(3);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(9);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaSubString#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue()).thenReturn(3);
		parameters.add(beginIndex);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue()).thenReturn(9);
		parameters.add(endIndex);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaSubString#asText()}
	 */
	@Test
	public void testAsText() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(separator.asText()).thenReturn("3");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.asText()).thenReturn("7");
		parameters.add(index);
		
		FormulaSubString formula = new FormulaSubString(null, parameters);
		
		assertEquals("subString(some_rule; 3; 7)", formula.asText());
	}
	
}
