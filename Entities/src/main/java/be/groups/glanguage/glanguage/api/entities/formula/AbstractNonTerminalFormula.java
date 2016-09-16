package be.groups.glanguage.glanguage.api.entities.formula;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public abstract class AbstractNonTerminalFormula extends AbstractFormula {
	
	protected AbstractNonTerminalFormula() {
		super();
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		throw new UnsupportedOperationException("Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		throw new UnsupportedOperationException("Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public String getStringValue() {
		throw new UnsupportedOperationException("Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public Boolean getBooleanValue() {
		throw new UnsupportedOperationException("Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public LocalDate getDateValue() {
		throw new UnsupportedOperationException("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public Duration getDurationValue() {
		throw new UnsupportedOperationException("Cannot invoke getDurationValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public boolean isTerminal() {
		return false;
	}
	
}
