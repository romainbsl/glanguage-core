package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

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
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue()).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue()).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaIn#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue()).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue()).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaIn#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue()).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue()).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaIn#getBooleanValue()} when parameter matches elements list
	 */
	@Test
	public void testGetBooleanValueMatching() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue()).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue()).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaIn#getBooleanValue()} when parameter doesn't match elements list
	 */
	@Test
	public void testGetBooleanValueNotMatching() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(4);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue()).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue()).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaIn#getBooleanValue()} when list is empty
	 */
	@Test
	public void testGetBooleanValueListEmpty() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList());
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaIn#getDateValue()} with string parameter
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue()).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue()).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaIn#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element1 = mock(AbstractFormula.class);
		when(element1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula element2 = mock(AbstractFormula.class);
		when(element2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element2.getIntegerValue()).thenReturn(2);
		
		AbstractFormula element3 = mock(AbstractFormula.class);
		when(element3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(element3.getIntegerValue()).thenReturn(3);
		
		FormulaIn formula = new FormulaIn(null, parameter, Arrays.asList(element1, element2, element3));
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaIn#asText()}
	 */
	@Test
	public void testAsText() {
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
