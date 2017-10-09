package be.groups.glanguage.core.dao.ruleset;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.ruleset.RuleSet;
import be.groups.glanguage.core.entities.ruleset.RuleSetVersion;
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

@RunWith(SpringJUnit4ClassRunner.class)
public class RuleSetDaoIntegrationTest extends IntegrationTest {

	@Autowired
	private RuleSetDao ruleSetDao;

	/**
	 * Tests {@link RuleSetDao#findAll()}
	 */
	@Test
	public void testFindAll() {
		List<RuleSet> ruleSets = ruleSetDao.findAll();
		assertNotNull(ruleSets);
		assertFalse(ruleSets.isEmpty());
		System.out.println("Number of rule sets : " + ruleSets.size());
	}

	/**
   * Tests {@link RuleSetDao#findById(Object)} when is is 900000
   */
	@Test
	public void testFindById() {
    Optional<RuleSet> optRuleSet = ruleSetDao.findById(-900000L);

    assertNotNull(optRuleSet);
    assertTrue(optRuleSet.isPresent());

    RuleSet ruleSet = optRuleSet.get();

    assertNotNull(ruleSet);
		assertEquals(Long.valueOf(-900000L), ruleSet.getId());
	}

	/**
	 * Tests {@link RuleSetDao#findByAlias(String)} when alias is "rs1_fr"
	 */
	@Test
	public void testFindByAliasFr() {
		RuleSet ruleSet = ruleSetDao.findByAlias("rs1_fr");
		assertNotNull(ruleSet);
		assertEquals("rs1_fr", ruleSet.getAlias().getItem(Language.FR).getText());
	}

	/**
	 * Tests {@link RuleSetDao#findByAlias(String)} when alias is "rs1_x"
	 */
	@Test
	public void testFindByAliasX() {
		RuleSet ruleSet = ruleSetDao.findByAlias("rs1_x");
		assertNotNull(ruleSet);
		assertEquals("rs1_x", ruleSet.getAlias().getItem(Language.EN).getText());
	}

	/**
	 * Tests {@link RuleSet} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
  @Transactional
  public void testJpaMapping() {
    Optional<RuleSet> optRuleSet = ruleSetDao.findById(-900000L);

    assertNotNull(optRuleSet);
    assertTrue(optRuleSet.isPresent());

    RuleSet ruleSet = optRuleSet.get();

		/* Checking entity */
		assertNotNull(ruleSet);

		assertEquals(Long.valueOf(-900000), ruleSet.getId());

		assertNotNull(ruleSet.getAlias());
		assertNotNull(ruleSet.getAlias().getItem(Language.FR));
		assertNotNull(ruleSet.getAlias().getItem(Language.FR).getText());
		assertEquals("rs1_fr", ruleSet.getAlias().getItem(Language.FR).getText());
		assertNotNull(ruleSet.getAlias().getItem(Language.NL));
		assertNotNull(ruleSet.getAlias().getItem(Language.NL).getText());
		assertEquals("rs1_nl", ruleSet.getAlias().getItem(Language.NL).getText());
		assertNotNull(ruleSet.getAlias().getItem(Language.EN));
		assertNotNull(ruleSet.getAlias().getItem(Language.EN).getText());
		assertEquals("rs1_x", ruleSet.getAlias().getItem(Language.EN).getText());

		assertNotNull(ruleSet.getDescription());
		assertNotNull(ruleSet.getDescription().getItem(Language.FR));
		assertNotNull(ruleSet.getDescription().getItem(Language.FR).getText());
		assertEquals("rs1_descr_fr", ruleSet.getDescription().getItem(Language.FR).getText());
		assertNotNull(ruleSet.getDescription().getItem(Language.NL));
		assertNotNull(ruleSet.getDescription().getItem(Language.NL).getText());
		assertEquals("rs1_descr_nl", ruleSet.getDescription().getItem(Language.NL).getText());
		assertNotNull(ruleSet.getDescription().getItem(Language.EN));
		assertNotNull(ruleSet.getDescription().getItem(Language.EN).getText());
		assertEquals("rs1_descr_x", ruleSet.getDescription().getItem(Language.EN).getText());

		/* Checking relationships */
		assertNotNull(ruleSet.getVersions());
		assertEquals(3, ruleSet.getVersions().size());
		assertEquals(3, ruleSet.getVersions().stream().map(RuleSetVersion::getId).distinct().count());

		List<Long> ruleSetVersionIds = Arrays.asList(-900000L, -900001L, -900002L);
		ruleSet.getVersions().forEach(v -> assertTrue(ruleSetVersionIds.contains(v.getId())));
	}
}
