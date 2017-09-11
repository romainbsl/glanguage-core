package be.groups.glanguage.glanguage.api.entities.ruleset;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link RuleSetVersion}
 * 
 * @author DUPIREFR
 */
public class RuleSetVersionIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleSetVersion} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleSetVersion ruleSetVersion = getEntityManager().find(RuleSetVersion.class, -900001);
		
		/* Checking entity */
		assertNotNull(ruleSetVersion);
		
		assertEquals(-900001, ruleSetVersion.getId());

		assertEquals(LocalDateTime.of(2016, 9, 7, 9, 0), ruleSetVersion.getProductionStartDate());
		assertEquals("1.0.1", ruleSetVersion.getVersion());
		assertNotNull(ruleSetVersion.getCreationDate());
		assertEquals("dupirefr", ruleSetVersion.getCreationAuthor());
		assertEquals("INTEGRATION_TEST", ruleSetVersion.getLabel());
		assertEquals(RuleSetVersionStatus.PROD, ruleSetVersion.getStatus());
		assertNull(ruleSetVersion.getModificationAuthor());
		assertNull(ruleSetVersion.getModificationDate());
		
		/* Checking relationships */
		assertNotNull(ruleSetVersion.getRuleSet());
		assertEquals(-900000, ruleSetVersion.getRuleSet().getId());
		
		assertNotNull(ruleSetVersion.getParent());
		assertEquals(-900000, ruleSetVersion.getParent().getId());
		
		assertNotNull(ruleSetVersion.getChildren());
		assertEquals(1, ruleSetVersion.getChildren().size());
		assertEquals(-900002, ((RuleSetVersion) ruleSetVersion.getChildren().toArray()[0]).getId());
		
		assertNotNull(ruleSetVersion.getIncludes());
		assertEquals(1, ruleSetVersion.getIncludes().size());
		assertEquals(-900003, ((RuleSetVersion) ruleSetVersion.getIncludes().toArray()[0]).getId());
		
		assertNotNull(ruleSetVersion.getIncludedIn());
		assertEquals(1, ruleSetVersion.getIncludedIn().size());
		assertEquals(-900004, ((RuleSetVersion) ruleSetVersion.getIncludedIn().toArray()[0]).getId());
		
		assertNotNull(ruleSetVersion.getRuleVersions());
		assertEquals(4, ruleSetVersion.getRuleVersions().size());
		assertEquals(4, ruleSetVersion.getRuleVersions().stream().map(RuleVersion::getId).distinct().count());
		
		List<Integer> ruleVersionIds = Arrays.asList(-900000, -900002, -900003, -900004);
		ruleSetVersion.getRuleVersions().forEach(v -> assertTrue(ruleVersionIds.contains(v.getId())));
	}
}
