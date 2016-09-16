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
 * Test class for {@link FormulaFormatNumeric}
 * 
 * @author DUPIREFR
 */
public class FormulaFormatNumericTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormatNumeric#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaFormatNumeric formula = new FormulaFormatNumeric();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(113.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("113,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(1131.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(6, formula.getStringValue().length());
		assertEquals("1131,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(1.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("*1,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("*11,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative but
	 * there are no handled sign
	 */
	@Test
	public void testGetStringValueNoneSignNegativeInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative but
	 * handled sign is positive
	 */
	@Test
	public void testGetStringValuePositiveSignNegativeInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.POSITIVE_ONLY);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative and
	 * handled sign is negative too
	 */
	@Test
	public void testGetStringValueNegativeSignNegativeInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("-11,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is positive but
	 * handled sign is negative
	 */
	@Test
	public void testGetStringValueNegativeSignPositiveInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative and both
	 * signs are handled
	 */
	@Test
	public void testGetStringValueBothSignsPositiveInteger() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("-11,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when decimal mark is punt
	 */
	@Test
	public void testGetStringValuePuntDecimalMark() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(".");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11.6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-11.57);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getIntegerValue()).thenReturn(1);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.getStringValue()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.getStringValue()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaFormatNumeric formula = new FormulaFormatNumeric();
		
		assertEquals("formatNumeric", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#asText()}
	 */
	@Test
	public void testAsText() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.asText()).thenReturn("11.57");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.asText()).thenReturn("5");
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.asText()).thenReturn("1");
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param4);
		
		AbstractFormula param5 = mock(AbstractFormula.class);
		when(param5.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param5.asText()).thenReturn("*");
		parameters.add(param5);
		
		AbstractFormula param6 = mock(AbstractFormula.class);
		when(param6.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param6.asText()).thenReturn(FormatSign.Values.NONE);
		parameters.add(param6);
		
		AbstractFormula param7 = mock(AbstractFormula.class);
		when(param7.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param7.asText()).thenReturn(",");
		parameters.add(param7);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(parameters);
		
		assertEquals("formatNumeric(11.57; 5; 1; " + FormatAlignment.Values.LEFT_JUSTIFY + "; *; " + FormatSign.Values.NONE + "; ,)",
				formula.asText());
	}
	
}
