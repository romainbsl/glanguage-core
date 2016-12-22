package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.errorframework.core.error.ErrorEnum;
import be.groups.errorframework.core.error.RootError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeNullValueInnerError;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.LinkedList;

@Entity
public abstract class AbstractTerminalFormula extends AbstractFormula {

    protected AbstractTerminalFormula() {
        super();
    }

    public AbstractTerminalFormula(FormulaDescription description) {
        super(description);
    }

    public AbstractTerminalFormula(FormulaDescription description, String constantValue) {
        this(description);

        if (constantValue == null) {
            throw new IllegalArgumentException("Constant value must be non-null");
        }
        this.setConstantValue(constantValue);
    }

    @Transient
    @Override
    public LinkedList<AbstractFormula> getParameters() {
        return null;
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageEvaluationException {
        throw new UnsupportedOperationException("Cannot invoke getIntegerValue() method on " + this.getClass()
                .getName() + " object");
    }

    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageEvaluationException {
        throw new UnsupportedOperationException("Cannot invoke getNumericValue() method on " + this.getClass()
                .getName() + " object");
    }

    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageEvaluationException {
        if (getConstantValue() != null) {
            return getConstantValue();
        } else {
            RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
            error.setInnererror(new TerminalFormulaUnableToInitializeNullValueInnerError());
            throw new GLanguageEvaluationException(error);
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException {
        throw new UnsupportedOperationException("Cannot invoke getBooleanValue() method on " + this.getClass()
                .getName() + " object");
    }

    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageEvaluationException {
        throw new UnsupportedOperationException("Cannot invoke getDateValue() method on " + this.getClass()
                .getName() + " object");
    }

    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageEvaluationException {
        throw new UnsupportedOperationException("Cannot invoke getDurationValue() method on " + this.getClass()
                .getName() + " object");
    }

    @JsonIgnore
    @Transient
    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public String asText() {
        return getConstantValue();
    }

}
