package be.groups.glanguage.glanguage.api.entities.ruleset;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link RuleSet}
 * 
 * @author DUPIREFR
 */
public class RuleSetIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleSet} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleSet ruleSet = getEntityManager().find(RuleSet.class, -900000);
		
		/* Checking entity */
		assertNotNull(ruleSet);
		
		assertEquals(-900000, ruleSet.getId());

		assertNotNull(ruleSet.getAlias());
		assertNotNull(ruleSet.getAlias().getItem(Language.FR));
		assertNotNull(ruleSet.getAlias().getItem(Language.FR).getText());
		assertEquals("rs1_fr", ruleSet.getAlias().getItem(Language.FR).getText());
		assertNotNull(ruleSet.getAlias().getItem(Language.NL));
		assertNotNull(ruleSet.getAlias().getItem(Language.NL).getText());
		assertEquals("rs1_nl", ruleSet.getAlias().getItem(Language.NL).getText());
		assertNotNull(ruleSet.getAlias().getItem(Language.EN));
		assertNotNull(ruleSet.getAlias().getItem(Language.EN).getText());
		assertEquals("rs1_x", ruleSet.getAlias().getItem(Language.EN).getText());

		assertNotNull(ruleSet.getDescription());
		assertNotNull(ruleSet.getDescription().getItem(Language.FR));
		assertNotNull(ruleSet.getDescription().getItem(Language.FR).getText());
		assertEquals("rs1_descr_fr", ruleSet.getDescription().getItem(Language.FR).getText());
		assertNotNull(ruleSet.getDescription().getItem(Language.NL));
		assertNotNull(ruleSet.getDescription().getItem(Language.NL).getText());
		assertEquals("rs1_descr_nl", ruleSet.getDescription().getItem(Language.NL).getText());
		assertNotNull(ruleSet.getDescription().getItem(Language.EN));
		assertNotNull(ruleSet.getDescription().getItem(Language.EN).getText());
		assertEquals("rs1_descr_x", ruleSet.getDescription().getItem(Language.EN).getText());
		
		/* Checking relationships */
		assertNotNull(ruleSet.getVersions());
		assertEquals(3, ruleSet.getVersions().size());
		assertEquals(3, ruleSet.getVersions().stream().map(RuleSetVersion::getId).distinct().count());
		
		List<Integer> ruleSetVersionIds = Arrays.asList(-900000, -900001, -900002);
		ruleSet.getVersions().forEach(v -> assertTrue(ruleSetVersionIds.contains(v.getId())));
	}
}
