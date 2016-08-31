package be.groups.glanguage.glanguage.api.entities.ruleset;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.groups.common.entities.util.LocalDateTimeConverter;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;

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
	private LocalDateTime exploitationStartDate;
	
	/**
	 * Version
	 */
	private String version;

	/**
	 * Author
	 */
	private String author;
	
	/**
	 * Status
	 */
	private RuleSetVersionStatus status;

	/**
	 * Label
	 */
	private String label;
	
	/**
	 * RuleSet this is a version of
	 */
	private RuleSet ruleSet;

	/**
	 * Parent RuleSetVersion of this
	 */
	private RuleSetVersion parent;

	/**
	 * Set of children RuleSetVersion's
	 */
	private Set<RuleSetVersion> children;
	
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
	public LocalDateTime getExploitationStartDate() {
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
	 * @return the author
	 */
	@Column(name = "AUTHOR")
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the status
	 */
	@Column(name = "STATUS", insertable = false, updatable = false)
	@Convert(converter = RuleSetVersionStatusConverter.class)
	public RuleSetVersionStatus getStatus() {
		return status;
	}

	/**
	 * @return the label
	 */
	@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}

	/**
	 * @return the parent
	 */
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public RuleSetVersion getParent() {
		return parent;
	}

	/**
	 * @return the children
	 */
	@OneToMany(mappedBy = "parent")
	public Set<RuleSetVersion> getChildren() {
		return children;
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
			name = "RULE_SET_VERSION_GROUP_ITEM",
			joinColumns = @JoinColumn(name = "INCLUDING_RULE_SET_VERSION_ID", referencedColumnName = "ID") ,
			inverseJoinColumns = @JoinColumn(name = "INCLUDED_RULE_SET_VERSION_ID", referencedColumnName = "ID") )
	public Set<RuleSetVersion> getIncludes() {
		return includes;
	}
	
	/**
	 * @return the includedIn
	 */
	@ManyToMany(mappedBy = "includes")
	public Set<RuleSetVersion> getIncludedIn() {
		return includedIn;
	}
	
	/**
	 * @return the ruleVersions
	 */
	@ManyToMany
	@JoinTable(
			name = "RULE_SET_VERSION_RULE_VERSION",
			joinColumns = @JoinColumn(name = "RULE_SET_VERSION_ID", referencedColumnName = "ID") ,
			inverseJoinColumns = @JoinColumn(name = "RULE_VERSION_ID", referencedColumnName = "ID") )
	@OrderBy("EFFECTIVITY_START_DATE DESC")
	public Set<RuleVersion> getRuleVersions() {
		return ruleVersions;
	}
	
	/**
	 * Get all RuleVersions for a code
	 * 
	 * @param code 
	 * @return
	 */
	@Transient
	private List<RuleVersion> getRuleVersions(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code)).collect(Collectors.toList());
	}
	
	/**
	 * Get the default definition RuleVersion for a code and effective at specified effective date
	 * 
	 * @param effective the date on which the version returned is effective
	 * @return the default definition RuleVersion that is effective at the specified date if it exists, null otherwise
	 */
	@Transient
	public RuleVersion getDefaultRuleVersion(String code, LocalDateTime effective) {
		Optional<RuleVersion> ruleVersion = getRuleVersions(code).stream()
				.filter(rv -> rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT) && rv.isEffective(effective))
				.findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		return null;
	}

	/**
	 * Get the RuleVersion effective at specified effective date and whose RuleDefinition matches the definition parameters
	 * 
	 * @param effective the date on which the RuleVersion returned is effective
	 * @param definitionParameters the definition parameters that the RuleVersion's RuleDefintion matches
	 * @return the default definition RuleVersion that is effective at the specified date if it exists, null otherwise
	 */
	@Transient
	public RuleVersion getDefinedRuleVersion(LocalDateTime effective, Collection<RuleDefinitionParameter> definitionParameters) {
		Optional<RuleVersion> ruleVersion = getRuleVersions().stream()
				.filter(rv -> rv.getRuleDefinition().match(definitionParameters) && rv.isEffective(effective)).findFirst();
		if (ruleVersion.isPresent()) {
			return ruleVersion.get();
		}
		return null;
	}
	
	/**
	 * Is this in exploitation at a specified date ? <br>
	 * This is in exploitation at a specified date if the specified date is between this start date and this end date inclusively
	 * 
	 * @param date The date at which this is in exploitation or not
	 * @return true If this is in exploitation at the specified date, false otherwise
	 */
	public boolean isInExploitation(LocalDateTime date) {
		return !date.isBefore(getExploitationStartDate());
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
	public void setExploitationStartDate(LocalDateTime exploitationStartDate) {
		this.exploitationStartDate = exploitationStartDate;
	}
	
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(RuleSetVersionStatus status) {
		this.status = status;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(RuleSetVersion parent) {
		this.parent = parent;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<RuleSetVersion> children) {
		this.children = children;
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
