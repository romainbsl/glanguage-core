package be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaUnableToEvaluateStringInnerError extends FormulaUnableToEvaluateTypeInnerError {

    public FormulaUnableToEvaluateStringInnerError(AbstractFormula formula,
                                                   Evaluator evaluator) {
        super(formula, evaluator, "getStringValue");
    }

}
