package be.groups.glanguage.core.dao.formula.description.usage;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.usage.FormulaUsage;
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
public class FormulaUsageDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaUsageDao formulaUsageDao;

	/*
     * Tests
	 */

  /**
   * Tests {@link FormulaDescription} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    Optional<FormulaUsage> optFormulaUsage = formulaUsageDao.findById(1L);

		/* Checking entity */
    assertNotNull(optFormulaUsage);
    assertTrue(optFormulaUsage.isPresent());

    FormulaUsage formulaUsage = optFormulaUsage.get();

    assertEquals(Long.valueOf(1L), formulaUsage.getId());

		/* Checking relationships */
    assertNotNull(formulaUsage.getFormulaDescription());
    assertNotNull(formulaUsage.getParameterCombination());
    assertEquals(Long.valueOf(1L), formulaUsage.getFormulaDescription().getId());
    assertEquals(Long.valueOf(1L), formulaUsage.getParameterCombination().getId());
  }
}