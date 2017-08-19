package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.call;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.*;
import be.groups.glanguage.glanguage.api.error.exception.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaApplicability}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaApplicabilityIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

  /**
   * Tests {@link FormulaApplicability#asText()}
   */
  @Test
  @Category(DaoIntegrationTest.class)
  public void testAsText() throws GLanguageException {
    FormulaApplicability formula = spy(FormulaApplicability.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.C_APPLICABILITY)).when(formula)
                                                                                   .getDescription();
    doReturn("some_rule").when(formula).getConstantValue();

    assertEquals("some_rule.applicable", formula.asText());
  }
}
