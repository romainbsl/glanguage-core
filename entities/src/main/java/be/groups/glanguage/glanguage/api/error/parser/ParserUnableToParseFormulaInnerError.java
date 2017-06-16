package be.groups.glanguage.glanguage.api.error.parser;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

import java.util.LinkedList;

/**
 * Created by michotte on 6/01/2017.
 */
public class ParserUnableToParseFormulaInnerError extends ParserInnerError {

    private Exception exception;

    public ParserUnableToParseFormulaInnerError(FormulaDescription formulaDescription,
                                                LinkedList<AbstractFormula> parameters,
                                                String methodName,
                                                Exception exception) {
        super(GLanguageErrorRegistry.PARSER_UNABLE_TO_PARSE_FORMULA, methodName, getCause(formulaDescription, parameters)
                , null);
        this.exception = exception;
    }

    private static String getCause(FormulaDescription formulaDescription, LinkedList<AbstractFormula> parameters) {
        StringBuilder sb = new StringBuilder("Unable to parse a formula of type " + formulaDescription.getName());
        if (parameters != null && !parameters.isEmpty()) {
            sb.append(" with parameter");
            if (parameters.size() == 1) {
                sb.append(" " + parameters.get(0).asText());
            } else {
                sb.append("s: ");
                for (int i = 0; i < parameters.size(); i++) {
                    sb.append("\n\t" + (i + 1) + ".\t" + parameters.get(i).asText());
                }
            }
        }
        return sb.toString();
    }

    public Exception getException() {
        return exception;
    }
}
