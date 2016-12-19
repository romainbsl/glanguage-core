package be.groups.glanguage.glanguage.api.entities.ruleset;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleSet}
 * 
 * @author DUPIREFR
 */
public class RuleSetTest extends BaseDatabaseTest {

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
		
		assertEquals("rs1_fr", ruleSet.getAliasFr());
		assertEquals("rs1_nl", ruleSet.getAliasNl());
		assertEquals("rs1_de", ruleSet.getAliasDe());
		assertEquals("rs1_x", ruleSet.getAliasX());
		
		assertEquals("rs1_descr_fr", ruleSet.getDescriptionFr());
		assertEquals("rs1_descr_nl", ruleSet.getDescriptionNl());
		assertEquals("rs1_descr_de", ruleSet.getDescriptionDe());
		assertEquals("rs1_descr_x", ruleSet.getDescriptionX());
		
		/* Checking relationships */
		assertNotNull(ruleSet.getVersions());
		assertEquals(3, ruleSet.getVersions().size());
		assertEquals(3, ruleSet.getVersions().stream().map(RuleSetVersion::getId).distinct().count());
		
		List<Integer> ruleSetVersionIds = Arrays.asList(-900000, -900001, -900002);
		ruleSet.getVersions().forEach(v -> assertTrue(ruleSetVersionIds.contains(v.getId())));
	}
	
	/**
	 * Test {@link RuleSet#getRuleIdentities()} using {@link RuleSetTestResources#rs1}
	 */
	@Test
	public void testGetRuleIdentitiesRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleIdentity> ruleIdentities = rs.getRuleIdentities();
		assertNotNull(ruleIdentities);
		assertEquals(3, ruleIdentities.size());
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r1));
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r2));
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r3));
	}
	
	/**
	 * Test {@link RuleSet#getRuleIdentities(String)} using {@link RuleSetTestResources#rs1} when code is "r1"
	 */
	@Test
	public void testGetRuleIdentitiesByCodeR1RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleIdentity> ruleIdentities = rs.getRuleIdentities("r1");
		assertNotNull(ruleIdentities);
		assertEquals(1, ruleIdentities.size());
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r1));
	}
	
	/**
	 * Test {@link RuleSet#getRuleIdentities(String)} using {@link RuleSetTestResources#rs1} when code is "r2"
	 */
	@Test
	public void testGetRuleIdentitiesByCodeR2RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleIdentity> ruleIdentities = rs.getRuleIdentities("r2");
		assertNotNull(ruleIdentities);
		assertEquals(1, ruleIdentities.size());
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r2));
	}
	
	/**
	 * Test {@link RuleSet#getVersion(LocalDateTime)} using {@link RuleSetTestResources#rs1} when date is 01/01/2015
	 */
	@Test
	public void testGetVersionAt20150101() {
		RuleSet rs = RuleSetTestResources.rs1;
		RuleSetVersion rsv = rs.getVersion(LocalDateTime.of(2015,1, 1, 0, 0));
		assertNotNull(rsv);
		assertEquals(RuleSetVersionTestResources.rs1v1, rsv);
	}
	
	/**
	 * Test {@link RuleSet#getVersion(LocalDateTime)} using {@link RuleSetTestResources#rs1} when date is 01/01/2016
	 */
	@Test
	public void testGetVersionAt20160101() {
		RuleSet rs = RuleSetTestResources.rs1;
		RuleSetVersion rsv = rs.getVersion(LocalDateTime.of(2016,1, 1, 0, 0));
		assertNotNull(rsv);
		assertEquals(RuleSetVersionTestResources.rs1v2, rsv);
	}
	
	/**
	 * Test {@link RuleSet#getVersion(String)} using {@link RuleSetTestResources#rs1} when version is "1.0.0"
	 */
	@Test
	public void testGetVersionByVersion100() {
		RuleSet rs = RuleSetTestResources.rs1;
		RuleSetVersion rsv = rs.getVersion("1.0.0");
		assertNotNull(rsv);
		assertEquals(RuleSetVersionTestResources.rs1v1, rsv);
	}
	
	/**
	 * Test {@link RuleSet#getVersion(String)} using {@link RuleSetTestResources#rs1} when version is "2.0.0"
	 */
	@Test
	public void testGetVersionByVersion200() {
		RuleSet rs = RuleSetTestResources.rs1;
		RuleSetVersion rsv = rs.getVersion("2.0.0");
		assertNotNull(rsv);
		assertEquals(RuleSetVersionTestResources.rs1v2, rsv);
	}
	
	/*
	 * Tests get...RuleDefinition(s) method
	 */
	
	/**
	 * Test {@link RuleSet#getRuleDefinitions()} using {@link RuleSetTestResources#rs1}
	 */
	@Test
	public void testGetRuleDefinitionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleDefinition> ruleDefinitions = rs.getRuleDefinitions();
		assertNotNull(ruleDefinitions);
		assertEquals(8, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleDefinitions(String)} using {@link RuleSetTestResources#rs1} when code is "r1"
	 */
	@Test
	public void testGetRuleDefinitionsByCodeR1RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleDefinition> ruleDefinitions = rs.getRuleDefinitions("r1");
		assertNotNull(ruleDefinitions);
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleDefinitions(String)} using {@link RuleSetTestResources#rs1} when code is "r2"
	 */
	@Test
	public void testGetRuleDefinitionsByCodeR2RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleDefinition> ruleDefinitions = rs.getRuleDefinitions("r2");
		assertNotNull(ruleDefinitions);
		assertEquals(6, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleDefinitions()}
	 */
	@Test
	public void testGetDefaultRuleDefinitionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefaultRuleDefinitions();
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		for (RuleDefinition ruleDefinition : ruleDefinitions) {
			assertTrue(ruleDefinition.getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleDefinitions(String)} using {@link RuleSetTestResources#rs1} when code is "r3"
	 */
	@Test
	public void testGetDefaultRuleDefinitionsByCodeR3RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefaultRuleDefinitions("r3");
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertEquals(ruleDefinitions.get(0).getLevel(), DefinitionLevel.DEFAULT);
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(5, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetTestResources#rs1} when
	 * code is "r1" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsByCodeR1Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions("r1", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetTestResources#rs1} when
	 * code is "r2" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsByCodeR2Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions("r2", parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(5, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetTestResources#rs1} when
	 * code is "r3" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsByCodeR3Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions("r3", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when code
	 * is "r2" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsByCodeR2Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions("r2", parameters);
		assertNotNull(ruleDefinitions);
		assertEquals(1, ruleDefinitions.size());
		assertEquals(RuleSetVersionTestResources.r2d1, ruleDefinitions.get(0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp1JC2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp1JC2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp1CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp1CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp1JC2CLA1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp1JC2CLA1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp1JC2CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp1JC2CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(4, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp1JC1CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp1JC1CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp2R1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsJC1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsJC1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsJC2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsJC2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleDefinition> ruleDefinitions = rs.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/*
	 * Tests get...RuleVersion(s) method
	 */
	
	/**
	 * Test {@link RuleSet#getRuleVersions()} using {@link RuleSetTestResources#rs1}
	 */
	@Test
	public void testGetRuleVersionsRS1V1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions();
		assertNotNull(ruleVersions);
		assertEquals(12, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(String)} using {@link RuleSetTestResources#rs1} when code is "r2"
	 */
	@Test
	public void testGetRuleVersionsByCodeR2RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions("r2");
		assertNotNull(ruleVersions);
		assertEquals(6, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(String)} using {@link RuleSetTestResources#rs1} when code is "r3"
	 */
	@Test
	public void testGetRuleVersionsByCodeR3RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions("r3");
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1} when date is
	 * 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions(LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1} when date is
	 * 01/01/2016
	 */
	@Test
	public void testGetEffectiveAt20160101RuleVersionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions(LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(9, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1} when date is
	 * 01/07/2016
	 */
	@Test
	public void testGetEffectiveAt20160701RuleVersionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions(LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersions);
		assertEquals(9, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1} when code is "r1" and
	 * date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR1RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions("r1", LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(String, LocalDate)} using {@link RuleSetTestResources#rs1} when code is
	 * "r2" and date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR2RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions("r2", LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(String, LocalDate)} using {@link RuleSetTestResources#rs1} when code is
	 * "r3" and date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR3RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions("r3", LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(String, LocalDate)} using {@link RuleSetTestResources#rs1} when code is
	 * "r3" and date is 01/01/2016
	 */
	@Test
	public void testGetEffectiveAt20160101RuleVersionsByCodeR3RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions("r3", LocalDate.of(2016,1, 1));
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1} when code is "r1" and
	 * date is 01/01/2016
	 */
	@Test
	public void testGetEffectiveAt20160101RuleVersionsByCodeR1RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions("r1", LocalDate.of(2016,1, 1));
		assertNotNull(ruleVersions);
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
	}
	
	/**
	 * Test {@link RuleSet#getRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1} when code is "r1" and
	 * date is 01/07/2016
	 */
	@Test
	public void testGetEffectiveAt20160701RuleVersionsByCodeR1RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		Collection<RuleVersion> ruleVersions = rs.getRuleVersions("r1", LocalDate.of(2016,7, 1));
		assertNotNull(ruleVersions);
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions()} using {@link RuleSetTestResources#rs1}
	 */
	@Test
	public void testGetDefaultRuleVersionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions();
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1}
	 */
	@Test
	public void testGetEffectiveAt20150101DefaultRuleVersionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions(LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions(String, LocalDate)} using {@link RuleSetTestResources#rs1} when code is
	 * "r1" and date is 2015/01/01
	 */
	@Test
	public void testGetEffectiveAt20150101DefaultRuleVersionsByCodeR1RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions("r1", LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions(String, LocalDate)} using {@link RuleSetTestResources#rs1} when code is
	 * "r3" and date is 2015/01/01
	 */
	@Test
	public void testGetEffectiveAt20150101DefaultRuleVersionsByCodeR3RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions("r3", LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions(String, LocalDate)} using {@link RuleSetTestResources#rs1} when code is
	 * "r1" and date is 2016/01/01
	 */
	@Test
	public void testGetEffectiveAt20160101DefaultRuleVersionsByCodeR1RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions("r1", LocalDate.of(2016,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions(String, LocalDate)} using {@link RuleSetTestResources#rs1} when code is
	 * "r1" and date is 2016/07/01
	 */
	@Test
	public void testGetEffectiveAt20160701DefaultRuleVersionsByCodeR1RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions("r1", LocalDate.of(2016,7, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions(String, LocalDate)} using {@link RuleSetTestResources#rs1} when code is
	 * "r3" and date is 2016/07/01
	 */
	@Test
	public void testGetEffectiveAt20160701DefaultRuleVersionsByCodeR3RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions("r3", LocalDate.of(2016,7, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1}
	 */
	@Test
	public void testGetEffectiveAt20160101DefaultRuleVersionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions(LocalDate.of(2016,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(4, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleVersions(LocalDate)} using {@link RuleSetTestResources#rs1}
	 */
	@Test
	public void testGetEffectiveAt20160701DefaultRuleVersionsRS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions(LocalDate.of(2016,7, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(4, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefaultRuleDefinitions(String)} using {@link RuleSetTestResources#rs1} when code is "r3"
	 */
	@Test
	public void testGetDefaultRuleVersionsByCodeR3RS1() {
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefaultRuleVersions("r3");
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		assertEquals(ruleVersions.get(0).getRuleDefinition().getLevel(), DefinitionLevel.DEFAULT);
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection, LocalDate)} using {@link RuleSetTestResources#rs1}
	 * when date is 01/01/2015 and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20150101DefinedRuleVersionsEmp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters, LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(4, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection, LocalDate)} using {@link RuleSetTestResources#rs1}
	 * when date is 01/01/2016 and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20160101DefinedRuleVersionsEmp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters, LocalDate.of(2016,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when code is
	 * "r1" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsByCodeR1Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleDefinitions = rs.getDefinedRuleVersions("r1", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when code is
	 * "r2" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsByCodeR2Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleDefinitions = rs.getDefinedRuleVersions("r2", parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(5, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(String, Collection, LocalDate)} using
	 * {@link RuleSetTestResources#rs1} when code is "r2" and date is 01/01/2014 and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20140101DefinedRuleVersionsByCodeR2Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleDefinitions = rs.getDefinedRuleVersions("r2", parameters, LocalDate.of(2014,1, 1));
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(String, Collection, LocalDate)} using
	 * {@link RuleSetTestResources#rs1} when code is "r2" and date is 01/01/2015 and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20150101DefinedRuleVersionsByCodeR2Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleDefinitions = rs.getDefinedRuleVersions("r2", parameters, LocalDate.of(2015,1, 1));
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(4, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(String, Collection, LocalDate)} using
	 * {@link RuleSetTestResources#rs1} when code is "r2" and date is 01/01/2016 and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20160101DefinedRuleVersionsByCodeR2Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleDefinitions = rs.getDefinedRuleVersions("r2", parameters, LocalDate.of(2016,1, 1));
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(5, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when code is
	 * "r3" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsByCodeR3Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleDefinitions = rs.getDefinedRuleVersions("r3", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection, LocalDate)} using
	 * {@link RuleSetTestResources#rs1} when date is 01/01/2015 collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20150101BestDefinedRuleVersionsEmp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters, LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection, LocalDate)} using
	 * {@link RuleSetTestResources#rs1} when date is 01/01/2016 collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20160101BestDefinedRuleVersionsEmp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters, LocalDate.of(2016,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertFalse(ruleVersions.isEmpty());
		assertEquals(4, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection, LocalDate)} using
	 * {@link RuleSetTestResources#rs1} when date is 01/07/2016 collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20160701BestDefinedRuleVersionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters, LocalDate.of(2016,7, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertFalse(ruleVersions.isEmpty());
		assertEquals(4, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(String, Collection)} using {@link RuleSetTestResources#rs1} when
	 * code is
	 * "r2" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsByCodeR2Emp1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleDefinitions = rs.getBestDefinedRuleVersions("r2", parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1JC2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp1JC2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp1CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1JC2CLA1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp1JC2CLA1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1JC2CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp1JC2CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(8, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1JC1CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp1JC1CLA2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>EMPLOYER</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsJC1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when code is
	 * "r1" and date is 01/01/2015 and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20150101BestDefinedRuleVersionsByCodeR1JC1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions("r1", parameters, LocalDate.of(2015,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when code is
	 * "r1" and date is 01/01/2016 and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20160101BestDefinedRuleVersionsByCodeR1JC1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions("r1", parameters, LocalDate.of(2016,1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsJC1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when code is
	 * "r1" and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsByCodeR1JC1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions("r1", parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when code is
	 * "r1" and date is 01/07/2016 and collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetEffectiveAt20160701BestDefinedRuleVersionsByCodeR1JC1RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions("r1", parameters, LocalDate.of(2016,7, 1));
		assertNotNull(ruleVersions);
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
	}
	
	/**
	 * Tests {@link RuleSet#getDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when collection
	 * has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsJC2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSet#getBestDefinedRuleVersions(Collection)} using {@link RuleSetTestResources#rs1} when
	 * collection has the following parameters :
	 * <table>
	 * <tr>
	 * <th>Level</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>JOINT COMMITTEE</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsJC2RS1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSet rs = RuleSetTestResources.rs1;
		List<RuleVersion> ruleVersions = rs.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	
}
