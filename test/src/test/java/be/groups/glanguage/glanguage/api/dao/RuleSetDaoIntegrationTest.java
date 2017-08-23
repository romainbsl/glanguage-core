package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RuleSetDaoIntegrationTest extends DaoIntegrationTest {
	
	/**
	 * Tests {@link RuleSetDao#findAll()}
	 */
	@Test
	public void testFindAll() {
		List<RuleSet> ruleSets = new RuleSetDao().findAll();
		assertNotNull(ruleSets);
		assertFalse(ruleSets.isEmpty());
		System.out.println("Number of rule sets : " + ruleSets.size());
	}
	
	/**
	 * Tests {@link RuleSetDao#findById(Integer)} when is is 900000
	 */
	@Test
	public void testFindById() {
		RuleSet ruleSet = new RuleSetDao().findById(-900000);
		assertNotNull(ruleSet);
		assertEquals(-900000, ruleSet.getId());
	}
	
	/**
	 * Tests {@link RuleSetDao#findByAlias(String)} when alias is "rs1_fr"
	 */
	@Test
	public void testFindByAliasFr() {
		RuleSet ruleSet = new RuleSetDao().findByAlias("rs1_fr");
		assertNotNull(ruleSet);
		assertEquals("rs1_fr", ruleSet.getAlias().getItem(Language.FR).getText());
	}
	
	/**
	 * Tests {@link RuleSetDao#findByAlias(String)} when alias is "rs1_x"
	 */
	@Test
	public void testFindByAliasX() {
		RuleSet ruleSet = new RuleSetDao().findByAlias("rs1_x");
		assertNotNull(ruleSet);
		assertEquals("rs1_x", ruleSet.getAlias().getItem(Language.EN).getText());
	}
		
}
