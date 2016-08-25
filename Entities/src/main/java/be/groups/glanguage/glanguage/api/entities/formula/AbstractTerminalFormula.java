package be.groups.glanguage.glanguage.api.entities.formula;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public abstract class AbstractTerminalFormula extends AbstractFormula {
	
	public AbstractTerminalFormula() {
		super();
	}

	@Transient
	@Override
	public FormulaReturnType getReturnType() {
		return getDescription().getReturnType();
	}

	@Transient
	@Override
	public LinkedList<AbstractFormula> getParameters() {
		return null;
	}
	
	@Transient
	@Override
	public boolean isTerminal() {
		return true;
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		return Integer.valueOf(getConstantValue());
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		return Double.valueOf(getConstantValue());
	}
	
	@Transient
	@Override
	public String getStringValue() {
		return getConstantValue();
	}
	
	@Transient
	@Override
	public Boolean getBooleanValue() {
		return Boolean.valueOf(getConstantValue());
	}
	
	@Transient
	@Override
	public LocalDate getDateValue() {
		return LocalDate.parse(getConstantValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	@Override
	public String asText() {
		return getConstantValue();
	}

}
