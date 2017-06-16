package be.groups.glanguage.glanguage.api.dao;

import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;
import be.groups.presta.backoffice.test.base.Environment;
import org.junit.BeforeClass;

public class DaoTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Environment.setUp();
		TNSNames.setUp();

		JpaUtil.setCentralEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.PREPROD_BE));
	}

}
