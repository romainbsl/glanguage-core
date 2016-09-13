/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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

	private LocalDate date;
	
	public FormulaTerminalDate() {
		super();
	}

	public FormulaTerminalDate(String constantValue) {
		super(constantValue);
		try {
			this.date = LocalDate.parse(getConstantValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException dtpe) {
			throw new IllegalArgumentException("Contant value must reprensent a date formatted as \"dd/MM/yyyy\" : " + constantValue);
		}
	}

	public FormulaTerminalDate(LocalDate constantValue) {
		super(constantValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	@Override
	public LocalDate getDateValue() {
		return date;
	}
	
}
