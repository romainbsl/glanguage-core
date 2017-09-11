package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.group;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.group.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.*;
import be.groups.glanguage.glanguage.api.error.exception.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
