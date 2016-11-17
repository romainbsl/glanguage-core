package be.groups.glanguage.glanguage.api.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;

public class RuleSetVersionDaoTest extends DaoTest {
	
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
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAliasFr());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}
	
	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetIdAndExploitationDate(Integer, LocalDateTime)} when rule set id is 900000 and
	 * exploitation date is now
	 */
	@Test
	public void testFindByRuleSetIdAndExploitationDate() {
		LocalDateTime exploitationDate = LocalDateTime.now();
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findByRuleSetIdAndExploitationDate(-900000, exploitationDate);
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
		assertFalse("exploitation start date after exploitation date",
				ruleSetVersion.getExploitationStartDate().isAfter(exploitationDate));
	}
	
	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetAliasAndExploitationDate(String, LocalDateTime)} when rule set alias is "rs1_fr" and
	 * exploitation date is now
	 */
	@Test
	public void testFindByRuleSetAliasAndExploitationDate() {
		LocalDateTime exploitationDate = LocalDateTime.now();
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findByRuleSetAliasAndExploitationDate("rs1_fr", exploitationDate);
		assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAliasFr());
		assertFalse("exploitation start date after exploitation date",
				ruleSetVersion.getExploitationStartDate().isAfter(exploitationDate));
	}
	
}
