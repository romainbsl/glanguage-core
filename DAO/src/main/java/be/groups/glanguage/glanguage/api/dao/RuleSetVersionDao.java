package be.groups.glanguage.glanguage.api.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import be.groups.common.persistence.base.BaseDao;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion_;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet_;

public class RuleSetVersionDao extends BaseDao<Integer, RuleSetVersion> {
	
	public RuleSetVersionDao() {
		super(RuleSetVersion.class);
	}
	
	public RuleSetVersion findById(Integer ruleSetVersionId) {
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setId(ruleSetVersionId);
		return find(ruleSetVersion);
	}
	
	public RuleSetVersion findByRuleSetIdAndVersion(Integer ruleSetId, String version) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<RuleSetVersion> criteria = (CriteriaQuery<RuleSetVersion>) builder.createQuery(RuleSetVersion.class);
		Root<RuleSetVersion> entityRoot = (Root<RuleSetVersion>) criteria.from(RuleSetVersion.class);
		criteria.select(entityRoot);
		// join with rule set
		Join<RuleSetVersion, RuleSet> ruleSet = entityRoot.join(RuleSetVersion_.ruleSet);
		
		criteria.where(builder.equal(ruleSet.get(RuleSet_.id), ruleSetId),
				builder.equal(entityRoot.get(RuleSetVersion_.version), version));
		return getEntityManager().createQuery(criteria).getSingleResult();
	}
	
	public RuleSetVersion findByRuleSetIdAndExploitationDate(Integer ruleSetId, LocalDateTime exploitationDate) {
		// @formatter:off
		String query = "select rsv from RuleSetVersion rsv"
				+ " where rsv.ruleSet.id = " + ruleSetId
				+ " and rsv.exploitationStartDate = ("
				+ " select max(rsv2.exploitationStartDate) from RuleSetVersion rsv2"
				+ " where rsv2.ruleSet.id = rsv.ruleSet.id"
				+ " and rsv2.exploitationStartDate < '" + exploitationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
				+ "'"
				+ ")";
		// @formatter:on
		return (RuleSetVersion) getEntityManager().createQuery(query).getSingleResult();
	}
	
}
