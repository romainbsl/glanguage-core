package be.groups.glanguage.glanguage.api.error;

import be.groups.errorframework.core.error.InnerError;

/**
 * Created by michotte on 23/12/2016.
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
