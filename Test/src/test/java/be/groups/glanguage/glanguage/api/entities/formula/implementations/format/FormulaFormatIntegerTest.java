package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.FormatAlignment;
import be.groups.glanguage.glanguage.api.entities.utils.FormatSign;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
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
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER),
				Arrays.asList(number, width, alignment, fill, sign));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getReturnType(Evaluator)} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER),
				Arrays.asList(number, width, alignment, fill, sign));
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("10***", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10001);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("10001", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(100011);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(6, formula.getStringValue(null).length());
		assertEquals("100011", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("**10*", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("***10", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative but
	 * there are no handled sign
	 */
	@Test
	public void testGetStringValueNoneSignNegativeInteger() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(-10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("10***", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative but
	 * handled sign is positive
	 */
	@Test
	public void testGetStringValuePositiveSignNegativeInteger() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(-10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.POSITIVE_ONLY);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("10***", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative and
	 * handled sign is negative too
	 */
	@Test
	public void testGetStringValueNegativeSignNegativeInteger() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(-10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("-10**", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is positive but
	 * handled sign is negative
	 */
	@Test
	public void testGetStringValueNegativeSignPositiveInteger() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NEGATIVE_ONLY);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("10***", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getStringValue()} when value to format is negative and both
	 * signs are handled
	 */
	@Test
	public void testGetStringValueBothSignsPositiveInteger() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(-10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.BOTH);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals(5, formula.getStringValue(null).length());
		assertEquals("-10**", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatInteger#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageEvaluationException {
		AbstractFormula number = mock(AbstractFormula.class);
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.getStringValue(null)).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		formula.getDurationValue(null);
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
		when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(number.asText()).thenReturn("10");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.asText()).thenReturn("5");
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.asText()).thenReturn("*");
		
		AbstractFormula sign = mock(AbstractFormula.class);
		when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(sign.asText()).thenReturn(FormatSign.Values.NONE);
		
		FormulaFormatInteger formula = new FormulaFormatInteger(null, Arrays.asList(number, width, alignment, fill, sign));
		
		assertEquals("formatInteger(10; 5; " + FormatAlignment.Values.LEFT_JUSTIFY + "; *; " + FormatSign.Values.NONE + ")",
				formula.asText());
	}
	
}
