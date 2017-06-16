package be.groups.glanguage.glanguage.api.error.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;

import java.util.List;

/**
 * Created by michotte on 26/01/2017.
 */
public class FormulaDescriptionInnerErrorFactory {

    public static FormulaDescriptionInnerError getUnableToValidate(AbstractFormula formula,
                                                                   List<AbstractFormula> parameters,
                                                                   Evaluator evaluator) {
        return new FormulaDescriptionUnableToValidateInnerError(formula, parameters, evaluator);
    }

    public static FormulaDescriptionInnerError getNoMatchingUsage(AbstractFormula formula,
                                                                  List<AbstractFormula> parameters,
                                                                  Evaluator evaluator) {
        return new FormulaDescriptionNoMatchingUsageInnerError(formula, parameters, evaluator);
    }

}
