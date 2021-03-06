package be.groups.glanguage.core.entities.rule.definition;

import be.groups.glanguage.core.entities.rule.RuleDefinition;

import javax.persistence.*;

/**
 * A RuleDefinitionParameter is a pair of {@link DefinitionLevel} and value
 * <p>
 * A RuleDefinitionParameter can be compared to another one to check if this matches the other one<br>
 * A RuleDefinitionParameter matches another one if they have the same level and the same value
 *
 * @author michotte
 */
@Entity
@Table(name = "RULE_DEFINITION_PARAMETER")
public class RuleDefinitionParameter implements Comparable<RuleDefinitionParameter> {

    /**
     * Id
     */
    private RuleDefinitionParameterId id;

    /**
     * The description this is part of
     */
    private RuleDefinition ruleDefinition;

    /**
     * The level of this parameter
     */
    private DefinitionLevel level;

    /**
     * The value of this parameter
     */
    private String value;

    public RuleDefinitionParameter() {
        super();
    }

    public RuleDefinitionParameter(DefinitionLevel level, String value) {
        this();
        this.level = level;
        this.value = value;
    }

    /**
     * Get the technical id
     *
     * @return the id
     */
    @EmbeddedId
    public RuleDefinitionParameterId getId() {
        return id;
    }

    /**
     * Get the rule definition of which {@code this} is a definition parameter
     *
     * @return the rule definition of which {@code this} is a definition parameter
     */
    @ManyToOne
    @JoinColumn(name = "RULE_DEFINITION_ID", insertable = false, updatable = false)
    public RuleDefinition getRuleDefinition() {
        return ruleDefinition;
    }

    /**
     * Get the definition level
     *
     * @return the the definition level
     */
    @Column(name = "LEVEL_ID", insertable = false, updatable = false)
    @Convert(converter = DefinitionLevelConverter.class)
    public DefinitionLevel getLevel() {
        return level;
    }

    /**
     * Get the value
     *
     * @return the value
     */
    @Column(name = "VALUE", insertable = false, updatable = false)
    public String getValue() {
        return value;
    }

    /**
     * Does this match another parameter ?<br>
     * This matches another parameter if they have the same level and the same value
     *
     * @param parameter the other parameter to match with
     * @return true if this matches another parameter, false otherwise
     */
    public boolean matches(RuleDefinitionParameter parameter) {
        return this.level.equals(parameter.level) && this.value.equals(parameter.value);
    }

    /**
     * @param id the id to set
     */
    public void setId(RuleDefinitionParameterId id) {
        this.id = id;
    }

    /**
     * @param ruleDefinition the ruleDefinition to set
     */
    public void setRuleDefinition(RuleDefinition ruleDefinition) {
        this.ruleDefinition = ruleDefinition;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(DefinitionLevel level) {
        this.level = level;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Compares this with {@code other} for ordering<br>
     * Returns the result of the comparison between this level and other's level and if they are equal, between this
     * value and other's value
     *
     * @param other the other object to compare to
     * @return a negative integer, zero, or a positive integer as this is less than, equal to, or more than the {@code
     * other}
     */
    public int compareTo(RuleDefinitionParameter other) {
        int i = level.compareTo(other.level);
        if (i == 0) {
            i = value.compareTo(other.value);
        }
        return i;
    }

    public String toString() {
        return "RuleDefinitionParameter [level=" + level + ", value=" + value + "]";
    }

}
