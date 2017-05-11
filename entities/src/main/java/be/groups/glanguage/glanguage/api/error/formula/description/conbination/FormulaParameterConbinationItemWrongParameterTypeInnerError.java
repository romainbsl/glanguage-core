package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItemType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;

import java.util.Iterator;

/**
 * Created by michotte on 11/05/2017.
 */
public class FormulaParameterConbinationItemWrongParameterTypeInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterConbinationItemWrongParameterTypeInnerError(AbstractFormula formula,
                                                                       FormulaUsage usage,
                                                                       FormulaParameterConbinationItem
                                                                               conbinationParameter,
                                                                       String parameterAsText,
                                                                       FormulaReturnType actualType,
                                                                       Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_CONBINATION_ITEM_WRONG_TYPE,
              formula,
              evaluator,
              "validate",
              getCause(usage, conbinationParameter, parameterAsText, actualType));
    }

    private static String getCause(FormulaUsage usage,
                                   FormulaParameterConbinationItem conbinationParameter,
                                   String parameterAsText,
                                   FormulaReturnType actualType) {
        StringBuilder sb = new StringBuilder("Parameter " + getParameterName(usage,conbinationParameter) +
                                                     " at index [" + conbinationParameter.getSequenceNumber() +
                                                     "] ('" + parameterAsText + "')");
        sb.append(" has a wrong type " + ": actual type = " + actualType.name());
        if (conbinationParameter.getTypes() != null) {
            Iterator<FormulaParameterConbinationItemType> itExpectedTypes = conbinationParameter.getTypes().iterator();
            if (itExpectedTypes.hasNext()) {
                sb.append(" , expected types = {'" + itExpectedTypes.next().getReturnType().name());
                while (itExpectedTypes.hasNext()) {
                    sb.append("', '" + itExpectedTypes.next().getReturnType().name());
                }
                sb.append("'}");
            }
        }
        return sb.toString();
    }
}
