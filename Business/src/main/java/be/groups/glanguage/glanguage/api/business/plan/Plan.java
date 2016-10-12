package be.groups.glanguage.glanguage.api.business.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaApplicability;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaFormula;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaRuleReference;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.RuleCallFormula;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItem;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

public class Plan {
	
	private List<RuleVersion> ruleVersions = new ArrayList<>();
	
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
	
	public Collection<RuleVersion> evaluate() {
		getRuleVersions().stream().forEach(rv -> rv.getValue());
		return getRuleVersions().stream().filter(rv -> rv.isCached()).collect(Collectors.toList());
	}
	
	public Collection<RuleVersion> evaluate(String ruleIdentifier, boolean recursive) {
		RuleVersion ruleVersion = getEffectiveRuleVersionByIdenitifier(ruleIdentifier);
		if (ruleVersion != null) {
			evaluate(ruleVersion, recursive);
		} else {
			throw new RuntimeException("Unable to evaluate the rule identified by " + ruleIdentifier
					+ " because there is no rule corresponding to this identifier in this plan");
		}
		return getRuleVersions().stream().filter(rv -> rv.isCached()).collect(Collectors.toList());
	}
	
	private void evaluate(RuleVersion ruleVersion, boolean recursive) {
		ruleVersion.getValue();
		if (recursive && ruleVersion.getGroupItems() != null && !ruleVersion.getGroupItems().isEmpty()) {
			ruleVersion.getGroupItems().stream().map(i -> i.getReferencedRule()).forEach(rv -> evaluate(rv, recursive));
		}
	}
	
	public void branch() {
		getRuleVersions().stream().forEach(rv -> branch(rv));
	}
	
	private void branch(RuleVersion rv) {
		if (rv.getGroupItems() != null && !rv.getGroupItems().isEmpty()) {
			rv.getGroupItems().stream().forEach(gi -> branch(gi));
		}
		if (rv.getApplicabilityCondition() != null) {
			branch(rv.getApplicabilityCondition());
		}
		if (rv.getFormula() != null) {
			branch(rv.getFormula());
		}
	}
	
	private void branch(RuleGroupItem gi) {
		gi.setReferencedRule(getEffectiveRuleVersionByRuleIdentityId(String.valueOf(gi.getItemRule().getId())));
	}
	
	private void branch(AbstractFormula formula) {
		if (formula instanceof FormulaRuleReference || formula instanceof FormulaApplicability || formula instanceof FormulaFormula) {
			branch((RuleCallFormula) formula);
		} else {
			formula.getParameters().stream().forEach(p -> branch(p));
		}
	}
	
	private void branch(RuleCallFormula formula) {
		formula.setReferencedRule(getEffectiveRuleVersionByRuleIdentityId(formula.getRuleId()));
	}
	
	private RuleVersion getEffectiveRuleVersionByIdenitifier(String ruleIdentifier) {
		RuleVersion ruleVersion = getEffectiveRuleVersionByRuleIdentityId(ruleIdentifier);
		if (ruleVersion == null) {
			ruleVersion = getEffectiveRuleVersionByCode(ruleIdentifier);
		}
		if (ruleVersion == null) {
			ruleVersion = getEffectiveRuleVersionByAlias(ruleIdentifier);
		}
		return ruleVersion;
	}
	
	private RuleVersion getEffectiveRuleVersionByRuleIdentityId(String ruleId) {
		return getRuleVersions().stream().filter(rv -> String.valueOf(rv.getRuleDefinition().getRuleIdentity().getId()).equals(ruleId))
				.findFirst().orElse(null);
	}
	
	private RuleVersion getEffectiveRuleVersionByCode(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getCode().equals(code)).findFirst().orElse(null);
	}
	
	private RuleVersion getEffectiveRuleVersionByAlias(String alias) {
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
