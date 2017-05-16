package be.groups.glanguage.glanguage.api.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion_;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet_;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.presta.backoffice.persistence.base.BaseDao;

public class RuleSetVersionDao extends BaseDao<Integer, RuleSetVersion> {

	public RuleSetVersionDao() {
		super(RuleSetVersion.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return JpaUtil.getCentralEntityManager();
	}

	/**
	 * Find a {@link RuleSetVersion} by id
	 *
	 * @param ruleSetVersionId
	 * @return The {@link RuleSetVersion} identified by {@code id}
	 */
	public RuleSetVersion findById(Integer ruleSetVersionId) {
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setId(ruleSetVersionId);
		return find(ruleSetVersion);
	}

	/**
	 * Find a {@link RuleSetVersion} by rule set id and version
	 *
	 * @param ruleSetId
	 * @param version
	 * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code rulSetId} corresponding to the {@code version}
	 */
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

	/**
	 * Find a {@link RuleSetVersion} by rule set alias and version
	 *
	 * @param ruleSetAlias
	 * @param version
	 * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code ruleSetAlias} corresponding to the {@code version}
	 */
	public RuleSetVersion findByRuleSetAliasAndVersion(String ruleSetAlias, String version) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<RuleSetVersion> criteria = (CriteriaQuery<RuleSetVersion>) builder.createQuery(RuleSetVersion.class);
		Root<RuleSetVersion> entityRoot = (Root<RuleSetVersion>) criteria.from(RuleSetVersion.class);
		criteria.select(entityRoot);
		// join with rule set
		Join<RuleSetVersion, RuleSet> ruleSet = entityRoot.join(RuleSetVersion_.ruleSet);

		criteria.where(builder.or(builder.equal(ruleSet.get(RuleSet_.aliasFr), ruleSetAlias),
				builder.equal(ruleSet.get(RuleSet_.aliasNl), ruleSetAlias), builder.equal(ruleSet.get(RuleSet_.aliasDe), ruleSetAlias),
				builder.equal(ruleSet.get(RuleSet_.aliasX), ruleSetAlias)),
				builder.equal(entityRoot.get(RuleSetVersion_.version), version));
		return getEntityManager().createQuery(criteria).getSingleResult();
	}

	/**
	 * Find a {@link RuleSetVersion} by rule set id and exploitation date
	 *
	 * @param ruleSetId
	 * @param exploitationDate
	 * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code rulSetId} corresponding to the {@code exploitationDate}
	 */
	public RuleSetVersion findByRuleSetIdAndExploitationDate(Integer ruleSetId, LocalDateTime exploitationDate) {
		// @formatter:off
		String query = "select rsv from RuleSetVersion rsv"
				+ " where rsv.ruleSet.id = :ruleSetId "
				+ " and rsv.exploitationStartDate = ("
				+ " select max(rsv2.exploitationStartDate) from RuleSetVersion rsv2"
				+ " where rsv2.ruleSet.id = rsv.ruleSet.id"
				+ " and rsv2.exploitationStartDate < :exploitationDate "
				+ ")";
		// @formatter:on
		return (RuleSetVersion) getEntityManager()
				.createQuery(query)
				.setParameter("ruleSetId", ruleSetId)
				.setParameter("exploitationDate", exploitationDate)
				.getSingleResult();
	}

	/**
	 * Find a {@link RuleSetVersion} by rule set alias and exploitation date
	 *
	 * @param ruleSetAlias
	 * @param exploitationDate
	 * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code ruleSetAlias} corresponding to the {@code exploitationDate}
	 */
	public RuleSetVersion findByRuleSetAliasAndExploitationDate(String ruleSetAlias, LocalDateTime exploitationDate) {
		// @formatter:off
		String query = "select rsv from RuleSetVersion rsv"
				+ " where (rsv.ruleSet.aliasFr = :ruleSetAlias "
				+ " or rsv.ruleSet.aliasNl = :ruleSetAlias "
				+ " or rsv.ruleSet.aliasDe = :ruleSetAlias "
				+ " or rsv.ruleSet.aliasX = :ruleSetAlias) "
				+ " and rsv.exploitationStartDate = ("
				+ " select max(rsv2.exploitationStartDate) from RuleSetVersion rsv2"
				+ " where rsv2.ruleSet.id = rsv.ruleSet.id"
				+ " and rsv2.exploitationStartDate < :exploitationDate "
				+ ")";
		// @formatter:on
		return (RuleSetVersion) getEntityManager()
				.createQuery(query)
				.setParameter("ruleSetAlias", ruleSetAlias)
				.setParameter("exploitationDate", exploitationDate)
				.getSingleResult();
	}

}
