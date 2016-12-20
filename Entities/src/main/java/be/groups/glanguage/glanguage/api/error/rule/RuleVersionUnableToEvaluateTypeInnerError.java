package be.groups.glanguage.glanguage.api.error.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GlanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.MessageInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public class RuleVersionUnableToEvaluateTypeInnerError extends MessageInnerError {

    public RuleVersionUnableToEvaluateTypeInnerError(RuleVersion ruleVersion, Evaluator evaluator, String methodName) {
        this(GlanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE_TYPE.getCode(), ruleVersion, evaluator, methodName);
    }

    public RuleVersionUnableToEvaluateTypeInnerError(String code, RuleVersion ruleVersion, Evaluator evaluator,
                                                         String methodName) {
        super(code, createMessage(ruleVersion, evaluator, methodName));
    }

    private static String createMessage(RuleVersion ruleVersion, Evaluator evaluator, String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to evaluate rule version");
        sb.append("[id: " + ruleVersion.getId() + ", code: " + ruleVersion.getCode() + "]");
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
