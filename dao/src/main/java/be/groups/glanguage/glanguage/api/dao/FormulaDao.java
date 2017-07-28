package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula_;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.presta.backoffice.persistence.base.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * DAO for {@link AbstractFormula}
 * <p>
 * @author michotte
 */
public class FormulaDao extends BaseDao<Integer, AbstractFormula> {

	public FormulaDao() {
		super(AbstractFormula.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return JpaUtil.getCentralEntityManager();
	}

	/**
	 * Find an {@link AbstractFormula} by {@code id}
	 *
	 * @param id the identifier of the {@link AbstractFormula} to be returned
	 * @return the {@link AbstractFormula} identified by the {@code id}, or null if it doesn't exists
	 */
	public AbstractFormula findById(int id) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<AbstractFormula> criteria = (CriteriaQuery<AbstractFormula>) builder.createQuery(AbstractFormula.class);
		Root<AbstractFormula> entityRoot = (Root<AbstractFormula>) criteria.from(AbstractFormula.class);
		criteria.select(entityRoot);
		
		criteria.where(builder.equal(entityRoot.get(AbstractFormula_.id), id));
		return getEntityManager().createQuery(criteria).getSingleResult();		
	}

}
