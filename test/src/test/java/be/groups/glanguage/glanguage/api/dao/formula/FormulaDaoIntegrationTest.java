package be.groups.glanguage.glanguage.api.dao.formula;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDao formulaDao;

  @Test
  public void testFindById() {
    Optional<AbstractFormula> optFormula = formulaDao.findById(-900000);

    assertNotNull(optFormula);
    assertTrue(optFormula.isPresent());

    AbstractFormula formula = optFormula.get();
    assertEquals(-900000, formula.getId());
  }

  /**
   * Tests {@link AbstractFormula} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    Optional<AbstractFormula> optFormula = formulaDao.findById(-900003);

    assertNotNull(optFormula);
    assertTrue(optFormula.isPresent());

    AbstractFormula formula = optFormula.get();

		/* Checking entity */
    assertNotNull(formula);

    assertEquals(-900003, formula.getId());

    assertEquals("TRUE", formula.getConstantValue());
    assertEquals(Integer.valueOf(4), formula.getSequenceNumber());

		/* Checking relationships */
    assertNotNull(formula.getDescription());
    assertEquals(Integer.valueOf(1004), formula.getDescription().getId());
  }
}
