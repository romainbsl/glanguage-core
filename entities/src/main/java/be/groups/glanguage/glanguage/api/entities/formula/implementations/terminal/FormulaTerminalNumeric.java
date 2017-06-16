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
@DiscriminatorValue(FormulaType.Values.TERMINAL_NUMERIC)
public class FormulaTerminalNumeric extends AbstractTerminalFormula {

    protected FormulaTerminalNumeric() {
        super();
    }

    public FormulaTerminalNumeric(FormulaDescription description, String constantValue) throws GLanguageException {
        super(description, constantValue);
    }

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

    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.NUMERIC;
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getNumericValue(evaluator).intValue();
    }

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
