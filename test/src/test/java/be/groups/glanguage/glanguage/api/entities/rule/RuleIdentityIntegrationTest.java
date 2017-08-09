package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link RuleIdentity}
 * 
 * @author DUPIREFR
 */
public class RuleIdentityIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleIdentity} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleIdentity ruleIdentity = getEntityManager().find(RuleIdentity.class, -900000);

		/* Checking entity */
		assertNotNull(ruleIdentity);

		assertEquals(-900000, ruleIdentity.getId());

		/* Checking relationships */
		assertNotNull(ruleIdentity.getRuleDefinitions());
		assertEquals(2, ruleIdentity.getRuleDefinitions().size());
		assertEquals(2, ruleIdentity.getRuleDefinitions().stream().map(RuleDefinition::getId).distinct().count());

		List<Integer> ruleDefinitionIds = Arrays.asList(-900000, -900001);
		ruleIdentity.getRuleDefinitions().forEach(d -> assertTrue(ruleDefinitionIds.contains(d.getId())));
	}
}
