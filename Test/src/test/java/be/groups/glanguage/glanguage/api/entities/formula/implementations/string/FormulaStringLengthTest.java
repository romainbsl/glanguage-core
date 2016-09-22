package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

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
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaStringLength}
 * 
 * @author DUPIREFR
 */
public class FormulaStringLengthTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaStringLength#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaStringLength formula = new FormulaStringLength();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_STRING_LENGTH), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaStringLength#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaStringLength formula = new FormulaStringLength();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaStringLength#isValid()} when parameter is string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaStringLength formula =
				new FormulaStringLength(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH), Arrays.asList(string));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaStringLength#isValid()} when parameter is not string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringLength formula =
				new FormulaStringLength(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH), Arrays.asList(string));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaStringLength#getReturnType()} when parameter is string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaStringLength formula =
				new FormulaStringLength(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH), Arrays.asList(string));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaStringLength#getReturnType()} when parameter is not string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringLength formula =
				new FormulaStringLength(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH), Arrays.asList(string));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaStringLength#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		
		FormulaStringLength formula = new FormulaStringLength(null, Arrays.asList(string));
		
		assertEquals(Integer.valueOf(8), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaStringLength#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		
		FormulaStringLength formula = new FormulaStringLength(null, Arrays.asList(string));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaStringLength#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		
		FormulaStringLength formula = new FormulaStringLength(null, Arrays.asList(string));
		
		assertEquals("8", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringLength#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		
		FormulaStringLength formula = new FormulaStringLength(null, Arrays.asList(string));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaStringLength#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		
		FormulaStringLength formula = new FormulaStringLength(null, Arrays.asList(string));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaStringLength#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a string");
		
		FormulaStringLength formula = new FormulaStringLength(null, Arrays.asList(string));
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaStringLength#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule");
		
		FormulaStringLength formula = new FormulaStringLength(null, Arrays.asList(string));
		
		assertEquals("stringLength(some_rule)", formula.asText());
	}
	
}
