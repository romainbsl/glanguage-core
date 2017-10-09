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
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeMalformedValueInnerError;
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToParseValueInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula representing a constant boolean value
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_BOOLEAN)
public class FormulaTerminalBoolean extends AbstractTerminalFormula {

    protected FormulaTerminalBoolean() {
        super();
    }

    public FormulaTerminalBoolean(FormulaDescription description, String constantValue) throws GLanguageException {
        super(description, constantValue);
    }

    public FormulaTerminalBoolean(FormulaDescription description, Boolean constantValue) throws GLanguageException {
        this(description, constantValue.toString());
    }

    /**
     * Is this valid ?<br>
     * This is valid if :
     * <ol>
     * <li>the {@link FormulaTerminalBoolean#getConstantValue()} is not null</li>
     * <li>the {@link FormulaTerminalBoolean#getConstantValue()} is a string equal to "true" or "false"
     * ignoring the case</li>
     * <li>the {@link Boolean#valueOf(String)} does not throw an exception</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if this is valid, false otherwise
     */
    @Override
    public boolean isValid(Evaluator evaluator) {
        if (getConstantValue() != null) {
            if (getConstantValue().equalsIgnoreCase("true") || getConstantValue().equalsIgnoreCase("false")) {
                try {
                    Boolean.valueOf(getConstantValue());
                } catch (Exception e) {
                    return false;
                }
            } else {
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
     * <li>the {@link FormulaTerminalBoolean#getConstantValue()} is not null</li>
     * <li>the {@link FormulaTerminalBoolean#getConstantValue()} is a string equal to "true" or "false"
     * ignoring the case</li>
     * <li>the {@link Boolean#valueOf(String)} does not throw an exception</li>
     * </ol>
     *
     * @param constantValue the value to be validated
     * @throws GLanguageException if the {@code constantValue} is null or not equal to "true" or "false" ignoring the
     *                            case or if the {@link Boolean#valueOf(String)} throws an exception
     */
    @Override
    public void validate(String constantValue) throws GLanguageException {
        if (constantValue != null) {
            if (constantValue.equalsIgnoreCase("true") || constantValue.equalsIgnoreCase("false")) {
                try {
                    Boolean.valueOf(constantValue);
                } catch (Exception e) {
                    throw new GLanguageException(new TerminalFormulaUnableToParseValueInnerError(this,
                                                                                                 null,
                                                                                                 ErrorMethod.VALIDATE
                                                                                                         .getName(),
                                                                                                 constantValue,
                                                                                                 FormulaReturnType
                                                                                                         .BOOLEAN,
                                                                                                 "[true;false;TRUE;"
                                                                                                         + "FALSE]",
                                                                                                 e));
                }
            } else {
                throw new GLanguageException(new TerminalFormulaUnableToInitializeMalformedValueInnerError(this,
                                                                                                           null,
                                                                                                           ErrorMethod.VALIDATE
                                                                                                                   .getName(),
                                                                                                           constantValue,
                                                                                                           FormulaReturnType.BOOLEAN,
                                                                                                           "[true;false;TRUE;FALSE]"));
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
     * The return type of this type of formula is always {@link FormulaReturnType#BOOLEAN}
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return always {@link FormulaReturnType#BOOLEAN}
     */
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.BOOLEAN;
    }

    /**
     * Get the value as {@link Boolean}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return true or false corresponding to the parameter value
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        if (getConstantValue() != null) {
            if (getConstantValue().equalsIgnoreCase("true") || getConstantValue().equalsIgnoreCase("false")) {
                try {
                    return Boolean.valueOf(getConstantValue());
                } catch (Exception e) {
                    throw new GLanguageException(new TerminalFormulaUnableToParseValueInnerError(this,
                                                                                                 evaluator,
                                                                                                 "doGetBooleanValue",
                                                                                                 getConstantValue(),
                                                                                                 FormulaReturnType
                                                                                                         .BOOLEAN,
                                                                                                 "[true;false;TRUE;"
                                                                                                         + "FALSE]",
                                                                                                 e));
                }
            } else {
                throw new GLanguageException(new TerminalFormulaUnableToInitializeMalformedValueInnerError(this,
                                                                                                           evaluator,
                                                                                                           "doGetBooleanValue",
                                                                                                           getConstantValue(),
                                                                                                           FormulaReturnType.BOOLEAN,
                                                                                                           "[true;false;TRUE;FALSE]"));
            }
        } else {
            throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
                                                                                                  evaluator,
                                                                                                  "doGetBooleanValue"));
        }
    }

}
