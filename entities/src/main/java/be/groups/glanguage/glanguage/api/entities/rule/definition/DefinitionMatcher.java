/**
 *
 */
package be.groups.glanguage.glanguage.api.entities.rule.definition;

import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author michotte
 */
public class DefinitionMatcher {

    /**
     * Get the {@link RuleDefinition} that best matches to the collection of {@link RuleDefinitionParameter} {@code
     * parameters}<br>
     * <p>
     * It is assumed that the collection of {@link RuleDefinition} {@code ruleDefinitions} contains only one
     * {@link RuleDefinition} which level is {@link DefinitionLevel#DEFAULT}, if not, if the default
     * {@link RuleDefinition} as to be returned, one {@link RuleDefinition} is selected arbitrarily among the
     * {@link RuleDefinition}'s which level is {@link DefinitionLevel#DEFAULT}
     * <p>
     * It is assumed that the collection of {@link RuleDefinitionParameter} {@code parameters} contains only one
     * parameter of the same level, if not, for each present level, one parameter is selected arbitrarily among the
     * parameters of the same level<br>
     * <p>
     * A {@link RuleDefinition} with more parameters than {@code parameters} will never match<br>
     * <p>
     * The {@link RuleDefinition} that best matches to the collection of {@link RuleDefinitionParameter} {@code
     * parameters} is the one that have exactly the same parameters as {@code parameters} or, if it doesn't exist,
     * the one that have the largest number of highest priority level parameters that matches the {@code parameters}<br>
     * <p>
     * The priority order of levels is, from highest priority to lowest :
     * <ol>
     * <li>{@link DefinitionLevel#SOCIAL_SECRETARY}</li>
     * <li>{@link DefinitionLevel#EMPLOYER}</li>
     * <li>{@link DefinitionLevel#JOINT_COMMITTEE}</li>
     * <li>{@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}</li>
     * </ol>
     * <p>
     * <b>API Note :</b><br>
     * Let {@code ruleDefinitions} contains :
     * <ol>
     * <li>RD.0 the DEFAULT {@link RuleDefinition} (with no parameters)
     * <li>RD.1 a {@link RuleDefinition} with parameters :
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * </ol>
     * </li>
     * <li>RD.2 a {@link RuleDefinition} with parameters :
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
     * </ol>
     * </li>
     * <li>RD.3 a {@link RuleDefinition} with parameters :
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
     * </ol>
     * </li>
     * <li>RD.4 a {@link RuleDefinition} with parameters :
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
     * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 1</li>
     * </ol>
     * </li>
     * </ol>
     * If {@code parameters} contains :
     * <ol>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.1
     * </li>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.2
     * </li>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.3
     * </li>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
     * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 1</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.4
     * </li>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
     * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.2
     * </li>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 1</li>
     * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 1</li>
     * <li>level {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}, value 2</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.3
     * </li>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#EMPLOYER}, value 2</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.0
     * </li>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 1</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.0
     * </li>
     * <li>
     * <ol>
     * <li>level {@link DefinitionLevel#JOINT_COMMITTEE}, value 2</li>
     * </ol>
     * the returned {@link RuleDefinition} is RD.0
     * </li>
     * </ol>
     *
     * @param ruleDefinitions the collection of {@link RuleDefinition} from which the best matching item has to be
     *                        returned
     * @param parameters      the collection of {@link RuleDefinitionParameter} to match against the {@code
     *                        ruleDefinitions}
     * @return the {@link RuleDefinition} in {@code ruleDefinitions} that best matches to the collection of
     * {@link RuleDefinitionParameter} {@code parameters} if it exists, or (one of) the default
     * {@link RuleDefinition}('s arbitrarily) if it exists, null otherwise
     * @see DefinitionLevel
     * @see RuleDefinitionParameter
     * @see DefinitionMatcherStrategy
     * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
     */
    public static RuleDefinition getBestMatch(Collection<RuleDefinition> ruleDefinitions,
                                              Collection<RuleDefinitionParameter> parameters) {
        RuleDefinition ruleDefinition = ruleDefinitions.stream().filter(rd -> rd
                .matches(parameters, DefinitionMatcherStrategy.STRICTLY)).findFirst().orElse(null);
        if (ruleDefinition != null) {
            return ruleDefinition;
        } else {
            List<RuleDefinition> listToFilter = new ArrayList<>(ruleDefinitions);
            List<RuleDefinition> filteredList = new ArrayList<>();
            List<RuleDefinitionParameter> actualParameters = new ArrayList<>();
            for (DefinitionLevel definitionLevel : DefinitionLevelFactory
                    .getParameterDefinitionLevelsOrderedByPriority()) {
                RuleDefinitionParameter parameter = parameters.stream().filter(p -> p.getLevel()
                        .equals(definitionLevel)).findFirst().orElse(null);
                if (parameter != null) {
                    actualParameters.add(parameter);
                    filteredList = listToFilter.stream().filter(rd -> rd
                            .matches(actualParameters, DefinitionMatcherStrategy.AT_LEAST_ONE_BY_LEVEL)).collect(
                            Collectors.toList());
                    if (filteredList.isEmpty()) {
                        actualParameters.remove(actualParameters.size() - 1);
                        filteredList = listToFilter;
                        listToFilter = new ArrayList<>(ruleDefinitions);
                    } else if (filteredList.size() == 1 && filteredList.get(0).matches(parameters,
                                                                                       DefinitionMatcherStrategy
                                                                                               .AT_MOST_ONE_BY_LEVEL)) {
                        return filteredList.get(0);
                    } else {
                        listToFilter = filteredList;
                    }
                }
            }
            if (!filteredList.isEmpty()) {
                Optional<RuleDefinition> definition = filteredList.stream().filter(d -> d
                        .matches(parameters, DefinitionMatcherStrategy.AT_MOST_ONE_BY_LEVEL)).findFirst();
                if (definition.isPresent()) {
                    return definition.get();
                }
            }

            // Return default definition
            return ruleDefinitions.stream().filter(rd -> rd.getLevel().equals(DefinitionLevel.DEFAULT)).findFirst()
                    .orElse(null);
        }
    }

