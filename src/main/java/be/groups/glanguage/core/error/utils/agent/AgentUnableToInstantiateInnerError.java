package be.groups.glanguage.core.error.utils.agent;

import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.GLanguageInnerError;

/**
 * @author michotte
 */
public class AgentUnableToInstantiateInnerError extends GLanguageInnerError {
    final static GLanguageErrorRegistry errorRegistry = GLanguageErrorRegistry.AGENT_UNABLE_TO_INSTANTIATE;

    public AgentUnableToInstantiateInnerError() {
        super(errorRegistry.getCode());
        setMainMessage(errorRegistry.getMessage());
    }

    public AgentUnableToInstantiateInnerError(String cause) {
        this();
        setMessage(cause);
    }
}

