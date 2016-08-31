package be.groups.glanguage.glanguage.api.entities.rule;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	 * @return the id
	 */
	@Id
	@Column(name = "ID")
	public int getId() {
		return id;
	}
	
	/**
	 * @return the ruleVersion
	 */
	@OneToMany(mappedBy = "ruleDescription")
	public List<RuleVersion> getRuleVersions() {
		return ruleVersions;
	}
	
	/**
	 * @return the code
	 */
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}
	
	/**
	 * @return the aliasFr
	 */
	@Column(name = "ALIAS_FR")
	public String getAliasFr() {
		return aliasFr;
	}
	
	/**
	 * @return the aliasNl
	 */
	@Column(name = "ALIAS_NL")
	public String getAliasNl() {
		return aliasNl;
	}
	
	/**
	 * @return the aliasDe
	 */
	@Column(name = "ALIAS_DE")
	public String getAliasDe() {
		return aliasDe;
	}
	
	/**
	 * @return the aliasX
	 */
	@Column(name = "ALIAS_X")
	public String getAliasX() {
		return aliasX;
	}
	
	/**
	 * @return the descriptionFr
	 */
	@Column(name = "DESCRIPTION_FR")
	public String getDescriptionFr() {
		return descriptionFr;
	}
	
	/**
	 * @return the descriptionNl
	 */
	@Column(name = "DESCRIPTION_NL")
	public String getDescriptionNl() {
		return descriptionNl;
	}
	
	/**
	 * @return the descriptionDe
	 */
	@Column(name = "DESCRIPTION_DE")
	public String getDescriptionDe() {
		return descriptionDe;
	}
	
	/**
	 * @return the descriptionX
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
	 * @param aliasFr the aliasFr to set
	 */
	public void setAliasFr(String aliasFr) {
		this.aliasFr = aliasFr;
	}
	
	/**
	 * @param aliasNl the aliasNl to set
	 */
	public void setAliasNl(String aliasNl) {
		this.aliasNl = aliasNl;
	}
	
	/**
	 * @param aliasDe the aliasDe to set
	 */
	public void setAliasDe(String aliasDe) {
		this.aliasDe = aliasDe;
	}
	
	/**
	 * @param aliasX the aliasX to set
	 */
	public void setAliasX(String aliasX) {
		this.aliasX = aliasX;
	}
	
	/**
	 * @param descriptionFr the descriptionFr to set
	 */
	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}
	
	/**
	 * @param descriptionNl the descriptionNl to set
	 */
	public void setDescriptionNl(String descriptionNl) {
		this.descriptionNl = descriptionNl;
	}
	
	/**
	 * @param descriptionDe the descriptionDe to set
	 */
	public void setDescriptionDe(String descriptionDe) {
		this.descriptionDe = descriptionDe;
	}
	
	/**
	 * @param descriptionX the descriptionX to set
	 */
	public void setDescriptionX(String descriptionX) {
		this.descriptionX = descriptionX;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		RuleDescription other = (RuleDescription) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
