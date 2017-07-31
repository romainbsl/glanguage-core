package be.groups.glanguage.glanguage.api.business.universe;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.Assert.*;

public class UniverseTest extends BaseDatabaseTest {

	/**
	 * Test {@link Universe#getAllRuleSets()}
	 */
	@Test
	public void testGetAllRuleSet() {
		Collection<RuleSet> ruleSets = Universe.getAllRuleSets();
		assertNotNull(ruleSets);
		assertFalse(ruleSets.isEmpty());
	}
	
	/**
	 * Test {@link Universe#getRuleSet(Integer)} when id is 900000
	 */
	@Test
	public void testGetRuleSetById() {
		RuleSet ruleSet = Universe.getRuleSet(-900000);
		assertNotNull(ruleSet);
		assertEquals(-900000, ruleSet.getId());
		assertNotNull(ruleSet.getVersions());
		assertFalse(ruleSet.getVersions().isEmpty());
	}
	
	/**
	 * Test {@link Universe#getRuleSet(String)} when alias is "rs1_fr"
	 */
	@Test
	public void testGetRuleSetByAlias() {
		RuleSet ruleSet = Universe.getRuleSet("rs1_fr");
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
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion(-900000);
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getId());
	}
	
	/**
	 * Test {@link Universe#getRuleSetVersion(Integer, String)} when rule set id is 900000 and version is "1.0.0"
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetIdAndVersion() {
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion(-900000, "1.0.0");
		assertNotNull(ruleSetVersion);
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
	}
	
	/**
	 * Test {@link Universe#getRuleSetVersion(String, String)} when rule set id is "rs1_fr" and version is "1.0.0"
	 */
	@Test
	public void testGetRuleSetVersionByRuleSetAliasAndVersion() {
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion("rs1_fr", "1.0.0");
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
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion(-900000, productionDate);
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
		RuleSetVersion ruleSetVersion = Universe.getRuleSetVersion("rs1_fr", productionDate);
		assertNotNull(ruleSetVersion);
		assertEquals("rs1_fr", ruleSetVersion.getRuleSet().getAlias().getItem(Language.FR).getText());
		assertFalse("production start date after production date",
				ruleSetVersion.getProductionStartDate().isAfter(productionDate));
	}
	
	/**
	 * Tests {@link Universe#getPlan(Integer, LocalDate)}
	 */
	@Test
	public void testGetPlan() {
		LocalDate effectiveDate = LocalDate.now();
		Plan plan = Universe.getPlan(-900000, effectiveDate);
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
		AbstractFormula formula = Universe.getFormula(-900000);
		assertNotNull(formula);
		assertEquals(-900000, formula.getId());
	}
	
}
