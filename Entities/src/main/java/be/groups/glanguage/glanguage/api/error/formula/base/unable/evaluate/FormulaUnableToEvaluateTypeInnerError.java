package be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public abstract class FormulaUnableToEvaluateTypeInnerError extends FormulaInnerError {

    public FormulaUnableToEvaluateTypeInnerError(AbstractFormula formula,
                                                 Evaluator evaluator,
                                                 String methodName) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE_TYPE, formula, evaluator, methodName, null);
    }

    public FormulaUnableToEvaluateTypeInnerError(AbstractFormula formula,
                                                 Evaluator evaluator,
                                                 String methodName,
                                                 String cause) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE_TYPE, formula, evaluator, methodName, cause);
    }

}
