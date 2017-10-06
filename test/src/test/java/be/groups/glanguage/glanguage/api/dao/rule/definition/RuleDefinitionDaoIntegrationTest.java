package be.groups.glanguage.glanguage.api.dao.rule.definition;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.entities.rule.definition.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.*;

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
