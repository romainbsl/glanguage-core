package be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public abstract class FormulaCannotInvokeEvaluationTypedMethodInnerError extends FormulaInnerError {

    public FormulaCannotInvokeEvaluationTypedMethodInnerError(AbstractFormula formula,
                                                              Evaluator evaluator,
                                                              String methodName) {
        super(GLanguageErrorRegistry.FORMULA_CANNOT_INVOKE_EVALUATION_TYPED_METHOD, formula, evaluator, methodName, null);
    }

}
