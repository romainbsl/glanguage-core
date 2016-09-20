package be.groups.glanguage.glanguage.api.entities.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameterId;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTest;
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
	@Category(JpaMappingTest.class)
	public void testJpaMapping() {
		RuleDefinition ruleDefinition = em.find(RuleDefinition.class, 900001);

		/* Checking entity */
		assertNotNull(ruleDefinition);

		assertEquals(900001, ruleDefinition.getId());

		/* Checking relationships */
		assertEquals(900000, ruleDefinition.getRuleIdentity().getId());

		assertNotNull(ruleDefinition.getDefinitionParameters());
		assertEquals(2, ruleDefinition.getDefinitionParameters().size());
		assertEquals(2, ruleDefinition.getDefinitionParameters().stream().map(p -> p.getId()).distinct().count());

		RuleDefinitionParameterId firstRuleDefinitionParameterId = new RuleDefinitionParameterId();
		firstRuleDefinitionParameterId.setLevelId(2);
		firstRuleDefinitionParameterId.setRuleDefinitionId(900001);
		firstRuleDefinitionParameterId.setValue("100000");

		RuleDefinitionParameterId secondtRuleDefinitionParameterId = new RuleDefinitionParameterId();
		secondtRuleDefinitionParameterId.setLevelId(3);
		secondtRuleDefinitionParameterId.setRuleDefinitionId(900001);
		secondtRuleDefinitionParameterId.setValue("355");

		List<RuleDefinitionParameterId> ruleDefinitionParameterIds = Arrays.asList(firstRuleDefinitionParameterId,
				secondtRuleDefinitionParameterId);
		ruleDefinition.getDefinitionParameters().forEach(p -> {
			assertTrue(ruleDefinitionParameterIds.contains(p.getId()));
		});

		assertNotNull(ruleDefinition.getVersions());
		assertEquals(1, ruleDefinition.getVersions().size());
		assertEquals(900003, ruleDefinition.getVersions().get(0).getId());
	}

	/**
	 * Tests {@link RuleDefinition#getVersion(LocalDateTime, LocalDateTime)}
	 * when no rule version is matching, neither are its rule sets
	 */
	@Test
	public void testGetVersionNoMatching() {
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);
		LocalDateTime observation = LocalDateTime.of(2016, 2, 20, 0, 0);

		RuleDefinition ruleDefinition = new RuleDefinition();

		Set<RuleSetVersion> ruleSetVersions = new HashSet<>();
		RuleSetVersion ruleSetVersion = mock(RuleSetVersion.class);
		when(ruleSetVersion.isInExploitation(observation)).thenReturn(false);
		ruleSetVersions.add(ruleSetVersion);

		RuleVersion firstRuleVersion = mock(RuleVersion.class);
		when(firstRuleVersion.isEffective(effectivity)).thenReturn(false);
		when(firstRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(firstRuleVersion);

		RuleVersion secondRuleVersion = mock(RuleVersion.class);
		when(secondRuleVersion.isEffective(effectivity)).thenReturn(false);
		when(secondRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(secondRuleVersion);

		assertNull(ruleDefinition.getVersion(effectivity, observation));
	}

	/**
	 * Tests {@link RuleDefinition#getVersion(LocalDateTime, LocalDateTime)}
	 * when a rule version is matching but not its rule sets
	 */
	@Test
	public void testGetVersionMatchingRuleVersionNoMatchingRuleSetVersion() {
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);
		LocalDateTime observation = LocalDateTime.of(2016, 2, 20, 0, 0);

		RuleDefinition ruleDefinition = new RuleDefinition();

		Set<RuleSetVersion> ruleSetVersions = new HashSet<>();
		RuleSetVersion ruleSetVersion = mock(RuleSetVersion.class);
		when(ruleSetVersion.isInExploitation(observation)).thenReturn(false);
		ruleSetVersions.add(ruleSetVersion);

		RuleVersion firstRuleVersion = mock(RuleVersion.class);
		when(firstRuleVersion.isEffective(effectivity)).thenReturn(false);
		when(firstRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(firstRuleVersion);

		RuleVersion secondRuleVersion = mock(RuleVersion.class);
		when(secondRuleVersion.isEffective(effectivity)).thenReturn(true);
		when(secondRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(secondRuleVersion);

		assertNull(ruleDefinition.getVersion(effectivity, observation));
	}

	/**
	 * Tests {@link RuleDefinition#getVersion(LocalDateTime, LocalDateTime)}
	 * when no rule version is matching, but their rule sets are
	 */
	@Test
	public void testGetVersionMatchingRuleSetVersionNoMatchingRuleVersion() {
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);
		LocalDateTime observation = LocalDateTime.of(2016, 2, 20, 0, 0);

		RuleDefinition ruleDefinition = new RuleDefinition();

		Set<RuleSetVersion> ruleSetVersions = new HashSet<>();
		RuleSetVersion ruleSetVersion = mock(RuleSetVersion.class);
		when(ruleSetVersion.isInExploitation(observation)).thenReturn(true);
		ruleSetVersions.add(ruleSetVersion);

		RuleVersion firstRuleVersion = mock(RuleVersion.class);
		when(firstRuleVersion.isEffective(effectivity)).thenReturn(false);
		when(firstRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(firstRuleVersion);

		RuleVersion secondRuleVersion = mock(RuleVersion.class);
		when(secondRuleVersion.isEffective(effectivity)).thenReturn(false);
		when(secondRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(secondRuleVersion);

		assertNull(ruleDefinition.getVersion(effectivity, observation));
	}

	/**
	 * Tests {@link RuleDefinition#getVersion(LocalDateTime, LocalDateTime)}
	 * when a rule version and its rule sets are matching
	 */
	@Test
	public void testGetVersionMatching() {
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);
		LocalDateTime observation = LocalDateTime.of(2016, 2, 20, 0, 0);

		RuleDefinition ruleDefinition = new RuleDefinition();

		Set<RuleSetVersion> ruleSetVersions = new HashSet<>();
		RuleSetVersion ruleSetVersion = mock(RuleSetVersion.class);
		when(ruleSetVersion.isInExploitation(observation)).thenReturn(true);
		ruleSetVersions.add(ruleSetVersion);

		RuleVersion firstRuleVersion = mock(RuleVersion.class);
		when(firstRuleVersion.isEffective(effectivity)).thenReturn(false);
		when(firstRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(firstRuleVersion);

		RuleVersion secondRuleVersion = mock(RuleVersion.class);
		when(secondRuleVersion.isEffective(effectivity)).thenReturn(true);
		when(secondRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(secondRuleVersion);

		assertNotNull(ruleDefinition.getVersion(effectivity, observation));
		assertEquals(secondRuleVersion, ruleDefinition.getVersion(effectivity, observation));
	}

	/**
	 * Tests {@link RuleDefinition#getLevel()} when no parameters are defined
	 */
	@Test
	public void testGetLevelNoParameters() {
		RuleDefinition ruleDefinition = new RuleDefinition();

		assertNotNull(ruleDefinition.getLevel());
		assertEquals(DefinitionLevel.DEFAULT, ruleDefinition.getLevel());
	}

	/**
	 * Tests {@link RuleDefinition#getLevel()} when a unique parameter is
	 * defined
	 */
	@Test
	public void testGetLevelOneParameter() {
		RuleDefinition ruleDefinition = new RuleDefinition();

		RuleDefinitionParameter ruleDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(ruleDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		ruleDefinition.getDefinitionParameters().add(ruleDefinitionParameter);

		assertNotNull(ruleDefinition.getLevel());
		assertEquals(DefinitionLevel.JOINT_COMMITTEE, ruleDefinition.getLevel());
	}

	/**
	 * Tests {@link RuleDefinition#getLevel()} when multiple parameters of the
	 * same level are defined
	 */
	@Test
	public void testGetLevelMultipleSameLevelParameters() {
		RuleDefinition ruleDefinition = new RuleDefinition();

		RuleDefinitionParameter firstRuleDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(firstRuleDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);

		RuleDefinitionParameter secondRuleDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(secondRuleDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);

		ruleDefinition.getDefinitionParameters().add(firstRuleDefinitionParameter);
		ruleDefinition.getDefinitionParameters().add(secondRuleDefinitionParameter);

		assertNotNull(ruleDefinition.getLevel());
		assertEquals(DefinitionLevel.JOINT_COMMITTEE, ruleDefinition.getLevel());
	}

	/**
	 * Tests {@link RuleDefinition#getLevel()} when multiple parameters of the
	 * different levels are defined
	 */
	@Test
	public void testGetLevelMultipleDifferentLevelsParameters() {
		RuleDefinition ruleDefinition = new RuleDefinition();

		RuleDefinitionParameter firstRuleDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(firstRuleDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);

		RuleDefinitionParameter secondRuleDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(secondRuleDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);

		ruleDefinition.getDefinitionParameters().add(firstRuleDefinitionParameter);
		ruleDefinition.getDefinitionParameters().add(secondRuleDefinitionParameter);

		assertNotNull(ruleDefinition.getLevel());
		assertEquals(DefinitionLevel.CUSTOM, ruleDefinition.getLevel());
	}

}