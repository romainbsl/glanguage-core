package be.groups.glanguage.core.dao.formula.description;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.dao.formula.FormulaDescriptionDao;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaPriority;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.utils.Language;
import be.groups.glanguage.core.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDescriptionDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionDao formulaDescriptionDao;

  @Test
  public void testFindAll() {
    List<FormulaDescription> formulaDescriptions = formulaDescriptionDao.findAll();
    assertNotNull(formulaDescriptions);
    assertFalse(formulaDescriptions.isEmpty());
    System.out.println(formulaDescriptions.size());
    System.out.println(formulaDescriptions);
  }

  /**
   * Tests {@link FormulaDescription} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    Optional<FormulaDescription> optFormulaDescription = formulaDescriptionDao.findById(214L);

		/* Checking entity */
    assertNotNull(optFormulaDescription);
    assertTrue(optFormulaDescription.isPresent());

    FormulaDescription formulaDescription = optFormulaDescription.get();

    assertEquals(Long.valueOf(214L), formulaDescription.getId());

    assertEquals(FormulaType.OP_OR, formulaDescription.getType());

    assertEquals("OR", formulaDescription.getName());

    assertEquals("'OU' logique", formulaDescription.getDescription(Language.FR));
    assertEquals("Logische 'OF'", formulaDescription.getDescription(Language.NL));
    assertEquals("", formulaDescription.getDescription(Language.X));
    assertEquals("Logical 'OR'", formulaDescription.getDescription(Language.EN));

    assertEquals(FormulaPriority.OR, formulaDescription.getPriority());

		/* Checking relationships */
    assertNotNull(formulaDescription.getUsages());
    assertEquals(1, formulaDescription.getUsages().size());
    assertEquals(Long.valueOf(96L), formulaDescription.getUsages().iterator().next().getId());
  }
}
