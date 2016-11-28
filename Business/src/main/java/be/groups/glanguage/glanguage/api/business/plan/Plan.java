package be.groups.glanguage.glanguage.api.business.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.RuleCallFormula;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItem;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

public class Plan {
	
	private List<RuleVersion> ruleVersions = new ArrayList<>();
	private boolean isBranched;
	
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
	 * @return the isBranched
	 */
	public boolean isBranched() {
		return isBranched;
	}
	
	/**
	 * @param isBranched the isBranched to set
	 */
	private void setBranched(boolean isBranched) {
		this.isBranched = isBranched;
	}
	
	public Collection<RuleVersion> evaluate(Object context) {
		if (!isBranched()) {
			branch(context);
		}
		getRuleVersions().stream().forEach(rv -> rv.getValue());
		return getRuleVersions().stream().filter(rv -> rv.isCached()).collect(Collectors.toList());
	}
	
	public Collection<RuleVersion> evaluate(Object context, String ruleIdentifier, boolean recursive) {
		RuleVersion ruleVersion = getEffectiveRuleVersionByIdenitifier(ruleIdentifier);
		if (ruleVersion != null) {
			if (!isBranched()) {
				branch(ruleVersion, context);
			}
			evaluate(ruleVersion, recursive);
		} else {
			throw new RuntimeException("Unable to evaluate the rule identified by " + ruleIdentifier
					+ " because there is no rule corresponding to this identifier in this plan");
		}
		return getRuleVersions().stream().filter(rv -> rv.isEvaluated()).collect(Collectors.toList());
	}
	
	public void resetEvaluation() {
		getRuleVersions().stream().forEach(RuleVersion::resetValue);
	}
	
	private void evaluate(RuleVersion ruleVersion, boolean recursive) {
		ruleVersion.getValue();
		if (recursive && ruleVersion.getGroupItems() != null && !ruleVersion.getGroupItems().isEmpty()) {
			ruleVersion.getGroupItems().stream().map(i -> i.getReferencedRule()).forEach(rv -> evaluate(rv, recursive));
		}
	}
	
	public void branch(Object context) {
		getRuleVersions().stream().forEach(rv -> branch(rv, context));
		setBranched(true);
	}
	
	public void branch(RuleVersion rv, Object context) {
		if (rv.getGroupItems() != null && !rv.getGroupItems().isEmpty()) {
			rv.getGroupItems().stream().forEach(gi -> branch(rv, gi, context));
		}
		if (rv.getApplicabilityCondition() != null) {
			branch(rv, rv.getApplicabilityCondition(), context);
		}
		if (rv.getFormula() != null) {
			branch(rv, rv.getFormula(), context);
		}
	}
	
	public void branch(RuleVersion fromRuleVersion, RuleGroupItem gi, Object context) {
		RuleVersion rv = getEffectiveRuleVersionByIdenitifier(String.valueOf(gi.getItemRule().getId()));
		if (rv != null) {
			gi.setReferencedRule(rv);
			branch(rv, context);
		} else {
			throw new RuntimeException("There is no rule version in the plan corresponding to the rule identity id \""
					+ gi.getItemRule().getId() + "\" in the group of rule \"" + fromRuleVersion.getCode() + "\"");
		}
	}
	
	public void branch(RuleVersion fromRuleVersion, AbstractFormula formula, Object context) {
		switch (formula.getDescription().getType()) {
			case C_RULE_REFERENCE:
				branch(fromRuleVersion, (RuleCallFormula) formula, context);
				break;
			case C_GET:
				branch((FormulaGet) formula, context);
				break;
			default:
				if (formula.getParameters() != null && !formula.getParameters().isEmpty()) {
					formula.getParameters().stream().forEach(p -> branch(fromRuleVersion, p, context));
				}
		}
	}
	
	public void branch(RuleVersion fromRuleVersion, RuleCallFormula formula, Object context) {
		RuleVersion rv = getEffectiveRuleVersionByIdenitifier(formula.getConstantValue());
		if (rv != null) {
			formula.setReferencedRule(rv);
			branch(rv, context);
		} else {
			if (fromRuleVersion == null) {
				throw new RuntimeException("There is no rule version in the plan corresponding to the rule reference \""
						+ formula.getConstantValue() + "\"");
			} else {
				throw new RuntimeException("There is no rule version in the plan corresponding to the rule reference \""
						+ formula.getConstantValue() + "\" in the formula of rule \"" + fromRuleVersion.getCode() + "\"");
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
		return getRuleVersions().stream().filter(rv -> String.valueOf(rv.getRuleDefinition().getRuleIdentity().getId()).equals(ruleId))
				.findFirst().orElse(null);
	}
	
	public RuleVersion getEffectiveRuleVersionByCode(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getCode().equals(code)).findFirst().orElse(null);
	}
	
	public RuleVersion getEffectiveRuleVersionByAlias(String alias) {
		Optional<RuleVersion> ruleVersion = getRuleVersions().stream().filter(rv -> rv.getRuleDescription() != null
				&& rv.getRuleDescription().getAliasFr() != null && rv.getRuleDescription().getAliasFr().equals(alias)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		ruleVersion = getRuleVersions().stream().filter(rv -> rv.getRuleDescription() != null
				&& rv.getRuleDescription().getAliasNl() != null && rv.getRuleDescription().getAliasNl().equals(alias)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		ruleVersion = getRuleVersions().stream().filter(rv -> rv.getRuleDescription() != null
				&& rv.getRuleDescription().getAliasDe() != null && rv.getRuleDescription().getAliasDe().equals(alias)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		ruleVersion = getRuleVersions().stream().filter(rv -> rv.getRuleDescription() != null
				&& rv.getRuleDescription().getAliasX() != null && rv.getRuleDescription().getAliasX().equals(alias)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		return null;
	}
	
}
