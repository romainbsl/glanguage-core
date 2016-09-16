/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Formula representing a logical not operation<br>
 * This Formula has exactly one (1) parameter<br>
 * The type of the parameter must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_NOT)
public class FormulaNot extends UnaryFormula {

	protected FormulaNot() {
		super();
	}

	public FormulaNot(AbstractFormula child) {
		super( child);
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		return !getParameters().get(0).getBooleanValue();
	}

	@Override
	public String operationAsText() {
		return "not";
	}

}
