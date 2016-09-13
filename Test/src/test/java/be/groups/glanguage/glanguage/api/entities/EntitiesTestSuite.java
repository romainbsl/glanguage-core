package be.groups.glanguage.glanguage.api.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormulaTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaAndTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaDifferenceTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaEqualTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreaterOrEqualTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaGreaterTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaOrTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmallerOrEqualTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.FormulaSmallerTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalBooleanTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalDateTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalIntegerTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalNumericTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalStringTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaExistTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaNotTest;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.FormulaUnaryMinusTest;
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
@SuiteClasses({ RuleSetTest.class, RuleSetVersionTest.class, RuleIdentityTest.class, RuleDefinitionTest.class,
		RuleDefinitionParameterTest.class, RuleVersionTest.class, RuleDescriptionTest.class, RuleGroupItemTest.class,
		AbstractFormulaTest.class, FormulaTerminalBooleanTest.class, FormulaTerminalIntegerTest.class,
		FormulaTerminalNumericTest.class, FormulaTerminalStringTest.class, FormulaTerminalDateTest.class,
		FormulaExistTest.class, FormulaNotTest.class, FormulaUnaryMinusTest.class, FormulaAndTest.class,
		FormulaOrTest.class, FormulaGreaterTest.class, FormulaGreaterOrEqualTest.class, FormulaSmallerTest.class,
		FormulaSmallerOrEqualTest.class, FormulaEqualTest.class, FormulaDifferenceTest.class })
public class EntitiesTestSuite {

}
