package be.groups.glanguage.glanguage.api.entities.rule.definition;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher.DefinitionMatcherStrategy;

/**
 * Test class for {@link DefinitionMatcher}
 * 
 * @author DUPIREFR
 */
public class DefinitionMatcherTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST)} when both collections are
	 * empty
	 */
	@Test
	public void testMatchAtLeastBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST)} when first collection is
	 * empty, but not the second
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
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST))} when second collection is
	 * empty, but not the first
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
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST)} when both collections
	 * contains elements, but levels don't match
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
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST)} when both collections
	 * contains elements and levels match but not values
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
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST)} when both collections
	 * contains elements and levels and values match
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
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL)} when both
	 * collections are empty
	 */
	@Test
	public void testMatchAtLeastOneByLevelBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL)} when first
	 * collection is empty, but not the second
	 */
	@Test
	public void testMatchAtLeastOneByLevelFirstEmptySecondNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);
		
		assertTrue(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests
	 * {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL)} when second
	 * collection is empty, but not the first
	 */
	@Test
	public void testMatchAtLeastOneByLevelSecondEmptyFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);
		
		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		
		assertFalse(DefinitionMatcher.matches(definition, criteria, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL));
	}
	
	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL)} when both
	 * collections contains elements, but levels don't match
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
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL)} when both
	 * collections contains elements and levels match but not values
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
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL)} when both
	 * collections contains elements and levels and values match
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
	
}
