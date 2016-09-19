package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_ABS)
public class FormulaMathAbs extends MathFormula {
	
	public FormulaMathAbs() {
		super();
	}

	public FormulaMathAbs(List<AbstractFormula> parameters) {
		super(FormulaDescription.F_ABS, parameters);
	}

	@Override
	public Double getNumericValueImpl() {
		return Math.abs(getParameters().get(0).getNumericValue());
	}
	
	@Override
	public String operationAsText() {
		return "abs";
	}
	
}
