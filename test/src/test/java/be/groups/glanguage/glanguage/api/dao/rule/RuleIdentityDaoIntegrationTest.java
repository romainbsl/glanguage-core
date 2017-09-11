package be.groups.glanguage.glanguage.api.dao.rule;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
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
    Optional<RuleIdentity> optRuleIdentity = ruleIdentityDao.findById(-900000);

    assertNotNull(optRuleIdentity);
    assertTrue(optRuleIdentity.isPresent());

    RuleIdentity ruleIdentity = optRuleIdentity.get();

		/* Checking entity */
    assertNotNull(ruleIdentity);
    assertEquals(-900000, ruleIdentity.getId());

		/* Checking relationships */
    assertNotNull(ruleIdentity.getRuleDefinitions());
    assertEquals(2, ruleIdentity.getRuleDefinitions().size());
    assertEquals(2,
        ruleIdentity.getRuleDefinitions().stream().map(RuleDefinition::getId).distinct().count());

    List<Integer> ruleDefinitionIds = Arrays.asList(-900000, -900001);
    ruleIdentity.getRuleDefinitions()
                .forEach(d -> assertTrue(ruleDefinitionIds.contains(d.getId())));
  }
}
