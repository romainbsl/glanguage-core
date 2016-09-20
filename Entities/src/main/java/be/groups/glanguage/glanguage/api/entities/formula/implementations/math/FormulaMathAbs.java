package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_ABS)
public class FormulaMathAbs extends MathFormula {
	
	public FormulaMathAbs() {
		super();
	}

	public FormulaMathAbs(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
	}

	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
		return Math.abs(getParameters().get(0).getNumericValue());
	}
	
	@Override
	public String operationAsText() {
		return "abs";
	}
	
}
