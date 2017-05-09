package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.GLanguageEvaluationExceptionTest;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaFormula}
 * 
 * @author DUPIREFR
 */
public class FormulaFormulaTest extends GLanguageEvaluationExceptionTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormula#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaFormula formula = new FormulaFormula();
		
		assertEquals(Integer.valueOf(FormulaType.Values.C_FORMULA), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaFormula#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaFormula formula = new FormulaFormula();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormula#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.INTEGER);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getIntegerValue(null)).thenReturn(1);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormula#getIntegerValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormula#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.NUMERIC);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getNumericValue(null)).thenReturn(1.5);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormula#getNumericValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormula#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.STRING);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getStringValue(null)).thenReturn("string");

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals("string", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormula#getStringValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormula#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.BOOLEAN);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getBooleanValue(null)).thenReturn(true);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormula#getBooleanValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);

		try {
			formula.getBooleanValue(null);
		} catch (GLanguageException e) {
			handleException(e);
		}
	}
	
	/**
	 * Tests {@link FormulaFormula#getDateValue()}
	 */
	@Test
	public void testGetDateValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.DATE);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormula#getDateValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormula#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.DURATION);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getDurationValue(null)).thenReturn(Duration.ofDays(2L));

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Duration.ofDays(2L), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaFormula#getDurationValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaFormula#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		assertEquals("some_rule.formula", formula.asText());
	}
	
}
