package be.groups.glanguage.glanguage.api.entities.ruleset;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "RULE_SET")
public class RuleSet {

	/**
	 * Technical unique ID
	 */
	private int id;
		
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

	/**
	 * Versions
	 */
	private Set<RuleSetVersion> versions;

	public RuleSet() {
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
	 * @return the versions
	 */
	@OneToMany(mappedBy = "ruleSet")
	@OrderBy("EXPLOITATION_START_DATE DESC")
	public Set<RuleSetVersion> getVersions() {
		return versions;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @param versions the versions to set
	 */
	public void setVersions(Set<RuleSetVersion> versions) {
		this.versions = versions;
	}
	
}
