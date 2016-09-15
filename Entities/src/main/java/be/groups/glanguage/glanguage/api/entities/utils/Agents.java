package be.groups.glanguage.glanguage.api.entities.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Copied from be.groups.kernel.utils.agents.Agents<br>
 * Object that represent an agent for dynamic and deferred execution of a method on an object<br>
 *
 * Limitations : <br>
 * To dynamically execute a method that takes a parameter of the basic types of Java (boolean, int, double, ...), the signature of the
 * method has to be converted using the corresponding wrapper types (Boolean , Integer, Double, ...)
 *
 * @author Agustin BERROCAL MENA
 * @author michotte
 */
public class Agents {
	
	private static final String PATTERN_TO_USE_CURRENT_ELEMENT_OFF_LIST = "?";
	
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
	 * @param object Target object
	 * @param methodName Method name
	 * @param methodParameterTypes list of types of the parameters
	 */
	public Agents(Object object, String methodName, Class<?>... methodParameterTypes) {
		if (object == null) {
			throw new IllegalArgumentException("object must be non-null");
		}
		if (methodName == null || methodName.isEmpty()) {
			throw new IllegalArgumentException("methodName must be a non-null non-empty string");
		}
		this.object = object;
		this.methodName = methodName;
		this.fieldName = methodName;
		if (methodParameterTypes == null || methodParameterTypes.length == 0) {
			try {
				method = findMethodIncludingInherited(object.getClass());
			} catch (Exception ex) {
				try {
					field = findFieldIncludingInherited(object.getClass());
				} catch (Exception ex2) {
					throw new RuntimeException(FormatErrorMsg("Error when creating agents to execute Dynamic call", ex2), ex2);
				}
			}
		} else {
			try {
				method = findMethodIncludingInherited(object.getClass(), methodParameterTypes);
			} catch (Exception ex3) {
				throw new RuntimeException(FormatErrorMsg("Error when creating agents to execute Dynamic call", methodParameterTypes, ex3), ex3);
			}
		}
	}
	
	/**
	 * Creation of an agent with a target object {@code object} and a method {@code method} to be executed
	 *
	 * @param object Target object
	 * @param method Method to be executed
	 */
	public Agents(Object object, Method method) {
		if (object == null) {
			throw new IllegalArgumentException("object must be non-null");
		}
		if (method == null) {
			throw new IllegalArgumentException("method must be non-null");
		}
		this.object = object;
		this.methodName = method.getName();
		this.method = method;
	}
	
