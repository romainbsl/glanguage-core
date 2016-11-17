package be.groups.glanguage.glanguage.api.entities.formula;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
 * Test class for {@link AbstractFormula}
 * 
 * @author DUPIREFR
 */
public class AbstractFormulaTest {

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
	 * Tests {@link AbstractFormula} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		AbstractFormula formula = em.find(AbstractFormula.class, -900003);

		/* Checking entity */
		assertNotNull(formula);

		assertEquals(-900003, formula.getId());

		assertEquals("TRUE", formula.getConstantValue());
		assertEquals(Integer.valueOf(4), formula.getSequenceNumber());
		
		/* Checking relationships */
		assertNotNull(formula.getDescription());
		assertEquals(Integer.valueOf(1004), formula.getDescription().getId());
	}

}