    /**
     * Does {@code first} collection of {@link RuleDefinitionParameter} match {@code second} collection of
     * {@link RuleDefinitionParameter} using {@link DefinitionMatcherStrategy strategy} as matching strategy ?
     *
     * @param first    the collection of {@link RuleDefinitionParameter} to match against {@code seoond}
     * @param second   the collection of {@link RuleDefinitionParameter} to match against {@code first}
     * @param strategy the {@link DefinitionMatcherStrategy} to be used to match {@code first} against {@code second}
     * @return true if {@code first} matches {@code second} using {@code strategy}, false otherwise
     * @see RuleDefinitionParameter
     * @see DefinitionMatcherStrategy
     */
    public static boolean matches(Collection<RuleDefinitionParameter> first,
                                  Collection<RuleDefinitionParameter> second,
                                  DefinitionMatcherStrategy strategy) {
        switch (strategy) {
            case AT_LEAST:
                return matchesAtLeast(first, second);
            case AT_LEAST_ONE_BY_LEVEL:
                return matchesAtLeastOneByLevel(first, second);
            case AT_MOST:
                return matchesAtMost(first, second);
            case AT_MOST_ONE_BY_LEVEL:
                return matchesAtMostOneByLevel(first, second);
            case LOOSELY:
                return matchesLoosely(first, second);
            case STRICTLY:
                return matchesStrictly(first, second);
            default:
                return false;
        }
    }

