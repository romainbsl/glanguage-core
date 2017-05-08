package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Entity
public abstract class AbstractNonTerminalFormula extends AbstractFormula {
	
	protected AbstractNonTerminalFormula() {
		super();
	}
	
	protected AbstractNonTerminalFormula(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description);
		// FIXME pass an evaluator with the whole plan in it, initialized by parser
		isValid(parameters, null);
	}

	@JsonIgnore
	@Transient
	public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
		return this.getDescription().isValid(parameters, evaluator);
	}

	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		throw new UnsupportedOperationException("Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue(Evaluator evaluator) {
		throw new UnsupportedOperationException("Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public String getStringValue(Evaluator evaluator) {
		throw new UnsupportedOperationException("Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue(Evaluator evaluator) {
		throw new UnsupportedOperationException("Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue(Evaluator evaluator) {
		throw new UnsupportedOperationException("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue(Evaluator evaluator) {
		throw new UnsupportedOperationException("Cannot invoke getDurationValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public boolean isTerminal() {
		return false;
	}

}
