package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula_;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.presta.backoffice.persistence.base.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class FormulaDao extends BaseDao<Integer, AbstractFormula> {

	public FormulaDao() {
		super(AbstractFormula.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return JpaUtil.getCentralEntityManager();
	}

	public AbstractFormula findById(int id) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<AbstractFormula> criteria = (CriteriaQuery<AbstractFormula>) builder.createQuery(AbstractFormula.class);
		Root<AbstractFormula> entityRoot = (Root<AbstractFormula>) criteria.from(AbstractFormula.class);
		criteria.select(entityRoot);
		
		criteria.where(builder.equal(entityRoot.get(AbstractFormula_.id), id));
		return getEntityManager().createQuery(criteria).getSingleResult();		
	}

}
