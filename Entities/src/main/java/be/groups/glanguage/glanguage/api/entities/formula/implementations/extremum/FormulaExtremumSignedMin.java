package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_SMIN)
public class FormulaExtremumSignedMin extends ExtremumFormula {
	
	public FormulaExtremumSignedMin() {
		super();
	}
	
	public FormulaExtremumSignedMin(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
	}
	
	@Override
	public Double getNumericValue() {
		Iterator<AbstractFormula> itParameters = getParameters().iterator();
		double temp;
		double sign = 0.0;
		double result = Double.MAX_VALUE;
		do {
			temp = itParameters.next().getNumericValue();
			if (sign == 0.0) {
				if (Double.compare(temp, 0.0) >= 0) {
					sign = 1.0;
				} else {
					sign = -1.0;
				}
			}
			temp = Math.abs(temp);
			if (result > temp) {
				result = temp;
			}
		} while (itParameters.hasNext());
		return result * sign;
	}
	
	@Override
	public String operationAsText() {
		return "smin";
	}
	
}
