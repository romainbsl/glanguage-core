package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaSubString}
 * 
 * @author DUPIREFR
 */
public class FormulaSubStringTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaSubString#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaSubString formula = new FormulaSubString();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_SUBSTRING), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaSubString#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaSubString formula = new FormulaSubString();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaSubString#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		assertEquals("special", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with negative begin index
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueNegativeIndex() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(-1);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with impossible index range (end index <
	 * begin index)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueImpossibleRange() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(2);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with end index too big
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueIndexTooBig() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(16);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);

		FormulaSubString formula = spy(FormulaSubString.class);
		doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

		formula.getDurationValue(null);
	}

}
