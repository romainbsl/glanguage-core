package be.groups.glanguage.core.error.utils.agent;

import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.GLanguageInnerError;

/**
 * Created by boissero on 3/28/2017.
 */
public class AgentNotCallableInnerError extends GLanguageInnerError {
    final static GLanguageErrorRegistry errorRegistry = GLanguageErrorRegistry.AGENT_NOT_CALLABLE;

    public AgentNotCallableInnerError() {
        super(errorRegistry.getCode());
        setMainMessage(errorRegistry.getMessage());
    }

    public AgentNotCallableInnerError(String cause) {
        this();
        setMessage(cause);
    }

    public AgentNotCallableInnerError(String methodName, Class<?>[] methodParameterTypes) {
        this();

        StringBuilder sb = new StringBuilder();
        sb.append("The method/field '");
        sb.append(methodName);
        sb.append("' doesn't exist ");

        for (int i = 0; i < methodParameterTypes.length - 1; i++) {
            if (i > 0)
                sb.append(", ");
            else
                sb.append("' with the parameters (");

            sb.append(methodParameterTypes[i].toString());

            if (i == methodParameterTypes.length - 1) sb.append(")");
        }

        setMessage(sb.toString());
    }
}
