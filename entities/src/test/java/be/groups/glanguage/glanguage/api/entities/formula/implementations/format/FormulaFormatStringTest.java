package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.format.FormatAlignment;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaFormatString}
 * 
 * @author DUPIREFR
 */
public class FormulaFormatStringTest {
	
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
	 * Tests {@link FormulaFormatString#getReturnType(Evaluator)} when parameters match
	 */
	@Test
	public void testGetReturnTypeMatching() throws GLanguageException {
		FormulaFormatString formula = spy(FormulaFormatString.class);

		assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaFormatString#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatString#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("value");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		assertEquals(10, formula.getStringValue(null).length());
		assertEquals("value00000", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("valueexact");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		assertEquals(10, formula.getStringValue(null).length());
		assertEquals("valueexact", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("value_too_long");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		assertEquals(10, formula.getStringValue(null).length());
		assertEquals("value_too_", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("value");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		assertEquals(10, formula.getStringValue(null).length());
		assertEquals("00value000", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("value");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		assertEquals(10, formula.getStringValue(null).length());
		assertEquals("00000value", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatString#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatString#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatString#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("some_rule1");
		
		AbstractFormula width = mock(AbstractFormula.class);
		when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(width.getIntegerValue(null)).thenReturn(10);
		
		AbstractFormula alignment = mock(AbstractFormula.class);
		when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		
		AbstractFormula fill = mock(AbstractFormula.class);
		when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(fill.getStringValue(null)).thenReturn("0");

		FormulaFormatString formula = spy(FormulaFormatString.class);
		doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatString#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaFormatString formula = new FormulaFormatString();
		
		assertEquals("formatString", formula.operationAsText());
	}

}
