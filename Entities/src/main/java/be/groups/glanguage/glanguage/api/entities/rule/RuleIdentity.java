package be.groups.glanguage.glanguage.api.entities.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;

/**
 * A RuleIdentity is the unique identity of a rule
 * A RuleIdentity has a unique code identifying it
 * 
 * @author michotte
 */
@Table(name = "RULE_IDENTITY")
@Entity
@SuppressWarnings("unused")
public class RuleIdentity implements Comparable<RuleIdentity> {
	
	/**
	 * Code
	 */
	private String code;
	
	/**
	 * Alias in French language
	 */
	private String aliasFr;
	
	/**
	 * Alias in Dutch language
	 */
	private String aliasNl;
	
	/**
	 * Alias in German language
	 */
	private String aliasDe;
	
	/**
	 * Alias in other language
	 */
	private String aliasX;
	
	/**
	 * Description in French language
	 */
	private String descriptionFr;
	
	/**
	 * Description in Dutch language
	 */
	private String descriptionNl;
	
	/**
	 * Description in German language
	 */
	private String descriptionDe;
	
	/**
	 * Description in other language
	 */
	private String descriptionX;
	
	/**
	 * Set of RuleDefinition
	 */
	private Set<RuleDefinition> ruleDefinitions;
	
	/**
	 * Group items
	 */
	private Collection<RuleGroupItem> groupItems;
	
	/**
	 * Default RuleDefintion
	 */
	private RuleDefinition defaultRuleDefinition;
	
	/**
	 * Collection of {@link RuleDefinition}'s of {@link DefinitioneLevel.SOCIAL_SECRETARY} level
	 */
	private Collection<RuleDefinition> socialSecretaryRuleDefintions;
	
	/**
	 * Collection of {@link RuleDefinition}'s of {@link DefinitioneLevel.EMPLOYER} level
	 */
	private Collection<RuleDefinition> employerRuleDefintions;
	
	/**
	 * Collection of {@link RuleDefinition}'s of {@link DefinitioneLevel.JOINT_COMMITTEE} level
	 */
	private Collection<RuleDefinition> jointCommitteeRuleDefintions;
	
	/**
	 * Collection of {@link RuleDefinition}'s of {@link DefinitioneLevel.COLLECTIVE_LABOR_AGREEMENT} level
	 */
	private Collection<RuleDefinition> collectiveLaborAgreementRuleDefintions;
	
	/**
	 * Collection of {@link RuleDefinition}'s of {@link DefinitioneLevel.CUSTOM} level
	 */
	private Collection<RuleDefinition> customRuleDefintions;
	
	public RuleIdentity() {
		super();
	}
	
