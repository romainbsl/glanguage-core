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
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.FormatAlignment;

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
	 * Tests {@link FormulaFormatString#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("value");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("value00000", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when value has exactly the correct size
	 */
	@Test
	public void testGetStringValueExact() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("valueexact");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("valueexact", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when value is too long
	 */
	@Test
	public void testGetStringValueTooLong() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("value_too_long");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("value_too_", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when alignment is center
	 */
	@Test
	public void testGetStringValueCenterAlign() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("value");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.CENTER_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("00value000", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getStringValue()} when alignment is right
	 */
	@Test
	public void testGetStringValueRightAlign() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("value");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.RIGHT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		assertEquals(10, formula.getStringValue().length());
		assertEquals("00000value", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormatString#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormatString#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.getStringValue()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getIntegerValue()).thenReturn(10);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.getStringValue()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.getStringValue()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
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
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param1.asText()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.asText()).thenReturn("10");
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param3.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);
		parameters.add(param3);
		
		AbstractFormula param4 = mock(AbstractFormula.class);
		when(param4.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(param4.asText()).thenReturn("0");
		parameters.add(param4);
		
		FormulaFormatString formula = new FormulaFormatString(null, parameters);
		
		assertEquals("formatString(some_rule1; 10; " + FormatAlignment.Values.LEFT_JUSTIFY + "; 0)", formula.asText());
	}
	
}
