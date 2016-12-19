package be.groups.glanguage.glanguage.api.entities.rule.definition;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;

/**
 * A DefintionLevelParameter is a pair of RuleDefintionLevel and value
 * 
 * A RuleDefintionLevelParameter can be compared to another one to check if this
 * matches the other one A RuleDefintionLevelParameter matches another one if
 * they have the same level and the same value
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
	 * @return the id
	 */
	@EmbeddedId
	public RuleDefinitionParameterId getId() {
		return id;
	}
	
	/**
	 * @return the ruleDefinitionLevelDescription
	 */
	@ManyToOne
	@JoinColumn(name = "RULE_DEFINITION_ID", insertable = false, updatable = false)
	public RuleDefinition getRuleDefinition() {
		return ruleDefinition;
	}
	
	/**
	 * @return the level
	 */
	@Column(name = "LEVEL_ID", insertable = false, updatable = false)
	@Convert(converter = DefinitionLevelConverter.class)
	public DefinitionLevel getLevel() {
		return level;
	}
	
	/**
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
	 * Returns the result of the comparison between this level and other's level and if they are equal, between this value and other's
	 * value
	 * 
	 * @param other the other object to compare to
	 * @return a negative integer, zero, or a positive integer as this is less than, equal to, or more than the {@code other}
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
