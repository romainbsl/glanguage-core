package be.groups.glanguage.glanguage.api.entities.evaluation;

import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

import java.util.Map;

/**
 * Created by michotte on 14/12/2016.
 */
public interface Evaluator {

    /**
     * Get the context of evaluation
     * @return The context of evaluation
     */
    public Object getContext();

    /**
     * Get the {@link RuleVersion} corresponding to a {@link RuleIdentity} id
     * @param ruleIdentityId The id of the {@link RuleIdentity}
     * @return The {@link RuleVersion} corresponding to the {@link RuleIdentity} id {@code ruleIdentityId}
     */
    public RuleVersion getRuleVersion(String ruleIdentityId);

    /**
     * Get the map of {@link RuleVersion} that have been evaluated by this evaluator along with their value
     * @return The map of {@link RuleVersion} that have been evaluated by this evaluator along with their value
     */
    public Map<RuleVersion, Object> getEvaluatedRuleVersions();

    /**
     * Is a {@link RuleVersion} evaluated ?
     * @param ruleVersion The {@link RuleVersion}
     * @return true if the {@link RuleVersion} {@code ruleVersion} is evaluated, false otherwise
     */
    public default boolean isRuleVersionEvaluated(RuleVersion ruleVersion) {
        return getEvaluatedRuleVersions().containsKey(ruleVersion);
    }

    /**
     * Evaluate a {@link RuleVersion} without setting its value
     * @param ruleVersion The {@link RuleVersion} to evaluate
     */
    public default void evaluateRuleVersion(RuleVersion ruleVersion) {
        if (!isRuleVersionEvaluated(ruleVersion)) {
            ruleVersion.getValue(this);
        }
    }

    public default void addEvaluatedRuleVersion(RuleVersion ruleVersion, Object value) {
        getEvaluatedRuleVersions().put(ruleVersion, value);
    }

    public default Object getRuleVersionValue(RuleVersion ruleVersion) {
        return getEvaluatedRuleVersions().get(ruleVersion);
    }
}