	/**
	 * @return the code
	 */
	@Id
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}
	
	/**
	 * @return the aliasFr
	 */
	@Column(name = "ALIAS_FR")
	public String getAliasFr() {
		return aliasFr;
	}
	
	/**
	 * @return the aliasNl
	 */
	@Column(name = "ALIAS_NL")
	public String getAliasNl() {
		return aliasNl;
	}
	
	/**
	 * @return the aliasDe
	 */
	@Column(name = "ALIAS_DE")
	public String getAliasDe() {
		return aliasDe;
	}
	
	/**
	 * @return the aliasX
	 */
	@Column(name = "ALIAS_X")
	public String getAliasX() {
		return aliasX;
	}
	
	/**
	 * @return the descriptionFr
	 */
	@Column(name = "DESCRIPTION_FR")
	public String getDescriptionFr() {
		return descriptionFr;
	}
	
	/**
	 * @return the descriptionNl
	 */
	@Column(name = "DESCRIPTION_NL")
	public String getDescriptionNl() {
		return descriptionNl;
	}
	
	/**
	 * @return the descriptionDe
	 */
	@Column(name = "DESCRIPTION_DE")
	public String getDescriptionDe() {
		return descriptionDe;
	}
	
	/**
	 * @return the descriptionX
	 */
	@Column(name = "DESCRIPTION_X")
	public String getDescriptionX() {
		return descriptionX;
	}
	
	/**
	 * @return the ruleDefinitions
	 */
	@OneToMany(mappedBy = "ruleIdentity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<RuleDefinition> getRuleDefinitions() {
		return ruleDefinitions;
	}
	
	/**
	 * @return the groupItems
	 */
	@OneToMany(mappedBy = "ruleIdentity")
	public Collection<RuleGroupItem> getGroupItems() {
		return groupItems;
	}
	
	/**
	 * @return the defaultRuleDefinition
	 */
	@Transient
	public RuleDefinition getDefaultRuleDefinition() {
		if (defaultRuleDefinition == null) {
			allocateAllRuleDefinitions();
		}
		return defaultRuleDefinition;
	}
	
	/**
	 * @return the socialSecretaryRuleDefintions
	 */
	@Transient
	public Collection<RuleDefinition> getSocialSecretaryRuleDefintions() {
		if (socialSecretaryRuleDefintions == null) {
			allocateAllRuleDefinitions();
		}
		return socialSecretaryRuleDefintions;
	}
	
	/**
	 * @return the employerRuleDefintions
	 */
	@Transient
	public Collection<RuleDefinition> getEmployerRuleDefintions() {
		if (employerRuleDefintions == null) {
			allocateAllRuleDefinitions();
		}
		return employerRuleDefintions;
	}
	
	/**
	 * @return the jointCommitteRuleDefintions
	 */
	@Transient
	public Collection<RuleDefinition> getJointCommitteeRuleDefintions() {
		if (jointCommitteeRuleDefintions == null) {
			allocateAllRuleDefinitions();
		}
		return jointCommitteeRuleDefintions;
	}
	
	/**
	 * @return the collectiveLaborAgreementRuleDefintions
	 */
	@Transient
	public Collection<RuleDefinition> getCollectiveLaborAgreementRuleDefintions() {
		if (collectiveLaborAgreementRuleDefintions == null) {
			allocateAllRuleDefinitions();
		}
		return collectiveLaborAgreementRuleDefintions;
	}
	
	/**
	 * @return the customRuleDefintions
	 */
	@Transient
	public Collection<RuleDefinition> getCustomRuleDefintions() {
		if (customRuleDefintions == null) {
			allocateAllRuleDefinitions();
		}
		return customRuleDefintions;
	}
	
	/**
	 * Get the RuleDefinition corresponding to a RuleDefinitionLevelDescription
	 * A RuleDefinition corresponds to a RuleDefinitionLevelDescription if the RuleDefinition's RuleDefinitionLevelDescription matches
	 * the RuleDefinitionLevelDescription
	 * 
	 * @param description the RuleDefinitionLevelDescription to which the RuleDefinition has to correspond
	 * @return the RuleDefinition corresponding to the description if it exists, null otherwise
	 */
	@Transient
	public RuleDefinition getDefinition(Collection<RuleDefinitionParameter> parameters) {
		if (defaultRuleDefinition == null || socialSecretaryRuleDefintions == null || employerRuleDefintions == null
				|| jointCommitteeRuleDefintions == null || collectiveLaborAgreementRuleDefintions == null
				|| customRuleDefintions == null) {
			allocateAllRuleDefinitions();
		}
		// Search in this "custom" level definitions
		for (RuleDefinition definition : customRuleDefintions) {
			if (definition.match(parameters)) {
				return definition;
			}
		}
		// Search in this "collective labor agreement" level definitions
		for (RuleDefinition definition : collectiveLaborAgreementRuleDefintions) {
			if (definition.match(parameters)) {
				return definition;
			}
		}
		// Search in this "joint committee" level definitions
		for (RuleDefinition definition : jointCommitteeRuleDefintions) {
			if (definition.match(parameters)) {
				return definition;
			}
		}
		// Search in this "employer" level definitions
		for (RuleDefinition definition : employerRuleDefintions) {
			if (definition.match(parameters)) {
				return definition;
			}
		}
		// Search in this "social secretary" level definitions
		for (RuleDefinition definition : socialSecretaryRuleDefintions) {
			if (definition.match(parameters)) {
				return definition;
			}
		}
		// Return "default" definition
		return getDefaultRuleDefinition();
	}
	
	/**
	 * Allocate all ruleDefintions to the right map or set depending on its level
	 */
	private void allocateAllRuleDefinitions() {
		// Create all maps and set
		this.socialSecretaryRuleDefintions = new ArrayList<RuleDefinition>();
		this.employerRuleDefintions = new ArrayList<RuleDefinition>();
		this.jointCommitteeRuleDefintions = new ArrayList<RuleDefinition>();
		this.collectiveLaborAgreementRuleDefintions = new ArrayList<RuleDefinition>();
		this.customRuleDefintions = new ArrayList<RuleDefinition>();
		
		// Allocate all rule definitions
		Iterator<RuleDefinition> itRuleDefinitions = ruleDefinitions.iterator();
		while (itRuleDefinitions.hasNext()) {
			RuleDefinition ruleDefinition = itRuleDefinitions.next();
			switch (ruleDefinition.getLevel()) {
				case DEFAULT:
					defaultRuleDefinition = ruleDefinition;
					break;
				case SOCIAL_SECRETARY:
					socialSecretaryRuleDefintions.add(ruleDefinition);
					break;
				case EMPLOYER:
					employerRuleDefintions.add(ruleDefinition);
					break;
				case JOINT_COMMITTEE:
					jointCommitteeRuleDefintions.add(ruleDefinition);
					break;
				case COLLECTIVE_LABOR_AGREEMENT:
					collectiveLaborAgreementRuleDefintions.add(ruleDefinition);
					break;
				case CUSTOM:
					customRuleDefintions.add(ruleDefinition);
					break;
				default:
					assert false;
			}
		}
	}
	
	/**
	 * @param code the code to set
	 */
	private void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @param aliasFr the aliasFr to set
	 */
	public void setAliasFr(String aliasFr) {
		this.aliasFr = aliasFr;
	}
	
	/**
	 * @param aliasNl the aliasNl to set
	 */
	public void setAliasNl(String aliasNl) {
		this.aliasNl = aliasNl;
	}
	
	/**
	 * @param aliasDe the aliasDe to set
	 */
	public void setAliasDe(String aliasDe) {
		this.aliasDe = aliasDe;
	}
	
	/**
	 * @param aliasX the aliasX to set
	 */
	public void setAliasX(String aliasX) {
		this.aliasX = aliasX;
	}
	
	/**
	 * @param descriptionFr the descriptionFr to set
	 */
	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}
	
	/**
	 * @param descriptionNl the descriptionNl to set
	 */
	public void setDescriptionNl(String descriptionNl) {
		this.descriptionNl = descriptionNl;
	}
	
	/**
	 * @param descriptionDe the descriptionDe to set
	 */
	public void setDescriptionDe(String descriptionDe) {
		this.descriptionDe = descriptionDe;
	}
	
	/**
	 * @param descriptionX the descriptionX to set
	 */
	public void setDescriptionX(String descriptionX) {
		this.descriptionX = descriptionX;
	}
	
	/**
	 * @param ruleDefinitions the ruleDefinitions to set
	 */
	private void setRuleDefinitions(Set<RuleDefinition> ruleDefinitions) {
		this.ruleDefinitions = ruleDefinitions;
	}
	
	/**
	 * @param groupItems the groupItems to set
	 */
	public void setGroupItems(Collection<RuleGroupItem> groupItems) {
		this.groupItems = groupItems;
	}

	/**
	 * @param defaultRuleDefinition the defaultRuleDefinition to set
	 */
	private void setDefaultRuleDefinition(RuleDefinition defaultRuleDefinition) {
		this.defaultRuleDefinition = defaultRuleDefinition;
	}
	
	/**
	 * @param socialSecretaryRuleDefintions the socialSecretaryRuleDefintions to set
	 */
	private void setSocialSecretaryRuleDefintions(Collection<RuleDefinition> socialSecretaryRuleDefintions) {
		this.socialSecretaryRuleDefintions = socialSecretaryRuleDefintions;
	}
	
	/**
	 * @param employerRuleDefintions the employerRuleDefintions to set
	 */
	private void setEmployerRuleDefintions(Collection<RuleDefinition> employerRuleDefintions) {
		this.employerRuleDefintions = employerRuleDefintions;
	}
	
	/**
	 * @param jointCommitteRuleDefintions the jointCommitteRuleDefintions to set
	 */
	private void setJointCommitteeRuleDefintions(Collection<RuleDefinition> jointCommitteRuleDefintions) {
		this.jointCommitteeRuleDefintions = jointCommitteRuleDefintions;
	}
	
	/**
	 * @param collectiveLaborAgreementRuleDefintions the collectiveLaborAgreementRuleDefintions to set
	 */
	private void setCollectiveLaborAgreementRuleDefintions(Collection<RuleDefinition> collectiveLaborAgreementRuleDefintions) {
		this.collectiveLaborAgreementRuleDefintions = collectiveLaborAgreementRuleDefintions;
	}
	
	/**
	 * @param customRuleDefintions the customRuleDefintions to set
	 */
	private void setCustomRuleDefintions(Collection<RuleDefinition> customRuleDefintions) {
		this.customRuleDefintions = customRuleDefintions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleIdentity other = (RuleIdentity) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public int compareTo(RuleIdentity o) {
		return code.compareTo(o.code);
	}
	
}
