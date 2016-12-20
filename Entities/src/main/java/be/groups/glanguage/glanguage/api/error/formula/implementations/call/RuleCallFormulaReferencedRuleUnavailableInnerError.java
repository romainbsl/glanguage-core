package be.groups.glanguage.glanguage.api.error.formula.implementations.call;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.RuleCallFormula;
import be.groups.glanguage.glanguage.api.error.GlanguageErrorRegistry;

/**
 * Created by michotte on 20/12/2016.
 */
public class RuleCallFormulaReferencedRuleUnavailableInnerError extends InnerError {

    public RuleCallFormulaReferencedRuleUnavailableInnerError(RuleCallFormula formula, Evaluator evaluator) {
        super(GlanguageErrorRegistry.FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE.getCode(), createMessage
                (formula, evaluator), null);
    }

    private static String createMessage(RuleCallFormula formula, Evaluator evaluator) {
        StringBuilder sb = new StringBuilder();
        sb.append(formula.getClass().getName());
        sb.append("[id: " + formula.getId() + "]");
        sb.append("(evaluator: ");
        if (evaluator == null) {
            sb.append("null) -> referenced rule not known => branch !");
        } else {
            sb.append("not null) -> referenced rule[id: " + formula.getConstantValue() + "] not available");
        }
        return sb.toString();
    }
}
