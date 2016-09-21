package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

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
	public void testGetIntegerValue() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.INTEGER);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getIntegerValue()).thenReturn(1);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaFormula#getIntegerValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetIntegerValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaFormula#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.NUMERIC);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getNumericValue()).thenReturn(1.5);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Double.valueOf(1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaFormula#getNumericValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetNumericValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaFormula#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.STRING);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getStringValue()).thenReturn("string");
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals("string", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaFormula#getStringValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetStringValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaFormula#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.BOOLEAN);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getBooleanValue()).thenReturn(true);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaFormula#getBooleanValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetBooleanValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormula#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.DATE);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaFormula#getDateValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetDateValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormula#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.DURATION);
		formula.setDescription(description);
		
		AbstractFormula ruleVersionFormula = mock(AbstractFormula.class);
		when(ruleVersionFormula.getDurationValue()).thenReturn(Duration.ofDays(2L));
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getFormula()).thenReturn(ruleVersionFormula);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Duration.ofDays(2L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaFormula#getDurationValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetDurationValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaFormula#asText()}
	 */
	@Test
	public void testAsText() {
		String ruleId = "some_rule";
		
		FormulaFormula formula = new FormulaFormula(null, ruleId);
		
		assertEquals("some_rule.formula", formula.asText());
	}
	
}
