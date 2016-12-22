package be.groups.glanguage.glanguage.api.error.formula.implementations;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 20/12/2016.
 */
public class AbstractFormulaUnableToEvaluateTypeInnerError extends InnerError {

    public AbstractFormulaUnableToEvaluateTypeInnerError(AbstractFormula formula, Evaluator evaluator, String methodName) {
        this(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE_TYPE.getCode(), formula, evaluator, methodName);
    }

    public AbstractFormulaUnableToEvaluateTypeInnerError(String code, AbstractFormula formula, Evaluator evaluator, String
            methodName) {
        super(code, createMessage(formula, evaluator, methodName), null);
    }

    private static String createMessage(AbstractFormula formula, Evaluator evaluator, String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to evaluate formula ");
        sb.append(formula.getClass().getName());
        sb.append("[id: " + formula.getId() + "]");
        sb.append("." + methodName);
        sb.append("(evaluator: ");
        if (evaluator == null) {
            sb.append("null)");
        } else {
            sb.append("not null)");
        }
        return sb.toString();
    }

}
