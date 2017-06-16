package be.groups.glanguage.glanguage.api.error.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItemValue;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

import java.util.Iterator;

/**
 * Created by michotte on 11/05/2017.
 */
public class FormulaParameterCombinationItemWrongParameterValueInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterCombinationItemWrongParameterValueInnerError(AbstractFormula formula,
                                                                        FormulaUsage usage,
                                                                        FormulaParameterCombinationItem
                                                                                combinationParameter,
                                                                        String parameterAsText,
                                                                        Object actualValue,
                                                                        Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_COMBINATION_ITEM_WRONG_VALUE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(usage, combinationParameter, parameterAsText, actualValue));
    }

    private static String getCause(FormulaUsage usage,
                                   FormulaParameterCombinationItem combinationParameter,
                                   String parameterAsText,
                                   Object actualValue) {
        StringBuilder sb = new StringBuilder("Parameter " + usage.getParameterName(combinationParameter)
                .asText(Language.EN) + " at index [" + combinationParameter
                .getSequenceNumber() + "] ('" + parameterAsText + "')");
        sb.append("has a wrong value " + ": actual value = '" + String.valueOf(actualValue) + "'");
        if (combinationParameter.getValues() != null) {
            Iterator<FormulaParameterCombinationItemValue> itExpectedValues = combinationParameter.getValues()
                    .iterator();
            if (itExpectedValues.hasNext()) {
                sb.append(" , expected values = {'" + itExpectedValues.next());
                while (itExpectedValues.hasNext()) {
                    sb.append("', '" + itExpectedValues.next());
                }
                sb.append("'}");
            }
        }
        return sb.toString();
    }
}
