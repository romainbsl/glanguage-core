package be.groups.glanguage.glanguage.api.dao;

import be.groups.common.persistence.base.BaseDao;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;

public class RuleSetDao extends BaseDao<Integer, RuleSet> {

	public RuleSetDao() {
		super(RuleSet.class);
	}

	public RuleSet findById(Integer id) {
		RuleSet ruleSet = new RuleSet();
		ruleSet.setId(id);
		return find(ruleSet);	
	}

}
