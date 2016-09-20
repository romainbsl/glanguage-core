package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test class for {@link FormulaApplicability}
 * 
 * @author DUPIREFR
 */
public class FormulaApplicabilityTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaApplicability#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaApplicability formula = new FormulaApplicability();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaApplicability#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Tests {@link FormulaApplicability#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Tests {@link FormulaApplicability#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Tests {@link FormulaApplicability#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Tests {@link FormulaApplicability#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Tests {@link FormulaApplicability#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Tests {@link FormulaApplicability#asText()}
	 */
	@Test
	public void testAsText() {
		String ruleId = "some_rule";
		
		FormulaApplicability formula = new FormulaApplicability(ruleId);
		
		assertEquals("some_rule.applicable", formula.asText());
	}
	
}
