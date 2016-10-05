/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.rule.definition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;

/**
 * 
 * @author michotte
 */
public class DefinitionMatcher {
	
	/**
	 * Get the {@link RuleDefinition} that best matches to the collection of {@link RuleDefinitionParameters} {@code parameters}<br>
	 * 
	 * It is assumed that the collection of {@link RuleDefinition} {@code ruleDefinitions} contains only one {@link RuleDefinition}
	 * which level is {@link DefinitionLevel#DEFAULT}, if not, if the default {@link RuleDefinition} as to be returned, one
	 * {@link RuleDefinition} is selected arbitrarily among the {@link RuleDefinition}'s which level is {@link DefinitionLevel#DEFAULT}
	 * <br>
	 * 
	 * It is assumed that the collection of {@link RuleDefinitionParameters} {@code parameters} contains only one parameter of the same
	 * level, if not, for each present level, one parameter is selected arbitrarily among the parameters of the same level<br>
	 * 
	 * A {@link RuleDefinition} with more parameters than {@code parameters} will never match<br>
	 * 
	 * The {@link RuleDefinition} that best matches to the collection of {@link RuleDefinitionParameters} {@code parameters} is the one
	 * that have exactly the same parameters as {@code parameters} or, if it doesn't exist, the one that have the largest number of
	 * highest priority level parameters that matches the parameters of the collection {@code parameters}<br>
	 * 
	 * The priority order of levels is, from highest priority to lowest :
	 * <ol>
	 * <li>{@link DefinitionLevel#SOCIAL_SECRETARY}</li>
	 * <li>{@link DefinitionLevel#EMPLOYER}</li>
	 * <li>{@link DefinitionLevel#JOINT_COMMITTEE}</li>
	 * <li>{@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}</li>
	 * </ol>
	 * 
	 * <b>API Note :</b><br>
	 * Let {@code ruleDefinitions} contains :
	 * <ul>
	 * <li>RD.0 the DEFAULT {@link RuleDefinition} (with no parameters)
	 * <li>RD.1 a {@link RuleDefinition} with parameters :
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * </ul>
	 * </li>
	 * <li>RD.2 a {@link RuleDefinition} with parameters :
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * </ul>
	 * </li>
	 * <li>RD.3 a {@link RuleDefinition} with parameters :
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
	 * </ul>
	 * </li>
	 * <li>RD.4 a {@link RuleDefinition} with parameters :
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 1</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * If {@code parameters} contains :
	 * <ol>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.1
	 * </li>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.2
	 * </li>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.3
	 * </li>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 1</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.4
	 * </li>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.2
	 * </li>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 1</li>
	 * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.3
	 * </li>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#EMPLOYER}, value 2</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.0
	 * </li>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 1</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.0
	 * </li>
	 * <li>
	 * <ul>
	 * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
	 * </ul>
	 * the returned {@link RuleDefinition} is RD.0
	 * </li>
	 * </ol>
	 * 
	 * @param id
	 * @param ruleDefinitions
	 * @param parameters
	 * @return the {@link RuleDefinition} in the collection of {@link RuleDefinitionParameters} {@code parameters} that best matches to
	 *         the collection of {@link RuleDefinitionParameters} {@code parameters} if it
	 *         exists, {@code getDefaultDefinition()} otherwise
	 * @throws RuntimeException if there is more than one {@link RuleDefinition} that best matches to the collection of
	 *         {@link RuleDefinitionParameters} {@code parameters}
	 * @see DefinitionLevel
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 * @see DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)
	 */
	public static RuleDefinition getBestMatch(Collection<RuleDefinition> ruleDefinitions,
			Collection<RuleDefinitionParameter> parameters) {
		RuleDefinition ruleDefinition = ruleDefinitions.stream()
				.filter(rd -> rd.matches(parameters, DefinitionMatcherStrategy.STRICTLY)).findFirst().orElse(null);
		if (ruleDefinition != null) {
			return ruleDefinition;
		} else {
			Collection<RuleDefinition> listToFilter = ruleDefinitions;
			List<RuleDefinition> filteredList = new ArrayList<>();
			List<RuleDefinitionParameter> actualParameters = new ArrayList<>();
			for (DefinitionLevel definitionLevel : DefinitionLevelFactory.getParameterDefinitionLevelsOrderedByPriority()) {
				RuleDefinitionParameter parameter =
						parameters.stream().filter(p -> p.getLevel().equals(definitionLevel)).findFirst().orElse(null);
				if (parameter != null) {
					actualParameters.add(parameter);
					filteredList = listToFilter.stream()
							.filter(rd -> rd.matches(actualParameters, DefinitionMatcherStrategy.AT_LEAST))
							.collect(Collectors.toList());
					if (filteredList.isEmpty()) {
						actualParameters.remove(actualParameters.size() - 1);
						listToFilter = ruleDefinitions;
					} else
						if (filteredList.size() == 1 && filteredList.get(0).matches(parameters, DefinitionMatcherStrategy.AT_MOST)) {
						return filteredList.get(0);
					} else {
						listToFilter = filteredList;
					}
				}
			}
			if (!filteredList.isEmpty()) {
				throw new DefinitionException(actualParameters, filteredList);
			} else {
				return ruleDefinitions.stream().filter(rd -> rd.getLevel().equals(DefinitionLevel.DEFAULT)).findFirst().orElse(null);
			}
		}
	}
	
