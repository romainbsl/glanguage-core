/**
 *
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.errorframework.core.error.ErrorEnum;
import be.groups.errorframework.core.error.RootError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
        .TerminalFormulaUnableToInitializeMalformedValueInnerError;


import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
        .TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
        .TerminalFormulaUnableToParseValueInnerError;
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

    private Boolean booleanValue;

    protected FormulaTerminalBoolean() {
        super();
    }

    public FormulaTerminalBoolean(FormulaDescription description, String constantValue) {
        super(description, constantValue);
    }

    public FormulaTerminalBoolean(FormulaDescription description, Boolean constantValue) {
        this(description, constantValue.toString());
    }

    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException {
        if (booleanValue == null) {
            initializeBooleanValue(getConstantValue());
        }
        return booleanValue;
    }

    private void initializeBooleanValue(String constantValue) throws GLanguageEvaluationException {
        if (constantValue != null) {
            if (constantValue.equalsIgnoreCase("true") || constantValue.equalsIgnoreCase("false")) {
                try {
                    this.booleanValue = Boolean.valueOf(constantValue);
                } catch (Exception e) {
                    RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
                    error.setInnererror(new TerminalFormulaUnableToParseValueInnerError(constantValue,
                            FormulaReturnType.BOOLEAN,
                            "[true;false;TRUE;FALSE]",
                            e));
                    throw new GLanguageEvaluationException(error);
                }
            } else {
                RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
                error.setInnererror(new TerminalFormulaUnableToInitializeMalformedValueInnerError(constantValue,
                        FormulaReturnType.BOOLEAN,
                        "[true;false;TRUE;FALSE]"));
                throw new GLanguageEvaluationException(error);
            }
        } else {
            RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
            error.setInnererror(new TerminalFormulaUnableToInitializeNullValueInnerError());
            throw new GLanguageEvaluationException(error);
        }
    }

}
