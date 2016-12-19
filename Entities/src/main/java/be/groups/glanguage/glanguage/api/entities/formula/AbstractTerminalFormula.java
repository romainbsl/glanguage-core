package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.LinkedList;

@Entity
public abstract class AbstractTerminalFormula extends AbstractFormula {
	
	protected AbstractTerminalFormula() {
		super();
	}
	
	public AbstractTerminalFormula(FormulaDescription description) {
		super(description);
	}
	
	public AbstractTerminalFormula(FormulaDescription description, String constantValue) {
		this(description);
		
		if (constantValue == null) {
			throw new IllegalArgumentException("Constant value must be non-null");
		}
		this.setConstantValue(constantValue);
	}
	
	@Transient
	@Override
	public LinkedList<AbstractFormula> getParameters() {
		return null;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public String getStringValue(Evaluator evaluator) {
		return getConstantValue();
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getDurationValue() method on " + this.getClass().getName() + " object");
	}
	
	@JsonIgnore
	@Transient
	@Override
	public boolean isTerminal() {
		return true;
	}
	
	@Override
	public String asText() {
		return getConstantValue();
	}
	
}
