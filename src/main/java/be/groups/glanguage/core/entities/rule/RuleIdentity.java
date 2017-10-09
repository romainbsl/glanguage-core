package be.groups.glanguage.core.entities.rule;

import be.groups.glanguage.core.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.core.entities.rule.definition.DefinitionMatcher;
import be.groups.glanguage.core.entities.rule.definition.DefinitionMatcher.DefinitionMatcherStrategy;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A RuleIdentity is the unique identity of a rule<br>
 * A RuleIdentity has a set of {@link RuleDefinition} each corresponding to a {@link DefinitionLevel} with 1 of
 * {@link DefinitionLevel#DEFAULT} and 1 or more of other {@link DefinitionLevel}<br>
 * A RuleIdentity has a collection of {@link RuleGroupItem parent rules}
 *
 * @author michotte
 * @see RuleDefinition
 * @see DefinitionLevel
 * @see RuleGroupItem
 */
@Table(name = "RULE_IDENTITY")
@Entity
@SuppressWarnings("unused")
public class RuleIdentity implements Comparable<RuleIdentity> {

    /**
     * Technical unique id
     */
    private Long id;

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
     * Collection of {@link RuleDefinition}'s of {@link DefinitionLevel#SOCIAL_SECRETARY} level
     */
    private Collection<RuleDefinition> socialSecretaryRuleDefinitions;

    /**
     * Collection of {@link RuleDefinition}'s of {@link DefinitionLevel#EMPLOYER} level
     */
    private Collection<RuleDefinition> employerRuleDefinitions;

    /**
     * Collection of {@link RuleDefinition}'s of {@link DefinitionLevel#JOINT_COMMITTEE} level
     */
    private Collection<RuleDefinition> jointCommitteeRuleDefinitions;

    /**
     * Collection of {@link RuleDefinition}'s of {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT} level
     */
    private Collection<RuleDefinition> collectiveLaborAgreementRuleDefinitions;

    /**
     * Collection of {@link RuleDefinition}'s of {@link DefinitionLevel#CUSTOM} level
     */
    private Collection<RuleDefinition> customRuleDefinitions;

    public RuleIdentity() {
        super();

        this.ruleDefinitions = new HashSet<>();
    }

    /**
     * Get the technical id
     *
     * @return the id
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Get the set of {@link RuleDefinition}<br>
     * Each {@link RuleIdentity} has 1 {@link DefinitionLevel#DEFAULT} rule definition and 1 or more other definition
     *
     * @return the set of {@link RuleDefinition}
     * @see RuleDefinition
     * @see DefinitionLevel
     */
    @OneToMany(mappedBy = "ruleIdentity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<RuleDefinition> getRuleDefinitions() {
        return ruleDefinitions;
    }

    /**
     * Get the collection of {@link RuleGroupItem} (parent rules)
     *
     * @return the collection of {@link RuleGroupItem} (parent rules)
     * @see RuleGroupItem
     */
    @OneToMany(mappedBy = "itemRule")
    public Collection<RuleGroupItem> getGroupItems() {
        return groupItems;
    }

    /**
     * Get the {@link RuleDefinition} of {@link DefinitionLevel#DEFAULT} definition level
     *
     * @return the {@link RuleDefinition} of {@link DefinitionLevel#DEFAULT} definition level
     * @see RuleDefinition
     * @see DefinitionLevel
     */
    @Transient
    public RuleDefinition getDefaultRuleDefinition() {
        if (defaultRuleDefinition == null) {
            allocateAllRuleDefinitions();
        }
        return defaultRuleDefinition;
    }

    /**
     * Get the collection of {@link RuleDefinition} of {@link DefinitionLevel#SOCIAL_SECRETARY} definition level
     *
     * @return the collection of {@link RuleDefinition} of {@link DefinitionLevel#SOCIAL_SECRETARY} definition level,
     * may be empty
     * @see RuleDefinition
     * @see DefinitionLevel
     */
    @Transient
    public Collection<RuleDefinition> getSocialSecretaryRuleDefinitions() {
        if (socialSecretaryRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return socialSecretaryRuleDefinitions;
    }

    /**
     * Get the collection of {@link RuleDefinition} of {@link DefinitionLevel#EMPLOYER} definition level
     *
     * @return the collection of {@link RuleDefinition} of {@link DefinitionLevel#EMPLOYER} definition level,
     * may be empty
     * @see RuleDefinition
     * @see DefinitionLevel
     */
    @Transient
    public Collection<RuleDefinition> getEmployerRuleDefinitions() {
        if (employerRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return employerRuleDefinitions;
    }

    /**
     * Get the collection of {@link RuleDefinition} of {@link DefinitionLevel#JOINT_COMMITTEE} definition level
     *
     * @return the collection of {@link RuleDefinition} of {@link DefinitionLevel#JOINT_COMMITTEE} definition level,
     * may be empty
     * @see RuleDefinition
     * @see DefinitionLevel
     */
    @Transient
    public Collection<RuleDefinition> getJointCommitteeRuleDefinitions() {
        if (jointCommitteeRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return jointCommitteeRuleDefinitions;
    }

    /**
     * Get the collection of {@link RuleDefinition} of {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT} definition
     * level
     *
     * @return the collection of {@link RuleDefinition} of {@link DefinitionLevel#COLLECTIVE_LABOR_AGREEMENT}
     * definition level, may be empty
     * @see RuleDefinition
     * @see DefinitionLevel
     */
    @Transient
    public Collection<RuleDefinition> getCollectiveLaborAgreementRuleDefinitions() {
        if (collectiveLaborAgreementRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return collectiveLaborAgreementRuleDefinitions;
    }

    /**
     * Get the collection of {@link RuleDefinition} of {@link DefinitionLevel#CUSTOM} definition level
     *
     * @return the collection of {@link RuleDefinition} of {@link DefinitionLevel#CUSTOM} definition level,
     * may be empty
     * @see RuleDefinition
     * @see DefinitionLevel
     */
    @Transient
    public Collection<RuleDefinition> getCustomRuleDefinitions() {
        if (customRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return customRuleDefinitions;
    }

    /**
     * Get all {@link RuleDefinition}'s that match at least the collection of {@link RuleDefinitionParameter} {@code
     * parameters}
     *
     * @param parameters the parameters to match
     * @return the list of all {@link RuleDefinition}'s that match at least the collection of
     * {@link RuleDefinitionParameter} {@code parameters}
     * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
     * @see DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)
     * @see DefinitionMatcherStrategy
     */
    @Transient
    public List<RuleDefinition> getRuleDefinitions(Collection<RuleDefinitionParameter> parameters) {
        return getRuleDefinitions().stream().filter(rd -> rd.matches(parameters, DefinitionMatcherStrategy.AT_LEAST))
                .collect(Collectors.toList());
    }

    /**
     * Get the {@link RuleDefinition} that matches the best the collection of {@link RuleDefinitionParameter}
     * {@code parameters}
     *
     * @param parameters the parameters to match
     * @return the {@link RuleDefinition} that best matches the collection of {@link RuleDefinitionParameter} {@code
     * parameters}
     * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
     * @see DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)
     */
    @Transient
    public RuleDefinition getRuleDefinition(Collection<RuleDefinitionParameter> parameters) {
        return DefinitionMatcher.getBestMatch(getRuleDefinitions(), parameters);
    }

    /**
     * Allocate all ruleDefintions to the right map or set depending on its
     * level
     */
    private void allocateAllRuleDefinitions() {
        // Create all maps and set
        this.socialSecretaryRuleDefinitions = new ArrayList<>();
        this.employerRuleDefinitions = new ArrayList<>();
        this.jointCommitteeRuleDefinitions = new ArrayList<>();
        this.collectiveLaborAgreementRuleDefinitions = new ArrayList<>();
        this.customRuleDefinitions = new ArrayList<>();

        // Allocate all rule definitions
        for (RuleDefinition ruleDefinition : ruleDefinitions) {
            switch (ruleDefinition.getLevel()) {
                case DEFAULT:
                    defaultRuleDefinition = ruleDefinition;
                    break;
                case SOCIAL_SECRETARY:
                    socialSecretaryRuleDefinitions.add(ruleDefinition);
                    break;
                case EMPLOYER:
                    employerRuleDefinitions.add(ruleDefinition);
                    break;
                case JOINT_COMMITTEE:
                    jointCommitteeRuleDefinitions.add(ruleDefinition);
                    break;
                case COLLECTIVE_LABOR_AGREEMENT:
                    collectiveLaborAgreementRuleDefinitions.add(ruleDefinition);
                    break;
                case CUSTOM:
                    customRuleDefinitions.add(ruleDefinition);
                    break;
                default:
                    assert false;
            }
        }
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param ruleDefinitions the ruleDefinitions to set
     */
    public void setRuleDefinitions(Set<RuleDefinition> ruleDefinitions) {
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
    public void setDefaultRuleDefinition(RuleDefinition defaultRuleDefinition) {
        this.defaultRuleDefinition = defaultRuleDefinition;
    }

    /**
     * @param socialSecretaryRuleDefinitions the socialSecretaryRuleDefintions to set
     */
    public void setSocialSecretaryRuleDefinitions(Collection<RuleDefinition> socialSecretaryRuleDefinitions) {
        this.socialSecretaryRuleDefinitions = socialSecretaryRuleDefinitions;
    }

    /**
     * @param employerRuleDefinitions the employerRuleDefintions to set
     */
    public void setEmployerRuleDefinitions(Collection<RuleDefinition> employerRuleDefinitions) {
        this.employerRuleDefinitions = employerRuleDefinitions;
    }

    /**
     * @param jointCommitteeRuleDefinitions the jointCommitteRuleDefintions to set
     */
    public void setJointCommitteeRuleDefinitions(Collection<RuleDefinition> jointCommitteeRuleDefinitions) {
        this.jointCommitteeRuleDefinitions = jointCommitteeRuleDefinitions;
    }

    /**
     * @param collectiveLaborAgreementRuleDefinitions the collectiveLaborAgreementRuleDefintions to set
     */
    public void setCollectiveLaborAgreementRuleDefinitions(Collection<RuleDefinition>
                                                                   collectiveLaborAgreementRuleDefinitions) {
        this.collectiveLaborAgreementRuleDefinitions = collectiveLaborAgreementRuleDefinitions;
    }

    /**
     * @param customRuleDefinitions the customRuleDefintions to set
     */
    public void setCustomRuleDefinitions(Collection<RuleDefinition> customRuleDefinitions) {
        this.customRuleDefinitions = customRuleDefinitions;
    }

    @Override
    public int compareTo(RuleIdentity o) {
        return id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuleIdentity)) return false;

        RuleIdentity that = (RuleIdentity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
