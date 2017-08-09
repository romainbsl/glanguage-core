package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link RuleDefinition}
 * 
 * @author DUPIREFR
 */
public class RuleDefinitionTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleDefinition#getVersion(LocalDate, LocalDateTime)}
	 * when no rule version is matching, neither are its rule sets
	 */
	@Test
	public void testGetVersionNoMatching() {
		LocalDate effective = LocalDate.of(2015, 1, 1);
		LocalDateTime observation = LocalDateTime.of(2016, 2, 20, 0, 0);

		RuleDefinition ruleDefinition = new RuleDefinition();

		Set<RuleSetVersion> ruleSetVersions = new HashSet<>();
		RuleSetVersion ruleSetVersion = mock(RuleSetVersion.class);
		when(ruleSetVersion.isInProduction(observation)).thenReturn(false);
		ruleSetVersions.add(ruleSetVersion);

		RuleVersion firstRuleVersion = mock(RuleVersion.class);
		when(firstRuleVersion.isEffective(effective)).thenReturn(false);
		when(firstRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(firstRuleVersion);

		RuleVersion secondRuleVersion = mock(RuleVersion.class);
		when(secondRuleVersion.isEffective(effective)).thenReturn(false);
		when(secondRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(secondRuleVersion);

		assertNull(ruleDefinition.getVersion(effective, observation));
	}

	/**
	 * Tests {@link RuleDefinition#getVersion(LocalDate, LocalDateTime)}
	 * when a rule version is matching but not its rule sets
	 */
	@Test
	public void testGetVersionMatchingRuleVersionNoMatchingRuleSetVersion() {
		LocalDate effective = LocalDate.of(2015, 1, 1);
		LocalDateTime observation = LocalDateTime.of(2016, 2, 20, 0, 0);

		RuleDefinition ruleDefinition = new RuleDefinition();

		Set<RuleSetVersion> ruleSetVersions = new HashSet<>();
		RuleSetVersion ruleSetVersion = mock(RuleSetVersion.class);
		when(ruleSetVersion.isInProduction(observation)).thenReturn(false);
		ruleSetVersions.add(ruleSetVersion);

		RuleVersion firstRuleVersion = mock(RuleVersion.class);
		when(firstRuleVersion.isEffective(effective)).thenReturn(false);
		when(firstRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(firstRuleVersion);

		RuleVersion secondRuleVersion = mock(RuleVersion.class);
		when(secondRuleVersion.isEffective(effective)).thenReturn(true);
		when(secondRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(secondRuleVersion);

		assertNull(ruleDefinition.getVersion(effective, observation));
	}

	/**
	 * Tests {@link RuleDefinition#getVersion(LocalDate, LocalDateTime)}
	 * when no rule version is matching, but their rule sets are
	 */
	@Test
	public void testGetVersionMatchingRuleSetVersionNoMatchingRuleVersion() {
		LocalDate effective = LocalDate.of(2015, 1, 1);
		LocalDateTime observation = LocalDateTime.of(2016, 2, 20, 0, 0);

		RuleDefinition ruleDefinition = new RuleDefinition();

		Set<RuleSetVersion> ruleSetVersions = new HashSet<>();
		RuleSetVersion ruleSetVersion = mock(RuleSetVersion.class);
		when(ruleSetVersion.isInProduction(observation)).thenReturn(true);
		ruleSetVersions.add(ruleSetVersion);

		RuleVersion firstRuleVersion = mock(RuleVersion.class);
		when(firstRuleVersion.isEffective(effective)).thenReturn(false);
		when(firstRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(firstRuleVersion);

		RuleVersion secondRuleVersion = mock(RuleVersion.class);
		when(secondRuleVersion.isEffective(effective)).thenReturn(false);
		when(secondRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(secondRuleVersion);

		assertNull(ruleDefinition.getVersion(effective, observation));
	}

	/**
	 * Tests {@link RuleDefinition#getVersion(LocalDate, LocalDateTime)}
	 * when a rule version and its rule sets are matching
	 */
	@Test
	public void testGetVersionMatching() {
		LocalDate effective = LocalDate.of(2015, 1, 1);
		LocalDateTime observation = LocalDateTime.of(2016, 2, 20, 0, 0);

		RuleDefinition ruleDefinition = new RuleDefinition();

		Set<RuleSetVersion> ruleSetVersions = new HashSet<>();
		RuleSetVersion ruleSetVersion = mock(RuleSetVersion.class);
		when(ruleSetVersion.isInProduction(observation)).thenReturn(true);
		ruleSetVersions.add(ruleSetVersion);

		RuleVersion firstRuleVersion = mock(RuleVersion.class);
		when(firstRuleVersion.isEffective(effective)).thenReturn(false);
		when(firstRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(firstRuleVersion);

		RuleVersion secondRuleVersion = mock(RuleVersion.class);
		when(secondRuleVersion.isEffective(effective)).thenReturn(true);
		when(secondRuleVersion.getRuleSetVersions()).thenReturn(ruleSetVersions);
		ruleDefinition.getVersions().add(secondRuleVersion);

		assertNotNull(ruleDefinition.getVersion(effective, observation));
		assertEquals(secondRuleVersion, ruleDefinition.getVersion(effective, observation));
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
