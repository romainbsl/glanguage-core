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
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaIn;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDivide;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaIntegerDivision;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaModulo;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMultiply;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction.FormulaIfInstruction;
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

}
