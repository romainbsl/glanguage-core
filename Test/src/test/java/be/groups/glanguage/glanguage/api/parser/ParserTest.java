package be.groups.glanguage.glanguage.api.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.action.standard.AsStandard;
import be.groups.glanguage.glanguage.api.business.analysis.byaccj.SlangTab;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalBoolean;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDuration;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalString;

public class ParserTest {
	
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
		assertEquals(FormulaDescription.TERMINAL_BOOLEAN, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof Boolean);
		assertEquals(true, semanticalAction.getFormulaList().get(0).getValue());
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
		assertEquals(FormulaDescription.TERMINAL_BOOLEAN, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.BOOLEAN, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof Boolean);
		assertEquals(false, semanticalAction.getFormulaList().get(0).getValue());
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
		assertEquals(FormulaDescription.TERMINAL_INTEGER, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.INTEGER, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof Integer);
		assertEquals(0, semanticalAction.getFormulaList().get(0).getValue());
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
		assertEquals(FormulaDescription.TERMINAL_NUMERIC, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.NUMERIC, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof Double);
		assertEquals(0.0, semanticalAction.getFormulaList().get(0).getValue());
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalString);
		assertEquals(FormulaDescription.TERMINAL_STRING, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("abc", semanticalAction.getFormulaList().get(0).getValue());
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
		assertEquals(FormulaDescription.TERMINAL_STRING, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("0", semanticalAction.getFormulaList().get(0).getValue());
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
		assertEquals(FormulaDescription.TERMINAL_STRING, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("0.0", semanticalAction.getFormulaList().get(0).getValue());
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
		assertEquals(FormulaDescription.TERMINAL_STRING, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.STRING, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof String);
		assertEquals("0,0", semanticalAction.getFormulaList().get(0).getValue());
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDate);
		assertEquals(FormulaDescription.TERMINAL_DATE, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DATE, semanticalAction.getFormulaList().get(0).getReturnType());
		assertTrue(semanticalAction.getFormulaList().get(0).getValue() instanceof LocalDate);
		assertEquals(LocalDate.of(2015, 12, 31), semanticalAction.getFormulaList().get(0).getValue());
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		Object value = semanticalAction.getFormulaList().get(0).getValue();
		assertTrue("Value type not expected : " + value.getClass().getName(),
				value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		Object value = semanticalAction.getFormulaList().get(0).getValue();
		assertTrue("Value type not expected : " + value.getClass().getName(),
				value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		try {
			Object value = semanticalAction.getFormulaList().get(0).getValue();
			assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
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
    	assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
    			semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
    	assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
    	assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
    	try {
    		Object value = semanticalAction.getFormulaList().get(0).getValue();
    		assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
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
    	assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
    			semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
    	assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
    	assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
    	try {
    		Object value = semanticalAction.getFormulaList().get(0).getValue();
    		assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		try {
			Object value = semanticalAction.getFormulaList().get(0).getValue();
			assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
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
    	assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
    			semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
    	assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
    	assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
    	try {
    		Object value = semanticalAction.getFormulaList().get(0).getValue();
    		assertTrue("Value type not expected : " + value.getClass().getName(), value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		Object value = semanticalAction.getFormulaList().get(0).getValue();
		assertTrue("Value type not expected : " + value.getClass().getName(),
				value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		Object value = semanticalAction.getFormulaList().get(0).getValue();
		assertTrue("Value type not expected : " + value.getClass().getName(),
				value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		Object value = semanticalAction.getFormulaList().get(0).getValue();
		assertTrue("Value type not expected : " + value.getClass().getName(),
				value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		Object value = semanticalAction.getFormulaList().get(0).getValue();
		assertTrue("Value type not expected : " + value.getClass().getName(),
				value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		Object value = semanticalAction.getFormulaList().get(0).getValue();
		assertTrue("Value type not expected : " + value.getClass().getName(),
				value instanceof Duration);
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
		assertTrue("Formula object type not expected : " + semanticalAction.getFormulaList().get(0).getDescription().name(),
				semanticalAction.getFormulaList().get(0) instanceof FormulaTerminalDuration);
		assertEquals(FormulaDescription.TERMINAL_DURATION, semanticalAction.getFormulaList().get(0).getDescription());
		assertEquals(FormulaReturnType.DURATION, semanticalAction.getFormulaList().get(0).getReturnType());
		Object value = semanticalAction.getFormulaList().get(0).getValue();
		assertTrue("Value type not expected : " + value.getClass().getName(),
				value instanceof Duration);
		assertEquals(0, ((Duration) value).toDays());
		assertEquals(0, ((Duration) value).toHours());
		assertEquals(0, ((Duration) value).toMinutes());
		assertEquals(0, ((Duration) value).toMillis() / 1000);
		assertEquals(100, ((Duration) value).toMillis());
	}

}
