package be.groups.glanguage.glanguage.api.error.rule;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 20/12/2016.
 */
public class RuleVersionUnableToCheckApplicabilityInnerError extends InnerError {

    public RuleVersionUnableToCheckApplicabilityInnerError(RuleVersion ruleVersion, Evaluator evaluator) {
        this(GLanguageErrorRegistry.RULE_VERSION_UNABLE_TO_CHECK_APPLICABILITY.getCode(), ruleVersion, evaluator);
    }

    public RuleVersionUnableToCheckApplicabilityInnerError(String code, RuleVersion ruleVersion, Evaluator evaluator) {
        super(code, createMessage(ruleVersion, evaluator), null);
    }

    private static String createMessage(RuleVersion ruleVersion, Evaluator evaluator) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to check applicability of rule version");
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
