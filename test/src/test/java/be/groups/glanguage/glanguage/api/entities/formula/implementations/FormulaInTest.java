package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaIn}
 * 
 * @author DUPIREFR
 */
public class FormulaInTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaIn#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaIn formula = new FormulaIn();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_IN), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaIn#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaIn formula = new FormulaIn();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaIn#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue(null)).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaIn#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue(null)).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaIn#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue(null)).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaIn#getBooleanValue()} when parameter matches elements list
	 */
	@Test
	public void testGetBooleanValueMatching() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue(null)).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaIn#getBooleanValue()} when parameter doesn't match elements list
	 */
	@Test
	public void testGetBooleanValueNotMatching() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(4);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue(null)).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaIn#getBooleanValue()} when list is empty
	 */
	@Test
	public void testGetBooleanValueListEmpty() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(1);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList());
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaIn#getDateValue()} with string parameter
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue(null)).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaIn#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue(null)).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaIn#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.asText()).thenReturn("1");
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.asText()).thenReturn("1");
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.asText()).thenReturn("2");
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.asText()).thenReturn("3");
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		assertEquals("1 in (1, 2, 3)", formula.asText());
	}
	
}