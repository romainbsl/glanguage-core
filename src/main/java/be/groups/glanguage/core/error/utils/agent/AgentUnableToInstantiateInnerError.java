package be.groups.glanguage.core.error.utils.agent;

import be.groups.glanguage.core.error.GLanguageErrorRegistry;

/**
 * @author michotte
 */
public class AgentUnableToInstantiateInnerError extends AgentInnerError {

    public AgentUnableToInstantiateInnerError(String cause) {
        super(GLanguageErrorRegistry.AGENT_UNABLE_TO_INSTANTIATE, cause);
    }

    public AgentUnableToInstantiateInnerError(String methodName, Class<?>[] methodParameterTypes) {
        super(GLanguageErrorRegistry.AGENT_UNABLE_TO_INSTANTIATE, methodName, methodParameterTypes);
    }
}

