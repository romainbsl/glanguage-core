package be.groups.glanguage.glanguage.api.dao;

import org.junit.BeforeClass;

import be.groups.common.test.utils.Environment;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

public class DaoTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Environment.setUp();
		TNSNames.setUp();

		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
	}

}
