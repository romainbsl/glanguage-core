package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_MIN)
public class FormulaExtremumMin extends ExtremumFormula {
	
	public FormulaExtremumMin() {
		super();
	}
	
	public FormulaExtremumMin(List<AbstractFormula> parameters) {
		super(parameters);
	}
	
	@Transient
	@Override
	public Integer getIntegerValueImpl() {
		return getNumericValue().intValue();
	}
	
	@Transient
	@Override
	public Double getNumericValueImpl() {
		Iterator<AbstractFormula> itParameters = getParameters().iterator();
		double temp;
		double result = Double.MAX_VALUE;
		do {
			temp = itParameters.next().getNumericValue();
			if (result > temp) {
				result = temp;
			}
		} while (itParameters.hasNext());
		return result;
	}
	
	@Override
	public String operationAsText() {
		return "min";
	}
	
}
