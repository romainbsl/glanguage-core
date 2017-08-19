package be.groups.glanguage.glanguage.api.business;

import be.groups.glanguage.glanguage.api.business.evaluation.*;
import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.binary.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.call.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.duration.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.extremum.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.format.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.group.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.instruction.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.math.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.rounding.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.string.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.terminal.*;
import be.groups.glanguage.glanguage.api.business.factory.formula.implementations.unary.*;
import be.groups.glanguage.glanguage.api.business.parser.*;
import be.groups.glanguage.glanguage.api.business.plan.*;
import be.groups.glanguage.glanguage.api.business.universe.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

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
