package be.groups.glanguage.glanguage.api.dao.ruleset;

import be.groups.glanguage.glanguage.api.*;
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
import org.springframework.transaction.annotation.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RuleSetVersionDaoIntegrationTest extends IntegrationTest {
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
	 * Tests {@link RuleSetVersionDao#findById(Object)} when id is 900000
	 */
	@Test
	public void testFindById() {
    Optional<RuleSetVersion> optRuleSetVersion = ruleSetVersionDao.findById(-900000L);

    assertNotNull(optRuleSetVersion);
    assertTrue(optRuleSetVersion.isPresent());

    RuleSetVersion ruleSetVersion = optRuleSetVersion.get();
    assertEquals(Long.valueOf(-900000), ruleSetVersion.getId());
	}

	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetIdAndVersion(Long, String)} when rule set id is 900000 and version is
	 * "1.0.0"
	 */
	@Test
	public void testFindByRuleSetIdAndVersion() {
		RuleSetVersion ruleSetVersion = ruleSetVersionDao.findByRuleSetIdAndVersion(-900000L, "1.0.0");
		assertNotNull(ruleSetVersion);
		assertEquals(Long.valueOf(-900000), ruleSetVersion.getRuleSet().getId());
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
	 * Tests {@link RuleSetVersionDao#findByRuleSetIdAndProductionDate(Long, LocalDateTime)} when rule set id is 900000
	 * and
	 * production date is now
	 */
	@Test
	public void testFindByRuleSetIdAndProductionDate() {
		LocalDateTime productionDate = LocalDateTime.now();
		RuleSetVersion ruleSetVersion =
				ruleSetVersionDao.findByRuleSetIdAndProductionDate(-900000L, productionDate);
		assertNotNull(ruleSetVersion);
		assertEquals(Long.valueOf(-900000), ruleSetVersion.getRuleSet().getId());
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
	@Transactional
	public void testJpaMapping() {
		Optional<RuleSetVersion> optRuleSetVersion = ruleSetVersionDao.findById(-900001L);

		assertNotNull(optRuleSetVersion);
		assertTrue(optRuleSetVersion.isPresent());

		RuleSetVersion ruleSetVersion = optRuleSetVersion.get();

		/* Checking entity */
		assertNotNull(ruleSetVersion);

		assertEquals(Long.valueOf(-900001), ruleSetVersion.getId());

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
		assertEquals(Long.valueOf(-900000L), ruleSetVersion.getRuleSet().getId());

		assertNotNull(ruleSetVersion.getParent());
		assertEquals(Long.valueOf(-900000L), ruleSetVersion.getParent().getId());

		assertNotNull(ruleSetVersion.getChildren());
		assertEquals(1, ruleSetVersion.getChildren().size());
		assertEquals(Long.valueOf(-900002L), ((RuleSetVersion) ruleSetVersion.getChildren().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getIncludes());
		assertEquals(1, ruleSetVersion.getIncludes().size());
		assertEquals(Long.valueOf(-900003L), ((RuleSetVersion) ruleSetVersion.getIncludes().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getIncludedIn());
		assertEquals(1, ruleSetVersion.getIncludedIn().size());
		assertEquals(Long.valueOf(-900004L), ((RuleSetVersion) ruleSetVersion.getIncludedIn().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getRuleVersions());
		assertEquals(4, ruleSetVersion.getRuleVersions().size());
		assertEquals(4,
				ruleSetVersion.getRuleVersions().stream().map(RuleVersion::getId).distinct().count());

		List<Long> ruleVersionIds = Arrays.asList(-900000L, -900002L, -900003L, -900004L);
		ruleSetVersion.getRuleVersions().forEach(v -> assertTrue(ruleVersionIds.contains(v.getId())));
	}
}
