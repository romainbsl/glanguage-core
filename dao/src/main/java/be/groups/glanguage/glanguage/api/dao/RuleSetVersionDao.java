package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion_;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet_;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.presta.backoffice.persistence.base.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

/**
 * DAO for {@link RuleSetVersion}
 * <p>
 * Created by michotte
 */
public class RuleSetVersionDao extends BaseDao<Integer, RuleSetVersion> {

    public RuleSetVersionDao() {
        super(RuleSetVersion.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return JpaUtil.getCentralEntityManager();
    }

    /**
     * Find a {@link RuleSetVersion} by {@code ruleSetVersionId}
     *
     * @param ruleSetVersionId the identifier of the {@link RuleSetVersion} to be returned
     * @return The {@link RuleSetVersion} identified by {@code ruleSetVersionId}, or null if it doesn't exists
     */
    public RuleSetVersion findById(Integer ruleSetVersionId) {
        RuleSetVersion ruleSetVersion = new RuleSetVersion();
        ruleSetVersion.setId(ruleSetVersionId);
        return find(ruleSetVersion);
    }

    /**
     * Find a {@link RuleSetVersion} by {@code ruleSetId} and {@code version}
     *
     * @param ruleSetId the identifier of the {@link RuleSet} of the {@link RuleSetVersion} to be returned
     * @param version   the version of the {@link RuleSetVersion} to be returned
     * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code ruleSetId} corresponding to the
     * {@code version}, or null if it doesn't exists
     */
    public RuleSetVersion findByRuleSetIdAndVersion(Integer ruleSetId, String version) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RuleSetVersion> criteria = (CriteriaQuery<RuleSetVersion>) builder
                .createQuery(RuleSetVersion.class);
        Root<RuleSetVersion> entityRoot = (Root<RuleSetVersion>) criteria.from(RuleSetVersion.class);
        criteria.select(entityRoot);
        // join with rule set
        Join<RuleSetVersion, RuleSet> ruleSet = entityRoot.join(RuleSetVersion_.ruleSet);

        criteria.where(builder.equal(ruleSet.get(RuleSet_.id), ruleSetId),
                       builder.equal(entityRoot.get(RuleSetVersion_.version), version));
        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    /**
     * Find a {@link RuleSetVersion} by {@code ruleSetAlias} and {@code version}
     *
     * @param ruleSetAlias the alias of the {@link RuleSet} of the {@link RuleSetVersion} to be returned
     * @param version      the version of the {@link RuleSetVersion} to be returned
     * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code ruleSetAlias}  orresponding to
     * the {@code version}, or null if it doesn't exists
     */
    public RuleSetVersion findByRuleSetAliasAndVersion(String ruleSetAlias, String version) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RuleSetVersion> criteria = (CriteriaQuery<RuleSetVersion>) builder
                .createQuery(RuleSetVersion.class);
        Root<RuleSetVersion> entityRoot = (Root<RuleSetVersion>) criteria.from(RuleSetVersion.class);
        criteria.select(entityRoot);
        // join with rule set
        Join<RuleSetVersion, RuleSet> ruleSet = entityRoot.join(RuleSetVersion_.ruleSet);

        criteria.where(builder.or(builder.equal(ruleSet.get(RuleSet_.aliasFr), ruleSetAlias),
                                  builder.equal(ruleSet.get(RuleSet_.aliasNl), ruleSetAlias),
                                  builder.equal(ruleSet.get(RuleSet_.aliasDe), ruleSetAlias),
                                  builder.equal(ruleSet.get(RuleSet_.aliasX), ruleSetAlias)),
                       builder.equal(entityRoot.get(RuleSetVersion_.version), version));
        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    /**
     * Find a {@link RuleSetVersion} by {@code rulSetId} and {@code productionDate}
     *
     * @param ruleSetId      the identifier of the {@link RuleSet} of the {@link RuleSetVersion} to be returned
     * @param productionDate the maximum {@link RuleSetVersion#productionStartDate} of the {@link RuleSetVersion} to be
     *                       returned
     * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code rulSetId} corresponding to the
     * {@code productionDate}
     */
    public RuleSetVersion findByRuleSetIdAndProductionDate(Integer ruleSetId, LocalDateTime productionDate) {
        // @formatter:off
		String query = "select rsv from RuleSetVersion rsv"
				+ " where rsv.ruleSet.id = :ruleSetId "
				+ " and rsv.productionStartDate = ("
				+ " select max(rsv2.productionStartDate) from RuleSetVersion rsv2"
				+ " where rsv2.ruleSet.id = rsv.ruleSet.id"
				+ " and rsv2.productionStartDate < :productionDate "
				+ ")";
		// @formatter:on
        return (RuleSetVersion) getEntityManager().createQuery(query).setParameter("ruleSetId", ruleSetId).setParameter(
                "productionDate",
                productionDate).getSingleResult();
    }

    /**
     * Find a {@link RuleSetVersion} by {@code ruleSetAlias} and {@code productionDate}
     *
     * @param ruleSetAlias   the alias of the {@link RuleSet} of the {@link RuleSetVersion} to be returned
     * @param productionDate the maximum {@link RuleSetVersion#productionStartDate} of the {@link RuleSetVersion} to be
     *                       returned
     * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code ruleSetAlias} corresponding to
     * the {@code productionDate}
     */
    public RuleSetVersion findByRuleSetAliasAndProductionDate(String ruleSetAlias, LocalDateTime productionDate) {
        // @formatter:off
		String query = "select rsv from RuleSetVersion rsv"
				+ " where (rsv.ruleSet.aliasFr = :ruleSetAlias "
				+ " or rsv.ruleSet.aliasNl = :ruleSetAlias "
				+ " or rsv.ruleSet.aliasDe = :ruleSetAlias "
				+ " or rsv.ruleSet.aliasX = :ruleSetAlias) "
				+ " and rsv.productionStartDate = ("
				+ " select max(rsv2.productionStartDate) from RuleSetVersion rsv2"
				+ " where rsv2.ruleSet.id = rsv.ruleSet.id"
				+ " and rsv2.productionStartDate < :productionDate "
				+ ")";
		// @formatter:on
        return (RuleSetVersion) getEntityManager().createQuery(query).setParameter("ruleSetAlias", ruleSetAlias)
                .setParameter("productionDate", productionDate).getSingleResult();
    }

}
