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
 * Test class for {@link FormulaTerminalBoolean}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalBooleanTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalBoolean#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_BOOLEAN), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#isValid()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNoParameters() throws GLanguageException {
		FormulaTerminalBoolean formula =
				new FormulaTerminalBoolean(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN), true);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() throws GLanguageException {
		FormulaTerminalBoolean formula =
				new FormulaTerminalBoolean(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN), true);
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getReturnType()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNoParameters() throws GLanguageException {
		FormulaTerminalBoolean formula =
				new FormulaTerminalBoolean(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN), true);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() throws GLanguageException {
		FormulaTerminalBoolean formula =
				new FormulaTerminalBoolean(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN), true);
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		assertEquals("true", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() throws GLanguageException {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		assertEquals("true", formula.asText());
	}
	
}
