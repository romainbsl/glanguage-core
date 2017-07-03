package be.groups.glanguage.glanguage.api.entities.rule;

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
	 * Alias in French language
	 */
	private String aliasFr;

	/**
	 * Alias in Dutch language
	 */
	private String aliasNl;

	/**
	 * Alias in German language
	 */
	private String aliasDe;

	/**
	 * Alias in other language
	 */
	private String aliasX;

	/**
	 * Description in French language
	 */
	private String descriptionFr;

	/**
	 * Description in Dutch language
	 */
	private String descriptionNl;

	/**
	 * Description in German language
	 */
	private String descriptionDe;

	/**
	 * Description in other language
	 */
	private String descriptionX;

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
	 * Get the French alias
	 *
	 * @return the French alias
	 */
	@Column(name = "ALIAS_FR")
	public String getAliasFr() {
		return aliasFr;
	}

	/**
	 * Get the Dutch alias
	 *
	 * @return the Dutch alias
	 */
	@Column(name = "ALIAS_NL")
	public String getAliasNl() {
		return aliasNl;
	}

	/**
	 * Get the German alias
	 *
	 * @return the German alias
	 */
	@Column(name = "ALIAS_DE")
	public String getAliasDe() {
		return aliasDe;
	}

	/**
	 * Get the other alias
	 *
	 * @return the other alias
	 */
	@Column(name = "ALIAS_X")
	public String getAliasX() {
		return aliasX;
	}

	/**
	 * Get the French description
	 *
	 * @return the French description
	 */
	@Column(name = "DESCRIPTION_FR")
	public String getDescriptionFr() {
		return descriptionFr;
	}

	/**
	 * Get the Dutch description
	 *
	 * @return the Dutch description
	 */
	@Column(name = "DESCRIPTION_NL")
	public String getDescriptionNl() {
		return descriptionNl;
	}

	/**
	 * Get the German description
	 *
	 * @return the German description
	 */
	@Column(name = "DESCRIPTION_DE")
	public String getDescriptionDe() {
		return descriptionDe;
	}

	/**
	 * Get the other description
	 *
	 * @return the other description
	 */
	@Column(name = "DESCRIPTION_X")
	public String getDescriptionX() {
		return descriptionX;
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
	 * @param aliasFr the French alias to set
	 */
	public void setAliasFr(String aliasFr) {
		this.aliasFr = aliasFr;
	}

	/**
	 * @param aliasNl the Dutch alias to set
	 */
	public void setAliasNl(String aliasNl) {
		this.aliasNl = aliasNl;
	}

	/**
	 * @param aliasDe the German alias to set
	 */
	public void setAliasDe(String aliasDe) {
		this.aliasDe = aliasDe;
	}

	/**
	 * @param aliasX the other alias to set
	 */
	public void setAliasX(String aliasX) {
		this.aliasX = aliasX;
	}

	/**
	 * @param descriptionFr the French description to set
	 */
	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	/**
	 * @param descriptionNl the Dutch description to set
	 */
	public void setDescriptionNl(String descriptionNl) {
		this.descriptionNl = descriptionNl;
	}

	/**
	 * @param descriptionDe the German description to set
	 */
	public void setDescriptionDe(String descriptionDe) {
		this.descriptionDe = descriptionDe;
	}

	/**
	 * @param descriptionX the other description to set
	 */
	public void setDescriptionX(String descriptionX) {
		this.descriptionX = descriptionX;
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
