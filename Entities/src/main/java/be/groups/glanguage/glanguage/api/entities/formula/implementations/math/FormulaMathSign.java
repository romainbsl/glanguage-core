package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_SIGN)
public class FormulaMathSign extends MathFormula {
	
	public FormulaMathSign() {
		super();
	}
	
	public FormulaMathSign(List<AbstractFormula> parameters) {
		super(FormulaDescription.F_SIGN, parameters);
	}
	
	@Override
	public Double getNumericValueImpl() {
		return Double.valueOf(Double.compare(getParameters().get(0).getNumericValue(), 0.0));
	}
	
	@Override
	public String operationAsText() {
		return "sign";
	}
	
}
