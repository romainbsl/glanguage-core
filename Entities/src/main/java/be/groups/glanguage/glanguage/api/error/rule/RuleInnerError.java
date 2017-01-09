package be.groups.glanguage.glanguage.api.error.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.GLanguageInnerError;

/**
 * Created by michotte on 23/12/2016.
 */
public abstract class RuleInnerError extends GLanguageInnerError {

    private String ruleVersionIdentification;
    private String evaluator;
    private String methodName;
    private String cause;

    public RuleInnerError(GLanguageErrorRegistry error, RuleVersion ruleVersion, Evaluator evaluator, String
            methodName, String cause) {
        super(error.getCode());
        setMainMessage(error.getMessage());
        createFields(ruleVersion, evaluator, methodName, cause);
        setMessage(createMessage());
    }

    private void createFields(RuleVersion ruleVersion, Evaluator evaluator, String methodName, String cause) {
        this.ruleVersionIdentification = "[id: " + ruleVersion.getId() + ", code: " + ruleVersion.getCode() + "]";
        this.evaluator = evaluator == null ? "null" : "not null";
        this.methodName = methodName;
        this.cause = cause;
    }

    public String createMessage() {
        StringBuilder sb = new StringBuilder(super.createMessage());
        sb.append(": " + ruleVersionIdentification);
        sb.append("." + methodName);
        sb.append("(evaluator: ");
        sb.append("(evaluator: " + evaluator + ")");
        if (cause != null) {
            sb.append(" - Cause: " + cause);
        }
        return sb.toString();
    }


}
