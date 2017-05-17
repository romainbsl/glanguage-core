package be.groups.glanguage.glanguage.api;

import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;
import be.groups.presta.backoffice.persistence.base.helper.TransactionHelper;
import be.groups.presta.backoffice.test.base.Environment;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;

public class BaseDatabaseTest {
	
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

		JpaUtil.setCentralEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		em = JpaUtil.getCentralEntityManager();
		
		if (!TransactionHelper.isCentralActive()) {
			TransactionHelper.begin();
		}
	}
	
	@AfterClass
	public static void close() {
		if (TransactionHelper.isCentralActive()) {
			TransactionHelper.rollback();
		}
	}
	
	/*
	 * Static methods
	 */
	public static EntityManager getEntityManager() {
		return em;
	}
	
}
