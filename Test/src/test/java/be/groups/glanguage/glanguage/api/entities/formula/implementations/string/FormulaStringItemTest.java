package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaStringItem}
 * 
 * @author DUPIREFR
 */
public class FormulaStringItemTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaStringItem#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaStringItem formula = new FormulaStringItem();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index 2
	 */
	@Test
	public void testGetStringValueIndex2() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		assertEquals("special", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index 3
	 */
	@Test
	public void testGetStringValueIndex3() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(3);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		assertEquals("value", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index out of bounds
	 */
	@Test
	public void testGetStringValueIndexOutOfBounds() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(4);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		assertEquals("", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with slash separator
	 */
	@Test
	public void testGetStringValueSlashSeparator() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special/value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn("/");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		assertEquals("value", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#asText()}
	 */
	@Test
	public void testAsText() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule");
		parameters.add(string);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.asText()).thenReturn("/");
		parameters.add(separator);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.asText()).thenReturn("2");
		parameters.add(index);
		
		FormulaStringItem formula = new FormulaStringItem(null, parameters);
		
		assertEquals("stringItem(some_rule; /; 2)", formula.asText());
	}
	
}
