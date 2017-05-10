package be.groups.glanguage.glanguage.api.error.exception;

import be.groups.errorframework.core.error.InnerError;
import be.groups.errorframework.core.error.RootError;
import be.groups.errorframework.core.exception.GroupSException;

/**
 * Created by michotte on 20/12/2016.
 */
public class GLanguageException extends GroupSException {

    public GLanguageException(InnerError innerError) {
        super(new GlanguageError());
        getError().setInnerError(innerError);
    }

    private static class GlanguageError extends RootError {
        @Override
        public String getRootErrorCode() {
            return "GLANGUAGE_ERROR";
        }
        @Override
        public String getRootErrorMessage() {
            return "Something went wrong in the process of the Glanguage. Please refer to the InnerError(s)";
        }
    }
}
