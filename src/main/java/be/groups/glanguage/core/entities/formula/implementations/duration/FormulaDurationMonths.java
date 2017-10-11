package be.groups.glanguage.core.entities.formula.implementations.duration;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Formula implementing an operation on a duration to get the number of months represented by it, or a date to extract
 * the month part from it, or to create a duration representing a number of months
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_MONTHS)
public class FormulaDurationMonths extends DurationFormula {

    public FormulaDurationMonths() {
        super();
    }

    public FormulaDurationMonths(FormulaDescription description,
                                 List<AbstractFormula> parameters,
                                 Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);
    }

    /**
     * Get the value as {@link Integer}<br>
     * The value depends on the type of the parameter :
     * <ul>
     * <li>parameter type :
     * {@link FormulaReturnType#INTEGER} ->
     * return the value of the {@link AbstractFormula#getIntegerValue(Evaluator)} of the parameter</li>
     * <li>parameter type :
     * {@link FormulaReturnType#DATE} ->
     * return the value of {@link LocalDate#getMonthValue()} of the {@link AbstractFormula#getDateValue(Evaluator)} of
     * the parameter</li>
     * <li>parameter type :
     * {@link FormulaReturnType#DURATION} ->
     * return the value of {@link Duration#toDays()} divided by {@link DurationFormula#MONTH_AVG_DAYS_COUNT} of the
     * {@link AbstractFormula#getDurationValue(Evaluator)} of the parameter</li>
     * </ul>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return an {@link Integer} representing :
     * <ul>
     * <li>the value of the parameter if the the parameter type is {@link FormulaReturnType#INTEGER}</li>
     * <li>the month part of the parameter if the parameter type is {@link FormulaReturnType#DATE}</li>
     * <li>the number of months represented by the parameter if the parameter type is
     * {@link FormulaReturnType#DURATION}</li>
     * </ul>
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        switch (getParameters().get(0).getReturnType(evaluator)) {
            case INTEGER:
                return getParameters().get(0).getIntegerValue(evaluator);
            case DATE:
                return getParameters().get(0).getDateValue(evaluator).getMonthValue();
            case DURATION:
                return Math.toIntExact(getParameters().get(0).getDurationValue(evaluator)
                                               .toDays()) / MONTH_AVG_DAYS_COUNT;
            default:
                throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.INTEGER,
                    "Parameter of wrong type : " + getParameters().get(0).getReturnType(evaluator) + " ; should be " +
                        "INTEGER, DATE or DURATION"));
        }
    }

    /**
     * Get the value as {@link Duration} representing the number of months of the parameter
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return a {@link Duration} representing the number of months of the parameter
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        return Duration.ofDays(Period.ofMonths(getIntegerValue(evaluator)).getMonths() * MONTH_AVG_DAYS_COUNT);
    }

    @Override
    public String operationAsText() {
        return "months";
    }

}
