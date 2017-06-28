package be.groups.glanguage.glanguage.api.error.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombination;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;

/**
 * @author michotte
 */
public class FormulaParameterCombinationInnerErrorFactory {

    public static FormulaDescriptionInnerError getUnableToValidate(AbstractFormula formula,
                                                                   FormulaParameterCombination combination,
                                                                   Evaluator evaluator) {
        return new FormulaParameterCombinationUnableToValidateInnerError(formula,
                                                                             combination,
                                                                             evaluator);
    }

    public static FormulaDescriptionInnerError getWrongParameterNumber(AbstractFormula formula,
                                                            int actualNumberOfParameters,
                                                            int expectedMinimumNumberOfParameters,
                                                            int expectedMaximumNumberOfParameters,
                                                                       Evaluator evaluator) {
        return new FormulaParameterCombinationWrongParameterNumberInnerError(formula,
                                                                             evaluator,
                                                                             actualNumberOfParameters,
                                                                             expectedMinimumNumberOfParameters,
                                                                             expectedMaximumNumberOfParameters);
    }

    public static FormulaDescriptionInnerError getUnreachableParameters(AbstractFormula formula,
                                                                        int numberOfUnreachableParameters,
                                                                        Evaluator evaluator) {
        return new FormulaParameterCombinationUnreachableParametersInnerError(formula, numberOfUnreachableParameters, evaluator);
    }
}
