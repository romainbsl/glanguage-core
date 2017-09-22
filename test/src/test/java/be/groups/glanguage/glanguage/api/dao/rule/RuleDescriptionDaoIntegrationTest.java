package be.groups.glanguage.glanguage.api.dao.rule;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.entities.utils.*;
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
 * Test class for {@link RuleDescription}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleDescriptionDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private RuleDescriptionDao ruleDescriptionDao;
  /*
   * Tests
	 */

  /**
   * Tests {@link RuleDescription} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  @Transactional
  public void testJpaMapping() {
    Optional<RuleDescription> optRuleDescription = ruleDescriptionDao.findById(-900000L);

    assertNotNull(optRuleDescription);
    assertTrue(optRuleDescription.isPresent());

    RuleDescription ruleDescription = optRuleDescription.get();

		/* Checking entity */
    assertNotNull(ruleDescription);

    assertEquals(Long.valueOf(-900000L), ruleDescription.getId());

    assertEquals("r1", ruleDescription.getCode());

    assertNotNull(ruleDescription.getAlias());
    assertNotNull(ruleDescription.getAlias().getItem(Language.FR));
    assertNotNull(ruleDescription.getAlias().getItem(Language.FR).getText());
    assertEquals("r1_fr", ruleDescription.getAlias().getItem(Language.FR).getText());
    assertNotNull(ruleDescription.getAlias().getItem(Language.NL));
    assertNotNull(ruleDescription.getAlias().getItem(Language.NL).getText());
    assertEquals("r1_nl", ruleDescription.getAlias().getItem(Language.NL).getText());
    assertNotNull(ruleDescription.getAlias().getItem(Language.EN));
    assertNotNull(ruleDescription.getAlias().getItem(Language.EN).getText());
    assertEquals("r1_x", ruleDescription.getAlias().getItem(Language.EN).getText());

    assertNotNull(ruleDescription.getDescription());
    assertNotNull(ruleDescription.getDescription().getItem(Language.FR));
    assertNotNull(ruleDescription.getDescription().getItem(Language.FR).getText());
    assertEquals("descr_r1_fr", ruleDescription.getDescription().getItem(Language.FR).getText());
    assertNotNull(ruleDescription.getDescription().getItem(Language.NL));
    assertNotNull(ruleDescription.getDescription().getItem(Language.NL).getText());
    assertEquals("descr_r1_nl", ruleDescription.getDescription().getItem(Language.NL).getText());
    assertNotNull(ruleDescription.getDescription().getItem(Language.EN));
    assertNotNull(ruleDescription.getDescription().getItem(Language.EN).getText());
    assertEquals("descr_r1_x", ruleDescription.getDescription().getItem(Language.EN).getText());

		/* Checking relationships */
    assertNotNull(ruleDescription.getRuleVersions());
    assertEquals(4, ruleDescription.getRuleVersions().size());
    assertEquals(4,
        ruleDescription.getRuleVersions().stream().map(RuleVersion::getId).distinct().count());

    List<Long> ruleVersionIds = Arrays.asList(-900000L, -900001L, -900002L, -900003L);
    ruleDescription.getRuleVersions()
                   .forEach(rv -> assertTrue(ruleVersionIds.contains(rv.getId())));
  }
}
