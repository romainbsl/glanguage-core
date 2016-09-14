package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_MIN)
public class FormulaExtremumMin extends ExtremumFormula {
	
	public FormulaExtremumMin() {
		super();
	}

	public FormulaExtremumMin(LinkedList<AbstractFormula> parameters) {
		super(parameters);
	}

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

	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isParametersCombinationAuthorized() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
