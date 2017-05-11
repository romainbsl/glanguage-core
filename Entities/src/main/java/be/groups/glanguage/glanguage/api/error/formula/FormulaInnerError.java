package be.groups.glanguage.glanguage.api.error.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.GLanguageInnerError;

/**
 * Created by michotte on 23/12/2016.
 */
public class FormulaInnerError extends GLanguageInnerError {

    private String formulaIdentification;
    private String evaluator;
    private String methodName;
    private String cause;
    private String formulaAsText;

    public FormulaInnerError(GLanguageErrorRegistry error,
                             AbstractFormula formula,
                             Evaluator evaluator,
                             String methodName,
                             String cause) {
        super(error.getCode());
        setMainMessage(error.getMessage());
        createFields(formula, evaluator, methodName, cause);
        setMessage(createMessage());
    }

    protected void createFields(AbstractFormula formula, Evaluator evaluator, String methodName, String cause) {
        this.formulaIdentification = formula.getClass().getName() + "[id: " + formula.getId() + "]";
        this.evaluator = evaluator == null ? "null" : "not null";
        this.methodName = methodName;
        this.cause = cause;
        this.formulaAsText = formula.asText();
    }

    public String createMessage() {
        StringBuilder sb = new StringBuilder(super.createMessage());
        sb.append(": " + formulaIdentification);
        sb.append("." + methodName);
        sb.append("(evaluator: " + evaluator + ")");
        if (cause != null) {
            sb.append(" - Cause: " + cause);
        }
        sb.append(" | \"" + formulaAsText + "\"");
        return sb.toString();
    }

    public String getFormulaIdentification() {
        return formulaIdentification;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getCause() {
        return cause;
    }

    public String getFormulaAsText() {
        return formulaAsText;
    }

}