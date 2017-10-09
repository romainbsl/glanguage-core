package be.groups.glanguage.core.error.formula.description.combination;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationItemValue;
import be.groups.glanguage.core.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.core.entities.utils.Language;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;

import java.util.Iterator;

/**
 * @author michotte
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
