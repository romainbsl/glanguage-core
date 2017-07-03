package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionMatcher.DefinitionMatcherStrategy;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * A RuleDefintion is a definition of a {@link RuleIdentity} for a {@link DefinitionLevel} and a set of
 * {@link RuleDefinitionParameter}<br>
 * A RuleDefintion has a set of {@link RuleVersion} each corresponding to an effective period with at least 1 item
 *
 * @author michotte
 * @see RuleIdentity
 * @see DefinitionLevel
 * @see RuleDefinitionParameter
 * @see RuleVersion
 */
@Entity
@Table(name = "RULE_DEFINITION")
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
    private List<RuleVersion> versions;

    public RuleDefinition() {
        super();

        this.definitionParameters = new TreeSet<>();
        this.versions = new ArrayList<>();
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
     * Get the rule identity of which this is one of the definitions
     *
     * @return the rule identity
     */
    @ManyToOne
    @JoinColumn(name = "RULE_IDENTITY_ID")
    public RuleIdentity getRuleIdentity() {
        return ruleIdentity;
    }

    /**
     * Get the set of {@link RuleDefinitionParameter} order by {@link RuleDefinitionParameter#level}
     *
     * @return tthe set of {@link RuleDefinitionParameter} order by {@link RuleDefinitionParameter#level}
     */
    @OneToMany(mappedBy = "ruleDefinition")
    @OrderBy(value = "level, value")
    public SortedSet<RuleDefinitionParameter> getDefinitionParameters() {
        return definitionParameters;
    }

    /**
     * Get the set of {@link RuleVersion}
     *
     * @return the the set of {@link RuleVersion}
     */
    @OneToMany(mappedBy = "ruleDefinition")
    @OrderBy("VERSION DESC")
    public List<RuleVersion> getVersions() {
        return versions;
    }

    /**
     * Get the {@link RuleVersion} effective at specified {@code effective} date and in production
     * at specified {@code observe} date
     *
     * @param effective the date on which the {@link RuleVersion} returned is effective
     * @param observe   the date on which the {@link RuleVersion} returned is in production
     * @return the {@link RuleVersion} that is effective at the specified {@code effective} date and
     * in production at specified {@code observe} date if it exists, null otherwise
     */
    @Transient
    public RuleVersion getVersion(LocalDate effective, LocalDateTime observe) {
        Iterator<RuleVersion> itRuleVersions = getVersions().iterator();
        while (itRuleVersions.hasNext()) {
            RuleVersion ruleVersion = itRuleVersions.next();
            if (ruleVersion.isEffective(effective) && ruleVersion.getRuleSetVersions().stream().anyMatch(rv -> rv
                    .isInProduction(observe))) {
                return ruleVersion;
            }
        }
        return null;
    }

    /**
     * Get the definition level<br>
     * The level of definition can be :
     * <ul>
     * <li>{@link DefinitionLevel#DEFAULT} if there is no definition parameter</li>
     * <li>{@link DefinitionLevel#CUSTOM} if there are parameters with different level</li>
     * <li>Others {@link DefinitionLevel} if there is only one parameter or if they are all of the same level, in
     * these cases, the definition level is the level of the parameter(s)</li>
     * </ul>
     *
     * @return the definition level
     */
    @Transient
    public DefinitionLevel getLevel() {
        if (getDefinitionParameters() == null || getDefinitionParameters().isEmpty()) {
            return DefinitionLevel.DEFAULT;
        } else if (getDefinitionParameters().size() == 1) {
            return getDefinitionParameters().first().getLevel();
        } else {
            DefinitionLevel referecenceLevel = getDefinitionParameters().first().getLevel();
            Iterator<RuleDefinitionParameter> itDefinitionParameters = getDefinitionParameters().iterator();
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

    /**
     * Does this matches the {@code definitionParameters} parameters according to
     * {@link DefinitionMatcherStrategy strategy} ?
     *
     * @param definitionParameters the collection of {@link RuleDefinitionParameter} to match against {@code
     *                             this} {@link RuleDefinition#definitionParameters}
     * @param strategy             the {@link DefinitionMatcherStrategy} to be used to match {@code
     *                             definitionParameters} against {@code this}
     *                             {@link RuleDefinition#definitionParameters}
     * @return true if the parameters of this match the {@code definitionParameters} parameters according to
     * {@link DefinitionMatcherStrategy strategy} matching strategy, false otherwise
     * @see DefinitionMatcherStrategy
     * @see DefinitionMatcher#matchesAtLeastOneByLevel(Collection, Collection)
     */
    public boolean matches(Collection<RuleDefinitionParameter> definitionParameters,
                           DefinitionMatcherStrategy strategy) {
        return DefinitionMatcher.matches(getDefinitionParameters(), definitionParameters, strategy);
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param ruleIdentity the identity to set
     */
    public void setRuleIdentity(RuleIdentity ruleIdentity) {
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
    public void setVersions(List<RuleVersion> versions) {
        this.versions = versions;
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
        RuleDefinition other = (RuleDefinition) obj;
        if (id != other.id) return false;
        return true;
    }

    @Override
    public String toString() {
        return "RuleDefinition [id=" + id + ", definitionParameters=" + definitionParameters + "]";
    }

}
