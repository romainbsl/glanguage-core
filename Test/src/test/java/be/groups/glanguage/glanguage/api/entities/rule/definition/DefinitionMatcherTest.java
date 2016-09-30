package be.groups.glanguage.glanguage.api.entities.rule.definition;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

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
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection)} when both
	 * collections are empty
	 */
	@Test
	public void testMatchBothEmpty() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		assertTrue(DefinitionMatcher.matches(definition, criteria));
	}

	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection)} when first
	 * collection is empty, but not the second
	 */
	@Test
	public void testMatchFirstEmptySecondNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();
		RuleDefinitionParameter criteriaParameter = mock(RuleDefinitionParameter.class);
		when(criteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		criteria.add(criteriaParameter);

		assertTrue(DefinitionMatcher.matches(definition, criteria));
	}

	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection)} when second
	 * collection is empty, but not the first
	 */
	@Test
	public void testMatchSecondEmptyFirstNot() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();
		RuleDefinitionParameter definitionParameter = mock(RuleDefinitionParameter.class);
		when(definitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(definitionParameter);

		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		assertFalse(DefinitionMatcher.matches(definition, criteria));
	}

	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection)} when both
	 * collections contains elements, but levels don't match
	 */
	@Test
	public void testMatchBothNotEmptyLevelsNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);

		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);

		RuleDefinitionParameter jointCommitteeCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(jointCommitteeCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.JOINT_COMMITTEE);
		criteria.add(jointCommitteeCriteriaParameter);

		assertFalse(DefinitionMatcher.matches(definition, criteria));
	}

	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection)} when both
	 * collections contains elements and levels match but not values
	 */
	@Test
	public void testMatchBothNotEmptyLevelsMatchingValuesNotMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);

		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);

		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(false);
		criteria.add(employerCriteriaParameter);

		assertFalse(DefinitionMatcher.matches(definition, criteria));
	}

	/**
	 * Tests {@link DefinitionMatcher#matches(Collection, Collection)} when both
	 * collections contains elements and levels and values match
	 */
	@Test
	public void testMatchBothNotEmptyMatching() {
		Collection<RuleDefinitionParameter> definition = new ArrayList<>();

		RuleDefinitionParameter socialDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(socialDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		definition.add(socialDefinitionParameter);

		RuleDefinitionParameter employerDefinitionParameter = mock(RuleDefinitionParameter.class);
		when(employerDefinitionParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		definition.add(employerDefinitionParameter);

		Collection<RuleDefinitionParameter> criteria = new ArrayList<>();

		RuleDefinitionParameter socialCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(socialCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.SOCIAL_SECRETARY);
		when(socialCriteriaParameter.matches(socialDefinitionParameter)).thenReturn(true);
		criteria.add(socialCriteriaParameter);

		RuleDefinitionParameter employerCriteriaParameter = mock(RuleDefinitionParameter.class);
		when(employerCriteriaParameter.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		when(employerCriteriaParameter.matches(employerDefinitionParameter)).thenReturn(true);
		criteria.add(employerCriteriaParameter);

		assertTrue(DefinitionMatcher.matches(definition, criteria));
	}

}