    /**
     * Does {@code first} collection of {@link RuleDefinitionParameter} match at least one by level {@code second}
     * collection of {@link RuleDefinitionParameter} ?<br>
     * {@code first} matches at least one by level {@code second} if :
     * <ul>
     * <li>{@code second} is an empty collection</li>
     * <li>OR</li>
     * <li>for each different {@link DefinitionLevel} of {@code second}'s parameters, there is at least one parameter
     * that matches one of the {@code first}'s parameters
     * </li>
     * </ul>
     * This method corresponds to the {@link DefinitionMatcherStrategy#AT_LEAST_ONE_BY_LEVEL} strategy<br>
     * A call to this method is equivalent to a call to
     * {@link DefinitionMatcher#matchesAtMostOneByLevel(Collection, Collection)} with {@code first} and {@code
     * second} parameters in reverse order
     *
     * @param first  the collection of {@link RuleDefinitionParameter} to match against {@code seoond}
     * @param second the collection of {@link RuleDefinitionParameter} to match against {@code first}
     * @return true if {@code first} matches {@code second}, false otherwise
     * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
     */
    private static boolean matchesAtLeastOneByLevel(Collection<RuleDefinitionParameter> first,
                                                    Collection<RuleDefinitionParameter> second) {
        if (second == null || second.isEmpty()) {
            return true;
        } else if (first == null || first.isEmpty()) {
            return false;
        } else {
            boolean match = true;
            Iterator<DefinitionLevel> itDefinitionLevels = DefinitionLevelFactory
                    .getParameterDefinitionLevelsOrderedByPriority().iterator();
            while (match && itDefinitionLevels.hasNext()) {
                DefinitionLevel definitionLevel = itDefinitionLevels.next();
                List<RuleDefinitionParameter> parameters = second.stream().filter(s -> s.getLevel()
                        .equals(definitionLevel)).collect(Collectors.toList());
                if (!parameters.isEmpty()) {
                    match = parameters.stream().anyMatch(s -> first.stream().anyMatch(f -> f.matches(s)));
                }
            }
            return match;
        }
    }

    /**
     * Does {@code first} collection of {@link RuleDefinitionParameter} match at least {@code second} collection of
     * {@link RuleDefinitionParameter} ?<br>
     * {@code first} matches at least {@code second} if {@code second} collection is a subset of {@code first}
     * collection, in other words, they match if for each parameter of {@code second}'s collection there is a
     * parameter of {@code first} collection that matches it<br>
     * This method corresponds to the {@link DefinitionMatcherStrategy#AT_LEAST} strategy
     * A call to this method is equivalent to a call to
     * {@link DefinitionMatcher#matchesAtMost(Collection, Collection)} with {@code first} and {@code second}
     * parameters in reverse order
     *
     * @param first  the collection of {@link RuleDefinitionParameter} to match against {@code seoond}
     * @param second the collection of {@link RuleDefinitionParameter} to match against {@code first}
     * @return true if {@code first} matches {@code second}, false otherwise
     * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
     */
    private static boolean matchesAtLeast(Collection<RuleDefinitionParameter> first,
                                          Collection<RuleDefinitionParameter> second) {
        return second == null || second.isEmpty() || (first != null && !first.isEmpty() && second.stream()
                .allMatch(s -> first.stream().anyMatch(f -> f.matches(s))));
    }

    /**
     * Does {@code first} collection of {@link RuleDefinitionParameter} match at most one by level {@code second}
     * collection of {@link RuleDefinitionParameter} ?<br>
     * {@code first} matches at most one by level {@code second} if :
     * <ul>
     * <li>{@code first} is an empty collection</li>
     * <li>OR</li>
     * <li>for each different level of {@code first}'s parameters, there is at least one parameter that matches one
     * of the {@code second}'s parameters
     * </li>
     * </ul>
     * This method corresponds to the {@link DefinitionMatcherStrategy#AT_MOST_ONE_BY_LEVEL} strategy<br>
     * A call to this method is equivalent to a call to
     * {@link DefinitionMatcher#matchesAtLeastOneByLevel(Collection, Collection)} with {@code first} and {@code
     * second} parameters in reverse order
     *
     * @param first  the collection of {@link RuleDefinitionParameter} to match against {@code seoond}
     * @param second the collection of {@link RuleDefinitionParameter} to match against {@code first}
     * @return true if {@code first} matches {@code second}, false otherwise
     * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
     */
    private static boolean matchesAtMostOneByLevel(Collection<RuleDefinitionParameter> first,
                                                   Collection<RuleDefinitionParameter> second) {
        return matchesAtLeastOneByLevel(second, first);
    }

