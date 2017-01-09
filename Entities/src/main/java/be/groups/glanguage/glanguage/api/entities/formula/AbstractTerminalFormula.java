package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke.*;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
        .TerminalFormulaUnableToInitializeNullValueInnerError;
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
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(new FormulaCannotInvokeEvaluationIntegerMethodInnerError(this, evaluator));
    }

    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(new FormulaCannotInvokeEvaluationNumericMethodInnerError(this, evaluator));
    }

    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        if (getConstantValue() != null) {
            return getConstantValue();
        } else {
            throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
                                                                                                  evaluator,
                                                                                                  "doGetStringValue"));
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(new FormulaCannotInvokeEvaluationBooleanMethodInnerError(this, evaluator));
    }

    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(new FormulaCannotInvokeEvaluationDateMethodInnerError(this, evaluator));
    }

    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(new FormulaCannotInvokeEvaluationDurationMethodInnerError(this, evaluator));
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
