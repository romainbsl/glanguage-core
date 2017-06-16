package be.groups.glanguage.glanguage.api.entities.rule;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 
 * @author michotte
 */
@Embeddable
public class RuleGroupItemId implements Serializable {

	private static final long serialVersionUID = 6731852480663123776L;

	private int ruleVersionId;
	private int ruleId;

	public RuleGroupItemId() {
		super();
	}

	/**
	 * @return the ruleVersionId
	 */
	@Column(name = "RULE_VERSION_ID")
	public int getRuleVersionId() {
		return ruleVersionId;
	}

	/**
	 * @return the ruleCode
	 */
	@Column(name = "RULE_IDENTITY_ID")
	public int getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleVersionId
	 *            the ruleVersionId to set
	 */
	public void setRuleVersionId(int ruleVersionId) {
		this.ruleVersionId = ruleVersionId;
	}

	/**
	 * @param ruleCode
	 *            the ruleCode to set
	 */
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ruleId;
		result = prime * result + ruleVersionId;
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
		RuleGroupItemId other = (RuleGroupItemId) obj;
		if (ruleId != other.ruleId)
			return false;
		if (ruleVersionId != other.ruleVersionId)
			return false;
		return true;
	}

}
