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
 * Test class for {@link FormulaRoundingFloor}
 * 
 * @author DUPIREFR
 */
public class FormulaRoundingFloorTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaRoundingFloor#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaRoundingFloor formula = new FormulaRoundingFloor();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_FLOOR), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaRoundingFloor formula = new FormulaRoundingFloor();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#isValid()} when parameter is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#isValid()} when parameter is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#isValid()} when parameter is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#isValid()} when parameter is integer and precision is
	 * integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidIntegerInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#isValid()} when parameter is numeric and precision is
	 * integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumericInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#isValid()} when parameter is numeric and precision is
	 * numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumericNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getReturnType()} when parameter is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getReturnType()} when parameter is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getReturnType()} when parameter is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, null);
						
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getReturnType()} when parameter is integer and
	 * precision is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeIntegerInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getReturnType()} when parameter is numeric and
	 * precision is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumericInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getReturnType()} when parameter is numeric and
	 * precision is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumericNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingFloor formula =
				new FormulaRoundingFloor(FormulaDescriptionFactory.getDescription(FormulaType.F_FLOOR),
						FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), parameter, precision);
						
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getIntegerValue()} with integer parameter
	 */
	@Test
	public void testGetIntegerValueInt() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(117);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		assertEquals(Integer.valueOf(110), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getIntegerValue()} with numeric parameter
	 */
	@Test
	public void testGetIntegerValueNum() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getNumericValue()} with integer parameter
	 */
	@Test
	public void testGetNumericValueInt() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(117);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		assertEquals(Double.valueOf(110), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getNumericValue()} with numeric parameter
	 */
	@Test
	public void testGetNumericValueNum() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		assertEquals(Double.valueOf(1.56), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaRoundingFloor formula = new FormulaRoundingFloor();
		
		assertEquals("floor", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.asText()).thenReturn("some_rule");
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.asText()).thenReturn("0.01");
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(null, null, parameter, precision);
		
		assertEquals("floor(some_rule; 0.01)", formula.asText());
	}
	
}
