package be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaIfInstruction}
 * 
 * @author DUPIREFR
 */
public class FormulaIfInstructionTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaIfInstruction#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaIfInstruction formula = new FormulaIfInstruction();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getIntegerValue()} when condition is true
	 */
	@Test
	public void testGetIntegerValueCondTrue() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(ifStatement.getIntegerValue()).thenReturn(2);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(elseStatement.getIntegerValue()).thenReturn(1);
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getIntegerValue()} when condition is false
	 */
	@Test
	public void testGetIntegerValueCondFalse() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(ifStatement.getIntegerValue()).thenReturn(2);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(elseStatement.getIntegerValue()).thenReturn(1);
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getIntegerValue()} when condition is false for first if,
	 * but true for second
	 */
	@Test
	public void testGetIntegerValueCondFalseCondTrue() {
		AbstractFormula subCondition = mock(AbstractFormula.class);
		when(subCondition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(subCondition.getBooleanValue()).thenReturn(true);
		
		AbstractFormula subIfStatement = mock(AbstractFormula.class);
		when(subIfStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(subIfStatement.getIntegerValue()).thenReturn(2);
		
		AbstractFormula subElseStatement = mock(AbstractFormula.class);
		when(subElseStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(subElseStatement.getIntegerValue()).thenReturn(1);
		
		FormulaIfInstruction subFormula = new FormulaIfInstruction(null, subCondition, subIfStatement, subElseStatement);
		
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.getIntegerValue()).thenReturn(3);
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, subFormula);
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getNumericValue()} when condition is true
	 */
	@Test
	public void testGetNumericValueCondTrue() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(ifStatement.getNumericValue()).thenReturn(2.5);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(elseStatement.getNumericValue()).thenReturn(1.5);
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(Double.valueOf(2.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getNumericValue()} when condition is false
	 */
	@Test
	public void testGetNumericValueCondFalse() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(ifStatement.getNumericValue()).thenReturn(2.5);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(elseStatement.getNumericValue()).thenReturn(1.5);
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(Double.valueOf(1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getStringValue()} when condition is true
	 */
	@Test
	public void testGetStringValueCondTrue() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.getStringValue()).thenReturn("condIsTrue");
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(elseStatement.getStringValue()).thenReturn("condIsFalse");
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals("condIsTrue", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getStringValue()} when condition is false
	 */
	@Test
	public void testGetStringValueCondFalse() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.getStringValue()).thenReturn("condIsTrue");
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(elseStatement.getStringValue()).thenReturn("condIsFalse");
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals("condIsFalse", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getBooleanValue()} when condition is true
	 */
	@Test
	public void testGetBooleanValueCondTrue() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(ifStatement.getBooleanValue()).thenReturn(true);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(elseStatement.getBooleanValue()).thenReturn(false);
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getBooleanValue()} when condition is false
	 */
	@Test
	public void testGetBooleanValueCondFalse() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(ifStatement.getBooleanValue()).thenReturn(true);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(elseStatement.getBooleanValue()).thenReturn(false);
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getDateValue()} when condition is true
	 */
	@Test
	public void testGetDateValueCondTrue() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(ifStatement.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(elseStatement.getDateValue()).thenReturn(LocalDate.of(2014, 1, 1));
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getDateValue()} when condition is false
	 */
	@Test
	public void testGetDateValueCondFalse() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(ifStatement.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(elseStatement.getDateValue()).thenReturn(LocalDate.of(2014, 1, 1));
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(LocalDate.of(2014, 1, 1), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getDurationValue()} when condition is true
	 */
	@Test
	public void testGetDurationValueCondTrue() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(ifStatement.getDurationValue()).thenReturn(Duration.ofDays(2L));
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(elseStatement.getDurationValue()).thenReturn(Duration.ofDays(3L));
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(Duration.ofDays(2L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getDurationValue()} when condition is false
	 */
	@Test
	public void testGetDurationValueCondFalse() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue()).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(ifStatement.getDurationValue()).thenReturn(Duration.ofDays(2L));
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(elseStatement.getDurationValue()).thenReturn(Duration.ofDays(3L));
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals(Duration.ofDays(3L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#asText()} with only one if
	 */
	@Test
	public void testAsTextOneIf() {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.asText()).thenReturn("some_cond");
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.asText()).thenReturn("some_rule1");
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(elseStatement.asText()).thenReturn("some_rule2");
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, elseStatement);
		
		assertEquals("if some_cond then\n\tsome_rule1\nelse\n\tsome_rule2\nend", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#asText()} with a second if
	 */
	@Test
	public void testAsTextTwoIfs() {
		AbstractFormula subCondition = mock(AbstractFormula.class);
		when(subCondition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(subCondition.asText()).thenReturn("some_cond2");
		
		AbstractFormula subIfStatement = mock(AbstractFormula.class);
		when(subIfStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(subIfStatement.asText()).thenReturn("some_rule2");
		
		AbstractFormula subElseStatement = mock(AbstractFormula.class);
		when(subElseStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(subElseStatement.asText()).thenReturn("some_rule3");
		
		FormulaIfInstruction subFormula = new FormulaIfInstruction(null, subCondition, subIfStatement, subElseStatement);
		
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.asText()).thenReturn("some_cond1");
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.asText()).thenReturn("some_rule1");
		
		FormulaIfInstruction formula = new FormulaIfInstruction(null, condition, ifStatement, subFormula);
		
		assertEquals("if some_cond1 then\n\tsome_rule1\nelseif some_cond2 then\n\tsome_rule2\nelse\n\tsome_rule3\nend",
				formula.asText());
	}
	
}
