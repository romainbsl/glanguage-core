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
	 * Tests {@link RuleSetVersionDao#findById()}
	 */
	@Test
	public void testFindById() {
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findById(1);
		assertNotNull(ruleSetVersion);
		assertEquals(1, ruleSetVersion.getId());
	}
	
	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetIdAndVersion()}
	 */
	@Test
	public void testFindByRuleSetIdAndVersion() {
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findByRuleSetIdAndVersion(1, "1.0.0");
		assertNotNull(ruleSetVersion);
		assertEquals(1, ruleSetVersion.getRuleSet().getId());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}
	
	/**
	 * Tests {@link RuleSetVersionDao#findByRuleSetIdAndExploitationDate()}
	 */
	@Test
	public void testFindByRuleSetIdAndExploitationDate() {
		LocalDateTime exploitationDate = LocalDateTime.now();
		RuleSetVersion ruleSetVersion = new RuleSetVersionDao().findByRuleSetIdAndExploitationDate(1, exploitationDate);
		assertNotNull(ruleSetVersion);
		assertEquals(1, ruleSetVersion.getRuleSet().getId());
		assertFalse("exploitation start date after exploitation date",
				ruleSetVersion.getExploitationStartDate().isAfter(exploitationDate));
	}	

}
