package be.groups.glanguage.glanguage.api.business.plan;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Collection;

import static org.junit.Assert.*;

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
	 * Tests {@link Plan#evaluate(Object)}}
	 */
	@Test
	public void testEvaluate() {
		LocalDate effectivityDate = LocalDate.now();
		Plan plan = Universe.getPlan(-900003, effectivityDate);
		plan.resetEvaluation();
		assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
		Collection<RuleVersion> evaluatedRuleVersions = plan.evaluate(null);
		assertTrue(plan.isBranched());
		assertNotNull(evaluatedRuleVersions);
		assertFalse(evaluatedRuleVersions.isEmpty());
		assertEquals(4, evaluatedRuleVersions.size());
		for (RuleVersion ruleVersion: evaluatedRuleVersions) {
			switch (ruleVersion.getId()) {
				case -900005:
				case -900006: 
					assertEquals(1500, ruleVersion.getValue());
					break;
				case -900007:
					assertEquals(1000, ruleVersion.getValue());
					break;
				case -900008:
					assertEquals(500, ruleVersion.getValue());
					break;
				default:
					fail("Unexepected evaluated rule version : " + ruleVersion.getId());
			}
		}
	}
	
	/**
	 * Tests {@link Plan#evaluate(Object, String, boolean)}
	 */
	@Test
	public void testEvaluateRule900002() {
		LocalDate effectivityDate = LocalDate.now();
		Plan plan = Universe.getPlan(-900003, effectivityDate);
		plan.resetEvaluation();
		assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
		Collection<RuleVersion> evaluatedRuleVersions = plan.evaluate(null, "-900002", false);
		assertFalse(plan.isBranched());
		assertNotNull(evaluatedRuleVersions);
		assertFalse(evaluatedRuleVersions.isEmpty());
		assertEquals(1, evaluatedRuleVersions.size());
		for (RuleVersion ruleVersion: evaluatedRuleVersions) {
			switch (ruleVersion.getId()) {
				case -900005: 
					assertEquals(1500, ruleVersion.getValue());
					break;
				default:
					fail("Unexepected evaluated rule version : " + ruleVersion.getId());
			}
		}
	}
	
	/**
	 * Tests {@link Plan#evaluate(Object, String, boolean)}
	 */
	@Test
	public void testEvaluateRule900003() {
		LocalDate effectivityDate = LocalDate.now();
		Plan plan = Universe.getPlan(-900003, effectivityDate);
		plan.resetEvaluation();
		assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
		Collection<RuleVersion> evaluatedRuleVersions = plan.evaluate(null, "-900003", false);
		assertFalse(plan.isBranched());
		assertNotNull(evaluatedRuleVersions);
		assertFalse(evaluatedRuleVersions.isEmpty());
		assertEquals(3, evaluatedRuleVersions.size());
		for (RuleVersion ruleVersion: evaluatedRuleVersions) {
			switch (ruleVersion.getId()) {
				case -900006: 
					assertEquals(1500, ruleVersion.getValue());
					break;
				case -900007:
					assertEquals(1000, ruleVersion.getValue());
					break;
				case -900008:
					assertEquals(500, ruleVersion.getValue());
					break;
				default:
					fail("Unexepected evaluated rule version : " + ruleVersion.getId());
			}
		}
	}
	
}
