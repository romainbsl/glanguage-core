package be.groups.glanguage.core.entities.formula.implementations.string;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

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
	 * Tests {@link FormulaStringItem#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaStringItem formula = new FormulaStringItem();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_STRING_ITEM), formula.getDiscriminatorValue());
	}
	
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
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(2);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringItem#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(2);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index 2
	 */
	@Test
	public void testGetStringValueIndex2() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(2);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		assertEquals("special", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index 3
	 */
	@Test
	public void testGetStringValueIndex3() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(3);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		assertEquals("value", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index out of bounds
	 */
	@Test
	public void testGetStringValueIndexOutOfBounds() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(4);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		assertEquals("", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with slash separator
	 */
	@Test
	public void testGetStringValueSlashSeparator() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special/value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn("/");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(2);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		assertEquals("value", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaStringItem#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(2);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringItem#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(2);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringItem#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue(null)).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue(null)).thenReturn(2);

		FormulaStringItem formula = spy(FormulaStringItem.class);
		doReturn(Arrays.asList(string, separator, index)).when(formula).getParameters();

		formula.getDurationValue(null);
	}

}
