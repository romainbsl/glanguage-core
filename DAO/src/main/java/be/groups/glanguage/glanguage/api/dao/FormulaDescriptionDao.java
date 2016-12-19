package be.groups.glanguage.glanguage.api.dao;

import be.groups.common.persistence.base.BaseDao;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.marmota.persistence.JpaUtil;

import javax.persistence.EntityManager;

public class FormulaDescriptionDao extends BaseDao<Integer, FormulaDescription> {

	public FormulaDescriptionDao() {
		super(FormulaDescription.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return JpaUtil.getCentralEntityManager();
	}
}
