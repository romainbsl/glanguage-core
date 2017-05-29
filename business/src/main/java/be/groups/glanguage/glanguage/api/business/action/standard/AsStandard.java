package be.groups.glanguage.glanguage.api.business.action.standard;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.analysis.IdentifierParameterList;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaAnomaly;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaBracket;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaIn;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaApplicability;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaFormula;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGet;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaRuleReference;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMax;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMin;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMax;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMin;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatNumeric;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.group.FormulaGroupMultiply;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.group.FormulaGroupSum;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.group.FormulaGroupSumV;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction.FormulaIfInstruction;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathAbs;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathSign;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringItem;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringLength;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaSubString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaExist;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaNot;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaUnaryMinus;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.parser.ParserUnableToParseFormulaInnerError;
import be.groups.glanguage.glanguage.api.error.parser.ParserUnableToParseTextInnerError;
import be.groups.glanguage.glanguage.api.error.parser.ParserUnknownFormulaTypeInnerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Set of semantical actions applicable during analysis
 */
public class AsStandard implements SemanticalAction {

    static Logger logger = LoggerFactory.getLogger(Class.class);

    /**
     * Result of the analysis
     */
    private AbstractFormula formula;

    /**
     * @return the formula resulting of the analysis
     */
    @Override
    public AbstractFormula getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    @Override
    public void setFormula(AbstractFormula formula) {
        this.formula = formula;
    }

    @Override
    public void initialize() {
        this.formula = null;
    }

    @Override
    public void endAnalysis() {
        // do nothing
    }

    @Override
    public void beginAnalysis() {
    }

    @Override
    public LocalDate createDate(int d, int m, int y) {
        return LocalDate.of(y, m, d);
    }

