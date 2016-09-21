package be.groups.glanguage.glanguage.api.entities.formula.description;

import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 
 * @author DUPIREFR
 * 		
 */
@Entity
@Table(name = "FORMULA_PARAMETER_COMBINATION")
public class FormulaParametersCombination {
	
	/*
	 * Fields
	 */
	private Integer id;
	
	private FormulaReturnType returnType;
	
	private FormulaDescription description;
	
	private SortedSet<FormulaParameterDescription> parametersDescriptions;
	
	/*
	 * Constructors
	 */
	public FormulaParametersCombination() {
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
	
	@Column(name = "FORMULA_RETURN_TYPE_ID", nullable = false)
	@Convert(converter = FormulaReturnTypeConverter.class)
	public FormulaReturnType getReturnType() {
		return returnType;
	}
	
	@ManyToOne
	@JoinColumn(name = "FORMULA_DESCRIPTION_ID", referencedColumnName = "ID")
	public FormulaDescription getDescription() {
		return description;
	}
	
	@OneToMany(mappedBy = "parametersCombination", fetch = FetchType.EAGER)
	@OrderBy("SEQUENCE_NUMBER")
	public SortedSet<FormulaParameterDescription> getParametersDescriptions() {
		return parametersDescriptions;
	}
	
	/*
	 * Setters
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setReturnType(FormulaReturnType returnType) {
		this.returnType = returnType;
	}
	
	public void setDescription(FormulaDescription description) {
		this.description = description;
	}
	
	public void setParametersDescriptions(SortedSet<FormulaParameterDescription> parametersDescriptions) {
		this.parametersDescriptions = parametersDescriptions;
	}
	
}
