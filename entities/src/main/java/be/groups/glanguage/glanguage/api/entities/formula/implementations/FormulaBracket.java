package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@DiscriminatorValue(FormulaType.Values.F_BRACKETS)
public class FormulaBracket extends AbstractFormula {

    protected FormulaBracket() {
        super();
    }

    public FormulaBracket(FormulaDescription description, AbstractFormula child) throws GLanguageException {
        super(description);
        if (child == null) {
            throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
        }
        this.parameters = new ArrayList<>();
        this.parameters.add(child);
    }

    @Transient
    @Override
    public boolean isTerminal() {
        return false;
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getIntegerValue(evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getNumericValue(evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getStringValue(evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getBooleanValue(evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getDateValue(evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getDurationValue(evaluator);
    }

    @Transient
    @Override
    public String asText() {
        return "(" + getParameters().get(0).asText() + ")";
    }

}