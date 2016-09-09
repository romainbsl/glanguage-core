package be.groups.glanguage.glanguage.api.entities.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameterId;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link RuleDefinition}
 * 
 * @author DUPIREFR
 */
public class RuleDefinitionTest {

	/*
	 * Static fields
	 */
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

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleDefinition} JPA mapping
	 */
	@Test
	public void testJpaMapping() {
		RuleDefinition ruleDefinition = em.find(RuleDefinition.class, 900002);

		/* Checking entity */
		assertNotNull(ruleDefinition);

		assertEquals(900002, ruleDefinition.getId());

		/* Checking relationships */
		assertEquals(900001, ruleDefinition.getRuleIdentity().getId());

		assertNotNull(ruleDefinition.getDefinitionParameters());
		assertEquals(2, ruleDefinition.getDefinitionParameters().size());
		assertEquals(2, ruleDefinition.getDefinitionParameters().stream().map(p -> p.getId()).distinct().count());

		RuleDefinitionParameterId firstRuleDefinitionParameterId = new RuleDefinitionParameterId();
		firstRuleDefinitionParameterId.setLevelId(2);
		firstRuleDefinitionParameterId.setRuleDefinitionId(900002);
		firstRuleDefinitionParameterId.setValue("100000");

		RuleDefinitionParameterId secondtRuleDefinitionParameterId = new RuleDefinitionParameterId();
		secondtRuleDefinitionParameterId.setLevelId(3);
		secondtRuleDefinitionParameterId.setRuleDefinitionId(900002);
		secondtRuleDefinitionParameterId.setValue("355");

		List<RuleDefinitionParameterId> ruleDefinitionParameterIds = Arrays.asList(firstRuleDefinitionParameterId,
				secondtRuleDefinitionParameterId);
		ruleDefinition.getDefinitionParameters().forEach(p -> {
			assertTrue(ruleDefinitionParameterIds.contains(p.getId()));
		});

		assertNotNull(ruleDefinition.getVersions());
		assertEquals(2, ruleDefinition.getVersions().size());
		assertEquals(2, ruleDefinition.getVersions().stream().map(v -> v.getId()).distinct().count());
		
		List<Integer> ruleVersionIds = Arrays.asList(900000, 900001);
		ruleDefinition.getVersions().forEach(v -> {
			assertTrue(ruleVersionIds.contains(v.getId()));
		});
	}

}
