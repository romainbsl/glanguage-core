package be.groups.glanguage.core.business.factory.formula.implementations.call;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.call.FormulaApplicability;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaApplicability}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaApplicabilityIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

  /**
   * Tests {@link FormulaApplicability#asText()}
   */
  @Test
  @Category(IntegrationTest.class)
  public void testAsText() throws GLanguageException {
    FormulaApplicability formula = spy(FormulaApplicability.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.C_APPLICABILITY)).when(formula)
                                                                                   .getDescription();
    doReturn("some_rule").when(formula).getConstantValue();

    assertEquals("some_rule.applicable", formula.asText());
  }
}
