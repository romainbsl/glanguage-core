package be.groups.glanguage.core.error.formula.description;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class FormulaDescriptionInnerError extends FormulaInnerError {

    private FormulaDescription formulaDescription;

    public FormulaDescriptionInnerError(GLanguageErrorRegistry error,
                                        AbstractFormula formula,
                                        Evaluator evaluator,
                                        String methodName,
                                        String cause) {
        super(error, formula, evaluator, methodName, cause);
    }

    @Override
    protected void createFields(AbstractFormula formula, Evaluator evaluator, String methodName, String cause) {
        super.createFields(formula, evaluator, methodName, cause);
        this.formulaDescription = formula.getDescription();
    }

    public FormulaDescription getFormulaDescription() {
        return formulaDescription;
    }
}