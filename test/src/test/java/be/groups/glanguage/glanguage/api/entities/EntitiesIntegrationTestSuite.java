package be.groups.glanguage.glanguage.api.entities;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormulaIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescriptionIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItemIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsageIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaAnomalyIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaBracketIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaDateIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaInIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaAndIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDifferenceIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDivideIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaEqualIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreaterIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreaterOrEqualIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaIntegerDivisionIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMinusIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaModuloIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMultiplyIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaOrIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaPlusIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmallerIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmallerOrEqualIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaApplicabilityIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaFormulaIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaGetIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaPrimitiveIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaRuleReferenceIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationDaysIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationHoursIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationMinutesIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationMonthsIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.FormulaDurationYearsIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMaxIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMinIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMaxIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMinIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDateIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatIntegerIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatNumericIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatStringIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.group.FormulaGroupSumVIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction.FormulaIfInstructionIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathAbsIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathSignIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingArithmeticIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingBankersIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingCeilIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingFloorIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingTruncIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringItemIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringLengthIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaSubStringIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalBooleanIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDateIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDurationIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalIntegerIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumericIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalStringIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaExistIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaNotIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaUnaryMinusIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinitionIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDescriptionIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItemIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentityIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersionIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameterIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetIntegrationTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersionIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for Entities module
 *
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({RuleSetIntegrationTest.class, RuleSetVersionIntegrationTest.class, RuleIdentityIntegrationTest.class, RuleDefinitionIntegrationTest.class,
		RuleDefinitionParameterIntegrationTest.class, RuleVersionIntegrationTest.class, RuleDescriptionIntegrationTest.class, RuleGroupItemIntegrationTest.class,
		AbstractFormulaIntegrationTest.class, FormulaDescriptionIntegrationTest.class,
		FormulaUsageIntegrationTest.class, FormulaParameterCombinationIntegrationTest.class, FormulaParameterCombinationItemIntegrationTest.class,
		FormulaTerminalBooleanIntegrationTest.class, FormulaTerminalDurationIntegrationTest.class, FormulaTerminalIntegerIntegrationTest.class,
		FormulaTerminalNumericIntegrationTest.class, FormulaTerminalStringIntegrationTest.class, FormulaTerminalDateIntegrationTest.class,
		FormulaExistIntegrationTest.class, FormulaNotIntegrationTest.class, FormulaUnaryMinusIntegrationTest.class, FormulaAndIntegrationTest.class, FormulaOrIntegrationTest
		.class, FormulaGreaterIntegrationTest.class, FormulaGreaterOrEqualIntegrationTest.class, FormulaSmallerIntegrationTest.class,
		FormulaSmallerOrEqualIntegrationTest.class, FormulaEqualIntegrationTest.class, FormulaDifferenceIntegrationTest.class, FormulaPlusIntegrationTest.class,
		FormulaMinusIntegrationTest.class, FormulaMultiplyIntegrationTest.class, FormulaDivideIntegrationTest.class, FormulaIntegerDivisionIntegrationTest.class,
		FormulaModuloIntegrationTest.class, FormulaExtremumMaxIntegrationTest.class, FormulaExtremumMinIntegrationTest.class,
		FormulaExtremumSignedMaxIntegrationTest.class, FormulaExtremumSignedMinIntegrationTest.class, FormulaFormatStringIntegrationTest.class,
		FormulaFormatIntegerIntegrationTest.class, FormulaFormatNumericIntegrationTest.class, FormulaFormatDateIntegrationTest.class,
		FormulaMathAbsIntegrationTest.class, FormulaMathSignIntegrationTest.class, FormulaRoundingArithmeticIntegrationTest.class,
		FormulaRoundingCeilIntegrationTest.class, FormulaRoundingFloorIntegrationTest.class, FormulaRoundingTruncIntegrationTest.class,
		FormulaRoundingBankersIntegrationTest.class, FormulaDurationDaysIntegrationTest.class, FormulaDurationHoursIntegrationTest.class,
		FormulaDurationMinutesIntegrationTest.class, FormulaDurationMonthsIntegrationTest.class, FormulaDurationYearsIntegrationTest.class,
		FormulaIfInstructionIntegrationTest.class, FormulaStringItemIntegrationTest.class, FormulaStringLengthIntegrationTest.class,
		FormulaSubStringIntegrationTest.class, FormulaApplicabilityIntegrationTest.class, FormulaFormulaIntegrationTest.class, FormulaGetIntegrationTest.class,
		FormulaPrimitiveIntegrationTest.class, FormulaRuleReferenceIntegrationTest.class, FormulaAnomalyIntegrationTest.class, FormulaBracketIntegrationTest
		.class, FormulaDateIntegrationTest.class, FormulaInIntegrationTest.class, FormulaGroupSumVIntegrationTest.class, })
public class EntitiesIntegrationTestSuite {

}
