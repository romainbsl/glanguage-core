package be.groups.glanguage.glanguage.api.business.plan;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

public class PlanTest {
	
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
	 * Tests {@link Plan#evaluate()}
	 */
	@Test
	public void testEvaluate() {
		LocalDateTime effectivityDate = LocalDateTime.now();
		Plan plan = Universe.getPlan(900003, effectivityDate, false);
		plan.resetEvaluation();
		assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
		Collection<RuleVersion> evaluatedRuleVersions = plan.evaluate();
		assertTrue(plan.isBranched());
		assertNotNull(evaluatedRuleVersions);
		assertFalse(evaluatedRuleVersions.isEmpty());
		assertEquals(4, evaluatedRuleVersions.size());
		for (RuleVersion ruleVersion: evaluatedRuleVersions) {
			switch (ruleVersion.getId()) {
				case 900005:
				case 900006: 
					assertEquals(1500, ruleVersion.getValue());
					break;
				case 900007:
					assertEquals(1000, ruleVersion.getValue());
					break;
				case 900008:
					assertEquals(500, ruleVersion.getValue());
					break;
				default:
					fail("Unexepected evaluated rule version : " + ruleVersion.getId());
			}
		}
	}
	
	/**
	 * Tests {@link Plan#evaluate(String, boolean)}
	 */
	@Test
	public void testEvaluateRule900002() {
		LocalDateTime effectivityDate = LocalDateTime.now();
		Plan plan = Universe.getPlan(900003, effectivityDate, false);
		plan.resetEvaluation();
		assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
		Collection<RuleVersion> evaluatedRuleVersions = plan.evaluate("900002", false);
		assertTrue(plan.isBranched());
		assertNotNull(evaluatedRuleVersions);
		assertFalse(evaluatedRuleVersions.isEmpty());
		assertEquals(1, evaluatedRuleVersions.size());
		for (RuleVersion ruleVersion: evaluatedRuleVersions) {
			switch (ruleVersion.getId()) {
				case 900005: 
					assertEquals(1500, ruleVersion.getValue());
					break;
				default:
					fail("Unexepected evaluated rule version : " + ruleVersion.getId());
			}
		}
	}
	
	/**
	 * Tests {@link Plan#evaluate(String, boolean)}
	 */
	@Test
	public void testEvaluateRule900003() {
		LocalDateTime effectivityDate = LocalDateTime.now();
		Plan plan = Universe.getPlan(900003, effectivityDate, false);
		plan.resetEvaluation();
		assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
		Collection<RuleVersion> evaluatedRuleVersions = plan.evaluate("900003", false);
		assertTrue(plan.isBranched());
		assertNotNull(evaluatedRuleVersions);
		assertFalse(evaluatedRuleVersions.isEmpty());
		assertEquals(3, evaluatedRuleVersions.size());
		for (RuleVersion ruleVersion: evaluatedRuleVersions) {
			switch (ruleVersion.getId()) {
				case 900006: 
					assertEquals(1500, ruleVersion.getValue());
					break;
				case 900007:
					assertEquals(1000, ruleVersion.getValue());
					break;
				case 900008:
					assertEquals(500, ruleVersion.getValue());
					break;
				default:
					fail("Unexepected evaluated rule version : " + ruleVersion.getId());
			}
		}
	}
	
}
