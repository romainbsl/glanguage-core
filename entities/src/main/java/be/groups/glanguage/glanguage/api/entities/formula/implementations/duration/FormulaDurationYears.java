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
import java.time.Period;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_YEARS)
public class FormulaDurationYears extends DurationFormula {

    public FormulaDurationYears() {
        super();
    }

    public FormulaDurationYears(FormulaDescription description,
                                List<AbstractFormula> parameters,
                                Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        switch (getParameters().get(0).getReturnType(evaluator)) {
            case INTEGER:
                return getParameters().get(0).getIntegerValue(evaluator);
            case DATE:
                return getParameters().get(0).getDateValue(evaluator).getYear();
            case DURATION:
                return Math.toIntExact(getParameters().get(0).getDurationValue(evaluator)
                                               .toDays()) / YEAR_AVG_DAYS_COUNT;
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
        return Duration.ofDays(Period.ofYears(getIntegerValue(evaluator)).getYears() * YEAR_AVG_DAYS_COUNT);
    }

    @Override
    public String operationAsText() {
        return "years";
    }

}
