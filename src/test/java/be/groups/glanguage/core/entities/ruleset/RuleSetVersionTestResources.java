package be.groups.glanguage.core.entities.ruleset;

import be.groups.glanguage.core.entities.rule.RuleDefinition;
import be.groups.glanguage.core.entities.rule.RuleDescription;
import be.groups.glanguage.core.entities.rule.RuleIdentity;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class RuleSetVersionTestResources {

	protected static final RuleSetVersion rs1v1;
	protected static final RuleSetVersion rs1v2;
	
	protected static final RuleIdentity r1;
	protected static final RuleIdentity r2;
	protected static final RuleIdentity r3;
	
	protected static final RuleDefinition r1d0;
	protected static final RuleDefinition r2d0;
	protected static final RuleDefinition r2d1;
	protected static final RuleDefinition r2d2;
	protected static final RuleDefinition r2d3;
	protected static final RuleDefinition r2d4;
	protected static final RuleDefinition r2d5;
	protected static final RuleDefinition r3d0;
	
	protected static final RuleVersion r1d0v1_0;
	protected static final RuleVersion r1d0v2_0;
	protected static final RuleVersion r1d0v1_1;
	protected static final RuleVersion r1d0v2_1;
	protected static final RuleVersion r1d0v3_0;
	protected static final RuleVersion r2d0v1_0;
	protected static final RuleVersion r2d1v1_0;
	protected static final RuleVersion r2d2v1_0;
	protected static final RuleVersion r2d3v1_0;
	protected static final RuleVersion r2d4v1_0;
	protected static final RuleVersion r2d5v1_0;
	protected static final RuleVersion r3d0v1_0;
	
	protected static final RuleDefinitionParameter parameterEmployer1;
	protected static final RuleDefinitionParameter parameterJointCommittee2;
	protected static final RuleDefinitionParameter parameterCollectiveLborAgreement1;
	protected static final RuleDefinitionParameter parameterCollectiveLborAgreement2;
	
	static {
		/* Creation */
		rs1v1 = new RuleSetVersion();
		rs1v2 = new RuleSetVersion();
		
		r1 = new RuleIdentity();
		r2 = new RuleIdentity();
		r3 = new RuleIdentity();
		
		r1d0 = new RuleDefinition();
		r2d0 = new RuleDefinition();
		r2d1 = new RuleDefinition();
		r2d2 = new RuleDefinition();
		r2d3 = new RuleDefinition();
		r2d4 = new RuleDefinition();
		r2d5 = new RuleDefinition();
		r3d0 = new RuleDefinition();
		
		r1d0v1_0 = new RuleVersion();
		r1d0v2_0 = new RuleVersion();
		r1d0v1_1 = new RuleVersion();
		r1d0v2_1 = new RuleVersion();
		r1d0v3_0 = new RuleVersion();
		r2d0v1_0 = new RuleVersion();
		r2d1v1_0 = new RuleVersion();
		r2d2v1_0 = new RuleVersion();
		r2d3v1_0 = new RuleVersion();
		r2d4v1_0 = new RuleVersion();
		r2d5v1_0 = new RuleVersion();
		r3d0v1_0 = new RuleVersion();
		
		parameterEmployer1 = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "1");
		parameterJointCommittee2 = new RuleDefinitionParameter(DefinitionLevel.JOINT_COMMITTEE, "2");
		parameterCollectiveLborAgreement1 = new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "1");
		parameterCollectiveLborAgreement2 = new RuleDefinitionParameter(DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT, "2");
		
		/* R1 */
		r1.setId(1L);
		/* R1 D0 */
		r1d0.setId(10L);
		r1d0.setRuleIdentity(r1);
		/* R1 definitions */
		r1.getRuleDefinitions().add(r1d0);
		/* R1 description */
		RuleDescription r1Description = new RuleDescription();
		r1Description.setCode("r1");
		/* R1 D0 V1_0 */
		r1d0v1_0.setId(101L);
		r1d0v1_0.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		r1d0v1_0.setEffectiveEndDate(LocalDate.of(2015, 12, 31));
		r1d0v1_0.setRuleDefinition(r1d0);
		r1d0v1_0.setRuleDescription(r1Description);
		/* R1 D0 V1_1 */
		r1d0v1_1.setId(1011L);
		r1d0v1_1.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		r1d0v1_1.setEffectiveEndDate(LocalDate.of(2015, 12, 31));
		r1d0v1_1.setRuleDefinition(r1d0);
		r1d0v1_1.setRuleDescription(r1Description);
		/* R1 D0 V2_0 */
		r1d0v2_0.setId(102L);
		r1d0v2_0.setEffectiveStartDate(LocalDate.of(2016, 1, 1));
		r1d0v2_0.setEffectiveEndDate(LocalDate.MAX);
		r1d0v2_0.setRuleDefinition(r1d0);
		r1d0v2_0.setRuleDescription(r1Description);
		/* R1 D0 V2_1 */
		r1d0v2_1.setId(1021L);
		r1d0v2_1.setEffectiveStartDate(LocalDate.of(2016, 1, 1));
		r1d0v2_1.setEffectiveEndDate(LocalDate.of(2016, 6, 30));
		r1d0v2_1.setRuleDefinition(r1d0);
		r1d0v2_1.setRuleDescription(r1Description);
		/* R1 D0 V3_0 */
		r1d0v3_0.setId(103L);
		r1d0v3_0.setEffectiveStartDate(LocalDate.of(2016, 7, 1));
		r1d0v3_0.setEffectiveEndDate(LocalDate.MAX);
		r1d0v3_0.setRuleDefinition(r1d0);
		r1d0v3_0.setRuleDescription(r1Description);
		/* R1 D0 versions */
		Set<RuleVersion> r1d0Versions = new TreeSet<>();
		r1d0Versions.add(r1d0v1_0);
		r1d0Versions.add(r1d0v1_1);
		r1d0Versions.add(r1d0v2_0);
		r1d0Versions.add(r1d0v2_1);
		r1d0Versions.add(r1d0v3_0);
		r1d0.setVersions(r1d0Versions);
		
		/* R2 */
		r2.setId(2L);
		/* R2 description */
		RuleDescription r2Description = new RuleDescription();
		r2Description.setCode("r2");
		
		/* R2 D0 */
		r2d0.setId(20L);
		r2d0.setRuleIdentity(r2);
		/* R2 D0 V1_0 */
		r2d0v1_0.setId(201L);
		r2d0v1_0.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		r2d0v1_0.setEffectiveEndDate(LocalDate.MAX);
		r2d0v1_0.setRuleDefinition(r2d0);
		r2d0v1_0.setRuleDescription(r2Description);
		/* R2 D0 versions */
		Set<RuleVersion> r2d0Versions = new TreeSet<>();
		r2d0Versions.add(r2d0v1_0);
		r2d0.setVersions(r2d0Versions);
		
		/* R2 D1 */
		r2d1.setId(21L);
		r2d1.setRuleIdentity(r2);
		/* R2 D1 definition parameters */
		r2d1.getDefinitionParameters().add(parameterEmployer1);
		/* R2 D1 V1_0 */
		r2d1v1_0.setId(211L);
		r2d1v1_0.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		r2d1v1_0.setEffectiveEndDate(LocalDate.MAX);
		r2d1v1_0.setRuleDefinition(r2d1);
		r2d1v1_0.setRuleDescription(r2Description);
		/* R2 D1 versions */
		r2d1.getVersions().add(r2d1v1_0);
		
		/* R2 D2 */
		r2d2.setId(22L);
		r2d2.setRuleIdentity(r2);
		/* R2 D2 definition parameters */
		r2d2.getDefinitionParameters().add(parameterEmployer1);
		r2d2.getDefinitionParameters().add(parameterJointCommittee2);
		/* R2 D2 V1_0 */
		r2d2v1_0.setId(221L);
		r2d2v1_0.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		r2d2v1_0.setEffectiveEndDate(LocalDate.MAX);
		r2d2v1_0.setRuleDefinition(r2d2);
		r2d2v1_0.setRuleDescription(r2Description);
		/* R2 D2 versions */
		r2d2.getVersions().add(r2d2v1_0);
		
		/* R2 D3 */
		r2d3.setId(23L);
		r2d3.setRuleIdentity(r2);
		/* R2 D3 definition parameters */
		r2d3.getDefinitionParameters().add(parameterEmployer1);
		r2d3.getDefinitionParameters().add(parameterCollectiveLborAgreement2);		
		/* R2 D3 V1_0 */
		r2d3v1_0.setId(231L);
		r2d3v1_0.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		r2d3v1_0.setEffectiveEndDate(LocalDate.MAX);
		r2d3v1_0.setRuleDefinition(r2d3);
		r2d3v1_0.setRuleDescription(r2Description);
		/* R2 D3 versions */
		r2d3.getVersions().add(r2d3v1_0);
		
		/* R2 D4 */
		r2d4.setId(24L);
		r2d4.setRuleIdentity(r2);
		/* R2 D4 definition parameters */
		r2d4.getDefinitionParameters().add(parameterEmployer1);
		r2d4.getDefinitionParameters().add(parameterJointCommittee2);
		r2d4.getDefinitionParameters().add(parameterCollectiveLborAgreement1);
		/* R2 D4 V1_0 */
		r2d4v1_0.setId(241L);
		r2d4v1_0.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		r2d4v1_0.setEffectiveEndDate(LocalDate.MAX);
		r2d4v1_0.setRuleDefinition(r2d4);
		r2d4v1_0.setRuleDescription(r2Description);
		/* R2 D4 versions */
		r2d4.getVersions().add(r2d4v1_0);
		
		/* R2 D5 */
		r2d5.setId(25L);
		r2d5.setRuleIdentity(r2);
		/* R2 D5 definition parameters */
		r2d5.getDefinitionParameters().add(parameterEmployer1);
		r2d5.getDefinitionParameters().add(parameterJointCommittee2);
		r2d5.getDefinitionParameters().add(parameterCollectiveLborAgreement2);
		/* R2 D5 V1_0 */
		r2d5v1_0.setId(251L);
		r2d5v1_0.setEffectiveStartDate(LocalDate.of(2016, 1, 1));
		r2d5v1_0.setEffectiveEndDate(LocalDate.MAX);
		r2d5v1_0.setRuleDefinition(r2d5);
		r2d5v1_0.setRuleDescription(r2Description);
		/* R2 D5 versions */
		r2d5.getVersions().add(r2d5v1_0);
				
		/* R2 definitions */
		r2.getRuleDefinitions().add(r2d0);
		r2.getRuleDefinitions().add(r2d1);
		r2.getRuleDefinitions().add(r2d2);
		r2.getRuleDefinitions().add(r2d3);
		r2.getRuleDefinitions().add(r2d4);
		r2.getRuleDefinitions().add(r2d5);
		
		/* R3 */
		r3.setId(3L);
		/* R3 description */
		RuleDescription r3Description = new RuleDescription();
		r3Description.setCode("r3");
		
		/* R3 D0 */
		r3d0.setId(30L);
		r3d0.setRuleIdentity(r3);
		/* R3 D0 V1_0 */
		r3d0v1_0.setId(301L);
		r3d0v1_0.setEffectiveStartDate(LocalDate.of(2016, 1, 1));
		r3d0v1_0.setEffectiveEndDate(LocalDate.MAX);
		r3d0v1_0.setRuleDefinition(r3d0);
		r3d0v1_0.setRuleDescription(r3Description);	
		/* R3 D0 versions */
		r3d0.getVersions().add(r3d0v1_0);
		
		/* R3 definitions */
		r3.getRuleDefinitions().add(r3d0);
		
		/* RS1 V1 */
		rs1v1.setId(1L);
		rs1v1.setVersion("1.0.0");
		rs1v1.setProductionStartDate(LocalDateTime.of(2015, 1, 1, 0, 0));
		/* RS1 V1 versiosn */
		Set<RuleVersion> rs1v1Versions = new HashSet<>();
		rs1v1Versions.add(r1d0v1_0);
		rs1v1Versions.add(r1d0v2_0);
		rs1v1Versions.add(r2d0v1_0);
		rs1v1Versions.add(r2d1v1_0);
		rs1v1Versions.add(r2d2v1_0);
		rs1v1Versions.add(r2d3v1_0);
		rs1v1Versions.add(r2d4v1_0);
		rs1v1.setRuleVersions(rs1v1Versions);		
		
		/* RS1 V2 */
		rs1v2.setId(2L);
		rs1v2.setVersion("2.0.0");
		rs1v2.setProductionStartDate(LocalDateTime.of(2016, 1, 1, 0, 0));
		/* RS1 V1 versiosn */
		Set<RuleVersion> rs1v2Versions = new HashSet<>();
		rs1v2Versions.add(r1d0v1_1);
		rs1v2Versions.add(r1d0v2_1);
		rs1v2Versions.add(r1d0v3_0);
		rs1v2Versions.add(r2d0v1_0);
		rs1v2Versions.add(r2d1v1_0);
		rs1v2Versions.add(r2d2v1_0);
		rs1v2Versions.add(r2d3v1_0);
		rs1v2Versions.add(r2d4v1_0);
		rs1v2Versions.add(r2d5v1_0);
		rs1v2Versions.add(r3d0v1_0);
		rs1v2.setRuleVersions(rs1v2Versions);
	}
	
}
