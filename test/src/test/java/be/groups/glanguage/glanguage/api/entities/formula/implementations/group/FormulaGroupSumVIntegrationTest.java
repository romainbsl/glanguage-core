package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumeric;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItem;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaGroupSumV}
 * 
 * @author micmax
 */
public class FormulaGroupSumVIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaGroupSumV#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaGroupSumV formula = new FormulaGroupSumV();
		
		assertEquals(Integer.valueOf(FormulaType.Values.G_SUMV), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaGroupSumV formula = new FormulaGroupSumV();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there is no group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNoGroupRule() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there is a rule with wrong type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidGroupRuleWithWrongTypes() throws GLanguageException {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

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

		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();
		doReturn(groupRule).when(formula).getGroupRule();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there are rules with right type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidGroupRuleWithRightTypes() throws GLanguageException {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		RuleVersion rv2 = mock(RuleVersion.class);
		when(rv2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(Comparator.comparingInt(RuleGroupItem::getSequenceNumber));
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		groupRule.setGroupItems(ruleGroup);

		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();
		doReturn(groupRule).when(formula).getGroupRule();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there is no group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNoGroupRule() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there are rules with integer and numeric type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeGroupRuleIntegerAndNumeric() throws GLanguageException {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		RuleVersion rv2 = mock(RuleVersion.class);
		when(rv2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		ruleGroupItem1.setSequenceNumber(1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		ruleGroupItem1.setSequenceNumber(2);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(Comparator.comparingInt(RuleGroupItem::getSequenceNumber));
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		groupRule.setGroupItems(ruleGroup);

		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();
		doReturn(groupRule).when(formula).getGroupRule();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there are rules with integer type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeGroupRuleAllInteger() throws GLanguageException {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		RuleVersion rv2 = mock(RuleVersion.class);
		when(rv2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(Comparator.comparingInt(RuleGroupItem::getSequenceNumber));
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		groupRule.setGroupItems(ruleGroup);
		
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();
		doReturn(groupRule).when(formula).getGroupRule();
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there are rules with numeric type within the group rule
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeGroupRuleAllNumeric() throws GLanguageException {
		RuleVersion rv1 = mock(RuleVersion.class);
		when(rv1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		RuleVersion rv2 = mock(RuleVersion.class);
		when(rv2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		RuleVersion groupRule = new RuleVersion();
		RuleGroupItem ruleGroupItem1 = new RuleGroupItem(); 
		ruleGroupItem1.setGroupRule(groupRule);
		ruleGroupItem1.setReferencedRule(rv1);
		RuleGroupItem ruleGroupItem2 = new RuleGroupItem(); 
		ruleGroupItem2.setGroupRule(groupRule);
		ruleGroupItem2.setReferencedRule(rv2);
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(Comparator.comparingInt(RuleGroupItem::getSequenceNumber));
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		groupRule.setGroupItems(ruleGroup);

		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();
		doReturn(groupRule).when(formula).getGroupRule();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaGroupSumV#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
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
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(Comparator.comparingInt(RuleGroupItem::getSequenceNumber));
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		ruleGroup.add(ruleGroupItem3);
		ruleGroup.add(ruleGroupItem4);
		ruleGroup.add(ruleGroupItem5);
		groupRule.setGroupItems(ruleGroup);

		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();
		doReturn(groupRule).when(formula).getGroupRule();

		assertEquals(new Integer(500), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
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
		SortedSet<RuleGroupItem> ruleGroup = new TreeSet<>(Comparator.comparingInt(RuleGroupItem::getSequenceNumber));
		ruleGroup.add(ruleGroupItem1);
		ruleGroup.add(ruleGroupItem2);
		ruleGroup.add(ruleGroupItem3);
		ruleGroup.add(ruleGroupItem4);
		ruleGroup.add(ruleGroupItem5);
		groupRule.setGroupItems(ruleGroup);

		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();
		doReturn(groupRule).when(formula).getGroupRule();

		assertEquals(new Double(502.0), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaGroupSumV#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#operationAsText()}
	 */
	@Test
	public void testOperationAsText() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		assertEquals("sumv", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		assertEquals("sumv(1)", formula.asText());
	}
	
}
