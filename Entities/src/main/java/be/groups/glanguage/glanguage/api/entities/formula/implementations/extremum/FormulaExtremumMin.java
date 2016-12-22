package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Iterator;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_MIN)
public class FormulaExtremumMin extends ExtremumFormula {
	
	public FormulaExtremumMin() {
		super();
	}
	
	public FormulaExtremumMin(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
	}

	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageEvaluationException {
		Iterator<AbstractFormula> itParameters = getParameters().iterator();
		double temp;
		double result = Double.MAX_VALUE;
		do {
			temp = itParameters.next().getNumericValue(evaluator);
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
