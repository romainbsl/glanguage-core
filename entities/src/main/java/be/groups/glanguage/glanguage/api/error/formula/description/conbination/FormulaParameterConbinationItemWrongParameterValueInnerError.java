package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItemValue;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;

import java.util.Iterator;

/**
 * Created by michotte on 11/05/2017.
 */
public class FormulaParameterConbinationItemWrongParameterValueInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterConbinationItemWrongParameterValueInnerError(AbstractFormula formula,
                                                                        FormulaUsage usage,
                                                                        FormulaParameterConbinationItem
                                                                                conbinationParameter,
                                                                        String parameterAsText,
                                                                        Object actualValue,
                                                                        Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_CONBINATION_ITEM_WRONG_VALUE,
              formula,
              evaluator,
              "validate",
              getCause(usage, conbinationParameter, parameterAsText, actualValue));
    }

    private static String getCause(FormulaUsage usage,
                                   FormulaParameterConbinationItem conbinationParameter,
                                   String parameterAsText,
                                   Object actualValue) {
        StringBuilder sb = new StringBuilder("Parameter " + getParameterName(usage, conbinationParameter) +
                                                     " at index [" + conbinationParameter.getSequenceNumber() +
                                                     "] ('" + parameterAsText + "')");
        sb.append("has a wrong value " + ": actual value = '" + String.valueOf(actualValue) + "'");
        if (conbinationParameter.getValues() != null) {
            Iterator<FormulaParameterConbinationItemValue> itExpectedValues = conbinationParameter.getValues()
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
