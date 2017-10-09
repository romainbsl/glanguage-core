package be.groups.glanguage.core.business.evaluation;

import be.groups.glanguage.core.business.plan.Plan;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.core.entities.formula.implementations.call.RuleCallFormula;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.exception.GLanguageException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides the ability of evaluating a {@link Plan} and to cache the values of its
 * {@link RuleVersion rule versions}<br>
 * It also provides a method to get access to a context needed by
 * {@link FormulaGet get formulas} in order
 * to have access to the methods by reflection on that context object<br>
 * It also provides a method to get access to a {@link Plan} needed by
 * {@link RuleCallFormula rule call formulas}
 * in order to get the corresponding {@link RuleVersion} from that {@link Plan}
 *
 * @author michotte
 */
public class PlanEvaluator implements Evaluator {

    private Plan plan;
    private Object context;

    private Map<String, RuleVersion> ruleVersionsByIdentifier;
    private Map<RuleVersion, Object> cache;

    public PlanEvaluator(Plan plan, Object context) {
        this.plan = plan;
        this.context = context;

        this.ruleVersionsByIdentifier = new HashMap<>();
        this.cache = new HashMap<>();
    }

    @Override
    public Object getContext() {
        return context;
    }

    @Override
    public RuleVersion getRuleVersion(String ruleIdentifier) {
        if (!ruleVersionsByIdentifier.containsKey(ruleIdentifier)) {
            ruleVersionsByIdentifier.put(ruleIdentifier, plan.getEffectiveRuleVersionByIdenitifier(ruleIdentifier));
        }
        return ruleVersionsByIdentifier.get(ruleIdentifier);
    }

    @Override
    public Map<RuleVersion, Object> getEvaluatedRuleVersions() {
        return cache;
    }

    /**
     * Evaluate the whole {@code plan}
     */
    public void evaluatePlan() throws GLanguageException {
        plan.evaluate(this);
    }

    /**
     * Evaluate a {@link RuleVersion} corresponding to an identifier, recursively or not
     * @param ruleIdentifier The identifier of the {@link RuleVersion} to evaluate
     * @param recursive Flag saying if evaluation has to be recursive or not
     */
    public void evaluateRuleVersion(String ruleIdentifier, boolean recursive) throws GLanguageException {
        plan.evaluate(ruleIdentifier, recursive, this);
    }

}
