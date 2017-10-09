package be.groups.glanguage.core.error.formula;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.GLanguageInnerError;

/**
 * @author michotte
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