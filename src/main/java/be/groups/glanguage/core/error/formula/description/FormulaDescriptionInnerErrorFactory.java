package be.groups.glanguage.core.error.formula.description;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;

import java.util.List;

/**
 * @author michotte
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
