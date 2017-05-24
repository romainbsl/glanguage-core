package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.GLanguageEvaluationExceptionTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

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
		FormulaApplicability formula = spy(FormulaApplicability.class);
		doReturn(FormulaReturnType.INTEGER).when(formula).getReturnType(null);

		formula.getIntegerValue(null);
	}

	/**
	 * Tests {@link FormulaApplicability#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		FormulaApplicability formula = spy(FormulaApplicability.class);
		doReturn(FormulaReturnType.NUMERIC).when(formula).getReturnType(null);

		formula.getNumericValue(null);
	}

	/**
	 * Tests {@link FormulaApplicability#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		FormulaApplicability formula = spy(FormulaApplicability.class);
		doReturn(FormulaReturnType.STRING).when(formula).getReturnType(null);

		formula.getStringValue(null);
	}

	/**
	 * Tests {@link FormulaApplicability#getBooleanValue()} when applicability condition is true
	 */
	@Test
	public void testGetBooleanValueCondTrue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(null, ruleId);
		
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
	 * Tests {@link FormulaApplicability#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		FormulaApplicability formula = spy(FormulaApplicability.class);
		doReturn(FormulaReturnType.DATE).when(formula).getReturnType(null);

		formula.getDateValue(null);
	}

	/**
	 * Tests {@link FormulaApplicability#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		FormulaApplicability formula = spy(FormulaApplicability.class);
		doReturn(FormulaReturnType.DURATION).when(formula).getReturnType(null);

		formula.getDurationValue(null);
	}

	/**
	 * Tests {@link FormulaApplicability#asText()}
	 */
	@Test
	@Category(BaseDatabaseTest.class)
	public void testAsText() throws GLanguageException {
		FormulaApplicability formula = spy(FormulaApplicability.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.C_APPLICABILITY)).when(formula).getDescription();
		doReturn("some_rule").when(formula).getConstantValue();
		
		assertEquals("some_rule.applicable", formula.asText());
	}
	
}
