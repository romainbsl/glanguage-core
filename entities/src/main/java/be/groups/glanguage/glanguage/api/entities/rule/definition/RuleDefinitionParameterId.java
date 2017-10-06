/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.rule.definition;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Id class for {@link RuleDefinitionParameter}
 *
 * @author michotte
 * @see RuleDefinitionParameter
 */
@Embeddable
public class RuleDefinitionParameterId implements Serializable {

	private static final long serialVersionUID = -512381237663922211L;

	private Long ruleDefinitionId;
	private Long levelId;
	private String value;

	public RuleDefinitionParameterId() {
		super();
	}

	/**
	 * @return the ruleDefinitionId
	 */
	@Column(name = "RULE_DEFINITION_ID")
	public Long getRuleDefinitionId() {
		return ruleDefinitionId;
	}

	/**
	 * @return the levelId
	 */
	@Column(name = "LEVEL_ID")
	public Long getLevelId() {
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
	public void setRuleDefinitionId(Long ruleDefinitionId) {
		this.ruleDefinitionId = ruleDefinitionId;
	}

	/**
	 * @param levelId
	 *            the levelId to set
	 */
	public void setLevelId(Long levelId) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RuleDefinitionParameterId)) return false;

		RuleDefinitionParameterId that = (RuleDefinitionParameterId) o;

		if (!ruleDefinitionId.equals(that.ruleDefinitionId)) return false;
		if (!levelId.equals(that.levelId)) return false;
		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		int result = ruleDefinitionId.hashCode();
		result = 31 * result + levelId.hashCode();
		result = 31 * result + value.hashCode();
		return result;
	}
}
