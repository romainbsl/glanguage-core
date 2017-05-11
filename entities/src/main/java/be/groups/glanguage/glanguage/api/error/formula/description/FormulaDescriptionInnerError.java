package be.groups.glanguage.glanguage.api.error.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 23/12/2016.
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

    protected static String getParameterName(FormulaUsage usage, FormulaParameterConbinationItem conbinationParameter) {
        String usageParameterName = usage.getParameterName(conbinationParameter);
        return usageParameterName != null ? usageParameterName : conbinationParameter.getName().asText();
    }

    public FormulaDescription getFormulaDescription() {
        return formulaDescription;
    }
}