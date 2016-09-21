package be.groups.glanguage.glanguage.api.business.action.standard;

import java.time.LocalDate;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.analysis.IdentifierParameterList;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaAnomaly;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaBracket;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaIn;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaAnd;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDifference;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDivide;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaEqual;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreater;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreaterOrEqual;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaIntegerDivision;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMinus;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaModulo;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMultiply;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaOr;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaPlus;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmaller;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmallerOrEqual;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaApplicability;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaFormula;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaRuleReference;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationDays;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationHours;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationMinutes;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationMonths;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationYears;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMax;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMin;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMax;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMin;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.group.FormulaGroupMultiply;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction.FormulaIfInstruction;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathAbs;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathSign;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingArithmetic;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingBankers;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingCeil;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingFloor;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingTrunc;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringItem;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringLength;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaSubString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalBoolean;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDuration;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaExist;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaNot;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaUnaryMinus;

/**
 * Set of semantical actions applicable during analysis
 */
public class AsStandard implements SemanticalAction {
	
	static Logger logger = LoggerFactory.getLogger(Class.class);
	
	/**
	 * Result of the analysis
	 */
	private LinkedList<AbstractFormula> formulaList;
	
	/**
	 * @return the formulaList
	 */
	@Override
	public LinkedList<AbstractFormula> getFormulaList() {
		return formulaList;
	}
	
	@Override
	public void initialize() {
		this.formulaList = new LinkedList<>();
	}
	
	@Override
	public void endAnalysis() {
		// do nothing
	}
	
	@Override
	public void beginAnalysis() {}
	
	@Override
	public LocalDate createDate(int d, int m, int y) {
		return LocalDate.of(y, m, d);
	}
	
	@Override
	public AbstractFormula unaryOperation(FormulaType formulaDescriptionId, AbstractFormula formula) {
		AbstractFormula result = null;
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(formulaDescriptionId);
		switch (formulaDescriptionId) {
			case OP_NOT:
				result = new FormulaNot(formulaDescription, formula);
				break;
			case OP_UNARY_PLUS:
				result = formula;
				break;
			case OP_UNARY_MINUS:
				result = new FormulaUnaryMinus(formulaDescription, formula);
				break;
			case OP_EXIST:
				result = new FormulaExist(formulaDescription, formula);
				break;
			default:
				throw new InternalError("Internal error : unknown unary formula type : " + formulaDescriptionId);
		}
		return result;
	}
	
	
	@Override
	public AbstractFormula binaryOperation(FormulaType formulaDescriptionId, AbstractFormula formula1,
			AbstractFormula formula2) {
		AbstractFormula result = null;
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(formulaDescriptionId);
		switch (formulaDescriptionId) {
			case OP_MULTIPLY:
				result = new FormulaMultiply(formulaDescription, formula1, formula2);
				break;
			case OP_DIVIDE:
				result = new FormulaDivide(formulaDescription, formula1, formula2);
				break;
			case OP_INTEGER_DIVISION:
				result = new FormulaIntegerDivision(formulaDescription, formula1, formula2);
				break;
			case OP_MODULO:
				result = new FormulaModulo(formulaDescription, formula1, formula2);
				break;
			case OP_PLUS:
				result = new FormulaPlus(formulaDescription, formula1, formula2);
				break;
			case OP_MINUS:
				result = new FormulaMinus(formulaDescription, formula1, formula2);
				break;
			case OP_EQUAL:
				result = new FormulaEqual(formulaDescription, formula1, formula2);
				break;
			case OP_DIFFERENCE:
				result = new FormulaDifference(formulaDescription, formula1, formula2);
				break;
			case OP_SMALLER:
				result = new FormulaSmaller(formulaDescription, formula1, formula2);
				break;
			case OP_SMALLER_OR_EQUAL:
				result = new FormulaSmallerOrEqual(formulaDescription, formula1, formula2);
				break;
			case OP_GREATER:
				result = new FormulaGreater(formulaDescription, formula1, formula2);
				break;
			case OP_GREATER_OR_EQUAL:
				result = new FormulaGreaterOrEqual(formulaDescription, formula1, formula2);
				break;
			case OP_AND:
				result = new FormulaAnd(formulaDescription, formula1, formula2);
				break;
			case OP_OR:
				result = new FormulaOr(formulaDescription, formula1, formula2);
				break;
			default:
				throw new InternalError("Internal error : unknown binary formula type : " + formulaDescriptionId);
		}
		return result;
	}
	
