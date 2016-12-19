package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;

import javax.persistence.*;

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
	private RuleVersion referencedRule;

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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RULE_VERSION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	public RuleVersion getGroupRule() {
		return groupRule;
	}

	/**
	 * @return the ruleIdentity
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RULE_IDENTITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	public RuleIdentity getItemRule() {
		return itemRule;
	}
	
	/**
	 * @return the sequenceNumber
	 */
	@Column(name = "SEQUENCE_NUMBER")
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param evaluator nullable, if present it is used to retrieve the {@link RuleVersion} corresponding to the
	 * {@link RuleIdentity} {@code itemRule}
	 * @return the referencedRule
	 */
	@Transient
	public RuleVersion getReferencedRule(Evaluator evaluator) {
		if (referencedRule == null && evaluator != null) {
			referencedRule = evaluator.getRuleVersion(String.valueOf(getItemRule().getId()));
		}
		return referencedRule;
	}

	/**
	 * @return true if {@code referencedRule} is not null, false otherwise
	 */
	@Transient
	public boolean isBranched() {
		return referencedRule != null;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(RuleGroupItemId id) {
		this.id = id;
	}

	/**
	 * @param groupRule the ruleVersion to set
	 */
	public void setGroupRule(RuleVersion groupRule) {
		this.groupRule = groupRule;
	}

	/**
	 * @param itemRule the ruleIdentity to set
	 */
	public void setItemRule(RuleIdentity itemRule) {
		this.itemRule = itemRule;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @param referencedRule the referencedRule to set
	 */
	public void setReferencedRule(RuleVersion referencedRule) {
		this.referencedRule = referencedRule;
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
