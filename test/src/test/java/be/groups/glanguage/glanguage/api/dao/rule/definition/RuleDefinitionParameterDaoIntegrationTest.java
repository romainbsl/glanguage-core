package be.groups.glanguage.glanguage.api.dao.rule.definition;

import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.rule.definition.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleDefinitionParameter}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleDefinitionParameterDaoIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private RuleDefinitionParameterDao parameterDao;

	/*
   * Tests
	 */

  /**
   * Tests {@link RuleDefinitionParameter} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    RuleDefinitionParameterId ruleDefintionParameterId = new RuleDefinitionParameterId();
    ruleDefintionParameterId.setLevelId(2);
    ruleDefintionParameterId.setRuleDefinitionId(-900001);
    ruleDefintionParameterId.setValue("100000");

    Optional<RuleDefinitionParameter> optRuleDefinitionParameter =
        parameterDao.findById(ruleDefintionParameterId);

		/* Checking entity */
    assertNotNull(optRuleDefinitionParameter);
    assertTrue(optRuleDefinitionParameter.isPresent());

    RuleDefinitionParameter ruleDefinitionParameter = optRuleDefinitionParameter.get();

    assertEquals(ruleDefintionParameterId, ruleDefinitionParameter.getId());

		/* Checking relationships */
    assertNotNull(ruleDefinitionParameter.getRuleDefinition());
    assertEquals(-900001, ruleDefinitionParameter.getRuleDefinition().getId());
  }
}
