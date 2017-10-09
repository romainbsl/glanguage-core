package be.groups.glanguage.core.entities.formula.description.combination;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.description.combination.FormulaParameterCombinationInnerErrorFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;

/**
 * This class represent a combination of parameters<br>
 * A combination of combinationParameters has a set of {@link FormulaParameterCombinationItem} ordered by
 * {@link FormulaParameterCombinationItem#sequenceNumber}<br>
 * A combination of combinationParameters could be shared between several {@link FormulaUsage}
 *
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_PARAM_COMB")
public class FormulaParameterCombination {

    /*
     * Fields
     */

    /**
     * Technical unique ID
     */
    private Long id;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    /**
     * Set of {@link FormulaParameterCombinationItem} combinationParameters
     */
    private SortedSet<FormulaParameterCombinationItem> combinationParameters;

    /*
     * Constructors
     */
    public FormulaParameterCombination() {
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
    public Long getId() {
        return id;
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
    @Column(name = "DESCRIPTION", nullable = false)
    public String getDescription() {
        return description;
    }

    /**
     * Get the set of {@link FormulaParameterCombinationItem} combinationParameters
     *
     * @return the set of {@link FormulaParameterCombinationItem} combinationParameters
     */
    @OneToMany(mappedBy = "combination", fetch = FetchType.EAGER)
    @OrderBy("sequence_number")
    public SortedSet<FormulaParameterCombinationItem> getCombinationParameters() {
        return combinationParameters;
    }

    /*
     * Setters
     */

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param combinationParameters the set of {@link FormulaParameterCombinationItem} combinationParameters to set
     */
    public void setCombinationParameters(SortedSet<FormulaParameterCombinationItem> combinationParameters) {
        this.combinationParameters = combinationParameters;
    }

    /*
     * Methods
     */

    /**
     * Validate a list of {@link AbstractFormula parameters} of a {@link AbstractFormula formula} for a
     * {@link FormulaUsage usage} with an {@link Evaluator evaluator} (can be null)
     * Validation process :
     * <ol>
     * <li>Check if it is valid by delegating to {@link FormulaParameterCombination#isValid(List, Evaluator)}</li>
     * <li>If valid, do nothing</li>
     * <li>Else if not valid, check if the number of items in the list of
     * {@link AbstractFormula parameters} is valid by delegating to
     * {@link FormulaParameterCombination#isValidParameterNumber(List)}</li>
     * <li>If the number of items in the list of {@link AbstractFormula parameters} is not valid, throw a
     * {@link GLanguageException}</li>
     * <li>Else if the number of items in the list of {@link AbstractFormula parameters} is valid, check
     * if the items in the list of {@link AbstractFormula parameters} are valid by delegating to {@link
     * FormulaParameterCombination#isValidParameters(List, Evaluator)}</li>
     * <li>If not valid, delegate to
     * {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}</li>
     * <li>If a {@link GLanguageException} is thrown, set an outer error and throw it</li>
     * </ol>
     *
     * @param formula    the {@link AbstractFormula formula} to be validated
     * @param usage      the {@link FormulaUsage} from which {@code this} is the parameter combination
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     * @throws GLanguageException if an error occurs during the validation process or if one item of the list of
     *                            {@link AbstractFormula parameters} is not valid
     */
    public void validate(AbstractFormula formula,
                         FormulaUsage usage,
                         List<AbstractFormula> parameters,
                         Evaluator evaluator) throws GLanguageException {
        try {
            if (!isValid(parameters, evaluator)) {
                if (!isValidParameterNumber(parameters)) {
                    throw new GLanguageException(FormulaParameterCombinationInnerErrorFactory.getWrongParameterNumber(
                            formula,
                            parameters.size(),
                            getParametersMinimumNumber(),
                            getParametersMaximumNumber(),
                            evaluator));
                } else if (!isValidParameters(parameters, evaluator)) {
                    // validate each parameter in order to get more precise exceptions about the invalidity
                    validateParameters(formula, usage, parameters, evaluator);
                }
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(FormulaParameterCombinationInnerErrorFactory
                                               .getUnableToValidate(formula, this, evaluator));
            throw e;
        }
    }

    /**
     * Validate a list of {@link AbstractFormula parameters} of a {@link AbstractFormula formula} for a
     * {@link FormulaUsage usage} with an {@link Evaluator evaluator} (can be null) against the set of
     * {@link FormulaParameterCombination#combinationParameters}<br>
     * Validation process :<br>
     * Each item of the list of {@link AbstractFormula parameters} is validated against the corresponding item of
     * {@link FormulaParameterCombination#combinationParameters} considering their
     * {@link FormulaParameterCombinationItem#optional} and {@link FormulaParameterCombinationItem#repeatable} status
     *
     * @param formula    the {@link AbstractFormula formula} to be validated
     * @param usage      the {@link FormulaUsage} from which {@code this} is the parameter combination
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     * @throws GLanguageException if an error occurs during the validation process or if one item of the list of
     *                            {@link AbstractFormula parameters} is not valid
     */
    public void validateParameters(AbstractFormula formula,
                                   FormulaUsage usage,
                                   List<AbstractFormula> parameters,
                                   Evaluator evaluator) throws GLanguageException {
        // Create a list from the order set of combination parameters and initialize an iterator for this list
        List<FormulaParameterCombinationItem> combinationParameters = new ArrayList<>(getCombinationParameters());
        ListIterator<FormulaParameterCombinationItem> itCombinationParameters = combinationParameters.listIterator();
        // Initialize an iterator for the list of parameters
        ListIterator<AbstractFormula> itParameters = parameters.listIterator();
        FormulaParameterCombinationItem combinationParameter = null;
        while (itCombinationParameters.hasNext() && itParameters.hasNext()) {
            // Iterate on the list of combination parameters and on the list of parameters until there is no more
            // item in one or both of them
            // Get the next item of the list of combination parameters
            combinationParameter = itCombinationParameters.next();
            // Get the next item of the list of parameters
            AbstractFormula parameter = itParameters.next();
            if (!combinationParameter.isValid(parameter, evaluator)) {
                if (!combinationParameter.getOptional()) {
                    combinationParameter.validate(formula, usage, parameter, evaluator);
                } else {
                    // Go back to the invalid parameter to get it validated with the next combination parameter at
                    // next loop iteration
                    itParameters.previous();
                }
            } else {
                if (itParameters.hasNext() && combinationParameter.getRepeatable()) {
                    // If there are more items in the list of parameters and the current item of the list of combination
                    // parameters is repeatable, iterate on the list of parameters until the item is not valid against the
                    // combination parameter or until the number of remaining items is exactly the number of non-optional
                    // combination parameters after the current one or until there is no more item
                    int minimumNumberOfParameterAfterThis = 0;
                    if (itCombinationParameters.hasNext()) {
                        List<FormulaParameterCombinationItem> subList = combinationParameters.subList(
                                combinationParameters.indexOf(combinationParameter) + 1,
                                combinationParameters.size());
                        minimumNumberOfParameterAfterThis = Math.toIntExact(subList.stream()
                                                                                    .filter(p -> !p.getOptional())
                                                                                    .count());
                    }
                    while (itParameters.hasNext() && itParameters.previousIndex() < parameters
                            .size() - minimumNumberOfParameterAfterThis) {
                        parameter = itParameters.next();
                        if (!combinationParameter.isValid((parameter), evaluator) ||
                            itParameters.previousIndex() == parameters.size() - minimumNumberOfParameterAfterThis) {
                            // Go back to the invalid parameter to get it validated with the next combination
                            // parameter at next loop iteration
                            itParameters.previous();
                            break;
                        }
                    }
                }
            }
        }
        if (itCombinationParameters.hasNext() && !combinationParameters.subList(combinationParameters
                                                                                        .indexOf(combinationParameter) + 1,
                                                                                combinationParameters.size()).stream()
                .allMatch(FormulaParameterCombinationItem::getOptional)) {
            // If there are still items in the list of combination parameters (that means that the end of the list of
            // parameters has been reached) and there is at least one item that is not optional, validate the first not
            // optional item with a null parameter, which is going to throw a GLanguageException
            combinationParameters.subList(combinationParameters.indexOf(combinationParameter) + 1,
                                          combinationParameters.size()).stream().filter(p -> !p.getOptional())
                    .findFirst().orElse(null).validate(formula, usage, null, evaluator);
        } else if (itParameters.hasNext()) {
            // If there are still items in the list of parameters (that means that the end of the list of combination
            // parameters has been reached), throw a GLanguageException
            int numberOfUnreachableParameters = parameters.size() - parameters.indexOf(itParameters.next()) + 1;
            throw new GLanguageException(FormulaParameterCombinationInnerErrorFactory.getUnreachableParameters(formula,
                                                                                                               numberOfUnreachableParameters,
                                                                                                               evaluator));
        }
    }

    /**
     * Is this valid for a list of {@link AbstractFormula parameters} with an {@link Evaluator evaluator} (can be
     * null) ?<br>
     * Validation process :<br>
     * Check if :
     * <ol>
     *     <li>the number of items in the list of {@link AbstractFormula parameters} is valid by delegating to
     * {@link FormulaParameterCombination#isValidParameterNumber(List)}</li>
     *     <li>the list of {@link AbstractFormula parameters} is null or empty or if it all items in the list are valid
     *     by delegating to {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}</li>
     * </ol>
     * If all checks are true, return true, false otherwise
     *
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     * @return true if this is valid for the list of {@link AbstractFormula parameters}, false otherwise
     */
    @JsonIgnore
    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return isValidParameterNumber(parameters) && (parameters == null || parameters.size() == 0 || isValidParameters(
                parameters,
                evaluator));
    }

    /**
     * Is this valid for a list of {@link AbstractFormula parameters} only according to the number of parameters ?<br>
     * Validation process:
     * <ol>
     *     <li>If the list of {@link AbstractFormula parameters} is null or empty, return true if
     *     {@link FormulaParameterCombination#getParametersMinimumNumber()} is equal to 0, false otherwise</li>
     *     <li>Else, if the list of {@link AbstractFormula parameters} is not null and not empty, return true if the
     *     number of items in the list is between {@link FormulaParameterCombination#getParametersMinimumNumber()} and
     *     {@link FormulaParameterCombination#getParametersMaximumNumber()} inclusively</li>
     * </ol>
     *
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @return true if this is valid for the list of {@link AbstractFormula parameters} only according to the number of
     * parameters, false otherwise
     */
    @JsonIgnore
    @Transient
    public boolean isValidParameterNumber(List<AbstractFormula> parameters) {
        if (parameters == null || parameters.size() == 0) {
            return getParametersMinimumNumber() == 0;
        } else {
            return parameters.size() >= getParametersMinimumNumber() && parameters
                    .size() <= getParametersMaximumNumber();
        }
    }

    /**
     * Is this valid for a list of {@link AbstractFormula parameters} with an {@link Evaluator evaluator} (can be
     * null) against the set of {@link FormulaParameterCombination#combinationParameters}<br>
     * Validation process :<br>
     * Each item of the list of {@link AbstractFormula parameters} is validated against the corresponding item of
     * {@link FormulaParameterCombination#combinationParameters} considering their
     * {@link FormulaParameterCombinationItem#optional} and {@link FormulaParameterCombinationItem#repeatable} status
     *
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     */
    @JsonIgnore
    @Transient
    public boolean isValidParameters(List<AbstractFormula> parameters, Evaluator evaluator) {
        List<FormulaParameterCombinationItem> combinationParameters = new ArrayList<>(getCombinationParameters());
        ListIterator<FormulaParameterCombinationItem> itCombinationParameters = combinationParameters.listIterator();
        FormulaParameterCombinationItem combinationParameter = null;
        ListIterator<AbstractFormula> itParameters = parameters.listIterator();
        while (itCombinationParameters.hasNext() && itParameters.hasNext()) {
            combinationParameter = itCombinationParameters.next();
            AbstractFormula parameter = itParameters.next();
            if (!combinationParameter.isValid(parameter, evaluator)) {
                if (!combinationParameter.getOptional()) {
                    return false;
                } else {
                    // Go back to the invalid parameter to get it validated with the next combination parameter at
                    // next loop iteration
                    itParameters.previous();
                }
            } else {
                if (itParameters.hasNext() && combinationParameter.getRepeatable()) {
                    int minimumNumberOfParameterAfterThis = 0;
                    if (itCombinationParameters.hasNext()) {
                        List<FormulaParameterCombinationItem> subList = combinationParameters.subList(
                                combinationParameters.indexOf(combinationParameter) + 1,
                                combinationParameters.size());
                        minimumNumberOfParameterAfterThis = Math.toIntExact(subList.stream()
                                                                                    .filter(p -> !p.getOptional())
                                                                                    .count());
                    }
                    while (itParameters.hasNext() && itParameters.previousIndex() < parameters
                            .size() - minimumNumberOfParameterAfterThis) {
                        parameter = itParameters.next();
                        if (!combinationParameter.isValid((parameter), evaluator) ||
                            itParameters.previousIndex() == parameters.size() - minimumNumberOfParameterAfterThis) {
                            // Go back to the invalid parameter to get it
                            // validated with the next combination parameter at next loop iteration
                            itParameters.previous();
                            break;
                        }
                    }
                }
            }
        }
        return !(itParameters.hasNext() || (itCombinationParameters.hasNext() && !combinationParameters.subList(
                combinationParameters.indexOf(combinationParameter) + 1,
                combinationParameters.size()).stream().allMatch(FormulaParameterCombinationItem::getOptional)));
    }

    /**
     * Get the minimum number of parameters for which {@code this} could be valid<br>
     * The minimum number of parameters for which {@code this} could be valid is the number of
     * {@link FormulaParameterCombination#combinationParameters} that are not
     * {@link FormulaParameterCombinationItem#optional}
     *
     * @return the minimum number of parameters for which {@code this} could be valid
     */
    @JsonIgnore
    @Transient
    public Integer getParametersMinimumNumber() {
        return Math.toIntExact(getCombinationParameters().stream().filter(p -> !p.getOptional()).count());
    }

    /**
     * Get the maximum number of parameters for which {@code this} could be valid<br>
     * The maximum number of parameters for which {@code this} could be valid is the number of
     * {@link FormulaParameterCombination#combinationParameters} or {@link Integer#MAX_VALUE} if there is at least
     * one combination parameter that is {@link FormulaParameterCombinationItem#repeatable}
     *
     * @return the maximum number of parameters for which {@code this} could be valid
     */
    @JsonIgnore
    @Transient
    public Integer getParametersMaximumNumber() {
        if (getCombinationParameters().stream().anyMatch(FormulaParameterCombinationItem::getRepeatable)) {
            return Integer.MAX_VALUE;
        } else {
            return getCombinationParameters().size();
        }
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FormulaParameterCombination{" + "name='" + name + '\'' + ", " +
                                                     "description='" + description + '\'' + ", combinationParameters=");
        for (FormulaParameterCombinationItem parameter : combinationParameters) {
            sb.append("\n\t").append(parameter.toString());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaParameterCombination)) return false;

        FormulaParameterCombination that = (FormulaParameterCombination) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
