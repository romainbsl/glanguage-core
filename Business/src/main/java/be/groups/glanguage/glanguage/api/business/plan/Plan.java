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
		if (!isBranched()) {
			branch(ruleVersion, context);
		}
		if (ruleVersion != null) {
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
			rv.getGroupItems().stream().forEach(gi -> branch(gi));
		}
		if (rv.getApplicabilityCondition() != null) {
			branch(rv.getApplicabilityCondition(), context);
		}
		if (rv.getFormula() != null) {
			branch(rv.getFormula(), context);
		}
	}
	
	public void branch(RuleGroupItem gi) {
		gi.setReferencedRule(getEffectiveRuleVersionByIdenitifier(String.valueOf(gi.getItemRule().getId())));
	}
	
	public void branch(AbstractFormula formula, Object context) {
		switch (formula.getDescription().getType()) {
			case C_RULE_REFERENCE:
				branch((RuleCallFormula) formula);
				break;
			case C_GET:
				branch((FormulaGet) formula, context);
				break;
			default:
				if (formula.getParameters() != null && !formula.getParameters().isEmpty()) {
					formula.getParameters().stream().forEach(p -> branch(p, context));
				}
		}
	}
	
	public void branch(RuleCallFormula formula) {
		formula.setReferencedRule(getEffectiveRuleVersionByIdenitifier(formula.getRuleId()));
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
		Optional<RuleVersion> ruleVersion =
				getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getAliasFr().equals(alias)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		ruleVersion = getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getAliasNl().equals(alias)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		ruleVersion = getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getAliasDe().equals(alias)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		ruleVersion = getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getAliasX().equals(alias)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		return null;
	}
	
}
