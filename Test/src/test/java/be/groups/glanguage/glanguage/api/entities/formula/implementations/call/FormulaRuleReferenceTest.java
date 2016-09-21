package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

/**
 * Test class for {@link FormulaRuleReference}
 * 
 * @author DUPIREFR
 */
public class FormulaRuleReferenceTest {
	
	/*
	 * Tests
	 */
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
	public void testGetIntegerValue() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.INTEGER);
		formula.setDescription(description);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getIntegerValue()).thenReturn(1);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getIntegerValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetIntegerValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.NUMERIC);
		formula.setDescription(description);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getNumericValue()).thenReturn(1.5);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Double.valueOf(1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getNumericValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetNumericValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.STRING);
		formula.setDescription(description);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getStringValue()).thenReturn("string");
		formula.setReferencedRule(ruleVersion);
		
		assertEquals("string", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getStringValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetStringValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.BOOLEAN);
		formula.setDescription(description);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getBooleanValue()).thenReturn(true);
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getBooleanValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetBooleanValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.DATE);
		formula.setDescription(description);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getDateValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetDateValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		FormulaDescription description = mock(FormulaDescription.class);
		when(description.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.DURATION);
		formula.setDescription(description);
		
		RuleVersion ruleVersion = mock(RuleVersion.class);
		when(ruleVersion.getDurationValue()).thenReturn(Duration.ofDays(2L));
		formula.setReferencedRule(ruleVersion);
		
		assertEquals(Duration.ofDays(2L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaRuleReference#getDurationValue()} without rule reference
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetDurationValueWithoutRuleRef() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaRuleReference#asText()}
	 */
	@Test
	public void testAsText() {
		String ruleId = "some_rule";
		
		FormulaRuleReference formula = new FormulaRuleReference(null, ruleId);
		
		assertEquals("some_rule", formula.asText());
	}
	
}
