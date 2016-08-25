package be.groups.glanguage.glanguage.api.entities.rule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author michotte
 */
@Embeddable
public class RuleGroupItemId implements Serializable {

	private static final long serialVersionUID = 6731852480663123776L;

	private int ruleVersionId;
	private String ruleCode;

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
	@Column(name = "RULE_CODE")
	public String getRuleCode() {
		return ruleCode;
	}

	/**
	 * @param ruleVersionId the ruleVersionId to set
	 */
	public void setRuleVersionId(int ruleVersionId) {
		this.ruleVersionId = ruleVersionId;
	}

	/**
	 * @param ruleCode the ruleCode to set
	 */
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ruleCode == null) ? 0 : ruleCode.hashCode());
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
		if (ruleCode == null) {
			if (other.ruleCode != null)
				return false;
		} else if (!ruleCode.equals(other.ruleCode))
			return false;
		if (ruleVersionId != other.ruleVersionId)
			return false;
		return true;
	}
	
	
}
