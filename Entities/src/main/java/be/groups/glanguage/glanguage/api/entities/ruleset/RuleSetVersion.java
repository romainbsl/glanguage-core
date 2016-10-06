package be.groups.glanguage.glanguage.api.entities.ruleset;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher.DefinitionMatcherStrategy;
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
	 * Creation author
	 */
	private String creationAuthor;
	
	/**
	 * Creation date
	 */
	private LocalDateTime creationDate;
	
	/**
	 * Modification author
	 */
	private String modificationAuthor;
	
	/**
	 * Modification date
	 */
	private LocalDateTime modificationDate;
	
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
	 * @return the creation author
	 */
	@Column(name = "AUTHOR_CREATION")
	public String getCreationAuthor() {
		return creationAuthor;
	}
	
	/**
	 * @return the creation date
	 */
	@Column(name = "DATE_CREATION")
	@Convert(converter = LocalDateTimeConverter.class)
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	/**
	 * @return the modification author
	 */
	@Column(name = "AUTHOR_MODIFICATION")
	public String getModificationAuthor() {
		return modificationAuthor;
	}
	
	/**
	 * @return the modification date
	 */
	@Column(name = "DATE_MODIFICATION")
	@Convert(converter = LocalDateTimeConverter.class)
	public LocalDateTime getModificationDate() {
		return modificationDate;
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
	@JoinTable(name = "RULE_SET_VERSION_GROUP_ITEM",
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
	@JoinTable(name = "RULE_SET_VERSION_RULE_VERSION",
			joinColumns = @JoinColumn(name = "RULE_SET_VERSION_ID", referencedColumnName = "ID") ,
			inverseJoinColumns = @JoinColumn(name = "RULE_VERSION_ID", referencedColumnName = "ID") )
	@OrderBy("EFFECTIVITY_START_DATE DESC")
	public Set<RuleVersion> getRuleVersions() {
		return ruleVersions;
	}
	
	/**
	 * Get all {@link RuleIdentity}'s, corresponding to the {@link RuleVersion}'s of this {@link RuleSetVersion}
	 * 
	 * @return The list of all {@link RuleIdentity}'s, corresponding to the {@link RuleVersion}'s of this {@link RuleSetVersion}
	 */
	@Transient
	public List<RuleIdentity> getRuleIdentitites() {
		return getRuleVersions().stream().map(rv -> rv.getRuleDefinition().getRuleIdentity()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s, corresponding to the {@link RuleVersion}'s of this {@link RuleSetVersion}
	 * 
	 * @return The list of all {@link RuleDefinition}'s, corresponding to the {@link RuleVersion}'s of this {@link RuleSetVersion}
	 */
	@Transient
	public List<RuleDefinition> getRuleDefinitions() {
		return getRuleVersions().stream().map(rv -> rv.getRuleDefinition()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all default {@link RuleDefinition}'s (those that have no definition parameters), corresponding to the
	 * {@link RuleVersion}'s of this {@link RuleSetVersion}
	 * 
	 * @return The list of all default {@link RuleDefinition}'s, corresponding to the {@link RuleVersion}'s of this
	 *         {@link RuleSetVersion}
	 */
	@Transient
	public List<RuleDefinition> getDefaultRuleDefinitions() {
		return getRuleVersions().stream().map(rv -> rv.getRuleDefinition()).distinct()
				.filter(rd -> rd.getLevel().equals(DefinitionLevel.DEFAULT)).collect(Collectors.toList());
	}
	
	/**
	 * Get all default {@link RuleDefinition}'s (those that have no definition parameters) that have a code equal to {@code code},
	 * corresponding to the {@link RuleVersion}'s of this {@link RuleSetVersion}
	 * 
	 * @param definitionParameters
	 * @return The list of all default {@link RuleDefinition}'s (those that have no definition parameters) that have a code equal to
	 *         {@code code}, corresponding to the {@link RuleVersion}'s of this {@link RuleSetVersion}
	 * @see RuleDefinition#matches(Collection)
	 */
	@Transient
	public List<RuleDefinition> getDefaultRuleDefinitions(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code))
				.map(rv -> rv.getRuleDefinition()).distinct().filter(rd -> rd.getLevel().equals(DefinitionLevel.DEFAULT))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s that match at least {@code definitionParameters} parameters
	 * 
	 * @param definitionParameters
	 * @return The list of all {@link RuleDefinition}'s that match at least {@code definitionParameters} parameters
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleDefinition> getDefinedRuleDefinitions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getRuleVersions().stream().map(rv -> rv.getRuleDefinition()).distinct()
				.filter(rd -> rd.matches(definitionParameters, DefinitionMatcherStrategy.AT_LEAST)).collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s that have a code equal to {@code code} and that matches at least {@code definitionParameters}
	 * parameters
	 * 
	 * @param definitionParameters
	 * @return The list of all {@link RuleDefinition}'s that have a code equal to {@code code} and that matches at least
	 *         {@code definitionParameters} parameters
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleDefinition> getDefinedRuleDefinitions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code))
				.map(rv -> rv.getRuleDefinition()).distinct()
				.filter(rd -> rd.matches(definitionParameters, DefinitionMatcherStrategy.AT_LEAST)).collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code}
	 * 
	 * @param code
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code}
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code)).collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that are effective at {@code effectivityDate}
	 * 
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s that are effective at {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(LocalDateTime effectivityDate) {
		return getRuleVersions().stream().filter(rv -> rv.isEffective(effectivityDate)).collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at {@code effectivityDate}
	 * 
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 *         {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(String code, LocalDateTime effectivityDate) {
		return getRuleVersions().stream()
				.filter(rv -> rv.getRuleDescription().getCode().equals(code) && rv.isEffective(effectivityDate))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a default {@link RuleDefinition} (those that have no definition parameters)
	 * 
	 * @return The list of all default defined {@link RuleVersion}'s
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions() {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have a default {@link RuleDefinition} (those that
	 * have no definition parameters)
	 * 
	 * @param code
	 * @return The list of all default defined {@link RuleVersion}'s
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code)
				&& rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT)).collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a default {@link RuleDefinition} (those that have no definition parameters) and that are
	 * effective at {@code effectivityDate}
	 * 
	 * @param effectivityDate
	 * @return The list of all default defined {@link RuleVersion}'s effective at {@code effectivityDate}
	 * @see RuleVersion#isEffective(LocalDateTime)
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(LocalDateTime effectivityDate) {
		return getRuleVersions().stream()
				.filter(rv -> rv.isEffective(effectivityDate) && rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get the {@link RuleVersion} that have a code equal to {@code code} and that have a default {@link RuleDefinition} (those that
	 * have no definition parameters) and that are effective at {@code effectivityDate}
	 * 
	 * @param code
	 * @param effectivityDate
	 * @return The list of all default defined {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 *         {@code effectivityDate}
	 * @see RuleVersion#isEffective(LocalDateTime)
	 */
	@Transient
	public RuleVersion getDefaultRuleVersion(String code, LocalDateTime effectivityDate) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code)
				&& rv.isEffective(effectivityDate) && rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT)).findFirst()
				.orElse(null);
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least {@code definitionParameters}
	 * parameters
	 * 
	 * @param definitionParameters
	 * @return The list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least
	 *         {@code definitionParameters} parameters
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getRuleVersions().stream()
				.filter(rv -> rv.getRuleDefinition().matches(definitionParameters, DefinitionMatcherStrategy.AT_LEAST))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least {@code definitionParameters}
	 * parameters and that are effective at {@code effectivityDate}
	 * 
	 * @param definitionParameters
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least
	 *         {@code definitionParameters} parameters and that are effective at {@code effectivityDate}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 * @see RuleVersion#isEffective(LocalDateTime)
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters,
			LocalDateTime effectivityDate) {
		return getRuleVersions().stream()
				.filter(rv -> rv.isEffective(effectivityDate)
						&& rv.getRuleDefinition().matches(definitionParameters, DefinitionMatcherStrategy.AT_LEAST))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have a {@link RuleDefinition} that matches at
	 * least {@code definitionParameters} parameters
	 * 
	 * @param code
	 * @param definitionParameters
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a {@link RuleDefinition} that
	 *         matches at least {@code definitionParameters} that are effective at {@code effectivityDate}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getRuleVersions().stream()
				.filter(rv -> rv.getRuleDescription().getCode().equals(code)
						&& rv.getRuleDefinition().matches(definitionParameters, DefinitionMatcherStrategy.AT_LEAST))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have the {@link RuleDefinition} that best matches
	 * {@code definitionParameters} parameters
	 * 
	 * @param code
	 * @param definitionParameters
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have the {@link RuleDefinition}
	 *         that best matches {@code definitionParameters} that are effective at {@code effectivityDate}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		Stream<RuleVersion> versionsForCode = getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code));
		Map<RuleDefinition, List<RuleVersion>> ruleVersionsByRuleDefinition =
				versionsForCode.collect(Collectors.groupingBy(RuleVersion::getRuleDefinition));
		RuleDefinition bestRuleDefinition = null;
		bestRuleDefinition = DefinitionMatcher
				.getBestMatch(versionsForCode.map(rv -> rv.getRuleDefinition()).collect(Collectors.toList()), definitionParameters);
		if (bestRuleDefinition != null) {
			return ruleVersionsByRuleDefinition.get(bestRuleDefinition);
		} else {
			return null;
		}
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have a {@link RuleDefinition} that matches at
	 * least {@code definitionParameters} parameters and that are effective at {@code effectivityDate}
	 * 
	 * @param code
	 * @param definitionParameters
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a {@link RuleDefinition} that
	 *         matches at least {@code definitionParameters} and that are effective at {@code effectivityDate}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 * @see RuleVersion#isEffective(LocalDateTime)
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDateTime effectivityDate) {
		return getRuleVersions().stream()
				.filter(rv -> rv.getRuleDescription().getCode().equals(code) && rv.isEffective(effectivityDate)
						&& rv.getRuleDefinition().matches(definitionParameters, DefinitionMatcherStrategy.AT_LEAST))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get the {@link RuleVersion} that have a code equal to {@code code} and that have the {@link RuleDefinition} that best matches
	 * {@code definitionParameters} parameters and that is effective at {@code effectivityDate}
	 * 
	 * @param code
	 * @param definitionParameters
	 * @param effectivityDate
	 * @return The {@link RuleVersion}'s that have a code equal to {@code code} and that have the {@link RuleDefinition} that best
	 *         matches {@code definitionParameters} and that is effective at {@code effectivityDate}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
	 * @see RuleVersion#isEffective(LocalDateTime)
	 */
	@Transient
	public RuleVersion getBestDefinedRuleVersion(String code, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDateTime effectivityDate) {
		Stream<RuleVersion> effectiveVersionsForCode = getRuleVersions().stream()
				.filter(rv -> rv.getRuleDescription().getCode().equals(code) && rv.isEffective(effectivityDate));
		Map<RuleDefinition, RuleVersion> ruleVersionByRuleDefinition =
				effectiveVersionsForCode.collect(Collectors.toMap(RuleVersion::getRuleDefinition, Function.identity()));
		RuleDefinition bestRuleDefinition = null;
		bestRuleDefinition = DefinitionMatcher.getBestMatch(
				effectiveVersionsForCode.map(rv -> rv.getRuleDefinition()).collect(Collectors.toList()), definitionParameters);
		if (bestRuleDefinition != null) {
			return ruleVersionByRuleDefinition.get(bestRuleDefinition);
		} else {
			return null;
		}
	}
	
	/**
	 * Is this in exploitation at a specified date ? <br>
	 * This is in exploitation at a specified date if the specified date is
	 * between this start date and this end date inclusively
	 * 
	 * @param date
	 *        The date at which this is in exploitation or not
	 * @return true If this is in exploitation at the specified date, false
	 *         otherwise
	 */
	public boolean isInExploitation(LocalDateTime date) {
		return !date.isBefore(getExploitationStartDate());
	}
	
	/**
	 * @param id
	 *        the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param exploitationStartDate
	 *        the exploitationStartDate to set
	 */
	public void setExploitationStartDate(LocalDateTime exploitationStartDate) {
		this.exploitationStartDate = exploitationStartDate;
	}
	
	/**
	 * @param version
	 *        the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * @param creationAuthor
	 *        the creationAuthor to set
	 */
	public void setCreationAuthor(String creationAuthor) {
		this.creationAuthor = creationAuthor;
	}
	
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * @param modificationAuthor
	 *        the modificationAuthor to set
	 */
	public void setModificationAuthor(String modificationAuthor) {
		this.modificationAuthor = modificationAuthor;
	}
	
	/**
	 * @param modificationDate the modificationDate to set
	 */
	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	/**
	 * @param status
	 *        the status to set
	 */
	public void setStatus(RuleSetVersionStatus status) {
		this.status = status;
	}
	
	/**
	 * @param label
	 *        the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * @param parent
	 *        the parent to set
	 */
	public void setParent(RuleSetVersion parent) {
		this.parent = parent;
	}
	
	/**
	 * @param children
	 *        the children to set
	 */
	public void setChildren(Set<RuleSetVersion> children) {
		this.children = children;
	}
	
	/**
	 * @param ruleSet
	 *        the ruleSet to set
	 */
	public void setRuleSet(RuleSet ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	/**
	 * @param includes
	 *        the includes to set
	 */
	public void setIncludes(Set<RuleSetVersion> includes) {
		this.includes = includes;
	}
	
	/**
	 * @param includedIn
	 *        the includedIn to set
	 */
	public void setIncludedIn(Set<RuleSetVersion> includedIn) {
		this.includedIn = includedIn;
	}
	
	/**
	 * @param ruleVersions
	 *        the ruleVersions to set
	 */
	public void setRuleVersions(Set<RuleVersion> ruleVersions) {
		this.ruleVersions = ruleVersions;
	}
	
}
