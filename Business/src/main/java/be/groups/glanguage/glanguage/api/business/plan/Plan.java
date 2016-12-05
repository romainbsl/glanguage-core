package be.groups.glanguage.glanguage.api.business.plan;

import java.util.*;
import java.util.stream.Collectors;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.RuleCallFormula;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItem;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Plan {

    private final Logger LOG = LoggerFactory.getLogger(Plan.class);

    private List<RuleVersion> ruleVersions = new ArrayList<>();
    private boolean branched;
    private HashSet<RuleVersion> branchedRuleVersions;
    private HashSet<AbstractFormula> branchedFormulas;

    public Plan() {
        super();
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

    /**
     * @return the branched
     */
    public boolean isBranched() {
        return branched;
    }

    /**
     * @param ruleVersion
     * @return true if {@code ruleVersion} is branched - is contained in {@code branchedRuleVersions}, false otherwise
     */
    public boolean isBranched(RuleVersion ruleVersion) {
        return branchedRuleVersions != null && branchedRuleVersions.contains(ruleVersion);
    }

    /**
     * @param formula
     * @return true if {@code formula} is branched - is contained in {@code branchedFormulas}, false otherwise
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
     * Set the branched status of a rule version
     *
     * @param ruleVersion the rule version to set branched status
     * @param branched    branched status to set for rule version
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
     * Set the branched status of a formula
     *
     * @param formula  the formula to set branched status
     * @param branched branched status to set for formula
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

    public Collection<RuleVersion> evaluate(Object context) {
        if (!isBranched()) {
            branch(context);
        }
        getRuleVersions().forEach(rv -> rv.getValue());

        return getRuleVersions().stream().filter(rv -> rv.isCached()).collect(Collectors.toList());
    }

    public Collection<RuleVersion> evaluate(Object context, String ruleIdentifier, boolean recursive) {
        RuleVersion ruleVersion = getEffectiveRuleVersionByIdenitifier(ruleIdentifier);
        if (ruleVersion != null) {
            if (!isBranched(ruleVersion)) {
                branch(ruleVersion, context);
            }
            evaluate(ruleVersion, recursive);
        } else {
            throw new RuntimeException("Unable to evaluate the rule identified by " + ruleIdentifier + " because " +
                    "there is no rule corresponding to this identifier in this plan");
        }
        return getRuleVersions().stream().filter(rv -> rv.isEvaluated()).collect(Collectors.toList());
    }

    public void resetEvaluation() {
        getRuleVersions().stream().forEach(RuleVersion::resetValue);
    }

    private void evaluate(RuleVersion ruleVersion, boolean recursive) {
        ruleVersion.getValue();
        if (recursive && ruleVersion.getGroupItems() != null && !ruleVersion.getGroupItems().isEmpty()) {
            ruleVersion.getGroupItems().stream().map(i -> i.getReferencedRule()).forEach(rv -> evaluate(rv,
                    recursive));
        }
    }

    public void branch(Object context) {
        //getRuleVersions().stream().forEach(rv -> branch(rv, context));
//        Iterator<RuleVersion> it = getRuleVersions().iterator();
//        for (;it.hasNext();) {
//            branch(it.next(), context);
//        }
        for (int i = 0; i < getRuleVersions().size(); i++) {
            branch(getRuleVersions().get(i), context);
        }
        setBranched(true);
    }

    public void branch(RuleVersion ruleVersion, Object context) {
        if (!isBranched(ruleVersion)) {
            setBranched(ruleVersion, true);
            if (ruleVersion.getGroupItems() != null && !ruleVersion.getGroupItems().isEmpty()) {
                //ruleVersion.getGroupItems().stream().forEach(gi -> branch(ruleVersion, gi, context));
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

    public void branch(RuleVersion fromRuleVersion, RuleGroupItem ruleGroupItem, Object context) {
        RuleVersion rv = getEffectiveRuleVersionByIdenitifier(String.valueOf(ruleGroupItem.getItemRule().getId()));
        if (rv != null) {
            ruleGroupItem.setReferencedRule(rv);
            branch(rv, context);
        } else {
            throw new RuntimeException("There is no rule version in the plan corresponding to the rule identity id "
                    + "\"" + ruleGroupItem
                    .getItemRule().getId() + "\" in the group of rule \"" + fromRuleVersion.getCode() + "\"");
        }
    }

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
                        //formula.getParameters().stream().forEach(p -> branch(fromRuleVersion, p, context));
                    }
            }
        }
    }

    public void branch(RuleVersion fromRuleVersion, RuleCallFormula formula, Object context) {
        RuleVersion ruleVersion = getEffectiveRuleVersionByIdenitifier(formula.getConstantValue());
        if (ruleVersion != null) {
            formula.setReferencedRule(ruleVersion);
            branch(ruleVersion, context);
        } else {
            LOG.debug("Rule version not found");
            if (fromRuleVersion == null) {
                throw new RuntimeException("There is no rule version in the plan corresponding to the rule reference " +
                        "" + "\"" + formula
                        .getConstantValue() + "\"");
            } else {
                throw new RuntimeException("There is no rule version in the plan corresponding to the rule reference " +
                        "" + "\"" + formula
                        .getConstantValue() + "\" in the formula of rule \"" + fromRuleVersion.getCode() + "\"");
            }
        }
    }

    public void branch(FormulaGet formula, Object context) {
        formula.setContext(context);
    }

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

    public RuleVersion getEffectiveRuleVersionByRuleIdentityId(String ruleId) {
        return getRuleVersions().stream()
                .filter(rv -> String.valueOf(rv.getRuleDefinition().getRuleIdentity().getId()).equals(ruleId))
                .findFirst().orElse(null);
    }

    public RuleVersion getEffectiveRuleVersionByCode(String code) {
        return getRuleVersions().stream().filter(rv -> rv.getCode().equals(code)).findFirst().orElse(null);
    }

    public RuleVersion getEffectiveRuleVersionByAlias(String alias) {
        Optional<RuleVersion> ruleVersion = getRuleVersions().stream()
                .filter(rv -> rv.getRuleDescription() != null && rv.getRuleDescription().getAliasFr() != null && rv
                        .getRuleDescription().getAliasFr().equals(alias)).findFirst();
        if (ruleVersion.isPresent()) {
            return ruleVersion.get();
        }
        ruleVersion = getRuleVersions().stream()
                .filter(rv -> rv.getRuleDescription() != null && rv.getRuleDescription().getAliasNl() != null && rv
                        .getRuleDescription().getAliasNl().equals(alias)).findFirst();
        if (ruleVersion.isPresent()) {
            return ruleVersion.get();
        }
        ruleVersion = getRuleVersions().stream()
                .filter(rv -> rv.getRuleDescription() != null && rv.getRuleDescription().getAliasDe() != null && rv
                        .getRuleDescription().getAliasDe().equals(alias)).findFirst();
        if (ruleVersion.isPresent()) {
            return ruleVersion.get();
        }
        ruleVersion = getRuleVersions().stream()
                .filter(rv -> rv.getRuleDescription() != null && rv.getRuleDescription().getAliasX() != null && rv
                        .getRuleDescription().getAliasX().equals(alias)).findFirst();
        if (ruleVersion.isPresent()) {
            return ruleVersion.get();
        }
        return null;
    }

}
