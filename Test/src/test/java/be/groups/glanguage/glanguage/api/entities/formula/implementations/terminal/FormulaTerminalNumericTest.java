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
	public void testIsValidNoParameters() {
		FormulaTerminalNumeric formula =
				new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "1.5");
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() {
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
	public void testGetReturnTypeNoParameters() {
		FormulaTerminalNumeric formula =
				new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "1.5");
				
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() {
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
	public void testGetIntegerValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		assertEquals(Double.valueOf(1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		assertEquals("1.5", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric(null, "1.5");
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
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
