package be.groups.glanguage.core.dao.rule.definition;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.rule.RuleDefinition;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameterId;
import be.groups.glanguage.core.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleDefinition}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleDefinitionDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private RuleDefinitionDao ruleDefinitionDao;

	/*
   * Tests
	 */

  /**
   * Tests {@link RuleDefinition} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  @Transactional
  public void testJpaMapping() {
    Optional<RuleDefinition> optRuleDefinition = ruleDefinitionDao.findById(-900001L);

    assertNotNull(optRuleDefinition);
    assertTrue(optRuleDefinition.isPresent());

    RuleDefinition ruleDefinition = optRuleDefinition.get();

		/* Checking entity */
    assertNotNull(ruleDefinition);

    assertEquals(Long.valueOf(-900001L), ruleDefinition.getId());

		/* Checking relationships */
    assertEquals(Long.valueOf(-900000L), ruleDefinition.getRuleIdentity().getId());

    assertNotNull(ruleDefinition.getDefinitionParameters());
    assertEquals(2, ruleDefinition.getDefinitionParameters().size());
    assertEquals(2,
        ruleDefinition.getDefinitionParameters()
                      .stream()
                      .map(RuleDefinitionParameter::getId)
                      .distinct()
                      .count());

    RuleDefinitionParameterId firstRuleDefinitionParameterId = new RuleDefinitionParameterId();
    firstRuleDefinitionParameterId.setLevelId(2L);
    firstRuleDefinitionParameterId.setRuleDefinitionId(-900001L);
    firstRuleDefinitionParameterId.setValue("100000");

    RuleDefinitionParameterId secondtRuleDefinitionParameterId = new RuleDefinitionParameterId();
    secondtRuleDefinitionParameterId.setLevelId(3L);
    secondtRuleDefinitionParameterId.setRuleDefinitionId(-900001L);
    secondtRuleDefinitionParameterId.setValue("355");

    List<RuleDefinitionParameterId> ruleDefinitionParameterIds =
        Arrays.asList(firstRuleDefinitionParameterId,
            secondtRuleDefinitionParameterId);
    ruleDefinition.getDefinitionParameters().forEach(p -> {
      assertTrue(ruleDefinitionParameterIds.contains(p.getId()));
    });

    assertNotNull(ruleDefinition.getVersions());
    assertEquals(1, ruleDefinition.getVersions().size());
    assertEquals(Long.valueOf(-900003L), ruleDefinition.getVersions().iterator().next().getId());
  }
}
