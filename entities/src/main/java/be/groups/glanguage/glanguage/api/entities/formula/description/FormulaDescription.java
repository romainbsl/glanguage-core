package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerErrorFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a description of a type of formula<br>
 * It defines :
 * <ul>
 *     <li>a priority (in evaluation process)</li>
 *     <li>a textual representation</li>
 *     <li>a position (in textual representation)</li>
 *     <li>a set of usages</li>
 * </ul>
 * Each formula type as a defined set of usages, each mapping a {@link FormulaReturnType return type} to a
 * {@link be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombination}
 * which defines the order and the types of the parameters and whether they are, optional or not and repeatable or not
 *
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_DESC")
public class FormulaDescription {

    /*
     * Fields
     */
    /**
     * Technical unique ID
     */
    private Integer id;

    /**
     * Formula type for which this is the description
     */
    private FormulaType type;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private MultilingualString description;

    /**
     * Priority
     */
    private FormulaPriority priority;

    /**
     * Textual representation
     */
    private String text;

    /**
     * Position in textual representation
     */
    private FormulaPosition position;

    /**
     * Set of {@link FormulaUsage}
     */
    private Set<FormulaUsage> usages;


    /*
     * Constructors
     */
    public FormulaDescription() {
        super();
    }

    /*
     * Getters
     */

    /**
     * Get the technical id
     *
     * @return the id
     */
    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    /**
     * Get the formula type for which this is the description
     *
     * @return the formula type for which this is the description
     */
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @Convert(converter = FormulaTypeConverter.class)
    public FormulaType getType() {
        return type;
    }

    /**
     * Get the name
     *
     * @return the name
     */
    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Get the description
     *
     * @return the description
     */
    @ManyToOne
    @JoinColumn(name = "DESC_ID", referencedColumnName = "ID")
    public MultilingualString getDescription() {
        return description;
    }

    /**
     * Get the description text in {@link Language language}
     *
     * @param language the language of the description text to be returned
     * @return the description text in {@link Language language} if it exists, an empty {@link String} otherwise
     */
    @Transient
    public String getDescription(Language language) {
        return description.asText(language);
    }

    /**
     * Get the priority<br>
     * The priority defines the order of evaluation of a sequence of formula
     *
     * @return the priority
     */
    @Column(name = "PRIORITY_ID")
    @Convert(converter = FormulaPriorityConverter.class)
    public FormulaPriority getPriority() {
        return priority;
    }

    /**
     * Get the textual representation
     *
     * @return the textual representation
     */
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    /**
     * Get the position in textual representation
     *
     * @return the position in textual representation
     */
    @Column(name = "POSITION")
    @Convert(converter = FormulaPositionConverter.class)
    public FormulaPosition getPosition() {
        return position;
    }

    @OneToMany(mappedBy = "formulaDescription", fetch = FetchType.EAGER)
    public Set<FormulaUsage> getUsages() {
        return usages;
    }

