package be.groups.glanguage.glanguage.api.entities.rule;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	private RuleVersion ruleVersion;
	private RuleIdentity ruleIdentity;
	private int sequenceNumber;

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
	public RuleVersion getRuleVersion() {
		return ruleVersion;
	}

	/**
	 * @return the ruleIdentity
	 */
	@ManyToOne
	@JoinColumn(name = "RULE_CODE", referencedColumnName = "CODE", insertable = false, updatable = false)
	public RuleIdentity getRuleIdentity() {
		return ruleIdentity;
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
	 * @param ruleVersion
	 *            the ruleVersion to set
	 */
	public void setRuleVersion(RuleVersion ruleVersion) {
		this.ruleVersion = ruleVersion;
	}

	/**
	 * @param ruleIdentity
	 *            the ruleIdentity to set
	 */
	public void setRuleIdentity(RuleIdentity ruleIdentity) {
		this.ruleIdentity = ruleIdentity;
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
		int i = ruleVersion.getId() - o.getRuleVersion().getId();
		if (i == 0) {
			i = sequenceNumber - o.getSequenceNumber();
		}
		return i;
	}

}
