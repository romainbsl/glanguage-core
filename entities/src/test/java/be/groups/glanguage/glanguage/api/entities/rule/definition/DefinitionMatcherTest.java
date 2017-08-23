package be.groups.glanguage.glanguage.api.entities.rule.definition;

import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher.DefinitionMatcherStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link DefinitionMatcher}
 * 
 * @author DUPIREFR
 */
public class DefinitionMatcherTest {
	
	/*
	 * Tests
	 */
	
	/*
	 * Tests for strategy DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL
	 */
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)}
	 * when both collections are null, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothNull() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first
	 * collection is null, but not the second, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelFirstNullSecondNot() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second
	 * collection is null, but not the first, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelSecondNullFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections are empty, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first
	 * collection is empty, but not the second, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelFirstEmptySecondNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second
	 * collection is empty, but not the first, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelSecondEmptyFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements, but levels don't match, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothNotEmptyLevelsNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels match but not values, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothNotEmptyLevelsMatchingValuesNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(false);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothNotEmptyMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but first has an additional parameter, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothNotEmptyFirstWithMoreParametersMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter jointCommitteeDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		when(jointCommitteeDefinitionParameter.matches(any())).thenReturn(false);
		definition.add(jointCommitteeDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but second has an additional parameter, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothNotEmptySecondWithMoreParametersNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but first has two parameters of same level, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothNotEmptyFirstWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter1 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter1.matches(employerCriteriaParameter)).thenReturn(false);
		definition.add(employerDefinitionParameter1);
		
		RuleDefinitionParameter employerDefinitionParameter2 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter2.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter2);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but second has two parameters of same level, and strategy is AT_LEAST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothNotEmptySecondWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter1 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter1);
		
		RuleDefinitionParameter employerCriteriaParameter2 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter2);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter1)).thenReturn(false);
		when(employerDefinitionParameter.matches(employerCriteriaParameter2)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/*
	 * Tests for strategy DefinitionMatcherStrategy.AT_LEAST
	 */
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections are null, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothNull() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first
	 * collection is null, but not the second, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastFirstNullSecondNot() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second
	 * collection is null, but not the first, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastSecondNullFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections are
	 * empty, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first collection is
	 * empty, but not the second, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastFirstEmptySecondNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second collection is
	 * empty, but not the first, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastSecondEmptyFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements, but levels don't match, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothNotEmptyLevelsNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels match but not values, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothNotEmptyLevelsMatchingValuesNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(false);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothNotEmptyMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, but first has an additional parameter, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothNotEmptyFirstWithMoreParametersMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter jointCommitteeDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		when(jointCommitteeDefinitionParameter.matches(any())).thenReturn(false);
		definition.add(jointCommitteeDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, but second has an additional parameter, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothNotEmptySecondWithMoreParametersNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but first has two parameters of same level, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothNotEmptyFirstWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter1 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter1.matches(employerCriteriaParameter)).thenReturn(false);
		definition.add(employerDefinitionParameter1);
		
		RuleDefinitionParameter employerDefinitionParameter2 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter2.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter2);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but second has two parameters of same level, and strategy is AT_LEAST
	 */
	@Test
	public void testMatchAtLeastBothNotEmptySecondWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter1 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter1);
		
		RuleDefinitionParameter employerCriteriaParameter2 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter2);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter1)).thenReturn(false);
		when(employerDefinitionParameter.matches(employerCriteriaParameter2)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/*
	 * Tests for strategy DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL
	 */
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections are null, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothNull() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first
	 * collection is null, but not the second, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelFirstNullSecondNot() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second
	 * collection is null, but not the first, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelSecondNullFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections are empty, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first
	 * collection is empty, but not the second, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelFirstEmptySecondNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second
	 * collection is empty, but not the first, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelSecondEmptyFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothNotEmptyLevelsNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels match but not values, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothNotEmptyLevelsMatchingValuesNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(false);
		criteria.add(employerCriteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothNotEmptyMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but first has an additional parameter, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothNotEmptyFirstWithMoreParametersNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);

		RuleDefinitionParameter jointCommitteeDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		definition.add(jointCommitteeDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but second has an additional parameter, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothNotEmptySecondWithMoreParametersNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but first has two parameters of same level, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothNotEmptyFirstWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter1 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter1);

		RuleDefinitionParameter employerDefinitionParameter2 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter2);

		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);

		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter1)).thenReturn(false);
		when(employerCriteriaParameter.matches(employerDefinitionParameter2)).thenReturn(true);
		criteria.add(employerCriteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but second has two parameters of same level, and strategy is AT_MOST_ONE_BY_LEVEL
	 */
	@Test
	public void testMatchAtMostOneByLevelBothNotEmptySecondWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);

		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);

		RuleDefinitionParameter employerCriteriaParameter1 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter1.matches(employerDefinitionParameter)).thenReturn(false);
		criteria.add(employerCriteriaParameter1);

		RuleDefinitionParameter employerCriteriaParameter2 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter2.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter2);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL));
	}
		
	/*
	 * Tests for strategy DefinitionMatcherStrategy.AT_MOST
	 */
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections are null, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothNull() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first
	 * collection is null, but not the second, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostFirstNullSecondNot() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second
	 * collection is null, but not the first, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostSecondNullFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections are
	 * empty, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first collection is
	 * empty, but not the second, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostFirstEmptySecondNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second collection is
	 * empty, but not the first, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostSecondEmptyFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements, but levels don't match, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothNotEmptyLevelsNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels match but not values, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothNotEmptyLevelsMatchingValuesNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(false);
		criteria.add(employerCriteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothNotEmptyMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);

		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter);

		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, but first has an additional parameter, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothNotEmptyFirstWithMoreParametersNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);

		RuleDefinitionParameter jointCommitteeDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
