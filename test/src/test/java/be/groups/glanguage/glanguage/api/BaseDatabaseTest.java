package be.groups.glanguage.glanguage.api;

import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;
import be.groups.presta.backoffice.test.base.Environment;
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

		JpaUtil.setCentralEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.PREPROD_BE));
		em = JpaUtil.getCentralEntityManager();
	}

	/*
	 * Static methods
	 */
	public static EntityManager getEntityManager() {
		return em;
	}
	
}
