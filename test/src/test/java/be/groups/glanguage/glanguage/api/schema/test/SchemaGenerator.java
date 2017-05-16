package be.groups.glanguage.glanguage.api.schema.test;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Persistence;

import org.junit.Test;

/**
 * Helper class to generate database schema based on classes annotations
 * 
 * @author michotte
 */
public class SchemaGenerator {
	
	@Test
	public void generateSchema() {		
		Map<String, String> properties = new HashMap<>();
		properties.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=tom.groups.local)(PORT=1542)))(CONNECT_DATA=(SERVICE_NAME=sopre1)))");
		properties.put("hibernate.connection.username", "PREPROD1M2");
		properties.put("hibernate.connection.password", "mesbayv");
		
		Persistence.generateSchema("lyrics", properties);
		System.exit(0);
	}
	
}
