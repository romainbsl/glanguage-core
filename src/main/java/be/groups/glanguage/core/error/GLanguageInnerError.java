package be.groups.glanguage.core.error;

import be.groups.errorframework.core.error.InnerError;

/**
 * @author michotte
 */
public abstract class GLanguageInnerError extends InnerError {

    private String mainMessage;

    public GLanguageInnerError(String code) {
        super(code, null);
    }

    public GLanguageInnerError(String code, String message) {
        super(code, message, null);
    }

    public String getMainMessage() {
        return mainMessage;
    }

    public void setMainMessage(String mainMessage) {
        this.mainMessage = mainMessage;
    }

    public String createMessage() {
        return mainMessage;
    }

}