	/**
	 * Creation of an agent with a target object {@code object} and a field {@code field} to be accessed
	 *
	 * @param object Target object
	 * @param field Field to be accessed
	 */
	public Agents(Object object, Field field) {
		if (object == null) {
			throw new IllegalArgumentException("object must be non-null");
		}
		if (field == null) {
			throw new IllegalArgumentException("field must be non-null");
		}
		this.object = object;
		this.fieldName = field.getName();
		this.field = field;
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
	 *         access to this field on this target object
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
	 * 
	 * Like: object.method()
	 *
	 * @param object Objet cible
	 * @param methodName Nom de la méthode à exécuter
	 * @return Résultat de l'exécution dynamique de la méthode {@code method}
	 *         portant sur l'objet cible {@code object}
	 */
	public static Object call(Object object, String methodName) {
		if (object == null) {
			throw new IllegalArgumentException("object must be non-null");
		}
		if (methodName == null || methodName.isEmpty()) {
			throw new IllegalArgumentException("methodName must be a non-null non-empty string");
		}
		return new Agents(object, methodName).call();
	}
	
	/**
	 * Appel et exécution dynamique de la méthode {@code methodName} portant
	 * sur l'objet cible {@code object} avec comme paramètres {@code methodParameters}.<br>
	 * 
	 * Like: object.method(methodParameters)
	 *
	 * @param object Objet cible
	 * @param methodName Nom de la méthode à exécuter
	 * @param methodParametersType Type des paramètres de la méthode à exécuter
	 * @param methodParameters Paramètres de la méthode à exécuter
	 * @return Résultat de l'exécution dynamique de la méthode {@code method}
	 *         portant sur l'objet {@code object} avec comme paramètres {@code methodParameters}
	 */
	public static Object call(Object object, String methodName, Class<?>[] methodParametersType, Object[] methodParameters) {
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
		return new Agents(object, methodName, methodParametersType).call(methodParameters);
	}
	
	/**
	 * Parcourrir la liste {@code list} du début à la fin et exécuter dynamiquement une action.<br>
	 * Une action est définie par une méthode {@code actionMethodName} portant sur un objet cible {@code actionObject}.<br>
	 * Like: for(Object object: list) actionObject.actionMethod()
	 * 
	 * @param list Liste à parcourrir
	 * @param actionObject Objet cible; utiliser le Patern "?" pour que l'objet cible soit l'élément courant de la liste parcourrue
	 * @param actionMethodName Nom de la méthode à exécuter
	 */
	public static void doAll(List<?> list, Object actionObject, String actionMethodName) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceActionObjectByCurrentElement)
				actionObject = currentElement;
			new Agents(actionObject, actionMethodName).call();
		}
	}
	
	/**
	 * Parcourrir la liste {@code list} du début à la fin et exécuter dynamiquement une action.<br>
	 * Une action est définie par une méthode {@code actionMethodName} portant sur
	 * un objet cible {@code actionObject} avec comme paramètres {@code actionMethodParameters}.<br>
	 * Like: for(Object object: list) actionObject.actionMethod(actionMethodParameters...)
	 * 
	 * @param list Liste à parcourrir
	 * @param actionObject Objet cible; utiliser le Patern "?" pour que l'objet cible soit l'élément courant de la liste parcourrue
	 * @param actionMethodName Nom de la méthode à exécuter
	 * @param actionMethodParametersType Type des paramètres associés à la méthode cible à exécuter
	 * @param actionMethodParameters Paramètres associés à la méthode à exécuter; utiliser le Patern "?" pour que l'objet cible soit
	 *        l'élément courant de la liste parcourrue
	 */
	public static void doAll(List<?> list, Object actionObject, String actionMethodName, Class<?>[] actionMethodParametersType,
			Object[] actionMethodParameters) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (actionMethodParametersType == null) {
			throw new IllegalArgumentException("actionMethodParametersType must be non-null");			
		}
		if (actionMethodParameters == null) {
			throw new IllegalArgumentException("actionMethodParameters must be non-null");			
		}
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceActionObjectByCurrentElement)
				actionObject = currentElement;
			for (int i = 0; i < actionMethodParameters.length; i++) {
				if (isToBeReplacedByCurrentElementOffList(actionMethodParameters[i])) {
					actionMethodParameters[i] = currentElement;
				}
			}
			new Agents(actionObject, actionMethodName, actionMethodParametersType).call(actionMethodParameters);
		}
	}
	
	/**
	 * Parcourrir la liste {@code list} du début à la fin et exécuter dynamiquement une action si le test est satisfait.<br>
	 * Une action est définie par une méthode {@code actionMethodName} portant sur un objet cible {@code actionObject}.<br>
	 * Le test est défini par une méthode {@code testMethodName} portant sur un objet cible {@code testObject}.<br>
	 * Like: for(Object object: list) if(testObject.testMethod()) actionObject.actionMethod()
	 * 
	 * @param list Liste à parcourrir
	 * @param actionObject Objet cible; utiliser le Patern "?" pour que l'objet cible soit l'élément courant de la liste parcourrue
	 * @param actionMethodName Nom de la méthode à exécuter
	 * @param testObject Objet test cible; utiliser le Patern "?" pour que l'objet test cible soit l'élément courant de la liste
	 *        parcourrue
	 * @param testMethodName Nom de la méthode test à satisfaire
	 */
	public static void doIf(List<?> list, Object actionObject, String actionMethodName, Object testObject, String testMethodName) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (testObject == null) {
			throw new IllegalArgumentException("testObject must be non-null");
		}
		if (testMethodName == null || testMethodName.isEmpty()) {
			throw new IllegalArgumentException("testMethodName must be a non-null non-empty string");
		}
		boolean replaceTestObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(testObject);
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceTestObjectByCurrentElement)
				testObject = currentElement;
			if ((Boolean) new Agents(testObject, testMethodName).call()) {
				if (replaceActionObjectByCurrentElement)
					actionObject = currentElement;
				new Agents(actionObject, actionMethodName).call();
			}
		}
	}
	
	/**
	 * Parcourrir la liste {@code list} du début à la fin et exécuter dynamiquement une action si le test est satisfait.<br>
	 * Une action est définie par une méthode {@code actionMethodName} portant sur
	 * un objet cible {@code actionObject} avec comme paramètres {@code actionMethodParameters}.<br>
	 * Le test est défini par une méthode {@code testMethodName} portant sur
	 * un objet cible {@code testObject} avec comme paramètres {@code testMethodParameters}.<br>
	 * Like: for(Object object: list) if(testObject.testMethod(testMethodParameters)) actionObject.actionMethod(actionMethodParameters)
	 * 
	 * @param list Liste à parcourrir
	 * @param actionObject Objet cible; utiliser le Patern "?" pour que l'objet cible soit l'élément courant de la liste parcourrue
	 * @param actionMethodName Nom de la méthode à exécuter
	 * @param testObject Objet test cible; utiliser le Patern "?" pour que l'objet test cible soit l'élément courant de la liste
	 *        parcourrue
	 * @param testMethodName Nom de la méthode test à satisfaire
	 * @param testMethodParametersType Type des paramètres associés à la méthode test à satisfaire
	 * @param testMethodParameters Paramètres associés à la méthode test à satisfaire; utiliser le Patern "?" pour que l'objet test
	 *        cible soit l'élément courant de la liste parcourrue
	 */
	public static void doIf(List<?> list, Object actionObject, String actionMethodName, Object testObject, String testMethodName,
			Class<?>[] testMethodParametersType, Object[] testMethodParameters) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (testObject == null) {
			throw new IllegalArgumentException("testObject must be non-null");
		}
		if (testMethodName == null || testMethodName.isEmpty()) {
			throw new IllegalArgumentException("testMethodName must be a non-null non-empty string");
		}
		if (testMethodParametersType == null) {
			throw new IllegalArgumentException("testMethodParametersType must be non-null");			
		}
		if (testMethodParameters == null) {
			throw new IllegalArgumentException("testMethodParameters must be non-null");			
		}
		boolean replaceTestObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(testObject);
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceTestObjectByCurrentElement)
				testObject = currentElement;
			for (int i = 0; i < testMethodParameters.length; i++) {
				if (isToBeReplacedByCurrentElementOffList(testMethodParameters[i])) {
					testMethodParameters[i] = currentElement;
				}
			}
			if ((Boolean) new Agents(testObject, testMethodName, testMethodParametersType).call(testMethodParameters)) {
				if (replaceActionObjectByCurrentElement)
					actionObject = currentElement;
				new Agents(actionObject, actionMethodName).call();
			}
		}
	}
	
	public static void doIf(List<?> list, Object actionObject, String actionMethodName, Class<?>[] actionMethodParametersType,
			Object[] actionMethodParameters, Object testObject, String testMethodName) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (actionMethodParametersType == null) {
			throw new IllegalArgumentException("actionMethodParametersType must be non-null");			
		}
		if (actionMethodParameters == null) {
			throw new IllegalArgumentException("actionMethodParameters must be non-null");			
		}
		if (testObject == null) {
			throw new IllegalArgumentException("testObject must be non-null");
		}
		if (testMethodName == null || testMethodName.isEmpty()) {
			throw new IllegalArgumentException("testMethodName must be a non-null non-empty string");
		}
		boolean replaceTestObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(testObject);
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceTestObjectByCurrentElement)
				testObject = currentElement;
			if ((Boolean) new Agents(testObject, testMethodName).call()) {
				if (replaceActionObjectByCurrentElement)
					actionObject = currentElement;
				for (int i = 0; i < actionMethodParameters.length; i++) {
					if (isToBeReplacedByCurrentElementOffList(actionMethodParameters[i])) {
						actionMethodParameters[i] = currentElement;
					}
				}
				new Agents(actionObject, actionMethodName, actionMethodParametersType).call(actionMethodParameters);
			}
		}
	}
	
	public static void doIf(List<?> list, Object actionObject, String actionMethodName, Class<?>[] actionMethodParametersType,
			Object[] actionMethodParameters, Object testObject, String testMethodName, Class<?>[] testMethodParametersType,
			Object[] testMethodParameters) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (actionMethodParametersType == null) {
			throw new IllegalArgumentException("actionMethodParametersType must be non-null");			
		}
		if (actionMethodParameters == null) {
			throw new IllegalArgumentException("actionMethodParameters must be non-null");			
		}
		if (testObject == null) {
			throw new IllegalArgumentException("testObject must be non-null");
		}
		if (testMethodName == null || testMethodName.isEmpty()) {
			throw new IllegalArgumentException("testMethodName must be a non-null non-empty string");
		}
		if (testMethodParametersType == null) {
			throw new IllegalArgumentException("testMethodParametersType must be non-null");			
		}
		if (testMethodParameters == null) {
			throw new IllegalArgumentException("testMethodParameters must be non-null");			
		}
		boolean replaceTestObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(testObject);
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceTestObjectByCurrentElement)
				testObject = currentElement;
			for (int i = 0; i < testMethodParameters.length; i++) {
				if (isToBeReplacedByCurrentElementOffList(testMethodParameters[i])) {
					testMethodParameters[i] = currentElement;
				}
			}
			if ((Boolean) new Agents(testObject, testMethodName, testMethodParametersType).call(testMethodParameters)) {
				if (replaceActionObjectByCurrentElement)
					actionObject = currentElement;
				for (int i = 0; i < actionMethodParameters.length; i++) {
					if (isToBeReplacedByCurrentElementOffList(actionMethodParameters[i])) {
						actionMethodParameters[i] = currentElement;
					}
				}
				new Agents(actionObject, actionMethodName, actionMethodParametersType).call(actionMethodParameters);
			}
		}
	}
	
	/**
	 * Parcourrir la liste {@code list} à partir du début et exécuter dynamiquement une action jusqu'à ce que une condition de fin soit
	 * rencontrée.<br>
	 * Une action est définie par une méthode {@code actionMethodName} portant sur un objet cible {@code actionObject}.<br>
	 * La condition de fin est définie par une méthode {@code conditionMethodName} portant sur un objet cible {@code conditionObject}.
	 * <br>
	 * Like: for(Object object: list) conditionObject.conditionMethod()? return: actionObject.actionMethod()
	 * 
	 * @param list Liste à parcourrir
	 * @param actionObject Objet cible; utiliser le Patern "?" pour que l'objet cible soit l'élément courant de la liste parcourrue
	 * @param actionMethodName Nom de la méthode à exécuter
	 * @param conditionObject Objet condition cible; utiliser le Patern "?" pour que l'objet condition cible soit l'élément courant de
	 *        la liste parcourrue
	 * @param conditionMethodName Nom de la méthode condition de fin à rencontrer
	 */
	public static void doUntil(List<?> list, Object actionObject, String actionMethodName, Object conditionObject,
			String conditionMethodName) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (conditionObject == null) {
			throw new IllegalArgumentException("conditionObject must be non-null");
		}
		if (conditionMethodName == null || conditionMethodName.isEmpty()) {
			throw new IllegalArgumentException("conditionMethodName must be a non-null non-empty string");
		}
		boolean replaceConditionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(conditionObject);
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceConditionObjectByCurrentElement)
				conditionObject = currentElement;
			if ((Boolean) new Agents(conditionObject, conditionMethodName).call()) {
				if (replaceActionObjectByCurrentElement)
					actionObject = currentElement;
				new Agents(actionObject, actionMethodName).call();
			} else {
				return;
			}
		}
	}
	
	public static void doUntil(List<?> list, Object actionObject, String actionMethodName, Object conditionObject,
			String conditionMethodName, Class<?>[] conditionMethodParametersType, Object[] conditionMethodParameters) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (conditionObject == null) {
			throw new IllegalArgumentException("conditionObject must be non-null");
		}
		if (conditionMethodName == null || conditionMethodName.isEmpty()) {
			throw new IllegalArgumentException("conditionMethodName must be a non-null non-empty string");
		}
		if (conditionMethodParametersType == null) {
			throw new IllegalArgumentException("conditionMethodParametersType must be non-null");			
		}
		if (conditionMethodParameters == null) {
			throw new IllegalArgumentException("conditionMethodParameters must be non-null");			
		}
		boolean replaceConditionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(conditionObject);
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceConditionObjectByCurrentElement)
				conditionObject = currentElement;
			for (int i = 0; i < conditionMethodParameters.length; i++) {
				if (isToBeReplacedByCurrentElementOffList(conditionMethodParameters[i])) {
					conditionMethodParameters[i] = currentElement;
				}
			}
			if ((Boolean) new Agents(conditionObject, conditionMethodName, conditionMethodParametersType)
					.call(conditionMethodParameters)) {
				if (replaceActionObjectByCurrentElement)
					actionObject = currentElement;
				new Agents(actionObject, actionMethodName).call();
			} else {
				return;
			}
		}
	}
	
	public static void doUntil(List<?> list, Object actionObject, String actionMethodName, Class<?>[] actionMethodParametersType,
			Object[] actionMethodParameters, Object conditionObject, String conditionMethodName) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (actionMethodParametersType == null) {
			throw new IllegalArgumentException("actionMethodParametersType must be non-null");			
		}
		if (actionMethodParameters == null) {
			throw new IllegalArgumentException("actionMethodParameters must be non-null");			
		}
		if (conditionObject == null) {
			throw new IllegalArgumentException("conditionObject must be non-null");
		}
		if (conditionMethodName == null || conditionMethodName.isEmpty()) {
			throw new IllegalArgumentException("conditionMethodName must be a non-null non-empty string");
		}
		boolean replaceConditionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(conditionObject);
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceConditionObjectByCurrentElement)
				conditionObject = currentElement;
			if ((Boolean) new Agents(conditionObject, conditionMethodName).call()) {
				if (replaceActionObjectByCurrentElement)
					actionObject = currentElement;
				for (int i = 0; i < actionMethodParameters.length; i++) {
					if (isToBeReplacedByCurrentElementOffList(actionMethodParameters[i])) {
						actionMethodParameters[i] = currentElement;
					}
				}
				new Agents(actionObject, actionMethodName, actionMethodParametersType).call(actionMethodParameters);
			} else {
				return;
			}
		}
	}
	
	public static void doUntil(List<?> list, Object actionObject, String actionMethodName, Class<?>[] actionMethodParametersType,
			Object[] actionMethodParameters, Object conditionObject, String conditionMethodName,
			Class<?>[] conditionMethodParametersType, Object[] conditionMethodParameters) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (actionObject == null) {
			throw new IllegalArgumentException("actionObject must be non-null");
		}
		if (actionMethodName == null || actionMethodName.isEmpty()) {
			throw new IllegalArgumentException("actionMethodName must be a non-null non-empty string");
		}
		if (actionMethodParametersType == null) {
			throw new IllegalArgumentException("actionMethodParametersType must be non-null");			
		}
		if (actionMethodParameters == null) {
			throw new IllegalArgumentException("actionMethodParameters must be non-null");			
		}
		if (conditionObject == null) {
			throw new IllegalArgumentException("conditionObject must be non-null");
		}
		if (conditionMethodName == null || conditionMethodName.isEmpty()) {
			throw new IllegalArgumentException("conditionMethodName must be a non-null non-empty string");
		}
		if (conditionMethodParametersType == null) {
			throw new IllegalArgumentException("conditionMethodParametersType must be non-null");			
		}
		if (conditionMethodParameters == null) {
			throw new IllegalArgumentException("conditionMethodParameters must be non-null");			
		}
		boolean replaceConditionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(conditionObject);
		boolean replaceActionObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(actionObject);
		for (Object currentElement : list) {
			if (replaceConditionObjectByCurrentElement)
				conditionObject = currentElement;
			for (int i = 0; i < conditionMethodParameters.length; i++) {
				if (isToBeReplacedByCurrentElementOffList(conditionMethodParameters[i])) {
					conditionMethodParameters[i] = currentElement;
				}
			}
			if ((Boolean) new Agents(conditionObject, conditionMethodName, conditionMethodParametersType)
					.call(conditionMethodParameters)) {
				if (replaceActionObjectByCurrentElement)
					actionObject = currentElement;
				for (int i = 0; i < actionMethodParameters.length; i++) {
					if (isToBeReplacedByCurrentElementOffList(actionMethodParameters[i])) {
						actionMethodParameters[i] = currentElement;
					}
				}
				new Agents(actionObject, actionMethodName, actionMethodParametersType).call(actionMethodParameters);
			} else {
				return;
			}
		}
	}
	
	/**
	 * Parcourrir la liste {@code list} du début à la fin et vérifier dynamiquement si le test est satisfait pour chacun des éléments
	 * de la liste.<br>
	 * Le test est défini par une méthode {@code testMethodName} portant sur un objet cible {@code testObject}.<br>
	 * Like: for(Object object: list) if(testObject.testMethod()) actionObject.actionMethod()
	 * 
	 * Is `a_test' true for all items?
	 * -- (Semantics not guaranteed if `a_test' changes the structure.)
	 * 
	 * @param list Liste à parcourrir
	 * @param testObject Objet test cible; utiliser le Patern "?" pour que l'objet test cible soit l'élément courant de la liste
	 *        parcourrue
	 * @param testMethodName Nom de la méthode test à satisfaire
	 * @return Résultat {@code true} ssi le test est satisfait pour chacun des éléments de la liste
	 */
	public static boolean forAll(List<?> list, Object testObject, String testMethodName) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (testObject == null) {
			throw new IllegalArgumentException("testObject must be non-null");
		}
		if (testMethodName == null || testMethodName.isEmpty()) {
			throw new IllegalArgumentException("testMethodName must be a non-null non-empty string");
		}
		boolean replaceTestObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(testObject);
		for (Object currentElement : list) {
			if (replaceTestObjectByCurrentElement)
				testObject = currentElement;
			if (!(Boolean) new Agents(testObject, testMethodName).call()) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean forAll(List<?> list, Object testObject, String testMethodName, Class<?>[] testMethodParametersType,
			Object[] testMethodParameters) {
		if (list == null) {
			throw new IllegalArgumentException("list must be non-null");
		}
		if (testObject == null) {
			throw new IllegalArgumentException("testObject must be non-null");
		}
		if (testMethodName == null || testMethodName.isEmpty()) {
			throw new IllegalArgumentException("testMethodName must be a non-null non-empty string");
		}
		if (testMethodParametersType == null) {
			throw new IllegalArgumentException("testMethodParametersType must be non-null");			
		}
		if (testMethodParameters == null) {
			throw new IllegalArgumentException("testMethodParameters must be non-null");			
		}
		boolean replaceTestObjectByCurrentElement = isToBeReplacedByCurrentElementOffList(testObject);
		for (Object currentElement : list) {
			if (replaceTestObjectByCurrentElement)
				testObject = currentElement;
			for (int i = 0; i < testMethodParameters.length; i++) {
				if (isToBeReplacedByCurrentElementOffList(testMethodParameters[i])) {
					testMethodParameters[i] = currentElement;
				}
			}
			if (!(Boolean) new Agents(testObject, testMethodName, testMethodParametersType).call(testMethodParameters)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isToBeReplacedByCurrentElementOffList(Object object) {
		return PATTERN_TO_USE_CURRENT_ELEMENT_OFF_LIST.equals(object);
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
	
	private String FormatErrorMsg(String message, Class<?>[] methodParameterTypes, Exception ex) {
		String errorMsg = "";
		
		String methodParamTypes = "";
		for (Class<?> clazz : methodParameterTypes)
			methodParamTypes += clazz.getName() + ", ";
		methodParamTypes.substring(0, methodParamTypes.length() - 2);
		
		errorMsg = message +
				" of " + object.getClass().getName() + "'s Method " + methodName + "(" + methodParamTypes + ")" + "\n" +
				" => Message: " + ex.getClass() + " " + ex.getMessage();
		if (ex.getCause() != null)
			errorMsg = errorMsg + "\n" +
					"    Caused by: " + ex.getCause().getClass() + " " + ex.getCause().getMessage();
		return errorMsg;
	}
	
	private Method findMethodIncludingInherited(Class<?> currentClass) throws NoSuchMethodException {
		return findMethodIncludingInherited(currentClass, null);
	}
	
	private Method findMethodIncludingInherited(Class<?> currentClass, Class<?>[] methodParameterTypes) throws NoSuchMethodException {
		Method result = null;
		NoSuchMethodException lastException = null;
		while (currentClass != null) {
			lastException = null;
			try {
				// Recherche parmis l'ensemble des méthodes "public" de la classe et super classe.
				result = currentClass.getMethod(methodName, methodParameterTypes);
				break;
			} catch (NoSuchMethodException e) {
				try {
					// Recherche parmis l'ensemble des méthodes "private, protected et public" de la classe.
					result = currentClass.getDeclaredMethod(methodName, methodParameterTypes);
					result.setAccessible(true);
					break;
					
				} catch (NoSuchMethodException e2) {
					lastException = e2;
					currentClass = currentClass.getSuperclass();
				}
			}
		}
		if (result == null && lastException != null)
			throw lastException;
			
		return result;
	}
	
	private Field findFieldIncludingInherited(Class<?> currentClass) throws NoSuchFieldException {
		Field result = null;
		NoSuchFieldException lastException = null;
		while (currentClass != null) {
			lastException = null;
			try {
				// Recherche parmis l'ensemble des champs "public" de la classe et super classe.
				result = currentClass.getField(methodName);
				break;
			} catch (NoSuchFieldException e) {
				try {
					// Recherche parmis l'ensemble des champs "private, protected et public" de la classe.
					result = currentClass.getDeclaredField(methodName);
					result.setAccessible(true);
					break;
					
				} catch (NoSuchFieldException e2) {
					lastException = e2;
					currentClass = currentClass.getSuperclass();
				}
			}
		}
		if (result == null && lastException != null)
			throw lastException;
			
		return result;
	}
	
	private void resetError() {
		isError = false;
	}
	
	private void setError() {
		isError = true;
	}
	
}
// $Source: F:/CVSRoot5/COMPOSANTS/src/composants/support/agents/Agents.java,v $
