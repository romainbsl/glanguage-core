package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.FormatAlignment;
import be.groups.glanguage.glanguage.api.entities.utils.FormatSign;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaFormatInteger}
 * 
 * @author DUPIREFR
 */
public class FormulaFormatIntegerTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormatInteger#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaFormatInteger formula = new FormulaFormatInteger();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_FORMAT_INTEGER), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaFormatInteger formula = new FormulaFormatInteger();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#isValid()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER),
				Arrays.asList(number, width, alignment, fill, sign));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#isValid()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER),
				Arrays.asList(number, width, alignment, fill, sign));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getReturnType()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER),
				Arrays.asList(number, width, alignment, fill, sign));
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10***", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10001);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10001", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(100011);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(6, formula.getStringValue().length());
		assertEquals("100011", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("**10*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("***10", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative but
	 * there are no handled sign
	 */
	@Test
	public void testGetStringValueNoneSignNegativeInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(-10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10***", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative but
	 * handled sign is positive
	 */
	@Test
	public void testGetStringValuePositiveSignNegativeInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(-10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.POSITIVE_ONLY);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10***", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative and
	 * handled sign is negative too
	 */
	@Test
	public void testGetStringValueNegativeSignNegativeInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(-10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("-10**", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is positive but
	 * handled sign is negative
	 */
	@Test
	public void testGetStringValueNegativeSignPositiveInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("10***", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative and both
	 * signs are handled
	 */
	@Test
	public void testGetStringValueBothSignsPositiveInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(-10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue().length());
		assertEquals("-10**", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue()).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getDurationValue();
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
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(number.asText()).thenReturn("10");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.asText()).thenReturn("5");
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.asText()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.asText()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals("formatInteger(10; 5; " + FormatAlignment.Values.LEFT_JUSTIFY + "; *; " + FormatSign.Values.NONE + ")",
				formula.asText());
	}
	
}
