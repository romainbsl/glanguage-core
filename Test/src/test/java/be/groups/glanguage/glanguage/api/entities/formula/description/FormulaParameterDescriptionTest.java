package be.groups.glanguage.glanguage.api.entities.formula.description;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTest;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link FormulaParameterDescription}
 * 
 * @author DUPIREFR
 */
public class FormulaParameterDescriptionTest {
	
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
	 * Tests {@link FormulaParameterDescription} JPA mapping
	 */
	@Test
	@Category(JpaMappingTest.class)
	public void testJpaMapping() {
		FormulaParameterDescription parameterDescription = em.find(FormulaParameterDescription.class, 1);
		
		/* Checking entity */
		assertNotNull(parameterDescription);
		
		assertEquals(Integer.valueOf(1), parameterDescription.getId());
		
		assertEquals("operand", parameterDescription.getName());
		assertEquals("Objet à nier", parameterDescription.getDescriptionFr());
		assertEquals("Object te ontkennen", parameterDescription.getDescriptionNl());
		assertEquals("Item to negate", parameterDescription.getDescriptionX());
		assertNull(parameterDescription.getDescriptionDe());
		
		assertEquals(FormulaReturnType.BOOLEAN, parameterDescription.getReturnType());
		
		/* Checking relationships */
		assertNotNull(parameterDescription.getParametersCombination());
		assertEquals(Integer.valueOf(1), parameterDescription.getParametersCombination().getId());
	}
	
}
