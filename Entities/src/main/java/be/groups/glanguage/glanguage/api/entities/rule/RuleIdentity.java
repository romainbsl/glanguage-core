package be.groups.glanguage.glanguage.api.entities.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;

/**
 * A RuleIdentity is the unique identity of a rule A RuleIdentity has a unique
 * code identifying it
 * 
 * @author michotte
 */
@Table(name = "RULE_IDENTITY")
@Entity
@SuppressWarnings("unused")
public class RuleIdentity implements Comparable<RuleIdentity> {

	/**
	 * Technical unique id
	 */
	private int id;

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
	 * Collection of {@link RuleDefinition}'s of
	 * {@link DefinitioneLevel.SOCIAL_SECRETARY} level
	 */
	private Collection<RuleDefinition> socialSecretaryRuleDefintions;

	/**
	 * Collection of {@link RuleDefinition}'s of
	 * {@link DefinitioneLevel.EMPLOYER} level
	 */
	private Collection<RuleDefinition> employerRuleDefintions;

	/**
	 * Collection of {@link RuleDefinition}'s of
	 * {@link DefinitioneLevel.JOINT_COMMITTEE} level
	 */
	private Collection<RuleDefinition> jointCommitteeRuleDefintions;

	/**
	 * Collection of {@link RuleDefinition}'s of
	 * {@link DefinitioneLevel.COLLECTIVE_LABOR_AGREEMENT} level
	 */
	private Collection<RuleDefinition> collectiveLaborAgreementRuleDefintions;

	/**
	 * Collection of {@link RuleDefinition}'s of {@link DefinitioneLevel.CUSTOM}
	 * level
	 */
	private Collection<RuleDefinition> customRuleDefintions;

	public RuleIdentity() {
		super();

		this.ruleDefinitions = new HashSet<>();
	}

	/**
	 * @return the id
	 */
	@Id
	public int getId() {
		return id;
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
	 * A RuleDefinition corresponds to a RuleDefinitionLevelDescription if the
	 * RuleDefinition's RuleDefinitionLevelDescription matches the
	 * RuleDefinitionLevelDescription
	 * 
	 * @param description
	 *            the RuleDefinitionLevelDescription to which the RuleDefinition
	 *            has to correspond
	 * @return the RuleDefinition corresponding to the description if it exists,
	 *         null otherwise
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
	 * Allocate all ruleDefintions to the right map or set depending on its
	 * level
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
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param ruleDefinitions
	 *            the ruleDefinitions to set
	 */
	private void setRuleDefinitions(Set<RuleDefinition> ruleDefinitions) {
		this.ruleDefinitions = ruleDefinitions;
	}

	/**
	 * @param groupItems
	 *            the groupItems to set
	 */
	public void setGroupItems(Collection<RuleGroupItem> groupItems) {
		this.groupItems = groupItems;
	}

	/**
	 * @param defaultRuleDefinition
	 *            the defaultRuleDefinition to set
	 */
	private void setDefaultRuleDefinition(RuleDefinition defaultRuleDefinition) {
		this.defaultRuleDefinition = defaultRuleDefinition;
	}

	/**
	 * @param socialSecretaryRuleDefintions
	 *            the socialSecretaryRuleDefintions to set
	 */
	private void setSocialSecretaryRuleDefintions(Collection<RuleDefinition> socialSecretaryRuleDefintions) {
		this.socialSecretaryRuleDefintions = socialSecretaryRuleDefintions;
	}

	/**
	 * @param employerRuleDefintions
	 *            the employerRuleDefintions to set
	 */
	private void setEmployerRuleDefintions(Collection<RuleDefinition> employerRuleDefintions) {
		this.employerRuleDefintions = employerRuleDefintions;
	}

	/**
	 * @param jointCommitteRuleDefintions
	 *            the jointCommitteRuleDefintions to set
	 */
	private void setJointCommitteeRuleDefintions(Collection<RuleDefinition> jointCommitteRuleDefintions) {
		this.jointCommitteeRuleDefintions = jointCommitteRuleDefintions;
	}

	/**
	 * @param collectiveLaborAgreementRuleDefintions
	 *            the collectiveLaborAgreementRuleDefintions to set
	 */
	private void setCollectiveLaborAgreementRuleDefintions(
			Collection<RuleDefinition> collectiveLaborAgreementRuleDefintions) {
		this.collectiveLaborAgreementRuleDefintions = collectiveLaborAgreementRuleDefintions;
	}

	/**
	 * @param customRuleDefintions
	 *            the customRuleDefintions to set
	 */
	private void setCustomRuleDefintions(Collection<RuleDefinition> customRuleDefintions) {
		this.customRuleDefintions = customRuleDefintions;
	}

	public int compareTo(RuleIdentity o) {
		return id - o.id;
	}

}
