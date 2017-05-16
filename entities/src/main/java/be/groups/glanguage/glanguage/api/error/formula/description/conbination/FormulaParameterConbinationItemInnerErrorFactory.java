package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;

/**
 * Created by michotte on 26/01/2017.
 */
public class FormulaParameterConbinationItemInnerErrorFactory {

    public static InnerError getNullParameter(AbstractFormula formula,
                                              FormulaUsage usage,
                                              FormulaParameterConbinationItem conbinationParameter,
                                              Evaluator evaluator) {
        return new FormulaParameterConbinationItemNullInnerError(formula, usage, conbinationParameter, evaluator);
    }

    public static FormulaDescriptionInnerError getUnableToValidate(AbstractFormula formula,
                                                                   FormulaUsage usage,
                                                                   FormulaParameterConbinationItem conbinationParameter,
                                                                   String parameterAsText,
                                                                   Evaluator evaluator) {
        return new FormulaParameterConbinationItemUnableToValidateInnerError(formula,
                                                                             usage,
                                                                             conbinationParameter,
                                                                             parameterAsText,
                                                                             evaluator);
    }

    public static FormulaDescriptionInnerError getWrongParameterType(AbstractFormula formula,
                                                                     FormulaUsage usage,
                                                                     FormulaParameterConbinationItem
                                                                            conbinationParameter,
                                                                     String parameterAsText,
                                                                     FormulaReturnType actualReturnType,
                                                                     Evaluator evaluator) {
        return new FormulaParameterConbinationItemWrongParameterTypeInnerError(formula,
                                                                               usage,
                                                                               conbinationParameter,
                                                                               parameterAsText,
                                                                               actualReturnType,
                                                                               evaluator);
    }

    public static FormulaDescriptionInnerError getWrongParameterValue(AbstractFormula formula,
                                                                      FormulaUsage usage,
                                                                      FormulaParameterConbinationItem
                                                                             conbinationParameter,
                                                                      String parameterAsText,
                                                                      Object actualValue,
                                                                      Evaluator evaluator) {
        return new FormulaParameterConbinationItemWrongParameterValueInnerError(formula,
                                                                                usage,
                                                                                conbinationParameter,
                                                                                parameterAsText,
                                                                                actualValue,
                                                                                evaluator);
    }

}
