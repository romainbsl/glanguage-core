package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.utils.FormatAlignment;
import be.groups.glanguage.glanguage.api.entities.utils.FormatSign;

/**
 * Test class for {@link FormulaFormatInteger}
 * 
 * @author DUPIREFR
 */
public class FormulaFormatIntegerTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormatInteger#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaFormatInteger formula = new FormulaFormatInteger();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10***", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10001);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10001", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(100011);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(6, formula.getStringValue().length());
		assertEquals("100011", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("**10*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("***10", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative but
	 * there are no handled sign
	 */
	@Test
	public void testGetStringValueNoneSignNegativeInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(-10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10***", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative but
	 * handled sign is positive
	 */
	@Test
	public void testGetStringValuePositiveSignNegativeInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(-10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.POSITIVE_ONLY);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10***", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative and
	 * handled sign is negative too
	 */
	@Test
	public void testGetStringValueNegativeSignNegativeInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(-10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("-10**", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is positive but
	 * handled sign is negative
	 */
	@Test
	public void testGetStringValueNegativeSignPositiveInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10***", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative and both
	 * signs are handled
	 */
	@Test
	public void testGetStringValueBothSignsPositiveInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(-10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("-10**", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getIntegerValue()).thenReturn(10);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaFormatInteger formula = new FormulaFormatInteger();
		
		assertEquals("formatInteger", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#asText()}
	 */
	@Test
	public void testAsText() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.asText()).thenReturn("10");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.asText()).thenReturn("5");
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.asText()).thenReturn("*");
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.asText()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param5);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(parameters);
		
		assertEquals("formatInteger(10; 5; " + FormatAlignment.Values.LEFT_JUSTIFY + "; *; " + FormatSign.Values.NONE + ")",
				formula.asText());
	}
	
}
