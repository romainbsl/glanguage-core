package be.groups.glanguage.glanguage.api.error.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GlanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.MessageInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public class AbstractFormulaUnableToEvaluateInnerError extends MessageInnerError {

    public AbstractFormulaUnableToEvaluateInnerError(AbstractFormula formula, Evaluator evaluator) {
        super(GlanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE.getCode(), createMessage(formula, evaluator));
    }

    private static String createMessage(AbstractFormula formula, Evaluator evaluator) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to evaluate formula ");
        sb.append(formula.getClass().getName());
        sb.append("[id: " + formula.getId() + "]");
        sb.append("(evaluator: ");
        if (evaluator == null) {
            sb.append("null)");
        } else {
            sb.append("not null)");
        }
        return sb.toString();
    }

}
