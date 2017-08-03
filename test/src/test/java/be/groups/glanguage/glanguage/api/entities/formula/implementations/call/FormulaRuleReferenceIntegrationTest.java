package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.GLanguageEvaluationExceptionTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaRuleReference}
 * 
 * @author DUPIREFR
 */
public class FormulaRuleReferenceIntegrationTest extends GLanguageEvaluationExceptionTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaRuleReference#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaRuleReference formula = new FormulaRuleReference();
		
		assertEquals(Integer.valueOf(FormulaType.Values.C_RULE_REFERENCE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaRuleReference#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaRuleReference formula = new FormulaRuleReference();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = spy(FormulaRuleReference.class);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getIntegerValue(null)).thenReturn(1);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getIntegerValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId, null);
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = spy(FormulaRuleReference.class);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getNumericValue(null)).thenReturn(1.5);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getNumericValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId, null);
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = spy(FormulaRuleReference.class);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getStringValue(null)).thenReturn("string");
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals("string", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getStringValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId, null);
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = spy(FormulaRuleReference.class);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getBooleanValue(null)).thenReturn(true);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals(Boolean.TRUE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getBooleanValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId, null);

		try {
			formula.getBooleanValue(null);
		} catch (GLanguageException e) {
			handleException(e);
		}
	}

	/**
	 * Tests {@link FormulaRuleReference#getDateValue()}
	 */
	@Test
	public void testGetDateValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = spy(FormulaRuleReference.class);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getDateValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValueWithoutRuleRef() throws GLanguageException {
		FormulaRuleReference formula = spy(FormulaRuleReference.class);
		doReturn(FormulaReturnType.DATE).when(formula).getReturnType(null);
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() throws GLanguageException {
		FormulaRuleReference formula = spy(FormulaRuleReference.class);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getDurationValue(null)).thenReturn(Duration.ofDays(2L));
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals(Duration.ofDays(2L), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getDurationValue()} without rule reference
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValueWithoutRuleRef() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId, null);
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaRuleReference#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		FormulaRuleReference formula = spy(FormulaRuleReference.class);

		String ruleId = "some_rule";
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getCode()).thenReturn(ruleId);
		doReturn(ruleVersion).when(formula).doGetReferencedRule(null);

		assertEquals("some_rule", formula.asText());
	}
	
}
