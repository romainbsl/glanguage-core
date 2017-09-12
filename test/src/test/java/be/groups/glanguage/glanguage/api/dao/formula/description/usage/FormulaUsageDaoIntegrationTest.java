package be.groups.glanguage.glanguage.api.dao.formula.description.usage;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.*;
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
    Optional<FormulaUsage> optFormulaUsage = formulaUsageDao.findById(1);

		/* Checking entity */
    assertNotNull(optFormulaUsage);
    assertTrue(optFormulaUsage.isPresent());

    FormulaUsage formulaUsage = optFormulaUsage.get();

    assertEquals(Integer.valueOf(1), formulaUsage.getId());

		/* Checking relationships */
    assertNotNull(formulaUsage.getFormulaDescription());
    assertNotNull(formulaUsage.getParameterCombination());
    assertEquals(Integer.valueOf(1), formulaUsage.getFormulaDescription().getId());
    assertEquals(Integer.valueOf(1), formulaUsage.getParameterCombination().getId());
  }
}