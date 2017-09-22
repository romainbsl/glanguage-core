package be.groups.glanguage.glanguage.api.dao.formula.description.combination;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.*;
import be.groups.glanguage.glanguage.api.entities.utils.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

/**
 * Test class for {@link FormulaParameterCombinationItem}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaParameterCombinationDaoItemIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaParameterCombinationItemDao formulaParameterCombinationItemDao;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaParameterCombinationItem} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    Optional<FormulaParameterCombinationItem> optParameter =
        formulaParameterCombinationItemDao.findById(1L);

		/* Checking entity */
    assertNotNull(optParameter);
    assertTrue(optParameter.isPresent());

    FormulaParameterCombinationItem parameter = optParameter.get();

    assertEquals(Long.valueOf(1L), parameter.getId());

    assertEquals("integer", parameter.getName(Language.EN));
    assertEquals("expression de type INTEGER", parameter.getDescription(Language.FR));
    assertEquals("expressie van type INTEGER", parameter.getDescription(Language.NL));
    assertEquals("expression of type INTEGER", parameter.getDescription(Language.EN));
    assertEquals("", parameter.getDescription(Language.X));

    assertEquals(1, parameter.getTypes().size());
    assertTrue(parameter.getReturnTypes().contains(FormulaReturnType.INTEGER));

		/* Checking relationships */
    assertNotNull(parameter.getCombination());
    assertEquals(Long.valueOf(1L), parameter.getCombination().getId());
  }
}
