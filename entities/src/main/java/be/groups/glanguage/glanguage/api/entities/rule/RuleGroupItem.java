package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A RuleGroupItem represents an item of the hierarchy of rules<br>
 * A RuleGroupItem links a parent {@link RuleVersion} to a child {@link RuleIdentity}<br>
 * A RuleGroupItem has a sequence number to determine the order of the child rules in the hierarchy of the parent
 * one<br>
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
	 * Get the id
	 *
	 * @return the id
	 */
	@EmbeddedId
	public RuleGroupItemId getId() {
		return id;
	}

	/**
	 * Get the group rule (parent rule)
	 *
	 * @return the group rule
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RULE_VERSION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	public RuleVersion getGroupRule() {
		return groupRule;
	}

	/**
	 * Get the item rule (child rule)
	 *
	 * @return the item rule
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RULE_IDENTITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	public RuleIdentity getItemRule() {
		return itemRule;
	}
	
	/**
	 * Get the sequence number
	 *
	 * @return the sequence number
	 */
	@Column(name = "SEQUENCE_NUMBER")
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Get the rule version referenced by {@link RuleGroupItem#getItemRule()} with an evaluator (can be null)
	 * Evaluation process :
	 * <ol>
	 *     <li>If {@link RuleGroupItem#referencedRule} is null and {@code evaluator} is not null, delegate to
	 *     		{@link Evaluator#getRuleVersion(String)} and assign the returned object to {@link RuleGroupItem#referencedRule}</li>
	 *     <li>Return the {@link RuleGroupItem#referencedRule}</li>
	 * </ol>
	 * @param evaluator nullable, if present it is used to retrieve the {@link RuleVersion} corresponding to the
	 * {@link RuleIdentity} {@code itemRule}
	 * @return the referenced rule if it exists or if it exists in {@code evaluator}, null otherwise
	 */
	@Transient
	public RuleVersion getReferencedRule(Evaluator evaluator) {
		if (referencedRule == null && evaluator != null) {
			referencedRule = evaluator.getRuleVersion(String.valueOf(getItemRule().getId()));
		}
		return referencedRule;
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
	public int compareTo(@NotNull RuleGroupItem o) {
		int i = groupRule.getId() - o.getGroupRule().getId();
		if (i == 0) {
			i = sequenceNumber - o.getSequenceNumber();
		}
		return i;
	}

}
