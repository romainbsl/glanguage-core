package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

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
	public void testIsValidMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_ITEM),
				Arrays.asList(string, separator, index));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaStringItem#isValid()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_ITEM),
				Arrays.asList(string, separator, index));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getReturnType()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_ITEM),
				Arrays.asList(string, separator, index));
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getReturnType()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_ITEM),
				Arrays.asList(string, separator, index));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index 2
	 */
	@Test
	public void testGetStringValueIndex2() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		assertEquals("special", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index 3
	 */
	@Test
	public void testGetStringValueIndex3() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(3);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		assertEquals("value", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with index out of bounds
	 */
	@Test
	public void testGetStringValueIndexOutOfBounds() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(4);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		assertEquals("", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getStringValue()} with slash separator
	 */
	@Test
	public void testGetStringValueSlashSeparator() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special/value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn("/");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		assertEquals("value", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue()).thenReturn("a special value");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.getStringValue()).thenReturn(" ");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.getIntegerValue()).thenReturn(2);
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaStringItem#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule");
		
		AbstractFormula separator = mock(AbstractFormula.class);
		when(separator.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(separator.asText()).thenReturn("/");
		
		AbstractFormula index = mock(AbstractFormula.class);
		when(index.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(index.asText()).thenReturn("2");
		
		FormulaStringItem formula = new FormulaStringItem(null, Arrays.asList(string, separator, index));
		
		assertEquals("stringItem(some_rule; /; 2)", formula.asText());
	}
	
}
