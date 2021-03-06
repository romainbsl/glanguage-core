package be.groups.glanguage.core.entities.formula.implementations.duration;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.util.List;

/**
 * Formula implementing an operation on a duration to get the number of minutes represented by it, or to create a
 * duration representing a number of minutes
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_MINUTES)
public class FormulaDurationMinutes extends DurationFormula {

    public FormulaDurationMinutes() {
        super();
    }

    public FormulaDurationMinutes(FormulaDescription description,
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
     * {@link FormulaReturnType#DURATION} ->
     * return the value of {@link Duration#toMinutes()} of the {@link AbstractFormula#getDurationValue(Evaluator)} of the
     * parameter</li>
     * </ul>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return an {@link Integer} representing :
     * <ul>
     * <li>the value of the parameter if the the parameter type is {@link FormulaReturnType#INTEGER}</li>
     * <li>the number of minutes represented by the parameter if the parameter type is
     * {@link FormulaReturnType#DURATION}</li>
     * </ul>
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        if (getParameters().get(0).getReturnType(evaluator).equals(FormulaReturnType.DURATION)) {
            return Math.toIntExact(getParameters().get(0).getDurationValue(evaluator).toMinutes());
        } else {
            return getParameters().get(0).getIntegerValue(evaluator);
        }
    }

    /**
     * Get the value as {@link Duration} representing the number of minutes of the parameter
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return a {@link Duration} representing the number of minutes of the parameter
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        return Duration.ofMinutes(getIntegerValue(evaluator));
    }

    @Override
    public String operationAsText() {
        return "minutes";
    }

}
