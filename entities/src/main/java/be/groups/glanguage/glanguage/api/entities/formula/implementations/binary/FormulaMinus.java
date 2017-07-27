package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Formula implementing a "subtraction" operation<br>
 * This formula is of type {@link FormulaType#OP_MINUS}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_MINUS)
public class FormulaMinus extends BinaryFormula {

    protected FormulaMinus() {
        super();
    }

    public FormulaMinus(FormulaDescription description,
                        AbstractFormula child1,
                        AbstractFormula child2,
                        Evaluator evaluator) throws GLanguageException {
        super(description, child1, child2, evaluator);
    }

    /**
     * Get the result of applying a "subtraction" operation on the integer values of the parameters :<br>
     * the result of the subtraction of the integer value of the second parameter from the integer value of the first
     * one
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a "subtraction" operation on the integer values of the parameters
     * one
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getIntegerValue(evaluator) - getParameters().get(1).getIntegerValue(evaluator);
    }

    /**
     * Get the result of applying a "subtraction" operation on the numeric values of the parameters :<br>
     * the result of the subtraction of the numeric value of the second parameter from the numeric value of the first
     * one
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a "subtraction" operation on the numeric values of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getNumericValue(evaluator) - getParameters().get(1).getNumericValue(evaluator);
    }

    /**
     * Get the result of applying a "subtraction" operation on the date value of the first parameter and the duration
     * value of the second parameter :<br>
     * the result of the subtraction of the duration value of the second parameter from the date value of the first one
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a "subtraction" operation on the duration values of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getDateValue(evaluator).minusDays(getParameters().get(1)
                                                                                .getDurationValue(evaluator).toDays());
    }

    /**
     * Get the result of applying a "subtraction" operation on the duration values of the parameters :<br>
     * the result of the subtraction of the duration value of the second parameter from the duration value of the
     * first one
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a "subtraction" operation on the duration values of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getDurationValue(evaluator).minusDays(getParameters().get(1)
                                                                                    .getDurationValue(evaluator)
                                                                                    .toDays());
    }

    @Override
    public String operationAsText() {
        return "-";
    }

}
