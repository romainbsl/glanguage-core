package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaApplicability}
 * 
 * @author DUPIREFR
 */
public class FormulaApplicabilityIntegrationTest {
	
	/*
	 * Tests
	 */

	/**
	 * Tests {@link FormulaApplicability#asText()}
	 */
	@Test
	@Category(BaseDatabaseTest.class)
	public void testAsText() throws GLanguageException {
		FormulaApplicability formula = spy(FormulaApplicability.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.C_APPLICABILITY)).when(formula).getDescription();
		doReturn("some_rule").when(formula).getConstantValue();
		
		assertEquals("some_rule.applicable", formula.asText());
	}
	
}
