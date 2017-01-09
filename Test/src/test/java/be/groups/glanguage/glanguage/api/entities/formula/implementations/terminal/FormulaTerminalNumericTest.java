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
 * Test class for {@link FormulaTerminalNumeric}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalNumericTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalNumeric#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_NUMERIC), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#isValid()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNoParameters() throws GLanguageException {
		FormulaTerminalNumeric formula =
				new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "1.5");
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() throws GLanguageException {
		FormulaTerminalNumeric formula =
				new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "1.5");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getReturnType()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNoParameters() throws GLanguageException {
		FormulaTerminalNumeric formula =
				new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "1.5");
				
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() throws GLanguageException {
		FormulaTerminalNumeric formula =
				new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "1.5");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		assertEquals(Double.valueOf(1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		assertEquals("1.5", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageException {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageException {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageException {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		assertEquals("1.5", formula.asText());
	}
	
}
