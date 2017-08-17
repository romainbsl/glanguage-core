package be.groups.glanguage.glanguage.api.dao.formula.description;

import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.dao.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.utils.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDescriptionDaoIntegrationTest extends DaoIntegrationTest {

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
    Optional<FormulaDescription> optFormulaDescription = formulaDescriptionDao.findById(214);

		/* Checking entity */
    assertNotNull(optFormulaDescription);
    assertTrue(optFormulaDescription.isPresent());

    FormulaDescription formulaDescription = optFormulaDescription.get();

    assertEquals(Integer.valueOf(214), formulaDescription.getId());

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
    assertEquals(Integer.valueOf(96), formulaDescription.getUsages().iterator().next().getId());
  }
}
