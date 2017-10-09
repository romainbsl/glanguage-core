package be.groups.glanguage.core.entities.formula.implementations.terminal;

import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalInteger}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalIntegerTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalInteger#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger();

		Assert.assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_INTEGER), formula.getDiscriminatorValue());
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
	 * Tests {@link FormulaTerminalInteger#getReturnType()} when no parameters
	 */
	@Test
	public void testGetReturnType() throws GLanguageException {
		FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);

		Assert.assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
		doReturn("1").when(formula).getConstantValue();

		Assert.assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
		doReturn("1").when(formula).getConstantValue();

		Assert.assertEquals(Double.valueOf(1), formula.getNumericValue());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
		doReturn("1").when(formula).getConstantValue();

		Assert.assertEquals("1", formula.getStringValue());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
		formula.getDurationValue();
	}

}
