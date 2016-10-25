package be.groups.glanguage.glanguage.api.business.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.action.standard.AsStandard;
import be.groups.glanguage.glanguage.api.business.analysis.byaccj.SlangTab;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaAnomaly;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaBracket;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaIn;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaAnd;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDifference;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDivide;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaEqual;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreater;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreaterOrEqual;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaIntegerDivision;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMinus;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaModulo;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMultiply;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaOr;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaPlus;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmaller;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmallerOrEqual;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationDays;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationHours;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationMinutes;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationMonths;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationYears;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMax;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMin;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMax;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMin;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction.FormulaIfInstruction;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathAbs;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathSign;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingArithmetic;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingBankers;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingCeil;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingFloor;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingTrunc;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringItem;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringLength;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaSubString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalBoolean;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDuration;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaNot;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaUnaryMinus;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

public class ParserTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Setups
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();
		
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		
		if (!TransactionHelper.isActive()) {
			TransactionHelper.begin();
		}
	}
	
	@AfterClass
	public static void close() {
		if (TransactionHelper.isActive()) {
			TransactionHelper.rollback();
		}
	}
	
	@Test
	public void testParseBooleanTrue() {
		String str = "true";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBooleanFalse() {
		String str = "false";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseInteger() {
		String str = "0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDouble() {
		String str = "0,0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseString() {
		String str = "\"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseIntegerAsString() {
		String str = "\"0\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDoubleDotAsString() {
		String str = "\"0.0\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDoublCommaAsString() {
		String str = "\"0,0\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDate() {
		String str = "'31/12/2015'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationAll() {
		String str = "'P1Y1M1DT1H1M1.1S'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationPeriodAll() {
		String str = "'P1Y1M1D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationPeriodOnlyYears() {
		String str = "'P1Y'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationPeriodOnlyMonths() {
		String str = "'P1M'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationPeriodOnlyDays() {
		String str = "'P1D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationPeriodOnlyYearsAndMonths() {
		String str = "'P1Y1M'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationPeriodOnlyYearsAndDays() {
		String str = "'P1Y1D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationDurationAll() {
		String str = "'P1DT1H1M1.1S'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationDurationOnlyDays() {
		String str = "'P1D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationDurationOnlyHours() {
		String str = "'PT1H'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationDurationOnlyMinutes() {
		String str = "'PT1M'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationDurationOnlySeconds() {
		String str = "'PT1S'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDurationDurationOnlyMillis() {
		String str = "'PT0.1S'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testComplexIf1() {
		String n = "1";
		String str = getComplexIf(n);
		
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testComplexIf2() {
		String n = "2";
		String str = getComplexIf(n);
		
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testComplexIf3() {
		String n = "3";
		String str = getComplexIf(n);
		
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testComplexIf4() {
		String n = "4";
		String str = getComplexIf(n);
		
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testComplexIf5() {
		String n = "5";
		String str = getComplexIf(n);
		
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testComplexIf6() {
		String n = "-1";
		String str = getComplexIf(n);
		
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testGet() {
		String str = "get string contrat.nature()";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseIn() {
		String str = "r1 in (2;3)";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormula().getReturnType());
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
	public void testParseUnaryNotTrue() {
		String str = "not true";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryNotFalse() {
		String str = "not false";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryNot1equal1() {
		String str = "not (0 = 0)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryNot1Comma1equal1Comma1() {
		String str = "not (1,1 = 1,1)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryMinus1WithoutBlank() {
		String str = "-1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryMinus1WithBlank() {
		String str = "- 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryMinusMinus1() {
		String str = "- - 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryPlus1WithoutBlank() {
		String str = "+1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryPlus1WithBlank() {
		String str = "+ 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseUnaryPlus1Comma1() {
		String str = "+ 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMultiply0by0WithoutBlank() {
		String str = "0*0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMultiply1by1WithBlank() {
		String str = "1 * 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMultiply0by1() {
		String str = "0 * 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMultiply1by0() {
		String str = "1 * 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMultiply1by1Comma1() {
		String str = "1 * 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMultiply0Comma5by1Comma1() {
		String str = "0,5 * 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMultiply10000Comma01ByMinus10() {
		String str = "10000,01 * -10";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDivide1by0WithoutBlank() {
		String str = "1/0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDivide1by1WithBlank() {
		String str = "1 / 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDivide1by0Comma5() {
		String str = "1 / 0,5";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDivide0Comma5By2() {
		String str = "0,5 / 2";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDivide0Comma5By0Comma5() {
		String str = "0,5 / 0,5";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryIntegerDivision1by0WithoutBlank() {
		String str = "1//0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryIntegerDivision2by1WithBlank() {
		String str = "2 // 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryIntegerDivision0by1() {
		String str = "0 // 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryModulo1by0WithoutBlank() {
		String str = "1\\\\0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryModulo3by2WithBlank() {
		String str = "3 \\\\ 2";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryModulo0by1() {
		String str = "0 \\\\ 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlus1And1WithoutBlank() {
		String str = "1+1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlus1And1WithBlank() {
		String str = "1 + 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlus1Comma1And1Comma1WithoutBlank() {
		String str = "1,1+1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlus1Comma1And1Comma1WithBlank() {
		String str = "1,1 + 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlusABAndCWithoutBlank() {
		String str = "\"ab\"+\"c\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlusABAndCWithBlank() {
		String str = "\"ab\" + \"c\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlusDateAndDuration() {
		String str = "'01/01/2016' + 'P1D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlusDurationAndDate() {
		String str = "'P1D' + '01/01/2016'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryPlusBothDuration() {
		String str = "'P1D' + 'P1D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinus1And1WithoutBlank() {
		String str = "1-1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinus1And1WithBlank() {
		String str = "1 - 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinus1Comma1And1Comma1WithoutBlank() {
		String str = "1,1-1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinus1Comma1And1Comma1WithBlank() {
		String str = "1,1 - 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinus1And1Comma1WithBlank() {
		String str = "1 - 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinus1Comma1And1WithBlank() {
		String str = "1,1 - 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinus1And2() {
		String str = "1 - 2";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinusDateAndDuration() {
		String str = "'01/01/2016' - 'P1D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinusBothDuration() {
		String str = "'P2D' - 'P1D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryMinusBothDurationSecondGreaterThanFirst() {
		String str = "'P1D' - 'P2D'";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryEqual1And1WithoutBlank() {
		String str = "1=1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryEqual1And1WithBlank() {
		String str = "1 = 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryEqual1And0() {
		String str = "1 = 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryEqual1Comma1And1Comma1() {
		String str = "1,1 = 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryEqual1Comma1And0() {
		String str = "1 = 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryEqualABCAndABC() {
		String str = "\"abc\" = \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryEqualABCAndAB() {
		String str = "\"abc\" = \"ab\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDifference1And1WithoutBlank() {
		String str = "1<>1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDifference1And1WithBlank() {
		String str = "1 <> 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDifference1And0() {
		String str = "1 <> 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDifference1Comma1And1Comma1() {
		String str = "1,1 <> 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDifference1Comma1And0() {
		String str = "1 <> 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDifferenceABCAndABC() {
		String str = "\"abc\" <> \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryDifferenceABCAndAB() {
		String str = "\"abc\" <> \"ab\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmaller1And1WithoutBlank() {
		String str = "1<1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmaller1And1WithBlank() {
		String str = "1 < 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmaller1And0() {
		String str = "1 < 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmaller0And1() {
		String str = "0 < 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmaller1Comma1And1Comma1() {
		String str = "1,1 < 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmaller1Comma1And0() {
		String str = "1,1 < 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmaller0And1Comma1() {
		String str = "0 < 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerABCAndABC() {
		String str = "\"abc\" < \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerABCAndAB() {
		String str = "\"abc\" < \"ab\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerABAndABC() {
		String str = "\"ab\" < \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqual1And1WithoutBlank() {
		String str = "1<=1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqual1And1WithBlank() {
		String str = "1 <= 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqual1And0() {
		String str = "1 <= 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqual0And1() {
		String str = "0 <= 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqual1Comma1And1Comma1() {
		String str = "1,1 <= 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqual1Comma1And0() {
		String str = "1,1 <= 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqual0And1Comma1() {
		String str = "0 <= 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqualABCAndABC() {
		String str = "\"abc\" <= \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqualABCAndAB() {
		String str = "\"abc\" <= \"ab\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinarySmallerOrEqualABAndABC() {
		String str = "\"ab\" <= \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreater1And1WithoutBlank() {
		String str = "1>1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreater1And1WithBlank() {
		String str = "1 > 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreater1And0() {
		String str = "1 > 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreater0And1() {
		String str = "0 > 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreater1Comma1And1Comma1() {
		String str = "1,1 > 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreater1Comma1And0() {
		String str = "1,1 > 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreater0And1Comma1() {
		String str = "0 > 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterABCAndABC() {
		String str = "\"abc\" > \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterABCAndAB() {
		String str = "\"abc\" > \"ab\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterABAndABC() {
		String str = "\"ab\" > \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqual1And1WithoutBlank() {
		String str = "1>=1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqual1And1WithBlank() {
		String str = "1 >= 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqual1And0() {
		String str = "1 >= 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqual0And1() {
		String str = "0 >= 1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqual1Comma1And1Comma1() {
		String str = "1,1 >= 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqual1Comma1And0() {
		String str = "1,1 >= 0";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqual0And1Comma1() {
		String str = "0 >= 1,1";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqualABCAndABC() {
		String str = "\"abc\" >= \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqualABCAndAB() {
		String str = "\"abc\" >= \"ab\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryGreaterOrEqualABAndABC() {
		String str = "\"ab\" >= \"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryAndTrueAndTrue() {
		String str = "true and true";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryAndTrueAndFalse() {
		String str = "true and false";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryAndFalseAndFalse() {
		String str = "false and false";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryOrTrueAndTrue() {
		String str = "true or true";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryOrTrueAndFalse() {
		String str = "true or false";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBinaryOrFalseAndFalse() {
		String str = "false or false";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBracket1WithoutBlank() {
		String str = "(1)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBracket1WithBlank() {
		String str = "( 1 )";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBracket1Comma1() {
		String str = "(1,1)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBracketABC() {
		String str = "(\"abc\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBracketTrue() {
		String str = "(true)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseIn1And1() {
		String str = "1 in (1)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseIn1And123() {
		String str = "1 in (1;2;3)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseIn1And23() {
		String str = "1 in (2;3)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseIn1Comma1And1Comma1() {
		String str = "1,1 in (1,1)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseIn1Comma1And1Comma1And2Comma2And3Comma3() {
		String str = "1,1 in (1,1;2,2;3,3)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseIn1Comma1And2Comma2And3Comma3() {
		String str = "1,1 in (2,2;3,3)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseInAAndA() {
		String str = "\"a\" in (\"a\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseInAAndABC() {
		String str = "\"a\" in (\"a\";\"b\";\"c\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseInAAndBC() {
		String str = "\"a\" in (\"b\";\"c\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseCeil1Comma111Precision0Comma01() {
		String str = "ceil(1,111 ; 0,01)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseFloor1Comma111Precision0Comma01() {
		String str = "floor(1,111 ; 0,01)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseRound1Comma111Precision0Comma01() {
		String str = "rounded(1,111 ; 0,01)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseTrunc1Comma111Precision2() {
		String str = "trunc(1,111 ; 2)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBankersRounded1Comma111Precision2() {
		String str = "bankers_rounded(1,111 ; 2)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseCeil1Comma111NoPrecision() {
		String str = "ceil(1,111)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseFloor1Comma111NoPrecision() {
		String str = "floor(1,111)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseRound1Comma111NoPrecision() {
		String str = "rounded(1,111)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseTrunc1Comma111NoPrecision() {
		String str = "trunc(1,111)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseBankersRounded1Comma111NoPrecision() {
		String str = "bankers_rounded(1,111)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseFormatDate() {
		String str = "formatDate('01/01/2016' ; \"yyyyMMdd\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseFormatInteger() {
		String str = "formatInteger(1000 ; 10 ; \"NONE\" ; \"NONE\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseFormatNumeric() {
		String str = "formatNumeric(1000,0 ; 10 ; 2 ; \"NONE\" ; \"NONE\" ; \",\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseFormatString() {
		String str = "formatString(\"abc\" ; 5 ; \"CENTER\" ; \"\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseStringItem() {
		String str = "stringItem(\"a/ab/abc\" ; \"/\" ; 2)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseSubString() {
		String str = "substring(\"abcdef\" ; 1 ; 3)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMax() {
		String str = "max(1 ; 2 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseSignedMax() {
		String str = "smax(1 ; 2 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMinusSignedMax() {
		String str = "smax(-1 ; 2 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMaxDouble() {
		String str = "max(1 ; 2,0 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseSignedMaxDouble() {
		String str = "smax(1 ; 2,0 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMinusSignedMaxDouble() {
		String str = "smax(-1 ; 2,0 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMin() {
		String str = "min(1 ; 2 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseSignedMin() {
		String str = "smin(1 ; 2 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMinusSignedMin() {
		String str = "smin(-1 ; 2 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMinDouble() {
		String str = "min(1 ; 2,0 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseSignedMinDouble() {
		String str = "smin(1 ; 2,0 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMinusSignedMinDouble() {
		String str = "smin(-1 ; 2,0 ; 3 ; -4)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseFormulaDateString() {
		String str = "date(\"01/01/2016\")";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseFormulaDateParts() {
		String str = "date(1 ; 1 ; 2016)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMinutesInteger() {
		String str = "minutes(10)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMinutesDuration() {
		String str = "minutes('PT10M')";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMinutesHours() {
		String str = "minutes(hours(2))";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseHoursInteger() {
		String str = "hours(10)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseHoursDuration() {
		String str = "hours('PT10H')";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseHoursMinutes() {
		String str = "hours(minutes(120))";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDaysInteger() {
		String str = "days(2)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDaysDuration() {
		String str = "days('P2D')";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDaysHours() {
		String str = "days(hours(24))";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseDaysDate() {
		String str = "days('02/01/2016')";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMonthsInteger() {
		String str = "months(2)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMonthsDuration() {
		String str = "months('P2M')";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMonthsDays() {
		String str = "months(days(31))";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseMonthsDate() {
		String str = "months('01/02/2016')";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseYearsInteger() {
		String str = "years(2)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseYearsDuration() {
		String str = "years('P2Y')";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseYearMonths() {
		String str = "years(months(12))";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseYearsDate() {
		String str = "years('01/02/2016')";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseAbsPositiveInteger() {
		String str = "abs(10)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseAbsNegativeInteger() {
		String str = "abs (-10)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseAbsPositiveNumeric() {
		String str = "abs(10,0)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseAbsNegativeNumeric() {
		String str = "abs (-10,0)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseSignPositiveInteger() {
		String str = "sign(10)";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaMathSign);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
		assertTrue(semanticalAction.getFormula().getIntegerValue() > 0);
	}
	
	/**
	 * Tests {@link SlangTab#analyze()} with string "sign (-10)"
	 */
	@Test
	public void testParseSignNegativeInteger() {
		String str = "sign (-10)";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaMathSign);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
		assertTrue(semanticalAction.getFormula().getIntegerValue() < 0);
	}
	
	/**
	 * Tests {@link SlangTab#analyze()} with string "sign(0)"
	 */
	@Test
	public void testParseSignZeroInteger() {
		String str = "sign(0)";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaMathSign);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
		assertTrue(semanticalAction.getFormula().getIntegerValue() == 0);
	}
	
	/**
	 * Tests {@link SlangTab#analyze()} with string "sign(+0)"
	 */
	@Test
	public void testParseSignPositiveZeroInteger() {
		String str = "sign(0)";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaMathSign);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
		assertTrue(semanticalAction.getFormula().getIntegerValue() == 0);
	}
	
	/**
	 * Tests {@link SlangTab#analyze()} with string "sign(-0)"
	 */
	@Test
	public void testParseSignNegativeZeroInteger() {
		String str = "sign(-0)";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaMathSign);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormula().getReturnType());
		assertTrue(semanticalAction.getFormula().getIntegerValue() < 0);
	}
	
	/**
	 * Tests {@link SlangTab#analyze()} with string "sign (10,0)"
	 */
	@Test
	public void testParseSignPositiveNumeric() {
		String str = "sign(10,0)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseSignNegativeNumeric() {
		String str = "sign (-10,0)";
		SemanticalAction semanticalAction = new AsStandard();
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
	public void testParseAnomalyIntegerId() {
		String str = "put_text (1000)";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaAnomaly);
		assertEquals(FormulaReturnType.PROCEDURE, semanticalAction.getFormula().getReturnType());
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
	public void testParseAnomalyStringId() {
		String str = "put_text (\"1000\")";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaAnomaly);
		assertEquals(FormulaReturnType.PROCEDURE, semanticalAction.getFormula().getReturnType());
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
	public void testParseAnomalyIntegerIdAndMessage() {
		String str = "put_text (1000 ; \"abc\")";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaAnomaly);
		assertEquals(FormulaReturnType.PROCEDURE, semanticalAction.getFormula().getReturnType());
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
	public void testParseAnomalyStringIdAndMessage() {
		String str = "put_text (\"1000\" ; \"abc\")";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormula());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormula().getDescription().getName(),
				semanticalAction.getFormula() instanceof FormulaAnomaly);
		assertEquals(FormulaReturnType.PROCEDURE, semanticalAction.getFormula().getReturnType());
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
	public void testParseStringLenght() {
		String str = "stringLength(\"abc\")";
		SemanticalAction semanticalAction = new AsStandard();
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
