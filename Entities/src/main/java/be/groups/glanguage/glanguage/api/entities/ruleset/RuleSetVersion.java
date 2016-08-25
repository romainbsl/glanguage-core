package be.groups.glanguage.glanguage.api.entities.ruleset;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.groups.common.entities.util.LocalDateTimeConverter;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

@Entity
@Table(name = "RULE_SET_VERSION")
public class RuleSetVersion {

	/**
	 * Technical unique ID
	 */
	private int id;

	/**
	 * Date from which this is in production inclusively
	 */
	private LocalDate exploitationStartDate;

	/**
	 * Version
	 */
	private String version;

	/**
	 * RuleSet this is a version of
	 */
	private RuleSet ruleSet;
	
	/**
	 * Set of RuleSetVersions included in this
	 */
	private Set<RuleSetVersion> includes;
	
	/**
	 * Set of RuleSetVersions this is included in
	 */
	private Set<RuleSetVersion> includedIn;
	
	/**
	 * Set of RuleVersions
	 */
	private Set<RuleVersion> ruleVersions;

	public RuleSetVersion() {
		super();
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
	 * @return the exploitationStartDate
	 */
	@Column(name = "EXPLOITATION_START_DATE")
	@Convert(converter = LocalDateTimeConverter.class)
	public LocalDate getExploitationStartDate() {
		return exploitationStartDate;
	}

	/**
	 * @return the version
	 */
	@Column(name = "VERSION")
	public String getVersion() {
		return version;
	}

	/**
	 * @return the ruleSet
	 */
	@ManyToOne
	@JoinColumn(name = "RULE_SET_ID")
	public RuleSet getRuleSet() {
		return ruleSet;
	}
	
	/**
	 * @return the includes
	 */
	@ManyToMany
	  @JoinTable(
	      name="RULE_SET_VERSION_GROUP_ITEM",
	      joinColumns=@JoinColumn(name="RULE_SET_VERSION_ID", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="INCLUDED_RULE_SET_VERSION_ID", referencedColumnName="ID"))
	public Set<RuleSetVersion> getIncludes() {
		return includes;
	}

	/**
	 * @return the includedIn
	 */
	@OneToMany(mappedBy = "includes")
	public Set<RuleSetVersion> getIncludedIn() {
		return includedIn;
	}

	/**
	 * @return the ruleVersions
	 */
	@ManyToMany
	  @JoinTable(
	      name="RULE_SET_VERSION_RULE_VERSION",
	      joinColumns=@JoinColumn(name="RULE_SET_VERSION_ID", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="RULE_VERSION_ID", referencedColumnName="ID"))
	public Set<RuleVersion> getRuleVersions() {
		return ruleVersions;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param exploitationStartDate the exploitationStartDate to set
	 */
	public void setExploitationStartDate(LocalDate exploitationStartDate) {
		this.exploitationStartDate = exploitationStartDate;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @param ruleSet the ruleSet to set
	 */
	public void setRuleSet(RuleSet ruleSet) {
		this.ruleSet = ruleSet;
	}

	/**
	 * @param includes the includes to set
	 */
	public void setIncludes(Set<RuleSetVersion> includes) {
		this.includes = includes;
	}

	/**
	 * @param includedIn the includedIn to set
	 */
	public void setIncludedIn(Set<RuleSetVersion> includedIn) {
		this.includedIn = includedIn;
	}

	/**
	 * @param ruleVersions the ruleVersions to set
	 */
	public void setRuleVersions(Set<RuleVersion> ruleVersions) {
		this.ruleVersions = ruleVersions;
	}
	
}
