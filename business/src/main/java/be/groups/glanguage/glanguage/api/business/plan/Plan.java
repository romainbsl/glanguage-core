package be.groups.glanguage.glanguage.api.business.plan;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.RuleCallFormula;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDescription;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItem;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualStringItem;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Plan {

    private final Logger LOG = LoggerFactory.getLogger(Plan.class);

    private List<RuleVersion> ruleVersions = new ArrayList<>();
    private boolean branched;
    private HashSet<RuleVersion> branchedRuleVersions;
    private HashSet<AbstractFormula> branchedFormulas;

    private Integer currentCache;
    private List<HashMap<RuleVersion, Object>> caches;

    public Plan() {
        super();
        initCaching();
        initBranching();
        initEvaluation();
    }

    /**
     * @return the ruleVersions
     */
    public List<RuleVersion> getRuleVersions() {
        return ruleVersions;
    }

    /**
     * @param ruleVersions the ruleVersions to set
     */
    public void setRuleVersions(List<RuleVersion> ruleVersions) {
        this.ruleVersions = ruleVersions;
    }

    //region Caching

    /**
     * Initialize the cache
     * Removes all caches and create a new current one
     */
    public void initCaching() {
        caches = new ArrayList<>();
        addCurrentCache();
    }

    /**
     * Get the cache corresponding to {@code cacheId}
     *
     * @param cacheId The id of the cache to get
     * @return The cache corresponding to {@code cacheId} if it exists, null otherwise
     */
    public HashMap<RuleVersion, Object> getCache(Integer cacheId) {
        return caches.get(cacheId);
    }

    /**
     * Add a new cache and return its id
     *
     * @return The id of the new cache to use to retrieve it via {@link Plan#getCache(Integer)} method
     */
    public Integer addCache() {
        caches.add(new HashMap<>());
        return caches.size() - 1;
    }

    /**
     * Get the current cache corresponding to {@code currentCache} id
     * The current cache is the one currently in use by evaluation process
     *
     * @return The current cache corresponding to {@code currentCache} id if it exists, null otherwise
     */
    public HashMap<RuleVersion, Object> getCurrentCache() {
        return getCache(currentCache);
    }

    /**
     * Add a new cache, make it current, and return its id
     * Just after a call to this method, a call to {@link Plan#getCurrentCache()} will return the new cache created
     *
     * @return The new cache created
     */

    public HashMap<RuleVersion, Object> addCurrentCache() {
        currentCache = addCache();
        return getCurrentCache();
    }

    /**
     * Set the current cache by setting the {@code currentCache} id
     * Just after a call to this method, a call to {@link Plan#getCurrentCache()} will return the cache corresponding
     * to {@code cacheId}
     *
     * @param cacheId The {@code currentCache} id to set
     */
    public void setCurrentCache(Integer cacheId) {
        currentCache = cacheId;
    }

    /**
     * Is a {@link RuleVersion} cached - Is there a value calculated for a {@link RuleVersion} ?
     *
     * @param ruleVersion The {@link RuleVersion} to check if it is cached
     * @return true if the {@link RuleVersion} {@code ruleVersion} is cached, false otherwise
     */
    public boolean isCached(RuleVersion ruleVersion) {
        return getCurrentCache().containsKey(ruleVersion);
    }

    //endregion

    //region Branching

    /**
     * Initialize branching
     * Resets branching status and lists
     */
    public void initBranching() {
        setBranched(false);
        branchedFormulas = null;
        branchedRuleVersions = null;
    }

    /**
     * Is this branched
     *
     * @return the branched
     */
    public boolean isBranched() {
        return branched;
    }

    /**
     * Is a {@link RuleVersion} branched
     *
     * @param ruleVersion The {@link RuleVersion} to check if it is branched
     * @return true if {@code ruleVersion} is branched, false otherwise
     */
    public boolean isBranched(RuleVersion ruleVersion) {
        return branchedRuleVersions != null && branchedRuleVersions.contains(ruleVersion);
    }

    /**
     * Is an {@link AbstractFormula} branched
     *
     * @param formula The {@link AbstractFormula} to check if it is branched
     * @return true if {@code formula} is branched, false otherwise
     */
    public boolean isBranched(AbstractFormula formula) {
        return branchedFormulas != null && branchedFormulas.contains(formula);
    }

    /**
     * @param branched the branched to set
     */
    private void setBranched(boolean branched) {
        this.branched = branched;
    }

    /**
     * Set the branching status of a {@link RuleVersion}
     *
     * @param ruleVersion the {@link RuleVersion} to set branching status
     * @param branched    branching status to set for the {@code ruleVersion}
     */
    private void setBranched(RuleVersion ruleVersion, boolean branched) {
        if (branchedRuleVersions == null) {
            branchedRuleVersions = new HashSet<>();
        }
        if (branched) {
            branchedRuleVersions.add(ruleVersion);
        } else {
            branchedRuleVersions.remove(ruleVersion);
        }
    }

    /**
     * Set the branching status of an {@link AbstractFormula}
     *
     * @param formula  the {@link AbstractFormula} to set branching status
     * @param branched branching status to set for {@code formula}
     */
    private void setBranched(AbstractFormula formula, boolean branched) {
        if (branchedFormulas == null) {
            branchedFormulas = new HashSet<>();
        }
        if (branched) {
            branchedFormulas.add(formula);
        } else {
            branchedFormulas.remove(formula);
        }
    }

    /**
     * Branch with a context
     *
     * @param context The context to branch
     */
    public void branch(Object context) {
//        getRuleVersions().stream().forEach(rv -> branch(rv, context));
//        Iterator<RuleVersion> it = getRuleVersions().iterator();
//        for (;it.hasNext();) {
//            branch(it.next(), context);
//        }
        for (int i = 0; i < getRuleVersions().size(); i++) {
            branch(getRuleVersions().get(i), context);
        }
        setBranched(true);
    }

    /**
     * Branch a {@link RuleVersion} with a context
     *
     * @param ruleVersion The {@link RuleVersion} to branch
     * @param context     The context to branch
     */
    public void branch(RuleVersion ruleVersion, Object context) {
        if (!isBranched(ruleVersion)) {
            setBranched(ruleVersion, true);
            if (ruleVersion.getGroupItems() != null && !ruleVersion.getGroupItems().isEmpty()) {
//                ruleVersion.getGroupItems().stream().forEach(gi -> branch(ruleVersion, gi, context));
                Iterator<RuleGroupItem> it = ruleVersion.getGroupItems().iterator();
                while (it.hasNext()) {
                    branch(ruleVersion, it.next(), context);
                }
            }
            if (ruleVersion.getApplicabilityCondition() != null) {
                branch(ruleVersion, ruleVersion.getApplicabilityCondition(), context);
            }
            if (ruleVersion.getFormula() != null) {
                branch(ruleVersion, ruleVersion.getFormula(), context);
            }
        }
    }

    /**
     * Branch a {@link RuleGroupItem} with a context
     *
     * @param fromRuleVersion The {@link RuleVersion} owning the {@code ruleGroupItem}
     * @param ruleGroupItem   The {@link RuleGroupItem} to branch
     * @param context         The context to branch
     */
    public void branch(RuleVersion fromRuleVersion, RuleGroupItem ruleGroupItem, Object context) {
        RuleVersion rv = getEffectiveRuleVersionByIdenitifier(String.valueOf(ruleGroupItem.getItemRule().getId()));
        if (rv != null) {
            ruleGroupItem.setReferencedRule(rv);
            branch(rv, context);
        } else {
            throw new RuntimeException("There is no rule version in the plan corresponding to the rule identity id "
                                               + "\"" + ruleGroupItem
                    .getItemRule().getId() + "\" in the group of " + "rule \"" + fromRuleVersion.getCode() + "\"");
        }
    }

    /**
     * Branch an {@link AbstractFormula} with a context
     *
     * @param fromRuleVersion The {@link RuleVersion} owning the {@code formula}
     * @param formula         The {@link AbstractFormula} to branch
     * @param context         The context to branch
     */
    public void branch(RuleVersion fromRuleVersion, AbstractFormula formula, Object context) {
        if (!isBranched(formula)) {
            setBranched(formula, true);
            switch (formula.getDescription().getType()) {
                case C_RULE_REFERENCE:
                case C_APPLICABILITY:
                case C_FORMULA:
                    branch(fromRuleVersion, (RuleCallFormula) formula, context);
                    break;
                case C_GET:
                    branch((FormulaGet) formula, context);
                default:
                    if (formula.getParameters() != null && !formula.getParameters().isEmpty()) {
                        for (int i = 0; i < formula.getParameters().size(); i++) {
                            branch(fromRuleVersion, formula.getParameters().get(i), context);
                        }
                        //formula.getCombinationParameters().stream().forEach(p -> branch(fromRuleVersion, p, context));
                    }
            }
        }
    }

    /**
     * Branch an {@link RuleCallFormula} with a context
     *
     * @param fromRuleVersion The {@link RuleVersion} owning the {@code formula}
     * @param formula         The {@link RuleCallFormula} to branch
     * @param context         The context to branch
     */
    public void branch(RuleVersion fromRuleVersion, RuleCallFormula formula, Object context) {
        RuleVersion ruleVersion = getEffectiveRuleVersionByIdenitifier(formula.getConstantValue());
        if (ruleVersion != null) {
            formula.setReferencedRule(ruleVersion);
            branch(ruleVersion, context);
        } else {
            LOG.debug("Rule version not found");
            if (fromRuleVersion == null) {
                throw new RuntimeException("There is no rule version in the plan corresponding to the rule reference "
                                                   + "\"" + formula.getConstantValue() + "\"");
            } else {
                throw new RuntimeException("There is no rule version in the plan corresponding to the rule reference "
                                                   + "\"" + formula.getConstantValue() + "\" in the formula of rule \""
                                                   + fromRuleVersion.getCode() + "\"");
            }
        }
    }

    /**
     * Branch an {@link FormulaGet} with a context
     *
     * @param formula The {@link FormulaGet} to branch
     * @param context The context to branch
     */
    public void branch(FormulaGet formula, Object context) {
        formula.setContext(context);
    }

    //endregion

    //region Evaluation

    /**
     * Initialize evaluation
     * Resets the values of all {@link RuleVersion}'s in {@code ruleVersions}
     */
    public void initEvaluation() {
        if (getRuleVersions() != null) {
            getRuleVersions().stream().forEach(RuleVersion::resetValue);
        }
    }

    /**
     * Evaluate the whole plan without context nor evaluator and return the result of evaluation
     *
     * @return The map of evaluated {@link RuleVersion}'s associated with their value as result of evaluation
     */
    public Map<RuleVersion, Object> evaluate() throws GLanguageException {
        return evaluateWithContext(null);
    }

    /**
     * Evaluate the whole plan with an {@link Evaluator} and return the result of evaluation
     *
     * @param evaluator The evaluator to use to evaluate
     * @return The map of evaluated {@link RuleVersion}'s associated with their value as result of evaluation
     */
    public Map<RuleVersion, Object> evaluate(Evaluator evaluator) throws GLanguageException {
//        getRuleVersions().forEach(rv -> evaluate(rv, false, evaluator));
        for (RuleVersion rv : getRuleVersions()) {
            evaluate(rv, false, evaluator);
        }
        return getCurrentCache();
    }

    /**
     * Evaluate a {@link RuleVersion}, recursively or not, with an evaluator
     *
     * @param ruleIdentifier The identifier of a {@link RuleVersion} to evaluate
     * @param recursive      Flag indicating whether to evaluate recursively or not
     * @param evaluator      The evaluator to use to evaluate
     */
    public void evaluate(String ruleIdentifier, boolean recursive, Evaluator evaluator) throws GLanguageException {
        RuleVersion ruleVersion = getEffectiveRuleVersionByIdenitifier(ruleIdentifier);
        if (ruleVersion != null) {
            evaluate(ruleVersion, recursive, evaluator);
        } else {
            throw new RuntimeException("Unable to evaluateWithContext the rule identified by " + ruleIdentifier + " "
                                               + "because there is no rule corresponding to this identifier in this "
                                               + "plan");
        }
    }

    /**
     * Evaluate the whole plan with a context and return the result of evaluation
     *
     * @param context The contxt to evaluate
     * @return The map of evaluated {@link RuleVersion}'s associated with their value as result of evaluation
     */
    public Map<RuleVersion, Object> evaluateWithContext(Object context) throws GLanguageException {
        if (!isBranched()) {
            branch(context);
        }
//        getRuleVersions().forEach(rv -> evaluate(rv, false, null));
        for (RuleVersion rv : getRuleVersions()) {
            evaluate(rv, false, null);
        }
        return getCurrentCache();
    }

    /**
     * Evaluate a {@link RuleVersion}, recursively or not, with a context
     *
     * @param context        The context to evaluate
     * @param ruleIdentifier The identifier of the {@link RuleVersion} to evaluate
     * @param recursive      Flag indicating whether to evaluate recursively or not
     */
    public Map<RuleVersion, Object> evaluateWithContext(Object context,
                                                        String ruleIdentifier,
                                                        boolean recursive) throws GLanguageException {
        RuleVersion ruleVersion = getEffectiveRuleVersionByIdenitifier(ruleIdentifier);
        if (ruleVersion != null) {
            if (!isBranched(ruleVersion)) {
                branch(ruleVersion, context);
            }
            evaluate(ruleVersion, recursive, null);
        } else {
            throw new RuntimeException("Unable to evaluateWithContext the rule identified by " + ruleIdentifier + " "
                                               + "because there is no rule corresponding to this identifier in this "
                                               + "plan");
        }

//        return getRuleVersions().stream().filter(RuleVersion::isEvaluated).collect(Collectors.toMap(r -> r, r -> r
// .getValue()));
        Map<RuleVersion, Object> result = new HashMap<>();
        for (RuleVersion rv : getRuleVersions()) {
            if (rv.isEvaluated()) {
                result.put(rv, rv.getValue());
            }
        }
        return result;
    }

    /**
     * Evaluate a {@link RuleVersion}, recursively or not, with an {@link Evaluator}
     * Evaluating a {@link RuleVersion} recursively means evaluating that {@link RuleVersion} and all
     * {@link RuleVersion}'s referenced as a sub-rule of that {@link RuleVersion}
     * Evaluating a {@link RuleVersion} non-recursively means evaluating that {@link RuleVersion} only
     *
     * @param ruleVersion The {@link RuleVersion} to evaluate
     * @param recursive   Flag indicating whether to evaluate recursively or not
     * @param evaluator   The evaluator to use to evaluate. May be null
     */
    private void evaluate(RuleVersion ruleVersion, boolean recursive, Evaluator evaluator) throws GLanguageException {
        if (evaluator != null) {
            evaluator.evaluateRuleVersion(ruleVersion);
        } else {
            if (!isCached(ruleVersion)) {
                Object value = ruleVersion.getValue(evaluator);
                getCurrentCache().put(ruleVersion, value);
            }
        }
        if (recursive && ruleVersion.isApplicable(evaluator) && ruleVersion.getGroupItems() != null && !ruleVersion
                .getGroupItems().isEmpty()) {
//            ruleVersion.getGroupItems().stream().map(i -> i.getReferencedRule(evaluator))
//                    .forEach(rv -> evaluate(rv, recursive, evaluator));
            for (RuleGroupItem i : ruleVersion.getGroupItems()) {
                evaluate(i.getReferencedRule(evaluator), recursive, evaluator);
            }
        }
    }

    //endregion

    //region Getting RuleVersion

    /**
     * Get the {@link RuleVersion} corresponding to a {@code ruleIdentifier}
     * <ol>
     * <li>First try to find it using {@link Plan#getEffectiveRuleVersionByRuleIdentityId(String)}</li>
     * <li>If not found, try to find it using {@link Plan#getEffectiveRuleVersionByCode(String)}</li>
     * <li>If not found, try to find it  using {@link Plan#getEffectiveRuleVersionByAlias(String)}</li>
     * </ol>
     *
     * @param ruleIdentifier the identifier of the {@link RuleVersion} to return
     * @return the {@link RuleVersion} corresponding to {@code ruleIdentifier}, or null if it doesn't exists
     */
    public RuleVersion getEffectiveRuleVersionByIdenitifier(String ruleIdentifier) {
        RuleVersion ruleVersion = getEffectiveRuleVersionByRuleIdentityId(ruleIdentifier);
        if (ruleVersion == null) {
            ruleVersion = getEffectiveRuleVersionByCode(ruleIdentifier);
        }
        if (ruleVersion == null) {
            ruleVersion = getEffectiveRuleVersionByAlias(ruleIdentifier);
        }
        return ruleVersion;
    }

    /**
     * Get the {@link RuleVersion} corresponding to a {@code ruleId} by matching the
     * {@link be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity#id}
     *
     * @param ruleId the {@link be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity#id} of the
     *               {@link RuleVersion} to be returned
     * @return the {@link RuleVersion} with the
     * {@link be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity#id} corresponding to {@code ruleIdentifier},
     * or null if it doesn't exists
     */
    public RuleVersion getEffectiveRuleVersionByRuleIdentityId(String ruleId) {
        return getRuleVersions().stream().filter(rv -> String.valueOf(rv.getRuleDefinition().getRuleIdentity().getId())
                .equals(ruleId)).findFirst().orElse(null);
    }

    /**
     * Get the {@link RuleVersion} corresponding to a {@code code} by matching the
     * {@link be.groups.glanguage.glanguage.api.entities.rule.RuleDescription#code}
     *
     * @param code the {@link be.groups.glanguage.glanguage.api.entities.rule.RuleDescription#code} of the
     *             {@link RuleVersion} to be returned
     * @return the {@link RuleVersion} with the
     * {@link be.groups.glanguage.glanguage.api.entities.rule.RuleDescription#code} corresponding to {@code code},
     * or null if it doesn't exists
     */
    public RuleVersion getEffectiveRuleVersionByCode(String code) {
        return getRuleVersions().stream().filter(rv -> rv.getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * Get the {@link RuleVersion} corresponding to an {@code alias} by matching the one of the
     * {@link MultilingualStringItem#getText()} of the {@link MultilingualString} representing the
     * {@link RuleDescription#getAlias()} of its {@link RuleVersion#getRuleDescription()}
     *
     * @param alias one of the
     *              {@link MultilingualStringItem#getText()} of the {@link MultilingualString} representing the
     *              {@link RuleDescription#getAlias()} of the {@link RuleVersion#getRuleDescription()} of the
     *              {@link RuleVersion} to be returned
     * @return the {@link RuleVersion} with one of the
     * {@link MultilingualStringItem#getText()} of the {@link MultilingualString} representing the
     * {@link RuleDescription#getAlias()} of its {@link RuleVersion#getRuleDescription()} corresponding to {@code
     * alias}, or null if it doesn't exists
     */
    public RuleVersion getEffectiveRuleVersionByAlias(String alias) {
        return getRuleVersions().stream().filter(rv -> rv.getRuleDescription() != null && rv.getRuleDescription()
                .getAlias() != null && rv.getRuleDescription().getAlias().getItems().stream()
                .anyMatch(i -> i.getText().equals(alias))).findFirst().orElse(null);
    }

    //endregion
}
