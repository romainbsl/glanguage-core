package be.groups.glanguage.glanguage.api.error.parser;

import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 6/01/2017.
 */
public class ParserUnableToParseTextInnerError extends ParserInnerError {

    private Exception exception;

    public ParserUnableToParseTextInnerError(String text, String methodName, Exception exception) {
        super(GLanguageErrorRegistry.PARSER_UNABLE_TO_PARSE_FORMULA, methodName, getCause(), text);
        this.exception = exception;
    }

    private static String getCause() {
        return "Unknown error";
    }

}