    /*
     * Setters
     */

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param type the type to set
     */
    public void setType(FormulaType type) {
        this.type = type;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(MultilingualString description) {
        this.description = description;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(FormulaPriority priority) {
        this.priority = priority;
    }

    /**
     * @param text the textual representation to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @param position the position in textual representation to set
     */
    public void setPosition(FormulaPosition position) {
        this.position = position;
    }

    /**
     * @param usages the set of {@link FormulaUsage} to set
     */
    public void setUsages(Set<FormulaUsage> usages) {
        this.usages = usages;
    }

    /*
     * Methods
     */

    /**
     * Validate a {@link AbstractFormula formula} for a list of {@link AbstractFormula parameters} with an evaluator
     * (can be null)
     * Validation process :
     * <ol>
     *     <li>Check if it is valid by delegating to {@link FormulaDescription#isValid(List, Evaluator)}</li>
     *     <li>If valid, do nothing</li>
     *     <li>Else if not valid, get the best usage for the list of {@link AbstractFormula parameters}</li>
     *     <li>If it is null, throw a {@link GLanguageException}</li>
     *     <li>Else if it is not null, delegate to {@link FormulaUsage#validate(AbstractFormula, List, Evaluator)} to
     *          get the exception explaining why it is not valid</li>
     *     <li>If a {@link GLanguageException} is thrown, set an outer error and throw it</li>
     * </ol>
     *
     * @param formula the {@link AbstractFormula formula} to be validated
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @throws GLanguageException if an error occurs during the validation process or if the list of
     * {@link AbstractFormula parameters} are not valid
     */
    public void validate(AbstractFormula formula,
                         List<AbstractFormula> parameters,
                         Evaluator evaluator) throws GLanguageException {
        try {
            if (!isValid(parameters, evaluator)) {
                FormulaUsage usage = getBestMatchingUsage(parameters, evaluator);
                if (usage != null) {
                    // If a best matching usage is found, validate it in order to get more precise exceptions about
                    // the invalidity
                    usage.validate(formula, parameters, evaluator);
                } else {
                    throw new GLanguageException(FormulaDescriptionInnerErrorFactory
                                                         .getNoMatchingUsage(formula, parameters, evaluator));
                }
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(FormulaDescriptionInnerErrorFactory
                                               .getUnableToValidate(formula, parameters, evaluator));
            throw e;
        }
    }

    /**
     * Is this valid for a list of {@link AbstractFormula parameters} with an evaluator (can be null) ?<br>
     * This is valid if at least one of its {@link FormulaDescription#usages} is valid<br>
     * Usage validity is determined by delegating to {@link FormulaUsage#isValid(List, Evaluator)}
     *
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if it is valid, false otherwise
     */
    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return getUsages().stream().anyMatch(u -> u.isValid(parameters, evaluator));
    }

    /**
     * Get the return type for a list of {@link AbstractFormula parameters} with an evaluator (can be null)<br>
     * Evaluation process :
     * <ol>
     *     <li>Get the valid usage for a list of {@link AbstractFormula parameters}, if it exists</li>
     *     <li>If it is null, return {@link FormulaReturnType#UNDEFINED}</li>
     *     <li>Else if it is not null :
     *          <ol>
     *              <li>If {@link FormulaUsage#getReturnTypes()} contains only 1 item, return it</li>
     *              <li>Else, return {@link FormulaReturnType#UNDEFINED}</li>
     *          </ol>
     *     </li>
     * </ol>
     *
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the return type for a list of {@link AbstractFormula parameters} or
     * {@link FormulaReturnType#UNDEFINED} if it cannot be determined
     */
    @Transient
    public FormulaReturnType getReturnType(List<AbstractFormula> parameters, Evaluator evaluator) {
        FormulaUsage usage = getValidUsage(parameters, evaluator);
        if (usage != null) {
            if (usage.getTypes().size() == 1) {
                return new ArrayList<>(usage.getReturnTypes()).get(0);
            } else {
                return FormulaReturnType.UNDEFINED;
            }
        } else {
            return FormulaReturnType.UNDEFINED;
        }
    }

    /**
     * Get the valid usage for a list of {@link AbstractFormula parameters} with an evaluator (can be null)<br>
     * The valid usage for a list of {@link AbstractFormula parameters} is the one that corresponds the best to the
     * list of {@link AbstractFormula parameters}
     * Evaluation process :
     * <ol>
     *     <li>If there is no usage, return null</li>
     *     <li>If there is only 1 usage and this is valid, return it</li>
     *     <li>Else, if there is more than 1 usages, filter to have only the valid ones :
     *          <ol>
     *              <li>If there is no usage, return null</li>
     *              <li>If there is only 1 usage, return it</li>
     *              <li>Else, if there is more than 1 usages, filter to have only the ones that have minimal
     *                  parameter number :
     *                  <ol>
     *                      <li>If there is no usage, return null</li>
     *                      <li>If there is only 1 usage, return it</li>
     *                      <li>Else, if there is more than 1 usages, return the one that have the most restrictive
     *                          parameters types</li>
     *                  </ol>
     *              </li>
     *          </ol>
     *     </li>
     * </ol>
     *
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the valid usage for a list of {@link AbstractFormula parameters} if it exists, null otherwise
     */
    @Transient
    public FormulaUsage getValidUsage(List<AbstractFormula> parameters, Evaluator evaluator) {
        // Only 1 usage -> if valid, return it
        if (getUsages().size() == 1) {
            FormulaUsage usage = getUsages().iterator().next();
            if (usage.isValid(parameters, evaluator)) {
                return usage;
            }
        } else if (getUsages().size() > 1) {
            // More than 1 usages
            // Select the valid usages
            List<FormulaUsage> validUsages = getUsages().stream().filter(u -> u.isValid(parameters, evaluator)).collect(
                    Collectors.toList());
            if (validUsages.size() == 1) {
                // Only 1 valid usage -> return it
                return validUsages.get(0);
            } else if (validUsages.size() > 1) {
                // More than 1 valid usages
                // Select the valid usages with minimal parameter number
                validUsages = getUsagesWithMinimalParameterNumber(validUsages);
                if (validUsages.size() == 1) {
                    // Only 1 valid usage -> return it
                    return validUsages.get(0);
                } else if (validUsages.size() > 1) {
                    // More than one smallest maximum parameter number usages
                    // Select the one with the most restrictive parameters types
                    Integer minimumParametersReturnTypesNumber = Integer.MAX_VALUE;
                    FormulaUsage usage = null;
                    for (FormulaUsage currentUsage : validUsages) {
                        Integer currentUsageParametersReturnTypesNumber = currentUsage.getParameterCombination()
                                .getParameters().stream().map(p -> p.getTypes().size()).reduce((i1, i2) -> i1 + i2)
                                .orElse(0);
                        if (currentUsageParametersReturnTypesNumber < minimumParametersReturnTypesNumber) {
                            minimumParametersReturnTypesNumber = currentUsageParametersReturnTypesNumber;
                            usage = currentUsage;
                        }
                    }
                    return usage;
                }
            }
        }
        return null;
    }

    /**
     * Get the best matching usage for a list of {@link AbstractFormula parameters} with an evaluator (can be null)<br>
     * The valid usage for a list of {@link AbstractFormula parameters} is the one that corresponds the best to the
     * list of {@link AbstractFormula parameters}
     * Evaluation process :
     * <ol>
     *     <li>If there is no usage, return null</li>
     *     <li>If there is only 1 usage, return it</li>
     *     <li>Else, if there is more than 1 usages, filter to have only the ones that have a matching number of
     *         parameters :
     *          <ol>
     *              <li>If there is no usage, return null</li>
     *              <li>If there is only 1 usage, return it</li>
     *              <li>Else, if there is more than 1 usages, filter to have only the ones that have minimal
     *                  parameter number :
     *                  <ol>
     *                      <li>If there is no usage, return null</li>
     *                      <li>If there is only 1 usage, return it</li>
     *                      <li>Else, if there is more than 1 usages, filter to have only the ones for which minimum
     *                          parameter number is equal to the number of {@code parameters} :
     *                          <ol>
     *                              <li>If there is no usage, return null</li>
     *                              <li>If there is only 1 usage, return it</li>
     *                              <li>Else, if there is more than 1 usages, filter to have only the ones for which
     *                                  maximum parameter number is equal to the number of {@code parameters} :
     *                                  <ol>
     *                                      <li>If there is no usage, return null</li>
     *                                      <li>If there is only 1 usage, return it</li>
     *                                      <li>Else, if there is more than 1 usages, return the one for which the
     *                                          types of parameters best match the types of {@code parameters}
     *                                      </li>
     *                                  </ol>
     *                              </li>
     *                          </ol>
     *                      </li>
     *                  </ol>
     *              </li>
     *          </ol>
     *     </li>
     * </ol>
     *
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the best matching usage for a list of {@link AbstractFormula parameters} if it exists, null otherwise
     */
    @Transient
    public FormulaUsage getBestMatchingUsage(List<AbstractFormula> parameters, Evaluator evaluator) {
        // Only 1 usage -> return it
        if (getUsages().size() == 1) {
            return getUsages().iterator().next();
        } else if (getUsages().size() > 1) {
            // Select the usages with matching number of parameters
            List<FormulaUsage> matchingUsages = getUsages().stream().filter(u -> u.getParameterCombination()
                    .getParametersMinimumNumber() <= parameters.size() && u.getParameterCombination()
                    .getParametersMaximumNumber() >= parameters.size()).collect(Collectors.toList());
            if (matchingUsages.size() == 1) {
                // Only 1 matching usage -> return it
                return matchingUsages.get(0);
            } else if (matchingUsages.size() > 1) {
                // More than 1 matching usages
                // Select the matching usages with minimal parameter number
                matchingUsages = getUsagesWithMinimalParameterNumber(matchingUsages);
                if (matchingUsages.size() == 1) {
                    // Only 1 matching usage -> return it
                    return matchingUsages.get(0);
                } else if (matchingUsages.size() > 1) {
                    // More than 1 matching usages
                    // Select the matching usages with minimum number of parameter equal to parameters list size
                    ArrayList<FormulaUsage> matchingMinimumParameterNumberUsages = new ArrayList<>();
                    for (FormulaUsage usage : matchingUsages) {
                        if (usage.getParameterCombination().getParametersMinimumNumber() == parameters.size()) {
                            matchingMinimumParameterNumberUsages.add(usage);
                        }
                    }
                    if (matchingMinimumParameterNumberUsages.size() == 1) {
                        // Only 1 matching usage -> return it
                        return matchingMinimumParameterNumberUsages.get(0);
                    } else if (matchingMinimumParameterNumberUsages.size() > 1) {
                        // More than 1 matching usages
                        // Select the matching usages with maximum number of parameter equal to parameters list size
                        ArrayList<FormulaUsage> matchingMaximumParameterNumberUsages = new ArrayList<>();
                        for (FormulaUsage matchingUsage : matchingMinimumParameterNumberUsages) {
                            if (matchingUsage.getParameterCombination().getParametersMaximumNumber() == parameters
                                    .size()) {
                                matchingMaximumParameterNumberUsages.add(matchingUsage);
                            }
                        }
                        if (matchingMaximumParameterNumberUsages.size() == 1) {
                            // Only 1 matching usage -> return it
                            return matchingMaximumParameterNumberUsages.get(0);
                        } else if (matchingMaximumParameterNumberUsages.size() > 1) {
                            // More than 1 matching usages
                            // Select the matching usage with best matching parameters types
                            int maximumNumberOfMatchinParameterTypes = 0;
                            FormulaUsage matchingUsage = null;
                            for (FormulaUsage currentMatchingUsage : matchingMaximumParameterNumberUsages) {
                                List<FormulaParameterCombinationItem> usageParameters = currentMatchingUsage
                                        .getParameterCombination().getParameters().stream().collect(Collectors
                                                                                                            .toList());
                                int numberOfMatchingParameterTypes = 0;
                                for (int i = 0; i < parameters.size(); i++) {
                                    if (usageParameters.get(i).isValidType(parameters.get(i), evaluator)) {
                                        numberOfMatchingParameterTypes++;
                                    }
                                }
                                if (numberOfMatchingParameterTypes > maximumNumberOfMatchinParameterTypes) {
                                    maximumNumberOfMatchinParameterTypes = numberOfMatchingParameterTypes;
                                    matchingUsage = currentMatchingUsage;
                                }
                            }
                            return matchingUsage;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get the list of {@link FormulaUsage} in {@code usages} that have the minimal parameter number<br>
     * Evaluation process :
     * <ol>
     *     <li>If there is no usage, return null</li>
     *     <li>If there is only 1 usage, return a list with it</li>
     *     <li>Else, if there is more than 1 usages, filter to have only the ones that have minimal minimum number of
     *         parameters :
     *          <ol>
     *              <li>If there is only 1 usage, return a list with it</li>
     *              <li>Else, if there is more than 1 usages, return a list with the ones that have minimal
     *                  maximum number of parameters</li>
     *         </ol>
     *     </li>
     * </ol>
     *
     * @param usages the list of {@link FormulaUsage} from which to extract the ones that have minimal parameter number
     * @return the list of {@link FormulaUsage} in {@code usages} that have the minimal parameter number
     */
    public List<FormulaUsage> getUsagesWithMinimalParameterNumber(List<FormulaUsage> usages) {
        if (usages != null) {
            if (usages.size() == 1) {
                return usages;
            } else if (usages.size() > 1) {
                // More than 1 valid usages
                // Select the ones with smallest minimum parameter number
                List<FormulaUsage> validMinimalUsages = null;
                int minimumParameterNumber = Integer.MAX_VALUE;
                for (FormulaUsage currentValidUsage : usages) {
                    if (currentValidUsage.getParameterCombination().getParametersMinimumNumber() < minimumParameterNumber) {
                        validMinimalUsages = new ArrayList<>();
                        minimumParameterNumber = currentValidUsage.getParameterCombination().getParametersMinimumNumber();

                    }
                    if (currentValidUsage.getParameterCombination().getParametersMinimumNumber() == minimumParameterNumber) {
                        validMinimalUsages.add(currentValidUsage);
                    }
                }
                if (validMinimalUsages.size() == 1) {
                    return validMinimalUsages;
                } else if (validMinimalUsages.size() > 1) {
                    // More than one smallest minimum parameter number usages
                    // Select the ones with smallest maximum parameter number
                    List<FormulaUsage> validMaximalUsages = new ArrayList<>();
                    int maximumParameterNumber = Integer.MAX_VALUE;
                    for (FormulaUsage currentValidUsage : usages) {
                        if (currentValidUsage.getParameterCombination().getParametersMaximumNumber() < maximumParameterNumber) {
                            validMaximalUsages = new ArrayList<>();
                            maximumParameterNumber = currentValidUsage.getParameterCombination().getParametersMaximumNumber();
                        }
                        if (currentValidUsage.getParameterCombination()
                                .getParametersMaximumNumber() == maximumParameterNumber) {
                            validMaximalUsages.add(currentValidUsage);
                        }
                    }
                    return validMaximalUsages;
                }
            }
        }
        return null;
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaDescription{" + "type=" + type + ", name='" + name + '\'' + ", description=" + description +
                "," + " priority=" + priority + ", position=" + position + ", usages=" + usages + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaDescription)) return false;

        FormulaDescription that = (FormulaDescription) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
