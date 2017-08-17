package be.groups.glanguage.glanguage.api.dao.ruleset;

import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.entities.ruleset.*;
import be.groups.glanguage.glanguage.api.entities.utils.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.time.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RuleSetVersionDaoIntegrationTest extends DaoIntegrationTest {
	@Autowired
	private RuleSetVersionDao ruleSetVersionDao;

	/**
	 * Tests {@link RuleSetVersionDao#findAll()}
	 */
	@Test
	public void testFindAll() {
		List<RuleSetVersion> ruleSetVersions = ruleSetVersionDao.findAll();
		assertNotNull(ruleSetVersions);
		assertFalse(ruleSetVersions.isEmpty());
		System.out.println("Number of rule set versions : " + ruleSetVersions.size());
	}

	/**
	 * Tests {@link RuleSetVersionDao#findById(Integer)} when id is 900000
	 */
	@Test
	public void testFindById() {
		RuleSetVersion ruleSetVersion = ruleSetVersionDao.findById(-900000);
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getId());
	}

	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetIdAndVersion(Integer, String)} when rule set id is 900000 and version is "1.0.0"
	 */
	@Test
	public void testFindByRuleSetIdAndVersion() {
		RuleSetVersion ruleSetVersion = ruleSetVersionDao.findByRuleSetIdAndVersion(-900000, "1.0.0");
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}

	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetAliasAndVersion(String, String)} when rule set alias is "rs1_fr" and version is
	 * "1.0.0"
	 */
	@Test
	public void testFindByRuleSetAliasAndVersion() {
		RuleSetVersion ruleSetVersion =
				ruleSetVersionDao.findByRuleSetAliasAndVersion("rs1_fr", "1.0.0");
		assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAlias().getItem(Language.FR).getText());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}

	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetIdAndProductionDate(Integer, LocalDateTime)} when rule set id is 900000 and
	 * production date is now
	 */
	@Test
	public void testFindByRuleSetIdAndProductionDate() {
		LocalDateTime productionDate = LocalDateTime.now();
		RuleSetVersion ruleSetVersion =
				ruleSetVersionDao.findByRuleSetIdAndProductionDate(-900000, productionDate);
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
		assertFalse("production start date after production date",
				ruleSetVersion.getProductionStartDate().isAfter(productionDate));
	}

	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetAliasAndProductionDate(String, LocalDateTime)} when rule set alias is "rs1_fr" and
	 * production date is now
	 */
	@Test
	public void testFindByRuleSetAliasAndProductionDate() {
		LocalDateTime productionDate = LocalDateTime.now();
		RuleSetVersion ruleSetVersion =
				ruleSetVersionDao.findByRuleSetAliasAndProductionDate("rs1_fr", productionDate);
		assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAlias().getItem(Language.FR).getText());
		assertFalse("production start date after production date",
				ruleSetVersion.getProductionStartDate().isAfter(productionDate));
	}

	/**
	 * Tests {@link RuleSetVersion} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleSetVersion ruleSetVersion = ruleSetVersionDao.findById(-900001);

		/* Checking entity */
		assertNotNull(ruleSetVersion);

		assertEquals(-900001, ruleSetVersion.getId());

		assertEquals(LocalDateTime.of(2016, 9, 7, 9, 0), ruleSetVersion.getProductionStartDate());
		assertEquals("1.0.1", ruleSetVersion.getVersion());
		assertNotNull(ruleSetVersion.getCreationDate());
		assertEquals("dupirefr", ruleSetVersion.getCreationAuthor());
		assertEquals("INTEGRATION_TEST", ruleSetVersion.getLabel());
		assertEquals(RuleSetVersionStatus.PROD, ruleSetVersion.getStatus());
		assertNull(ruleSetVersion.getModificationAuthor());
		assertNull(ruleSetVersion.getModificationDate());

		/* Checking relationships */
		assertNotNull(ruleSetVersion.getRuleSet());
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());

		assertNotNull(ruleSetVersion.getParent());
		assertEquals(-900000, ruleSetVersion.getParent().getId());

		assertNotNull(ruleSetVersion.getChildren());
		assertEquals(1, ruleSetVersion.getChildren().size());
		assertEquals(-900002, ((RuleSetVersion) ruleSetVersion.getChildren().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getIncludes());
		assertEquals(1, ruleSetVersion.getIncludes().size());
		assertEquals(-900003, ((RuleSetVersion) ruleSetVersion.getIncludes().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getIncludedIn());
		assertEquals(1, ruleSetVersion.getIncludedIn().size());
		assertEquals(-900004, ((RuleSetVersion) ruleSetVersion.getIncludedIn().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getRuleVersions());
		assertEquals(4, ruleSetVersion.getRuleVersions().size());
		assertEquals(4,
				ruleSetVersion.getRuleVersions().stream().map(RuleVersion::getId).distinct().count());

		List<Integer> ruleVersionIds = Arrays.asList(-900000, -900002, -900003, -900004);
		ruleSetVersion.getRuleVersions().forEach(v -> assertTrue(ruleVersionIds.contains(v.getId())));
	}
}
