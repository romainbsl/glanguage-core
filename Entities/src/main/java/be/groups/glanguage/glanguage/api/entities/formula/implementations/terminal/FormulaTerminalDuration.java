/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_DURATION)
public class FormulaTerminalDuration extends AbstractTerminalFormula {
	
	public FormulaTerminalDuration() {
		super();
	}
	
	public FormulaTerminalDuration(String constantValue) {
		super(constantValue);
	}
	
	public FormulaTerminalDuration(LocalDate date) {
		super(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}
	
	public Duration getDurationValue() {
		try {
			if (getConstantValue().contains("T")) {
				if (getConstantValue().contains("Y") ||
						(getConstantValue().contains("M") && getConstantValue().indexOf("M") < getConstantValue().indexOf("T"))) {
					String[] split = getConstantValue().split("T");
					assert(split.length == 2);
					return Duration.parse(Period.parse(split[0]).getDays() + "DT" + split[1]);
				} else {
					return Duration.parse(getConstantValue());
				}
			} else {
				return Duration.ofDays(Period.parse(getConstantValue()).getDays());
			}
		} catch (DateTimeParseException dtpe) {
			throw new IllegalArgumentException(
					"Contant value must reprensent a duration formatted as \"P[nY][nM][nD][T[nH][nM][n][.nS]]\" : "
							+ getConstantValue());
		}
	}

}
