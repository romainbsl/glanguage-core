package be.groups.glanguage.glanguage.api.business.action;

import be.groups.glanguage.glanguage.api.business.analysis.IdentifierParameterList;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.*;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Set of semantical actions applicable during analysis
 */
public interface SemanticalAction {

	/**
	 * @return the formula resulting of the analysis
	 */
	AbstractFormula getFormula();

	/**
	 * @param formula the formula to set
	 */
	void setFormula(AbstractFormula formula);
	
	/** Initialize the set of semantical actions */
	void initialize();
	
	/** End analysis */
	void endAnalysis();
	
	/** Begin analysis */
	void beginAnalysis();

	/**
	 * Creation of a date
	 * 
	 * @param d An integer representing a day of month
	 * @param m An integer representing a month of year
	 * @param y An integer representing a year
	 * @return A LocalDate representing the date of day {@code d} of month {@code m} of year {@code y}
	 */
	LocalDate createDate(int d, int m, int y);
	
	/**
	 * Check that {@code n} String contains a positive integer between {@code min} and {@code max} inclusively if {@code max} is
	 * positive or greater or equal to {@code min} if {@code max} is negative
	 * 
	 * @param n The String to check
	 * @param min The lower limit of the range within which the integer respresented by {@code n} must be
	 * @param max If positive, the upper limit of the range within which the integer respresented by {@code n} must be. A negative
	 *        value means there is no upper limit
	 * @return The integer represented by n
	 */
	int checkInteger(String n, int min, int max);
	
	/**
	 * "Unary operation" node of type {@code opType} applied to {@link AbstractFormula} {@code formula}
	 * 
	 * @param formulaDescriptionId The type of unary operation to apply to {@code formula}
	 * @param formula The {@link AbstractFormula} on which the unary operation has to be applied
	 * @return A unary operation {@link AbstractFormula} of type {@code formulaType} with {@code formula1} as parameter
	 */
	AbstractFormula unaryOperation(FormulaType formulaDescriptionId, AbstractFormula formula);
	
	/**
	 * "Binary operation" node of type {@code opType} applied to {@link AbstractFormula}'s {@code formula1} and {@code formula2}
	 * 
	 * @param formulaDescriptionId The type of unary operation to apply to {@code formula1} and {@code formula2}
	 * @param formula1 The first {@link AbstractFormula} on which the binary operation has to be applied
	 * @param formula2 The second {@link AbstractFormula} on which the binary operation has to be applied
	 * @return A binary operation {@link AbstractFormula} of type {@code formulaType} with {@code formula1} as first parameter and
	 *         {@code formula2} as second parameter
	 */
	AbstractFormula binaryOperation(FormulaType formulaDescriptionId, AbstractFormula formula1, AbstractFormula formula2);
	
	/**
	 * "In operation" node returning a boolean value whether the result of {@link AbstractFormula} formula is contained in {@code list}
	 * or not
	 * 
	 * @param formula The formula to check if the result is - or is not - in the {@code list}
	 * @param list The list of values in which to check if the result of {@code formula} is - or is not - contained
	 * @return A {@link AbstractFormula}
	 */
	AbstractFormula inOperation(AbstractFormula formula, LinkedList<AbstractFormula> list);
	
	/**
	 * "Bracket formula" node 
	 *
	 * @param formula The formula to be encapsulated by a "bracket formula"
	 * @return A {@link be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaBracket}
	 * encapsulating {@code formula}
	 */
	AbstractFormula bracketFormula(AbstractFormula formula);
	
	/**
	 * "Reference to a rule" node
	 * 
	 * @param name The name of the rule referenced
	 * @return A {@link AbstractFormula}
	 */
	AbstractFormula referenceFormula(String name);
	
	/**
	 * "Terminal integer formula" node
	 * 
	 * @param s A String representing an Integer
	 * @return A {@link FormulaTerminalInteger}
	 */
	// @Requires ("s != null") // && s is an integer
	AbstractFormula terminalIntegerFormula(String s);
	
	/**
	 * "Terminal numeric formula" node
	 * 
	 * @param s A String representing a Double
	 * @return A {@link FormulaTerminalNumeric}
	 */
	// @Requires ("s != null") // && s is a double
	AbstractFormula terminalNumericFormula(String s);
	
	/**
	 * "Terminal date formula" node
	 * 
	 * @param d A date
	 * @return A {@link FormulaTerminalDate}
	 */
	// @Requires ("d != null")
	AbstractFormula terminalDateFormula(LocalDate d);
	
	 /**
	 * Terminal duration formula" node
	 *
	 * @param duration A String representing a duration
	 * @return A {@link FormulaTerminalDuration}
	 */
	 // @Requires ("duree != null")
	 AbstractFormula terminalDurationFormula(String duration);
	
	/**
	 * "Terminal boolean formula" node
	 * 
	 * @param b A boolean
	 * @return A {@link FormulaTerminalBoolean}
	 */
	AbstractFormula terminalBooleanFormula(boolean b);
	
	/**
	 * "Terminal string formula" node
	 * 
	 * @param s A String
	 * @return A {@link FormulaTerminalString}
	 */
	AbstractFormula terminalStringFormula(String s);
	
	/**
	 * "Empty formula" node
	 * 
	 * @return null
	 */
	AbstractFormula emptyFormula();
	
	/**
	 * "Standard function" node
	 * 
	 * @param formulaDescriptionId The type of formula to create
	 * @param parameters The parameters of the formula to create
	 * @return A standard function {@link AbstractFormula} of type {@code formulaType} with {@code parameters}
	 */
	AbstractFormula standardFunction(FormulaType formulaDescriptionId, LinkedList<AbstractFormula> parameters);
	
	/**
	 * "Standard group function" node
	 * 
	 * @param formulaDescriptionId The type of formula to create
	 * @param groupName The name identifying the group
	 * @return A standard group function {@link AbstractFormula} of type {@code formulaType} on a group identified by {@code groupName}
	 */
	AbstractFormula groupFunction(FormulaType formulaDescriptionId, String groupName);
	
	
	/**
	 * "Get function" node
	 * 
	 * @param returnType The type of the result of the formula to create
	 * @param list The sequence of methods to be called in order to retrieve the value of the formula to create
	 * @return A {@link FormulaGet} that would return a result of type {@code returnType} by calling the methods sequence described in
	 *         the {@code list}
	 */
	AbstractFormula getFunction(FormulaReturnType returnType, IdentifierParameterList list);
	
	
	/**
	 * Value of the applicability condition of the rule identified by {@code code}
	 * 
	 * @param code The code of the rule
	 * @return An {@link AbstractFormula} representing the call to the applicability condition of the rule identified by {@code code}
	 */
	AbstractFormula applicabiltyCall(String code);
	
	/**
	 * Value of the formula of the rule identified by {@code code}
	 * 
	 * @param code The code of the rule
	 * @return An {@link AbstractFormula} representing the call to the formula of the rule identified by {@code code}
	 */
	AbstractFormula formulaCall(String code);
	
	/**
	 * "If instruction" node
	 * Creer un noeud d'instruction {@code if}
	 * {@code cond} represente une formule booleenne,
	 * {@code si} et {@code sinon} sont de meme type.
	 * 
	 * @param condition The condition to be checked
	 * @param ifStatement The formula to evaluate if the {@code condition} is true
	 * @param elseStatement The formula to evaluate if the {@code condition} is false
	 */
	AbstractFormula ifInstruction(AbstractFormula condition, AbstractFormula ifStatement, AbstractFormula elseStatement);
	
}
