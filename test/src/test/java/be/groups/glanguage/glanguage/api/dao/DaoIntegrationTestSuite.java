package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.dao.formula.*;
import be.groups.glanguage.glanguage.api.dao.formula.description.*;
import be.groups.glanguage.glanguage.api.dao.formula.description.combination.*;
import be.groups.glanguage.glanguage.api.dao.formula.description.usage.*;
import be.groups.glanguage.glanguage.api.dao.rule.*;
import be.groups.glanguage.glanguage.api.dao.rule.definition.*;
import be.groups.glanguage.glanguage.api.dao.ruleset.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

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
