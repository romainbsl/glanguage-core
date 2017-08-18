package be.groups.glanguage.glanguage.api.dao.rule.definition;

import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
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
 * Test class for {@link RuleDefinition}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleDefinitionDaoIntegrationTest extends DaoIntegrationTest {

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
  public void testJpaMapping() {
    RuleDefinition ruleDefinition = ruleDefinitionDao.findEagerById(-900001);

		/* Checking entity */
    assertNotNull(ruleDefinition);

    assertEquals(-900001, ruleDefinition.getId());

		/* Checking relationships */
    assertEquals(-900000, ruleDefinition.getRuleIdentity().getId());

    assertNotNull(ruleDefinition.getDefinitionParameters());
    assertEquals(2, ruleDefinition.getDefinitionParameters().size());
    assertEquals(2,
        ruleDefinition.getDefinitionParameters().stream().map(p -> p.getId()).distinct().count());

    RuleDefinitionParameterId firstRuleDefinitionParameterId = new RuleDefinitionParameterId();
    firstRuleDefinitionParameterId.setLevelId(2);
    firstRuleDefinitionParameterId.setRuleDefinitionId(-900001);
    firstRuleDefinitionParameterId.setValue("100000");

    RuleDefinitionParameterId secondtRuleDefinitionParameterId = new RuleDefinitionParameterId();
    secondtRuleDefinitionParameterId.setLevelId(3);
    secondtRuleDefinitionParameterId.setRuleDefinitionId(-900001);
    secondtRuleDefinitionParameterId.setValue("355");

    List<RuleDefinitionParameterId> ruleDefinitionParameterIds =
        Arrays.asList(firstRuleDefinitionParameterId,
            secondtRuleDefinitionParameterId);
    ruleDefinition.getDefinitionParameters().forEach(p -> {
      assertTrue(ruleDefinitionParameterIds.contains(p.getId()));
    });

    assertNotNull(ruleDefinition.getVersions());
    assertEquals(1, ruleDefinition.getVersions().size());
    assertEquals(-900003, ruleDefinition.getVersions().iterator().next().getId());
  }
}
