package be.groups.glanguage.glanguage.api.entities.ruleset;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleDescription;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link RuleSetVersion}
 * 
 * @author DUPIREFR
 */
public class RuleSetVersionTest extends BaseDatabaseTest {

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

		assertEquals(LocalDateTime.of(2016, 9, 7, 9, 0), ruleSetVersion.getExploitationStartDate());
		assertEquals("1.0.1", ruleSetVersion.getVersion());
		assertNotNull(ruleSetVersion.getCreationDate());
		assertEquals("dupirefr", ruleSetVersion.getCreationAuthor());
		assertEquals("hotfix", ruleSetVersion.getLabel());
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
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersion(String, LocalDate)}}
	 * when a rule version is found
	 */
	@Test
	public void testGetDefaultRuleVersionFound() {
		String code = "r1";
		LocalDate effectivity = LocalDate.of(2015, 1,  1);
		
		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);
		
		RuleDefinition employerRuleDefinition = mock(RuleDefinition.class);
		when(employerRuleDefinition.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		
		RuleDescription ruleDescription = mock(RuleDescription.class);
		when(ruleDescription.getCode()).thenReturn("r1");
		
		RuleVersion defaultRuleVersionRightEffectivity = mock(RuleVersion.class);
		when(defaultRuleVersionRightEffectivity.getRuleDescription()).thenReturn(ruleDescription);
		when(defaultRuleVersionRightEffectivity.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersionRightEffectivity.isEffective(effectivity)).thenReturn(true);
		
		RuleVersion defaultRuleVersionWrongEffectivity = mock(RuleVersion.class);
		when(defaultRuleVersionWrongEffectivity.getRuleDescription()).thenReturn(ruleDescription);
		when(defaultRuleVersionWrongEffectivity.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersionWrongEffectivity.isEffective(effectivity)).thenReturn(false);
		
		RuleVersion employerRuleVersion = mock(RuleVersion.class);
		when(employerRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(employerRuleVersion.getRuleDefinition()).thenReturn(employerRuleDefinition);
		when(employerRuleVersion.isEffective(effectivity)).thenReturn(true);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersionRightEffectivity);
		ruleSetVersion.getRuleVersions().add(defaultRuleVersionWrongEffectivity);
		ruleSetVersion.getRuleVersions().add(employerRuleVersion);
		
		RuleVersion foundRuleVersion = ruleSetVersion.getDefaultRuleVersion(code, effectivity);
		
		assertNotNull(foundRuleVersion);
		assertEquals(defaultRuleVersionRightEffectivity, foundRuleVersion);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersion(String, LocalDate)}
	 * when a rule version exists for that code and the default definition but
	 * is not effective
	 */
	@Test
	public void testGetDefaultRuleVersionNoEffectiveRuleVersion() {
		String code = "r1";
		LocalDate effectivity = LocalDate.of(2015, 1,  1);
		
		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);
		
		RuleDefinition employerRuleDefinition = mock(RuleDefinition.class);
		when(employerRuleDefinition.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		
		RuleDescription ruleDescription = mock(RuleDescription.class);
		when(ruleDescription.getCode()).thenReturn("r1");
		
		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(false);
		
		RuleVersion employerRuleVersion = mock(RuleVersion.class);
		when(employerRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(employerRuleVersion.getRuleDefinition()).thenReturn(employerRuleDefinition);
		when(employerRuleVersion.isEffective(effectivity)).thenReturn(true);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		ruleSetVersion.getRuleVersions().add(employerRuleVersion);
		
		RuleVersion foundRuleVersion = ruleSetVersion.getDefaultRuleVersion(code, effectivity);
		
		assertNull(foundRuleVersion);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersion(String, LocalDate)}
	 * when a rule version exists for that code but not for the default
	 * definition
	 */
	@Test
	public void testGetDefaultRuleVersionNoDefaultRuleVersion() {
		String code = "r1";
		LocalDate effectivity = LocalDate.of(2015, 1,  1);
		
		RuleDefinition employerRuleDefinition = mock(RuleDefinition.class);
		when(employerRuleDefinition.getLevel()).thenReturn(DefinitionLevel.EMPLOYER);
		
		RuleDescription ruleDescription = mock(RuleDescription.class);
		when(ruleDescription.getCode()).thenReturn("r1");
		
		RuleVersion employerRuleVersion = mock(RuleVersion.class);
		when(employerRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(employerRuleVersion.getRuleDefinition()).thenReturn(employerRuleDefinition);
		when(employerRuleVersion.isEffective(effectivity)).thenReturn(true);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(employerRuleVersion);
		
		RuleVersion foundRuleVersion = ruleSetVersion.getDefaultRuleVersion(code, effectivity);
		
		assertNull(foundRuleVersion);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersion(String, LocalDate)}
	 * when no rule version exists for that code
	 */
	@Test
	public void testGetDefaultRuleVersionNoRuleVersionForCode() {
		String code = "r1";
		LocalDate effectivity = LocalDate.of(2015, 1,  1);
		
		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.getLevel()).thenReturn(DefinitionLevel.DEFAULT);
		
		RuleDescription ruleDescription = mock(RuleDescription.class);
		when(ruleDescription.getCode()).thenReturn("r2");
		
		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDescription()).thenReturn(ruleDescription);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(true);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		
		RuleVersion foundRuleVersion = ruleSetVersion.getDefaultRuleVersion(code, effectivity);
		
		assertNull(foundRuleVersion);
	}
	
	/**
	 * Tests
	 * {@link RuleSetVersion#getDefinedRuleVersions(Collection, LocalDate)}
	 * when a rule is found
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDefinedRuleVersionFound() {
		LocalDate effectivity = LocalDate.of(2015, 1,  1);
		List<RuleDefinitionParameter> ruleDefinitionParameters = (List<RuleDefinitionParameter>) mock(List.class);
		
		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.matches(eq(ruleDefinitionParameters), any())).thenReturn(false);
		
		RuleDefinition customRuleDefinition = mock(RuleDefinition.class);
		when(customRuleDefinition.matches(eq(ruleDefinitionParameters), any())).thenReturn(true);
		
		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(false);
		
		RuleVersion customRuleVersionRightEffectivity = mock(RuleVersion.class);
		when(customRuleVersionRightEffectivity.getRuleDefinition()).thenReturn(customRuleDefinition);
		when(customRuleVersionRightEffectivity.isEffective(effectivity)).thenReturn(true);
		
		RuleVersion customRuleVersionWrongEffectivity = mock(RuleVersion.class);
		when(customRuleVersionWrongEffectivity.getRuleDefinition()).thenReturn(customRuleDefinition);
		when(customRuleVersionWrongEffectivity.isEffective(effectivity)).thenReturn(false);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		ruleSetVersion.getRuleVersions().add(customRuleVersionRightEffectivity);
		ruleSetVersion.getRuleVersions().add(customRuleVersionWrongEffectivity);
		
		List<RuleVersion> foundRuleVersions = ruleSetVersion.getDefinedRuleVersions(ruleDefinitionParameters, effectivity);
		
		assertNotNull(foundRuleVersions);
		assertFalse(foundRuleVersions.isEmpty());
		assertEquals(1, foundRuleVersions.size());
		assertEquals(customRuleVersionRightEffectivity, foundRuleVersions.get(0));
	}
	
	/**
	 * Tests
	 * {@link RuleSetVersion#getDefinedRuleVersions(Collection, LocalDate)}
	 * when no effective rule version is found
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDefinedRuleVersionNoEffectiveRuleVersion() {
		LocalDate effectivity = LocalDate.of(2015, 1,  1);
		List<RuleDefinitionParameter> ruleDefinitionParameters = (List<RuleDefinitionParameter>) mock(List.class);
		
		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.matches(eq(ruleDefinitionParameters), any())).thenReturn(false);
		
		RuleDefinition customRuleDefinition = mock(RuleDefinition.class);
		when(customRuleDefinition.matches(eq(ruleDefinitionParameters), any())).thenReturn(true);
		
		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(false);
		
		RuleVersion customRuleVersion = mock(RuleVersion.class);
		when(customRuleVersion.getRuleDefinition()).thenReturn(customRuleDefinition);
		when(customRuleVersion.isEffective(effectivity)).thenReturn(false);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		ruleSetVersion.getRuleVersions().add(customRuleVersion);
		
		List<RuleVersion> foundRuleVersions = ruleSetVersion.getDefinedRuleVersions(ruleDefinitionParameters, effectivity);
		
		assertNotNull(foundRuleVersions);
		assertTrue(foundRuleVersions.isEmpty());
	}
	
	/**
	 * Tests
	 * {@link RuleSetVersion#getDefinedRuleVersions(Collection, LocalDate)}
	 * when no definition is found
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDefinedRuleVersionNoMatchingDefinition() {
		LocalDate effectivity = LocalDate.of(2015, 1,  1);
		List<RuleDefinitionParameter> ruleDefinitionParameters = (List<RuleDefinitionParameter>) mock(List.class);
		
		RuleDefinition defaultRuleDefinition = mock(RuleDefinition.class);
		when(defaultRuleDefinition.matches(eq(ruleDefinitionParameters), any())).thenReturn(false);
		
		RuleDefinition customRuleDefinition = mock(RuleDefinition.class);
		when(customRuleDefinition.matches(eq(ruleDefinitionParameters), any())).thenReturn(false);
		
		RuleVersion defaultRuleVersion = mock(RuleVersion.class);
		when(defaultRuleVersion.getRuleDefinition()).thenReturn(defaultRuleDefinition);
		when(defaultRuleVersion.isEffective(effectivity)).thenReturn(false);
		
		RuleVersion customRuleVersion = mock(RuleVersion.class);
		when(customRuleVersion.getRuleDefinition()).thenReturn(customRuleDefinition);
		when(customRuleVersion.isEffective(effectivity)).thenReturn(true);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setRuleVersions(new HashSet<>());
		ruleSetVersion.getRuleVersions().add(defaultRuleVersion);
		ruleSetVersion.getRuleVersions().add(customRuleVersion);
		
		List<RuleVersion> foundRuleVersions = ruleSetVersion.getDefinedRuleVersions(ruleDefinitionParameters, effectivity);
		
		assertNotNull(foundRuleVersions);
		assertTrue(foundRuleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#isInExploitation(LocalDateTime)} when true
	 */
	@Test
	public void testIsInExploitationTrue() {
		LocalDateTime exploitationDate = LocalDateTime.of(2015, 1,  1, 0, 0);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setExploitationStartDate(exploitationDate);
		
		assertTrue(ruleSetVersion.isInExploitation(exploitationDate));
	}
	
	/**
	 * Tests {@link RuleSetVersion#isInExploitation(LocalDateTime)} when false
	 */
	@Test
	public void testIsInExploitationFalse() {
		LocalDateTime exploitationDate = LocalDateTime.of(2015, 1,  1, 0, 0);
		
		RuleSetVersion ruleSetVersion = new RuleSetVersion();
		ruleSetVersion.setExploitationStartDate(exploitationDate);
		
		assertFalse(ruleSetVersion.isInExploitation(LocalDateTime.of(2014, 12,  31, 0, 0)));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleIdentities()} using {@link RuleSetVersionTestResources#rs1v1}
	 */
	@Test
	public void testGetRuleIdentitiesRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleIdentity> ruleIdentities = rsv.getRuleIdentities();
		assertNotNull(ruleIdentities);
		assertEquals(2, ruleIdentities.size());
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r1));
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r2));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleIdentities()}} using {@link RuleSetVersionTestResources#rs1v2}
	 */
	@Test
	public void testGetRuleIdentitiesRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleIdentity> ruleIdentities = rsv.getRuleIdentities();
		assertNotNull(ruleIdentities);
		assertEquals(3, ruleIdentities.size());
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r1));
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r2));
		assertTrue(ruleIdentities.contains(RuleSetVersionTestResources.r3));
	}
	
	/*
	 * Tests get...RuleDefinition(s) method
	 */
	
	/**
	 * Test {@link RuleSetVersion#getRuleDefinitions()} using {@link RuleSetVersionTestResources#rs1v1}
	 */
	@Test
	public void testGetRuleDefinitionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleDefinition> ruleDefinitions = rsv.getRuleDefinitions();
		assertNotNull(ruleDefinitions);
		assertEquals(6, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleDefinitions()} using {@link RuleSetVersionTestResources#rs1v2}
	 */
	@Test
	public void testGetRuleDefinitionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleDefinition> ruleDefinitions = rsv.getRuleDefinitions();
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
	 * Tests {@link RuleSetVersion#getDefaultRuleDefinitions()} using {@link RuleSetVersionTestResources#rs1v1}
	 */
	@Test
	public void testGetDefaultRuleDefinitionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefaultRuleDefinitions();
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		for (RuleDefinition ruleDefinition : ruleDefinitions) {
			assertTrue(ruleDefinition.getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleDefinitions()}
	 */
	@Test
	public void testGetDefaultRuleDefinitionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefaultRuleDefinitions();
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
	 * Tests {@link RuleSetVersion#getDefaultRuleDefinitions(String)} using {@link RuleSetVersionTestResources#rs1v1} when code is "r3"
	 */
	@Test
	public void testGetDefaultRuleDefinitionsByCodeR3RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefaultRuleDefinitions("r3");
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleDefinitions(String)} using {@link RuleSetVersionTestResources#rs1v2} when code is "r3"
	 */
	@Test
	public void testGetDefaultRuleDefinitionsByCodeR3RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefaultRuleDefinitions("r3");
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertEquals(ruleDefinitions.get(0).getLevel(), DefinitionLevel.DEFAULT);
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsEmp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(4, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsByCodeR1Emp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions("r1", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsByCodeR2Emp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions("r2", parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(4, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsByCodeR3Emp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions("r3", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
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
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsByCodeR1Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions("r1", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsByCodeR2Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions("r2", parameters);
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
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(String, Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsByCodeR3Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions("r3", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when code
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
	public void testGetBestDefinedRuleDefinitionsByCodeR2Emp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		RuleDefinition ruleDefinition = rsv.getBestDefinedRuleDefinition("r2", parameters);
		assertNotNull(ruleDefinition);
		assertEquals(RuleSetVersionTestResources.r2d1, ruleDefinition);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when code
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
	public void testGetBestDefinedRuleDefinitionsByCodeR2Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		RuleDefinition ruleDefinition = rsv.getBestDefinedRuleDefinition("r2", parameters);
		assertNotNull(ruleDefinition);
		assertEquals(RuleSetVersionTestResources.r2d1, ruleDefinition);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsEmp1JC2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsEmp1JC2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1JC2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1JC2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsEmp1CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsEmp1CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsEmp1JC2CLA1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsEmp1JC2CLA1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1JC2CLA1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1JC2CLA1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp1JC2CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsEmp1JC2CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp1JC2CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1JC2CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleDefinitionsEmp1JC1CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsEmp1JC1CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleDefinitionsEmp1JC1CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsEmp1JC1CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsEmp2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsEmp2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleDefinitionsEmp2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsEmp2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsJC1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsJC1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleDefinitionsJC1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsJC1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r3d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetDefinedRuleDefinitionsJC2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetDefinedRuleDefinitionsJC2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(3, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d5));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleDefinitionsJC2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(2, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r1d0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleDefinitions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleDefinitionsJC2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleDefinition> ruleDefinitions = rsv.getBestDefinedRuleDefinitions(parameters);
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
	 * Test {@link RuleSetVersion#getRuleVersions()} using {@link RuleSetVersionTestResources#rs1v1}
	 */
	@Test
	public void testGetRuleVersionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions();
		assertNotNull(ruleVersions);
		assertEquals(7, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions()} using {@link RuleSetVersionTestResources#rs1v2}
	 */
	@Test
	public void testGetRuleVersionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions();
		assertNotNull(ruleVersions);
		assertEquals(10, ruleVersions.size());
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
	 * Test {@link RuleSetVersion#getRuleVersions(String)} using {@link RuleSetVersionTestResources#rs1v1} when code is "r2"
	 */
	@Test
	public void testGetRuleVersionsByCodeR2RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r2");
		assertNotNull(ruleVersions);
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(String)} using {@link RuleSetVersionTestResources#rs1v1} when code is "r3"
	 */
	@Test
	public void testGetRuleVersionsByCodeR3RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r3");
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(String)} using {@link RuleSetVersionTestResources#rs1v2} when code is "r2"
	 */
	@Test
	public void testGetRuleVersionsByCodeR2RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r2");
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
	 * Test {@link RuleSetVersion#getRuleVersions(String)} using {@link RuleSetVersionTestResources#rs1v2} when code is "r3"
	 */
	@Test
	public void testGetRuleVersionsByCodeR3RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r3");
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when date is
	 * 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions(LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(6, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2} when date is
	 * 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions(LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(6, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when date is
	 * 01/01/2016
	 */
	@Test
	public void testGetEffectiveAt20160101RuleVersionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions(LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(6, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2} when date is
	 * 01/01/2016
	 */
	@Test
	public void testGetEffectiveAt20160101RuleVersionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions(LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(8, ruleVersions.size());
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
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when date is
	 * 01/07/2016
	 */
	@Test
	public void testGetEffectiveAt20160701RuleVersionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions(LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersions);
		assertEquals(6, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2} when date is
	 * 01/07/2016
	 */
	@Test
	public void testGetEffectiveAt20160701RuleVersionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions(LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersions);
		assertEquals(8, ruleVersions.size());
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
	 * Test {@link RuleSetVersion#getRuleVersions(String, LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when code is
	 * "r1" and date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR1RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r1", LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(String, LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when code is
	 * "r2" and date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR2RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r2", LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(String, LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when code is
	 * "r3" and date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR3RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r3", LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2} when code is "r1" and
	 * date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR1RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r1", LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(String, LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when code is
	 * "r2" and date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR2RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r2", LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(String, LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when code is
	 * "r3" and date is 01/01/2015
	 */
	@Test
	public void testGetEffectiveAt20150101RuleVersionsByCodeR3RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r3", LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(String, LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when code is
	 * "r3" and date is 01/01/2016
	 */
	@Test
	public void testGetEffectiveAt20160101RuleVersionsByCodeR3RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r3", LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when code is "r1" and
	 * date is 01/01/2016
	 */
	@Test
	public void testGetEffectiveAt20160101RuleVersionsByCodeR1RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r1", LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2} when code is "r1" and
	 * date is 01/01/2016
	 */
	@Test
	public void testGetEffectiveAt20160101RuleVersionsByCodeR1RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r1", LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v1} when code is "r1" and
	 * date is 01/07/2016
	 */
	@Test
	public void testGetEffectiveAt20160701RuleVersionsByCodeR1RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r1", LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
	}
	
	/**
	 * Test {@link RuleSetVersion#getRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2} when code is "r1" and
	 * date is 01/07/2016
	 */
	@Test
	public void testGetEffectiveAt20160701RuleVersionsByCodeR1RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		Collection<RuleVersion> ruleVersions = rsv.getRuleVersions("r1", LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersions);
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersions()} using {@link RuleSetVersionTestResources#rs1v1}
	 */
	@Test
	public void testGetDefaultRuleVersionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions();
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersions()} using {@link RuleSetVersionTestResources#rs1v2}
	 */
	@Test
	public void testGetDefaultRuleVersionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions();
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v1}
	 */
	@Test
	public void testGetEffectiveAt20150101DefaultRuleVersionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions(LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v1}
	 */
	@Test
	public void testGetEffectiveAt20160101DefaultRuleVersionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions(LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v1}
	 */
	@Test
	public void testGetEffectiveAt20160701DefaultRuleVersionsRS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions(LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2}
	 */
	@Test
	public void testGetEffectiveAt20150101DefaultRuleVersionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions(LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2}
	 */
	@Test
	public void testGetEffectiveAt20160101DefaultRuleVersionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions(LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleVersions(LocalDate)} using {@link RuleSetVersionTestResources#rs1v2}
	 */
	@Test
	public void testGetEffectiveAt20160701DefaultRuleVersionsRS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions(LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		for (RuleVersion ruleVersion : ruleVersions) {
			assertTrue(ruleVersion.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT));
		}
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleDefinitions(String)} using {@link RuleSetVersionTestResources#rs1v1} when code is "r3"
	 */
	@Test
	public void testGetDefaultRuleVersionsByCodeR3RS1V1() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions("r3");
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefaultRuleDefinitions(String)} using {@link RuleSetVersionTestResources#rs1v2} when code is "r3"
	 */
	@Test
	public void testGetDefaultRuleVersionsByCodeR3RS1V2() {
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefaultRuleVersions("r3");
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		assertEquals(ruleVersions.get(0).getRuleDefinition().getLevel(), DefinitionLevel.DEFAULT);
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	public void testGetDefinedRuleVersionsEmp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(4, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when code is
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
	public void testGetDefinedRuleVersionsByCodeR1Emp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r1", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when code is
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
	public void testGetDefinedRuleVersionsByCodeR2Emp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r2", parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(4, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when code is
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
	public void testGetDefinedRuleVersionsByCodeR3Emp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r3", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
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
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection, LocalDate)} using {@link RuleSetVersionTestResources#rs1v2}
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
	public void testGetEffectiveAt20150101DefinedRuleVersionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters, LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(4, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection, LocalDate)} using {@link RuleSetVersionTestResources#rs1v2}
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
	public void testGetEffectiveAt20160101DefinedRuleVersionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters, LocalDate.of(2016, 1, 1));
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
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when code is
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
	public void testGetDefinedRuleVersionsByCodeR1Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r1", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when code is
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
	public void testGetDefinedRuleVersionsByCodeR2Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r2", parameters);
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
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(String, Collection, LocalDate)} using
	 * {@link RuleSetVersionTestResources#rs1v2} when code is "r2" and date is 01/01/2014 and collection has the following parameters :
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
	public void testGetEffectiveAt20140101DefinedRuleVersionsByCodeR2Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r2", parameters, LocalDate.of(2014, 1, 1));
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(String, Collection, LocalDate)} using
	 * {@link RuleSetVersionTestResources#rs1v2} when code is "r2" and date is 01/01/2015 and collection has the following parameters :
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
	public void testGetEffectiveAt20150101DefinedRuleVersionsByCodeR2Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r2", parameters, LocalDate.of(2015, 1, 1));
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(4, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(String, Collection, LocalDate)} using
	 * {@link RuleSetVersionTestResources#rs1v2} when code is "r2" and date is 01/01/2016 and collection has the following parameters :
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
	public void testGetEffectiveAt20160101DefinedRuleVersionsByCodeR2Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r2", parameters, LocalDate.of(2016, 1, 1));
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
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when code is
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
	public void testGetDefinedRuleVersionsByCodeR3Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleDefinitions = rsv.getDefinedRuleVersions("r3", parameters);
		assertNotNull(ruleDefinitions);
		assertTrue(ruleDefinitions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleVersionsEmp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(String, Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleVersionsByCodeR2Emp1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleDefinitions = rsv.getBestDefinedRuleVersions("r2", parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection, LocalDate)} using
	 * {@link RuleSetVersionTestResources#rs1v2} when date is 01/01/2015 collection has the following parameters :
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
	public void testGetEffectiveAt20150101BestDefinedRuleVersionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters, LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection, LocalDate)} using
	 * {@link RuleSetVersionTestResources#rs1v2} when date is 01/01/2016 collection has the following parameters :
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
	public void testGetEffectiveAt20160101BestDefinedRuleVersionsEmp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters, LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection, LocalDate)} using
	 * {@link RuleSetVersionTestResources#rs1v2} when date is 01/07/2016 collection has the following parameters :
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
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters, LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d1v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(String, Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsByCodeR2Emp1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleDefinitions = rsv.getBestDefinedRuleVersions("r2", parameters);
		assertNotNull(ruleDefinitions);
		assertFalse(ruleDefinitions.isEmpty());
		assertEquals(1, ruleDefinitions.size());
		assertTrue(ruleDefinitions.contains(RuleSetVersionTestResources.r2d1v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	public void testGetDefinedRuleVersionsEmp1JC2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsEmp1JC2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleVersionsEmp1JC2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsEmp1JC2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	public void testGetDefinedRuleVersionsEmp1CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsEmp1CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleVersionsEmp1CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsEmp1CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1JC2CLA1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsEmp1JC2CLA1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleVersionsEmp1JC2CLA1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsEmp1JC2CLA1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1JC2CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsEmp1JC2CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(1, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp1JC2CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsEmp1JC2CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	 * <tr>
	 * <td>COLLECTIVE LABOR AGREEMENT</td>
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetDefinedRuleVersionsEmp1JC1CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsEmp1JC1CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	 * <td>2</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testGetBestDefinedRuleVersionsEmp1JC1CLA2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsEmp1JC1CLA2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d3v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	public void testGetDefinedRuleVersionsEmp2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsEmp2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleVersionsEmp2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsEmp2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	public void testGetDefinedRuleVersionsJC1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsJC1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertTrue(ruleVersions.isEmpty());
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleVersionsJC1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when code is
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
	public void testGetBestDefinedRuleVersionsByCodeR1JC1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions("r1", parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when code is
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
	public void testGetEffectiveAt20150101BestDefinedRuleVersionsByCodeR1JC1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		RuleVersion ruleVersion = rsv.getBestDefinedRuleVersion("r1", parameters, LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersion);
		assertEquals(RuleSetVersionTestResources.r1d0v1_0, ruleVersion);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when code is
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
	public void testGetEffectiveAt20160101BestDefinedRuleVersionsByCodeR1JC1RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		RuleVersion ruleVersion = rsv.getBestDefinedRuleVersion("r1", parameters, LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersion);
		assertEquals(RuleSetVersionTestResources.r1d0v2_0, ruleVersion);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsJC1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when code is
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
	public void testGetBestDefinedRuleVersionsByCodeR1JC1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions("r1", parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when code is
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
	public void testGetEffectiveAt20150101BestDefinedRuleVersionsByCodeR1JC1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		RuleVersion ruleVersion = rsv.getBestDefinedRuleVersion("r1", parameters, LocalDate.of(2015, 1, 1));
		assertNotNull(ruleVersion);
		assertEquals(RuleSetVersionTestResources.r1d0v1_1, ruleVersion);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when code is
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
	public void testGetEffectiveAt20160101BestDefinedRuleVersionsByCodeR1JC1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		RuleVersion ruleVersion = rsv.getBestDefinedRuleVersion("r1", parameters, LocalDate.of(2016, 1, 1));
		assertNotNull(ruleVersion);
		assertEquals(RuleSetVersionTestResources.r1d0v2_1, ruleVersion);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when code is
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
	public void testGetEffectiveAt20160701BestDefinedRuleVersionsByCodeR1JC1RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "1"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		RuleVersion ruleVersion = rsv.getBestDefinedRuleVersion("r1", parameters, LocalDate.of(2016, 7, 1));
		assertNotNull(ruleVersion);
		assertEquals(RuleSetVersionTestResources.r1d0v3_0, ruleVersion);
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when collection
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
	public void testGetDefinedRuleVersionsJC2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(2, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when collection
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
	public void testGetDefinedRuleVersionsJC2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d2v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d4v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d5v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v1} when
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
	public void testGetBestDefinedRuleVersionsJC2RS1V1() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v1;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(3, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
	}
	
	/**
	 * Tests {@link RuleSetVersion#getBestDefinedRuleVersions(Collection)} using {@link RuleSetVersionTestResources#rs1v2} when
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
	public void testGetBestDefinedRuleVersionsJC2RS1V2() {
		Collection<RuleDefinitionParameter> parameters = new ArrayList<>();
		parameters.add(new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2"));
		
		RuleSetVersion rsv = RuleSetVersionTestResources.rs1v2;
		List<RuleVersion> ruleVersions = rsv.getBestDefinedRuleVersions(parameters);
		assertNotNull(ruleVersions);
		assertFalse(ruleVersions.isEmpty());
		assertEquals(5, ruleVersions.size());
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v1_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v2_1));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r1d0v3_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r2d0v1_0));
		assertTrue(ruleVersions.contains(RuleSetVersionTestResources.r3d0v1_0));
	}
	
}