	/**
	 * Does {@code first} parameter collection match {@code second} parameter collection using {@code strategy} as matching strategy ?
	 * 
	 * @param first
	 * @param second
	 * @param strategy
	 * @return
	 */
	public static boolean matches(Collection<RuleDefinitionParameter> first, Collection<RuleDefinitionParameter> second,
			DefinitionMatcherStrategy strategy) {
		switch (strategy) {
			case AT_LEAST:
				return matchesAtLeast(first, second);
			case AT_LEAST_ONE_BY_LEVEL:
				return matchesAtLeastOneByLevel(first, second);
			case AT_MOST:
				return matchesAtMost(first, second);
			case LOOSELY:
				return matchesLoosely(first, second);
			case STRICTLY:
				return matchesStrictly(first, second);
			default:
				return false;
		}
	}
	
	/**
	 * Does {@code first} parameter collection match {@code second} parameter collection ?<br>
	 * {@code first} matches {@code second} if :
	 * <ul>
	 * <li>{@code first} is an empty collection</li>
	 * <li>OR</li>
	 * <li>for each different level of {@code first}'s parameters, there is at least one parameter that matches one of the
	 * {@code second}'s parameters
	 * </li>
	 * </ul>
	 * 
	 * @param first
	 * @param second
	 * @return true if {@code first} matches {@code second}, false otherwise
	 * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
	 */
	private static boolean matchesAtLeastOneByLevel(Collection<RuleDefinitionParameter> first,
			Collection<RuleDefinitionParameter> second) {
		if (first.isEmpty()) {
			return true;
		} else {
			boolean match = true;
			List<RuleDefinitionParameter> socialSecretariatParameters = first.stream()
					.filter(f -> f.getLevel() == DefinitionLevel.SOCIAL_SECRETARY).collect(Collectors.toList());
			if (!socialSecretariatParameters.isEmpty()) {
				match = socialSecretariatParameters.stream().anyMatch(f -> second.stream().anyMatch(s -> f.matches(s)));
			}
			if (match) {
				List<RuleDefinitionParameter> employerParameters = first.stream()
						.filter(f -> f.getLevel() == DefinitionLevel.EMPLOYER).collect(Collectors.toList());
				if (!employerParameters.isEmpty()) {
					match = employerParameters.stream().anyMatch(f -> second.stream().anyMatch(s -> f.matches(s)));
				}
				if (match) {
					List<RuleDefinitionParameter> jointCommitteeParameters = first.stream()
							.filter(f -> f.getLevel() == DefinitionLevel.JOINT_COMMITTEE).collect(Collectors.toList());
					if (!jointCommitteeParameters.isEmpty()) {
						match = jointCommitteeParameters.stream()
								.anyMatch(f -> second.stream().anyMatch(s -> f.matches(s)));
					}
					if (match) {
						List<RuleDefinitionParameter> collectiveLaborAgreementParameters = first.stream()
								.filter(f -> f.getLevel() == DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT)
								.collect(Collectors.toList());
						if (!collectiveLaborAgreementParameters.isEmpty()) {
							match = collectiveLaborAgreementParameters.stream()
									.anyMatch(f -> second.stream().anyMatch(s -> f.matches(s)));
						}
					}
				}
			}
			return match;
		}
	}
	
