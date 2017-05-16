package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

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
import java.util.List;

@Entity
@DiscriminatorValue(value = FormulaType.Values.F_DAYS)
public class FormulaDurationDays extends DurationFormula {

    public FormulaDurationDays() {
        super();
    }

    public FormulaDurationDays(FormulaDescription description, List<AbstractFormula> parameters) throws GLanguageException {
        super(description, parameters);
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        switch (getParameters().get(0).getReturnType(evaluator)) {
            case INTEGER:
                return getParameters().get(0).getIntegerValue(evaluator);
            case DATE:
                return getParameters().get(0).getDateValue(evaluator).getDayOfMonth();
            case DURATION:
                return Math.toIntExact(getParameters().get(0).getDurationValue(evaluator).toDays());
            default:
                throw new UnsupportedOperationException("Cannot invoke getIntegerValue() method on " + this.getClass()
                        .getName() + " object with a parameter of type " + getParameters().get(0)
                        .getReturnType(evaluator));
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        return Duration.ofDays(getIntegerValue(evaluator));
    }

    @Override
    public String operationAsText() {
        return "days";
    }

}
