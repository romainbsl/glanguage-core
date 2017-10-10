package be.groups.glanguage.core.error.utils.agent;

import be.groups.glanguage.core.error.GLanguageErrorRegistry;

/**
 * Created by boissero on 3/28/2017.
 */
public class AgentNotCallableInnerError extends AgentInnerError {

    public AgentNotCallableInnerError(String cause) {
        super(GLanguageErrorRegistry.AGENT_NOT_CALLABLE, cause);
    }

    public AgentNotCallableInnerError(String methodName, Class<?>[] methodParameterTypes) {
        super(GLanguageErrorRegistry.AGENT_NOT_CALLABLE, methodName, methodParameterTypes);
    }
}
