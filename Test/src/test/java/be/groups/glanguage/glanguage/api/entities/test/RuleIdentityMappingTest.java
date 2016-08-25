package be.groups.glanguage.glanguage.api.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaAnd;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaOr;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaTerminalBoolean;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaTerminalDate;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaTerminalString;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaUndefined;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link RuleIdentity}'s mapping
 * 
 * @author micmax
 * @author dupirefr
 *
 */
public class RuleIdentityMappingTest {

	private static EntityManager em;

	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		if(!TransactionHelper.isActive()) {
			TransactionHelper.begin();
		}
		em = JpaUtil.getEntityManager();
	}
	
	@AfterClass
	public static void close() {
		if(TransactionHelper.isActive()) {
			TransactionHelper.rollback();
		}
	}
	
	@Test
	public void testR1() {
		RuleIdentity ruleIdentity = em.find(RuleIdentity.class, "r_1");

		assertNotNull(ruleIdentity);

		assertEquals("r_1", ruleIdentity.getCode());

		assertEquals("r_1_aliasFr", ruleIdentity.getAliasFr());
		assertEquals("r_1_aliasNl", ruleIdentity.getAliasNl());
		assertEquals("r_1_aliasDe", ruleIdentity.getAliasDe());
		assertEquals("r_1_aliasX", ruleIdentity.getAliasX());

		assertEquals("r_1_descriptionFr", ruleIdentity.getDescriptionFr());
		assertEquals("r_1_descriptionNl", ruleIdentity.getDescriptionNl());
		assertEquals("r_1_descriptionDe", ruleIdentity.getDescriptionDe());
		assertEquals("r_1_descriptionX", ruleIdentity.getDescriptionX());

		assertNotNull(ruleIdentity.getDefaultRuleDefinition());
		assertTrue(ruleIdentity.getSocialSecretaryRuleDefintions().isEmpty());
		assertTrue(ruleIdentity.getEmployerRuleDefintions().isEmpty());
		assertTrue(ruleIdentity.getJointCommitteeRuleDefintions().isEmpty());
		assertTrue(ruleIdentity.getCollectiveLaborAgreementRuleDefintions().isEmpty());
		assertTrue(ruleIdentity.getCustomRuleDefintions().isEmpty());

		RuleDefinition ruleDefinition = ruleIdentity.getDefaultRuleDefinition();

		assertNotNull(ruleDefinition.getVersions());
		assertEquals(1, ruleDefinition.getVersions().size());

		RuleVersion ruleVersion = ruleDefinition.getVersions().first();
		
		assertEquals(LocalDate.of(2016, 1, 1), ruleVersion.getEffectivityStartDate());
		assertEquals(LocalDate.of(2016, 12, 31), ruleVersion.getEffectivityEndDate());
		assertEquals(LocalDate.of(2016, 1, 1), ruleVersion.getExploitationStartDate());
		assertNull(ruleVersion.getExploitationEndDate());
		assertEquals(RuleType.DEFAULT, ruleVersion.getRuleType());
		assertEquals(RoundingType.UNDEFINED, ruleVersion.getRoundingType());
		assertNull(ruleVersion.getRoundingPrecision());
		assertEquals("v1", ruleVersion.getVersion());

		assertNotNull(ruleVersion.getFormula());
		assertTrue(ruleVersion.getFormula() instanceof FormulaTerminalInteger);
		assertTrue(ruleVersion.getFormula().getValue() instanceof Integer);
		assertEquals(new Integer(10), ruleVersion.getFormula().getValue());
	}

	@Test
	public void testR2() {
		LocalDate date01012016 = LocalDate.of(2016, 1, 1);
		LocalDate date01072016 = LocalDate.of(2016, 6, 1);
		LocalDate date01082016 = LocalDate.of(2016, 7, 1);

		RuleIdentity ruleIdentity = em.find(RuleIdentity.class, "r_2");

		assertNotNull(ruleIdentity);

		assertEquals("r_2", ruleIdentity.getCode());
		assertNotNull(ruleIdentity.getRuleDefinitions());
		assertFalse(ruleIdentity.getRuleDefinitions().isEmpty());
		assertEquals(3, ruleIdentity.getRuleDefinitions().size());
		
		Iterator<RuleDefinition> itRuleDefinitions = ruleIdentity.getRuleDefinitions().iterator();
		while (itRuleDefinitions.hasNext()) {
			RuleDefinition ruleDefinition = itRuleDefinitions.next();
			switch (ruleDefinition.getId()) {
				case 2:
					assertEquals(DefinitionLevel.DEFAULT, ruleDefinition.getLevel());
					break;
				case 3:
					assertEquals(DefinitionLevel.SOCIAL_SECRETARY, ruleDefinition.getLevel());
					break;
				case 4:
					assertEquals(DefinitionLevel.EMPLOYER, ruleDefinition.getLevel());
					break;
			}
		}		
		
		assertNotNull(ruleIdentity.getDefaultRuleDefinition());
		RuleDefinition defaultRuleDefinition = ruleIdentity.getDefaultRuleDefinition();

		assertNotNull(defaultRuleDefinition.getVersions());
		assertFalse(defaultRuleDefinition.getVersions().isEmpty());
		assertTrue(defaultRuleDefinition.getVersions().size() == 2);

		RuleVersion firstRuleVersion = defaultRuleDefinition.getVersions().first();

		assertNotNull(firstRuleVersion.getFormula());
		assertEquals("v2", firstRuleVersion.getVersion());
		assertTrue(firstRuleVersion.getFormula() instanceof FormulaAnd);
		assertTrue(firstRuleVersion.getFormula().getValue() instanceof Boolean);
		assertEquals(new Boolean(false), firstRuleVersion.getFormula().getValue());
		assertNotNull(firstRuleVersion.getFormula().getParameters());
		assertEquals(2, firstRuleVersion.getFormula().getParameters().size());
		assertTrue(firstRuleVersion.getFormula().getParameters().get(0) instanceof FormulaOr);
		assertNotNull(firstRuleVersion.getFormula().getParameters().get(0).getParameters());
		assertFalse(firstRuleVersion.getFormula().getParameters().get(0).getParameters().isEmpty());
		assertTrue(firstRuleVersion.getFormula().getParameters().get(1) instanceof FormulaAnd);
		assertNotNull(firstRuleVersion.getFormula().getParameters().get(1).getParameters());
		assertFalse(firstRuleVersion.getFormula().getParameters().get(1).getParameters().isEmpty());
		for (int i = 0; i < firstRuleVersion.getFormula().getParameters().size(); i++) {
			for (int j = 0; j < firstRuleVersion.getFormula().getParameters().get(i).getParameters().size(); j++) {
				assertTrue(firstRuleVersion.getFormula().getParameters().get(i).getParameters()
						.get(j) instanceof FormulaTerminalBoolean);
			}
		}

		RuleVersion ruleVersionInExploitationAt01072016 = defaultRuleDefinition.getVersion(date01012016, date01072016);
		assertEquals(firstRuleVersion, ruleVersionInExploitationAt01072016);

		RuleVersion ruleVersionInExploitationAt01012016 = defaultRuleDefinition.getVersion(date01012016, date01012016);
		assertNotEquals(firstRuleVersion, ruleVersionInExploitationAt01012016);
		assertTrue(ruleVersionInExploitationAt01012016.getFormula() instanceof FormulaOr);
		assertTrue(ruleVersionInExploitationAt01012016.getFormula().getValue() instanceof Boolean);
		assertEquals(new Boolean(true), ruleVersionInExploitationAt01012016.getFormula().getValue());

		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.SOCIAL_SECRETARY, "1"));
		RuleDefinition socialSecretary1RuleDefinition = ruleIdentity.getDefinition(parameters);
		assertNotNull(socialSecretary1RuleDefinition);
		assertFalse(socialSecretary1RuleDefinition.getVersions().isEmpty());
		assertEquals(3, socialSecretary1RuleDefinition.getVersions().size());

		RuleVersion socialSecretaryRuleVersion = socialSecretary1RuleDefinition.getVersion(date01012016, date01012016);
		assertNotNull(socialSecretaryRuleVersion);
		assertEquals("v1", socialSecretaryRuleVersion.getVersion());

		socialSecretaryRuleVersion = socialSecretary1RuleDefinition.getVersion(date01012016, date01082016);
		assertNotNull(socialSecretaryRuleVersion);
		assertEquals("v1", socialSecretaryRuleVersion.getVersion());

		socialSecretaryRuleVersion = socialSecretary1RuleDefinition.getVersion(date01072016, date01012016);
		assertNotNull(socialSecretaryRuleVersion);
		assertEquals("v2", socialSecretaryRuleVersion.getVersion());

		socialSecretaryRuleVersion = socialSecretary1RuleDefinition.getVersion(date01072016, date01082016);
		assertNotNull(socialSecretaryRuleVersion);
		assertEquals("v3", socialSecretaryRuleVersion.getVersion());

		parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.SOCIAL_SECRETARY, "2"));
		RuleDefinition socialSecretary2RuleDefinition = ruleIdentity.getDefinition(parameters);
		assertNotNull(socialSecretary1RuleDefinition);
		assertEquals(defaultRuleDefinition, socialSecretary2RuleDefinition);

		parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		RuleDefinition employer1RuleDefinition = ruleIdentity.getDefinition(parameters);
		assertNotNull(employer1RuleDefinition);
		assertEquals(4, employer1RuleDefinition.getId());

		parameters.add(new RuleDefinitionParameter(DefinitionLevel.SOCIAL_SECRETARY, "2"));
		RuleDefinition employer1SocSec2RuleDefinition = ruleIdentity.getDefinition(parameters);
		assertNotNull(employer1SocSec2RuleDefinition);
		assertEquals(employer1RuleDefinition, employer1SocSec2RuleDefinition);

		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "X"));
		RuleDefinition employer1SocSec2ClaXRuleDefinition = ruleIdentity.getDefinition(parameters);
		assertNotNull(employer1SocSec2ClaXRuleDefinition);
		assertEquals(employer1RuleDefinition, employer1SocSec2ClaXRuleDefinition);
	}

	@Test
	public void testR3() {
		RuleIdentity ruleIdentity = em.find(RuleIdentity.class, "r_3");

		/* Testing RuleIdentity */
		assertNotNull(ruleIdentity);

		assertEquals("r_3", ruleIdentity.getCode());

		assertEquals("r_3_aliasFr", ruleIdentity.getAliasFr());
		assertEquals("r_3_aliasNl", ruleIdentity.getAliasNl());
		assertEquals("r_3_aliasDe", ruleIdentity.getAliasDe());
		assertEquals("r_3_aliasX", ruleIdentity.getAliasX());

		assertEquals("r_3_descriptionFr", ruleIdentity.getDescriptionFr());
		assertEquals("r_3_descriptionNl", ruleIdentity.getDescriptionNl());
		assertEquals("r_3_descriptionDe", ruleIdentity.getDescriptionDe());
		assertEquals("r_3_descriptionX", ruleIdentity.getDescriptionX());

		/* Testing definitions */
		assertEquals(4, ruleIdentity.getRuleDefinitions().size());
		assertNotNull(ruleIdentity.getDefaultRuleDefinition());
		assertEquals(1, ruleIdentity.getSocialSecretaryRuleDefintions().size());
		assertTrue(ruleIdentity.getEmployerRuleDefintions().isEmpty());
		assertTrue(ruleIdentity.getJointCommitteeRuleDefintions().isEmpty());
		assertTrue(ruleIdentity.getCollectiveLaborAgreementRuleDefintions().isEmpty());
		assertEquals(2, ruleIdentity.getCustomRuleDefintions().size());

		/* Testing default definition */
		RuleDefinition defaultRuleDefinition = ruleIdentity.getDefaultRuleDefinition();

		assertNotNull(defaultRuleDefinition.getVersions());
		assertEquals(1, defaultRuleDefinition.getVersions().size());

		/* Testing default version */
		RuleVersion defaultRuleVersion = defaultRuleDefinition.getVersions().first();

		assertEquals(LocalDate.of(2016, 1, 1), defaultRuleVersion.getEffectivityStartDate());
		assertEquals(LocalDate.of(2016, 12, 31), defaultRuleVersion.getEffectivityEndDate());
		assertEquals(LocalDate.of(2016, 1, 1), defaultRuleVersion.getExploitationStartDate());
		assertNull(defaultRuleVersion.getExploitationEndDate());
		assertEquals(RuleType.DEFAULT, defaultRuleVersion.getRuleType());
		assertEquals(RoundingType.ARITHMETIC, defaultRuleVersion.getRoundingType());
		assertEquals(Double.valueOf(0.01), defaultRuleVersion.getRoundingPrecision());
		assertEquals("v1", defaultRuleVersion.getVersion());

		assertNotNull(defaultRuleVersion.getFormula());
		assertTrue(defaultRuleVersion.getFormula() instanceof FormulaTerminalString);
		assertTrue(defaultRuleVersion.getFormula().getValue() instanceof String);
		assertEquals("ABC", defaultRuleVersion.getFormula().getValue());

		/* Testing social secretary definition */
		RuleDefinition socialSecretaryRuleDefinition = ruleIdentity.getSocialSecretaryRuleDefintions()
				.toArray(new RuleDefinition[1])[0];

		assertNotNull(socialSecretaryRuleDefinition.getDefinitionParameters());
		assertEquals(1, socialSecretaryRuleDefinition.getDefinitionParameters().size());

		RuleDefinitionParameter socialSecretaryRuleDefinitionParameter = socialSecretaryRuleDefinition
				.getDefinitionParameters().first();

		assertEquals(DefinitionLevel.SOCIAL_SECRETARY, socialSecretaryRuleDefinitionParameter.getLevel());
		assertEquals("2", socialSecretaryRuleDefinitionParameter.getValue());

		assertNotNull(socialSecretaryRuleDefinition.getVersions());
		assertEquals(1, socialSecretaryRuleDefinition.getVersions().size());

		RuleVersion socialSecretaryRuleVersion = socialSecretaryRuleDefinition.getVersions().first();

		assertEquals(LocalDate.of(2016, 1, 1), socialSecretaryRuleVersion.getEffectivityStartDate());
		assertEquals(LocalDate.of(2016, 12, 31), socialSecretaryRuleVersion.getEffectivityEndDate());
		assertEquals(LocalDate.of(2016, 1, 1), socialSecretaryRuleVersion.getExploitationStartDate());
		assertNull(socialSecretaryRuleVersion.getExploitationEndDate());
		assertEquals(RuleType.DEFAULT, socialSecretaryRuleVersion.getRuleType());
		assertEquals(RoundingType.CEIL, socialSecretaryRuleVersion.getRoundingType());
		assertEquals(Double.valueOf(0.01), socialSecretaryRuleVersion.getRoundingPrecision());
		assertEquals("v1", socialSecretaryRuleVersion.getVersion());

		assertNotNull(socialSecretaryRuleVersion.getFormula());
		assertTrue(socialSecretaryRuleVersion.getFormula() instanceof FormulaTerminalDate);
		assertTrue(socialSecretaryRuleVersion.getFormula().getValue() instanceof Date);
		assertEquals(LocalDate.of(2016, 1, 1), socialSecretaryRuleVersion.getFormula().getValue());
		
		/* Testing custom definitions */
		RuleDefinition[] customRuleDefinitions = ruleIdentity.getCustomRuleDefintions().toArray(new RuleDefinition[2]);
		
		for (RuleDefinition customRuleDefinition : customRuleDefinitions) {
			assertTrue(customRuleDefinition.getId() == 7 || customRuleDefinition.getId() == 8);
			
			if (customRuleDefinition.getId() == 7) {
				assertNotNull(customRuleDefinition.getDefinitionParameters());
				assertEquals(2, customRuleDefinition.getDefinitionParameters().size());

				RuleDefinitionParameter customRuleDefinitionParameter1 = customRuleDefinition
						.getDefinitionParameters().first();

				assertEquals(DefinitionLevel.SOCIAL_SECRETARY, customRuleDefinitionParameter1.getLevel());
				assertEquals("1", customRuleDefinitionParameter1.getValue());

				RuleDefinitionParameter customRuleDefinitionParameter2 = customRuleDefinition
						.getDefinitionParameters().last();

				assertEquals(DefinitionLevel.EMPLOYER, customRuleDefinitionParameter2.getLevel());
				assertEquals("1", customRuleDefinitionParameter2.getValue());

				assertNotNull(customRuleDefinition.getVersions());
				assertEquals(1, customRuleDefinition.getVersions().size());

				RuleVersion customRuleVersion = customRuleDefinition.getVersions().first();

				assertEquals(LocalDate.of(2016, 1, 1), customRuleVersion.getEffectivityStartDate());
				assertEquals(LocalDate.of(2016, 12, 31), customRuleVersion.getEffectivityEndDate());
				assertEquals(LocalDate.of(2016, 1, 1), customRuleVersion.getExploitationStartDate());
				assertNull(customRuleVersion.getExploitationEndDate());
				assertEquals(RuleType.DEFAULT, customRuleVersion.getRuleType());
				assertEquals(RoundingType.TRUNC, customRuleVersion.getRoundingType());
				assertEquals(Double.valueOf(2.0), customRuleVersion.getRoundingPrecision());
				assertEquals("v1", customRuleVersion.getVersion());

				assertNotNull(customRuleVersion.getFormula());
				assertTrue(customRuleVersion.getFormula() instanceof FormulaUndefined);
			} else {
				assertNotNull(customRuleDefinition.getDefinitionParameters());
				assertEquals(4, customRuleDefinition.getDefinitionParameters().size());

				RuleDefinitionParameter[] customRuleDefinitionParameters = customRuleDefinition
						.getDefinitionParameters().toArray(new RuleDefinitionParameter[4]);

				assertEquals(DefinitionLevel.SOCIAL_SECRETARY, customRuleDefinitionParameters[0].getLevel());
				assertEquals("1", customRuleDefinitionParameters[0].getValue());

				assertEquals(DefinitionLevel.EMPLOYER, customRuleDefinitionParameters[1].getLevel());
				assertEquals("1", customRuleDefinitionParameters[1].getValue());

				assertEquals(DefinitionLevel.EMPLOYER, customRuleDefinitionParameters[2].getLevel());
				assertEquals("2", customRuleDefinitionParameters[2].getValue());

				assertEquals(DefinitionLevel.EMPLOYER, customRuleDefinitionParameters[3].getLevel());
				assertEquals("3", customRuleDefinitionParameters[3].getValue());

				assertNotNull(customRuleDefinition.getVersions());
				assertEquals(1, customRuleDefinition.getVersions().size());

				RuleVersion customRuleVersion = customRuleDefinition.getVersions().first();

				assertEquals(LocalDate.of(2016, 1, 1), customRuleVersion.getEffectivityStartDate());
				assertEquals(LocalDate.of(2016, 12, 31), customRuleVersion.getEffectivityEndDate());
				assertEquals(LocalDate.of(2016, 1, 1), customRuleVersion.getExploitationStartDate());
				assertNull(customRuleVersion.getExploitationEndDate());
				assertEquals(RuleType.DEFAULT, customRuleVersion.getRuleType());
				assertEquals(RoundingType.BANKERS, customRuleVersion.getRoundingType());
				assertEquals(Double.valueOf(3.0), customRuleVersion.getRoundingPrecision());
				assertEquals("v1", customRuleVersion.getVersion());

				assertNotNull(customRuleVersion.getFormula());
				assertTrue(customRuleVersion.getFormula() instanceof FormulaUndefined);
			}
		}
	}

}
