package be.groups.glanguage.core.entities;

/**
 * Created by michotte on 9/10/2017.
 */

import be.groups.glanguage.core.entities.formula.description.FormulaDescriptionTest;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationItemTest;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationTest;
import be.groups.glanguage.core.entities.formula.description.usage.FormulaUsageTest;
import be.groups.glanguage.core.entities.formula.implementations.*;
import be.groups.glanguage.core.entities.formula.implementations.binary.*;
import be.groups.glanguage.core.entities.formula.implementations.call.*;
import be.groups.glanguage.core.entities.formula.implementations.duration.*;
import be.groups.glanguage.core.entities.formula.implementations.extremum.FormulaExtremumMaxTest;
import be.groups.glanguage.core.entities.formula.implementations.extremum.FormulaExtremumMinTest;
import be.groups.glanguage.core.entities.formula.implementations.extremum.FormulaExtremumSignedMaxTest;
import be.groups.glanguage.core.entities.formula.implementations.extremum.FormulaExtremumSignedMinTest;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatDateTest;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatIntegerTest;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatNumericTest;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatStringTest;
import be.groups.glanguage.core.entities.formula.implementations.group.FormulaGroupSumVTest;
import be.groups.glanguage.core.entities.formula.implementations.instruction.FormulaIfInstructionTest;
import be.groups.glanguage.core.entities.formula.implementations.math.FormulaMathAbsTest;
import be.groups.glanguage.core.entities.formula.implementations.math.FormulaMathSignTest;
import be.groups.glanguage.core.entities.formula.implementations.rounding.*;
import be.groups.glanguage.core.entities.formula.implementations.string.FormulaStringItemTest;
import be.groups.glanguage.core.entities.formula.implementations.string.FormulaStringLengthTest;
import be.groups.glanguage.core.entities.formula.implementations.terminal.*;
import be.groups.glanguage.core.entities.formula.implementations.unary.FormulaExistTest;
import be.groups.glanguage.core.entities.formula.implementations.unary.FormulaNotTest;
import be.groups.glanguage.core.entities.formula.implementations.unary.FormulaUnaryMinusTest;
import be.groups.glanguage.core.entities.rule.RounderTest;
import be.groups.glanguage.core.entities.rule.RuleDefinitionTest;
import be.groups.glanguage.core.entities.rule.RuleIdentityTest;
import be.groups.glanguage.core.entities.rule.RuleVersionTest;
import be.groups.glanguage.core.entities.rule.definition.DefinitionMatcherTest;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameterTest;
import be.groups.glanguage.core.entities.ruleset.RuleSetTest;
import be.groups.glanguage.core.entities.ruleset.RuleSetVersionTest;
import be.groups.glanguage.core.entities.utils.format.FormatDoubleTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FormulaParameterCombinationItemTest.class, FormulaParameterCombinationTest.class,
    FormulaUsageTest.class, FormulaDescriptionTest.class, FormulaAndTest.class, FormulaDifferenceTest.class,
    FormulaDivideTest.class, FormulaEqualTest.class, FormulaGreaterOrEqualTest.class, FormulaGreaterTest.class,
    FormulaIntegerDivisionTest.class, FormulaMinusTest.class, FormulaModuloTest.class, FormulaMultiplyTest.class,
    FormulaOrTest.class, FormulaPlusTest.class, FormulaSmallerOrEqualTest.class, FormulaSmallerTest.class,
    FormulaApplicabilityTest.class, FormulaFormulaTest.class, FormulaGetTest.class, FormulaPrimitiveTest.class,
    FormulaRuleReferenceTest.class, FormulaDurationDaysTest.class, FormulaDurationHoursTest.class,
    FormulaDurationMinutesTest.class, FormulaDurationMonthsTest.class, FormulaDurationYearsTest.class,
    FormulaExtremumMaxTest.class, FormulaExtremumMinTest.class, FormulaExtremumSignedMaxTest.class,
    FormulaExtremumSignedMinTest.class, FormulaFormatDateTest.class, FormulaFormatIntegerTest.class,
    FormulaFormatNumericTest.class, FormulaFormatStringTest.class, FormulaGroupSumVTest.class,
    FormulaIfInstructionTest.class, FormulaMathAbsTest.class, FormulaMathSignTest.class,
    FormulaRoundingArithmeticTest.class, FormulaRoundingBankersTest.class, FormulaRoundingCeilTest.class,
    FormulaRoundingFloorTest.class, FormulaRoundingTruncTest.class, FormulaStringItemTest.class,
    FormulaStringLengthTest.class, FormulaTerminalBooleanTest.class, FormulaTerminalDateTest.class,
    FormulaTerminalDurationTest.class, FormulaTerminalIntegerTest.class, FormulaTerminalNumericTest.class,
    FormulaTerminalStringTest.class, FormulaExistTest.class, FormulaNotTest.class, FormulaUnaryMinusTest.class,
    AbstractFormulaTest.class, FormulaAnomalyTest.class, FormulaBracketTest.class, FormulaDateTest.class,
    FormulaInTest.class, DefinitionMatcherTest.class, RuleDefinitionParameterTest.class, RounderTest.class,
    RuleDefinitionTest.class, RuleIdentityTest.class, RuleVersionTest.class, RuleSetTest.class, RuleSetVersionTest
    .class, FormatDoubleTest.class
})
public class EntitiesUnitTestSuite {
}
