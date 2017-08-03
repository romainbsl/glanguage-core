package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaAnd}
 * 
 * @author DUPIREFR
 */
public class FormulaAndIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaAnd#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaAnd formula = new FormulaAnd();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_AND), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaAnd formula = new FormulaAnd();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaAnd#isValid(Evaluator)} when both operands are boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothBoolean() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();
		
		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaAnd#isValid(Evaluator)} when first operand is not boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNotBoolean() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaAnd#isValid(Evaluator)} when second operand is not boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNotBoolean() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaAnd#isValid(Evaluator)} when both operands are not boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNotBoolean() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaAnd#getReturnType()} when both operands are boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothBoolean() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaAnd#getReturnType()} when first operand is not boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNotBoolean() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaAnd#getReturnType()} when second operand is not boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeSecondNotBoolean() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaAnd#getReturnType()} when both operands are not boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNotBoolean() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaAnd#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when parameters exist and both true
	 */
	@Test
	public void testGetBooleanValueParametersExistBothTrue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(true);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when parameters exist and left is false
	 */
	@Test
	public void testGetBooleanValueParametersExistLeftFalse() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(true);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when parameters exist and right is false
	 */
	@Test
	public void testGetBooleanValueParametersExistRightFalse() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when parameters exist and both false
	 */
	@Test
	public void testGetBooleanValueParametersExistBothFalse() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when left doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueLeftNotExists() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(null);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(true);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when left true and right doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueLeftTrueRightNotExists() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(null);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when left false and right doesn't exist
	 */
	@Test
	public void testGetBooleanValueLeftFalseRightNotExists() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(null);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when both parameter don't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueParametersNotExist() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(null);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(null);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaAnd formula = new FormulaAnd();
		
		assertEquals("and", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaAnd#asText()}
	 */
	@Test
	@Category(DatabaseTestCategory.class)
	public void testAsText() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.asText()).thenReturn("some_rule2");

		FormulaAnd formula = spy(FormulaAnd.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals("some_rule1 and some_rule2", formula.asText());
	}
	
}
