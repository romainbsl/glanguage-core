package be.groups.glanguage.glanguage.api.error.exception;

import be.groups.errorframework.core.error.RootError;
import be.groups.errorframework.core.exception.GroupSException;

/**
 * Created by michotte on 20/12/2016.
 */
public class GLanguageEvaluationException extends GroupSException {
    public GLanguageEvaluationException(RootError error) {
        super(error);
    }
}
