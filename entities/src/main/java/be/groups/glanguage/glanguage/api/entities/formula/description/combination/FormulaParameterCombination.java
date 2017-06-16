package be.groups.glanguage.glanguage.api.entities.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.combination
        .FormulaParameterCombinationInnerErrorFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;

/**
 * This class represent a combination of parameters
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_PARAM_COMB")
public class FormulaParameterCombination {

    /*
     * Fields
     */
    private Integer id;
    private String name;
    private String description;
    private SortedSet<FormulaParameterCombinationItem> parameters;

    /*
     * Constructors
     */
    public FormulaParameterCombination() {
        super();
    }

    /*
     * Getters
     */
    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "DESCRIPTION", nullable = false)
    public String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "combination", fetch = FetchType.EAGER)
    @OrderBy("sequence_number")
    public SortedSet<FormulaParameterCombinationItem> getParameters() {
        return parameters;
    }

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParameters(SortedSet<FormulaParameterCombinationItem> parameters) {
        this.parameters = parameters;
    }

    /*
     * Methods
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

    public void validateParameters(AbstractFormula formula,
                                    FormulaUsage usage,
                                    List<AbstractFormula> parameters,
                                    Evaluator evaluator) throws GLanguageException {
        List<FormulaParameterCombinationItem> combinationParameters = new ArrayList<>(getParameters());
        ListIterator<FormulaParameterCombinationItem> itCombinationParameters = combinationParameters.listIterator();
        FormulaParameterCombinationItem combinationParameter = null;
        ListIterator<AbstractFormula> itParameters = parameters.listIterator();
        while (itCombinationParameters.hasNext() && itParameters.hasNext()) {
            combinationParameter = itCombinationParameters.next();
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
                    int minimumNumberOfParameterAfterThis = 0;
                    if (itCombinationParameters.hasNext()) {
                        List<FormulaParameterCombinationItem> subList = combinationParameters.subList(
                                combinationParameters.indexOf(combinationParameter) + 1,
                                combinationParameters.size());
                        minimumNumberOfParameterAfterThis = Math.toIntExact(subList.stream()
                                                                                    .filter(p -> !p.getOptional())
                                                                                    .count());
                    }
                    while (itParameters.hasNext() && parameters.indexOf(parameter) < parameters
                            .size() - minimumNumberOfParameterAfterThis) {
                        parameter = itParameters.next();
                        if (!combinationParameter.isValid((parameter), evaluator) || parameters
                                .indexOf(parameter) == parameters.size() - minimumNumberOfParameterAfterThis) {
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
            itCombinationParameters.next().validate(formula, usage, null, evaluator);
        } else if (itParameters.hasNext()) {
            int numberOfUnreachableParameters = parameters.size() - parameters.indexOf(itParameters.next()) + 1;
            throw new GLanguageException(FormulaParameterCombinationInnerErrorFactory.getUnreachableParameters(formula,
                                                                                                               numberOfUnreachableParameters,
                                                                                                               evaluator));
        }
    }

    @JsonIgnore
    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return isValidParameterNumber(parameters) && (parameters == null || parameters.size() == 0 || isValidParameters(
                parameters,
                evaluator));
    }

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

    @JsonIgnore
    @Transient
    public boolean isValidParameters(List<AbstractFormula> parameters, Evaluator evaluator) {
        List<FormulaParameterCombinationItem> combinationParameters = new ArrayList<>(getParameters());
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
                    while (itParameters.hasNext() && parameters.indexOf(parameter) < parameters
                            .size() - minimumNumberOfParameterAfterThis) {
                        parameter = itParameters.next();
                        if (!combinationParameter.isValid((parameter), evaluator) || parameters
                                .indexOf(parameter) == parameters.size() - minimumNumberOfParameterAfterThis) {
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

    @JsonIgnore
    @Transient
    public Integer getParametersMinimumNumber() {
        return Math.toIntExact(getParameters().stream().filter(p -> !p.getOptional()).count());
    }

    @JsonIgnore
    @Transient
    public Integer getParametersMaximumNumber() {
        if (getParameters().stream().anyMatch(FormulaParameterCombinationItem::getRepeatable)) {
            return Integer.MAX_VALUE;
        } else {
            return getParameters().size();
        }
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FormulaParameterCombination{" + "name='" + name + '\'' + ", " +
                                                     "description='" + description + '\'' + ", parameters=");
        for (FormulaParameterCombinationItem parameter : parameters) {
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
