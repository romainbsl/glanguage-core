package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

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
@DiscriminatorValue(FormulaType.Values.OP_DIVIDE)
public class FormulaDivide extends BinaryFormula {

    protected FormulaDivide() {
        super();
    }

    public FormulaDivide(FormulaDescription description,
                         AbstractFormula child1,
                         AbstractFormula child2,
                         Evaluator evaluator) throws GLanguageException {
        super(description, child1, child2, evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getNumericValue(evaluator) / getParameters().get(1).getNumericValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "/";
    }

}
