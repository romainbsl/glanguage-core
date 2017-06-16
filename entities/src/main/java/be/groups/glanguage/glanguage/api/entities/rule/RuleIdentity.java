package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionException;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher.DefinitionMatcherStrategy;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "itemRule")
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
    public Collection<RuleDefinition> getSocialSecretaryRuleDefinitions() {
        if (socialSecretaryRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return socialSecretaryRuleDefinitions;
    }

    /**
     * @return the employerRuleDefintions
     */
    @Transient
    public Collection<RuleDefinition> getEmployerRuleDefinitions() {
        if (employerRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return employerRuleDefinitions;
    }

    /**
     * @return the jointCommitteRuleDefintions
     */
    @Transient
    public Collection<RuleDefinition> getJointCommitteeRuleDefinitions() {
        if (jointCommitteeRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return jointCommitteeRuleDefinitions;
    }

    /**
     * @return the collectiveLaborAgreementRuleDefintions
     */
    @Transient
    public Collection<RuleDefinition> getCollectiveLaborAgreementRuleDefinitions() {
        if (collectiveLaborAgreementRuleDefinitions == null) {
            allocateAllRuleDefinitions();
        }
        return collectiveLaborAgreementRuleDefinitions;
    }

    /**
     * @return the customRuleDefintions
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
     * @param parameters The parameters to match
     * @return The list of all {@link RuleDefinition}'s that match at least the collection of
     * {@link RuleDefinitionParameter} {@code parameters}
     * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
     * @see DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)
     */
    @Transient
    public List<RuleDefinition> getRuleDefinitions(Collection<RuleDefinitionParameter> parameters) {
        try {
            return getRuleDefinitions().stream()
                    .filter(rd -> rd.matches(parameters, DefinitionMatcherStrategy.AT_LEAST))
                    .collect(Collectors.toList());
        } catch (DefinitionException de) {
            throw new RuntimeException("Unable to find the best rule definition for rule " + getId() + " - " +
                    "parameters" + " not precise enough",
                    de);
        }
    }

    /**
     * Get the {@link RuleDefinition} that matches the best the collection of {@link RuleDefinitionParameter}
     * {@code parameters}
     *
     * @param parameters The parameters to match
     * @return The list of all {@link RuleDefinition}'s that match at least the collection of
     * {@link RuleDefinitionParameter} {@code parameters}
     * @see RuleDefinition#matches(Collection, DefinitionMatcherStrategy)
     * @see DefinitionMatcher#matches(Collection, Collection, DefinitionMatcherStrategy)
     */
    @Transient
    public RuleDefinition getRuleDefinition(Collection<RuleDefinitionParameter> parameters) {
        try {
            return DefinitionMatcher.getBestMatch(getRuleDefinitions(), parameters);
        } catch (DefinitionException de) {
            throw new RuntimeException("Unable to find the best rule definition for rule " + getId() + " - " +
                    "parameters" + " not precise enough",
                    de);
        }
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
    public void setId(int id) {
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
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RuleIdentity other = (RuleIdentity) obj;
        return id == other.id;
    }

    @Override
    public int compareTo(RuleIdentity o) {
        return id - o.id;
    }

}
