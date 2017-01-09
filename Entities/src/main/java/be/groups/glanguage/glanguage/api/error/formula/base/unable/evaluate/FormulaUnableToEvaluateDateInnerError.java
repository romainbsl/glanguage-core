package be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaUnableToEvaluateDateInnerError extends FormulaUnableToEvaluateTypeInnerError {

    public FormulaUnableToEvaluateDateInnerError(AbstractFormula formula,
                                                 Evaluator evaluator) {
        super(formula, evaluator, "getDateValue");
    }

    public FormulaUnableToEvaluateDateInnerError(AbstractFormula formula,
                                                 Evaluator evaluator,
                                                 String cause) {
        super(formula, evaluator, "getDateValue", cause);
    }

}
