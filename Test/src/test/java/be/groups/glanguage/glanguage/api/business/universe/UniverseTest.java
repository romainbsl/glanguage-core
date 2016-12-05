package be.groups.glanguage.glanguage.api.business.universe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

public class UniverseTest {
	
	/*
	 * Static fields
	 */
	@SuppressWarnings("unused")
	private static EntityManager em;
	
	/*
	 * Setups
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();
		
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		
		if (!TransactionHelper.isActive()) {
			TransactionHelper.begin();
		}
		
		em = JpaUtil.getEntityManager();
	}
	
	@AfterClass
	public static void close() {
		if (TransactionHelper.isActive()) {
			TransactionHelper.rollback();
		}
	}
	
	/**
	 * Test {@link Universe#getAllRuleSets()}
	 */
	@Test
	public void testGetAllRuleSet() {
		Collection<RuleSet> ruleSets = Universe.getAllRuleSets();
		assertNotNull(ruleSets);
		assertFalse(ruleSets.isEmpty());
	}
	
	/**
	 * Test {@link Universe#getRuleSet(Integer)} when id is 900000
	 */
	@Test
	public void testGetRuleSetById() {
		RuleSet ruleSet = Universe.getRuleSet(-900000);
		assertNotNull(ruleSet);
		assertEquals(-900000, ruleSet.getId());
		assertNotNull(ruleSet.getVersions());
		assertFalse(ruleSet.getVersions().isEmpty());
	}
	
	/**
	 * Test {@link Universe#getRuleSet(String)} when alias is "rs1_fr"
	 */
	@Test
	public void testGetRuleSetByAlias() {
		RuleSet ruleSet = Universe.getRuleSet("rs1_fr");
		assertNotNull(ruleSet);
		assertEquals("rs1_fr", ruleSet.getAliasFr());
		assertNotNull(ruleSet.getVersions());
		assertFalse(ruleSet.getVersions().isEmpty());
	}
	
	/**
	 * Test {@link Universe#getRuleSetVersion(Integer)} when id is 900000
	 */
	@Test
	public void testGetRuleSetVersionById() {
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion(-900000);
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getId());
	}
	
	/**
	 * Test {@link Universe#getRuleSetVersion(Integer, String)} when rule set id is 900000 and version is "1.0.0"
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetIdAndVersion() {
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion(-900000, "1.0.0");
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}
	
	/**
	 * Test {@link Universe#getRuleSetVersion(String, String)} when rule set id is "rs1_fr" and version is "1.0.0"
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetAliasAndVersion() {
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion("rs1_fr", "1.0.0");
		assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAliasFr());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}
	
	/**
	 * Test {@link Universe#getRuleSetVersion(Integer, LocalDateTime)} when rule set id is 900000 and exploitation date is now
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetIdAndExploitationDate() {
		LocalDateTime exploitationDate = LocalDateTime.now();
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion(-900000, exploitationDate);
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
		assertFalse("exploitation start date after exploitation date",
				ruleSetVersion.getExploitationStartDate().isAfter(exploitationDate));
	}
	
	/**
	 * Test {@link Universe#getRuleSetVersion(String, LocalDateTime)} when rule set id is "rs1_fr" and exploitation date is now
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetAliasAndExploitationDate() {
		LocalDateTime exploitationDate = LocalDateTime.now();
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion("rs1_fr", exploitationDate);
		assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAliasFr());
		assertFalse("exploitation start date after exploitation date",
				ruleSetVersion.getExploitationStartDate().isAfter(exploitationDate));
	}
	
	/**
	 * Tests {@link Universe#getPlan(Integer, LocalDateTime, boolean)}
	 */
	@Test
	public void testGetPlan() {
		LocalDate effectivityDate = LocalDate.now();
		Plan plan = Universe.getPlan(-900000, effectivityDate);
		assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
	}
	
	/**
	 * Test {@link Universe#getFormula(Integer)}
	 */
	@Test
	public void testGetFormula() {
		AbstractFormula formula = Universe.getFormula(-900000);
		assertNotNull(formula);
		assertEquals(-900000, formula.getId());
	}
	
}
