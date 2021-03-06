package be.groups.glanguage.core.error.parser;

import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.GLanguageInnerError;

/**
 * @author michotte
 */
public class ParserInnerError extends GLanguageInnerError {

    private String methodName;
    private String cause;
    private String text;

    public ParserInnerError() {
        this(GLanguageErrorRegistry.PARSER_INNER_ERROR, null,  null, null);
    }

    public ParserInnerError(GLanguageErrorRegistry error, String methodName, String cause, String text) {
        super(error.getCode());
        setMainMessage(error.getMessage());
        createFields(methodName, cause, text);
        setMessage(createMessage());
    }

    private void createFields(String methodName, String cause, String text) {
        this.methodName = methodName;
        this.cause = cause;
        this.text = text;
    }

    public String createMessage() {
        StringBuilder sb = new StringBuilder(super.createMessage());
        if (cause != null) {
            sb.append(" - Cause: " + cause);
        }
        if (text != null) {
            sb.append(" | \"" + text + "\"");
        }
        return sb.toString();
    }

    public String getMethodName() {
        return methodName;
    }

    public String getCause() {
        return cause;
    }

    public String getText() {
        return text;
    }
}
