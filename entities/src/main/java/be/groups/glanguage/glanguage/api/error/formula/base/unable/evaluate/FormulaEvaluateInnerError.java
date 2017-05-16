package be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;
import be.groups.glanguage.glanguage.api.error.utils.EvaluationMethod;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaEvaluateInnerError extends FormulaInnerError {

    public FormulaEvaluateInnerError(AbstractFormula formula, Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE, formula, evaluator, EvaluationMethod.VALUE.getName(), null);
    }

}
