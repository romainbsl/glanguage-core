package be.groups.glanguage.glanguage.api.dao.formula.description.combination;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

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
