package be.groups.glanguage.glanguage.api.error.parser;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 6/01/2017.
 */
public class ParserUnknownFormulaTypeInnerError extends ParserInnerError {

    public ParserUnknownFormulaTypeInnerError(FormulaType formulaType, String methodName, String text) {
        super(GLanguageErrorRegistry.PARSER_FORMULA_UNKNOWN_TYPE_INNER_ERROR, null,  getCause(formulaType), text);
    }

    private static String getCause(FormulaType formulaType) {
        return "Formula type: " + formulaType;
    }

}
