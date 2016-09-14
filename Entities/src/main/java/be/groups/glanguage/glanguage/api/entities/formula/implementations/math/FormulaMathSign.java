package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import java.util.LinkedList;

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
	
	public FormulaMathSign(LinkedList<AbstractFormula> parameters) {
		super(parameters);
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
