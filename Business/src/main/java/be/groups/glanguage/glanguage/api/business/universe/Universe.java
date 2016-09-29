package be.groups.glanguage.glanguage.api.business.universe;

import java.util.List;

import be.groups.glanguage.glanguage.api.dao.RuleSetDao;
import be.groups.glanguage.glanguage.api.dao.RuleSetVersionDao;
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
		
}
