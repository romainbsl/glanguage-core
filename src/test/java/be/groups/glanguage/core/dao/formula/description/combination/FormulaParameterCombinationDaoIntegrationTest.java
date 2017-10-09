package be.groups.glanguage.core.dao.formula.description.combination;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombination;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.core.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author michotte
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaParameterCombinationDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaParameterCombinationDao formulaParameterCombinationDao;

    /*
     * Tests
	 */

  /**
   * Tests {@link FormulaParameterCombination} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    Optional<FormulaParameterCombination> optParameterCombination =
        formulaParameterCombinationDao.findById(9L);

		/* Checking entity */
    assertNotNull(optParameterCombination);
    assertTrue(optParameterCombination.isPresent());

    FormulaParameterCombination parameterCombination = optParameterCombination.get();

    assertEquals(Long.valueOf(9L), parameterCombination.getId());

		/* Checking relationships */
    assertNotNull(parameterCombination.getCombinationParameters());
    assertEquals(2, parameterCombination.getCombinationParameters().size());
    assertEquals(2, parameterCombination.getCombinationParameters()
                                        .stream()
                                        .map(FormulaParameterCombinationItem::getId)
                                        .distinct()
                                        .count());
  }
}
