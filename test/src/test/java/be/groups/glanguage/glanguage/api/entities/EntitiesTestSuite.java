package be.groups.glanguage.glanguage.api.entities;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormulaTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescriptionTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItemTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsageTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaAnomalyTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaBracketTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaDateTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaInTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMaxTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMinTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMaxTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMinTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDateTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatIntegerTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatNumericTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatStringTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.group.FormulaGroupSumVTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction.FormulaIfInstructionTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathAbsTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathSignTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringItemTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaStringLengthTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.FormulaSubStringTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaExistTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaNotTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaUnaryMinusTest;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcherTest;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameterTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for Entities module
 *
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({RuleSetTest.class, RuleSetVersionTest.class, RuleIdentityTest.class, RuleDefinitionTest.class,
		RuleDefinitionParameterTest.class, RuleVersionTest.class, RuleDescriptionTest.class, RuleGroupItemTest.class,
		RounderTest.class, DefinitionMatcherTest.class, AbstractFormulaTest.class, FormulaDescriptionTest.class,
		FormulaUsageTest.class, FormulaParameterConbinationTest.class, FormulaParameterConbinationItemTest.class,
		FormulaTerminalBooleanTest.class, FormulaTerminalDurationTest.class, FormulaTerminalIntegerTest.class,
		FormulaTerminalNumericTest.class, FormulaTerminalStringTest.class, FormulaTerminalDateTest.class,
		FormulaExistTest.class, FormulaNotTest.class, FormulaUnaryMinusTest.class, FormulaAndTest.class, FormulaOrTest
		.class, FormulaGreaterTest.class, FormulaGreaterOrEqualTest.class, FormulaSmallerTest.class,
		FormulaSmallerOrEqualTest.class, FormulaEqualTest.class, FormulaDifferenceTest.class, FormulaPlusTest.class,
		FormulaMinusTest.class, FormulaMultiplyTest.class, FormulaDivideTest.class, FormulaIntegerDivisionTest.class,
		FormulaModuloTest.class, FormulaExtremumMaxTest.class, FormulaExtremumMinTest.class,
		FormulaExtremumSignedMaxTest.class, FormulaExtremumSignedMinTest.class, FormulaFormatStringTest.class,
		FormulaFormatIntegerTest.class, FormulaFormatNumericTest.class, FormulaFormatDateTest.class,
		FormulaMathAbsTest.class, FormulaMathSignTest.class, FormulaRoundingArithmeticTest.class,
		FormulaRoundingCeilTest.class, FormulaRoundingFloorTest.class, FormulaRoundingTruncTest.class,
		FormulaRoundingBankersTest.class, FormulaDurationDaysTest.class, FormulaDurationHoursTest.class,
		FormulaDurationMinutesTest.class, FormulaDurationMonthsTest.class, FormulaDurationYearsTest.class,
		FormulaIfInstructionTest.class, FormulaStringItemTest.class, FormulaStringLengthTest.class,
		FormulaSubStringTest.class, FormulaApplicabilityTest.class, FormulaFormulaTest.class, FormulaGetTest.class,
		FormulaPrimitiveTest.class, FormulaRuleReferenceTest.class, FormulaAnomalyTest.class, FormulaBracketTest
		.class, FormulaDateTest.class, FormulaInTest.class, FormulaGroupSumVTest.class, })
public class EntitiesTestSuite {

}
