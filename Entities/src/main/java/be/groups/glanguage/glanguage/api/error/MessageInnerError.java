package be.groups.glanguage.glanguage.api.error;

import be.groups.errorframework.core.error.InnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public class MessageInnerError extends InnerError{

    private String message;

    public MessageInnerError(String code, InnerError innererror, String message) {
        super(code, innererror);
        this.message = message;
    }

    public MessageInnerError(String code, String message) {
        this(code, null, message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
