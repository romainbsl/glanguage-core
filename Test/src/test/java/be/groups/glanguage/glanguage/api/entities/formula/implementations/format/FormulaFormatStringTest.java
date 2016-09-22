package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaFormatString}
 * 
 * @author DUPIREFR
 */
public class FormulaFormatStringTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormatString#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaFormatString formula = new FormulaFormatString();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_FORMAT_STRING), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaFormatString formula = new FormulaFormatString();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormatString#isValid()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatString formula = new FormulaFormatString(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_STRING),
				Arrays.asList(string, width, alignment, fill));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatString#isValid()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatString formula = new FormulaFormatString(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_STRING),
				Arrays.asList(string, width, alignment, fill));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getReturnType()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatString formula = new FormulaFormatString(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_STRING),
				Arrays.asList(string, width, alignment, fill));
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getReturnType()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatString formula = new FormulaFormatString(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_STRING),
				Arrays.asList(string, width, alignment, fill));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("value");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("value00000", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("valueexact");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("valueexact", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("value_too_long");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("value_too_", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("value");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("00value000", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("value");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("00000value", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue()).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaFormatString formula = new FormulaFormatString();
		
		assertEquals("formatString", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaFormatString#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(width.asText()).thenReturn("10");
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(alignment.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(fill.asText()).thenReturn("0");
		
		FormulaFormatString formula = new FormulaFormatString(null, Arrays.asList(string, width, alignment, fill));
		
		assertEquals("formatString(some_rule1; 10; " + FormatAlignment.Values.LEFT_JUSTIFY + "; 0)", formula.asText());
	}
	
}
