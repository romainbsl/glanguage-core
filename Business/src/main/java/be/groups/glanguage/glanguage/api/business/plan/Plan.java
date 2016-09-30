package be.groups.glanguage.glanguage.api.business.plan;

import java.util.ArrayList;
import java.util.List;

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
		
}
