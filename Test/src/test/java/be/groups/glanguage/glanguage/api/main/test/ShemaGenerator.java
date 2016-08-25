package be.groups.glanguage.glanguage.api.main.test;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Persistence;

public class ShemaGenerator {
	
	public static void main(String[] args) {		
		Map<String, String> properties = new HashMap<>();
		properties.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=tom.groups.local)(PORT=1542)))(CONNECT_DATA=(SERVICE_NAME=sopre1)))");
		properties.put("hibernate.connection.username", "PREPROD1M2");
		properties.put("hibernate.connection.password", "mesbayv");
		
		try{
			Persistence.generateSchema("universe", properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
