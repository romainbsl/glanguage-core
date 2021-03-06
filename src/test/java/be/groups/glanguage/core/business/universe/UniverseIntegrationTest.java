package be.groups.glanguage.core.business.universe;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.plan.Plan;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.ruleset.RuleSet;
import be.groups.glanguage.core.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.core.entities.utils.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UniverseIntegrationTest extends IntegrationTest {

  @Autowired
  private Universe universe;

	/**
	 * Test {@link Universe#getAllRuleSets()}
	 */
	@Test
	public void testGetAllRuleSet() {
    Collection<RuleSet> ruleSets = universe.getAllRuleSets();
    assertNotNull(ruleSets);
		assertFalse(ruleSets.isEmpty());
	}

	/**
	 * Test {@link Universe#getRuleSet(Long)} when id is 900000
	 */
	@Test
  @Transactional
  public void testGetRuleSetById() {
    RuleSet ruleSet = universe.getRuleSet(-900000L);
    assertNotNull(ruleSet);
		assertEquals(Long.valueOf(-900000L), ruleSet.getId());
		assertNotNull(ruleSet.getVersions());
		assertFalse(ruleSet.getVersions().isEmpty());
	}

	/**
	 * Test {@link Universe#getRuleSet(String)} when alias is "rs1_fr"
	 */
	@Test
  @Transactional
  public void testGetRuleSetByAlias() {
    RuleSet ruleSet = universe.getRuleSet("rs1_fr");
    assertNotNull(ruleSet);
		assertEquals("rs1_fr", ruleSet.getAlias().getItem(Language.FR).getText());
		assertNotNull(ruleSet.getVersions());
		assertFalse(ruleSet.getVersions().isEmpty());
	}

	/**
	 * Test {@link Universe#getRuleSetVersion(Long)} when id is 900000
	 */
	@Test
	public void testGetRuleSetVersionById() {
    RuleSetVersion ruleSetVersion = universe.getRuleSetVersion(-900000L);
    assertNotNull(ruleSetVersion);
		assertEquals(Long.valueOf(-900000), ruleSetVersion.getId());
	}

	/**
	 * Test {@link Universe#getRuleSetVersion(Long, String)} when rule set id is 900000 and version is "1.0.0"
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetIdAndVersion() {
    RuleSetVersion ruleSetVersion = universe.getRuleSetVersion(-900000L, "1.0.0");
    assertNotNull(ruleSetVersion);
		assertEquals(Long.valueOf(-900000L), ruleSetVersion.getRuleSet().getId());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}

	/**
	 * Test {@link Universe#getRuleSetVersion(String, String)} when rule set id is "rs1_fr" and version is "1.0.0"
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetAliasAndVersion() {
    RuleSetVersion ruleSetVersion = universe.getRuleSetVersion("rs1_fr", "1.0.0");
    assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAlias().getItem(Language.FR).getText());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}

  /**
	 * Test {@link Universe#getRuleSetVersion(Long, LocalDateTime)} when rule set id is 900000 and production date is
   * now
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetIdAndProductionDate() {
		LocalDateTime productionDate = LocalDateTime.now();
    RuleSetVersion ruleSetVersion = universe.getRuleSetVersion(-900000L, productionDate);
    assertNotNull(ruleSetVersion);
		assertEquals(Long.valueOf(-900000L), ruleSetVersion.getRuleSet().getId());
		assertFalse("production start date after production date",
				ruleSetVersion.getProductionStartDate().isAfter(productionDate));
	}

  /**
	 * Test {@link Universe#getRuleSetVersion(String, LocalDateTime)} when rule set id is "rs1_fr" and production date is now
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetAliasAndProductionDate() {
		LocalDateTime productionDate = LocalDateTime.now();
    RuleSetVersion ruleSetVersion = universe.getRuleSetVersion("rs1_fr", productionDate);
    assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAlias().getItem(Language.FR).getText());
		assertFalse("production start date after production date",
				ruleSetVersion.getProductionStartDate().isAfter(productionDate));
	}

  /**
	 * Tests {@link Universe#getPlan(Long, LocalDate)}
	 */
	@Test
  @Transactional
  public void testGetPlan() {
		LocalDate effectiveDate = LocalDate.now();
    Plan plan = universe.getPlan(-900000L, effectiveDate);
    assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
	}

  /**
	 * Test {@link Universe#getFormula(Long)}
	 */
	@Test
	public void testGetFormula() {
    AbstractFormula formula = universe.getFormula(-900000L);
    assertNotNull(formula);
		assertEquals(Long.valueOf(-900000L), formula.getId());
	}
}
