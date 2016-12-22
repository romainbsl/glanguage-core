package be.groups.glanguage.glanguage.api.business.evaluation;

import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michotte on 14/12/2016.
 */
public class PlanEvaluator implements Evaluator {

    private Plan plan;
    private Object context;

    private Map<String, RuleVersion> ruleVersionsByRuleIdentityId;
    private Map<RuleVersion, Object> cache;

    public PlanEvaluator(Plan plan, Object context) {
        this.plan = plan;
        this.context = context;

        this.ruleVersionsByRuleIdentityId = new HashMap<>();
        this.cache = new HashMap<>();
    }

    @Override
    public Object getContext() {
        return context;
    }

    @Override
    public RuleVersion getRuleVersion(String ruleIdentityId) {
        if (!ruleVersionsByRuleIdentityId.containsKey(ruleIdentityId)) {
            ruleVersionsByRuleIdentityId.put(ruleIdentityId, plan.getEffectiveRuleVersionByRuleIdentityId
                    (ruleIdentityId));
        }
        return ruleVersionsByRuleIdentityId.get(ruleIdentityId);
    }

    @Override
    public Map<RuleVersion, Object> getEvaluatedRuleVersions() {
        return cache;
    }

    /**
     * Evaluate the whole {@code plan}
     */
    public void evaluatePlan() throws GLanguageEvaluationException {
        plan.evaluate(this);
    }

    /**
     * Evaluate a {@link RuleVersion} corresponding to an identifier, recursively or not
     * @param ruleIdentifier The identifier of the {@link RuleVersion} to evaluate
     * @param recursive Flag saying if evaluation has to be recursive or not
     */
    public void evaluateRuleVersion(String ruleIdentifier, boolean recursive) throws GLanguageEvaluationException {
        plan.evaluate(ruleIdentifier, recursive, this);
    }

}
