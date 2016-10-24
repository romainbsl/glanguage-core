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
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaBracket;
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
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction.FormulaIfInstruction;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingArithmetic;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingBankers;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingCeil;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingFloor;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingTrunc;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringItem;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalBoolean;
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue(semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalBoolean);
		// assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof Boolean);
		assertEquals(true, semanticalAction.getFormulaList().get(0).getBooleanValue());
	}
	
	@Test
	public void testParseBooleanFalse() {
		String str = "false";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue(semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalBoolean);
		// assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof Boolean);
		assertEquals(false, semanticalAction.getFormulaList().get(0).getBooleanValue());
	}
	
	@Test
	public void testParseInteger() {
		String str = "0";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue(semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalInteger);
		// assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof Integer);
		assertEquals(new Integer(0), semanticalAction.getFormulaList().get(0).getIntegerValue());
	}
	
	@Test
	public void testParseDouble() {
		String str = "0,0";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue(semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalNumeric);
		// assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof Double);
		assertEquals(new Double(0.0), semanticalAction.getFormulaList().get(0).getNumericValue());
	}
	
	@Test
	public void testParseString() {
		String str = "\"abc\"";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalString);
		// assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("abc", semanticalAction.getFormulaList().get(0).getStringValue());
	}
	
	@Test
	public void testParseIntegerAsString() {
		String str = "\"0\"";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue(semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalString);
		// assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("0", semanticalAction.getFormulaList().get(0).getStringValue());
	}
	
	@Test
	public void testParseDoubleDotAsString() {
		String str = "\"0.0\"";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue(semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalString);
		// assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("0.0", semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue(semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalString);
		// assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("0,0", semanticalAction.getFormulaList().get(0).getStringValue());
	}
	
	@Test
	public void testParseDate() {
		String str = "'31/12/2015'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDate);
		// assertEquals(FormulaReturnType.DATE, semanticalAction.getFormulaList().get(0).getReturnType());
		// assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof LocalDate);
		assertEquals(LocalDate.of(2015, 12, 31), semanticalAction.getFormulaList().get(0).getDateValue());
	}
	
	@Test
	public void testParseDurationAll() {
		String str = "'P1Y1M1DT1H1M1.1S'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		// Object value = semanticalAction.getFormulaList().get(0).getValue();
		// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
		Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
		assertEquals(397, ((Duration) value).toDays());
		
	}
	
	@Test
	public void testParseDurationPeriodAll() {
		String str = "'P1Y1M1D'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		// Object value = semanticalAction.getFormulaList().get(0).getValue();
		// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
		Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
		assertEquals(397, ((Duration) value).toDays());
		
	}
	
	@Test
	public void testParseDurationPeriodOnlyYears() {
		String str = "'P1Y'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		try {
			// Object value = semanticalAction.getFormulaList().get(0).getValue();
			// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
			Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
			assertEquals(365, ((Duration) value).toDays());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testParseDurationPeriodOnlyMonths() {
		String str = "'P1M'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		try {
			// Object value = semanticalAction.getFormulaList().get(0).getValue();
			// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
			Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
			assertEquals(31, ((Duration) value).toDays());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testParseDurationPeriodOnlyDays() {
		String str = "'P1D'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		try {
			// Object value = semanticalAction.getFormulaList().get(0).getValue();
			// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
			Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
			assertEquals(1, ((Duration) value).toDays());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testParseDurationPeriodOnlyYearsAndMonths() {
		String str = "'P1Y1M'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		try {
			// Object value = semanticalAction.getFormulaList().get(0).getValue();
			// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
			Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
			assertEquals(396, ((Duration) value).toDays());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testParseDurationPeriodOnlyYearsAndDays() {
		String str = "'P1Y1D'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		try {
			// Object value = semanticalAction.getFormulaList().get(0).getValue();
			// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
			Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
			assertEquals(366, ((Duration) value).toDays());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testParseDurationDurationAll() {
		String str = "'P1DT1H1M1.1S'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		// Object value = semanticalAction.getFormulaList().get(0).getValue();
		// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
		Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
		assertEquals(1, ((Duration) value).toDays());
		assertEquals(25, ((Duration) value).toHours());
		assertEquals(1501, ((Duration) value).toMinutes());
		assertEquals(90061, ((Duration) value).toMillis() / 1000);
		assertEquals(90061100, ((Duration) value).toMillis());
	}
	
	@Test
	public void testParseDurationDurationOnlyDays() {
		String str = "'P1D'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		// Object value = semanticalAction.getFormulaList().get(0).getValue();
		// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
		Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
		assertEquals(1, ((Duration) value).toDays());
		assertEquals(24, ((Duration) value).toHours());
		assertEquals(1440, ((Duration) value).toMinutes());
	}
	
	@Test
	public void testParseDurationDurationOnlyHours() {
		String str = "'PT1H'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		// Object value = semanticalAction.getFormulaList().get(0).getValue();
		// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
		Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
		assertEquals(0, ((Duration) value).toDays());
		assertEquals(1, ((Duration) value).toHours());
		assertEquals(60, ((Duration) value).toMinutes());
	}
	
	@Test
	public void testParseDurationDurationOnlyMinutes() {
		String str = "'PT1M'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		// Object value = semanticalAction.getFormulaList().get(0).getValue();
		// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
		Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
		assertEquals(0, ((Duration) value).toDays());
		assertEquals(0, ((Duration) value).toHours());
		assertEquals(1, ((Duration) value).toMinutes());
	}
	
	@Test
	public void testParseDurationDurationOnlySeconds() {
		String str = "'PT1S'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		// Object value = semanticalAction.getFormulaList().get(0).getValue();
		// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
		Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
		assertEquals(0, ((Duration) value).toDays());
		assertEquals(0, ((Duration) value).toHours());
		assertEquals(0, ((Duration) value).toMinutes());
		assertEquals(1, ((Duration) value).toMillis() / 1000);
	}
	
	@Test
	public void testParseDurationDurationOnlyMillis() {
		String str = "'PT0.1S'";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		// assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
		// semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		// assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		// Object value = semanticalAction.getFormulaList().get(0).getValue();
		// assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
		Duration value = semanticalAction.getFormulaList().get(0).getDurationValue();
		assertEquals(0, ((Duration) value).toDays());
		assertEquals(0, ((Duration) value).toHours());
		assertEquals(0, ((Duration) value).toMinutes());
		assertEquals(0, ((Duration) value).toMillis() / 1000);
		assertEquals(100, ((Duration) value).toMillis());
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
	
	// TODO reactivate tests when getReturnType() method, needed by constructor of FormulaIfInstruction, is
	
	@Test
	public void testComplexIf1() {
		String n = "1";
		String str = getComplexIf(n);
		
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str.toString());
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaIfInstruction);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("if-if", semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaIfInstruction);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("if-elseif", semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaIfInstruction);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("if-elseif-if", semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaIfInstruction);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("if-elseif-else", semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaIfInstruction);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("if-else", semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaIfInstruction);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("else", semanticalAction.getFormulaList().get(0).getStringValue());
	}
	
	@Test
	public void testGet() {
		String str = "get string contrat.nature()";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaGet);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
	}
	
	@Test
	public void testParseIn() {
		String str = "r1 in (2;3)";
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(str);
		parser.analyze();
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaNot);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaNot);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaNot);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaNot);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaUnaryMinus);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(-1), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaUnaryMinus);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(-1), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaUnaryMinus);
		assertTrue(
				"Sub-formula object type not expected : "
						+ semanticalAction.getFormulaList().get(0).getParameters().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaUnaryMinus);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(1), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMultiply);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(0), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMultiply);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(1), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMultiply);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(0), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMultiply);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(0), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMultiply);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.1), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMultiply);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(0.55), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMultiply);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(-100000.1), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDivide);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getNumericValue().isInfinite());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDivide);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.0), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDivide);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(2.0), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDivide);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(0.25), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDivide);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.0), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIntegerDivision);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		semanticalAction.getFormulaList().get(0).getIntegerValue();
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIntegerDivision);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(2), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIntegerDivision);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(0), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaModulo);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		semanticalAction.getFormulaList().get(0).getIntegerValue();
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaModulo);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(1), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaModulo);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(0), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(2), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(2), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(2.2), semanticalAction.getFormulaList().get(0).getNumericValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(2.2), semanticalAction.getFormulaList().get(0).getNumericValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new String("abc"), semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new String("abc"), semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.DATE, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(LocalDate.of(2016, 1, 2), semanticalAction.getFormulaList().get(0).getDateValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.DATE, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(LocalDate.of(2016, 1, 2), semanticalAction.getFormulaList().get(0).getDateValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaPlus);
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(Duration.ofDays(2), semanticalAction.getFormulaList().get(0).getDurationValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(0), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(0), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(0.0), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(0.0), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(-0.1), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(0.1), semanticalAction.getFormulaList().get(0).getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(-1), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.DATE, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(LocalDate.of(2015, 12, 31), semanticalAction.getFormulaList().get(0).getDateValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(Duration.ofDays(1), semanticalAction.getFormulaList().get(0).getDurationValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaMinus);
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(Duration.ofDays(-1), semanticalAction.getFormulaList().get(0).getDurationValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDifference);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDifference);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDifference);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDifference);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDifference);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDifference);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaDifference);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmaller);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaSmallerOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreater);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaGreaterOrEqual);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaAnd);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaAnd);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaAnd);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaOr);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaOr);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaOr);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaBracket);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(1), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaBracket);
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Integer(1), semanticalAction.getFormulaList().get(0).getIntegerValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaBracket);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.1), semanticalAction.getFormulaList().get(0).getNumericValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaBracket);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new String("abc"), semanticalAction.getFormulaList().get(0).getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(1, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaBracket);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(2, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(4, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(2, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(4, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(2, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(4, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertTrue(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaIn);
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().getLast().getReturnType());
		assertFalse(semanticalAction.getFormulaList().getLast().getBooleanValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingCeil);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.12), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingFloor);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.11), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingArithmetic);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.11), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingTrunc);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.11), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingBankers);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.11), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(2, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingCeil);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(2.0), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(2, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingFloor);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.0), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(2, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingArithmetic);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.0), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(2, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingTrunc);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.11), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(2, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaRoundingBankers);
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new Double(1.11), semanticalAction.getFormulaList().getLast().getNumericValue(), DELTA);
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(3, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaFormatDate);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new String("20160101"), semanticalAction.getFormulaList().getLast().getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(5, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaFormatInteger);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new String("1000"), semanticalAction.getFormulaList().getLast().getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(7, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaFormatNumeric);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new String("1000,00"), semanticalAction.getFormulaList().getLast().getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(5, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaFormatString);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new String(" abc "), semanticalAction.getFormulaList().getLast().getStringValue());
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
		assertNotNull(semanticalAction.getFormulaList());
		assertFalse(semanticalAction.getFormulaList().isEmpty());
		assertEquals(4, semanticalAction.getFormulaList().size());
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().getLast().getDescription().getName(),
				semanticalAction.getFormulaList().getLast() instanceof FormulaStringItem);
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().getLast().getReturnType());
		assertEquals(new String("ab"), semanticalAction.getFormulaList().getLast().getStringValue());
	}

}
