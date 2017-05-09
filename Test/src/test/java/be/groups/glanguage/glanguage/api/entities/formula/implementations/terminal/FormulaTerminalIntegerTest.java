package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	public void testIsValidNoParameters() throws GLanguageException {
		FormulaTerminalInteger formula =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), "1");
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() throws GLanguageException {
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
	public void testGetReturnTypeNoParameters() throws GLanguageException {
		FormulaTerminalInteger formula =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), "1");
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() throws GLanguageException {
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
	public void testGetIntegerValue() throws GLanguageException {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		assertEquals(Double.valueOf(1), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		assertEquals("1", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalInteger#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		FormulaTerminalInteger formula = new FormulaTerminalInteger(null, "1");
		assertEquals("1", formula.asText());
	}
	
}
