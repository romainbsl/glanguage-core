package be.groups.glanguage.glanguage.api.entities.ruleset;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;

@Entity
@Table(name = "RULE_SET")
public class RuleSet {
	
	/**
	 * Technical unique ID
	 */
	private int id;
	
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
	 * Versions
	 */
	private Set<RuleSetVersion> versions;
	
	public RuleSet() {
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
	 * @return the versions
	 */
	@OneToMany(mappedBy = "ruleSet")
	@OrderBy("EXPLOITATION_START_DATE DESC")
	public Set<RuleSetVersion> getVersions() {
		return versions;
	}
	
	/**
	 * Get the {@link RuleSetVersion} of this corresponding to {@code version} version string
	 * 
	 * @param version The version string
	 * @return The {@link RuleSetVersion} of this corresponding to {@code version} version string if it exists, null otherwise
	 */
	@Transient
	public RuleSetVersion getVersion(String version) {
		return getVersions().stream().filter(rsv -> rsv.getVersion().equals(version)).findFirst().orElse(null);
	}
	
	/**
	 * Get the {@link RuleSetVersion} of this in exploitation at {@code exploitationDate} date
	 * 
	 * @param exploitationDate
	 * @return The {@link RuleSetVersion} of this in exploitation at {@code exploitationDate} date if it exists, null otherwise
	 */
	@Transient
	public RuleSetVersion getVersion(LocalDateTime exploitationDate) {
		return getVersions().stream().filter(rsv -> !rsv.getExploitationStartDate().isAfter(exploitationDate))
				.max((rv1, rv2) -> rv1.getExploitationStartDate().compareTo(rv2.getExploitationStartDate())).orElse(null);
	}
	
	/**
	 * Get all the {@link RuleIdentity}'s
	 * 
	 * @return The list of all {@link RuleIdentity}'s
	 */
	@Transient
	public List<RuleIdentity> getRuleIdentities() {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleIdentities().stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleIdentity}'s that have a code equal to {@code code}
	 * 
	 * @return The list of all {@link RuleIdentity}'s that have a code equal to {@code code}
	 */
	@Transient
	public List<RuleIdentity> getRuleIdentities(String code) {
		return getVersions().stream()
				.flatMap(rsv -> rsv.getDefaultRuleVersions(code).stream().map(rv -> rv.getRuleDefinition().getRuleIdentity()))
				.distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s
	 * 
	 * @return The list of all {@link RuleDefinition}'s
	 */
	@Transient
	public List<RuleDefinition> getRuleDefinitions() {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleDefinitions().stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s that have a code equal to {@code code}
	 * 
	 * @param code
	 * @return The list of all {@link RuleDefinition}'s that have a code equal to {@code code}
	 */
	@Transient
	public List<RuleDefinition> getRuleDefinitions(String code) {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions(code).stream().map(rv -> rv.getRuleDefinition())).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all default {@link RuleDefinition}'s (those that have no definition parameters)
	 * 
	 * @return The list of all default {@link RuleDefinition}'s (those that have no definition parameters)
	 */
	@Transient
	public List<RuleDefinition> getDefaultRuleDefinitions() {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleDefinitions().stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all default {@link RuleDefinition}'s (those that have no definition parameters) that have a code equal to {@code code}
	 * 
	 * @param code
	 * @return The list of all default {@link RuleDefinition}'s (those that have no definition parameters) that have a code equal to
	 *         {@code code}
	 */
	@Transient
	public List<RuleDefinition> getDefaultRuleDefinitions(String code) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleDefinitions(code).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s that match {@code definitionParameters} parameters
	 * 
	 * @param definitionParameters
	 * @return The list of all {@link RuleDefinition}'s that match {@code definitionParameters} parameters
	 */
	@Transient
	public List<RuleDefinition> getDefinedRuleDefinitions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleDefinitions(definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s that best matches {@code definitionParameters} parameters
	 * 
	 * @param definitionParameters
	 * @return The list of all {@link RuleDefinition}'s that best matches {@code definitionParameters} parameters
	 */
	@Transient
	public List<RuleDefinition> getBestDefinedRuleDefinitions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getBestDefinedRuleDefinitions(definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s that have a code equal to {@code code} and that match {@code definitionParameters} parameters
	 * 
	 * @param code
	 * @param definitionParameters
	 * @return The list of all {@link RuleDefinition}'s that have a code equal to {@code code} and that match
	 *         {@code definitionParameters} parameters
	 */
	@Transient
	public List<RuleDefinition> getDefinedRuleDefinitions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleDefinitions(code, definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleDefinition}'s that have a code equal to {@code code} and that best matches {@code definitionParameters}
	 * parameters
	 * 
	 * @param code
	 * @param definitionParameters
	 * @return The list of all {@link RuleDefinition}'s that have a code equal to {@code code} and that best matches
	 *         {@code definitionParameters} parameters
	 */
	@Transient
	public List<RuleDefinition> getBestDefinedRuleDefinitions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().map(rsv -> rsv.getBestDefinedRuleDefinition(code, definitionParameters)).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s
	 * 
	 * @return The list of all {@link RuleVersion}'s
	 */
	@Transient
	public List<RuleVersion> getRuleVersions() {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions().stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code}
	 * 
	 * @param code
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code}
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(String code) {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions(code).stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s effective at {@code effectivityDate}
	 * 
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s effective at {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(LocalDate effectivityDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions(effectivityDate).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and are effective at {@code effectivityDate}
	 * 
	 * @param code
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code} and are effective at
	 *         {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(String code, LocalDate effectivityDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions(code, effectivityDate).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a default {@link RuleDefinition} (those that have no definition parameters)
	 * 
	 * @return The list of all default defined {@link RuleVersion}'s
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions() {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleVersions().stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have a default {@link RuleDefinition} (those that
	 * have no definition parameters)
	 * 
	 * @param code
	 * @return The list of all default defined {@link RuleVersion}'s that have a code equal to {@code code}
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(String code) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleVersions(code).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a default {@link RuleDefinition} (those that have no definition parameters) and that are
	 * effective at {@code effectivityDate}
	 *
	 * @param effectivityDate
	 * @return The list of all default defined {@link RuleVersion}'s that are effective at {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(LocalDate effectivityDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleVersions(effectivityDate).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have a default {@link RuleDefinition} (those that
	 * have no definition parameters) and that are effective at {@code effectivityDate}
	 * 
	 * @param code
	 * @param effectivityDate
	 * @return The list of all default defined {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 *         {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(String code, LocalDate effectivityDate) {
		return getVersions().stream().map(rsv -> rsv.getDefaultRuleVersion(code, effectivityDate)).filter(Objects::nonNull).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least {@code definitionParameters} parameters
	 * 
	 * @param definitionParameters
	 * @return The list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least
	 *         {@code definitionParameters} parameters
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleVersions(definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have the {@link RuleDefinition} that best matches {@code definitionParameters} parameters
	 * 
	 * @param definitionParameters
	 * @return The list of all {@link RuleVersion}'s that have the {@link RuleDefinition} that best matches
	 *         {@code definitionParameters} parameters
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getBestDefinedRuleVersions(definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have a {@link RuleDefinition} that matches at
	 * least {@code definitionParameters} parameters
	 * 
	 * @param code
	 * @param definitionParameters
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a {@link RuleDefinition} that
	 *         matches at least {@code definitionParameters} parameters
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleVersions(code, definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have the {@link RuleDefinition} that best matches
	 * {@code definitionParameters} parameters
	 * 
	 * @param code
	 * @param definitionParameters
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have the {@link RuleDefinition}
	 *         that best matches {@code definitionParameters} parameters
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getBestDefinedRuleVersions(code, definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least {@code definitionParameters} parameters
	 * and that are effective at {@code effectivityDate}
	 * 
	 * @param definitionParameters
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least
	 *         {@code definitionParameters} parameters and that are effective at {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectivityDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleVersions(definitionParameters, effectivityDate).stream())
				.distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have the {@link RuleDefinition} that best matches {@code definitionParameters} parameters and
	 * that are effective at {@code effectivityDate}
	 * 
	 * @param definitionParameters
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that best matches {@code definitionParameters}
	 *         parameters and that are effective at {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectivityDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getBestDefinedRuleVersions(definitionParameters, effectivityDate).stream())
				.distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have a {@link RuleDefinition} that matches at
	 * least {@code definitionParameters} parameters and that are effective at {@code effectivityDate}
	 * 
	 * @param code
	 * @param definitionParameters
	 * @param effectivityDate
	 * @return The list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a {@link RuleDefinition} that
	 *         matches at least {@code definitionParameters} parameters and that are effective at {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectivityDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleVersions(code, definitionParameters, effectivityDate).stream())
				.distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get all {@link RuleVersion}'s that have a code equal to {@code code} and that have the {@link RuleDefinition} that best matches
	 * {@code definitionParameters} parameters and that are effective at {@code effectivityDate}
	 * 
	 * @param code
	 * @param definitionParameters
	 * @param effectivityDate
	 * @return The list of all default defined {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 *         {@link RuleDefinition} that best matches {@code definitionParameters} parameters and that are effective at
	 *         {@code effectivityDate}
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectivityDate) {
		return getVersions().stream().map(rsv -> rsv.getBestDefinedRuleVersion(code, definitionParameters, effectivityDate)).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * @param id
	 *        the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param aliasFr
	 *        the aliasFr to set
	 */
	public void setAliasFr(String aliasFr) {
		this.aliasFr = aliasFr;
	}
	
	/**
	 * @param aliasNl
	 *        the aliasNl to set
	 */
	public void setAliasNl(String aliasNl) {
		this.aliasNl = aliasNl;
	}
	
	/**
	 * @param aliasDe
	 *        the aliasDe to set
	 */
	public void setAliasDe(String aliasDe) {
		this.aliasDe = aliasDe;
	}
	
	/**
	 * @param aliasX
	 *        the aliasX to set
	 */
	public void setAliasX(String aliasX) {
		this.aliasX = aliasX;
	}
	
	/**
	 * @param descriptionFr
	 *        the descriptionFr to set
	 */
	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}
	
	/**
	 * @param descriptionNl
	 *        the descriptionNl to set
	 */
	public void setDescriptionNl(String descriptionNl) {
		this.descriptionNl = descriptionNl;
	}
	
	/**
	 * @param descriptionDe
	 *        the descriptionDe to set
	 */
	public void setDescriptionDe(String descriptionDe) {
		this.descriptionDe = descriptionDe;
	}
	
	/**
	 * @param descriptionX
	 *        the descriptionX to set
	 */
	public void setDescriptionX(String descriptionX) {
		this.descriptionX = descriptionX;
	}
	
	/**
	 * @param versions
	 *        the versions to set
	 */
	public void setVersions(Set<RuleSetVersion> versions) {
		this.versions = versions;
	}
	
}
