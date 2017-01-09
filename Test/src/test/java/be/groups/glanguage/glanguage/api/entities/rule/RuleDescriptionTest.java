package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
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

		assertEquals("r1_fr", ruleDescription.getAliasFr());
		assertEquals("r1_nl", ruleDescription.getAliasNl());
		assertEquals("r1_de", ruleDescription.getAliasDe());
		assertEquals("r1_x", ruleDescription.getAliasX());

		assertEquals("descr_r1_fr", ruleDescription.getDescriptionFr());
		assertEquals("descr_r1_nl", ruleDescription.getDescriptionNl());
		assertEquals("descr_r1_de", ruleDescription.getDescriptionDe());
		assertEquals("descr_r1_x", ruleDescription.getDescriptionX());

		/* Checking relationships */
		assertNotNull(ruleDescription.getRuleVersions());
		assertEquals(4, ruleDescription.getRuleVersions().size());
		assertEquals(4, ruleDescription.getRuleVersions().stream().map(RuleVersion::getId).distinct().count());

		List<Integer> ruleVersionIds = Arrays.asList(-900000, -900001, -900002, -900003);
		ruleDescription.getRuleVersions().forEach(rv -> assertTrue(ruleVersionIds.contains(rv.getId())));
	}

}
