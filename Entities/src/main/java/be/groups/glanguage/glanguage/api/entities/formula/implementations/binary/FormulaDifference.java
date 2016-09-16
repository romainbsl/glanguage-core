/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Formula representing a logical not equal operation<br>
 * This Formula has exactly two (2) parameters
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_DIFFERENCE)
public class FormulaDifference extends BinaryFormula {

	protected FormulaDifference() {
		super();
	}

	public FormulaDifference(AbstractFormula child1, AbstractFormula child2) {
		super( child1, child2);
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		return !getParameters().get(0).getValue().equals(getParameters().get(1).getValue());
	}

	@Override
	public String operationAsText() {
		return "<>";
	}

}