    /**
     * Does {@code first} collection of {@link RuleDefinitionParameter} match at most {@code second} collection of
     * {@link RuleDefinitionParameter} ?<br>
     * {@code first} matches at most {@code second} if {@code first} collection is a subset of {@code second}
     * collection, in other words, they match if for each parameter of {@code first}'s collection there is a
     * parameter of {@code second} collection that matches it<br>
     * This method corresponds to the {@link DefinitionMatcherStrategy#AT_MOST} strategy<br>
     * A call to this method is equivalent to a call to
     * {@link DefinitionMatcher#matchesAtLeast(Collection, Collection)} with {@code first} and {@code
     * second} parameters in reverse order
     *
     * @param first  the collection of {@link RuleDefinitionParameter} to match against {@code seoond}
     * @param second the collection of {@link RuleDefinitionParameter} to match against {@code first}
     * @return true if {@code first} matches {@code second}, false otherwise
     * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
     */
    private static boolean matchesAtMost(Collection<RuleDefinitionParameter> first,
                                         Collection<RuleDefinitionParameter> second) {
        return matchesAtLeast(second, first);
    }

    /**
     * Does {@code first} collection of {@link RuleDefinitionParameter} match strictly {@code second} collection of
     * {@link RuleDefinitionParameter} ?<br>
     * {@code first} matches strictly {@code second} if {@code first} and {@code second} collection are the same, in
     * other words, if each parameter of {@code first} matches one parameter of {@code second} and vice versa<br>
     * This method corresponds to the {@link DefinitionMatcherStrategy#STRICTLY} strategy
     *
     * @param first  the collection of {@link RuleDefinitionParameter} to match against {@code seoond}
     * @param second the collection of {@link RuleDefinitionParameter} to match against {@code first}
     * @return true if {@code first} matches {@code second}, false otherwise
     * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
     */
    private static boolean matchesStrictly(Collection<RuleDefinitionParameter> first,
                                           Collection<RuleDefinitionParameter> second) {
        return matchesAtLeast(first, second) && matchesAtMost(first, second);
    }

    /**
     * Does {@code first} collection of {@link RuleDefinitionParameter} match loosely {@code second} collection of
     * {@link RuleDefinitionParameter} ?<br>
     * {@code first} matches loosely {@code second} if {@code first} or {@code second} is null or empty or if there is
     * at least one parameter of {@code first} collection that matches one parameter of {@code second} collection<br>
     * This method corresponds to the {@link DefinitionMatcherStrategy#LOOSELY} strategy
     *
     * @param first  the collection of {@link RuleDefinitionParameter} to match against {@code seoond}
     * @param second the collection of {@link RuleDefinitionParameter} to match against {@code first}
     * @return true if {@code first} matches {@code second}, false otherwise
     * @see RuleDefinitionParameter#matches(RuleDefinitionParameter)
     */
    private static boolean matchesLoosely(Collection<RuleDefinitionParameter> first,
                                          Collection<RuleDefinitionParameter> second) {
        return first == null || first.isEmpty() || second == null || second.isEmpty() || first.stream()
                .anyMatch(f -> second.stream().anyMatch(s -> f.matches(s)));
    }

    /**
     * Enum representing the defintion matcher strategies
     *
     * @author michotte
     */
    public enum DefinitionMatcherStrategy {
        STRICTLY, LOOSELY, AT_MOST, AT_MOST_ONE_BY_LEVEL, AT_LEAST, AT_LEAST_ONE_BY_LEVEL;
    }

}
