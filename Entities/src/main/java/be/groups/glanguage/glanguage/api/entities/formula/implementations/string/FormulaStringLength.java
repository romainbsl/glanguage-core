package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_STRING_LENGTH)
public class FormulaStringLength extends AbstractNonTerminalFormula {

    public FormulaStringLength() {
        super();
    }

    public FormulaStringLength(FormulaDescription description, List<AbstractFormula> parameters) {
        super(description);

        if (parameters == null) {
            throw new IllegalArgumentException("parameters must be non-null");
        }

        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageEvaluationException {
        return String.valueOf(getIntegerValue(evaluator));
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageEvaluationException {
        return getParameters().get(0).getStringValue(evaluator) != null ? getParameters().get(0)
                .getStringValue(evaluator).length() : 0;
    }

    @Override
    public String asText() {
        return "stringLength(" + getParameters().get(0).asText() + ")";
    }

}
