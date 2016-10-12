package be.groups.glanguage.glanguage.api.business.universe;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.dao.RuleSetDao;
import be.groups.glanguage.glanguage.api.dao.RuleSetVersionDao;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;

public class Universe {
	
	public List<RuleSet> getAllRuleSets() {
		return new RuleSetDao().findAll();
	}
	
	public RuleSet getRuleSet(Integer ruleSetId) {
		return new RuleSetDao().findById(ruleSetId);
	}
	
	public RuleSetVersion getRuleSetVersion(Integer ruleSetVersionId) {
		return new RuleSetVersionDao().findById(ruleSetVersionId);
	}
	
	public RuleSetVersion getRuleSetVersion(Integer ruleSetId, String version) {
		return new RuleSetVersionDao().findByRuleSetIdAndVersion(ruleSetId, version);
	}
	
	public RuleSetVersion getRuleSetVersion(Integer ruleSetId, LocalDateTime exploitationDate) {
		return new RuleSetVersionDao().findByRuleSetIdAndExploitationDate(ruleSetId, exploitationDate);
	}
	
	public Plan getPlan(Integer ruleSetVersionId, LocalDateTime effectivityDate,
			Collection<RuleDefinitionParameter> definitionParameters, boolean branch) {
		return createPlan(getRuleSetVersion(ruleSetVersionId), definitionParameters, effectivityDate, branch);
	}
	
	public Plan getPlan(Integer ruleSetId, String version, LocalDateTime effectivityDate,
			Collection<RuleDefinitionParameter> definitionParameters, boolean branch) {
		return createPlan(getRuleSetVersion(ruleSetId, version), definitionParameters, effectivityDate, branch);
	}
	
	public Plan getPlan(Integer ruleSetId, LocalDateTime exploitationDate, LocalDateTime effectivityDate,
			Collection<RuleDefinitionParameter> definitionParameters, boolean branch) {
		return createPlan(getRuleSetVersion(ruleSetId, exploitationDate), definitionParameters, effectivityDate, branch);
	}
	
	private Plan createPlan(RuleSetVersion ruleSetVersion, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDateTime effectivityDate, boolean branch) {
		Plan plan = new Plan();
		if (definitionParameters == null || definitionParameters.isEmpty()) {
			plan.setRuleVersions(ruleSetVersion.getDefaultRuleVersions(effectivityDate));
		} else {
			plan.setRuleVersions(ruleSetVersion.getBestDefinedRuleVersions(definitionParameters, effectivityDate));
		}
		if (branch) {
			plan.branch();
		}
		return plan;
	}
	
}
