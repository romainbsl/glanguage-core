/**
 *
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
        .TerminalFormulaUnableToInitializeMalformedValueInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
        .TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
        .TerminalFormulaUnableToParseValueInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula representing a constant integer value
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

    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.BOOLEAN;
    }

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
