package be.groups.glanguage.core.dao.rule;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.rule.RuleDescription;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.entities.utils.Language;
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
