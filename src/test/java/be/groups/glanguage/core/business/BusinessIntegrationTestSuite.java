package be.groups.glanguage.core.business;

import be.groups.glanguage.core.business.evaluation.PlanEvaluatorIntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactoryIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.FormulaAnomalyIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.FormulaBracketIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.FormulaDateIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.FormulaInIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.binary.*;
import be.groups.glanguage.core.business.factory.formula.implementations.call.*;
import be.groups.glanguage.core.business.factory.formula.implementations.duration.*;
import be.groups.glanguage.core.business.factory.formula.implementations.extremum.FormulaExtremumMaxIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.extremum.FormulaExtremumMinIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.extremum.FormulaExtremumSignedMaxIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.extremum.FormulaExtremumSignedMinIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.format.FormulaFormatDateIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.format.FormulaFormatIntegerIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.format.FormulaFormatNumericIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.format.FormulaFormatStringIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.group.FormulaGroupSumVIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.instruction.FormulaIfInstructionIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.math.FormulaMathAbsIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.math.FormulaMathSignIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.rounding.*;
import be.groups.glanguage.core.business.factory.formula.implementations.string.FormulaStringItemIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.string.FormulaStringLengthIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.string.FormulaSubStringIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.terminal.*;
import be.groups.glanguage.core.business.factory.formula.implementations.unary.FormulaExistIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.unary.FormulaNotIntegrationTest;
import be.groups.glanguage.core.business.factory.formula.implementations.unary.FormulaUnaryMinusIntegrationTest;
import be.groups.glanguage.core.business.parser.ParserTest;
import be.groups.glanguage.core.business.plan.PlanIntegrationTest;
import be.groups.glanguage.core.business.universe.UniverseIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for Business module
 *
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({FormulaDescriptionFactoryIntegrationTest.class, ParserTest.class,
    PlanEvaluatorIntegrationTest.class, PlanIntegrationTest.class,
    UniverseIntegrationTest.class,
    FormulaTerminalBooleanIntegrationTest.class, FormulaTerminalDurationIntegrationTest.class,
    FormulaTerminalIntegerIntegrationTest.class,
    FormulaTerminalNumericIntegrationTest.class, FormulaTerminalStringIntegrationTest.class,
    FormulaTerminalDateIntegrationTest.class,
    FormulaExistIntegrationTest.class, FormulaNotIntegrationTest.class,
    FormulaUnaryMinusIntegrationTest.class, FormulaAndIntegrationTest.class,
    FormulaOrIntegrationTest
        .class, FormulaGreaterIntegrationTest.class, FormulaGreaterOrEqualIntegrationTest.class,
    FormulaSmallerIntegrationTest.class,
    FormulaSmallerOrEqualIntegrationTest.class, FormulaEqualIntegrationTest.class,
    FormulaDifferenceIntegrationTest.class, FormulaPlusIntegrationTest.class,
    FormulaMinusIntegrationTest.class, FormulaMultiplyIntegrationTest.class,
    FormulaDivideIntegrationTest.class, FormulaIntegerDivisionIntegrationTest.class,
    FormulaModuloIntegrationTest.class, FormulaExtremumMaxIntegrationTest.class,
    FormulaExtremumMinIntegrationTest.class,
    FormulaExtremumSignedMaxIntegrationTest.class, FormulaExtremumSignedMinIntegrationTest.class,
    FormulaFormatStringIntegrationTest.class,
    FormulaFormatIntegerIntegrationTest.class, FormulaFormatNumericIntegrationTest.class,
    FormulaFormatDateIntegrationTest.class,
    FormulaMathAbsIntegrationTest.class, FormulaMathSignIntegrationTest.class,
    FormulaRoundingArithmeticIntegrationTest.class,
    FormulaRoundingCeilIntegrationTest.class, FormulaRoundingFloorIntegrationTest.class,
    FormulaRoundingTruncIntegrationTest.class,
    FormulaRoundingBankersIntegrationTest.class, FormulaDurationDaysIntegrationTest.class,
    FormulaDurationHoursIntegrationTest.class,
    FormulaDurationMinutesIntegrationTest.class, FormulaDurationMonthsIntegrationTest.class,
    FormulaDurationYearsIntegrationTest.class,
    FormulaIfInstructionIntegrationTest.class, FormulaStringItemIntegrationTest.class,
    FormulaStringLengthIntegrationTest.class,
    FormulaSubStringIntegrationTest.class, FormulaApplicabilityIntegrationTest.class,
    FormulaFormulaIntegrationTest.class, FormulaGetIntegrationTest.class,
    FormulaPrimitiveIntegrationTest.class, FormulaRuleReferenceIntegrationTest.class,
    FormulaAnomalyIntegrationTest.class, FormulaBracketIntegrationTest
    .class, FormulaDateIntegrationTest.class, FormulaInIntegrationTest.class,
    FormulaGroupSumVIntegrationTest.class})
public class BusinessIntegrationTestSuite {

}
