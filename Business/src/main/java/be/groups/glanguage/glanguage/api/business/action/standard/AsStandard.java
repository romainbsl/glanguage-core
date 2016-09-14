package be.groups.glanguage.glanguage.api.business.action.standard;

import java.time.LocalDate;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.analysis.IdentifierParameterList;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaAnomaly;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaIn;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaRuleReference;
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
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMax;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMin;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMax;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMin;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatString;
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
	public void beginAnalysis() {
		initialize();
	}
	
	@Override
	public LocalDate createDate(int d, int m, int y) {
		return LocalDate.of(y, m, d);
	}
	
	@Override
	public AbstractFormula unaryOperation(FormulaDescription formulaDescription, AbstractFormula formula) {
		AbstractFormula result = null;
		switch (formulaDescription) {
			case OP_NOT:
				result = new FormulaNot(formula);
				break;
			case OP_UNARY_PLUS:
				result = formula;
				break;
			case OP_UNARY_MINUS:
				result = new FormulaUnaryMinus(formula);
				break;
			case OP_EXIST:
				result = new FormulaExist(formula);
				break;
			default:
				throw new InternalError("Internal error : unknown unary formula type : " + formulaDescription.getId());
		}
		return result;
	}
	
	
	@Override
	public AbstractFormula binaryOperation(FormulaDescription formulaDescription, AbstractFormula formula1, AbstractFormula formula2) {
		AbstractFormula result = null;
		switch (formulaDescription) {
			case OP_MULTIPLY:
				result = new FormulaMultiply(formula1, formula2);
				break;
			case OP_DIVIDE:
				result = new FormulaDivide(formula1, formula2);
				break;
			case OP_INTEGER_DIVISION:
				result = new FormulaIntegerDivision(formula1, formula2);
				break;
			case OP_MODULO:
				result = new FormulaModulo(formula1, formula2);
				break;
			case OP_PLUS:
				result = new FormulaPlus(formula1, formula2);
				break;
			case OP_MINUS:
				result = new FormulaMinus(formula1, formula2);
				break;
			case OP_EQUAL:
				result = new FormulaEqual(formula1, formula2);
				break;
			case OP_DIFFERENCE:
				result = new FormulaDifference(formula1, formula2);
				break;
			case OP_SMALLER:
				result = new FormulaSmaller(formula1, formula2);
				break;
			case OP_SMALLER_OR_EQUAL:
				result = new FormulaSmallerOrEqual(formula1, formula2);
				break;
			case OP_GREATER:
				result = new FormulaGreater(formula1, formula2);
				break;
			case OP_GREATER_OR_EQUAL:
				result = new FormulaGreaterOrEqual(formula1, formula2);
				break;
			case OP_AND:
				result = new FormulaAnd(formula1, formula2);
				break;
			case OP_OR:
				result = new FormulaOr(formula1, formula2);
				break;
			default:
				throw new InternalError("Internal error : unknown binary formula type : " + formulaDescription.getId());
		}
		return result;
	}
	
	@Override
	public AbstractFormula inOperation(AbstractFormula element, LinkedList<AbstractFormula> inList) {
		return new FormulaIn(element, inList);
	}
	
	@Override
	public AbstractFormula referenceFormula(String ruleId) {
		return new FormulaRuleReference(ruleId);
	}
	
	@Override
	public AbstractFormula terminalIntegerFormula(String s) {
		return new FormulaTerminalInteger(s);
	}
	
	@Override
	public AbstractFormula terminalNumericFormula(String s) {
		return new FormulaTerminalNumeric(s);
	}
	
	@Override
	public AbstractFormula terminalDateFormula(LocalDate d) {
		return new FormulaTerminalDate(d);
	}
	
	@Override
	public AbstractFormula terminalDurationFormula(String duration) {
		return new FormulaTerminalDuration(duration);
	}
	
	@Override
	public AbstractFormula terminalBooleanFormula(boolean b) {
		return new FormulaTerminalBoolean(b);
	}
	
	@Override
	public AbstractFormula terminalStringFormula(String s) {
		return new FormulaTerminalString(s);
	}
	
	@Override
	public AbstractFormula emptyFormula() {
		return null;
	}
	
	@Override
	public AbstractFormula standardFunction(FormulaDescription formulaDescription, LinkedList<AbstractFormula> parameters) {
		switch (formulaDescription) {
			case F_CEIL:
				return new FormulaRoundingCeil(parameters.get(0), parameters.get(1));
			case F_FLOOR:
				return new FormulaRoundingFloor(parameters.get(0), parameters.get(1));
			case F_ROUNDED:
				return new FormulaRoundingArithmetic(parameters.get(0), parameters.get(1));
			case F_TRUNC:
				return new FormulaRoundingTrunc(parameters.get(0), parameters.get(1));
			case F_BANKERS_ROUNDED:
				return new FormulaRoundingBankers(parameters.get(0), parameters.get(1));
			case F_FORMAT_DATE:
				return new FormulaFormatDate(parameters);
			case F_FORMAT_INTEGER:
				return new FormulaFormatInteger(parameters);
			case F_FORMAT_NUMERIC:
				return new FormulaFormatNumeric(parameters);
			case F_FORMAT_STRING:
				return new FormulaFormatString(parameters);
			case F_STRING_ITEM:
				return new FormulaStringItem(parameters);
			case F_SUBSTRING:
				return new FormulaSubString(parameters);
			case F_MAX:
				return new FormulaExtremumMax(parameters);
			case F_MIN:
				return new FormulaExtremumMin(parameters);
			case F_SMAX:
				return new FormulaExtremumSignedMax(parameters);
			case F_SMIN:
				return new FormulaExtremumSignedMin(parameters);
			case F_DATE:
				return new FormulaDate(parameters);
//			case F_MINUTES:
//			case F_HOURS:
//			case F_DAYS:
//			case F_MONTHS:
//			case F_YEARS:
//				result = new FormuleFonctionDuree(cf, parameters);
//				break;
			case F_ABS:
				return new FormulaMathAbs(parameters);
			case F_SIGN:
				return new FormulaMathSign(parameters);
			case F_PUT_TEXT:
				return new FormulaAnomaly(parameters);
			case F_STRING_LENGTH:
				return new FormulaStringLength(parameters);
			default:
				throw new InternalError("Internal error : unknown standard function formula type : " + formulaDescription.getId());
		}
	}
	
	@Override
	public AbstractFormula fonctionGroupe(EnumerationSLangage cf, String nomGroupe) {
		AbstractFormula result = null;
		switch (cf) {
			case F_MULT:
			case F_SUM:
			case F_SUMV:
				result = new FormuleFonctionGroupe(cf, nomGroupe);
				break;
			default:
				logger.error("Erreur lors de l'évaluation : Group function code " + cf + " " + DefinitionsSLangage.nomFonction(cf));
				break;
		}
		return result;
	}
	
	@Override
	public AbstractFormula fonctionGet(EnumerationTypeSLangage type, ListeIdentifiantParametre liste) {
		return new FormuleGet(type, liste.identifiants, liste.parametres);
	}
	
	@Override
	public AbstractFormula appelApplicable(String code) {
		return new AppelApplicable(code);
	}
	
	@Override
	public AbstractFormula appelFormule(String code) {
		return new AppelFormule(code);
	}
	
	@Override
	public AbstractFormula instructionIf(Formule cond, AbstractFormula si, AbstractFormula sinon) {
		return new InstructionIf(cond, si, sinon);
	}
	
	@Override
	/**
	 * Verifier que la chaine 'n' contient bien un entier positif
	 * et que 'a' <= 'n', puis que 'n' <= 'b' (si b > 0)
	 */
	@Requires("n != null && Integer.parseInt(n) == new Integer(n)")
	public int verifierEntier(String n, int a, int b) {
		int result = new Integer(n);
		if (result < a || (b > 0 && result > b)) {
			logger.error("L'entier '" + n + "' n'est pas dans l'interval [" + a + "," + b + "]");
		}
		return result;
	}

	@Override
	public int checkInteger(String n, int min, int max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractFormula groupFunction(FormulaDescription formulaDescription, String groupName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractFormula getFunction(FormulaReturnType returnType, IdentifierParameterList list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractFormula applicabiltyCall(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractFormula formulaCall(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractFormula ifInstruction(AbstractFormula condition, AbstractFormula ifStatement, AbstractFormula elseStatement) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
