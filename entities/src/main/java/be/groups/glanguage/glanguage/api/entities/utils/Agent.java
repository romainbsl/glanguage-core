package be.groups.glanguage.glanguage.api.entities.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Copied from be.groups.kernel.utils.agents.Agents<br>
 * Object that represent an agent for dynamic and deferred execution of a method on an object<br>
 * <p>
 * Limitations : <br>
 * To dynamically execute a method that takes a parameter of the basic types of Java (boolean, int, double, ...), the signature of the
 * method has to be converted using the corresponding wrapper types (Boolean , Integer, Double, ...)
 *
 * @author Agustin BERROCAL MENA
 * @author michotte
 */
public class Agent {

    private Object object;
    private String methodName;
    private Method method;
    private String fieldName;
    private Field field;

    private boolean isError;

    /**
     * Creation of an agent with a target object {@code object} a method name {@code methodName} and a list of types of the parameters
     * {@code methodParameterTypes} of the method to be executed
     *
     * @param object               Target object
     * @param methodName           Method name
     * @param methodParameterTypes list of types of the parameters
     */
    public Agent(Object object, String methodName, Class<?>... methodParameterTypes) {
        if (object == null) {
            throw new IllegalArgumentException("object must be non-null");
        }
        if (methodName == null || methodName.isEmpty()) {
            throw new IllegalArgumentException("methodName must be a non-null non-empty string");
        }
        this.object = object;
        this.methodName = methodName;
        this.fieldName = methodName;

//        Stream<Method> matchMethods = Arrays.stream(object.getClass().getMethods())
        Optional<Method> methodOptional = Arrays.stream(object.getClass().getMethods())
                .filter(
                        it -> it.getName().equals(methodName) &&
                                Arrays.equals(it.getParameterTypes(), methodParameterTypes)
                ).findFirst();

        /*if (methodParameterTypes == null || methodParameterTypes.length == 0) {*/
        /*Optional<Method> methodOptional = matchMethods.findFirst();*/

        if (methodOptional.isPresent())
            method = methodOptional.get();
        else {
            Optional<Field> fieldOptional =
                    Arrays.stream(object.getClass().getFields())
                            .filter(it -> it.getName().equals(fieldName))
                            .findFirst();

            if (fieldOptional.isPresent())
                field = fieldOptional.get();
        }
       /* } else {
            List<Method> methodList = matchMethods
                    .filter(
                            it ->
                    )
                    .collect(Collectors.toList());

            if (methodList.size() == 1)
                method = methodList.get(0);
            else if (methodList.size() > 1) {
                try {
                    method = object.getClass().getMethod(methodName, methodParameterTypes);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException();
                }
            }
        }*/

        if (!isMethod() && !isField())
            throw new RuntimeException();
    }

    private Method findBestMatchingMethod(List<Method> methodList, Class<?>[] methodParameterTypes) {
        Method bestMatchingMethod = null;

        for (Method checkMethod : methodList) {

        }

        return bestMatchingMethod;
    }

    public boolean isMethod() {
        return this.method != null;
    }

    public boolean isField() {
        return this.field != null;
    }

    public boolean isError() {
        return this.isError;
    }

    /**
     * Call and dynamic execution of this method on this target object with parameters {@code methodParameters}<br>
     * Like: object.method() OR object.method(methodParameters)<br>
     * OR<br>
     * Dynamic access to this field on this target object
     * Like: object.field
     *
     * @param methodParameters Parameters of the method to be executed
     * @return Result of the dynamic execution of this method on this target object with parameters {@code methodParameters} OR dynamic
     * access to this field on this target object
     */
    public Object call(Object... methodParameters) {
        if (methodParameters == null) {
            throw new IllegalArgumentException("methodParameters must be non-null");
        }
        resetError();
        try {
            if (isField())
                // Dynamic call of Object's Field ...
                // --------> like object.field <-----------
                return field.get(object);
            else {
                if (methodParameters == null || methodParameters.length == 0) {
                    // Dynamic call of Object's Method without methodParameters...
                    // --------> like object.method () <-----------
                    return method.invoke(object);
                } else {
                    // Dynamic call of Object's Method with methodParameters...
                    // --------> like object.method (methodParameters) <------------
                    return method.invoke(object, methodParameters);
                }
            }
        } catch (Exception ex) {
            setError();
            throw new RuntimeException(FormatErrorMsg("Error when creating agents to execute Dynamic call", ex), ex);
        }
    }

    /**
     * Appel et exécution dynamique de la méthode {@code methodName} portant sur l'objet cible {@code object}.<br>
     * <p>
     * Like: object.method()
     *
     * @param object     Objet cible
     * @param methodName Nom de la méthode à exécuter
     * @return Résultat de l'exécution dynamique de la méthode {@code method}
     * portant sur l'objet cible {@code object}
     */
    public static Object call(Object object, String methodName) {
        if (object == null) {
            throw new IllegalArgumentException("object must be non-null");
        }
        if (methodName == null || methodName.isEmpty()) {
            throw new IllegalArgumentException("methodName must be a non-null non-empty string");
        }
        return new Agent(object, methodName).call();
    }

    /**
     * Appel et exécution dynamique de la méthode {@code methodName} portant
     * sur l'objet cible {@code object} avec comme paramètres {@code methodParameters}.<br>
     * <p>
     * Like: object.method(methodParameters)
     *
     * @param object               Objet cible
     * @param methodName           Nom de la méthode à exécuter
     * @param methodParametersType Type des paramètres de la méthode à exécuter
     * @param methodParameters     Paramètres de la méthode à exécuter
     * @return Résultat de l'exécution dynamique de la méthode {@code method}
     * portant sur l'objet {@code object} avec comme paramètres {@code methodParameters}
     */
    public static Object call(Object object, String methodName, Class<?>[] methodParametersType,
                              Object[] methodParameters) {
        if (object == null) {
            throw new IllegalArgumentException("object must be non-null");
        }
        if (methodName == null || methodName.isEmpty()) {
            throw new IllegalArgumentException("methodName must be a non-null non-empty string");
        }
        if (methodParametersType == null) {
            throw new IllegalArgumentException("methodParametersType must be non-null");
        }
        if (methodParameters == null) {
            throw new IllegalArgumentException("methodParameters must be non-null");
        }
        return new Agent(object, methodName, methodParametersType).call(methodParameters);
    }

    private String FormatErrorMsg(String message, Exception ex) {
        String errorMsg = "";
        if (isField()) {
            errorMsg = message +
                    " of " + object.getClass().getName() + "'s Field " + fieldName + "\n" +
                    " => Message " + ex.getClass() + " " + ex.getMessage();
        } else {
            errorMsg = message +
                    " of " + object.getClass().getName() + "'s Method " + methodName + "\n" +
                    " => Message: " + ex.getClass() + " " + ex.getMessage();
        }
        if (ex.getCause() != null)
            errorMsg = errorMsg + "\n" +
                    "    Caused by: " + ex.getCause().getClass() + " " + ex.getCause().getMessage();
        return errorMsg;
    }

    private void resetError() {
        isError = false;
    }

    private void setError() {
        isError = true;
    }
}
