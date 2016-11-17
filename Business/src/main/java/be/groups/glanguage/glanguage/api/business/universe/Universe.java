package be.groups.glanguage.glanguage.api.business.universe;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.dao.FormulaDao;
import be.groups.glanguage.glanguage.api.dao.RuleSetDao;
import be.groups.glanguage.glanguage.api.dao.RuleSetVersionDao;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;

public class Universe {
	
	public static List<RuleSet> getAllRuleSets() {
		return new RuleSetDao().findAll();
	}
	
	public static RuleSet getRuleSet(Integer ruleSetId) {
		return new RuleSetDao().findById(ruleSetId);
	}
	
	public static RuleSet getRuleSet(String ruleSetAlias) {
		return new RuleSetDao().findByAlias(ruleSetAlias);
	}
	
	public static RuleSetVersion getRuleSetVersion(Integer ruleSetVersionId) {
		return new RuleSetVersionDao().findById(ruleSetVersionId);
	}
	
	public static RuleSetVersion getRuleSetVersion(Integer ruleSetId, String version) {
		return new RuleSetVersionDao().findByRuleSetIdAndVersion(ruleSetId, version);
	}
	
	public static RuleSetVersion getRuleSetVersion(String ruleSetAlias, String version) {
		return new RuleSetVersionDao().findByRuleSetAliasAndVersion(ruleSetAlias, version);
	}
	
	public static RuleSetVersion getRuleSetVersion(Integer ruleSetId, LocalDateTime exploitationDate) {
		return new RuleSetVersionDao().findByRuleSetIdAndExploitationDate(ruleSetId, exploitationDate);
	}
	
	public static RuleSetVersion getRuleSetVersion(String ruleSetAlias, LocalDateTime exploitationDate) {
		return new RuleSetVersionDao().findByRuleSetAliasAndExploitationDate(ruleSetAlias, exploitationDate);
	}
	
	public static Plan getPlan(Integer ruleSetVersionId, LocalDateTime effectivityDate) {
		return createPlan(getRuleSetVersion(ruleSetVersionId), null, effectivityDate);
	}
	
	public static Plan getPlan(Integer ruleSetVersionId, LocalDateTime effectivityDate,
			Collection<RuleDefinitionParameter> definitionParameters) {
		return createPlan(getRuleSetVersion(ruleSetVersionId), definitionParameters, effectivityDate);
	}
	
	public static Plan getPlan(Integer ruleSetId, String version, LocalDateTime effectivityDate) {
		return createPlan(getRuleSetVersion(ruleSetId, version), null, effectivityDate);
	}
	
	public static Plan getPlan(Integer ruleSetId, String version, LocalDateTime effectivityDate,
			Collection<RuleDefinitionParameter> definitionParameters) {
		return createPlan(getRuleSetVersion(ruleSetId, version), definitionParameters, effectivityDate);
	}
	
	public static Plan getPlan(String ruleSetAlias, String version, LocalDateTime effectivityDate) {
		return createPlan(getRuleSetVersion(ruleSetAlias, version), null, effectivityDate);
	}
	
	public static Plan getPlan(String ruleSetAlias, String version, LocalDateTime effectivityDate,
			Collection<RuleDefinitionParameter> definitionParameters) {
		return createPlan(getRuleSetVersion(ruleSetAlias, version), definitionParameters, effectivityDate);
	}
	
	public static Plan getPlan(Integer ruleSetId, LocalDateTime exploitationDate, LocalDateTime effectivityDate) {
		return createPlan(getRuleSetVersion(ruleSetId, exploitationDate), null, effectivityDate);
	}
	
	public static Plan getPlan(Integer ruleSetId, LocalDateTime exploitationDate, LocalDateTime effectivityDate,
			Collection<RuleDefinitionParameter> definitionParameters) {
		return createPlan(getRuleSetVersion(ruleSetId, exploitationDate), definitionParameters, effectivityDate);
	}
	
	public static Plan getPlan(String ruleSetAlias, LocalDateTime exploitationDate, LocalDateTime effectivityDate) {
		return createPlan(getRuleSetVersion(ruleSetAlias, exploitationDate), null, effectivityDate);
	}
	
	public static Plan getPlan(String ruleSetAlias, LocalDateTime exploitationDate, LocalDateTime effectivityDate,
			Collection<RuleDefinitionParameter> definitionParameters) {
		return createPlan(getRuleSetVersion(ruleSetAlias, exploitationDate), definitionParameters, effectivityDate);
	}
	
	private static Plan createPlan(RuleSetVersion ruleSetVersion, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDateTime effectivityDate) {
		Plan plan = new Plan();
		if (definitionParameters == null || definitionParameters.isEmpty()) {
			plan.setRuleVersions(ruleSetVersion.getDefaultRuleVersions(effectivityDate));
		} else {
			plan.setRuleVersions(ruleSetVersion.getBestDefinedRuleVersions(definitionParameters, effectivityDate));
		}
		return plan;
	}
	
	public static AbstractFormula getFormula(Integer id) {
		return new FormulaDao().findById(id);
	}
}
