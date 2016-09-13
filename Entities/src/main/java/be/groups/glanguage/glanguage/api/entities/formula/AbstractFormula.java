package be.groups.glanguage.glanguage.api.entities.formula;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

/**
 * Common implementation of a formula <br>
 * <br>
 * An AbstractFormula has : <br>
 * - a FormulaDescription <br>
 * - a parent Formula of which this is a parameter <br>
 * - an sequence number representing the position of this in the parent's
 * parameters sequence <br>
 * - a set of sub-Formula's representing the parameters of this Formula <br>
 * - an evaluated status <br>
 * - a value <br>
 * <br>
 * An AbstractFormula can be evaluated - can be given a value which type
 * corresponds to its {@link FormulaReturnType}. <br>
 * How an AbstractFormula is evaluated depends on its {@link FormulaType}. <br>
 * Evaluating an AbstractFormula consists in applying its own evaluation method
 * on the results of the evaluation of its sub- {@link AbstractFormula}'s
 * parameters.
 * 
 * @author michotte
 */
@Entity
@Table(name = "FORMULA", uniqueConstraints = @UniqueConstraint(columnNames = { "parent_formula_id",
		"sequence_number" }) )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "FORMULA_DESCRIPTION_ID", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AbstractFormula {

	/**
	 * Technical unique ID
	 */
	private int id;

	/**
	 * Set of RuleVersion this is the formula
	 */
	private Set<RuleVersion> ruleVersions;

	/**
	 * Formula description
	 */
	private FormulaDescription description;

	/**
	 * Parent formula
	 */
	private AbstractFormula parentFormula;

	/**
	 * Parameters
	 */
	protected List<AbstractFormula> parameters;

	/**
	 * Sequence number of this parameter in parent formula
	 */
	private Integer sequenceNumber;

	/**
	 * Constant value (if type is terminal)
	 */
	private String constantValue;

	public AbstractFormula() {
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
	 * @return the ruleVersions
	 */
	@OneToMany(mappedBy = "formula")
	public Set<RuleVersion> getRuleVersions() {
		return ruleVersions;
	}

	/**
	 * @return the description
	 */
	@Convert(converter = FormulaDescriptionConverter.class)
	public FormulaDescription getDescription() {
		return description;
	}

	/**
	 * @return the parentFormula
	 */
	@ManyToOne
	@JoinColumn(name = "PARENT_FORMULA_ID", updatable = false, nullable = true)
	public AbstractFormula getParentFormula() {
		return parentFormula;
	}

	/**
	 * @return the parameters
	 */
	@OneToMany(mappedBy = "parentFormula", cascade = CascadeType.ALL)
	@OrderBy("SEQUENCE_NUMBER")
	public List<AbstractFormula> getParameters() {
		return parameters;
	}

	/**
	 * @return the sequenceNumber
	 */
	@Column(name = "SEQUENCE_NUMBER", updatable = false, nullable = true)
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @return the constantValue
	 */
	@Column(name = "VALUE", nullable = true)
	public String getConstantValue() {
		return constantValue;
	}

	@Transient
	public abstract boolean isTerminal();

	@Transient
	public abstract FormulaReturnType getReturnType();

	/**
	 * @return Default true
	 */
	@Transient
	public boolean isValuable() {
		return true;
	}

	@Transient
	public Object getValue() {
		try {
			switch (getReturnType()) {
			case INTEGER:
				return getIntegerValue();
			case NUMERIC:
				return getNumericValue();
			case STRING:
				return getStringValue();
			case BOOLEAN:
				return getBooleanValue();
			case DATE:
				return getDateValue();
			default:
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Transient
	public abstract Integer getIntegerValue();

	@Transient
	public abstract Double getNumericValue();

	@Transient
	public abstract String getStringValue();

	@Transient
	public abstract Boolean getBooleanValue();

	@Transient
	public abstract LocalDate getDateValue();

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param ruleVersions
	 *            the ruleVersions to set
	 */
	public void setRuleVersions(Set<RuleVersion> ruleVersions) {
		this.ruleVersions = ruleVersions;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(FormulaDescription description) {
		this.description = description;
	}

	/**
	 * @param parentFormula
	 *            the parentFormula to set
	 */
	public void setParentFormula(AbstractFormula parentFormula) {
		this.parentFormula = parentFormula;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(List<AbstractFormula> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @param sequenceNumber
	 *            the sequenceNumber to set
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @param constantValue
	 *            the constantValue to set
	 */
	public void setConstantValue(String constantValue) {
		this.constantValue = constantValue;
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
		AbstractFormula other = (AbstractFormula) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public abstract String asText();

}
