package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleDescription}
 * 
 * @author DUPIREFR
 */
public class RuleDescriptionTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleDescription} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleDescription ruleDescription = getEntityManager().find(RuleDescription.class, -900000);

		/* Checking entity */
		assertNotNull(ruleDescription);
		
		assertEquals(-900000, ruleDescription.getId());

		assertEquals("r1", ruleDescription.getCode());

		assertNotNull(ruleDescription.getAlias());
		assertNotNull(ruleDescription.getAlias().getItem(Language.FR));
		assertNotNull(ruleDescription.getAlias().getItem(Language.FR).getText());
		assertEquals("r1_fr", ruleDescription.getAlias().getItem(Language.FR).getText());
		assertNotNull(ruleDescription.getAlias().getItem(Language.NL));
		assertNotNull(ruleDescription.getAlias().getItem(Language.NL).getText());
		assertEquals("r1_nl", ruleDescription.getAlias().getItem(Language.NL).getText());
		assertNotNull(ruleDescription.getAlias().getItem(Language.EN));
		assertNotNull(ruleDescription.getAlias().getItem(Language.EN).getText());
		assertEquals("r1_x", ruleDescription.getAlias().getItem(Language.EN).getText());

		assertNotNull(ruleDescription.getDescription());
		assertNotNull(ruleDescription.getDescription().getItem(Language.FR));
		assertNotNull(ruleDescription.getDescription().getItem(Language.FR).getText());
		assertEquals("descr_r1_fr", ruleDescription.getDescription().getItem(Language.FR).getText());
		assertNotNull(ruleDescription.getDescription().getItem(Language.NL));
		assertNotNull(ruleDescription.getDescription().getItem(Language.NL).getText());
		assertEquals("descr_r1_nl", ruleDescription.getDescription().getItem(Language.NL).getText());
		assertNotNull(ruleDescription.getDescription().getItem(Language.EN));
		assertNotNull(ruleDescription.getDescription().getItem(Language.EN).getText());
		assertEquals("descr_r1_x", ruleDescription.getDescription().getItem(Language.EN).getText());

		/* Checking relationships */
		assertNotNull(ruleDescription.getRuleVersions());
		assertEquals(4, ruleDescription.getRuleVersions().size());
		assertEquals(4, ruleDescription.getRuleVersions().stream().map(RuleVersion::getId).distinct().count());

		List<Integer> ruleVersionIds = Arrays.asList(-900000, -900001, -900002, -900003);
		ruleDescription.getRuleVersions().forEach(rv -> assertTrue(ruleVersionIds.contains(rv.getId())));
	}

}
