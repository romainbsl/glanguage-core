package be.groups.glanguage.glanguage.api.business.factory;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import be.groups.glanguage.glanguage.api.dao.FormulaDescriptionDao;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

public class FormulaDescriptionFactory {
	
	private static Map<FormulaType, FormulaDescription> formulaDescriptionByType;
	
	public static FormulaDescription getDescription(FormulaType formulaType) {
		return getFormulaDescriptionByType().get(formulaType);
	}
	
	private static Map<FormulaType, FormulaDescription> getFormulaDescriptionByType() {
		if (formulaDescriptionByType == null) {
			formulaDescriptionByType = new FormulaDescriptionDao().findAll().stream()
					.collect(Collectors.toMap(FormulaDescription::getType, Function.identity()));
		}
		return formulaDescriptionByType;
	}
}
