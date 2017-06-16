package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_UNARY_MINUS)
public class FormulaUnaryMinus extends UnaryFormula {

    protected FormulaUnaryMinus() {
        super();
    }

    public FormulaUnaryMinus(FormulaDescription description,
                             AbstractFormula child,
                             Evaluator evaluator) throws GLanguageException {
        super(description, child, evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    public Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return -getParameters().get(0).getIntegerValue(evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    public Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return -getParameters().get(0).getNumericValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "-";
    }

}