	/**
	 * Does {@code first} parameter collection match at least {@code second} parameter collection ?<br>
	 * {@code first} matches at least {@code second} if {@code second} collection is a subset of {@code first} collection, in other
	 * words, they match if for each parameter of {@code second}'s collection there is a parameter of {@code first} collection that
	 * matches it
	 * 
	 * @param first
	 * @param second
	 * @return true if {@code first} matches {@code second}, false otherwise
	 * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
	 */
	private static boolean matchesAtLeast(Collection<RuleDefinitionParameter> first, Collection<RuleDefinitionParameter> second) {
		return second == null || second.isEmpty()
				|| (first != null && !first.isEmpty() && second.stream().allMatch(s -> first.stream().anyMatch(f -> f.matches(s))));
	}
	
	/**
	 * Does {@code first} parameter collection match at most {@code second} parameter collection ?<br>
	 * {@code first} matches at most {@code second} if {@code first} collection is a subset of {@code second} collection, in other
	 * words, they match if for each parameter of {@code first}'s collection there is a parameter of {@code second} collection that
	 * matches it
	 * 
	 * @param first
	 * @param second
	 * @return true if {@code first} matches {@code second}, false otherwise
	 * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
	 */
	private static boolean matchesAtMost(Collection<RuleDefinitionParameter> first, Collection<RuleDefinitionParameter> second) {
		return first == null || first.isEmpty()
				|| (second != null && !second.isEmpty() && first.stream().allMatch(f -> second.stream().anyMatch(s -> f.matches(s))));
	}
	
	/**
	 * Does {@code first} parameter collection match strictly {@code second} parameter collection ?<br>
	 * {@code first} matches strictly {@code second} if {@code first} and {@code second} collection are the same, in other words, if
	 * each parameter of {@code first} matches one parameter of {@code second} and vice versa
	 * 
	 * @param first
	 * @param second
	 * @return true if {@code first} matches {@code second}, false otherwise
	 * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
	 */
	private static boolean matchesStrictly(Collection<RuleDefinitionParameter> first, Collection<RuleDefinitionParameter> second) {
		return matchesAtLeast(first, second) && matchesAtMost(first, second);
	}
	
	/**
	 * Does {@code first} parameter collection match loosely {@code second} parameter collection ?<br>
	 * {@code first} matches loosely {@code second} if there is at least one parameter of {@code first} collection that matches one
	 * parameter of {@code second} collection
	 * 
	 * @param first
	 * @param second
	 * @return true if {@code first} matches {@code second}, false otherwise
	 * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
	 */
	private static boolean matchesLoosely(Collection<RuleDefinitionParameter> first, Collection<RuleDefinitionParameter> second) {
		return ((first == null || first.isEmpty()) && (second == null || second.isEmpty()))
				|| first.stream().anyMatch(f -> second.stream().anyMatch(s -> f.matches(s)));
	}
	
	public enum DefinitionMatcherStrategy {
		STRICTLY, LOOSELY, AT_MOST, AT_LEAST, AT_LEAST_ONE_BY_LEVEL;
	}
	
}
