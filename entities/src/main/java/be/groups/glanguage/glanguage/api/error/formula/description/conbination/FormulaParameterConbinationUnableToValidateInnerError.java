package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbination;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

/**
 * Created by michotte on 11/05/2017.
 */
public class FormulaParameterConbinationUnableToValidateInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterConbinationUnableToValidateInnerError(AbstractFormula formula,
                                                                 FormulaParameterConbination conbination,
                                                                 Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_CONBINATION_UNABLE_TO_VALIDATE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(conbination));
    }

    private static String getCause(FormulaParameterConbination conbination) {
        return "Unable to validate parameter conbination [id: + " + conbination.getId() + ", name:" + conbination
                .getName() + "]";
    }
}
