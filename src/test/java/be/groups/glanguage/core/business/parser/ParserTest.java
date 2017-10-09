package be.groups.glanguage.core.business.parser;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.action.SemanticalAction;
import be.groups.glanguage.core.business.action.standard.AsStandard;
import be.groups.glanguage.core.business.analysis.byaccj.SlangTab;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.implementations.FormulaAnomaly;
import be.groups.glanguage.core.entities.formula.implementations.FormulaBracket;
import be.groups.glanguage.core.entities.formula.implementations.FormulaDate;
import be.groups.glanguage.core.entities.formula.implementations.FormulaIn;
import be.groups.glanguage.core.entities.formula.implementations.binary.*;
import be.groups.glanguage.core.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.core.entities.formula.implementations.duration.*;
import be.groups.glanguage.core.entities.formula.implementations.extremum.FormulaExtremumMax;
import be.groups.glanguage.core.entities.formula.implementations.extremum.FormulaExtremumMin;
import be.groups.glanguage.core.entities.formula.implementations.extremum.FormulaExtremumSignedMax;
import be.groups.glanguage.core.entities.formula.implementations.extremum.FormulaExtremumSignedMin;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatDate;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatInteger;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatNumeric;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatString;
import be.groups.glanguage.core.entities.formula.implementations.instruction.FormulaIfInstruction;
import be.groups.glanguage.core.entities.formula.implementations.math.FormulaMathAbs;
import be.groups.glanguage.core.entities.formula.implementations.math.FormulaMathSign;
import be.groups.glanguage.core.entities.formula.implementations.rounding.*;
import be.groups.glanguage.core.entities.formula.implementations.string.FormulaStringItem;
import be.groups.glanguage.core.entities.formula.implementations.string.FormulaStringLength;
import be.groups.glanguage.core.entities.formula.implementations.string.FormulaSubString;
import be.groups.glanguage.core.entities.formula.implementations.terminal.*;
import be.groups.glanguage.core.entities.formula.implementations.unary.FormulaNot;
import be.groups.glanguage.core.entities.formula.implementations.unary.FormulaUnaryMinus;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParserTest extends IntegrationTest {

  @Autowired
  AsStandard semanticalAction;

  /*
	 * Constants
	 */
  private static final double DELTA = 1e-15;

  @Test
  public void testParseBooleanTrue() throws GLanguageException {
    String str = "true";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalBoolean.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertEquals(Boolean.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(true, semanticalAction.getFormula().getBooleanValue());
  }

  @Test
  public void testParseBooleanFalse() throws GLanguageException {
    String str = "false";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalBoolean.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertEquals(Boolean.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(false, semanticalAction.getFormula().getBooleanValue());
  }

  @Test
  public void testParseInteger() throws GLanguageException {
    String str = "0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalInteger.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(Integer.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
  }

  @Test
  public void testParseDouble() throws GLanguageException {
    String str = "0,0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalNumeric.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(Double.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(new Double(0.0), semanticalAction.getFormula().getNumericValue());
  }

  @Test
  public void testParseString() throws GLanguageException {
    String str = "\"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalString.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(String.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals("abc", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testParseIntegerAsString() throws GLanguageException {
    String str = "\"0\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalString.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(String.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals("0", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testParseDoubleDotAsString() throws GLanguageException {
    String str = "\"0.0\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalString.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(String.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals("0.0", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testParseDoublCommaAsString() throws GLanguageException {
    String str = "\"0,0\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalString.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(String.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals("0,0", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testParseDate() throws GLanguageException {
    String str = "'31/12/2015'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDate.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DATE, semanticalAction.getFormula().getReturnType());
    assertEquals(LocalDate.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(LocalDate.of(2015, 12, 31), semanticalAction.getFormula().getDateValue());
  }

  @Test
  public void testParseDurationAll() throws GLanguageException {
    String str = "'P1Y1M1DT1H1M1.1S'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(397, semanticalAction.getFormula().getDurationValue().toDays());
    assertEquals(397 * 24 + 1, semanticalAction.getFormula().getDurationValue().toHours());
    assertEquals((397 * 24 + 1) * 60 + 1, semanticalAction.getFormula().getDurationValue().toMinutes());
    assertEquals(new Long((((397L * 24L + 1L) * 60L + 1L) * 60L + 1L) * 1000L + 100L),
        new Long(semanticalAction.getFormula().getDurationValue().toMillis()));
  }

  @Test
  public void testParseDurationPeriodAll() throws GLanguageException {
    String str = "'P1Y1M1D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(Duration.ofDays(397), semanticalAction.getFormula().getDurationValue());
  }

  @Test
  public void testParseDurationPeriodOnlyYears() throws GLanguageException {
    String str = "'P1Y'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(Duration.ofDays(365), semanticalAction.getFormula().getDurationValue());
  }

  @Test
  public void testParseDurationPeriodOnlyMonths() throws GLanguageException {
    String str = "'P1M'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(Duration.ofDays(31), semanticalAction.getFormula().getDurationValue());
  }

  @Test
  public void testParseDurationPeriodOnlyDays() throws GLanguageException {
    String str = "'P1D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(Duration.ofDays(1), semanticalAction.getFormula().getDurationValue());
  }

  @Test
  public void testParseDurationPeriodOnlyYearsAndMonths() throws GLanguageException {
    String str = "'P1Y1M'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(Duration.ofDays(396), semanticalAction.getFormula().getDurationValue());
  }

  @Test
  public void testParseDurationPeriodOnlyYearsAndDays() throws GLanguageException {
    String str = "'P1Y1D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    assertEquals(Duration.ofDays(366), semanticalAction.getFormula().getDurationValue());
  }

  @Test
  public void testParseDurationDurationAll() throws GLanguageException {
    String str = "'P1DT1H1M1.1S'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    Duration value = semanticalAction.getFormula().getDurationValue();
    assertEquals(1, value.toDays());
    assertEquals(25, value.toHours());
    assertEquals(1501, value.toMinutes());
    assertEquals(90061, value.toMillis() / 1000);
    assertEquals(90061100, value.toMillis());
  }

  @Test
  public void testParseDurationDurationOnlyDays() throws GLanguageException {
    String str = "'P1D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    Duration value = semanticalAction.getFormula().getDurationValue();
    assertEquals(1, value.toDays());
    assertEquals(24, value.toHours());
    assertEquals(1440, value.toMinutes());
  }

  @Test
  public void testParseDurationDurationOnlyHours() throws GLanguageException {
    String str = "'PT1H'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    Duration value = semanticalAction.getFormula().getDurationValue();
    assertEquals(0, value.toDays());
    assertEquals(1, value.toHours());
    assertEquals(60, value.toMinutes());
  }

  @Test
  public void testParseDurationDurationOnlyMinutes() throws GLanguageException {
    String str = "'PT1M'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    Duration value = semanticalAction.getFormula().getDurationValue();
    assertEquals(0, value.toDays());
    assertEquals(0, value.toHours());
    assertEquals(1, value.toMinutes());
  }

  @Test
  public void testParseDurationDurationOnlySeconds() throws GLanguageException {
    String str = "'PT1S'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    Duration value = semanticalAction.getFormula().getDurationValue();
    assertEquals(0, value.toDays());
    assertEquals(0, value.toHours());
    assertEquals(0, value.toMinutes());
    assertEquals(1, value.toMillis() / 1000);
  }

  @Test
  public void testParseDurationDurationOnlyMillis() throws GLanguageException {
    String str = "'PT0.1S'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertEquals(FormulaTerminalDuration.class, semanticalAction.getFormula().getClass());
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.class, semanticalAction.getFormula().getValue().getClass());
    Duration value = semanticalAction.getFormula().getDurationValue();
    assertEquals(0, value.toDays());
    assertEquals(0, value.toHours());
    assertEquals(0, value.toMinutes());
    assertEquals(0, value.toMillis() / 1000);
    assertEquals(100, value.toMillis());
  }

  private String getComplexIf(String n) {
    StringBuilder sb = new StringBuilder();
    sb.append("if " + n + " >= 0 then ");
    sb.append("\n");
    sb.append("\t");
    sb.append("if " + n + " = 1  then ");
    sb.append("\n");
    sb.append("\t\t");
    sb.append("\"if-if\"");
    sb.append("\n");
    sb.append("\t");
    sb.append("elseif " + n + " = 2  then ");
    sb.append("\n");
    sb.append("\t\t");
    sb.append("\"if-elseif\"");
    sb.append("\n");
    sb.append("\t");
    sb.append("elseif " + n + " <= 4 then ");
    sb.append("\n");
    sb.append("\t\t");
    sb.append("if  " + n + " = 3  then ");
    sb.append("\n");
    sb.append("\t\t\t");
    sb.append("\"if-elseif-if\"");
    sb.append("\n");
    sb.append("\t\t");
    sb.append("else ");
    sb.append("\n");
    sb.append("\t\t\t");
    sb.append("\"if-elseif-else\"");
    sb.append("\n");
    sb.append("\t\t");
    sb.append("end ");
    sb.append("\n");
    sb.append("\t");
    sb.append("else ");
    sb.append("\n");
    sb.append("\t\t");
    sb.append("\"if-else\"");
    sb.append("\n");
    sb.append("\t");
    sb.append("end ");
    sb.append("\n");
    sb.append("else ");
    sb.append("\n");
    sb.append("\t");
    sb.append("\"else\"");
    sb.append("\n");
    sb.append("end ");

    System.out.println(sb.toString());

    return sb.toString();
  }

  @Test
  public void testComplexIf1() throws GLanguageException {
    String n = "1";
    String str = getComplexIf(n);

    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str.toString());
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIfInstruction);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getValue() instanceof String);
    assertEquals("if-if", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testComplexIf2() throws GLanguageException {
    String n = "2";
    String str = getComplexIf(n);

    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str.toString());
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIfInstruction);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getValue() instanceof String);
    assertEquals("if-elseif", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testComplexIf3() throws GLanguageException {
    String n = "3";
    String str = getComplexIf(n);

    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str.toString());
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIfInstruction);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getValue() instanceof String);
    assertEquals("if-elseif-if", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testComplexIf4() throws GLanguageException {
    String n = "4";
    String str = getComplexIf(n);

    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str.toString());
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIfInstruction);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getValue() instanceof String);
    assertEquals("if-elseif-else", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testComplexIf5() throws GLanguageException {
    String n = "5";
    String str = getComplexIf(n);

    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str.toString());
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIfInstruction);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getValue() instanceof String);
    assertEquals("if-else", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testComplexIf6() throws GLanguageException {
    String n = "-1";
    String str = getComplexIf(n);

    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str.toString());
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIfInstruction);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getValue() instanceof String);
    assertEquals("else", semanticalAction.getFormula().getStringValue());
  }

  @Test
  public void testGet() throws GLanguageException {
    String str = "get string contrat.nature()";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGet);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
  }

  @Test
  public void testParseIn() throws GLanguageException {
    String str = "1 in (2;3)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for unary operation formulas
	 */

	/*
	 * Tests for unary NOT formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "not true"
   */
  @Test
  public void testParseUnaryNotTrue() throws GLanguageException {
    String str = "not true";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaNot);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "not false"
   */
  @Test
  public void testParseUnaryNotFalse() throws GLanguageException {
    String str = "not false";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaNot);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "not (0 = 0)"
   */
  @Test
  public void testParseUnaryNot1equal1() throws GLanguageException {
    String str = "not (0 = 0)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaNot);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "not 1,1 = 1,1"
   */
  @Test
  public void testParseUnaryNot1Comma1equal1Comma1() throws GLanguageException {
    String str = "not (1,1 = 1,1)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaNot);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for unary MINUS formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "-1"
   */
  @Test
  public void testParseUnaryMinus1WithoutBlank() throws GLanguageException {
    String str = "-1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaUnaryMinus);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(-1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "- 1"
   */
  @Test
  public void testParseUnaryMinus1WithBlank() throws GLanguageException {
    String str = "- 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaUnaryMinus);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(-1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "- - 1"
   */
  @Test
  public void testParseUnaryMinusMinus1() throws GLanguageException {
    String str = "- - 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaUnaryMinus);
    assertTrue(
        "Sub-formula object type not expected : "
            + semanticalAction.getFormula().getParameters().get(0).getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaUnaryMinus);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

	/*
	 * Tests for unary PLUS formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "+1"
   */
  @Test
  public void testParseUnaryPlus1WithoutBlank() throws GLanguageException {
    String str = "+1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaTerminalInteger);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "+ 1"
   */
  @Test
  public void testParseUnaryPlus1WithBlank() throws GLanguageException {
    String str = "+ 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),

        semanticalAction.getFormula() instanceof FormulaTerminalInteger);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "+ 1,1"
   */
  @Test
  public void testParseUnaryPlus1Comma1() throws GLanguageException {
    String str = "+ 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaTerminalNumeric);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.1), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

	/*
	 * Tests for binary operation formulas
	 */

	/*
	 * Tests for binary MULTIPLY formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "0*0"
   */
  @Test
  public void testParseBinaryMultiply0by0WithoutBlank() throws GLanguageException {
    String str = "0*0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMultiply);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 * 1"
   */
  @Test
  public void testParseBinaryMultiply1by1WithBlank() throws GLanguageException {
    String str = "1 * 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMultiply);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 * 1"
   */
  @Test
  public void testParseBinaryMultiply0by1() throws GLanguageException {
    String str = "0 * 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMultiply);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 * 0"
   */
  @Test
  public void testParseBinaryMultiply1by0() throws GLanguageException {
    String str = "1 * 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMultiply);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 * 1,1"
   */
  @Test
  public void testParseBinaryMultiply1by1Comma1() throws GLanguageException {
    String str = "1 * 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMultiply);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.1), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0,5 * 1,1"
   */
  @Test
  public void testParseBinaryMultiply0Comma5by1Comma1() throws GLanguageException {
    String str = "0,5 * 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMultiply);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(0.55), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "10000,01 * -10"
   */
  @Test
  public void testParseBinaryMultiply10000Comma01ByMinus10() throws GLanguageException {
    String str = "10000,01 * -10";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMultiply);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(-100000.1), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

	/*
	 * Tests for binary DIVIDE formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1/0"
   */
  @Test
  public void testParseBinaryDivide1by0WithoutBlank() throws GLanguageException {
    String str = "1/0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDivide);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getNumericValue().isInfinite());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 / 1"
   */
  @Test
  public void testParseBinaryDivide1by1WithBlank() throws GLanguageException {
    String str = "1 / 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDivide);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.0), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 / 0,5"
   */
  @Test
  public void testParseBinaryDivide1by0Comma5() throws GLanguageException {
    String str = "1 / 0,5";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDivide);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(2.0), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0,5 / 2"
   */
  @Test
  public void testParseBinaryDivide0Comma5By2() throws GLanguageException {
    String str = "0,5 / 2";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDivide);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(0.25), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0,5 / 0,5"
   */
  @Test
  public void testParseBinaryDivide0Comma5By0Comma5() throws GLanguageException {
    String str = "0,5 / 0,5";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDivide);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.0), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

	/*
	 * Tests for binary INTEGER DIVISION formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1//0"
   */
  @Test(expected = ArithmeticException.class)
  public void testParseBinaryIntegerDivision1by0WithoutBlank() throws GLanguageException {
    String str = "1//0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIntegerDivision);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    semanticalAction.getFormula().getIntegerValue();
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "2 // 1"
   */
  @Test
  public void testParseBinaryIntegerDivision2by1WithBlank() throws GLanguageException {
    String str = "2 // 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIntegerDivision);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 // 1"
   */
  @Test
  public void testParseBinaryIntegerDivision0by1() throws GLanguageException {
    String str = "0 // 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIntegerDivision);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
  }

	/*
	 * Tests for binary MODULO formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1\\0"
   */
  @Test(expected = ArithmeticException.class)
  public void testParseBinaryModulo1by0WithoutBlank() throws GLanguageException {
    String str = "1\\\\0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaModulo);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    semanticalAction.getFormula().getIntegerValue();
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "3 \\ 2"
   */
  @Test
  public void testParseBinaryModulo3by2WithBlank() throws GLanguageException {
    String str = "3 \\\\ 2";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaModulo);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 \\ 1"
   */
  @Test
  public void testParseBinaryModulo0by1() throws GLanguageException {
    String str = "0 \\\\ 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaModulo);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
  }

	/*
	 * Tests for binary PLUS formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1+1"
   */
  @Test
  public void testParseBinaryPlus1And1WithoutBlank() throws GLanguageException {
    String str = "1+1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 + 1"
   */
  @Test
  public void testParseBinaryPlus1And1WithBlank() throws GLanguageException {
    String str = "1 + 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1+1,1"
   */
  @Test
  public void testParseBinaryPlus1Comma1And1Comma1WithoutBlank() throws GLanguageException {
    String str = "1,1+1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(2.2), semanticalAction.getFormula().getNumericValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 + 1,1"
   */
  @Test
  public void testParseBinaryPlus1Comma1And1Comma1WithBlank() throws GLanguageException {
    String str = "1,1 + 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(2.2), semanticalAction.getFormula().getNumericValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""ab"+"c""
   */
  @Test
  public void testParseBinaryPlusABAndCWithoutBlank() throws GLanguageException {
    String str = "\"ab\"+\"c\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String("abc"), semanticalAction.getFormula().getStringValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""ab" + "c""
   */
  @Test
  public void testParseBinaryPlusABAndCWithBlank() throws GLanguageException {
    String str = "\"ab\" + \"c\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String("abc"), semanticalAction.getFormula().getStringValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "'01/01/2016' + 'P1D'"
   */
  @Test
  public void testParseBinaryPlusDateAndDuration() throws GLanguageException {
    String str = "'01/01/2016' + 'P1D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.DATE, semanticalAction.getFormula().getReturnType());
    assertEquals(LocalDate.of(2016, 1, 2), semanticalAction.getFormula().getDateValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "'P1D' + '01/01/2016'"
   */
  @Test
  public void testParseBinaryPlusDurationAndDate() throws GLanguageException {
    String str = "'P1D' + '01/01/2016'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.DATE, semanticalAction.getFormula().getReturnType());
    assertEquals(LocalDate.of(2016, 1, 2), semanticalAction.getFormula().getDateValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "'P1D' + 'P1D'"
   */
  @Test
  public void testParseBinaryPlusBothDuration() throws GLanguageException {
    String str = "'P1D' + 'P1D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaPlus);
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.ofDays(2), semanticalAction.getFormula().getDurationValue());
  }

	/*
	 * Tests for binary MINUS formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1-1"
   */
  @Test
  public void testParseBinaryMinus1And1WithoutBlank() throws GLanguageException {
    String str = "1-1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 - 1"
   */
  @Test
  public void testParseBinaryMinus1And1WithBlank() throws GLanguageException {
    String str = "1 - 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1-1,1"
   */
  @Test
  public void testParseBinaryMinus1Comma1And1Comma1WithoutBlank() throws GLanguageException {
    String str = "1,1-1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(0.0), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 - 1,1"
   */
  @Test
  public void testParseBinaryMinus1Comma1And1Comma1WithBlank() throws GLanguageException {
    String str = "1,1 - 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(0.0), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 - 1,1"
   */
  @Test
  public void testParseBinaryMinus1And1Comma1WithBlank() throws GLanguageException {
    String str = "1 - 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(-0.1), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 - 1"
   */
  @Test
  public void testParseBinaryMinus1Comma1And1WithBlank() throws GLanguageException {
    String str = "1,1 - 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(0.1), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 - 2"
   */
  @Test
  public void testParseBinaryMinus1And2() throws GLanguageException {
    String str = "1 - 2";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(-1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "'01/01/2016' - 'P1D'"
   */
  @Test
  public void testParseBinaryMinusDateAndDuration() throws GLanguageException {
    String str = "'01/01/2016' - 'P1D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.DATE, semanticalAction.getFormula().getReturnType());
    assertEquals(LocalDate.of(2015, 12, 31), semanticalAction.getFormula().getDateValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "'P2D' - 'P1D'"
   */
  @Test
  public void testParseBinaryMinusBothDuration() throws GLanguageException {
    String str = "'P2D' - 'P1D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.ofDays(1), semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "'P1D' - 'P2D'"
   */
  @Test
  public void testParseBinaryMinusBothDurationSecondGreaterThanFirst() throws GLanguageException {
    String str = "'P1D' - 'P2D'";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMinus);
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.ofDays(-1), semanticalAction.getFormula().getDurationValue());
  }

	/*
	 * Tests for binary EQUAL formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1=1"
   */
  @Test
  public void testParseBinaryEqual1And1WithoutBlank() throws GLanguageException {
    String str = "1=1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 = 1"
   */
  @Test
  public void testParseBinaryEqual1And1WithBlank() throws GLanguageException {
    String str = "1 = 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 = 0"
   */
  @Test
  public void testParseBinaryEqual1And0() throws GLanguageException {
    String str = "1 = 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 = 1,1"
   */
  @Test
  public void testParseBinaryEqual1Comma1And1Comma1() throws GLanguageException {
    String str = "1,1 = 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 = 0"
   */
  @Test
  public void testParseBinaryEqual1Comma1And0() throws GLanguageException {
    String str = "1 = 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" = "abc""
   */
  @Test
  public void testParseBinaryEqualABCAndABC() throws GLanguageException {
    String str = "\"abc\" = \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" = "ab""
   */
  @Test
  public void testParseBinaryEqualABCAndAB() throws GLanguageException {
    String str = "\"abc\" = \"ab\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for binary DIFFERENCE formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1<>1"
   */
  @Test
  public void testParseBinaryDifference1And1WithoutBlank() throws GLanguageException {
    String str = "1<>1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDifference);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 <> 1"
   */
  @Test
  public void testParseBinaryDifference1And1WithBlank() throws GLanguageException {
    String str = "1 <> 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDifference);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 <> 0"
   */
  @Test
  public void testParseBinaryDifference1And0() throws GLanguageException {
    String str = "1 <> 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDifference);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 <> 1,1"
   */
  @Test
  public void testParseBinaryDifference1Comma1And1Comma1() throws GLanguageException {
    String str = "1,1 <> 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDifference);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 <> 0"
   */
  @Test
  public void testParseBinaryDifference1Comma1And0() throws GLanguageException {
    String str = "1 <> 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDifference);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" <> "abc""
   */
  @Test
  public void testParseBinaryDifferenceABCAndABC() throws GLanguageException {
    String str = "\"abc\" <> \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDifference);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" <> "ab""
   */
  @Test
  public void testParseBinaryDifferenceABCAndAB() throws GLanguageException {
    String str = "\"abc\" <> \"ab\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDifference);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for binary SMALLER formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1<1"
   */
  @Test
  public void testParseBinarySmaller1And1WithoutBlank() throws GLanguageException {
    String str = "1<1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 < 1"
   */
  @Test
  public void testParseBinarySmaller1And1WithBlank() throws GLanguageException {
    String str = "1 < 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 < 0"
   */
  @Test
  public void testParseBinarySmaller1And0() throws GLanguageException {
    String str = "1 < 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 < 1"
   */
  @Test
  public void testParseBinarySmaller0And1() throws GLanguageException {
    String str = "0 < 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 < 1,1"
   */
  @Test
  public void testParseBinarySmaller1Comma1And1Comma1() throws GLanguageException {
    String str = "1,1 < 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 < 0"
   */
  @Test
  public void testParseBinarySmaller1Comma1And0() throws GLanguageException {
    String str = "1,1 < 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 < 1,1"
   */
  @Test
  public void testParseBinarySmaller0And1Comma1() throws GLanguageException {
    String str = "0 < 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" < "abc""
   */
  @Test
  public void testParseBinarySmallerABCAndABC() throws GLanguageException {
    String str = "\"abc\" < \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" < "ab""
   */
  @Test
  public void testParseBinarySmallerABCAndAB() throws GLanguageException {
    String str = "\"abc\" < \"ab\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""ab" < "abc""
   */
  @Test
  public void testParseBinarySmallerABAndABC() throws GLanguageException {
    String str = "\"ab\" < \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmaller);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for binary SMALLER OR EQUAL formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1<=1"
   */
  @Test
  public void testParseBinarySmallerOrEqual1And1WithoutBlank() throws GLanguageException {
    String str = "1<=1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 <= 1"
   */
  @Test
  public void testParseBinarySmallerOrEqual1And1WithBlank() throws GLanguageException {
    String str = "1 <= 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 <= 0"
   */
  @Test
  public void testParseBinarySmallerOrEqual1And0() throws GLanguageException {
    String str = "1 <= 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 <= 1"
   */
  @Test
  public void testParseBinarySmallerOrEqual0And1() throws GLanguageException {
    String str = "0 <= 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 <= 1,1"
   */
  @Test
  public void testParseBinarySmallerOrEqual1Comma1And1Comma1() throws GLanguageException {
    String str = "1,1 <= 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 <= 0"
   */
  @Test
  public void testParseBinarySmallerOrEqual1Comma1And0() throws GLanguageException {
    String str = "1,1 <= 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 <= 1,1"
   */
  @Test
  public void testParseBinarySmallerOrEqual0And1Comma1() throws GLanguageException {
    String str = "0 <= 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" <= "abc""
   */
  @Test
  public void testParseBinarySmallerOrEqualABCAndABC() throws GLanguageException {
    String str = "\"abc\" <= \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" <= "ab""
   */
  @Test
  public void testParseBinarySmallerOrEqualABCAndAB() throws GLanguageException {
    String str = "\"abc\" <= \"ab\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""ab" <= "abc""
   */
  @Test
  public void testParseBinarySmallerOrEqualABAndABC() throws GLanguageException {
    String str = "\"ab\" <= \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSmallerOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for binary GREATER formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1>1"
   */
  @Test
  public void testParseBinaryGreater1And1WithoutBlank() throws GLanguageException {
    String str = "1>1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 > 1"
   */
  @Test
  public void testParseBinaryGreater1And1WithBlank() throws GLanguageException {
    String str = "1 > 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 > 0"
   */
  @Test
  public void testParseBinaryGreater1And0() throws GLanguageException {
    String str = "1 > 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 > 1"
   */
  @Test
  public void testParseBinaryGreater0And1() throws GLanguageException {
    String str = "0 > 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 > 1,1"
   */
  @Test
  public void testParseBinaryGreater1Comma1And1Comma1() throws GLanguageException {
    String str = "1,1 > 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 > 0"
   */
  @Test
  public void testParseBinaryGreater1Comma1And0() throws GLanguageException {
    String str = "1,1 > 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 > 1,1"
   */
  @Test
  public void testParseBinaryGreater0And1Comma1() throws GLanguageException {
    String str = "0 > 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" > "abc""
   */
  @Test
  public void testParseBinaryGreaterABCAndABC() throws GLanguageException {
    String str = "\"abc\" > \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" > "ab""
   */
  @Test
  public void testParseBinaryGreaterABCAndAB() throws GLanguageException {
    String str = "\"abc\" > \"ab\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""ab" > "abc""
   */
  @Test
  public void testParseBinaryGreaterABAndABC() throws GLanguageException {
    String str = "\"ab\" > \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreater);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for binary GREATER OR EQUAL formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1>=1"
   */
  @Test
  public void testParseBinaryGreaterOrEqual1And1WithoutBlank() throws GLanguageException {
    String str = "1>=1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 >= 1"
   */
  @Test
  public void testParseBinaryGreaterOrEqual1And1WithBlank() throws GLanguageException {
    String str = "1 >= 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 >= 0"
   */
  @Test
  public void testParseBinaryGreaterOrEqual1And0() throws GLanguageException {
    String str = "1 >= 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 >= 1"
   */
  @Test
  public void testParseBinaryGreaterOrEqual0And1() throws GLanguageException {
    String str = "0 >= 1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 >= 1,1"
   */
  @Test
  public void testParseBinaryGreaterOrEqual1Comma1And1Comma1() throws GLanguageException {
    String str = "1,1 >= 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 >= 0"
   */
  @Test
  public void testParseBinaryGreaterOrEqual1Comma1And0() throws GLanguageException {
    String str = "1,1 >= 0";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "0 >= 1,1"
   */
  @Test
  public void testParseBinaryGreaterOrEqual0And1Comma1() throws GLanguageException {
    String str = "0 >= 1,1";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" >= "abc""
   */
  @Test
  public void testParseBinaryGreaterOrEqualABCAndABC() throws GLanguageException {
    String str = "\"abc\" >= \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""abc" >= "ab""
   */
  @Test
  public void testParseBinaryGreaterOrEqualABCAndAB() throws GLanguageException {
    String str = "\"abc\" >= \"ab\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""ab" >= "abc""
   */
  @Test
  public void testParseBinaryGreaterOrEqualABAndABC() throws GLanguageException {
    String str = "\"ab\" >= \"abc\"";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaGreaterOrEqual);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for binary AND formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "true and true"
   */
  @Test
  public void testParseBinaryAndTrueAndTrue() throws GLanguageException {
    String str = "true and true";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaAnd);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "true and false"
   */
  @Test
  public void testParseBinaryAndTrueAndFalse() throws GLanguageException {
    String str = "true and false";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaAnd);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "false and false"
   */
  @Test
  public void testParseBinaryAndFalseAndFalse() throws GLanguageException {
    String str = "false and false";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaAnd);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for binary OR formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "true or true"
   */
  @Test
  public void testParseBinaryOrTrueAndTrue() throws GLanguageException {
    String str = "true or true";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaOr);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "true or false"
   */
  @Test
  public void testParseBinaryOrTrueAndFalse() throws GLanguageException {
    String str = "true or false";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaOr);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "false or false"
   */
  @Test
  public void testParseBinaryOrFalseAndFalse() throws GLanguageException {
    String str = "false or false";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaOr);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for BRACKET formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "(1)"
   */
  @Test
  public void testParseBracket1WithoutBlank() throws GLanguageException {
    String str = "(1)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaBracket);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "( 1 )"
   */
  @Test
  public void testParseBracket1WithBlank() throws GLanguageException {
    String str = "( 1 )";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaBracket);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "(1,1)"
   */
  @Test
  public void testParseBracket1Comma1() throws GLanguageException {
    String str = "(1,1)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaBracket);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.1), semanticalAction.getFormula().getNumericValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "("abc")"
   */
  @Test
  public void testParseBracketABC() throws GLanguageException {
    String str = "(\"abc\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaBracket);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String("abc"), semanticalAction.getFormula().getStringValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "(true)"
   */
  @Test
  public void testParseBracketTrue() throws GLanguageException {
    String str = "(true)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaBracket);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for IN formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "1 in (1)"
   */
  @Test
  public void testParseIn1And1() throws GLanguageException {
    String str = "1 in (1)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 in (1;2;3)"
   */
  @Test
  public void testParseIn1And123() throws GLanguageException {
    String str = "1 in (1;2;3)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1 in (2;3)"
   */
  @Test
  public void testParseIn1And23() throws GLanguageException {
    String str = "1 in (2;3)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 in (1,1)"
   */
  @Test
  public void testParseIn1Comma1And1Comma1() throws GLanguageException {
    String str = "1,1 in (1,1)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 in (1,1;2,2;3,3)"
   */
  @Test
  public void testParseIn1Comma1And1Comma1And2Comma2And3Comma3() throws GLanguageException {
    String str = "1,1 in (1,1;2,2;3,3)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "1,1 in (2,2;3,3)"
   */
  @Test
  public void testParseIn1Comma1And2Comma2And3Comma3() throws GLanguageException {
    String str = "1,1 in (2,2;3,3)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""a" in ("a")"
   */
  @Test
  public void testParseInAAndA() throws GLanguageException {
    String str = "\"a\" in (\"a\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""a" in ("a";"b";"c")"
   */
  @Test
  public void testParseInAAndABC() throws GLanguageException {
    String str = "\"a\" in (\"a\";\"b\";\"c\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getBooleanValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string ""a" in ("b";"c")"
   */
  @Test
  public void testParseInAAndBC() throws GLanguageException {
    String str = "\"a\" in (\"b\";\"c\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaIn);
    assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
    assertFalse(semanticalAction.getFormula().getBooleanValue());
  }

	/*
	 * Tests for standard operation formulas
	 */

	/*
	 * Tests for standard rounding formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "ceil(1,111 ; 0,01)"
   */
  @Test
  public void testParseCeil1Comma111Precision0Comma01() throws GLanguageException {
    String str = "ceil(1,111 ; 0,01)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingCeil);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.12), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "floor(1,111 ; 0,01)"
   */
  @Test
  public void testParseFloor1Comma111Precision0Comma01() throws GLanguageException {
    String str = "floor(1,111 ; 0,01)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingFloor);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.11), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "round(1,111 ; 0,01)"
   */
  @Test
  public void testParseRound1Comma111Precision0Comma01() throws GLanguageException {
    String str = "rounded(1,111 ; 0,01)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingArithmetic);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.11), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "trunc(1,111 ; 2)"
   */
  @Test
  public void testParseTrunc1Comma111Precision2() throws GLanguageException {
    String str = "trunc(1,111 ; 2)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingTrunc);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.11), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "bankers_rounded(1,111 ; 2)"
   */
  @Test
  public void testParseBankersRounded1Comma111Precision2() throws GLanguageException {
    String str = "bankers_rounded(1,111 ; 2)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingBankers);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.11), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "ceil(1,111)"
   */
  @Test
  public void testParseCeil1Comma111NoPrecision() throws GLanguageException {
    String str = "ceil(1,111)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingCeil);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(2.0), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "floor(1,111)"
   */
  @Test
  public void testParseFloor1Comma111NoPrecision() throws GLanguageException {
    String str = "floor(1,111)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingFloor);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.0), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "round(1,111)"
   */
  @Test
  public void testParseRound1Comma111NoPrecision() throws GLanguageException {
    String str = "rounded(1,111)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingArithmetic);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.0), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "trunc(1,111)"
   */
  @Test
  public void testParseTrunc1Comma111NoPrecision() throws GLanguageException {
    String str = "trunc(1,111)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingTrunc);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.11), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "bankers_rounded(1,111)"
   */
  @Test
  public void testParseBankersRounded1Comma111NoPrecision() throws GLanguageException {
    String str = "bankers_rounded(1,111)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaRoundingBankers);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(1.11), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

	/*
	 * Tests for standard format formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "formatDate('01/01/2016' ; "yyyyMMdd")"
   */
  @Test
  public void testParseFormatDate() throws GLanguageException {
    String str = "formatDate('01/01/2016' ; \"yyyyMMdd\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaFormatDate);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String("20160101"), semanticalAction.getFormula().getStringValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "formatInteger(1000 ; 10 ; "NONE" ; "NONE")"
   */
  @Test
  public void testParseFormatInteger() throws GLanguageException {
    String str = "formatInteger(1000 ; 10 ; \"NONE\" ; \"NONE\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaFormatInteger);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String("1000"), semanticalAction.getFormula().getStringValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "formatNumeric(1000 ; 10 ; 2 ; "NONE" ; "NONE" ; ",")"
   */
  @Test
  public void testParseFormatNumeric() throws GLanguageException {
    String str = "formatNumeric(1000,0 ; 10 ; 2 ; \"NONE\" ; \"NONE\" ; \",\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaFormatNumeric);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String("1000,00"), semanticalAction.getFormula().getStringValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "formatString("abc" ; 5 ; "CENTER" ; "")"
   */
  @Test
  public void testParseFormatString() throws GLanguageException {
    String str = "formatString(\"abc\" ; 5 ; \"CENTER\" ; \"\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaFormatString);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String(" abc "), semanticalAction.getFormula().getStringValue());
  }

	/*
	 * Tests for standard STRING ITEM formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "stringItem("a/ab/abc" ; "/" ; 2)"
   */
  @Test
  public void testParseStringItem() throws GLanguageException {
    String str = "stringItem(\"a/ab/abc\" ; \"/\" ; 2)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaStringItem);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String("ab"), semanticalAction.getFormula().getStringValue());
  }

	/*
	 * Tests for standard SUB-STRING formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "substring("abcdef" ; 1 ; 3)"
   */
  @Test
  public void testParseSubString() throws GLanguageException {
    String str = "substring(\"abcdef\" ; 1 ; 3)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaSubString);
    assertEquals(FormulaReturnType.STRING, semanticalAction.getFormula().getReturnType());
    assertEquals(new String("abc"), semanticalAction.getFormula().getStringValue());
  }

	/*
	 * Tests for standard extremum formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "max(1 ; 2 ; 3 ; -4)"
   */
  @Test
  public void testParseMax() throws GLanguageException {
    String str = "max(1 ; 2 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumMax);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(3), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "smax(1 ; 2 ; 3 ; -4)"
   */
  @Test
  public void testParseSignedMax() throws GLanguageException {
    String str = "smax(1 ; 2 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumSignedMax);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(4), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "smax(1 ; 2 ; 3 ; -4)"
   */
  @Test
  public void testParseMinusSignedMax() throws GLanguageException {
    String str = "smax(-1 ; 2 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumSignedMax);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(-4), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "max(1 ; 2,0 ; 3 ; -4)"
   */
  @Test
  public void testParseMaxDouble() throws GLanguageException {
    String str = "max(1 ; 2,0 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumMax);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(3), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "smax(1 ; 2,0 ; 3 ; -4)"
   */
  @Test
  public void testParseSignedMaxDouble() throws GLanguageException {
    String str = "smax(1 ; 2,0 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumSignedMax);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(4), semanticalAction.getFormula().getIntegerValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "smax(1 ; 2,0 ; 3 ; -4)"
   */
  @Test
  public void testParseMinusSignedMaxDouble() throws GLanguageException {
    String str = "smax(-1 ; 2,0 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumSignedMax);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(-4), semanticalAction.getFormula().getIntegerValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "min(1 ; 2 ; 3 ; -4)"
   */
  @Test
  public void testParseMin() throws GLanguageException {
    String str = "min(1 ; 2 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumMin);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(-4), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "smin(1 ; 2 ; 3 ; -4)"
   */
  @Test
  public void testParseSignedMin() throws GLanguageException {
    String str = "smin(1 ; 2 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumSignedMin);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer("1"), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "smin(1 ; 2 ; 3 ; -4)"
   */
  @Test
  public void testParseMinusSignedMin() throws GLanguageException {
    String str = "smin(-1 ; 2 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumSignedMin);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer("-1"), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "min(1 ; 2,0 ; 3 ; -4)"
   */
  @Test
  public void testParseMinDouble() throws GLanguageException {
    String str = "min(1 ; 2,0 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumMin);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(-4), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "smin(1 ; 2,0 ; 3 ; -4)"
   */
  @Test
  public void testParseSignedMinDouble() throws GLanguageException {
    String str = "smin(1 ; 2,0 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumSignedMin);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double("1"), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "smin(1 ; 2,0 ; 3 ; -4)"
   */
  @Test
  public void testParseMinusSignedMinDouble() throws GLanguageException {
    String str = "smin(-1 ; 2,0 ; 3 ; -4)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaExtremumSignedMin);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double("-1"), semanticalAction.getFormula().getNumericValue(), DELTA);
  }

	/*
	 * Tests for standard DATE formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "date("1/1/2016")"
   */
  @Test
  public void testParseFormulaDateString() throws GLanguageException {
    String str = "date(\"01/01/2016\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDate);
    assertEquals(FormulaReturnType.DATE, semanticalAction.getFormula().getReturnType());
    assertEquals(LocalDate.of(2016, 1, 1), semanticalAction.getFormula().getDateValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "date(1 ; 1 ; 2016)"
   */
  @Test
  public void testParseFormulaDateParts() throws GLanguageException {
    String str = "date(1 ; 1 ; 2016)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDate);
    assertEquals(FormulaReturnType.DATE, semanticalAction.getFormula().getReturnType());
    assertEquals(LocalDate.of(2016, 1, 1), semanticalAction.getFormula().getDateValue());
  }

	/*
	 * Tests for standard duration formulas
	 */

	/*
	 * Tests for standard duration MINUTES formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "minutes(10)"
   */
  @Test
  public void testParseMinutesInteger() throws GLanguageException {
    String str = "minutes(10)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationMinutes);
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.ofMinutes(10), semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "minutes('PT10M')"
   */
  @Test
  public void testParseMinutesDuration() throws GLanguageException {
    String str = "minutes('PT10M')";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationMinutes);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(10), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "minutes(hours(2))"
   */
  @Test
  public void testParseMinutesHours() throws GLanguageException {
    String str = "minutes(hours(2))";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationMinutes);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(120), semanticalAction.getFormula().getIntegerValue());
  }

	/*
	 * Tests for standard duration HOURS formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "hours(10)"
   */
  @Test
  public void testParseHoursInteger() throws GLanguageException {
    String str = "hours(10)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationHours);
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.ofHours(10), semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "hours('PT10H')"
   */
  @Test
  public void testParseHoursDuration() throws GLanguageException {
    String str = "hours('PT10H')";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationHours);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(10), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "hours(minutes(120))"
   */
  @Test
  public void testParseHoursMinutes() throws GLanguageException {
    String str = "hours(minutes(120))";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationHours);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

	/*
	 * Tests for standard duration DAYS formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "days(2)"
   */
  @Test
  public void testParseDaysInteger() throws GLanguageException {
    String str = "days(2)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationDays);
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.ofDays(2), semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "days('P2D')"
   */
  @Test
  public void testParseDaysDuration() throws GLanguageException {
    String str = "days('P2D')";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationDays);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "days(hours(24))"
   */
  @Test
  public void testParseDaysHours() throws GLanguageException {
    String str = "days(hours(24))";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationDays);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "days('02/01/2016')"
   */
  @Test
  public void testParseDaysDate() throws GLanguageException {
    String str = "days('02/01/2016')";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationDays);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

	/*
	 * Tests for standard duration MONTHS formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "months(2)"
   */
  @Test
  public void testParseMonthsInteger() throws GLanguageException {
    String str = "months(2)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationMonths);
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.ofDays(2 * 31), semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "days('P2M')"
   */
  @Test
  public void testParseMonthsDuration() throws GLanguageException {
    String str = "months('P2M')";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationMonths);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "months(days(31))"
   */
  @Test
  public void testParseMonthsDays() throws GLanguageException {
    String str = "months(days(31))";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationMonths);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "months('01/02/2016')"
   */
  @Test
  public void testParseMonthsDate() throws GLanguageException {
    String str = "months('01/02/2016')";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationMonths);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

	/*
	 * Tests for standard duration YEARS formulas
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "years(2)"
   */
  @Test
  public void testParseYearsInteger() throws GLanguageException {
    String str = "years(2)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationYears);
    assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormula().getReturnType());
    assertEquals(Duration.ofDays(2 * 365), semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "years('P2Y')"
   */
  @Test
  public void testParseYearsDuration() throws GLanguageException {
    String str = "years('P2Y')";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationYears);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "years(months(12))"
   */
  @Test
  public void testParseYearMonths() throws GLanguageException {
    String str = "years(months(12))";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationYears);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(1), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "years('01/01/2016')"
   */
  @Test
  public void testParseYearsDate() throws GLanguageException {
    String str = "years('01/02/2016')";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaDurationYears);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(2016), semanticalAction.getFormula().getIntegerValue());
  }

	/*
	 * Tests for standard math formulas
	 */

	/*
	 * Tests for standard math ABS formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "abs(10)"
   */
  @Test
  public void testParseAbsPositiveInteger() throws GLanguageException {
    String str = "abs(10)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathAbs);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(10), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "abs (-10)"
   */
  @Test
  public void testParseAbsNegativeInteger() throws GLanguageException {
    String str = "abs (-10)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathAbs);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(10), semanticalAction.getFormula().getIntegerValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "abs (10,0)"
   */
  @Test
  public void testParseAbsPositiveNumeric() throws GLanguageException {
    String str = "abs(10,0)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathAbs);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(10.0), semanticalAction.getFormula().getNumericValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "abs (-10,0)"
   */
  @Test
  public void testParseAbsNegativeNumeric() throws GLanguageException {
    String str = "abs (-10,0)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathAbs);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertEquals(new Double(10.0), semanticalAction.getFormula().getNumericValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "sign(10)"
   */
  @Test
  public void testParseSignPositiveInteger() throws GLanguageException {
    String str = "sign(10)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathSign);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getIntegerValue() > 0);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "sign (-10)"
   */
  @Test
  public void testParseSignNegativeInteger() throws GLanguageException {
    String str = "sign (-10)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathSign);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getIntegerValue() < 0);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "sign(0)"
   */
  @Test
  public void testParseSignZeroInteger() throws GLanguageException {
    String str = "sign(0)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathSign);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getIntegerValue() == 0);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "sign(+0)"
   */
  @Test
  public void testParseSignPositiveZeroInteger() throws GLanguageException {
    String str = "sign(0)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathSign);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getIntegerValue() == 0);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "sign(-0)"
   */
  @Test
  public void testParseSignNegativeZeroInteger() throws GLanguageException {
    String str = "sign(-0)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathSign);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getIntegerValue() < 0);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "sign (10,0)"
   */
  @Test
  public void testParseSignPositiveNumeric() throws GLanguageException {
    String str = "sign(10,0)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathSign);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getNumericValue() > 0.0);
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "sign (-10,0)"
   */
  @Test
  public void testParseSignNegativeNumeric() throws GLanguageException {
    String str = "sign (-10,0)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaMathSign);
    assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormula().getReturnType());
    assertTrue(semanticalAction.getFormula().getNumericValue() < 0.0);
  }

	/*
	 * Tests for ANOMALY formula
	 */

  /**
   * Tests {@link SlangTab#analyze()} with string "put_text (1000)"
   */
  @Test
  public void testParseAnomalyIntegerId() throws GLanguageException {
    String str = "put_text (1000)";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaAnomaly);
    assertEquals(FormulaReturnType.UNDEFINED, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
    assertEquals(new Double(0.0), semanticalAction.getFormula().getNumericValue());
    assertEquals(new String(""), semanticalAction.getFormula().getStringValue());
    assertEquals(LocalDate.MIN, semanticalAction.getFormula().getDateValue());
    assertEquals(Duration.ZERO, semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "put_text ("1000")"
   */
  @Test
  public void testParseAnomalyStringId() throws GLanguageException {
    String str = "put_text (\"1000\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaAnomaly);
    assertEquals(FormulaReturnType.UNDEFINED, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
    assertEquals(new Double(0.0), semanticalAction.getFormula().getNumericValue());
    assertEquals(new String(""), semanticalAction.getFormula().getStringValue());
    assertEquals(LocalDate.MIN, semanticalAction.getFormula().getDateValue());
    assertEquals(Duration.ZERO, semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "put_text (1000 ; "abc")"
   */
  @Test
  public void testParseAnomalyIntegerIdAndMessage() throws GLanguageException {
    String str = "put_text (1000 ; \"abc\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaAnomaly);
    assertEquals(FormulaReturnType.UNDEFINED, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
    assertEquals(new Double(0.0), semanticalAction.getFormula().getNumericValue());
    assertEquals(new String(""), semanticalAction.getFormula().getStringValue());
    assertEquals(LocalDate.MIN, semanticalAction.getFormula().getDateValue());
    assertEquals(Duration.ZERO, semanticalAction.getFormula().getDurationValue());
  }

  /**
   * Tests {@link SlangTab#analyze()} with string "put_text ("1000" ; "abc")"
   */
  @Test
  public void testParseAnomalyStringIdAndMessage() throws GLanguageException {
    String str = "put_text (\"1000\" ; \"abc\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaAnomaly);
    assertEquals(FormulaReturnType.UNDEFINED, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(0), semanticalAction.getFormula().getIntegerValue());
    assertEquals(new Double(0.0), semanticalAction.getFormula().getNumericValue());
    assertEquals(new String(""), semanticalAction.getFormula().getStringValue());
    assertEquals(LocalDate.MIN, semanticalAction.getFormula().getDateValue());
    assertEquals(Duration.ZERO, semanticalAction.getFormula().getDurationValue());
  }

	/*
	 * Tests for STRING LENGTH formula
	 */
  /**
   * Tests {@link SlangTab#analyze()} with string "stringLength("abc")"
   */
  @Test
  public void testParseStringLenght() throws GLanguageException {
    String str = "stringLength(\"abc\")";
    SlangTab parser = new SlangTab(true);
    parser.setSemanticalAction(semanticalAction);
    parser.setFormulaString(str);
    parser.analyze();
    assertNotNull(semanticalAction.getFormula());
    assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
        semanticalAction.getFormula() instanceof FormulaStringLength);
    assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
    assertEquals(new Integer(3), semanticalAction.getFormula().getIntegerValue());
  }
}
