package be.groups.glanguage.core.dao;

import be.groups.glanguage.core.dao.formula.FormulaDaoIntegrationTest;
import be.groups.glanguage.core.dao.formula.description.FormulaDescriptionDaoIntegrationTest;
import be.groups.glanguage.core.dao.formula.description.combination.FormulaParameterCombinationDaoIntegrationTest;
import be.groups.glanguage.core.dao.formula.description.combination.FormulaParameterCombinationDaoItemIntegrationTest;
import be.groups.glanguage.core.dao.formula.description.usage.FormulaUsageDaoIntegrationTest;
import be.groups.glanguage.core.dao.rule.RuleDescriptionDaoIntegrationTest;
import be.groups.glanguage.core.dao.rule.RuleGroupItemDaoIntegrationTest;
import be.groups.glanguage.core.dao.rule.RuleIdentityDaoIntegrationTest;
import be.groups.glanguage.core.dao.rule.RuleVersionDaoIntegrationTest;
import be.groups.glanguage.core.dao.rule.definition.RuleDefinitionDaoIntegrationTest;
import be.groups.glanguage.core.dao.rule.definition.RuleDefinitionParameterDaoIntegrationTest;
import be.groups.glanguage.core.dao.ruleset.RuleSetDaoIntegrationTest;
import be.groups.glanguage.core.dao.ruleset.RuleSetVersionDaoIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({FormulaDaoIntegrationTest.class,
    FormulaDescriptionDaoIntegrationTest.class,
    FormulaParameterCombinationDaoIntegrationTest.class,
    FormulaParameterCombinationDaoItemIntegrationTest.class,
    FormulaUsageDaoIntegrationTest.class,
    RuleSetDaoIntegrationTest.class,
    RuleSetVersionDaoIntegrationTest.class,
    RuleIdentityDaoIntegrationTest.class, RuleDefinitionDaoIntegrationTest.class,
    RuleDefinitionParameterDaoIntegrationTest.class, RuleVersionDaoIntegrationTest.class,
    RuleDescriptionDaoIntegrationTest.class, RuleGroupItemDaoIntegrationTest.class,
    FormulaUsageDaoIntegrationTest.class, FormulaParameterCombinationDaoIntegrationTest.class,
    FormulaParameterCombinationDaoItemIntegrationTest.class})
public class DaoIntegrationTestSuite {

}
