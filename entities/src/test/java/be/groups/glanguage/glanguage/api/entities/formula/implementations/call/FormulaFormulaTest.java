package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
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
 * Test class for {@link FormulaFormula}
 * 
 * @author DUPIREFR
 */
public class FormulaFormulaTest {
	
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
		
		FormulaFormula formula = spy(FormulaFormula.class);

		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getIntegerValue(null)).thenReturn(1);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0L);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}

	/**
	 * Tests {@link FormulaFormula#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = spy(FormulaFormula.class);

		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getNumericValue(null)).thenReturn(1.5);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0L);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}

	/**
	 * Tests {@link FormulaFormula#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = spy(FormulaFormula.class);

		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getStringValue(null)).thenReturn("string");

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0L);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);
		
		assertEquals("string", formula.getStringValue(null));
	}

	/**
	 * Tests {@link FormulaFormula#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = spy(FormulaFormula.class);

		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getBooleanValue(null)).thenReturn(true);

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0L);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals(Boolean.TRUE, formula.getBooleanValue(null));
	}

	/**
	 * Tests {@link FormulaFormula#getDateValue()}
	 */
	@Test
	public void testGetDateValue() throws GLanguageException {
		String ruleId = "some_rule";
		
		FormulaFormula formula = spy(FormulaFormula.class);

		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0L);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);

		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue(null));
	}

	/**
	 * Tests {@link FormulaFormula#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() throws GLanguageException {
		FormulaFormula formula = spy(FormulaFormula.class);

		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getDurationValue(null)).thenReturn(Duration.ofDays(2L));

		RuleIdentity ruleIdentity = new RuleIdentity();
		ruleIdentity.setId(0L);
		RuleDefinition ruleDefinition = new RuleDefinition();
		ruleDefinition.setRuleIdentity(ruleIdentity);

		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		when(ruleVersion.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(ruleVersion.getRuleDefinition()).thenReturn(ruleDefinition);
		doReturn(ruleVersion).when(formula).getReferencedRule(null);
		
		assertEquals(Duration.ofDays(2L), formula.getDurationValue(null));
	}

	/**
	 * Tests {@link FormulaFormula#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {

		FormulaFormula formula = spy(FormulaFormula.class);

		String ruleId = "some_rule";
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getCode()).thenReturn(ruleId);
		doReturn(ruleVersion).when(formula).doGetReferencedRule(null);

		assertEquals("some_rule.formula", formula.asText());
	}
	
}
