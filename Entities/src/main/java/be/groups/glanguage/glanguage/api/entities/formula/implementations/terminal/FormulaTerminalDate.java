/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_DATE)
public class FormulaTerminalDate extends AbstractTerminalFormula {

	protected FormulaTerminalDate() {
		super();
	}

	public FormulaTerminalDate(FormulaDescription description, String constantValue) {
		super(description, constantValue);
	}

	public FormulaTerminalDate(FormulaDescription description, LocalDate constantValue) {
		this(description, constantValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue(Evaluator evaluator) {
		try {
			return LocalDate.parse(getConstantValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException dtpe) {
			throw new IllegalArgumentException("Contant value must reprensent a date formatted as \"dd/mm/yyyy\" : " + getConstantValue());
		}
	}

}
