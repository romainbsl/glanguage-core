package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

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
 * Test class for {@link FormulaTerminalString}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalStringTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalString#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalString formula = new FormulaTerminalString();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_STRING), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalString formula = new FormulaTerminalString();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#isValid()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNoParameters() {
		FormulaTerminalString formula =
				new FormulaTerminalString(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING), "string");
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() {
		FormulaTerminalString formula =
				new FormulaTerminalString(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING), "string");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getReturnType()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNoParameters() {
		FormulaTerminalString formula =
				new FormulaTerminalString(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING), "string");
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() {
		FormulaTerminalString formula =
				new FormulaTerminalString(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING), "string");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		assertEquals("string", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		assertEquals("\"string\"", formula.asText());
	}
	
}
