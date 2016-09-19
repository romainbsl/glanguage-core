package be.groups.glanguage.glanguage.api.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormulaTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescriptionTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaParameterDescriptionTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaParametersCombinationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaAndTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDifferenceTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDivideTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaEqualTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreaterOrEqualTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreaterTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaIntegerDivisionTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMinusTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaModuloTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaMultiplyTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaOrTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaPlusTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmallerOrEqualTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmallerTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMaxTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumMinTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMaxTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.FormulaExtremumSignedMinTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatDateTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatIntegerTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatNumericTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.FormulaFormatStringTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathAbsTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.FormulaMathSignTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingArithmeticTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingBankersTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingCeilTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingFloorTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding.FormulaRoundingTruncTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalBooleanTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDateTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDurationTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalIntegerTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumericTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalStringTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaExistTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaNotTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaUnaryMinusTest;
import be.groups.glanguage.glanguage.api.entities.rule.RounderTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinitionTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDescriptionTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleGroupItemTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentityTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersionTest;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameterTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersionTest;

/**
 * Test suite for entities package
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({RuleSetTest.class, RuleSetVersionTest.class, RuleIdentityTest.class, RuleDefinitionTest.class,
		RuleDefinitionParameterTest.class, RuleVersionTest.class, RuleDescriptionTest.class, RuleGroupItemTest.class,
		RounderTest.class, AbstractFormulaTest.class, FormulaDescriptionTest.class, FormulaParametersCombinationTest.class,
		FormulaParameterDescriptionTest.class, FormulaTerminalBooleanTest.class, FormulaTerminalDurationTest.class,
		FormulaTerminalIntegerTest.class, FormulaTerminalNumericTest.class, FormulaTerminalStringTest.class,
		FormulaTerminalDateTest.class, FormulaExistTest.class, FormulaNotTest.class, FormulaUnaryMinusTest.class, FormulaAndTest.class,
		FormulaOrTest.class, FormulaGreaterTest.class, FormulaGreaterOrEqualTest.class, FormulaSmallerTest.class,
		FormulaSmallerOrEqualTest.class, FormulaEqualTest.class, FormulaDifferenceTest.class, FormulaPlusTest.class,
		FormulaMinusTest.class, FormulaMultiplyTest.class, FormulaDivideTest.class, FormulaIntegerDivisionTest.class,
		FormulaModuloTest.class, FormulaExtremumMaxTest.class, FormulaExtremumMinTest.class, FormulaExtremumSignedMaxTest.class,
		FormulaExtremumSignedMinTest.class, FormulaFormatStringTest.class, FormulaFormatIntegerTest.class,
		FormulaFormatNumericTest.class, FormulaFormatDateTest.class, FormulaMathAbsTest.class, FormulaMathSignTest.class,
		FormulaRoundingArithmeticTest.class, FormulaRoundingCeilTest.class, FormulaRoundingFloorTest.class,
		FormulaRoundingTruncTest.class, FormulaRoundingBankersTest.class})
		
		
public class EntitiesTestSuite {

}
