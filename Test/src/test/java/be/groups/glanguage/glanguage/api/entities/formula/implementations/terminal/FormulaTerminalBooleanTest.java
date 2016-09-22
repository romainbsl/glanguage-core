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
	public void testIsValidNoParameters() {
		FormulaTerminalBoolean formula =
				new FormulaTerminalBoolean(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN), true);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() {
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
	public void testGetReturnTypeNoParameters() {
		FormulaTerminalBoolean formula =
				new FormulaTerminalBoolean(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN), true);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() {
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
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		assertEquals("true", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(null, true);
		assertEquals("true", formula.asText());
	}
	
}
