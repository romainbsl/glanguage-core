package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaRoundingBankers}
 * 
 * @author DUPIREFR
 */
public class FormulaRoundingBankersTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaRoundingBankers#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaRoundingBankers formula = new FormulaRoundingBankers();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_BANKERS_ROUNDED), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaRoundingBankers formula = new FormulaRoundingBankers();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#isValid()} when parameter is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#isValid()} when parameter is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#isValid()} when parameter is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#isValid()} when parameter is integer and precision is
	 * integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidIntegerInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#isValid()} when parameter is numeric and precision is
	 * integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumericInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#isValid()} when parameter is numeric and precision is
	 * numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumericNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getReturnType()} when parameter is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getReturnType()} when parameter is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getReturnType()} when parameter is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getReturnType()} when parameter is integer and
	 * precision is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeIntegerInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getReturnType()} when parameter is numeric and
	 * precision is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumericInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getReturnType()} when parameter is numeric and
	 * precision is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumericNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingBankers formula =
				new FormulaRoundingBankers(FormulaDescriptionFactory.getDescription(FormulaType.F_BANKERS_ROUNDED),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getIntegerValue()} with integer parameter
	 */
	@Test
	public void testGetIntegerValueInt() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(117);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(precision.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		assertEquals(Integer.valueOf(117), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getIntegerValue()} with numeric parameter
	 */
	@Test
	public void testGetIntegerValueNum() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(precision.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getNumericValue()} with integer parameter
	 */
	@Test
	public void testGetNumericValueInt() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(117);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(precision.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		assertEquals(Double.valueOf(117), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getNumericValue()} with numeric parameter
	 */
	@Test
	public void testGetNumericValueNum() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.575);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(precision.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		assertEquals(Double.valueOf(1.58), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(precision.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(precision.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(precision.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(precision.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaRoundingBankers formula = new FormulaRoundingBankers();
		
		assertEquals("bankers_rounded", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaRoundingBankers#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.asText()).thenReturn("some_rule");
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.asText()).thenReturn("2");
		
		FormulaRoundingBankers formula = new FormulaRoundingBankers(null, null, parameter, precision);
		
		assertEquals("bankers_rounded(some_rule; 2)", formula.asText());
	}
	
}
