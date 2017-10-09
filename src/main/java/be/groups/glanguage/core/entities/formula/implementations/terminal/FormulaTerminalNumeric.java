/**
 *
 */
package be.groups.glanguage.core.entities.formula.implementations.terminal;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToParseValueInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula representing a constant double value
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_NUMERIC)
public class FormulaTerminalNumeric extends AbstractTerminalFormula {

    protected FormulaTerminalNumeric() {
        super();
    }

    public FormulaTerminalNumeric(FormulaDescription description, String constantValue) throws GLanguageException {
        super(description, constantValue);
    }

    /**
     * Is this valid ?<br>
     * This is valid if :
     * <ol>
     * <li>the {@link FormulaTerminalNumeric#getConstantValue()} is not null</li>
     * <li>the {@link Double#valueOf(String)} does not throw an exception</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if this is valid, false otherwise
     */
    @Override
    public boolean isValid(Evaluator evaluator) {
        if (getConstantValue() != null) {
            try {
                Double.valueOf(getConstantValue());
            } catch (NumberFormatException nfe) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Validate this with a {@code constantValue}<br>
     * This is valid if :
     * <ol>
     * <li>the {@link FormulaTerminalNumeric#getConstantValue()} is not null</li>
     * <li>the {@link Double#valueOf(String)} does not throw an exception</li>
     * </ol>
     *
     * @param constantValue the value to be validated
     * @throws GLanguageException if the {@code constantValue} is null or if an exception is thrown during the
     * parsing of the parameter
     */
    @Override
    public void validate(String constantValue) throws GLanguageException {
        if (constantValue != null) {
            try {
                Double.valueOf(constantValue);
            } catch (NumberFormatException nfe) {
                throw new GLanguageException(new TerminalFormulaUnableToParseValueInnerError(this,
                                                                                             null,
                                                                                             ErrorMethod.VALIDATE
                                                                                                     .getName(),
                                                                                             getConstantValue(),
                                                                                             FormulaReturnType.BOOLEAN,
                                                                                             "(-)?[0-9]*(.)?[0-9]*",
                                                                                             nfe));
            }
        } else {
            throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
                                                                                                  null,
                                                                                                  ErrorMethod.VALIDATE
                                                                                                          .getName()));
        }
    }

    /**
     * Get the return type<br>
     * The return type of this type of formula is always {@link FormulaReturnType#NUMERIC}
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return always {@link FormulaReturnType#NUMERIC}
     */
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.NUMERIC;
    }

    /**
     * Get the value as {@link Integer}<br>
     * Calling this method is equivalent to calling {@link FormulaTerminalNumeric#doGetNumericValue(Evaluator)} method and
     * applying {@link Double#intValue()} on the result
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Integer} - the integer part of the value as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getNumericValue(evaluator).intValue();
    }

    /**
     * Get the value as {@link Double}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the number (with decimals) corresponding to the parameter formatted with the pattern "(-)?[0-9]*(.)
     * ?[0-9]*"
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        if (getConstantValue() != null) {
            try {
                return Double.valueOf(getConstantValue());
            } catch (NumberFormatException nfe) {
                throw new GLanguageException(new TerminalFormulaUnableToParseValueInnerError(this,
                                                                                             evaluator,
                                                                                             "doGetNumericValue",
                                                                                             getConstantValue(),
                                                                                             FormulaReturnType.BOOLEAN,
                                                                                             "(-)?[0-9]*(.)?[0-9]*",
                                                                                             nfe));
            }
        } else {
            throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
                                                                                                  evaluator,
                                                                                                  "doGetNumericValue"));
        }
    }

}
