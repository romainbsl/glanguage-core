package be.groups.glanguage.glanguage.api.entities.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationInnerErrorFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;

/**
 * This class represent a conbination of parameters
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_PARAM_CONB")
public class FormulaParameterConbination {

    /*
     * Fields
     */
    private Integer id;
    private String name;
    private String description;
    private SortedSet<FormulaParameterConbinationItem> parameters;

    /*
     * Constructors
     */
    public FormulaParameterConbination() {
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

    @OneToMany(mappedBy = "conbination")
    @OrderBy("sequence_number")
    public SortedSet<FormulaParameterConbinationItem> getParameters() {
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

    public void setParameters(SortedSet<FormulaParameterConbinationItem> parameters) {
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
                    throw new GLanguageException(FormulaParameterConbinationInnerErrorFactory.getWrongParameterNumber(
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
            e.getError().setOuterError(FormulaParameterConbinationInnerErrorFactory
                                               .getUnableToValidate(formula, this, evaluator));
            throw e;
        }
    }

    public void validateParameters(AbstractFormula formula,
                                    FormulaUsage usage,
                                    List<AbstractFormula> parameters,
                                    Evaluator evaluator) throws GLanguageException {
        List<FormulaParameterConbinationItem> conbinationParameters = new ArrayList<>(getParameters());
        ListIterator<FormulaParameterConbinationItem> itConbinationParameters = conbinationParameters.listIterator();
        FormulaParameterConbinationItem conbinationParameter = null;
        ListIterator<AbstractFormula> itParameters = parameters.listIterator();
        while (itConbinationParameters.hasNext() && itParameters.hasNext()) {
            conbinationParameter = itConbinationParameters.next();
            AbstractFormula parameter = itParameters.next();
            if (!conbinationParameter.isValid(parameter, evaluator)) {
                if (!conbinationParameter.getOptional()) {
                    conbinationParameter.validate(formula, usage, parameter, evaluator);
                } else {
                    // Go back to the invalid parameter to get it validated with the next conbination parameter at
                    // next loop iteration
                    itParameters.previous();
                }
            } else {
                if (itParameters.hasNext() && conbinationParameter.getRepeatable()) {
                    int minimumNumberOfParameterAfterThis = 0;
                    if (itConbinationParameters.hasNext()) {
                        List<FormulaParameterConbinationItem> subList = conbinationParameters.subList(
                                conbinationParameters.indexOf(conbinationParameter) + 1,
                                conbinationParameters.size());
                        minimumNumberOfParameterAfterThis = Math.toIntExact(subList.stream()
                                                                                    .filter(p -> !p.getOptional())
                                                                                    .count());
                    }
                    while (itParameters.hasNext() && parameters.indexOf(parameter) < parameters
                            .size() - minimumNumberOfParameterAfterThis) {
                        parameter = itParameters.next();
                        if (!conbinationParameter.isValid((parameter), evaluator) || parameters
                                .indexOf(parameter) == parameters.size() - minimumNumberOfParameterAfterThis) {
                            // Go back to the invalid parameter to get it validated with the next conbination
                            // parameter at next loop iteration
                            itParameters.previous();
                            break;
                        }
                    }
                }
            }
        }
        if (itConbinationParameters.hasNext() && !conbinationParameters.subList(conbinationParameters
                                                                                        .indexOf(conbinationParameter) + 1,
                                                                                conbinationParameters.size()).stream()
                .allMatch(FormulaParameterConbinationItem::getOptional)) {
            itConbinationParameters.next().validate(formula, usage, null, evaluator);
        } else if (itParameters.hasNext()) {
            int numberOfUnreachableParameters = parameters.size() - parameters.indexOf(itParameters.next()) + 1;
            throw new GLanguageException(FormulaParameterConbinationInnerErrorFactory.getUnreachableParameters(formula,
                                                                                                               numberOfUnreachableParameters,
                                                                                                               evaluator));
        }
    }

    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return isValidParameterNumber(parameters) && (parameters == null || parameters.size() == 0 || isValidParameters(
                parameters,
                evaluator));
    }

    @Transient
    public boolean isValidParameterNumber(List<AbstractFormula> parameters) {
        if (parameters == null || parameters.size() == 0) {
            return getParametersMinimumNumber() == 0;
        } else {
            return parameters.size() >= getParametersMinimumNumber() && parameters
                    .size() <= getParametersMaximumNumber();
        }
    }

    @Transient
    public boolean isValidParameters(List<AbstractFormula> parameters, Evaluator evaluator) {
        List<FormulaParameterConbinationItem> conbinationParameters = new ArrayList<>(getParameters());
        ListIterator<FormulaParameterConbinationItem> itConbinationParameters = conbinationParameters.listIterator();
        FormulaParameterConbinationItem conbinationParameter = null;
        ListIterator<AbstractFormula> itParameters = parameters.listIterator();
        while (itConbinationParameters.hasNext() && itParameters.hasNext()) {
            conbinationParameter = itConbinationParameters.next();
            AbstractFormula parameter = itParameters.next();
            if (!conbinationParameter.isValid(parameter, evaluator)) {
                if (!conbinationParameter.getOptional()) {
                    return false;
                } else {
                    // Go back to the invalid parameter to get it validated with the next conbination parameter at
                    // next loop iteration
                    itParameters.previous();
                }
            } else {
                if (itParameters.hasNext() && conbinationParameter.getRepeatable()) {
                    int minimumNumberOfParameterAfterThis = 0;
                    if (itConbinationParameters.hasNext()) {
                        List<FormulaParameterConbinationItem> subList = conbinationParameters.subList(
                                conbinationParameters.indexOf(conbinationParameter) + 1,
                                conbinationParameters.size());
                        minimumNumberOfParameterAfterThis = Math.toIntExact(subList.stream()
                                                                                    .filter(p -> !p.getOptional())
                                                                                    .count());
                    }
                    while (itParameters.hasNext() && parameters.indexOf(parameter) < parameters
                            .size() - minimumNumberOfParameterAfterThis) {
                        parameter = itParameters.next();
                        if (!conbinationParameter.isValid((parameter), evaluator) || parameters
                                .indexOf(parameter) == parameters.size() - minimumNumberOfParameterAfterThis) {
                            // Go back to the invalid parameter to get it
                            // validated with the next conbination parameter at next loop iteration
                            itParameters.previous();
                            break;
                        }
                    }
                }
            }
        }
        return !(itParameters.hasNext() || (itConbinationParameters.hasNext() && !conbinationParameters.subList(
                conbinationParameters.indexOf(conbinationParameter) + 1,
                conbinationParameters.size()).stream().allMatch(FormulaParameterConbinationItem::getOptional)));
    }

    @Transient
    public Integer getParametersMinimumNumber() {
        return Math.toIntExact(getParameters().stream().filter(p -> !p.getOptional()).count());
    }

    @Transient
    public Integer getParametersMaximumNumber() {
        if (getParameters().stream().anyMatch(FormulaParameterConbinationItem::getRepeatable)) {
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
        StringBuilder sb = new StringBuilder("FormulaParameterConbination{" + "name='" + name + '\'' + ", " +
                                                     "description='" + description + '\'' + ", parameters=");
        for (FormulaParameterConbinationItem parameter : parameters) {
            sb.append("\n\t").append(parameter.toString());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaParameterConbination)) return false;

        FormulaParameterConbination that = (FormulaParameterConbination) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
