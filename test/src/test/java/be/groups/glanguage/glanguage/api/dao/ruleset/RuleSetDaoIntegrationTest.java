package be.groups.glanguage.glanguage.api.dao.ruleset;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.ruleset.*;
import be.groups.glanguage.glanguage.api.entities.utils.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

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
   * Tests {@link RuleSetDao#findEagerById(Integer)} when is is 900000
   */
	@Test
	public void testFindById() {
    RuleSet ruleSet = ruleSetDao.findEagerById(-900000);
    assertNotNull(ruleSet);
		assertEquals(-900000, ruleSet.getId());
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
	public void testJpaMapping() {
    RuleSet ruleSet = ruleSetDao.findEagerById(-900000);

		/* Checking entity */
		assertNotNull(ruleSet);

		assertEquals(-900000, ruleSet.getId());

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

		List<Integer> ruleSetVersionIds = Arrays.asList(-900000, -900001, -900002);
		ruleSet.getVersions().forEach(v -> assertTrue(ruleSetVersionIds.contains(v.getId())));
	}
}
