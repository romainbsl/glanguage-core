package be.groups.glanguage.glanguage.api.entities.ruleset;

import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentity;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Defines a
 */
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
	 * Get the technical id
	 *
	 * @return the id
	 */
	@Id
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	/**
	 * Get the French alias
	 *
	 * @return the French alias
	 */
	@Column(name = "ALIAS_FR")
	public String getAliasFr() {
		return aliasFr;
	}

	/**
	 * Get the Dutch alias
	 *
	 * @return the Dutch alias
	 */
	@Column(name = "ALIAS_NL")
	public String getAliasNl() {
		return aliasNl;
	}

	/**
	 * Get the German alias
	 *
	 * @return the German alias
	 */
	@Column(name = "ALIAS_DE")
	public String getAliasDe() {
		return aliasDe;
	}

	/**
	 * Get the other alias
	 *
	 * @return the other alias
	 */
	@Column(name = "ALIAS_X")
	public String getAliasX() {
		return aliasX;
	}

	/**
	 * Get the French description
	 *
	 * @return the French description
	 */
	@Column(name = "DESCRIPTION_FR")
	public String getDescriptionFr() {
		return descriptionFr;
	}

	/**
	 * Get the Dutch description
	 *
	 * @return the Dutch description
	 */
	@Column(name = "DESCRIPTION_NL")
	public String getDescriptionNl() {
		return descriptionNl;
	}

	/**
	 * Get the German description
	 *
	 * @return the German description
	 */
	@Column(name = "DESCRIPTION_DE")
	public String getDescriptionDe() {
		return descriptionDe;
	}

	/**
	 * Get the other description
	 *
	 * @return the other description
	 */
	@Column(name = "DESCRIPTION_X")
	public String getDescriptionX() {
		return descriptionX;
	}
	
	/**
	 * Get the set of {@link RuleSetVersion} ordered by {@link RuleSetVersion#productionStartDate}
	 *
	 * @return the set of {@link RuleSetVersion} ordered by {@link RuleSetVersion#productionStartDate}
	 * @see RuleSetVersion
	 */
	@OneToMany(mappedBy = "ruleSet")
	@OrderBy("EXPLOITATION_START_DATE DESC")
	public Set<RuleSetVersion> getVersions() {
		return versions;
	}
	
	/**
	 * Get the {@link RuleSetVersion} of this corresponding to {@code version} version string
	 * 
	 * @param version the version string
	 * @return the {@link RuleSetVersion} of this corresponding to {@code version} version string if it exists, null
	 * otherwise
	 * @see RuleSetVersion
	 */
	@Transient
	public RuleSetVersion getVersion(String version) {
		return getVersions().stream().filter(rsv -> rsv.getVersion().equals(version)).findFirst().orElse(null);
	}
	
	/**
	 * Get the {@link RuleSetVersion} of this in production at {@code productionDate} date
	 * 
	 * @param productionDate the production date
	 * @return the {@link RuleSetVersion} of this in production at {@code productionDate} date if it exists, null
	 * otherwise
	 * @see RuleSetVersion
	 */
	@Transient
	public RuleSetVersion getVersion(LocalDateTime productionDate) {
		return getVersions().stream().filter(rsv -> !rsv.getProductionStartDate().isAfter(productionDate))
				.max(Comparator.comparing(RuleSetVersion::getProductionStartDate)).orElse(null);
	}
	
	/**
	 * Get the list of all {@link RuleIdentity}'s
	 * 
	 * @return the the list of all {@link RuleIdentity}'s
	 * @see RuleIdentity
	 */
	@Transient
	public List<RuleIdentity> getRuleIdentities() {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleIdentities().stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get the list of all {@link RuleIdentity}'s that have a code equal to {@code code}
	 *
	 * @param code the code identifying the rules to be returned
	 * @return the list of all {@link RuleIdentity}'s that have a code equal to {@code code}
	 * @see RuleIdentity
	 */
	@Transient
	public List<RuleIdentity> getRuleIdentities(String code) {
		return getVersions().stream()
				.flatMap(rsv -> rsv.getDefaultRuleVersions(code).stream().map(rv -> rv.getRuleDefinition().getRuleIdentity()))
				.distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get the list of all {@link RuleDefinition}'s
	 * 
	 * @return the list of all {@link RuleDefinition}'s
	 * @see RuleDefinition
	 */
	@Transient
	public List<RuleDefinition> getRuleDefinitions() {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleDefinitions().stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get the list of all {@link RuleDefinition}'s that have a code equal to {@code code}
	 * 
	 * @param code the code identifying the rules to be returned
	 * @return the list of all {@link RuleDefinition}'s that have a code equal to {@code code}
	 * @see RuleDefinition
	 */
	@Transient
	public List<RuleDefinition> getRuleDefinitions(String code) {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions(code).stream().map(rv -> rv.getRuleDefinition())).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get the list of all {@link RuleDefinition}'s that have a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 * 
	 * @return the list of all {@link RuleDefinition}'s that have a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 * @see RuleDefinition
	 * @see RuleSetVersion#getDefaultRuleDefinitions
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel
	 */
	@Transient
	public List<RuleDefinition> getDefaultRuleDefinitions() {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleDefinitions().stream()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get the list of all {@link RuleDefinition}'s that have a code equal to {@code code} and that have a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 * 
	 * @param code the code identifying the rules to be returned
	 * @return the list of all {@link RuleDefinition}'s that have a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 * that have a code equal to {@code code}
	 * @see RuleDefinition
	 * @see RuleSetVersion#getDefaultRuleDefinitions(String)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel
	 */
	@Transient
	public List<RuleDefinition> getDefaultRuleDefinitions(String code) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleDefinitions(code).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleDefinition}'s that matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleDefinition
	 * @see RuleSetVersion#getDefinedRuleDefinitions(Collection)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleDefinition> getDefinedRuleDefinitions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleDefinitions(definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that best matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleDefinition}'s that best matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleDefinition
	 * @see RuleSetVersion#getBestDefinedRuleDefinitions(Collection)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleDefinition> getBestDefinedRuleDefinitions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getBestDefinedRuleDefinitions(definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that have a code equal to {@code code} and that matches the
	 * collection of {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleDefinition}'s that have a code equal to {@code code} and that matches the
	 * collection of {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleDefinition
	 * @see RuleSetVersion#getDefinedRuleDefinitions(Collection)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleDefinition> getDefinedRuleDefinitions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleDefinitions(code, definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that have a code equal to {@code code} and that best matches the
	 * collection of {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleDefinition}'s that have a code equal to {@code code} and that best matches
	 * the collection of {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleDefinition
	 * @see RuleSetVersion#getDefinedRuleDefinitions(Collection)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleDefinition> getBestDefinedRuleDefinitions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().map(rsv -> rsv.getBestDefinedRuleDefinition(code, definitionParameters)).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Get the list of all {@link RuleVersion}'s
	 * 
	 * @return the list of all {@link RuleVersion}'s
	 * @see RuleVersion
	 * @see RuleSetVersion#getRuleVersions()
	 */
	@Transient
	public List<RuleVersion> getRuleVersions() {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions().stream()).distinct().collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code}
	 *
	 * @param code the code identifying the rules to be returned
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code}
	 * @see RuleVersion
	 * @see RuleSetVersion#getRuleVersions(String)
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(String code) {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions(code).stream()).distinct().collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate}
	 *
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate}
	 * @see RuleVersion
	 * @see RuleVersion#isEffective(LocalDate)
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(LocalDate effectiveDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions(effectiveDate).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate}
	 * @see RuleVersion
	 * @see RuleVersion#isEffective(LocalDate)
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(String code, LocalDate effectiveDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getRuleVersions(code, effectiveDate).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s and that have a {@link RuleDefinition} with a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 *
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} with a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 * @see RuleVersion
	 * @see RuleSetVersion#getRuleVersions(String)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions() {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleVersions().stream()).distinct().collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} with a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 *
	 * @param code the code identifying the rules to be returned
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} with a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 * @see RuleVersion
	 * @see RuleSetVersion#getDefaultRuleVersions(String)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(String code) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleVersions(code).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that have a
	 * {@link RuleDefinition} with a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 *
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that that are effective at {@link LocalDate effectiveDate} and
	 * that have a {@link RuleDefinition} with a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 * @see RuleVersion
	 * @see RuleVersion#isEffective(LocalDate)
	 * @see RuleSetVersion#getDefaultRuleVersions(LocalDate)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(LocalDate effectiveDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefaultRuleVersions(effectiveDate).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} with a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 *
	 * @param code the code identifying the rules to be returned
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that that are
	 * effective at {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} with a
	 * {@link be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel#DEFAULT} definition level
	 * @see RuleVersion
	 * @see RuleVersion#isEffective(LocalDate)
	 * @see RuleSetVersion#getDefaultRuleVersion(String, LocalDate)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(String code, LocalDate effectiveDate) {
		return getVersions().stream().map(rsv -> rsv.getDefaultRuleVersion(code, effectiveDate)).filter(Objects::nonNull).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleVersion
	 * @see RuleSetVersion#getDefinedRuleVersions(Collection)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleVersions(definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that best matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that best matches the collection
	 * of {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleVersion
	 * @see RuleSetVersion#getBestDefinedRuleVersions(Collection)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getBestDefinedRuleVersions(definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} that matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} that matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 * @see RuleVersion
	 * @see RuleSetVersion#getDefinedRuleVersions(String, Collection)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleVersions(code, definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} that best matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} that best matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 * @see RuleVersion
	 * @see RuleSetVersion#getBestDefinedRuleVersions(String, Collection)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getVersions().stream().flatMap(rsv -> rsv.getBestDefinedRuleVersions(code, definitionParameters).stream()).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that have a
	 * {@link RuleDefinition} that matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that
	 * have a {@link RuleDefinition} that matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 * @see RuleVersion
	 * @see RuleVersion#isEffective(LocalDate)
	 * @see RuleSetVersion#getDefinedRuleVersions(Collection, LocalDate)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectiveDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleVersions(definitionParameters, effectiveDate).stream())
				.distinct().collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that have a
	 * {@link RuleDefinition} that best matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that
	 * have a {@link RuleDefinition} that best matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 * @see RuleVersion
	 * @see RuleVersion#isEffective(LocalDate)
	 * @see RuleSetVersion#getBestDefinedRuleVersions(Collection, LocalDate)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectiveDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getBestDefinedRuleVersions(definitionParameters, effectiveDate).stream())
				.distinct().collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} that matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} that matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleVersion
	 * @see RuleVersion#isEffective(LocalDate)
	 * @see RuleSetVersion#getBestDefinedRuleVersion(String, Collection, LocalDate)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectiveDate) {
		return getVersions().stream().flatMap(rsv -> rsv.getDefinedRuleVersions(code, definitionParameters, effectiveDate).stream())
				.distinct().collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} that best matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} that best matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleVersion
	 * @see RuleVersion#isEffective(LocalDate)
	 * @see RuleSetVersion#getBestDefinedRuleVersion(String, Collection, LocalDate)
	 * @see be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectiveDate) {
		return getVersions().stream().map(rsv -> rsv.getBestDefinedRuleVersion(code, definitionParameters, effectiveDate)).distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param aliasFr the French alias to set
	 */
	public void setAliasFr(String aliasFr) {
		this.aliasFr = aliasFr;
	}

	/**
	 * @param aliasNl the Dutch alias to set
	 */
	public void setAliasNl(String aliasNl) {
		this.aliasNl = aliasNl;
	}

	/**
	 * @param aliasDe the German alias to set
	 */
	public void setAliasDe(String aliasDe) {
		this.aliasDe = aliasDe;
	}

	/**
	 * @param aliasX the other alias to set
	 */
	public void setAliasX(String aliasX) {
		this.aliasX = aliasX;
	}

	/**
	 * @param descriptionFr the French description to set
	 */
	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	/**
	 * @param descriptionNl the Dutch description to set
	 */
	public void setDescriptionNl(String descriptionNl) {
		this.descriptionNl = descriptionNl;
	}

	/**
	 * @param descriptionDe the German description to set
	 */
	public void setDescriptionDe(String descriptionDe) {
		this.descriptionDe = descriptionDe;
	}

	/**
	 * @param descriptionX the other description to set
	 */
	public void setDescriptionX(String descriptionX) {
		this.descriptionX = descriptionX;
	}
	
	/**
	 * @param versions the set of {@link RuleSetVersion} to set
	 */
	public void setVersions(Set<RuleSetVersion> versions) {
		this.versions = versions;
	}
	
}
