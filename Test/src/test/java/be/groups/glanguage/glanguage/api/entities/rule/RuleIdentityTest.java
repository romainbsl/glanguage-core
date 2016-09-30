package be.groups.glanguage.glanguage.api.entities.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link RuleIdentity}
 * 
 * @author DUPIREFR
 */
public class RuleIdentityTest {

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
	 * Tests {@link RuleIdentity} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleIdentity ruleIdentity = em.find(RuleIdentity.class, 900000);

		/* Checking entity */
		assertNotNull(ruleIdentity);

		assertEquals(900000, ruleIdentity.getId());

		/* Checking relationships */
		assertNotNull(ruleIdentity.getRuleDefinitions());
		assertEquals(2, ruleIdentity.getRuleDefinitions().size());
		assertEquals(2, ruleIdentity.getRuleDefinitions().stream().map(d -> d.getId()).distinct().count());

		List<Integer> ruleDefinitionIds = Arrays.asList(900000, 900001);
		ruleIdentity.getRuleDefinitions().forEach(d -> {
			assertTrue(ruleDefinitionIds.contains(d.getId()));
		});
	}

	/**
	 * Tests {@link RuleIdentity#getDefinition(Collection)} when no specific
	 * definition is defined
	 */
	@Test
	public void testGetDefinitionNoSpecificDefinition() {
		RuleIdentity ruleIdentity = new RuleIdentity();

		List<RuleDefinitionParameter> ruleDefinitionParameters = new ArrayList<>();
		ruleDefinitionParameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "100000"));

		RuleDefinition ruleDefinition = mock(RuleDefinition.class);
		when(ruleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);

		ruleIdentity.getRuleDefinitions().add(ruleDefinition);

		assertNotNull(ruleIdentity.getDefinition(ruleDefinitionParameters));
		assertEquals(ruleDefinition, ruleIdentity.getDefinition(ruleDefinitionParameters));
	}

	/**
	 * Tests {@link RuleIdentity#getDefinition(Collection)} when specific
	 * definitions are defined, but none is matching criteria
	 */
	@Test
	public void testGetDefinitionNoMatchingDefinition() {
		RuleIdentity ruleIdentity = new RuleIdentity();

		List<RuleDefinitionParameter> ruleDefinitionParameters = new ArrayList<>();
		ruleDefinitionParameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "100000"));

		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);

		RuleDefinition employerRuleDefinition = mock(RuleDefinition.class);
		when(employerRuleDefinition.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerRuleDefinition.matches(ruleDefinitionParameters)).thenReturn(false);

		ruleIdentity.getRuleDefinitions().add(defaultRuleDefinition);
		ruleIdentity.getRuleDefinitions().add(employerRuleDefinition);

		assertNotNull(ruleIdentity.getDefinition(ruleDefinitionParameters));
		assertEquals(defaultRuleDefinition, ruleIdentity.getDefinition(ruleDefinitionParameters));
	}

	/**
	 * Tests {@link RuleIdentity#getDefinition(Collection)} when specific
	 * definitions are defined and one is matching criteria
	 */
	@Test
	public void testGetDefinitionMatching() {
		RuleIdentity ruleIdentity = new RuleIdentity();

		List<RuleDefinitionParameter> ruleDefinitionParameters = new ArrayList<>();
		ruleDefinitionParameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "100000"));

		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);

		RuleDefinition employerRuleDefinition = mock(RuleDefinition.class);
		when(employerRuleDefinition.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerRuleDefinition.matches(ruleDefinitionParameters)).thenReturn(true);

		ruleIdentity.getRuleDefinitions().add(defaultRuleDefinition);
		ruleIdentity.getRuleDefinitions().add(employerRuleDefinition);

		assertNotNull(ruleIdentity.getDefinition(ruleDefinitionParameters));
		assertEquals(employerRuleDefinition, ruleIdentity.getDefinition(ruleDefinitionParameters));
	}

}
