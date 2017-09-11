package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItem;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

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
public class FormulaGroupSumVTest {
	
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
	public void testIsValidNoGroupRule() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there is a rule with wrong type within the group rule
	 */
	@Test
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
	public void testGetReturnTypeNoGroupRule() throws GLanguageException {
		FormulaGroupSumV formula = spy(FormulaGroupSumV.class);
		doReturn("1").when(formula).getConstantValue();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaGroupSumV#isValid(Evaluator)} when there are rules with integer and numeric type within the group rule
	 */
	@Test
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
