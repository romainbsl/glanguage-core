package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_MAX)
public class FormulaExtremumMax extends ExtremumFormula {
	
	public FormulaExtremumMax() {
		super();
	}
	
	public FormulaExtremumMax(List<AbstractFormula> parameters) {
		super(parameters);
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getNumericValue().intValue();
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		Iterator<AbstractFormula> itParameters = getParameters().iterator();
		Double temp;
		Double result = Double.MIN_VALUE;
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
