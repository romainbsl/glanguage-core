package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeNullValueInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedList;

@Entity
public abstract class AbstractTerminalFormula extends AbstractFormula {

    protected AbstractTerminalFormula() {
        super();
    }

    public AbstractTerminalFormula(FormulaDescription description) {
        super(description);
    }

    public AbstractTerminalFormula(FormulaDescription description, String constantValue) throws GLanguageException {
        this(description);

        validate(constantValue);

        this.setConstantValue(constantValue);
    }

    @JsonIgnore
    @Transient
    public abstract boolean isValid(Evaluator evaluator);

    @JsonIgnore
    public abstract void validate(String constantValue) throws GLanguageException;

    @Transient
    @Override
    public LinkedList<AbstractFormula> getParameters() {
        return null;
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
    public boolean isTerminal() {
        return true;
    }

    @Override
    public String asText() {
        return getConstantValue();
    }

}
