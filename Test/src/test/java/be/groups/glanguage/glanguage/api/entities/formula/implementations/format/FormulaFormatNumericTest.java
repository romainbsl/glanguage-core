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
 * Test class for {@link FormulaFormatNumeric}
 * 
 * @author DUPIREFR
 */
public class FormulaFormatNumericTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormatNumeric#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaFormatNumeric formula = new FormulaFormatNumeric();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_FORMAT_NUMERIC), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaFormatNumeric formula = new FormulaFormatNumeric();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#isValid()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_NUMERIC),
				Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#isValid()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_NUMERIC),
				Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getReturnType()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatNumeric formula = new FormulaFormatNumeric(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_NUMERIC),
				Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaFormatNumeric#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(113.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("113,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(1131.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(6, formula.getStringValue().length());
		assertEquals("1131,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(1.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("*1,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("*11,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative but
	 * there are no handled sign
	 */
	@Test
	public void testGetStringValueNoneSignNegativeInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative but
	 * handled sign is positive
	 */
	@Test
	public void testGetStringValuePositiveSignNegativeInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.POSITIVE_ONLY);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative and
	 * handled sign is negative too
	 */
	@Test
	public void testGetStringValueNegativeSignNegativeInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("-11,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is positive but
	 * handled sign is negative
	 */
	@Test
	public void testGetStringValueNegativeSignPositiveInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11,6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative and both
	 * signs are handled
	 */
	@Test
	public void testGetStringValueBothSignsPositiveInteger() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("-11,6", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when decimal mark is punt
	 */
	@Test
	public void testGetStringValuePuntDecimalMark() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(".");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals(5, formula.getStringValue().length());
		assertEquals("11.6*", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue()).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue()).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue()).thenReturn(FormatSign.Values.BOTH);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		formula.getDurationValue();
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
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(number.asText()).thenReturn("11.57");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.asText()).thenReturn("5");
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.asText()).thenReturn("1");
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.asText()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(sign.asText()).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(mark.asText()).thenReturn(",");
		
		FormulaFormatNumeric formula =
				new FormulaFormatNumeric(null, Arrays.asList(number, width, decimals, alignment, fill, sign, mark));
				
		assertEquals("formatNumeric(11.57; 5; 1; " + FormatAlignment.Values.LEFT_JUSTIFY + "; *; " + FormatSign.Values.NONE + "; ,)",
				formula.asText());
	}
	
}
