package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RULE_DESCRIPTION")
public class RuleDescription {

	/**
	 * Technical unique Id
	 */
	private int id;

	/**
	 * RuleVersion's of which this is the description
	 */
	private List<RuleVersion> ruleVersions;

	/**
	 * Code
	 */
	private String code;

	/**
	 * Alias in multiple languages
	 */
	private MultilingualString alias;

	/**
	 * Description in multiple languages
	 */
	private MultilingualString description;

	public RuleDescription() {
		super();
	}

	/**
	 * Get the technical id
	 *
	 * @return the id
	 */
	@Id
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	/**
	 * Get the rule version of which {@code this} is the description
	 *
	 * @return the rule version of which {@code this} is the description
	 */
	@OneToMany(mappedBy = "ruleDescription")
	public List<RuleVersion> getRuleVersions() {
		return ruleVersions;
	}

	/**
	 * Get the code
	 *
	 * @return the code
	 */
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	/**
	 * @return the alias
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ALIAS_ID", referencedColumnName = "ID")
	public MultilingualString getAlias() {
		return alias;
	}

	/**
	 * @return the description
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DESC_ID", referencedColumnName = "ID")
	public MultilingualString getDescription() {
		return description;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param ruleVersions the ruleVersions to set
	 */
	public void setRuleVersions(List<RuleVersion> ruleVersions) {
		this.ruleVersions = ruleVersions;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param alias
	 * 			the alias to set
	 */
	public void setAlias(MultilingualString alias) {
		this.alias = alias;
	}

	/**
	 * @param description
	 * 			the description to et
	 */
	public void setDescription(MultilingualString description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleDescription other = (RuleDescription) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