	@Override
	public AbstractFormula bracketFormula(AbstractFormula formula) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS);
		return new FormulaBracket(formulaDescription, formula);
	}
	
	@Override
	public AbstractFormula inOperation(AbstractFormula element, LinkedList<AbstractFormula> inList) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.F_IN);
		return new FormulaIn(formulaDescription, element, inList);
	}
	
	@Override
	public AbstractFormula referenceFormula(String ruleId) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_RULE_REFERENCE);
		return new FormulaRuleReference(formulaDescription, ruleId);
	}
	
	@Override
	public AbstractFormula terminalIntegerFormula(String s) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER);
		return new FormulaTerminalInteger(formulaDescription, s);
	}
	
	@Override
	public AbstractFormula terminalNumericFormula(String s) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC);
		return new FormulaTerminalNumeric(formulaDescription, s);
	}
	
	@Override
	public AbstractFormula terminalDateFormula(LocalDate d) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE);
		return new FormulaTerminalDate(formulaDescription, d);
	}
	
	@Override
	public AbstractFormula terminalDurationFormula(String duration) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION);
		return new FormulaTerminalDuration(formulaDescription, duration);
	}
	
	@Override
	public AbstractFormula terminalBooleanFormula(boolean b) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN);
		return new FormulaTerminalBoolean(formulaDescription, b);
	}
	
	@Override
	public AbstractFormula terminalStringFormula(String s) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING);
		return new FormulaTerminalString(formulaDescription, s);
	}
	
	@Override
	public AbstractFormula emptyFormula() {
		return null;
	}
	
	@Override
	public AbstractFormula standardFunction(FormulaType formulaDescriptionId, LinkedList<AbstractFormula> parameters) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(formulaDescriptionId);
		FormulaDescription precisionFormulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER);
		switch (formulaDescriptionId) {
			case F_CEIL:
				return new FormulaRoundingCeil(formulaDescription, precisionFormulaDescription, parameters.get(0), parameters.get(1));
			case F_FLOOR:
				return new FormulaRoundingFloor(formulaDescription, precisionFormulaDescription, parameters.get(0), parameters.get(1));
			case F_ROUNDED:
				return new FormulaRoundingArithmetic(formulaDescription, precisionFormulaDescription, parameters.get(0), parameters.get(1));
			case F_TRUNC:
				return new FormulaRoundingTrunc(formulaDescription, precisionFormulaDescription, parameters.get(0), parameters.get(1));
			case F_BANKERS_ROUNDED:
				return new FormulaRoundingBankers(formulaDescription, precisionFormulaDescription, parameters.get(0), parameters.get(1));
			case F_FORMAT_DATE:
				return new FormulaFormatDate(formulaDescription, parameters);
			case F_FORMAT_INTEGER:
				return new FormulaFormatInteger(formulaDescription, parameters);
			case F_FORMAT_NUMERIC:
				return new FormulaFormatNumeric(formulaDescription, parameters);
			case F_FORMAT_STRING:
				return new FormulaFormatString(formulaDescription, parameters);
			case F_STRING_ITEM:
				return new FormulaStringItem(formulaDescription, parameters);
			case F_SUBSTRING:
				return new FormulaSubString(formulaDescription, parameters);
			case F_MAX:
				return new FormulaExtremumMax(formulaDescription, parameters);
			case F_MIN:
				return new FormulaExtremumMin(formulaDescription, parameters);
			case F_SMAX:
				return new FormulaExtremumSignedMax(formulaDescription, parameters);
			case F_SMIN:
				return new FormulaExtremumSignedMin(formulaDescription, parameters);
			case F_DATE:
				return new FormulaDate(formulaDescription, parameters);
			case F_MINUTES:
				return new FormulaDurationMinutes(formulaDescription, parameters);
			case F_HOURS:
				return new FormulaDurationHours(formulaDescription, parameters);
			case F_DAYS:
				return new FormulaDurationDays(formulaDescription, parameters);
			case F_MONTHS:
				return new FormulaDurationMonths(formulaDescription, parameters);
			case F_YEARS:
				return new FormulaDurationYears(formulaDescription, parameters);
			case F_ABS:
				return new FormulaMathAbs(formulaDescription, parameters);
			case F_SIGN:
				return new FormulaMathSign(formulaDescription, parameters);
			case F_PUT_TEXT:
				return new FormulaAnomaly(formulaDescription, parameters);
			case F_STRING_LENGTH:
				return new FormulaStringLength(formulaDescription, parameters);
			default:
				throw new InternalError("Internal error : unknown standard function formula type : " + formulaDescriptionId);
		}
	}
	
	@Override
	public AbstractFormula groupFunction(FormulaType formulaDescriptionId, String groupName) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(formulaDescriptionId);
		switch (formulaDescriptionId) {
			case G_MULT:
				return new FormulaGroupMultiply(formulaDescription, groupName);
			case G_SUM:
				return new FormulaGroupMultiply(formulaDescription, groupName);
			case G_SUMV:
				return null; // TODO
			default:
				throw new InternalError("Internal error : unknown group function formula type : " + formulaDescriptionId);
		}
	}
	
	@Override
	public AbstractFormula getFunction(FormulaReturnType returnType, IdentifierParameterList identifierParameterlist) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_GET);
		FormulaDescription subFormulasDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE);
		return new FormulaGet(formulaDescription, subFormulasDescription, returnType, identifierParameterlist.getIdentifiers(),
				identifierParameterlist.getParameters());
	}
	
	@Override
	public AbstractFormula applicabiltyCall(String code) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_APPLICABILITY);
		return new FormulaApplicability(formulaDescription, code);
	}
	
	@Override
	public AbstractFormula formulaCall(String code) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_FORMULA);
		return new FormulaFormula(formulaDescription, code);
	}
	
	@Override
	public AbstractFormula ifInstruction(AbstractFormula condition, AbstractFormula ifStatement, AbstractFormula elseStatement) {
		FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.I_IF);
		return new FormulaIfInstruction(formulaDescription, condition, ifStatement, elseStatement);
	}
	
	@Override
	public int checkInteger(String n, int min, int max) {
		if (n == null) {
			throw new IllegalArgumentException("integer parameter must be non-null");
		}
		try {
			int result = new Integer(n);
			if (result < min || (max > 0 && result > max)) {
				throw new IllegalArgumentException(
						"integer '" + n + "' is out of the bounds [" + min + "," + (max > 0 ? max : Integer.MAX_VALUE) + "]");
			}
			return result;
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("parameter must represent an integer : " + n);
		}
	}
	
}
