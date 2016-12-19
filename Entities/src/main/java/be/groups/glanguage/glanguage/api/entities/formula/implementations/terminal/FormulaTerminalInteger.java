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

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_INTEGER)
public class FormulaTerminalInteger extends AbstractTerminalFormula {

	protected FormulaTerminalInteger() {
		super();
	}

	public FormulaTerminalInteger(FormulaDescription description, String constantValue) {
		super(description, constantValue);
	}

	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		try {
			return Integer.valueOf(getConstantValue());
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Contant value must reprensent an integer value : " + getConstantValue());
		}
	}

	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue(Evaluator evaluator) {
		return getIntegerValue(evaluator).doubleValue();
	}

}
