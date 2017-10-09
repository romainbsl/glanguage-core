package be.groups.glanguage.core.entities.ruleset;

import be.groups.glanguage.core.entities.rule.RuleDefinition;
import be.groups.glanguage.core.entities.rule.RuleIdentity;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.core.entities.rule.definition.DefinitionMatcher;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "RULE_SET_VERSION")
public class RuleSetVersion {
	
	/**
	 * Technical unique ID
	 */
	private Long id;
	
	/**
	 * Date from which this is in production inclusively
	 */
	private LocalDateTime productionStartDate;
	
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
	 * Parent RuleSetVersion of this (the one from which this was created)
	 */
	private RuleSetVersion parent;
	
	/**
	 * Set of children RuleSetVersion's (the ones that were created from this)
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
	 * Get the technical id
	 *
	 * @return the id
	 */
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	/**
	 * Get the production start date
	 *
	 * @return the production start date
	 */
	@Column(name = "EXPLOITATION_START_DATE")
	public LocalDateTime getProductionStartDate() {
		return productionStartDate;
	}
	
	/**
	 * Get the version
	 *
	 * @return the version
	 */
	@Column(name = "VERSION")
	public String getVersion() {
		return version;
	}
	
	/**
	 * Get the creation author
	 *
	 * @return the creation author
	 */
	@Column(name = "AUTHOR_CREATION")
	public String getCreationAuthor() {
		return creationAuthor;
	}
	
	/**
	 * Get the creation date
	 * @return the creation date
	 */
	@Column(name = "DATE_CREATION")
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Get the modification author
	 *
	 * @return the modification author
	 */
	@Column(name = "AUTHOR_MODIFICATION")
	public String getModificationAuthor() {
		return modificationAuthor;
	}

	/**
	 * Get the modification date
	 *
	 * @return the modification date
	 */
	@Column(name = "DATE_MODIFICATION")
	public LocalDateTime getModificationDate() {
		return modificationDate;
	}
	
	/**
	 * Get the status
	 *
	 * @return the status
	 */
	@Column(name = "STATUS", insertable = false, updatable = false)
	@Convert(converter = RuleSetVersionStatusConverter.class)
	public RuleSetVersionStatus getStatus() {
		return status;
	}
	
