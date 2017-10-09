package be.groups.glanguage.core.dao.rule.definition;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameterId;
import be.groups.glanguage.core.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleDefinitionParameter}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleDefinitionParameterDaoIntegrationTest extends IntegrationTest {

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
    ruleDefintionParameterId.setLevelId(2L);
    ruleDefintionParameterId.setRuleDefinitionId(-900001L);
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
    assertEquals(Long.valueOf(-900001L), ruleDefinitionParameter.getRuleDefinition().getId());
  }
}
