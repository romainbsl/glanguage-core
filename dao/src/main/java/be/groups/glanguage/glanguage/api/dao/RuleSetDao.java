package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.presta.backoffice.persistence.base.BaseDao;

import javax.persistence.EntityManager;

/**
 * DAO for {@link RuleSet}
 * <p>
 *
 * @author michotte
 */
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
     * @param id the identifier of the {@link RuleSet} to be returned
     * @return The {@link RuleSet} identified by {@code id}, or null if it doesn't exists
     */
    public RuleSet findById(Integer id) {
        RuleSet ruleSet = new RuleSet();
        ruleSet.setId(id);
        return find(ruleSet);
    }

    /**
     * Find a {@link RuleSet} by alias
     *
     * @param alias the alias of the {@link RuleSet} to be returned
     * @return The {@link RuleSet} identified by {@code alias}, or null if it doesn't exists
     */
    public RuleSet findByAlias(String alias) {
        // @formatter:off
		String query = "select rs from RuleSet rs"
                    + " where exists ("
                        + " select msi from MultilingualString ms, MultilingualStringItem msi"
                        + " where ms.id = rs.alias.id and msi.multilingualString.id = ms.id"
                        + " and dbms_lob.compare(msi.text, :alias) = 0"
                    + ")";
        // @formatter:on
        return (RuleSet) getEntityManager().createQuery(query).setParameter("alias", alias).getSingleResult();
    }

}
