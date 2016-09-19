package be.groups.glanguage.glanguage.api.entities.rule;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author michotte
 */
@Entity
@Table(name = "RULE_GROUP_ITEM", uniqueConstraints = @UniqueConstraint(columnNames = { "RULE_VERSION_ID",
		"SEQUENCE_NUMBER" }) )
public class RuleGroupItem implements Comparable<RuleGroupItem> {

	private RuleGroupItemId id;
	private RuleVersion groupRule;
	private RuleIdentity itemRule;
	private int sequenceNumber;
	private RuleVersion effectiveRuleVersion;

	public RuleGroupItem() {
		super();
	}

	/**
	 * @return the id
	 */
	@EmbeddedId
	public RuleGroupItemId getId() {
		return id;
	}

	/**
	 * @return the ruleVersion
	 */
	@ManyToOne
	@JoinColumn(name = "RULE_VERSION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	public RuleVersion getGroupRule() {
		return groupRule;
	}

	/**
	 * @return the ruleIdentity
	 */
	@ManyToOne
	@JoinColumn(name = "RULE_IDENTITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	public RuleIdentity getItemRule() {
		return itemRule;
	}

	@Transient
	public RuleVersion getEffectiveRule() {
		return effectiveRuleVersion;
	}
	
	/**
	 * @return the sequenceNumber
	 */
	@Column(name = "SEQUENCE_NUMBER")
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(RuleGroupItemId id) {
		this.id = id;
	}

	/**
	 * @param groupRule
	 *            the ruleVersion to set
	 */
	public void setGroupRule(RuleVersion groupRule) {
		this.groupRule = groupRule;
	}

	/**
	 * @param itemRule
	 *            the ruleIdentity to set
	 */
	public void setItemRule(RuleIdentity itemRule) {
		this.itemRule = itemRule;
	}

	/**
	 * @param sequenceNumber
	 *            the sequenceNumber to set
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	@Override
	public int compareTo(RuleGroupItem o) {
		int i = groupRule.getId() - o.getGroupRule().getId();
		if (i == 0) {
			i = sequenceNumber - o.getSequenceNumber();
		}
		return i;
	}

}
