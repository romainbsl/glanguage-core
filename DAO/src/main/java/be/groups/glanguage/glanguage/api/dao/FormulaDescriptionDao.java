package be.groups.glanguage.glanguage.api.dao;

import be.groups.common.persistence.base.BaseDao;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;

public class FormulaDescriptionDao extends BaseDao<Integer, FormulaDescription> {

	public FormulaDescriptionDao() {
		super(FormulaDescription.class);
	}

}
