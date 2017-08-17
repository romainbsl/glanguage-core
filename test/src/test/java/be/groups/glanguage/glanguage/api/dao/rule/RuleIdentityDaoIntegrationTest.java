package be.groups.glanguage.glanguage.api.dao.rule;

import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleIdentity}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleIdentityDaoIntegrationTest extends DaoIntegrationTest {

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
  public void testJpaMapping() {
    Optional<RuleIdentity> OptRuleIdentity = ruleIdentityDao.findById(-900000);

		/* Checking entity */
    assertNotNull(OptRuleIdentity);
    assertTrue(OptRuleIdentity.isPresent());

    RuleIdentity ruleIdentity = OptRuleIdentity.get();

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
