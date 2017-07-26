package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.Rounder;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaWrongParameterTypeInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract formula implementing rounding operations on a number
 *
 * @author michotte
 */
@Entity
public abstract class RoundingFormula extends AbstractNonTerminalFormula {

    protected RoundingFormula() {
        super();
    }

    public RoundingFormula(FormulaDescription description,
                           FormulaDescription precisionDescription,
                           List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
        super(description,
              getParametersAsList(parameters, description, precisionDescription), evaluator);
    }

    /**
     * Get the parameters as list<br>
     * Helper method for constructor to pass the parameters as a list to the super constructor of
     * {@link AbstractNonTerminalFormula}
     *
     * @param parameters the parameters to be added to the list
     * @return a list with 2 items which are
     * <ol>
     *     <li>the value to be rounded</li>
     *     <li>the precision to be used for rounding</li>
     * </ol>
     */
    @Transient
    @JsonIgnore
    private static List<AbstractFormula> getParametersAsList(List<AbstractFormula> parameters,
                                                             FormulaDescription description,
                                                             FormulaDescription precisionDescription)
            throws GLanguageException {
        List<AbstractFormula> list = new ArrayList<>();
        list.addAll(parameters);
        if (parameters.size() < 2) {
            list.add(getDefaultPrecision(description, precisionDescription));
        }
        return list;
    }

    /**
     * Get the rounded value as {@link Integer}<br>
     * Calling this method is equivalent to calling {@link RoundingFormula#doGetNumericValue(Evaluator)} method and
     * applying {@link Double#intValue()} on the result
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the rounded value as {@link Integer} - the integer part of the value as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        try {
            return getNumericValue().intValue();
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.INTEGER, null));
            throw e;
        }
    }

    /**
     * Get the rounded value as {@link Double}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the rounded value as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        try {
            FormulaReturnType returnType = getParameters().get(0).getReturnType(evaluator);
            switch (returnType) {
                case INTEGER:
                    return Rounder.round(getParameters().get(0).getIntegerValue(evaluator),
                                         getRoundingType(),
                                         getParameters().get(1).getNumericValue(evaluator));
                case NUMERIC:
                    return Rounder.round(getParameters().get(0).getNumericValue(evaluator),
                                         getRoundingType(),
                                         getParameters().get(1).getNumericValue(evaluator));
                default:
                    throw new GLanguageException(new FormulaWrongParameterTypeInnerError(this,
                                                                                         evaluator,
                                                                                         "doGetNumericValue",
                                                                                         "",
                                                                                         1,
                                                                                         returnType,
                                                                                         Arrays.asList
                                                                                                 (FormulaReturnType
                                                                                                          .INTEGER,
                                                                                                       FormulaReturnType.NUMERIC)));
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.NUMERIC,
                                                                         null));
            throw e;
        }
    }

    /**
     * Get the rounding type
     *
     * @return the rounding type
     */
    public abstract RoundingType getRoundingType();

    /**
     * Get the default precision for a {@link FormulaDescription}<br>
     * The default precision depends on the type of the formula<br>
     * If the {@code description} is of type {@link FormulaType#F_BANKERS_ROUNDED} or {@link FormulaType#F_TRUNC},
     * the default precision is 2 (= 2 decimals)<br>
     * Else, the default precision is 1 (= rounding to a multiple of 1)
     *
     * @param description the {@link FormulaDescription} of this formula
     * @param precisionDescription the {@link FormulaDescription} of the {@link AbstractFormula} to be returned
     * @return the default precision for the {@link FormulaDescription}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    public static AbstractFormula getDefaultPrecision(FormulaDescription description,
                                                      FormulaDescription precisionDescription) throws GLanguageException {
        try {
            switch (description.getType()) {
                case F_BANKERS_ROUNDED:
                    //fall through
                case F_TRUNC:
                    return new FormulaTerminalInteger(precisionDescription, "2");
                case F_CEIL:
                    //fall through
                case F_FLOOR:
                    //fall through
                case F_ROUNDED:
                    return new FormulaTerminalInteger(precisionDescription, "1");
                default:
                    //TODO
                    //Throw an error
                    return null;
            }
        } catch (GLanguageException e) {
            // TODO
            // e.getError().setOuterError(new FormulaEvaluateNumericInnerError(this, evaluator));
            throw e;
        }
    }

    @Override
    public String asText() {
        return operationAsText() + "(" + getParameters().get(0).asText() + "; " + getParameters().get(1).asText() + ")";
    }

    public abstract String operationAsText();

}
