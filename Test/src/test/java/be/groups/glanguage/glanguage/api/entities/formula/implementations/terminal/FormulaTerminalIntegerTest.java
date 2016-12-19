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
 * Test class for {@link FormulaTerminalInteger}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalIntegerTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalInteger#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_INTEGER), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#isValid()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNoParameters() {
		FormulaTerminalInteger formula =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), "1");
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() {
		FormulaTerminalInteger formula =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), "1");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getReturnType()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNoParameters() {
		FormulaTerminalInteger formula =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), "1");
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() {
		FormulaTerminalInteger formula =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), "1");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		assertEquals(Double.valueOf(1), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		assertEquals("1", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		assertEquals("1", formula.asText());
	}
	
}
