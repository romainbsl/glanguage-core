package be.groups.glanguage.core.entities.rule;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Id class for {@link RuleGroupItem}
 *
 * @author michotte
 * @see RuleGroupItem
 */
@Embeddable
public class RuleGroupItemId implements Serializable {

	private static final long serialVersionUID = 6731852480663123776L;

	private Long ruleVersionId;
	private Long ruleId;

	public RuleGroupItemId() {
		super();
	}

	/**
	 * Get the rule version id
	 *
	 * @return the rule version id
	 */
	@Column(name = "RULE_VERSION_ID")
	public Long getRuleVersionId() {
		return ruleVersionId;
	}

	/**
	 * Get the rule id
	 *
	 * @return the rule id
	 */
	@Column(name = "RULE_IDENTITY_ID")
	public Long getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleVersionId the rule version id to set
	 */
	public void setRuleVersionId(Long ruleVersionId) {
		this.ruleVersionId = ruleVersionId;
	}

	/**
	 * @param ruleId the rule id to set
	 */
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RuleGroupItemId)) return false;

		RuleGroupItemId that = (RuleGroupItemId) o;

		if (!ruleVersionId.equals(that.ruleVersionId)) return false;
		return ruleId.equals(that.ruleId);
	}

	@Override
	public int hashCode() {
		int result = ruleVersionId.hashCode();
		result = 31 * result + ruleId.hashCode();
		return result;
	}
}
