package be.groups.glanguage.glanguage.api.error.parser;

import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * @author michotte
 */
public class ParserUnableToParseTextInnerError extends ParserInnerError {

    private Exception exception;

    public ParserUnableToParseTextInnerError(String text, String methodName, String cause, Exception exception) {
        super(GLanguageErrorRegistry.PARSER_UNABLE_TO_PARSE_FORMULA, methodName, getCause(cause), text);
        this.exception = exception;
    }

    private static String getCause(String cause) {
        return cause != null ? cause : "Unknown error";
    }

    public Exception getException() {
        return exception;
    }
}
