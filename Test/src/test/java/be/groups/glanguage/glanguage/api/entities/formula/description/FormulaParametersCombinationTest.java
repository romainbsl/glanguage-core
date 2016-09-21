package be.groups.glanguage.glanguage.api.entities.formula.description;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link FormulaParametersCombination}
 * 
 * @author DUPIREFR
 */
public class FormulaParametersCombinationTest {
	
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
	 * Tests {@link FormulaParametersCombination} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		FormulaParametersCombination parametersCombination = em.find(FormulaParametersCombination.class, 2);
		
		/* Checking entity */
		assertNotNull(parametersCombination);
		
		assertEquals(Integer.valueOf(2), parametersCombination.getId());
		
		assertEquals(FormulaReturnType.BOOLEAN, parametersCombination.getReturnType());
		
		/* Checking relationships */
		assertNotNull(parametersCombination.getDescription());
		assertEquals(Integer.valueOf(213), parametersCombination.getDescription().getId());
		
		assertNotNull(parametersCombination.getParametersDescriptions());
		assertEquals(2, parametersCombination.getParametersDescriptions().size());
		assertEquals(2, parametersCombination.getParametersDescriptions().stream().map(d -> d.getId()).distinct().count());
		
		List<Integer> parametersDescriptionsIds = Arrays.asList(2, 3);
		parametersCombination.getParametersDescriptions().forEach(d -> {
			assertTrue(parametersDescriptionsIds.contains(d.getId()));
		});
	}
	
}
