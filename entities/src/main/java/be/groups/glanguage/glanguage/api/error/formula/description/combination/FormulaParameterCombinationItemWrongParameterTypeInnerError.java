package be.groups.glanguage.glanguage.api.error.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

import java.util.Iterator;

/**
 * @author michotte
 */
public class FormulaParameterCombinationItemWrongParameterTypeInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterCombinationItemWrongParameterTypeInnerError(AbstractFormula formula,
                                                                       FormulaUsage usage,
                                                                       FormulaParameterCombinationItem
                                                                               combinationParameter,
                                                                       String parameterAsText,
                                                                       FormulaReturnType actualType,
                                                                       Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_COMBINATION_ITEM_WRONG_TYPE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(usage, combinationParameter, parameterAsText, actualType));
    }

    private static String getCause(FormulaUsage usage,
                                   FormulaParameterCombinationItem combinationParameter,
                                   String parameterAsText,
                                   FormulaReturnType actualType) {
        StringBuilder sb = new StringBuilder("Parameter " + usage.getParameterName(combinationParameter)
                .asText(Language.EN) + " at index [" + combinationParameter
                .getSequenceNumber() + "] ('" + parameterAsText + "')");
        sb.append(" has a wrong type : " + " actual type = " + (actualType == null ? "[null]" : actualType.name()));
        if (combinationParameter.getTypes() != null) {
            Iterator<FormulaReturnType> itExpectedTypes = combinationParameter.getReturnTypes().iterator();
            if (itExpectedTypes.hasNext()) {
                sb.append(" , expected type(s) = {'" + itExpectedTypes.next().name());
                while (itExpectedTypes.hasNext()) {
                    sb.append("', '" + itExpectedTypes.next().name());
                }
                sb.append("'}");
            }
        }
        return sb.toString();
    }
}
