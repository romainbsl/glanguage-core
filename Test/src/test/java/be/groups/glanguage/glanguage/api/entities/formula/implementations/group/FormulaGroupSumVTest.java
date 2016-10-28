package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumeric;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItem;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaGroupSumV}
 * 
 * @author micmax
 */
public class FormulaGroupSumVTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaFormatDate#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaGroupSumV formula = new FormulaGroupSumV();
		
		assertEquals(Integer.valueOf(FormulaType.Values.G_SUMV), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaGroupSumV formula = new FormulaGroupSumV();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#isValid()} when there is no group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNoGroupRule() {
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#isValid()} when there is a rule with wrong type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidGroupRuleWithWrongTypes() {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem = new RuleGroupItem(); 
		ruleGroupItem.setGroupRule(groupRule);
		ruleGroupItem.setReferencedRule(rv1);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(new Comparator<RuleGroupItem>() {
			@Override
			public int compare(RuleGroupItem o1, RuleGroupItem o2) {
				return o1.getSequenceNumber() - o2.getSequenceNumber();
			}});
		ruleGroup.add(ruleGroupItem);
		groupRule.setGroupItems(ruleGroup);
		
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		formula.setGroupRule(groupRule);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#isValid()} when there are rules with right type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidGroupRuleWithRightTypes() {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		RuleVersion rv2 = mock(RuleVersion.class);
		when(rv2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(new Comparator<RuleGroupItem>() {
			@Override
			public int compare(RuleGroupItem o1, RuleGroupItem o2) {
				return o1.getSequenceNumber() - o2.getSequenceNumber();
			}});
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		groupRule.setGroupItems(ruleGroup);
		
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		formula.setGroupRule(groupRule);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#isValid()} when there is no group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnNoGroupRule() {
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
				
		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaFormatDate#isValid()} when there are rules with integer and numeric type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidGroupRuleIntegerAndNumeric() {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		RuleVersion rv2 = mock(RuleVersion.class);
		when(rv2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		ruleGroupItem1.setSequenceNumber(1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		ruleGroupItem1.setSequenceNumber(2);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(new Comparator<RuleGroupItem>() {
			@Override
			public int compare(RuleGroupItem o1, RuleGroupItem o2) {
				return o1.getSequenceNumber() - o2.getSequenceNumber();
			}});
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		groupRule.setGroupItems(ruleGroup);
		
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		formula.setGroupRule(groupRule);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaFormatDate#isValid()} when there are rules with integer type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidGroupRuleAllInteger() {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		RuleVersion rv2 = mock(RuleVersion.class);
		when(rv2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(new Comparator<RuleGroupItem>() {
			@Override
			public int compare(RuleGroupItem o1, RuleGroupItem o2) {
				return o1.getSequenceNumber() - o2.getSequenceNumber();
			}});
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		groupRule.setGroupItems(ruleGroup);
		
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		formula.setGroupRule(groupRule);
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaFormatDate#isValid()} when there are rules with numeric type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidGroupRuleAllNumeric() {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		RuleVersion rv2 = mock(RuleVersion.class);
		when(rv2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(new Comparator<RuleGroupItem>() {
			@Override
			public int compare(RuleGroupItem o1, RuleGroupItem o2) {
				return o1.getSequenceNumber() - o2.getSequenceNumber();
			}});
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		groupRule.setGroupItems(ruleGroup);
		
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		formula.setGroupRule(groupRule);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaFormatDate#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		FormulaTerminalNumeric formula1 = new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "100.4");
		FormulaTerminalNumeric formula2 = new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "100.3");
		
		RuleVersion rv1 = new RuleVersion();
		rv1.setFormula(formula1);
		rv1.setRoundingType(RoundingType.ARITHMETIC);
		rv1.setRoundingPrecision(1.0);
		RuleVersion rv2 = new RuleVersion();
		rv2.setFormula(formula1);
		rv2.setRoundingType(RoundingType.ARITHMETIC);
		rv2.setRoundingPrecision(1.0);
		RuleVersion rv3 = new RuleVersion();
		rv3.setFormula(formula1);
		rv3.setRoundingType(RoundingType.ARITHMETIC);
		rv3.setRoundingPrecision(1.0);
		RuleVersion rv4 = new RuleVersion();
		rv4.setFormula(formula1);
		rv4.setRoundingType(RoundingType.ARITHMETIC);
		rv4.setRoundingPrecision(1.0);
		RuleVersion rv5 = new RuleVersion();
		rv5.setFormula(formula2);
		rv5.setRoundingType(RoundingType.ARITHMETIC);
		rv5.setRoundingPrecision(1.0);
		
		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		ruleGroupItem1.setSequenceNumber(1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		ruleGroupItem2.setSequenceNumber(2);
		RuleGroupItem ruleGroupItem3 = new RuleGroupItem(); 
		ruleGroupItem3.setGroupRule(groupRule);
		ruleGroupItem3.setReferencedRule(rv3);
		ruleGroupItem3.setSequenceNumber(3);
		RuleGroupItem ruleGroupItem4 = new RuleGroupItem(); 
		ruleGroupItem4.setGroupRule(groupRule);
		ruleGroupItem4.setReferencedRule(rv4);
		ruleGroupItem4.setSequenceNumber(4);
		RuleGroupItem ruleGroupItem5 = new RuleGroupItem(); 
		ruleGroupItem5.setGroupRule(groupRule);
		ruleGroupItem5.setReferencedRule(rv5);
		ruleGroupItem5.setSequenceNumber(5);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(new Comparator<RuleGroupItem>() {
			@Override
			public int compare(RuleGroupItem o1, RuleGroupItem o2) {
				return o1.getSequenceNumber() - o2.getSequenceNumber();
			}});
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		ruleGroup.add(ruleGroupItem3);
		ruleGroup.add(ruleGroupItem4);
		ruleGroup.add(ruleGroupItem5);
		groupRule.setGroupItems(ruleGroup);
		
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		formula.setGroupRule(groupRule);
		
		assertEquals(new Integer(500), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		FormulaTerminalNumeric formula1 = new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "100.4");
		FormulaTerminalNumeric formula2 = new FormulaTerminalNumeric(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "100.3");
		
		RuleVersion rv1 = new RuleVersion();
		rv1.setFormula(formula1);
		rv1.setRoundingType(RoundingType.ARITHMETIC);
		rv1.setRoundingPrecision(1.0);
		RuleVersion rv2 = new RuleVersion();
		rv2.setFormula(formula1);
		rv2.setRoundingType(RoundingType.ARITHMETIC);
		rv2.setRoundingPrecision(1.0);
		RuleVersion rv3 = new RuleVersion();
		rv3.setFormula(formula1);
		rv3.setRoundingType(RoundingType.ARITHMETIC);
		rv3.setRoundingPrecision(1.0);
		RuleVersion rv4 = new RuleVersion();
		rv4.setFormula(formula1);
		rv4.setRoundingType(RoundingType.ARITHMETIC);
		rv4.setRoundingPrecision(1.0);
		RuleVersion rv5 = new RuleVersion();
		rv5.setFormula(formula2);
		rv5.setRoundingType(RoundingType.ARITHMETIC);
		rv5.setRoundingPrecision(1.0);
		
		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		ruleGroupItem1.setSequenceNumber(1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		ruleGroupItem2.setSequenceNumber(2);
		RuleGroupItem ruleGroupItem3 = new RuleGroupItem(); 
		ruleGroupItem3.setGroupRule(groupRule);
		ruleGroupItem3.setReferencedRule(rv3);
		ruleGroupItem3.setSequenceNumber(3);
		RuleGroupItem ruleGroupItem4 = new RuleGroupItem(); 
		ruleGroupItem4.setGroupRule(groupRule);
		ruleGroupItem4.setReferencedRule(rv4);
		ruleGroupItem4.setSequenceNumber(4);
		RuleGroupItem ruleGroupItem5 = new RuleGroupItem(); 
		ruleGroupItem5.setGroupRule(groupRule);
		ruleGroupItem5.setReferencedRule(rv5);
		ruleGroupItem5.setSequenceNumber(5);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(new Comparator<RuleGroupItem>() {
			@Override
			public int compare(RuleGroupItem o1, RuleGroupItem o2) {
				return o1.getSequenceNumber() - o2.getSequenceNumber();
			}});
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		ruleGroup.add(ruleGroupItem3);
		ruleGroup.add(ruleGroupItem4);
		ruleGroup.add(ruleGroupItem5);
		groupRule.setGroupItems(ruleGroup);
		
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		formula.setGroupRule(groupRule);
		
		assertEquals(new Double(502.0), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {		
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		
		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaFormatDate#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaFormatDate#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaFormatDate#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		
		assertEquals("sumv", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaFormatDate#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaGroupSumV formula = new FormulaGroupSumV(FormulaDescriptionFactory.getDescription(FormulaType.G_SUMV), "1");
		
		assertEquals("sumv(1)", formula.asText());
	}
	
}