	/**
	 * Get the label
	 *
	 * @return the label
	 */
	@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}
	
	/**
	 * Get the parent {@link RuleSetVersion} (the one form which this was created)
	 *
	 * @return the parent {@link RuleSetVersion}
	 */
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public RuleSetVersion getParent() {
		return parent;
	}
	
	/**
	 * Get the set of children {@link RuleSetVersion} (the ones that were created from this)
	 *
	 * @return the set of children {@link RuleSetVersion} (the ones that were created from this)
	 */
	@OneToMany(mappedBy = "parent")
	public Set<RuleSetVersion> getChildren() {
		return children;
	}
	
	/**
	 * Get the {@link RuleSet} of which this is a version
	 *
	 * @return the the {@link RuleSet} of which this is a version
	 */
	@ManyToOne
	@JoinColumn(name = "RULE_SET_ID")
	public RuleSet getRuleSet() {
		return ruleSet;
	}
	
	/**
	 * Get the list of all included {@link RuleSetVersion}
	 *
	 * @return the list of all included {@link RuleSetVersion}
	 */
	@ManyToMany
	@JoinTable(name = "RULE_SET_VERSION_GROUP_ITEM",
			joinColumns = @JoinColumn(name = "INCLUDING_RULE_SET_VERSION_ID", referencedColumnName = "ID") ,
			inverseJoinColumns = @JoinColumn(name = "INCLUDED_RULE_SET_VERSION_ID", referencedColumnName = "ID") )
	public Set<RuleSetVersion> getIncludes() {
		return includes;
	}

	/**
	 * Get the list of all {@link RuleSetVersion} this is included in
	 *
	 * @return the list of all {@link RuleSetVersion} this is included in
	 */
	@ManyToMany(mappedBy = "includes")
	public Set<RuleSetVersion> getIncludedIn() {
		return includedIn;
	}
	
	/**
	 * Get the set of all {@link RuleVersion} ordered by {@link RuleVersion#effectiveStartDate}
	 *
	 * @return the set of all {@link RuleVersion} ordered by {@link RuleVersion#effectiveStartDate}
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "RULE_SET_VERSION_RULE_VERSION",
			joinColumns = @JoinColumn(name = "RULE_SET_VERSION_ID", referencedColumnName = "ID") ,
			inverseJoinColumns = @JoinColumn(name = "RULE_VERSION_ID", referencedColumnName = "ID") )
	@OrderBy("EFFECTIVITY_START_DATE DESC")
	public Set<RuleVersion> getRuleVersions() {
		return ruleVersions;
	}
	
	/**
	 * Get the list of all {@link RuleIdentity}'s, corresponding to the {@link RuleVersion}'s
	 * 
	 * @return the list of all {@link RuleIdentity}'s, corresponding to the {@link RuleVersion}'s
	 */
	@Transient
	public List<RuleIdentity> getRuleIdentities() {
		return getRuleVersions().stream().map(rv -> rv.getRuleDefinition().getRuleIdentity()).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Get the list of all {@link RuleDefinition}'s, corresponding to the {@link RuleVersion}'s
	 * 
	 * @return the list of all {@link RuleDefinition}'s, corresponding to the {@link RuleVersion}'s
	 */
	@Transient
	public List<RuleDefinition> getRuleDefinitions() {
		return getRuleVersions().stream().map(rv -> rv.getRuleDefinition()).distinct().collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that have a {@link DefinitionLevel#DEFAULT} definition level,
	 * corresponding to the {@link RuleVersion}'s
	 *
	 * @return the list of all {@link RuleDefinition}'s that have a {@link DefinitionLevel#DEFAULT} definition level,
	 * corresponding to the {@link RuleVersion}'s
	 * @see RuleDefinition
	 * @see DefinitionLevel
	 */
	@Transient
	public List<RuleDefinition> getDefaultRuleDefinitions() {
		return getRuleVersions().stream().map(rv -> rv.getRuleDefinition()).distinct()
				.filter(rd -> rd.getLevel().equals(DefinitionLevel.DEFAULT)).collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that have a {@link DefinitionLevel#DEFAULT} definition level,
	 * corresponding to the {@link RuleVersion}'s that have a code equal to {@code code}
	 *
	 * @param code the code identifying the rules to be returned
	 * @return the list of all {@link RuleDefinition}'s that have a {@link DefinitionLevel#DEFAULT} definition level,
	 * corresponding to the {@link RuleVersion}'s that have a code equal to {@code code}
	 * @see RuleDefinition
	 * @see DefinitionLevel
	 */
	@Transient
	public List<RuleDefinition> getDefaultRuleDefinitions(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code))
				.map(rv -> rv.getRuleDefinition()).distinct().filter(rd -> rd.getLevel().equals(DefinitionLevel.DEFAULT))
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that matches the collection of {@link RuleDefinitionParameter}
	 * {@code definitionParameters}, corresponding to the {@link RuleVersion}'s
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleDefinition}'s that matches the collection of {@link RuleDefinitionParameter}
	 * {@code definitionParameters}, corresponding to the {@link RuleVersion}'s
	 * @see RuleDefinition
	 * @see RuleDefinition#matches(Collection, DefinitionMatcher.DefinitionMatcherStrategy)
	 * @see RuleDefinitionParameter
	 */
	@Transient
	public List<RuleDefinition> getDefinedRuleDefinitions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getRuleVersions().stream().map(rv -> rv.getRuleDefinition()).distinct()
				.filter(rd -> rd.matches(definitionParameters, DefinitionMatcher.DefinitionMatcherStrategy.AT_LEAST)).collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that best matches the collection of {@link RuleDefinitionParameter}
	 * {@code definitionParameters}, corresponding to the {@link RuleVersion}'s
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleDefinition}'s that best matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}, corresponding to the {@link RuleVersion}'s
	 * @see RuleDefinition
	 * @see RuleDefinitionParameter
	 * @see DefinitionMatcher#getBestMatch(Collection, Collection)
	 */
	@Transient
	public List<RuleDefinition> getBestDefinedRuleDefinitions(Collection<RuleDefinitionParameter> definitionParameters) {
		List<RuleDefinition> result = new ArrayList<>();
		/* In a rule set version, there can be multiple rule version by rule identity so we have to group them by
		rule identity */
		Map<RuleIdentity, List<RuleVersion>> ruleVersionsByRuleIdentity =
				getRuleVersions().stream().collect(Collectors.groupingBy(rv -> rv.getRuleDefinition().getRuleIdentity()));
				
		/* For each list of rule version by rule identity */
		for (List<RuleVersion> ruleVersions : ruleVersionsByRuleIdentity.values()) {
			/* Get the definition that best matches definitionParameters */
			result.add(DefinitionMatcher.getBestMatch(
					ruleVersions.stream().map(rv -> rv.getRuleDefinition()).distinct().collect(Collectors.toList()),
					definitionParameters));
		}
		return result;
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that matches the collection of {@link RuleDefinitionParameter}
	 * {@code definitionParameters}, corresponding to the {@link RuleVersion}'s that have a code equal to {@code code}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleDefinition}'s that matches the collection of {@link RuleDefinitionParameter}
	 * {@code definitionParameters}, corresponding to the {@link RuleVersion}'s that have a code equal to {@code code}
	 * @see RuleDefinition
	 * @see RuleDefinition#matches(Collection, DefinitionMatcher.DefinitionMatcherStrategy)
	 * @see RuleDefinitionParameter
	 */
	@Transient
	public List<RuleDefinition> getDefinedRuleDefinitions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code))
				.map(rv -> rv.getRuleDefinition()).distinct()
				.filter(rd -> rd.matches(definitionParameters, DefinitionMatcher.DefinitionMatcherStrategy.AT_LEAST)).collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleDefinition}'s that best matches the collection of {@link RuleDefinitionParameter}
	 * {@code definitionParameters}, corresponding to the {@link RuleVersion}'s that have a code equal to {@code code}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleDefinition}'s that best matches the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}, corresponding to the {@link RuleVersion}'s that
	 * have a code equal to {@code code}
	 * @see RuleDefinition
	 * @see RuleDefinitionParameter
	 * @see DefinitionMatcher#getBestMatch(Collection, Collection)
	 */
	@Transient
	public RuleDefinition getBestDefinedRuleDefinition(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		/* Filter */
		Stream<RuleVersion> versionsForCode = getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code));
		
		/* Return the definition that best matches definitionParameters */
		return DefinitionMatcher.getBestMatch(
				versionsForCode.map(rv -> rv.getRuleDefinition()).distinct().collect(Collectors.toList()), definitionParameters);
	}
	
	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code}
	 * 
	 * @param code the code identifying the rules to be returned
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code}
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code)).collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate}
	 *
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate}
	 * @see RuleVersion#isEffective(LocalDate)
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(LocalDate effectiveDate) {
		return getRuleVersions().stream().filter(rv -> rv.isEffective(effectiveDate)).collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate}
	 * @see RuleVersion#isEffective(LocalDate)
	 */
	@Transient
	public List<RuleVersion> getRuleVersions(String code, LocalDate effectiveDate) {
		return getRuleVersions().stream()
				.filter(rv -> rv.getRuleDescription().getCode().equals(code) && rv.isEffective(effectiveDate))
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that have a
	 * {@link DefinitionLevel#DEFAULT} definition level
	 *
	 * @return the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that have a
	 * {@link DefinitionLevel#DEFAULT} definition level
	 * @see RuleVersion
	 * @see DefinitionLevel
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions() {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT))
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} that have a {@link DefinitionLevel#DEFAULT} definition level
	 *
	 * @param code the code identifying the rules to be returned
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} that have a {@link DefinitionLevel#DEFAULT} definition level
	 * @see RuleVersion
	 * @see DefinitionLevel
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(String code) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code)
				&& rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT)).collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that have a
	 * {@link RuleDefinition} that have a {@link DefinitionLevel#DEFAULT} definition level
	 *
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that
	 * have a {@link RuleDefinition} that have a {@link DefinitionLevel#DEFAULT} definition level
	 * @see RuleVersion#isEffective(LocalDate)
	 * @see DefinitionLevel
	 */
	@Transient
	public List<RuleVersion> getDefaultRuleVersions(LocalDate effectiveDate) {
		return getRuleVersions().stream()
				.filter(rv -> rv.isEffective(effectiveDate) && rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT))
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} that have a
	 * {@link DefinitionLevel#DEFAULT} definition level
	 *
	 * @param code the code identifying the rules to be returned
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} that have a
	 * {@link DefinitionLevel#DEFAULT} definition level
	 * @see RuleVersion#isEffective(LocalDate)
	 * @see DefinitionLevel
	 */
	@Transient
	public RuleVersion getDefaultRuleVersion(String code, LocalDate effectiveDate) {
		return getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code)
				&& rv.isEffective(effectiveDate) && rv.getRuleDefinition().getLevel().equals(DefinitionLevel.DEFAULT)).findFirst()
				.orElse(null);
	}
	
	/**
	 * Get the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least the
	 * collection of {@link RuleDefinitionParameter} {@code definitionParameters}
	 * 
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that matches at least the
	 * collection of {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcher.DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters) {
		return getRuleVersions().stream()
				.filter(rv -> rv.getRuleDefinition().matches(definitionParameters, DefinitionMatcher.DefinitionMatcherStrategy.AT_LEAST))
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that best matches the
	 * collection of {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleVersion}'s that have a {@link RuleDefinition} that best matches the
	 * collection of {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see DefinitionMatcher#getBestMatch(Collection, Collection)
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters) {
		List<RuleVersion> result = new ArrayList<>();
		/* In a rule set version, there can be multiple rule version by rule definition so we have to group then by rule identity */
		Map<RuleDefinition, List<RuleVersion>> ruleVersionsByRuleDefinition =
				getRuleVersions().stream().collect(Collectors.groupingBy(rv -> rv.getRuleDefinition()));
				
		/* In a rule set version, there can be multiple rule version by rule identity so we have to group then by rule identity */
		Map<RuleIdentity, List<RuleVersion>> ruleVersionsByRuleIdentity =
				getRuleVersions().stream().collect(Collectors.groupingBy(rv -> rv.getRuleDefinition().getRuleIdentity()));
				
		/* For each list of rule version by rule identity */
		for (List<RuleVersion> ruleVersions : ruleVersionsByRuleIdentity.values()) {
			/* Get the definition that best matches definitionParameters */
			result.addAll(ruleVersionsByRuleDefinition.get(DefinitionMatcher.getBestMatch(
					ruleVersions.stream().map(rv -> rv.getRuleDefinition()).collect(Collectors.toList()), definitionParameters)));
		}
		return result;
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that have
	 * a {@link RuleDefinition} that matches at least the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that have
	 * a {@link RuleDefinition} that matches at least the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcher.DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectiveDate) {
		return getRuleVersions().stream()
				.filter(rv -> rv.isEffective(effectiveDate)
						&& rv.getRuleDefinition().matches(definitionParameters, DefinitionMatcher.DefinitionMatcherStrategy.AT_LEAST))
				.collect(Collectors.toList());
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that have
	 * a {@link RuleDefinition} that best matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 *
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that are effective at {@link LocalDate effectiveDate} and that have
	 * a {@link RuleDefinition} that best matches the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 * @see DefinitionMatcher#getBestMatch(Collection, Collection)
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectiveDate) {
		List<RuleVersion> result = new ArrayList<>();
		/* Filter */
		List<RuleVersion> effectiveVersions =
				getRuleVersions().stream().filter(rv -> rv.isEffective(effectiveDate)).collect(Collectors.toList());
				
		/*
		 * In a rule set version, there can be only one rule version that is effective at a specified date by rule definition, in other
		 * words, each filtered rule version has a distinct rule definition so we can map them one to one
		 */
		Map<RuleDefinition, RuleVersion> ruleVersionsByRuleDefinition =
				effectiveVersions.stream().collect(Collectors.toMap(RuleVersion::getRuleDefinition, Function.identity()));
				
		/*
		 * In a rule set version, there can be multiple rule version that are effective at a specified date by rule identity so we have
		 * to group then by rule identity
		 */
		Map<RuleIdentity, List<RuleVersion>> ruleVersionsByRuleIdentity =
				effectiveVersions.stream().collect(Collectors.groupingBy(rv -> rv.getRuleDefinition().getRuleIdentity()));
				
		/* For each list of rule version by rule identity */
		for (List<RuleVersion> ruleVersions : ruleVersionsByRuleIdentity.values()) {
			/* Get the definition that best matches definitionParameters */
			result.add(ruleVersionsByRuleDefinition.get(DefinitionMatcher.getBestMatch(
					ruleVersions.stream().map(rv -> rv.getRuleDefinition()).collect(Collectors.toList()), definitionParameters)));
		}
		return result;
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} that matches at least the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that have a
	 * {@link RuleDefinition} that matches at least the collection of {@link RuleDefinitionParameter} {@code
	 * definitionParameters}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcher.DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		return getRuleVersions().stream()
				.filter(rv -> rv.getRuleDescription().getCode().equals(code)
						&& rv.getRuleDefinition().matches(definitionParameters, DefinitionMatcher.DefinitionMatcherStrategy.AT_LEAST))
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
	 * @see DefinitionMatcher#getBestMatch(Collection, Collection)
	 */
	@Transient
	public List<RuleVersion> getBestDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters) {
		/* Filter */
		List<RuleVersion> versionsForCode =
				getRuleVersions().stream().filter(rv -> rv.getRuleDescription().getCode().equals(code)).collect(Collectors.toList());
				
		/*
		 * In a rule set version, there can be multiple rule version that have a specific code by rule definition so we have to group
		 * them by rule definition
		 */
		Map<RuleDefinition, List<RuleVersion>> ruleVersionsByRuleDefinition =
				versionsForCode.stream().collect(Collectors.groupingBy(RuleVersion::getRuleDefinition));
				
		/* Get the definition that best matches definitionParameters */
		return ruleVersionsByRuleDefinition.get(DefinitionMatcher.getBestMatch(
				versionsForCode.stream().map(rv -> rv.getRuleDefinition()).collect(Collectors.toList()), definitionParameters));
	}

	/**
	 * Get the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} that matches at least the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 *
	 * @param code the code identifying the rules to be returned
	 * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match
	 * @param effectiveDate the {@link LocalDate} at which the returned rules have to be effective
	 * @return the list of all {@link RuleVersion}'s that have a code equal to {@code code} and that are effective at
	 * {@link LocalDate effectiveDate} and that have a {@link RuleDefinition} that matches at least the collection of
	 * {@link RuleDefinitionParameter} {@code definitionParameters}
	 * @see RuleDefinition#matches(Collection, DefinitionMatcher.DefinitionMatcherStrategy)
	 */
	@Transient
	public List<RuleVersion> getDefinedRuleVersions(String code, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectiveDate) {
		return getRuleVersions().stream()
				.filter(rv -> rv.getRuleDescription().getCode().equals(code) && rv.isEffective(effectiveDate)
						&& rv.getRuleDefinition().matches(definitionParameters, DefinitionMatcher.DefinitionMatcherStrategy.AT_LEAST))
				.collect(Collectors.toList());
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
	 * @see DefinitionMatcher#getBestMatch(Collection, Collection)
	 */
	@Transient
	public RuleVersion getBestDefinedRuleVersion(String code, Collection<RuleDefinitionParameter> definitionParameters,
			LocalDate effectiveDate) {
		/* Filter */
		List<RuleVersion> effectiveVersionsForCode = getRuleVersions().stream()
				.filter(rv -> rv.getRuleDescription().getCode().equals(code) && rv.isEffective(effectiveDate))
				.collect(Collectors.toList());
				
		/*
		 * In a rule set version, there can be only one rule version that is effective at a specified date by rule definition, in other
		 * words, each filtered rule version has a distinct rule definition so we can map them one to one
		 */
		Map<RuleDefinition, RuleVersion> ruleVersionByRuleDefinition =
				effectiveVersionsForCode.stream().collect(Collectors.toMap(RuleVersion::getRuleDefinition, Function.identity()));
				
		/* Get the definition that best matches definitionParameters */
		return ruleVersionByRuleDefinition.get(DefinitionMatcher.getBestMatch(
				effectiveVersionsForCode.stream().map(rv -> rv.getRuleDefinition()).collect(Collectors.toList()),
				definitionParameters));
	}
	
	/**
	 * Is this in production at a specified date ? <br>
	 * This is in production at a specified date if the specified date is
	 * between this start date and this end date inclusively
	 * 
	 * @param date
	 *        The date at which this is in production or not
	 * @return true If this is in production at the specified date, false
	 *         otherwise
	 */
	public boolean isInProduction(LocalDateTime date) {
		return !date.isBefore(getProductionStartDate());
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @param productionStartDate  the production start date to set
	 */
	public void setProductionStartDate(LocalDateTime productionStartDate) {
		this.productionStartDate = productionStartDate;
	}
	
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * @param creationAuthor the creation author to set
	 */
	public void setCreationAuthor(String creationAuthor) {
		this.creationAuthor = creationAuthor;
	}
	
	/**
	 * @param creationDate the creation date to set
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * @param modificationAuthor the modification author to set
	 */
	public void setModificationAuthor(String modificationAuthor) {
		this.modificationAuthor = modificationAuthor;
	}
	
	/**
	 * @param modificationDate the modification date to set
	 */
	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
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
	 * @param parent the parent to set (the one from which this was created)
	 */
	public void setParent(RuleSetVersion parent) {
		this.parent = parent;
	}
	
	/**
	 * @param children the children to set (the ones created from this)
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
	 * @param includes the set of included {@link RuleSetVersion} to set
	 */
	public void setIncludes(Set<RuleSetVersion> includes) {
		this.includes = includes;
	}
	
	/**
	 * @param includedIn the set of including {@link RuleSetVersion} to set
	 */
	public void setIncludedIn(Set<RuleSetVersion> includedIn) {
		this.includedIn = includedIn;
	}
	
	/**
	 * @param ruleVersions the set of {@link RuleVersion} to set
	 */
	public void setRuleVersions(Set<RuleVersion> ruleVersions) {
		this.ruleVersions = ruleVersions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RuleSetVersion)) return false;

		RuleSetVersion that = (RuleSetVersion) o;

		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
