package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_SMAX)
public class FormulaExtremumSignedMax extends ExtremumFormula {
	
	public FormulaExtremumSignedMax() {
		super();
	}
	
	public FormulaExtremumSignedMax(List<AbstractFormula> parameters) {
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
		double sign = 0.0;
		double result = Double.MIN_VALUE;
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
			if (result < temp) {
				result = temp;
			}
		} while (itParameters.hasNext());
		return result * sign;
	}
	
	@Override
	public String operationAsText() {
		return "smax";
	}
	
}
