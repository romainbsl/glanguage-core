package be.groups.glanguage.core.entities.formula.implementations.terminal;

import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalDuration}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalDurationTest {
	
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
	 * Tests {@link FormulaTerminalDuration#getReturnType()} when no parameters
	 */
	@Test
	public void testGetReturnType() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);

		Assert.assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn("'P1Y2M3DT4H5M6.7S'").when(formula).getConstantValue();

		assertEquals("'P1Y2M3DT4H5M6.7S'", formula.getStringValue());
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn("'P1Y2M3DT4H5M6.7S'").when(formula).getConstantValue();

		assertEquals(Duration.parse("P430DT4H5M6.7S"), formula.getDurationValue());
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without time
	 */
	@Test
	public void testGetDurationValueWithoutTime() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn("'P1Y2M3D'").when(formula).getConstantValue();

		assertEquals(Duration.parse("P430D"), formula.getDurationValue());
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without year
	 */
	@Test
	public void testGetDurationValueWithoutYear() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn("'P2M3DT4H5M6.7S'").when(formula).getConstantValue();

		assertEquals(Duration.parse("P65DT4H5M6.7S"), formula.getDurationValue());
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without year and month
	 */
	@Test
	public void testGetDurationValueWithoutYearAndMonth() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn("'P3DT4H5M6.7S'").when(formula).getConstantValue();

		assertEquals(Duration.parse("P3DT4H5M6.7S"), formula.getDurationValue());
	}

	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without dates
	 */
	@Test
	public void testGetDurationValueWithoutDates() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn("'PT4H5M6.7S'").when(formula).getConstantValue();

		assertEquals(Duration.parse("PT4H5M6.7S"), formula.getDurationValue());
	}

}
