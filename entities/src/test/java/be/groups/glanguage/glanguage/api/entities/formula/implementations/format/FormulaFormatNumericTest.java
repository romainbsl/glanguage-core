package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.format.FormatAlignment;
import be.groups.glanguage.glanguage.api.entities.utils.format.FormatSign;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

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
	 * Tests {@link FormulaFormatNumeric#getReturnType()} when parameters match
	 */
	@Test
	public void testGetReturnTypeMatching() throws GLanguageException {
		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);

		assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaFormatNumeric#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("11,6*", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(113.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("113,6", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(1131.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(6, formula.getStringValue(null).length());
		assertEquals("1131,6", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(1.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("*1,6*", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("*11,6", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative but
	 * there are no handled sign
	 */
	@Test
	public void testGetStringValueNoneSignNegativeInteger() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("11,6*", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative but
	 * handled sign is positive
	 */
	@Test
	public void testGetStringValuePositiveSignNegativeInteger() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.POSITIVE_ONLY);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("11,6*", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative and
	 * handled sign is negative too
	 */
	@Test
	public void testGetStringValueNegativeSignNegativeInteger() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("-11,6", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is positive but
	 * handled sign is negative
	 */
	@Test
	public void testGetStringValueNegativeSignPositiveInteger() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("11,6*", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when value to format is negative and both
	 * signs are handled
	 */
	@Test
	public void testGetStringValueBothSignsPositiveInteger() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.BOTH);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("-11,6", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getStringValue()} when decimal mark is punt
	 */
	@Test
	public void testGetStringValuePuntDecimalMark() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(".");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("11.6*", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.BOTH);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.BOTH);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(number.getNumericValue(null)).thenReturn(-11.57);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula decimals = mock(AbstractFormula.class);
		when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(decimals.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.BOTH);
		
		AbstractFormula mark = mock(AbstractFormula.class);
		when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(mark.getStringValue(null)).thenReturn(",");

		FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
		doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula).getParameters();

		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatNumeric#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaFormatNumeric formula = new FormulaFormatNumeric();
		
		assertEquals("formatNumeric", formula.operationAsText());
	}

}
