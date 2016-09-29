package be.groups.glanguage.glanguage.api.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;

public class RuleSetDaoTest extends DaoTest {
	
	@Test
	public void testFindAll() {
		List<RuleSet> ruleSets = new RuleSetDao().findAll();
		assertNotNull(ruleSets);
		assertFalse(ruleSets.isEmpty());
		System.out.println("Number of rule sets : " + ruleSets.size());
	}
	
	@Test
	public void testFindById() {
		RuleSet ruleSet = new RuleSetDao().findById(1);
		assertNotNull(ruleSet);
		assertEquals(1, ruleSet.getId());
	}
		
}
