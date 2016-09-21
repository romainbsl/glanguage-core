package be.groups.glanguage.glanguage.api.entities.formula.description;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author DUPIREFR
 * 		
 */
@Entity
@Table(name = "FORMULA_PARAMETER_DESCRIPTION")
public class FormulaParameterDescription implements Comparable<FormulaParameterDescription> {
	
	/*
	 * Fields
	 */
	private Integer id;
	
	private Integer sequenceNumber;
	
	private FormulaParametersCombination parametersCombination;
	
	private FormulaReturnType returnType;

	private String name;
	
	private String descriptionFr;
	
	private String descriptionNl;
	
	private String descriptionDe;
	
	private String descriptionX;
	
	/*
	 * Constructors
	 */
	public FormulaParameterDescription() {
		super();
	}
	
	/*
	 * Getters
	 */
	@Id
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "SEQUENCE_NUMBER", nullable = false)
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	
	@ManyToOne
	@JoinColumn(name = "PARAMETER_COMBINATION_ID", referencedColumnName = "ID")
	public FormulaParametersCombination getParametersCombination() {
		return parametersCombination;
	}
	
	@Column(name = "FORMULA_RETURN_TYPE_ID", nullable = false)
	@Convert(converter = FormulaReturnTypeConverter.class)
	public FormulaReturnType getReturnType() {
		return returnType;
	}
	
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}
	
	@Column(name = "DESCRIPTION_FR", nullable = true)
	public String getDescriptionFr() {
		return descriptionFr;
	}
	
	@Column(name = "DESCRIPTION_NL", nullable = true)
	public String getDescriptionNl() {
		return descriptionNl;
	}
	
	@Column(name = "DESCRIPTION_DE", nullable = true)
	public String getDescriptionDe() {
		return descriptionDe;
	}
	
	@Column(name = "DESCRIPTION_X", nullable = true)
	public String getDescriptionX() {
		return descriptionX;
	}
	
	/*
	 * Setters
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public void setParametersCombination(FormulaParametersCombination parametersCombination) {
		this.parametersCombination = parametersCombination;
	}

	public void setReturnType(FormulaReturnType returnType) {
		this.returnType = returnType;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}
	
	public void setDescriptionNl(String descriptionNl) {
		this.descriptionNl = descriptionNl;
	}
	
	public void setDescriptionDe(String descriptionDe) {
		this.descriptionDe = descriptionDe;
	}
	
	public void setDescriptionX(String descriptionX) {
		this.descriptionX = descriptionX;
	}

	/*
	 * Methods
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(FormulaParameterDescription o) {
		return this.sequenceNumber.compareTo(o.sequenceNumber);
	}
	
}
