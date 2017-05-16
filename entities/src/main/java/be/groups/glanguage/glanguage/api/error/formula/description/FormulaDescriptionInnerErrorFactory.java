package be.groups.glanguage.glanguage.api.error.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;

import java.util.List;

/**
 * Created by michotte on 26/01/2017.
 */
public class FormulaDescriptionInnerErrorFactory {

    public static FormulaDescriptionInnerError getUnableToValidate(AbstractFormula formula,
                                                                   List<AbstractFormula> parameters,
                                                                   Evaluator evaluator,
                                                                   FormulaDescription formulaDescription) {
        // TODO
        return null;
    }

    public static FormulaDescriptionInnerError getNoMatchingUsage(AbstractFormula formula,
                                                                  List<AbstractFormula> parameters,
                                                                  Evaluator evaluator,
                                                                  FormulaDescription formulaDescription) {
        // TODO
        return null;
    }

}
