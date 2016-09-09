package be.groups.glanguage.glanguage.api.entities.ruleset;

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

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDescription;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link RuleSetVersion}
 * 
 * @author DUPIREFR
 */
public class RuleSetVersionTest {

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
	 * Tests {@link RuleSetVersion} JPA mapping
	 */
	@Test
	public void testJpaMapping() {
		RuleSetVersion ruleSetVersion = em.find(RuleSetVersion.class, 900001);

		/* Checking entity */
		assertNotNull(ruleSetVersion);

		assertEquals(900001, ruleSetVersion.getId());

		assertEquals(LocalDateTime.of(2016, 9, 7, 9, 0), ruleSetVersion.getExploitationStartDate());
		assertEquals("1.0.1", ruleSetVersion.getVersion());

		assertEquals("dupirefr", ruleSetVersion.getAuthor());
		assertEquals("hotfix", ruleSetVersion.getLabel());
		assertEquals(RuleSetVersionStatus.PROD, ruleSetVersion.getStatus());

		/* Checking relationships */
		assertNotNull(ruleSetVersion.getRuleSet());
		assertEquals(900000, ruleSetVersion.getRuleSet().getId());

		assertNotNull(ruleSetVersion.getParent());
		assertEquals(900000, ruleSetVersion.getParent().getId());

		assertNotNull(ruleSetVersion.getChildren());
		assertEquals(1, ruleSetVersion.getChildren().size());
		assertEquals(900002, ((RuleSetVersion) ruleSetVersion.getChildren().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getIncludes());
		assertEquals(1, ruleSetVersion.getIncludes().size());
		assertEquals(900003, ((RuleSetVersion) ruleSetVersion.getIncludes().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getIncludedIn());
		assertEquals(1, ruleSetVersion.getIncludedIn().size());
		assertEquals(900004, ((RuleSetVersion) ruleSetVersion.getIncludedIn().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getRuleVersions());
		assertEquals(4, ruleSetVersion.getRuleVersions().size());
		assertEquals(4, ruleSetVersion.getRuleVersions().stream().map(v -> v.getId()).distinct().count());

		List<Integer> ruleVersionIds = Arrays.asList(900000, 900002, 900003, 900004);
		ruleSetVersion.getRuleVersions().forEach(v -> {
			assertTrue(ruleVersionIds.contains(v.getId()));
		});
	}

	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersion(String, LocalDateTime)}
	 * when a rule version is found
	 */
	@Test
	public void testGetDefaultRuleVersionFound() {
		String code = "r1";
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);

		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);

		RuleDefinition employerRuleDefinition = mock(RuleDefinition.class);
		when(employerRuleDefinition.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);

		RuleDescription ruleDescription = mock(RuleDescription.class);
		when(ruleDescription.getCode()).thenReturn("r1");

		RuleVersion defaultRuleVersionRightEffectivity = mock(RuleVersion.class);
		when(defaultRuleVersionRightEffectivity.getRuleDescription()).thenReturn(ruleDescription);
		when(defaultRuleVersionRightEffectivity.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersionRightEffectivity.isEffective(effectivity)).thenReturn(true);

		RuleVersion defaultRuleVersionWrongEffectivity = mock(RuleVersion.class);
		when(defaultRuleVersionWrongEffectivity.getRuleDescription()).thenReturn(ruleDescription);
		when(defaultRuleVersionWrongEffectivity.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersionWrongEffectivity.isEffective(effectivity)).thenReturn(false);

		RuleVersion employerRuleVersion = mock(RuleVersion.class);
		when(employerRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(employerRuleVersion.getRuleDefinition()).thenReturn(employerRuleDefinition);
		when(employerRuleVersion.isEffective(effectivity)).thenReturn(true);

		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersionRightEffectivity);
		ruleSetVersion.getRuleVersions().add(defaultRuleVersionWrongEffectivity);
		ruleSetVersion.getRuleVersions().add(employerRuleVersion);

		RuleVersion foundRuleVersion = ruleSetVersion.getDefaultRuleVersion(code, effectivity);

		assertNotNull(foundRuleVersion);
		assertEquals(defaultRuleVersionRightEffectivity, foundRuleVersion);
	}

	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersion(String, LocalDateTime)}
	 * when a rule version exists for that code and the default definition but
	 * is not effective
	 */
	@Test
	public void testGetDefaultRuleVersionNoEffectiveRuleVersion() {
		String code = "r1";
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);

		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);

		RuleDefinition employerRuleDefinition = mock(RuleDefinition.class);
		when(employerRuleDefinition.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);

		RuleDescription ruleDescription = mock(RuleDescription.class);
		when(ruleDescription.getCode()).thenReturn("r1");

		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(false);

		RuleVersion employerRuleVersion = mock(RuleVersion.class);
		when(employerRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(employerRuleVersion.getRuleDefinition()).thenReturn(employerRuleDefinition);
		when(employerRuleVersion.isEffective(effectivity)).thenReturn(true);

		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		ruleSetVersion.getRuleVersions().add(employerRuleVersion);

		RuleVersion foundRuleVersion = ruleSetVersion.getDefaultRuleVersion(code, effectivity);

		assertNull(foundRuleVersion);
	}

	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersion(String, LocalDateTime)}
	 * when a rule version exists for that code but not for the default
	 * definition
	 */
	@Test
	public void testGetDefaultRuleVersionNoDefaultRuleVersion() {
		String code = "r1";
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);

		RuleDefinition employerRuleDefinition = mock(RuleDefinition.class);
		when(employerRuleDefinition.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);

		RuleDescription ruleDescription = mock(RuleDescription.class);
		when(ruleDescription.getCode()).thenReturn("r1");

		RuleVersion employerRuleVersion = mock(RuleVersion.class);
		when(employerRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(employerRuleVersion.getRuleDefinition()).thenReturn(employerRuleDefinition);
		when(employerRuleVersion.isEffective(effectivity)).thenReturn(true);

		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(employerRuleVersion);

		RuleVersion foundRuleVersion = ruleSetVersion.getDefaultRuleVersion(code, effectivity);

		assertNull(foundRuleVersion);
	}

	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersion(String, LocalDateTime)}
	 * when no rule version exists for that code
	 */
	@Test
	public void testGetDefaultRuleVersionNoRuleVersionForCode() {
		String code = "r1";
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);

		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);

		RuleDescription ruleDescription = mock(RuleDescription.class);
		when(ruleDescription.getCode()).thenReturn("r2");

		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(true);

		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);

		RuleVersion foundRuleVersion = ruleSetVersion.getDefaultRuleVersion(code, effectivity);

		assertNull(foundRuleVersion);
	}

	/**
	 * Tests
	 * {@link RuleSetVersion#getDefinedRuleVersion(LocalDateTime, java.util.Collection)}
	 * when a rule is found
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDefinedRuleVersionFound() {
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);
		List<RuleDefinitionParameter> ruleDefinitionParameters = (List<RuleDefinitionParameter>) mock(List.class);

		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.match(ruleDefinitionParameters)).thenReturn(false);

		RuleDefinition customRuleDefinition = mock(RuleDefinition.class);
		when(customRuleDefinition.match(ruleDefinitionParameters)).thenReturn(true);

		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(false);

		RuleVersion customRuleVersionRightEffectivity = mock(RuleVersion.class);
		when(customRuleVersionRightEffectivity.getRuleDefinition()).thenReturn(customRuleDefinition);
		when(customRuleVersionRightEffectivity.isEffective(effectivity)).thenReturn(true);

		RuleVersion customRuleVersionWrongEffectivity = mock(RuleVersion.class);
		when(customRuleVersionWrongEffectivity.getRuleDefinition()).thenReturn(customRuleDefinition);
		when(customRuleVersionWrongEffectivity.isEffective(effectivity)).thenReturn(false);

		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		ruleSetVersion.getRuleVersions().add(customRuleVersionRightEffectivity);
		ruleSetVersion.getRuleVersions().add(customRuleVersionWrongEffectivity);

		RuleVersion foundRuleVersion = ruleSetVersion.getDefinedRuleVersion(effectivity, ruleDefinitionParameters);

		assertNotNull(foundRuleVersion);
		assertEquals(customRuleVersionRightEffectivity, foundRuleVersion);
	}

	/**
	 * Tests
	 * {@link RuleSetVersion#getDefinedRuleVersion(LocalDateTime, java.util.Collection)}
	 * when no effective rule version is found
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDefinedRuleVersionNoEffectiveRuleVersion() {
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);
		List<RuleDefinitionParameter> ruleDefinitionParameters = (List<RuleDefinitionParameter>) mock(List.class);

		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.match(ruleDefinitionParameters)).thenReturn(false);

		RuleDefinition customRuleDefinition = mock(RuleDefinition.class);
		when(customRuleDefinition.match(ruleDefinitionParameters)).thenReturn(true);

		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(false);

		RuleVersion customRuleVersion = mock(RuleVersion.class);
		when(customRuleVersion.getRuleDefinition()).thenReturn(customRuleDefinition);
		when(customRuleVersion.isEffective(effectivity)).thenReturn(false);

		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		ruleSetVersion.getRuleVersions().add(customRuleVersion);

		RuleVersion foundRuleVersion = ruleSetVersion.getDefinedRuleVersion(effectivity, ruleDefinitionParameters);

		assertNull(foundRuleVersion);
	}

	/**
	 * Tests
	 * {@link RuleSetVersion#getDefinedRuleVersion(LocalDateTime, java.util.Collection)}
	 * when no definition is found
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDefinedRuleVersionNoMatchingDefinition() {
		LocalDateTime effectivity = LocalDateTime.of(2015, 1, 1, 0, 0);
		List<RuleDefinitionParameter> ruleDefinitionParameters = (List<RuleDefinitionParameter>) mock(List.class);

		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.match(ruleDefinitionParameters)).thenReturn(false);

		RuleDefinition customRuleDefinition = mock(RuleDefinition.class);
		when(customRuleDefinition.match(ruleDefinitionParameters)).thenReturn(false);

		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(false);

		RuleVersion customRuleVersion = mock(RuleVersion.class);
		when(customRuleVersion.getRuleDefinition()).thenReturn(customRuleDefinition);
		when(customRuleVersion.isEffective(effectivity)).thenReturn(true);

		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		ruleSetVersion.getRuleVersions().add(customRuleVersion);

		RuleVersion foundRuleVersion = ruleSetVersion.getDefinedRuleVersion(effectivity, ruleDefinitionParameters);

		assertNull(foundRuleVersion);
	}
}
