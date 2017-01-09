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

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaTerminalDate}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalDateTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalDate#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_DATE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalDate formula = new FormulaTerminalDate();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#isValid()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNoParameters() throws GLanguageException {
		FormulaTerminalDate formula =
				new FormulaTerminalDate(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE), "01/01/2015");
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() throws GLanguageException {
		FormulaTerminalDate formula =
				new FormulaTerminalDate(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE), "01/01/2015");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getReturnType()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNoParameters() throws GLanguageException {
		FormulaTerminalDate formula =
				new FormulaTerminalDate(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE), "01/01/2015");
				
		assertEquals(FormulaReturnType.DATE, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() throws GLanguageException {
		FormulaTerminalDate formula =
				new FormulaTerminalDate(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE), "01/01/2015");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() throws GLanguageException {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageException {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		assertEquals("01/01/2015", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageException {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getDateValue()}
	 */
	@Test
	public void testGetDateValue() throws GLanguageException {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageException {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		assertEquals("01/01/2015", formula.asText());
	}
	
}
