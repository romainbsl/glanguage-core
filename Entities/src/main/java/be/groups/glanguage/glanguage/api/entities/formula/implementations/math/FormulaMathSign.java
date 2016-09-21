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
@DiscriminatorValue(FormulaType.Values.F_SIGN)
public class FormulaMathSign extends MathFormula {
	
	public FormulaMathSign() {
		super();
	}
	
	public FormulaMathSign(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
	}

	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
		return Double.valueOf(Double.compare(getParameters().get(0).getNumericValue(), 0.0));
	}
	
	@Override
	public String operationAsText() {
		return "sign";
	}
	
}
