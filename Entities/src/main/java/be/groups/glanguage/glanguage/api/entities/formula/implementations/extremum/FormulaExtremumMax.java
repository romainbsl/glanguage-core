package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import java.util.Iterator;
import java.util.LinkedList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_MAX)
public class FormulaExtremumMax extends ExtremumFormula {
	
	public FormulaExtremumMax() {
		super();
	}
	
	public FormulaExtremumMax(LinkedList<AbstractFormula> parameters) {
		super(parameters);
	}
	
	@Override
	public Double getNumericValueImpl() {
		Iterator<AbstractFormula> itParameters = getParameters().iterator();
		double temp;
		double result = Double.MIN_VALUE;
		do {
			temp = itParameters.next().getNumericValue();
			if (result < temp) {
				result = temp;
			}
		} while (itParameters.hasNext());
		return result;
	}
	
	@Override
	public String operationAsText() {
		return "max";
	}

}
