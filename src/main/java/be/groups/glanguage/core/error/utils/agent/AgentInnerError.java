package be.groups.glanguage.core.error.utils.agent;

import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.GLanguageInnerError;

/**
 * Created by michotte on 10/10/2017.
 */
public class AgentInnerError extends GLanguageInnerError {

  public AgentInnerError(GLanguageErrorRegistry error, String cause) {
    super(error.getCode());
    setMainMessage(error.getMessage());
    setMessage(cause);
  }

  public AgentInnerError(GLanguageErrorRegistry error, String methodName, Class<?>[] methodParameterTypes) {
    super(error.getCode());
    setMainMessage(error.getMessage());
    setMessage(getMessage(methodName, methodParameterTypes));
  }

  private String getMessage(String methodName, Class<?>[] methodParameterTypes) {
    StringBuilder sb = new StringBuilder();
    sb.append("The method/field '");
    sb.append(methodName);
    sb.append("' doesn't exist with ");

    if (methodParameterTypes.length > 0) {
      for (int i = 0; i < methodParameterTypes.length; i++) {
        if (i > 0)
          sb.append(", ");
        else
          sb.append("the parameters (");

        sb.append(methodParameterTypes[i].toString());

        if (i == methodParameterTypes.length - 1) sb.append(")");
      }
    } else {
      sb.append("no parameters");
    }
    return sb.toString();
  }
}
