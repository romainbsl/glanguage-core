package be.groups.glanguage.glanguage.api.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import be.groups.common.persistence.base.BaseDao;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet_;
import be.groups.marmota.persistence.JpaUtil;

public class RuleSetDao extends BaseDao<Integer, RuleSet> {
	
	public RuleSetDao() {
		super(RuleSet.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return JpaUtil.getCentralEntityManager();
	}
	
	/**
	 * Find a {@link RuleSet} by id
	 * 
	 * @param id
	 * @return The {@link RuleSet} identified by {@code id}
	 */
	public RuleSet findById(Integer id) {
		RuleSet ruleSet = new RuleSet();
		ruleSet.setId(id);
		return find(ruleSet);
	}
	
	/**
	 * Find a {@link RuleSet} by alias
	 * 
	 * @param alias
	 * @return The {@link RuleSet} identified by {@code alias}
	 */
	public RuleSet findByAlias(String alias) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<RuleSet> criteria = (CriteriaQuery<RuleSet>) builder.createQuery(RuleSet.class);
		Root<RuleSet> entityRoot = (Root<RuleSet>) criteria.from(RuleSet.class);
		criteria.select(entityRoot);
		
		criteria.where(builder.or(builder.equal(entityRoot.get(RuleSet_.aliasFr), alias),
				builder.equal(entityRoot.get(RuleSet_.aliasNl), alias), builder.equal(entityRoot.get(RuleSet_.aliasDe), alias),
				builder.equal(entityRoot.get(RuleSet_.aliasX), alias)));
		return getEntityManager().createQuery(criteria).getSingleResult();
	}
	
}
