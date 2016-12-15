package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaSubString}
 * 
 * @author DUPIREFR
 */
public class FormulaSubStringTest extends BaseDatabaseTest {
	
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
	 * Tests {@link FormulaStringItem#isValid()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING),
				Arrays.asList(string, beginIndex, endIndex));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaStringItem#isValid()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING),
				Arrays.asList(string, beginIndex, endIndex));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaStringItem#getReturnType()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING),
				Arrays.asList(string, beginIndex, endIndex));
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaStringItem#getReturnType()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaStringItem formula = new FormulaStringItem(FormulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING),
				Arrays.asList(string, beginIndex, endIndex));
				
		assertNull(formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaSubString#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		assertEquals("special", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with negative begin index
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueNegativeIndex() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(-1);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with impossible index range (end index <
	 * begin index)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueImpossibleRange() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(2);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getStringValue()} with end index too big
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetStringValueIndexTooBig() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(16);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a special value");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.getIntegerValue(null)).thenReturn(9);
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaSubString#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule");
		
		AbstractFormula beginIndex = mock(AbstractFormula.class);
		when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(beginIndex.asText()).thenReturn("3");
		
		AbstractFormula endIndex = mock(AbstractFormula.class);
		when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(endIndex.asText()).thenReturn("7");
		
		FormulaSubString formula = new FormulaSubString(null, Arrays.asList(string, beginIndex, endIndex));
		
		assertEquals("subString(some_rule; 3; 7)", formula.asText());
	}
	
}
