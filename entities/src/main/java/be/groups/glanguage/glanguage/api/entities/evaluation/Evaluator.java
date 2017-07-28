package be.groups.glanguage.glanguage.api.entities.evaluation;

import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

import java.util.Map;

/**
 * This class provides the ability of evaluating {@link RuleVersion rule versions} and to cache their values.<br>
 * It also provides a method to get access to a context needed by
 * {@link be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet get formulas} in order
 * to have access to the methods by reflection on that context object
 *
 * @author michotte
 */
public interface Evaluator {

    /**
     * Get the context of evaluation
     *
     * @return The context of evaluation
     */
    Object getContext();

    /**
     * Get the {@link RuleVersion} corresponding to a {@link RuleIdentity} id
     *
     * @param ruleIdentityId The id of the {@link RuleIdentity}
     * @return The {@link RuleVersion} corresponding to the {@link RuleIdentity} id {@code ruleIdentityId}
     */
    RuleVersion getRuleVersion(String ruleIdentityId);

    /**
     * Get the map of {@link RuleVersion}'s that have been evaluated by this evaluator along with their value
     *
     * @return The map of {@link RuleVersion}'s that have been evaluated by this evaluator along with their value
     */
    Map<RuleVersion, Object> getEvaluatedRuleVersions();

    /**
     * Is a {@link RuleVersion} evaluated ?
     *
     * @param ruleVersion The {@link RuleVersion}
     * @return true if the {@link RuleVersion} {@code ruleVersion} is evaluated, false otherwise
     */
    default boolean isRuleVersionEvaluated(RuleVersion ruleVersion) {
        return getEvaluatedRuleVersions().containsKey(ruleVersion);
    }

    /**
     * Evaluate a {@link RuleVersion} without setting its value
     *
     * @param ruleVersion The {@link RuleVersion} to evaluate
     */
    default void evaluateRuleVersion(RuleVersion ruleVersion) throws GLanguageException {
        if (!isRuleVersionEvaluated(ruleVersion)) {
            ruleVersion.getValue(this);
        }
    }

    /**
     * Add a {@link RuleVersion} as evaluated with its value
     *
     * @param ruleVersion The {@link RuleVersion} to add as evaluated
     * @param value The value to associate with {@code ruleVersion}
     */
    default void addEvaluatedRuleVersion(RuleVersion ruleVersion, Object value) {
        getEvaluatedRuleVersions().put(ruleVersion, value);
    }

    /**
     * Get the value associated with a {@link RuleVersion}
     *
     * @param ruleVersion The {@link RuleVersion} to get the associated value
     * @return The value associated to {@code ruleVersion} if it has been evaluated by this evaluator, null otherwise
     */
    default Object getRuleVersionValue(RuleVersion ruleVersion) {
        return getEvaluatedRuleVersions().get(ruleVersion);
    }
}
