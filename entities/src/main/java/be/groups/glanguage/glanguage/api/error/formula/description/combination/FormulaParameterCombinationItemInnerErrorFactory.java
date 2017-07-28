package be.groups.glanguage.glanguage.api.error.formula.description.combination;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;

/**
 * @author michotte
 */
public class FormulaParameterCombinationItemInnerErrorFactory {

    public static InnerError getNullParameter(AbstractFormula formula,
                                              FormulaUsage usage,
                                              FormulaParameterCombinationItem combinationParameter,
                                              Evaluator evaluator) {
        return new FormulaParameterCombinationItemNullInnerError(formula, usage, combinationParameter, evaluator);
    }

    public static FormulaDescriptionInnerError getUnableToValidate(AbstractFormula formula,
                                                                   FormulaUsage usage,
                                                                   FormulaParameterCombinationItem combinationParameter,
                                                                   String parameterAsText,
                                                                   Evaluator evaluator) {
        return new FormulaParameterCombinationItemUnableToValidateInnerError(formula,
                                                                             usage,
                                                                             combinationParameter,
                                                                             parameterAsText,
                                                                             evaluator);
    }

    public static FormulaDescriptionInnerError getWrongParameterType(AbstractFormula formula,
                                                                     FormulaUsage usage,
                                                                     FormulaParameterCombinationItem
                                                                            combinationParameter,
                                                                     String parameterAsText,
                                                                     FormulaReturnType actualReturnType,
                                                                     Evaluator evaluator) {
        return new FormulaParameterCombinationItemWrongParameterTypeInnerError(formula,
                                                                               usage,
                                                                               combinationParameter,
                                                                               parameterAsText,
                                                                               actualReturnType,
                                                                               evaluator);
    }

    public static FormulaDescriptionInnerError getWrongParameterValue(AbstractFormula formula,
                                                                      FormulaUsage usage,
                                                                      FormulaParameterCombinationItem
                                                                             combinationParameter,
                                                                      String parameterAsText,
                                                                      Object actualValue,
                                                                      Evaluator evaluator) {
        return new FormulaParameterCombinationItemWrongParameterValueInnerError(formula,
                                                                                usage,
                                                                                combinationParameter,
                                                                                parameterAsText,
                                                                                actualValue,
                                                                                evaluator);
    }

}
