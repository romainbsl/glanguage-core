package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbination;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;

/**
 * Created by michotte on 26/01/2017.
 */
public class FormulaParameterConbinationInnerErrorFactory {

    public static FormulaDescriptionInnerError getUnableToValidate(AbstractFormula formula,
                                                                   FormulaParameterConbination conbination,
                                                                   Evaluator evaluator) {
        return new FormulaParameterConbinationUnableToValidateInnerError(formula,
                                                                             conbination,
                                                                             evaluator);
    }

    public static FormulaDescriptionInnerError getWrongParameterNumber(AbstractFormula formula,
                                                            int actualNumberOfParameters,
                                                            int expectedMinimumNumberOfParameters,
                                                            int expectedMaximumNumberOfParameters,
                                                                       Evaluator evaluator) {
        return new FormulaParameterConbinationWrongParameterNumberInnerError(formula,
                                                                             evaluator,
                                                                             actualNumberOfParameters,
                                                                             expectedMinimumNumberOfParameters,
                                                                             expectedMaximumNumberOfParameters);
    }

    public static FormulaDescriptionInnerError getUnreachableParameters(AbstractFormula formula,
                                                                        int numberOfUnreachableParameters,
                                                                        Evaluator evaluator) {
        return new FormulaParameterConbinationUnreachableParametersInnerError(formula, numberOfUnreachableParameters, evaluator);
    }
}
