/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.rule.definition;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 
 * @author michotte
 */
@Embeddable
public class RuleDefinitionParameterId implements Serializable {

	private static final long serialVersionUID = -512381237663922211L;

	private int ruleDefinitionId;
	private int levelId;
	private String value;

	public RuleDefinitionParameterId() {
		super();
	}

	/**
	 * @return the ruleDefinitionId
	 */
	@Column(name = "RULE_DEFINITION_ID")
	public int getRuleDefinitionId() {
		return ruleDefinitionId;
	}

	/**
	 * @return the levelId
	 */
	@Column(name = "LEVEL_ID")
	public int getLevelId() {
		return levelId;
	}

	/**
	 * @return the value
	 */
	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	/**
	 * @param ruleDefinitionId
	 *            the ruleDefinitionId to set
	 */
	public void setRuleDefinitionId(int ruleDefinitionId) {
		this.ruleDefinitionId = ruleDefinitionId;
	}

	/**
	 * @param levelId
	 *            the levelId to set
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + levelId;
		result = prime * result + ruleDefinitionId;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleDefinitionParameterId other = (RuleDefinitionParameterId) obj;
		if (levelId != other.levelId)
			return false;
		if (ruleDefinitionId != other.ruleDefinitionId)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