//		when(jointCommitteeDefinitionParameter.matches(any())).thenReturn(false);
		definition.add(jointCommitteeDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, but second has an additional parameter, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothNotEmptySecondWithMoreParametersMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but first has two parameters of same level, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothNotEmptyFirstWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter1 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter1);

		RuleDefinitionParameter employerDefinitionParameter2 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter2);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter1)).thenReturn(false);
		when(employerCriteriaParameter.matches(employerDefinitionParameter2)).thenReturn(true);
		criteria.add(employerCriteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but second has two parameters of same level, and strategy is AT_MOST
	 */
	@Test
	public void testMatchAtMostBothNotEmptySecondWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter1 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter1.matches(employerDefinitionParameter)).thenReturn(false);
		criteria.add(employerCriteriaParameter1);
		
		RuleDefinitionParameter employerCriteriaParameter2 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter2.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter2);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_MOST));
	}
	
	/*
	 * Tests for strategy DefinitionMatcherStrategy.STRICTLY
	 */
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections are null, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothNull() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first
	 * collection is null, but not the second, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyFirstNullSecondNot() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second
	 * collection is null, but not the first, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlySecondNullFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections are
	 * empty, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first collection is
	 * empty, but not the second, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyFirstEmptySecondNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second collection is
	 * empty, but not the first, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlySecondEmptyFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements, but levels don't match, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothNotEmptyLevelsNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels match but not values, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothNotEmptyLevelsMatchingValuesNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(false);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(false);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothNotEmptyMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);

		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter);


		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, but first has an additional parameter, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothNotEmptyFirstWithMoreParametersNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter jointCommitteeDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		when(jointCommitteeDefinitionParameter.matches(any())).thenReturn(false);
		definition.add(jointCommitteeDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, but second has an additional parameter, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothNotEmptySecondWithMoreParametersNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but first has two parameters of same level, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothNotEmptyFirstWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter1 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter1.matches(employerCriteriaParameter)).thenReturn(false);
		when(employerCriteriaParameter.matches(employerDefinitionParameter1)).thenReturn(false);
		definition.add(employerDefinitionParameter1);
		
		RuleDefinitionParameter employerDefinitionParameter2 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter2.matches(employerCriteriaParameter)).thenReturn(true);
		when(employerCriteriaParameter.matches(employerDefinitionParameter2)).thenReturn(true);
		definition.add(employerDefinitionParameter2);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but second has two parameters of same level, and strategy is STRICTLY
	 */
	@Test
	public void testMatchStrictlyBothNotEmptySecondWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter1 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter1);
		
		RuleDefinitionParameter employerCriteriaParameter2 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter2);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter1)).thenReturn(false);
		when(employerCriteriaParameter1.matches(employerDefinitionParameter)).thenReturn(false);
		when(employerDefinitionParameter.matches(employerCriteriaParameter2)).thenReturn(true);
		when(employerCriteriaParameter2.matches(employerDefinitionParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.STRICTLY));
	}
	
	/*
	 * Tests for strategy DefinitionMatcherStrategy.LOOSELY
	 */
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections are null, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothNull() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first
	 * collection is null, but not the second, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyFirstNullSecondNot() {
		Collection<RuleDefinitionParameter> definition = null;
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second
	 * collection is null, but not the first, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselySecondNullFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = null;
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections are
	 * empty, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when first collection is
	 * empty, but not the second, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyFirstEmptySecondNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when second collection is
	 * empty, but not the first, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselySecondEmptyFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements, but levels don't match, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothNotEmptyLevelsNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels match but not values, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothNotEmptyLevelsMatchingValuesNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(false);
		definition.add(employerDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothNotEmptyMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, but first has an additional parameter, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothNotEmptyFirstWithMoreParametersMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		RuleDefinitionParameter jointCommitteeDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		when(jointCommitteeDefinitionParameter.matches(any())).thenReturn(false);
		definition.add(jointCommitteeDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains elements and levels and values match, but second has an additional parameter, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothNotEmptySecondWithMoreParametersMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but first has two parameters of same level, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothNotEmptyFirstWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter1 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter1.matches(employerCriteriaParameter)).thenReturn(false);
		definition.add(employerDefinitionParameter1);
		
		RuleDefinitionParameter employerDefinitionParameter2 = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter2.matches(employerCriteriaParameter)).thenReturn(true);
		definition.add(employerDefinitionParameter2);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both
	 * collections contains elements and levels and values match, but second has two parameters of same level, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothNotEmptySecondWithTwoParametersOfSameLevelMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		RuleDefinitionParameter employerCriteriaParameter1 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter1.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter1);
		
		RuleDefinitionParameter employerCriteriaParameter2 = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter2.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		criteria.add(employerCriteriaParameter2);
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(true);
		definition.add(socialDefinitionParameter);
		
		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerDefinitionParameter.matches(employerCriteriaParameter1)).thenReturn(false);
		when(employerDefinitionParameter.matches(employerCriteriaParameter2)).thenReturn(true);
		definition.add(employerDefinitionParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)} when both collections
	 * contains one element that don't match each other, and strategy is LOOSELY
	 */
	@Test
	public void testMatchLooselyBothOneParameterNotMatching() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(socialCriteriaParameter);
		
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialDefinitionParameter.matches(socialCriteriaParameter)).thenReturn(false);
		definition.add(socialDefinitionParameter);
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.LOOSELY));
	}
	
	/*
	 * Tests DefinitionMatcher#getBestMacth(Collection, Collection)
	 */
	
	private RuleDefinitionParameter parameterEmployer1 = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1");
	private RuleDefinitionParameter parameterJointCommittee2 = new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2");
	private RuleDefinitionParameter parameterCollectiveLaborAgreement1 =
			new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1");
	private RuleDefinitionParameter parameterCollectiveLaborAgreement2 =
			new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2");
			
	/**
	 * Method to create a complex {@link RuleIdentity} with several {@link RuleDefinition}'s<br>
	 * 
	 * The {@link RuleDefinition}'s are :
	 * <ul>
	 * <li>ruleDefinition0 the DEFAULT {@link RuleDefinition} (with no parameters)
	 * <li>ruleDefinition1 a {@link RuleDefinition} with parameters :
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * </ul>
	 * </li>
	 * <li>ruleDefinition2 a {@link RuleDefinition} with parameters :
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * </ul>
	 * </li>
	 * <li>ruleDefinition3 a {@link RuleDefinition} with parameters :
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
	 * </ul>
	 * </li>
	 * <li>ruleDefinition4 a {@link RuleDefinition} with parameters :
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 1</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * @return
	 */
	private List<RuleDefinition> getRuleDefinitions() {
		RuleDefinition ruleDefinition0 = new RuleDefinition();
		ruleDefinition0.setId(0);
		
		RuleDefinition ruleDefinition1 = new RuleDefinition();
		ruleDefinition1.setId(1);
		ruleDefinition1.getDefinitionParameters().add(parameterEmployer1);
		
		RuleDefinition ruleDefinition2 = new RuleDefinition();
		ruleDefinition2.setId(2);
		ruleDefinition2.getDefinitionParameters().add(parameterEmployer1);
		ruleDefinition2.getDefinitionParameters().add(parameterJointCommittee2);
		
		RuleDefinition ruleDefinition3 = new RuleDefinition();
		ruleDefinition3.setId(3);
		ruleDefinition3.getDefinitionParameters().add(parameterEmployer1);
		ruleDefinition3.getDefinitionParameters().add(parameterCollectiveLaborAgreement2);
		
		RuleDefinition ruleDefinition4 = new RuleDefinition();
		ruleDefinition4.setId(4);
		ruleDefinition4.getDefinitionParameters().add(parameterEmployer1);
		ruleDefinition4.getDefinitionParameters().add(parameterJointCommittee2);
		ruleDefinition4.getDefinitionParameters().add(parameterCollectiveLaborAgreement1);
		
		return Arrays.asList(ruleDefinition0, ruleDefinition1, ruleDefinition2, ruleDefinition3, ruleDefinition4);
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch1() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(parameterEmployer1);
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(1, definition.getId());
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch2() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(parameterEmployer1);
		criteria.add(parameterJointCommittee2);
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(2, definition.getId());
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch3() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(parameterEmployer1);
		criteria.add(parameterCollectiveLaborAgreement2);
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(3, definition.getId());
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 1</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch4() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(parameterEmployer1);
		criteria.add(parameterJointCommittee2);
		criteria.add(parameterCollectiveLaborAgreement1);
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(4, definition.getId());
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch5() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(parameterEmployer1);
		criteria.add(parameterJointCommittee2);
		criteria.add(parameterCollectiveLaborAgreement2);
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(2, definition.getId());
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 1</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch6() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(parameterEmployer1);
		criteria.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		criteria.add(parameterCollectiveLaborAgreement2);
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(3, definition.getId());
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 2</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch7() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(0, definition.getId());
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 1</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch8() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(0, definition.getId());
	}
	
	/**
	 * Tests {@link DefinitionMatcher#getBestMatch(Collection, Collection)} with {@link RuleDefinition}'s of
	 * {@code complexRuleIdentity()} {@link RuleIdentity} and a collection of parameters containing :
	 * <ol>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * </ol>
	 */
	@Test
	public void testGetBestMatch9() {
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		criteria.add(parameterJointCommittee2);
		RuleDefinition definition = DefinitionMatcher.getBestMatch(getRuleDefinitions(), criteria);
		assertNotNull(definition);
		assertEquals(0, definition.getId());
	}
	
}
