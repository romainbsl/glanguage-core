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

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaApplicability}
 * 
 * @author DUPIREFR
 */
public class FormulaApplicabilityTest extends GLanguageEvaluationExceptionTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaApplicability#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaApplicability formula = new FormulaApplicability();
		
		assertEquals(Integer.valueOf(FormulaType.Values.C_APPLICABILITY), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaApplicability#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaApplicability formula = new FormulaApplicability();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaApplicability#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.INTEGER);
		formula.setDescription(description);
		
		AbstractFormula applicabilityCondition = mock(AbstractFormula.class);
		when(applicabilityCondition.getBooleanValue(null)).thenReturn(true);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getApplicabilityCondition()).thenReturn(applicabilityCondition);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getIntegerValue()} without reference rule
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValueWithoutReference() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.NUMERIC);
		formula.setDescription(description);
		
		AbstractFormula applicabilityCondition = mock(AbstractFormula.class);
		when(applicabilityCondition.getBooleanValue(null)).thenReturn(true);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getApplicabilityCondition()).thenReturn(applicabilityCondition);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getNumericValue()} without reference rule
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValueWithoutReference() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.STRING);
		formula.setDescription(description);
		
		AbstractFormula applicabilityCondition = mock(AbstractFormula.class);
		when(applicabilityCondition.getBooleanValue(null)).thenReturn(true);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getApplicabilityCondition()).thenReturn(applicabilityCondition);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getStringValue()} without reference rule
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValueWithoutReference() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = mock(FormulaApplicability.class);
		when(formula.getConstantValue()).thenReturn(ruleId);
		when(formula.getStringValue()).thenCallRealMethod();
		when(formula.getStringValue(null)).thenCallRealMethod();
		when(formula.doGetStringValue(null)).thenCallRealMethod();
		when(formula.getReturnType(null)).thenCallRealMethod();
		when(formula.getReferencedRule(null)).thenCallRealMethod();
		when(formula.doGetReferencedRule(null)).thenCallRealMethod();

		String value = formula.getStringValue(null);
		System.out.print(value);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getBooleanValue()} when applicability condition is true
	 */
	@Test
	public void testGetBooleanValueCondTrue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.BOOLEAN);
		formula.setDescription(description);
		
		AbstractFormula applicabilityCondition = mock(AbstractFormula.class);
		when(applicabilityCondition.getBooleanValue(null)).thenReturn(true);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getApplicabilityCondition()).thenReturn(applicabilityCondition);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaApplicability#getBooleanValue()} when applicability condition is false
	 */
	@Test
	public void testGetBooleanValueCondFalse() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.BOOLEAN);
		formula.setDescription(description);
		
		AbstractFormula applicabilityCondition = mock(AbstractFormula.class);
		when(applicabilityCondition.getBooleanValue(null)).thenReturn(false);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getApplicabilityCondition()).thenReturn(applicabilityCondition);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaApplicability#getBooleanValue()} without reference rule
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValueWithoutReference() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);

		try {
			formula.getBooleanValue(null);
		} catch (GLanguageException e) {
			handleException(e);
		}
	}
	
	/**
	 * Tests {@link FormulaApplicability#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.DATE);
		formula.setDescription(description);
		
		AbstractFormula applicabilityCondition = mock(AbstractFormula.class);
		when(applicabilityCondition.getBooleanValue(null)).thenReturn(true);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getApplicabilityCondition()).thenReturn(applicabilityCondition);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getDateValue()} without reference rule
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValueWithoutReference() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.DURATION);
		formula.setDescription(description);
		
		AbstractFormula applicabilityCondition = mock(AbstractFormula.class);
		when(applicabilityCondition.getBooleanValue(null)).thenReturn(true);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getApplicabilityCondition()).thenReturn(applicabilityCondition);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		formula.setReferencedRule(ruleVersion);
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#getDurationValue()} without reference rule
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValueWithoutReference() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaApplicability#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
		assertEquals("some_rule.applicable", formula.asText());
	}
	
}
