package be.groups.glanguage.glanguage.api.entities.formula.description;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTest;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link FormulaDescription}
 * 
 * @author DUPIREFR
 */
public class FormulaDescriptionTest {
	
	/*
	 * Static fields
	 */
	private static EntityManager em;
	
	/*
	 * Setups
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();
		
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		
		if (!TransactionHelper.isActive()) {
			TransactionHelper.begin();
		}
		
		em = JpaUtil.getEntityManager();
	}
	
	@AfterClass
	public static void close() {
		if (TransactionHelper.isActive()) {
			TransactionHelper.rollback();
		}
	}
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleIdentity} JPA mapping
	 */
	@Test
	@Category(JpaMappingTest.class)
	public void testJpaMapping() {
		FormulaDescription formulaDescription = em.find(FormulaDescription.class, 1);
		
		/* Checking entity */
		assertNotNull(formulaDescription);
		
		assertEquals(FormulaType.OP_OR, formulaDescription.getType());
		
		assertEquals("OR", formulaDescription.getName());
		
		assertEquals("Opération booléenne OU", formulaDescription.getDescriptionFr());
		assertEquals("OF boolean operatie", formulaDescription.getDescriptionNl());
		assertNull(formulaDescription.getDescriptionDe());
		assertEquals("OR boolean operator", formulaDescription.getDescriptionX());
		
		assertEquals(FormulaPriority.OR, formulaDescription.getPriority());
		
		/* Checking relationships */
		assertNotNull(formulaDescription.getParametersCombinations());
		assertEquals(1, formulaDescription.getParametersCombinations().size());
		assertEquals(Integer.valueOf(1), formulaDescription.getParametersCombinations().get(0).getId());
	}
	
}
