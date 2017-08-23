package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class RuleSetVersionDaoIntegrationTest extends DaoIntegrationTest {
	
	/**
	 * Tests {@link RuleSetVersionDao#findAll()}
	 */
	@Test
	public void testFindAll() {
		List<RuleSetVersion> ruleSetVersions = new RuleSetVersionDao().findAll();
		assertNotNull(ruleSetVersions);
		assertFalse(ruleSetVersions.isEmpty());
		System.out.println("Number of rule set versions : " + ruleSetVersions.size());
	}
	
	/**
	 * Tests {@link RuleSetVersionDao#findById(Integer)} when id is 900000
	 */
	@Test
	public void testFindById() {
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findById(-900000);
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getId());
	}
	
	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetIdAndVersion(Integer, String)} when rule set id is 900000 and version is "1.0.0"
	 */
	@Test
	public void testFindByRuleSetIdAndVersion() {
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findByRuleSetIdAndVersion(-900000, "1.0.0");
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
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findByRuleSetAliasAndVersion("rs1_fr", "1.0.0");
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
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findByRuleSetIdAndProductionDate(-900000, productionDate);
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
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findByRuleSetAliasAndProductionDate("rs1_fr", productionDate);
		assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAlias().getItem(Language.FR).getText());
		assertFalse("production start date after production date",
				ruleSetVersion.getProductionStartDate().isAfter(productionDate));
	}
	
}
