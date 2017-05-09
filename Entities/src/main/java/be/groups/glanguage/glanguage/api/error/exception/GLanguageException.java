package be.groups.glanguage.glanguage.api.error.exception;

import be.groups.errorframework.core.error.InnerError;
import be.groups.errorframework.core.exception.BusinessException;

/**
 * Created by michotte on 20/12/2016.
 */
public class GLanguageException extends BusinessException {

    public GLanguageException(InnerError rootInnerError) {
        super();
        getError().setInnerError(rootInnerError);
    }

}
