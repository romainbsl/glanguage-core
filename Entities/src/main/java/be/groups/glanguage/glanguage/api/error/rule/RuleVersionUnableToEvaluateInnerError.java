package be.groups.glanguage.glanguage.api.error.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GlanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.MessageInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public class RuleVersionUnableToEvaluateInnerError extends MessageInnerError {

    public RuleVersionUnableToEvaluateInnerError(RuleVersion ruleVersion, Evaluator evaluator) {
        this(GlanguageErrorRegistry.RULE_VERSION_UNABLE_TO_EVALUATE.getCode(), ruleVersion, evaluator);
    }

    public RuleVersionUnableToEvaluateInnerError(String code, RuleVersion ruleVersion, Evaluator evaluator) {
        super(code, createMessage(ruleVersion, evaluator));
    }

    private static String createMessage(RuleVersion ruleVersion, Evaluator evaluator) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to evaluate rule version");
        sb.append("[id: " + ruleVersion.getId() + ", code: " + ruleVersion.getCode() + "]");
        sb.append(" (evaluator: ");
        if (evaluator == null) {
            sb.append("null)");
        } else {
            sb.append("not null)");
        }
        return sb.toString();
    }
}
