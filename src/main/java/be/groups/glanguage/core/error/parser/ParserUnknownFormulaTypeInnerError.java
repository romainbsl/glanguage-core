package be.groups.glanguage.core.error.parser;

import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;

/**
 * @author michotte
 */
public class ParserUnknownFormulaTypeInnerError extends ParserInnerError {

    public ParserUnknownFormulaTypeInnerError(FormulaType formulaType, String methodName, String text) {
        super(GLanguageErrorRegistry.PARSER_UNKNOWN_FORMULA_TYPE, null, getCause(formulaType), text);
    }

    private static String getCause(FormulaType formulaType) {
        return "Formula type: " + formulaType;
    }

}
