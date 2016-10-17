package be.groups.glanguage.glanguage.api.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import be.groups.common.persistence.base.BaseDao;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula_;

public class FormulaDao extends BaseDao<Integer, AbstractFormula> {

	public FormulaDao() {
		super(AbstractFormula.class);
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
