package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaFormatDate}
 * 
 * @author DUPIREFR
 */
public class FormulaFormatDateTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormatDate#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaFormatDate formula = new FormulaFormatDate();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_FORMAT_DATE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaFormatDate formula = new FormulaFormatDate();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#isValid()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatDate formula = new FormulaFormatDate(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE),
				Arrays.asList(date, format));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#isValid()} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatDate formula = new FormulaFormatDate(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE),
				Arrays.asList(date, format));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getReturnType()} when parameters match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaFormatDate formula = new FormulaFormatDate(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE),
				Arrays.asList(date, format));
				
		assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaFormatDate#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");
		
		FormulaFormatDate formula = new FormulaFormatDate(null, Arrays.asList(date, format));
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");
		
		FormulaFormatDate formula = new FormulaFormatDate(null, Arrays.asList(date, format));
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");
		
		FormulaFormatDate formula = new FormulaFormatDate(null, Arrays.asList(date, format));
		
		assertEquals("2015-01-10", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getStringValue()} with another format
	 */
	@Test
	public void testGetStringValueAnotherFormat() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(format.getStringValue(null)).thenReturn("dd/MM/yyyy");
		
		FormulaFormatDate formula = new FormulaFormatDate(null, Arrays.asList(date, format));
		
		assertEquals("10/01/2015", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");
		
		FormulaFormatDate formula = new FormulaFormatDate(null, Arrays.asList(date, format));
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");
		
		FormulaFormatDate formula = new FormulaFormatDate(null, Arrays.asList(date, format));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");
		
		FormulaFormatDate formula = new FormulaFormatDate(null, Arrays.asList(date, format));
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormatDate#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaFormatDate formula = new FormulaFormatDate();
		
		assertEquals("formatDate", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		AbstractFormula date = mock(AbstractFormula.class);
		when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(date.asText()).thenReturn("10/01/2015");
		
		AbstractFormula format = mock(AbstractFormula.class);
		when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(format.asText()).thenReturn("yyyy-MM-dd");
		
		FormulaFormatDate formula = new FormulaFormatDate(null, Arrays.asList(date, format));
		
		assertEquals("formatDate(10/01/2015; yyyy-MM-dd)", formula.asText());
	}
	
}
