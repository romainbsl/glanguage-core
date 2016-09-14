/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.TERMINAL_DATE)
public class FormulaTerminalDate extends AbstractTerminalFormula {

	protected FormulaTerminalDate() {
		super();
	}

	public FormulaTerminalDate(String constantValue) {
		super(FormulaDescription.TERMINAL_DATE, constantValue);
	}

	public FormulaTerminalDate(LocalDate constantValue) {
		this(constantValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		try {
			return LocalDate.parse(getConstantValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException dtpe) {
			throw new IllegalArgumentException("Contant value must reprensent a date formatted as \"dd/mm/yyyy\" : " + getConstantValue());
		}
	}

}
