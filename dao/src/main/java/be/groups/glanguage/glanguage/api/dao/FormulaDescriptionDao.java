package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.presta.backoffice.persistence.base.BaseDao;

import javax.persistence.EntityManager;

/**
 * DAO for {@link FormulaDescription}
 * <p>
 * @author michotte
 */
public class FormulaDescriptionDao extends BaseDao<Integer, FormulaDescription> {

	public FormulaDescriptionDao() {
		super(FormulaDescription.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return JpaUtil.getCentralEntityManager();
	}
}
