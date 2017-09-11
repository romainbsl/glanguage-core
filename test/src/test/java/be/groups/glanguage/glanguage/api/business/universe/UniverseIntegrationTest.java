package be.groups.glanguage.glanguage.api.business.universe;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.business.plan.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.ruleset.*;
import be.groups.glanguage.glanguage.api.entities.utils.*;
import java.time.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

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
	 * Test {@link Universe#getRuleSet(Integer)} when id is 900000
	 */
	@Test
  @Ignore
  // TODO Lazy-init Exception : must find a way to use @Transactional without losing sharding
  public void testGetRuleSetById() {
    RuleSet ruleSet = universe.getRuleSet(-900000);
    assertNotNull(ruleSet);
		assertEquals(-900000, ruleSet.getId());
		assertNotNull(ruleSet.getVersions());
		assertFalse(ruleSet.getVersions().isEmpty());
	}

	/**
	 * Test {@link Universe#getRuleSet(String)} when alias is "rs1_fr"
	 */
	@Test
  @Ignore
  // TODO Lazy-init Exception : must find a way to use @Transactional without losing sharding
  public void testGetRuleSetByAlias() {
    RuleSet ruleSet = universe.getRuleSet("rs1_fr");
    assertNotNull(ruleSet);
		assertEquals("rs1_fr", ruleSet.getAlias().getItem(Language.FR).getText());
		assertNotNull(ruleSet.getVersions());
		assertFalse(ruleSet.getVersions().isEmpty());
	}

	/**
	 * Test {@link Universe#getRuleSetVersion(Integer)} when id is 900000
	 */
	@Test
	public void testGetRuleSetVersionById() {
    RuleSetVersion ruleSetVersion = universe.getRuleSetVersion(-900000);
    assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getId());
	}

	/**
	 * Test {@link Universe#getRuleSetVersion(Integer, String)} when rule set id is 900000 and version is "1.0.0"
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetIdAndVersion() {
    RuleSetVersion ruleSetVersion = universe.getRuleSetVersion(-900000, "1.0.0");
    assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
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
	 * Test {@link Universe#getRuleSetVersion(Integer, LocalDateTime)} when rule set id is 900000 and production date is now
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetIdAndProductionDate() {
		LocalDateTime productionDate = LocalDateTime.now();
    RuleSetVersion ruleSetVersion = universe.getRuleSetVersion(-900000, productionDate);
    assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
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
	 * Tests {@link Universe#getPlan(Integer, LocalDate)}
	 */
	@Test
  @Ignore
  // TODO Lazy-init Exception : must find a way to use @Transactional without losing sharding
  public void testGetPlan() {
		LocalDate effectiveDate = LocalDate.now();
    Plan plan = universe.getPlan(-900000, effectiveDate);
    assertNotNull(plan);
		assertNotNull(plan.getRuleVersions());
		assertFalse(plan.getRuleVersions().isEmpty());
		assertFalse(plan.isBranched());
	}

  /**
	 * Test {@link Universe#getFormula(Integer)}
	 */
	@Test
	public void testGetFormula() {
    AbstractFormula formula = universe.getFormula(-900000);
    assertNotNull(formula);
		assertEquals(-900000, formula.getId());
	}
}
