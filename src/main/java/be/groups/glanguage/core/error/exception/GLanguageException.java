package be.groups.glanguage.core.error.exception;

import be.groups.errorframework.core.error.InnerError;
import be.groups.errorframework.core.error.RootError;
import be.groups.errorframework.core.exception.GroupSException;

/**
 * @author michotte
 */
public class GLanguageException extends GroupSException {

    public GLanguageException(InnerError innerError) {
        super(new GLanguageError());
        getError().setInnerError(innerError);
    }

    private static class GLanguageError extends RootError {
        @Override
        public String getRootErrorCode() {
            return "GLANGUAGE_TECHNICAL_EXCEPTION";
        }
        @Override
        public String getRootErrorMessage() {
            return "Something went wrong in the process of the Glanguage. Please refer to the InnerError(s)";
        }
    }
}
