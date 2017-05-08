package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class DurationFormula extends AbstractNonTerminalFormula {
	public static final int YEAR_AVG_DAYS_COUNT = 365;
	public static final int MONTH_AVG_DAYS_COUNT = 31;
	
	public DurationFormula() {
		super();
	}
	
	public DurationFormula(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}

		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}
	
	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + ")";
	}
	
	public abstract String operationAsText();
	
}
