package be.groups.glanguage.core.dao.formula;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDao formulaDao;

  @Test
  public void testFindById() {
    Optional<AbstractFormula> optFormula = formulaDao.findById(-900000L);

    assertNotNull(optFormula);
    assertTrue(optFormula.isPresent());

    AbstractFormula formula = optFormula.get();
    assertEquals(Long.valueOf(-900000L), formula.getId());
  }

  /**
   * Tests {@link AbstractFormula} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    Optional<AbstractFormula> optFormula = formulaDao.findById(-900003L);

    assertNotNull(optFormula);
    assertTrue(optFormula.isPresent());

    AbstractFormula formula = optFormula.get();

		/* Checking entity */
    assertNotNull(formula);

    assertEquals(Long.valueOf(-900003L), formula.getId());

    assertEquals("TRUE", formula.getConstantValue());
    assertEquals(Integer.valueOf(4), formula.getSequenceNumber());

		/* Checking relationships */
    assertNotNull(formula.getDescription());
    assertEquals(Long.valueOf(1004L), formula.getDescription().getId());
  }
}
