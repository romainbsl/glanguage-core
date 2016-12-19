package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
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
 * Test class for {@link FormulaTerminalDuration}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalDurationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalDuration#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_DURATION), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#isValid()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNoParameters() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(
				FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION), "P1Y2M3DT4H5M6.7S");
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#isValid()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidParameters() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(
				FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION), "P1Y2M3DT4H5M6.7S");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getReturnType()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNoParameters() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(
				FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION), "P1Y2M3DT4H5M6.7S");
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getReturnType()} when some parameters are present
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeParameters() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(
				FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION), "P1Y2M3DT4H5M6.7S");
				
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		formula.setParameters(Arrays.asList(parameter));
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "P1Y2M3DT4H5M6.7S");
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "P1Y2M3DT4H5M6.7S");
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "P1Y2M3DT4H5M6.7S");
		assertEquals("P1Y2M3DT4H5M6.7S", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "P1Y2M3DT4H5M6.7S");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "P1Y2M3DT4H5M6.7S");
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "'P1Y2M3DT4H5M6.7S'");
		assertEquals(Duration.parse("P430DT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without time
	 */
	@Test
	public void testGetDurationValueWithoutTime() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "'P1Y2M3D'");
		assertEquals(Duration.parse("P430D"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without year
	 */
	@Test
	public void testGetDurationValueWithoutYear() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "'P2M3DT4H5M6.7S'");
		assertEquals(Duration.parse("P65DT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without year and month
	 */
	@Test
	public void testGetDurationValueWithoutYearAndMonth() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "'P3DT4H5M6.7S'");
		assertEquals(Duration.parse("P3DT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without dates
	 */
	@Test
	public void testGetDurationValueWithoutDates() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "'PT4H5M6.7S'");
		assertEquals(Duration.parse("PT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration(null, "string");
		assertEquals("string", formula.asText());
	}
	
}
