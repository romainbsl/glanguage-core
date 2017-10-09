package be.groups.glanguage.core.business.factory.formula.implementations.group;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.group.FormulaGroupSumV;
import be.groups.glanguage.core.entities.formula.implementations.terminal.FormulaTerminalNumeric;
import be.groups.glanguage.core.entities.rule.RuleGroupItem;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.entities.utils.rounding.RoundingType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaGroupSumV}
 *
 * @author micmax
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaGroupSumVIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

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
   * Tests {@link FormulaGroupSumV#getIntegerValue()}
   */
  @Test
  public void testGetIntegerValue() throws GLanguageException {
    FormulaTerminalNumeric formula1 = new FormulaTerminalNumeric(
        formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "100.4");
    FormulaTerminalNumeric formula2 = new FormulaTerminalNumeric(
        formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "100.3");

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
    SortedSet<RuleGroupItem> ruleGroup =
        new TreeSet<>(Comparator.comparingInt(RuleGroupItem::getSequenceNumber));
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
    FormulaTerminalNumeric formula1 = new FormulaTerminalNumeric(
        formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "100.4");
    FormulaTerminalNumeric formula2 = new FormulaTerminalNumeric(
        formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC), "100.3");

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
    SortedSet<RuleGroupItem> ruleGroup =
        new TreeSet<>(Comparator.comparingInt(RuleGroupItem::getSequenceNumber));
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
}
