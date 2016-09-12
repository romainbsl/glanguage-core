package be.groups.glanguage.glanguage.api.business.action.standard;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;
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
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalBoolean;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDate;
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
	
	@Override
	public void initialize() {
		// do_nothing
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
	public AbstractFormula unaryOperation(FormulaType formulaType, AbstractFormula formula) {
		AbstractFormula result = null;
		switch (formulaType) {
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
				logger.error("Internal error : unknown formula type : " + formulaType.getValue());
				break;
		}
		return result;
	}
	
	
	@Override
	public AbstractFormula binaryOperation(FormulaType formulaType, AbstractFormula formula1, AbstractFormula formula2) {
		AbstractFormula result = null;
		switch (formulaType) {
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
				logger.error("Erreur interne: code operateur binaire inconnu");
				break;
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
	
//	@Override
//	public AbstractFormula formuleConstanteDuree(String duree) {
//		SDuration d;
//		int i = 0;
//		int k = 0;
//		int annees = 0;
//		int mois = 0;
//		int jours = 0;
//		int heures = 0;
//		
//		while (i < duree.length()) {
//			switch (duree.charAt(i)) {
//				case '0':
//				case '1':
//				case '2':
//				case '3':
//				case '4':
//				case '5':
//				case '6':
//				case '7':
//				case '8':
//				case '9':
//					k = k * 10 + Character.getNumericValue(duree.charAt(i));
//					break;
//				case 'y':
//					annees = k;
//					k = 0;
//					break;
//				case 'm':
//					mois = k;
//					k = 0;
//					break;
//				case 'd':
//					jours = k;
//					k = 0;
//					break;
//				case ':':
//					heures = k;
//					k = 0;
//				default:
//					break;
//			}
//			i = i + 1;
//		}
//		d = new SDuration(jours, mois, annees);
//		d.set(d.getDays(), heures, k, 0);
//		return new CteDuree(d);
//	}
	
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
	public AbstractFormula standardFunction(FormulaType formulaType, LinkedList<AbstractFormula> parameters) {
		AbstractFormula result = null;
		switch (formulaType) {
			case F_CEIL:
				return new FormulaCeilRounding(parameters.get(0), parameters.get(1));
			case F_FLOOR:
			case F_ROUNDED:
			case F_TRUNC:
			case F_BANKERS_ROUNDED:
				return new FormuleFonctionArrondi(formulaType, parameters);
			case F_FORMATDATE:
				result = new FormuleFormatDate(cf, parameters);
				break;
			case F_FORMATINTEGER:
				result = new FormuleFormatInteger(cf, parameters);
				break;
			case F_FORMATNUMERIC:
				result = new FormuleFormatNumeric(cf, parameters);
				break;
			case F_FORMATSTRING:
				result = new FormuleFormatString(cf, parameters);
				break;
			case F_STRINGITEM:
				result = new FormuleStringItem(cf, parameters);
				break;
			case F_SUBSTRING:
				result = new FormuleSubstring(cf, parameters);
				break;
			case F_MAX:
			case F_MIN:
			case F_SMIN:
			case F_SMAX:
				result = new FormuleFonctionExtremum(cf, parameters);
				break;
			case F_DATE:
				result = new FormuleFonctionDivers(cf, parameters);
				break;
			case F_MINUTES:
			case F_HOURS:
			case F_DAYS:
			case F_MONTHS:
			case F_YEARS:
				result = new FormuleFonctionDuree(cf, parameters);
				break;
			case F_ABS:
			case F_SIGN:
				result = new FormuleFonctionMath(cf, parameters);
				break;
			case F_PUT_TEXT:
				result = new FormulePutText(cf, parameters);
				break;
			case F_STRINGLENGTH:
				result = new FormuleStringLength(cf, parameters);
				break;
			default:
				logger.log(Level.ERROR,
						"Erreur lors de l'évaluation : Standard function code " + cf + " " + DefinitionsSLangage.nomFonction(cf));
				break;
		}
		return result;
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
	
}