    @Override
    public AbstractFormula unaryOperation(FormulaType formulaDescriptionId, AbstractFormula formula) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(formulaDescriptionId);
        try {
            switch (formulaDescription.getType()) {
                case OP_NOT:
                    return new FormulaNot(formulaDescription, formula);
                case OP_UNARY_PLUS:
                    return formula;
                case OP_UNARY_MINUS:
                    return new FormulaUnaryMinus(formulaDescription, formula);
                case OP_EXIST:
                    return new FormulaExist(formulaDescription, formula);
                default:
                    throw new GLanguageException(new ParserUnknownFormulaTypeInnerError(formulaDescriptionId,
                                                                                        "unaryOperation",
                                                                                        null));
            }
        } catch (GLanguageException e) {
            LinkedList<AbstractFormula> parameters = new LinkedList<>();
            parameters.add(formula);
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                parameters,
                                                                                "unaryOperation",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula binaryOperation(FormulaType formulaDescriptionId,
                                           AbstractFormula formula1,
                                           AbstractFormula formula2) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(formulaDescriptionId);
        try {
            switch (formulaDescriptionId) {
                case OP_MULTIPLY:
                    return new FormulaMultiply(formulaDescription, formula1, formula2);
                case OP_DIVIDE:
                    return new FormulaDivide(formulaDescription, formula1, formula2);
                case OP_INTEGER_DIVISION:
                    return new FormulaIntegerDivision(formulaDescription, formula1, formula2);
                case OP_MODULO:
                    return new FormulaModulo(formulaDescription, formula1, formula2);
                case OP_PLUS:
                    return new FormulaPlus(formulaDescription, formula1, formula2);
                case OP_MINUS:
                    return new FormulaMinus(formulaDescription, formula1, formula2);
                case OP_EQUAL:
                    return new FormulaEqual(formulaDescription, formula1, formula2);
                case OP_DIFFERENCE:
                    return new FormulaDifference(formulaDescription, formula1, formula2);
                case OP_SMALLER:
                    return new FormulaSmaller(formulaDescription, formula1, formula2);
                case OP_SMALLER_OR_EQUAL:
                    return new FormulaSmallerOrEqual(formulaDescription, formula1, formula2);
                case OP_GREATER:
                    return new FormulaGreater(formulaDescription, formula1, formula2);
                case OP_GREATER_OR_EQUAL:
                    return new FormulaGreaterOrEqual(formulaDescription, formula1, formula2);
                case OP_AND:
                    return new FormulaAnd(formulaDescription, formula1, formula2);
                case OP_OR:
                    return new FormulaOr(formulaDescription, formula1, formula2);
                default:
                    throw new GLanguageException(new ParserUnknownFormulaTypeInnerError(formulaDescriptionId,
                                                                                        "binaryOperation",
                                                                                        null));
            }
        } catch (GLanguageException e) {
            LinkedList<AbstractFormula> parameters = new LinkedList<>();
            parameters.add(formula1);
            parameters.add(formula2);
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                parameters,
                                                                                "binaryOperation",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula bracketFormula(AbstractFormula formula) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS);
        try {
            return new FormulaBracket(formulaDescription, formula);
        } catch (GLanguageException e) {
            LinkedList<AbstractFormula> parameters = new LinkedList<>();
            parameters.add(formula);
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                parameters,
                                                                                "bracketFormula",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula inOperation(AbstractFormula element, LinkedList<AbstractFormula> inList) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.F_IN);
        try {
            return new FormulaIn(formulaDescription, element, inList);
        } catch (GLanguageException e) {
            LinkedList<AbstractFormula> parameters = new LinkedList<>();
            parameters.add(element);
            parameters.addAll(inList);
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                parameters,
                                                                                "inOperation",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula referenceFormula(String ruleId) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_RULE_REFERENCE);
        try {
            return new FormulaRuleReference(formulaDescription, ruleId);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "referenceFormula",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula terminalIntegerFormula(String s) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER);
        try {
            return new FormulaTerminalInteger(formulaDescription, s);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "terminalIntegerFormula",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula terminalNumericFormula(String s) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC);
        try {
            return new FormulaTerminalNumeric(formulaDescription, s);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "terminalNumericFormula",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula terminalDateFormula(LocalDate d) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE);
        try {
            return new FormulaTerminalDate(formulaDescription, d);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "terminalDateFormula",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula terminalDurationFormula(String duration) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION);
        try {
            return new FormulaTerminalDuration(formulaDescription, duration);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "terminalDurationFormula",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula terminalBooleanFormula(boolean b) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN);
        try {
            return new FormulaTerminalBoolean(formulaDescription, b);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "terminalBooleanFormula",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula terminalStringFormula(String s) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING);
        try {
            return new FormulaTerminalString(formulaDescription, s);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "terminalStringFormula",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within
             * SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a
             * checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked
             * exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula emptyFormula() {
        return null;
    }

    @Override
    public AbstractFormula standardFunction(FormulaType formulaDescriptionId, LinkedList<AbstractFormula> parameters) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(formulaDescriptionId);
        try {
            switch (formulaDescriptionId) {
                case F_CEIL:
                    return new FormulaRoundingCeil(formulaDescription, parameters);
                case F_FLOOR:
                    return new FormulaRoundingFloor(formulaDescription, parameters);
                case F_ROUNDED:
                    return new FormulaRoundingArithmetic(formulaDescription, parameters);
                case F_TRUNC:
                    return new FormulaRoundingTrunc(formulaDescription, parameters);
                case F_BANKERS_ROUNDED:
                    return new FormulaRoundingBankers(formulaDescription, parameters);
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
                    throw new GLanguageException(new ParserUnknownFormulaTypeInnerError(formulaDescriptionId,
                                                                                        "standardFunction",
                                                                                        null));
            }
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                parameters,
                                                                                "standardFunction",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula groupFunction(FormulaType formulaDescriptionId, String groupName) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(formulaDescriptionId);
        try {
            switch (formulaDescriptionId) {
                case G_MULT:
                    return new FormulaGroupMultiply(formulaDescription, groupName);
                case G_SUM:
                    return new FormulaGroupSum(formulaDescription, groupName);
                case G_SUMV:
                    return new FormulaGroupSumV(formulaDescription, groupName);
                default:
                    throw new InternalError("Internal error : unknown group function formula type : " +
                                                    formulaDescriptionId);
            }
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "groupFunction",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula getFunction(FormulaReturnType returnType, IdentifierParameterList identifierParameterlist) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_GET);
        FormulaDescription subFormulasDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE);
        try {
            return new FormulaGet(formulaDescription,
                                  subFormulasDescription,
                                  returnType,
                                  identifierParameterlist.getIdentifiers(),
                                  identifierParameterlist.getParameters());
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "getFunction",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula applicabiltyCall(String code) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_APPLICABILITY);
        try {
            return new FormulaApplicability(formulaDescription, code);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "applicabiltyCall",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula formulaCall(String code) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.C_FORMULA);
        try {
            return new FormulaFormula(formulaDescription, code);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "formulaCall",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public AbstractFormula ifInstruction(AbstractFormula condition,
                                         AbstractFormula ifStatement,
                                         AbstractFormula elseStatement) {
        FormulaDescription formulaDescription = FormulaDescriptionFactory.getDescription(FormulaType.I_IF);
        try {
            return new FormulaIfInstruction(formulaDescription, condition, ifStatement, elseStatement);
        } catch (GLanguageException e) {
            e.getError().setInnerError(new ParserUnableToParseFormulaInnerError(formulaDescription,
                                                                                null,
                                                                                "ifInstruction",
                                                                                null));
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkInteger(String n, int min, int max) {
        try {
            if (n == null) {
                throw new GLanguageException(new ParserUnableToParseTextInnerError(n,
                                                                                   "checkInteger",
                                                                                   "null string",
                                                                                   null));
            }
            try {
                int result = new Integer(n);
                if (result < min || (max > 0 && result > max)) {
                    throw new GLanguageException(new ParserUnableToParseTextInnerError(n,
                                                                                       "checkInteger",
                                                                                       "integer '" + n + "' is out "
                                                                                               + "of the bounds [" +
                                                                                               min + "," + (max > 0 ?
                                                                                               max : Integer
                                                                                               .MAX_VALUE) + "]",
                                                                                       null));
                }
                return result;
            } catch (NumberFormatException nfe) {
                throw new GLanguageException(new ParserUnableToParseTextInnerError(n,
                                                                                   "checkInteger",
                                                                                   "parameter must represent an " +
                                                                                           "integer : " + n,
                                                                                   nfe));
            }
        } catch (GLanguageException e) {
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method within SlangTab that
             * calls this method to handle GLanguageException or any checked exception. Therefore, throwing a checked
             * exception from this method lead to a compilation error in "yyparse()" method.
             * To avoid this we have no other choice than wrapping the GLanguageException into an unchecked exception
             * (e.g. RuntimeException)
             * This exception will rise through SlangTab methods until it reaches "inject(String)" method. That
             * method is developed by ourselves and can therefore handle the exception
             */
            throw new RuntimeException(e);
        }
    }

}
