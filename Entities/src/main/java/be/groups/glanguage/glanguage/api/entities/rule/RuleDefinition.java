package be.groups.glanguage.glanguage.api.entities.rule;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;

/**
 * A RuleDefintion is a definition of a RuleIdentity for a specific DefinitionLevel
 * 
 * @author michotte
 */
@Entity
@Table(name = "RULE_DEFINITION")
@SuppressWarnings("unused")
public class RuleDefinition {
	
	/**
	 * Technical unique ID
	 */
	private int id;
	
	/**
	 * The RuleIdentity of which this is a definition
	 */
	private RuleIdentity ruleIdentity;
	
	/**
	 * The sorted set of definition parameters
	 */
	private SortedSet<RuleDefinitionParameter> definitionParameters;
	
	/**
	 * Versions
	 */
	private SortedSet<RuleVersion> versions;
	
	public RuleDefinition() {
		super();
		versions = new TreeSet<RuleVersion>();
	}
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name = "ID")
	public int getId() {
		return id;
	}
	
	/**
	 * @return the identity
	 */
	@ManyToOne
	@JoinColumn(name = "RULE_IDENTITY_ID")
	public RuleIdentity getRuleIdentity() {
		return ruleIdentity;
	}
	
	/**
	 * @return the definitionParameters
	 */
	@OneToMany(mappedBy = "ruleDefinition")
	@OrderBy(value = "level, value")
	public SortedSet<RuleDefinitionParameter> getDefinitionParameters() {
		return definitionParameters;
	}

	/**
	 * @return the versions
	 */
	@OneToMany(mappedBy = "ruleDefinition", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("EXPLOITATION_START_DATE DESC, EFFECTIVITY_START_DATE DESC")
	public SortedSet<RuleVersion> getVersions() {
		return versions;
	}
	
	/**
	 * Get the version effective at specified effective and observe dates
	 * 
	 * @param effective the date on which the version returned is effective
	 * @param observe the date on which the version returned is in exploitation
	 * @return the version that is effective at the specified dates if it exists, null otherwise
	 */
	@Transient
	public RuleVersion getVersion(LocalDate effective, LocalDate observe) {
		// By annotation @OrderBy on getVersions() method, the first version in the list that is in exploitation at observe date and
		// effective at effective date is the right version to be returned
		Iterator<RuleVersion> itVersions = getVersions().iterator();
		while (itVersions.hasNext()) {
			RuleVersion version = itVersions.next();
			if (version.isInExploitation(observe)) {
				if (version.isEffective(effective)) {
					return version;
				}
			}
		}
		return null;
	}

	@Transient
	public DefinitionLevel getLevel() {
		if (definitionParameters == null || definitionParameters.isEmpty()) {
			return DefinitionLevel.DEFAULT;
		} else if (definitionParameters.size() == 1) {
			return definitionParameters.first().getLevel();
		} else {
			DefinitionLevel referecenceLevel = definitionParameters.first().getLevel();
			Iterator<RuleDefinitionParameter> itDefinitionParameters = definitionParameters.iterator();
			boolean custom = false;
			while (itDefinitionParameters.hasNext() && !custom) {
				custom = !referecenceLevel.equals(itDefinitionParameters.next().getLevel());
			}
			if (custom) {
				return DefinitionLevel.CUSTOM;
			} else {
				return referecenceLevel;
			}
		}
	}
	
	public boolean match(Collection<RuleDefinitionParameter> definitionParameters) {
		return DefinitionMatcher.match(this.definitionParameters, definitionParameters);
	}
	
	/**
	 * @param id the id to set
	 */
	private void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param identity the identity to set
	 */
	private void setRuleIdentity(RuleIdentity ruleIdentity) {
		this.ruleIdentity = ruleIdentity;
	}
	
	/**
	 * @param definitionParameters the definitionParameters to set
	 */
	public void setDefinitionParameters(SortedSet<RuleDefinitionParameter> definitionParameters) {
		this.definitionParameters = definitionParameters;
	}
	
	/**
	 * @param versions the versions to set
	 */
	private void setVersions(SortedSet<RuleVersion> versions) {
		this.versions = versions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		RuleDefinition other = (RuleDefinition) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
