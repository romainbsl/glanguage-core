package be.groups.glanguage.core.dao.rule;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.rule.RuleDefinition;
import be.groups.glanguage.core.entities.rule.RuleIdentity;
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
 * Test class for {@link RuleIdentity}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleIdentityDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private RuleIdentityDao ruleIdentityDao;

	/*
   * Tests
	 */

  /**
   * Tests {@link RuleIdentity} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  @Transactional
  public void testJpaMapping() {
    Optional<RuleIdentity> optRuleIdentity = ruleIdentityDao.findById(-900000L);

    assertNotNull(optRuleIdentity);
    assertTrue(optRuleIdentity.isPresent());

    RuleIdentity ruleIdentity = optRuleIdentity.get();

		/* Checking entity */
    assertNotNull(ruleIdentity);
    assertEquals(Long.valueOf(-900000L), ruleIdentity.getId());

		/* Checking relationships */
    assertNotNull(ruleIdentity.getRuleDefinitions());
    assertEquals(2, ruleIdentity.getRuleDefinitions().size());
    assertEquals(2,
        ruleIdentity.getRuleDefinitions().stream().map(RuleDefinition::getId).distinct().count());

    List<Long> ruleDefinitionIds = Arrays.asList(-900000L, -900001L);
    ruleIdentity.getRuleDefinitions()
                .forEach(d -> assertTrue(ruleDefinitionIds.contains(d.getId())));
  }
}
