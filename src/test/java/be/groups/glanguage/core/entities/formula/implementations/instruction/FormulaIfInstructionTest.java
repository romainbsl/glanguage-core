package be.groups.glanguage.core.entities.formula.implementations.instruction;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

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
	 * Tests {@link FormulaIfInstruction#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaIfInstruction formula = new FormulaIfInstruction();
		
		assertEquals(Integer.valueOf(FormulaType.Values.I_IF), formula.getDiscriminatorValue());
	}
	
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
	public void testGetIntegerValueCondTrue() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(ifStatement.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(elseStatement.getIntegerValue(null)).thenReturn(1);
		
		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getIntegerValue()} when condition is false
	 */
	@Test
	public void testGetIntegerValueCondFalse() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(ifStatement.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(elseStatement.getIntegerValue(null)).thenReturn(1);

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getIntegerValue()} when condition is false for first if,
	 * but true for second
	 */
	@Test
	public void testGetIntegerValueCondFalseCondTrue() throws GLanguageException {
		AbstractFormula subCondition = mock(AbstractFormula.class);
		when(subCondition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(subCondition.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula subIfStatement = mock(AbstractFormula.class);
		when(subIfStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(subIfStatement.getIntegerValue(null)).thenReturn(2);
		
		AbstractFormula subElseStatement = mock(AbstractFormula.class);
		when(subElseStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(subElseStatement.getIntegerValue(null)).thenReturn(1);
		
		FormulaIfInstruction subFormula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(subCondition, subIfStatement, subElseStatement)).when(subFormula).getParameters();

		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.getIntegerValue(null)).thenReturn(3);

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, subFormula)).when(formula).getParameters();

		assertEquals(Integer.valueOf(2), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getNumericValue()} when condition is true
	 */
	@Test
	public void testGetNumericValueCondTrue() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(ifStatement.getNumericValue(null)).thenReturn(2.5);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(elseStatement.getNumericValue(null)).thenReturn(1.5);

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(Double.valueOf(2.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getNumericValue()} when condition is false
	 */
	@Test
	public void testGetNumericValueCondFalse() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(ifStatement.getNumericValue(null)).thenReturn(2.5);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(elseStatement.getNumericValue(null)).thenReturn(1.5);

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getStringValue()} when condition is true
	 */
	@Test
	public void testGetStringValueCondTrue() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.getStringValue(null)).thenReturn("condIsTrue");
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(elseStatement.getStringValue(null)).thenReturn("condIsFalse");

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals("condIsTrue", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getStringValue()} when condition is false
	 */
	@Test
	public void testGetStringValueCondFalse() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.getStringValue(null)).thenReturn("condIsTrue");
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(elseStatement.getStringValue(null)).thenReturn("condIsFalse");

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals("condIsFalse", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getBooleanValue()} when condition is true
	 */
	@Test
	public void testGetBooleanValueCondTrue() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(ifStatement.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(elseStatement.getBooleanValue(null)).thenReturn(false);

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getBooleanValue()} when condition is false
	 */
	@Test
	public void testGetBooleanValueCondFalse() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(ifStatement.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(elseStatement.getBooleanValue(null)).thenReturn(false);

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getDateValue()} when condition is true
	 */
	@Test
	public void testGetDateValueCondTrue() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(ifStatement.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(elseStatement.getDateValue(null)).thenReturn(LocalDate.of(2014, 1, 1));

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getDateValue()} when condition is false
	 */
	@Test
	public void testGetDateValueCondFalse() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(ifStatement.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(elseStatement.getDateValue(null)).thenReturn(LocalDate.of(2014, 1, 1));

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(LocalDate.of(2014, 1, 1), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getDurationValue()} when condition is true
	 */
	@Test
	public void testGetDurationValueCondTrue() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(ifStatement.getDurationValue(null)).thenReturn(Duration.ofDays(2L));
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(elseStatement.getDurationValue(null)).thenReturn(Duration.ofDays(3L));

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(Duration.ofDays(2L), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#getDurationValue()} when condition is false
	 */
	@Test
	public void testGetDurationValueCondFalse() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(ifStatement.getDurationValue(null)).thenReturn(Duration.ofDays(2L));
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(elseStatement.getDurationValue(null)).thenReturn(Duration.ofDays(3L));

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals(Duration.ofDays(3L), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#asText()} with only one if
	 */
	@Test
	public void testAsTextOneIf() throws GLanguageException {
		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.asText()).thenReturn("some_cond");
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.asText()).thenReturn("some_rule1");
		
		AbstractFormula elseStatement = mock(AbstractFormula.class);
		when(elseStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(elseStatement.asText()).thenReturn("some_rule2");

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, elseStatement)).when(formula).getParameters();

		assertEquals("if some_cond then\n\tsome_rule1\nelse\n\tsome_rule2\nend", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaIfInstruction#asText()} with a second if
	 */
	@Test
	public void testAsTextTwoIfs() throws GLanguageException {
		AbstractFormula subCondition = mock(AbstractFormula.class);
		when(subCondition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(subCondition.asText()).thenReturn("some_cond2");
		
		AbstractFormula subIfStatement = mock(AbstractFormula.class);
		when(subIfStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(subIfStatement.asText()).thenReturn("some_rule2");
		
		AbstractFormula subElseStatement = mock(AbstractFormula.class);
		when(subElseStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(subElseStatement.asText()).thenReturn("some_rule3");

		FormulaIfInstruction subFormula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(subCondition, subIfStatement, subElseStatement)).when(subFormula).getParameters();

		AbstractFormula condition = mock(AbstractFormula.class);
		when(condition.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(condition.asText()).thenReturn("some_cond1");
		
		AbstractFormula ifStatement = mock(AbstractFormula.class);
		when(ifStatement.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(ifStatement.asText()).thenReturn("some_rule1");

		FormulaIfInstruction formula = Mockito.spy(FormulaIfInstruction.class);
		doReturn(Arrays.asList(condition, ifStatement, subFormula)).when(formula).getParameters();

		assertEquals("if some_cond1 then\n\tsome_rule1\nelseif some_cond2 then\n\tsome_rule2\nelse\n\tsome_rule3\nend",
				formula.asText());
	}
	
}
