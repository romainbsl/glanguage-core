package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaStringItem}
 * 
 * @author DUPIREFR
 */
public class FormulaStringItemTest extends BaseDatabaseTest {
	
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
	 * Tests {@link FormulaStringItem#isValid()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_ITEM),
				Arrays.asList(string, separator, index));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaStringItem#isValid()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_ITEM),
				Arrays.asList(string, separator, index));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getReturnType()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_ITEM),
				Arrays.asList(string, separator, index));
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaStringItem#getReturnType()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_ITEM),
				Arrays.asList(string, separator, index));
				
		assertNull(formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaStringItem#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringItem#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		assertEquals("value", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaStringItem#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringItem#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringItem#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
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
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringItem#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(separator.asText()).thenReturn("/");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(index.asText()).thenReturn("2");
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		assertEquals("stringItem(some_rule; /; 2)", formula.asText());
	}
	
}
