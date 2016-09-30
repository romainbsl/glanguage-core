package be.groups.glanguage.glanguage.api.business.universe;

import java.time.LocalDateTime;
import java.util.List;

import be.groups.glanguage.glanguage.api.business.plan.Plan;
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
    	
    public RuleSetVersion getRuleSetVersion(Integer ruleSetId, LocalDateTime exploitationDate) {
    	return new RuleSetVersionDao().findByRuleSetIdAndExploitationDate(ruleSetId, exploitationDate);
    }
    
    public Plan getPlan(Integer ruleSetVersionId, LocalDateTime effectivityDate) {
    	return createPlan(getRuleSetVersion(ruleSetVersionId), effectivityDate);
    }
    
    public Plan getPlan(Integer ruleSetId, String version, LocalDateTime effectivityDate) {
    	return createPlan(getRuleSetVersion(ruleSetId, version), effectivityDate);
    }
    
    public Plan getPlan(Integer ruleSetId, LocalDateTime exploitationDate, LocalDateTime effectivityDate) {
    	return createPlan(getRuleSetVersion(ruleSetId, exploitationDate), effectivityDate);
    }

	private Plan createPlan(RuleSetVersion ruleSetVersion, LocalDateTime effectivityDate) {
		Plan plan = new Plan();
				
		return plan;
	}
		
}
