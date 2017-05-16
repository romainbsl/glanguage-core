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
                            getParametersMaximumNumber()));
                } else if (!isValidParameters(parameters, evaluator)) {
                    // validate each parameter in order to get more precise exceptions about the invalidity
                    validateParameters(formula, usage, parameters, evaluator);
                }
            }
        } catch (GLanguageException e) {
            // TODO
            throw e;
        }
    }

    private void validateParameters(AbstractFormula formula,
                                    FormulaUsage usage,
                                    List<AbstractFormula> parameters,
                                    Evaluator evaluator) throws GLanguageException {
        List<FormulaParameterConbinationItem> conbinationParameters = new ArrayList<>(getParameters());
        ListIterator<FormulaParameterConbinationItem> itConbinationParameters = conbinationParameters.listIterator();
        ListIterator<AbstractFormula> itParameters = parameters.listIterator();
        FormulaParameterConbinationItem conbinationParameter = itConbinationParameters.next();
        AbstractFormula parameter = itParameters.next();
        while (itConbinationParameters.hasNext() && itParameters.hasNext()) {
            if (!conbinationParameter.isValid(parameter, evaluator)) {
                if (!conbinationParameter.getOptional()) {
                    conbinationParameter.validate(formula, usage, parameter, evaluator);
                } else {
                    conbinationParameter = itConbinationParameters.next();
                }
            } else {
                parameter = itParameters.next();
                if (conbinationParameter.isValid(parameter, evaluator) && conbinationParameter.getRepeatable()) {
                    int minimumNumberOfNonOptionalParameterAfterThis = 0;
                    int maximumNumberOfNonOptionalParameterAfterThis = 0;
                    if (itConbinationParameters.hasNext()) {
                        List<FormulaParameterConbinationItem> subList = conbinationParameters.subList(
                                conbinationParameters.indexOf(conbinationParameter) + 1,
                                conbinationParameters.size());
                        minimumNumberOfNonOptionalParameterAfterThis = Math.toIntExact(subList.stream().filter(p -> !p
                                .getOptional()).count());
                        maximumNumberOfNonOptionalParameterAfterThis = subList.stream().anyMatch(p -> p
                                .getRepeatable()) ? Integer.MAX_VALUE : subList.size();
                    }
                    while (itParameters.hasNext() && conbinationParameter.isValid((parameter = itParameters.next()),
                                                                                  evaluator) && parameters.indexOf(
                            parameter) < parameters.size() - minimumNumberOfNonOptionalParameterAfterThis) {
                        ;
                    }
                    if (parameters.indexOf(parameter) >= parameters
                            .size() - maximumNumberOfNonOptionalParameterAfterThis) {
                        conbinationParameter = itConbinationParameters.next();
                    }
                } else {
                    conbinationParameter = itConbinationParameters.next();
                }
            }
        }
    }

    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        if (!isValidParameterNumber(parameters)) {
            return false;
        } else if (!isValidParameters(parameters, evaluator)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidParameterNumber(List<AbstractFormula> parameters) {
        if (parameters == null || parameters.size() == 0 && getParametersMinimumNumber() != 0) {
            return false;
        } else if (!(parameters.size() >= getParametersMinimumNumber() && parameters
                .size() <= getParametersMaximumNumber())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidParameters(List<AbstractFormula> parameters, Evaluator evaluator) {
        List<FormulaParameterConbinationItem> conbinationParameters = new ArrayList<>(getParameters());
        ListIterator<FormulaParameterConbinationItem> itConbinationParameters = conbinationParameters.listIterator();
        ListIterator<AbstractFormula> itParameters = parameters.listIterator();
        FormulaParameterConbinationItem conbinationParameter = itConbinationParameters.next();
        AbstractFormula parameter = itParameters.next();
        while (itConbinationParameters.hasNext() && itParameters.hasNext()) {
            if (!conbinationParameter.isValid(parameter, evaluator)) {
                if (!conbinationParameter.getOptional()) {
                    return false;
                } else {
                    conbinationParameter = itConbinationParameters.next();
                }
            } else {
                parameter = itParameters.next();
                if (conbinationParameter.isValid(parameter, evaluator) && conbinationParameter.getRepeatable()) {
                    int minimumNumberOfNonOptionalParameterAfterThis = 0;
                    int maximumNumberOfNonOptionalParameterAfterThis = 0;
                    if (itConbinationParameters.hasNext()) {
                        List<FormulaParameterConbinationItem> subList = conbinationParameters.subList(
                                conbinationParameters.indexOf(conbinationParameter) + 1,
                                conbinationParameters.size());
                        minimumNumberOfNonOptionalParameterAfterThis = Math.toIntExact(subList.stream().filter(p -> !p
                                .getOptional()).count());
                        maximumNumberOfNonOptionalParameterAfterThis = subList.stream().anyMatch(p -> p
                                .getRepeatable()) ? Integer.MAX_VALUE : subList.size();
                    }
                    while (itParameters.hasNext() && conbinationParameter.isValid((parameter = itParameters.next()),
                                                                                  evaluator) && parameters.indexOf(
                            parameter) < parameters.size() - minimumNumberOfNonOptionalParameterAfterThis) {
                        ;
                    }
                    if (parameters.indexOf(parameter) >= parameters
                            .size() - maximumNumberOfNonOptionalParameterAfterThis) {
                        conbinationParameter = itConbinationParameters.next();
                    }
                } else {
                    conbinationParameter = itConbinationParameters.next();
                }
            }
        }
        return true;
    }

    public Integer getParametersMinimumNumber() {
        return Math.toIntExact(parameters.stream().filter(p -> !p.getOptional()).count());
    }

    public Integer getParametersMaximumNumber() {
        if (parameters.stream().anyMatch(p -> p.getRepeatable())) {
            return Integer.MAX_VALUE;
        } else {
            return parameters.size();
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
            sb.append("\n\t" + parameter.toString());
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
