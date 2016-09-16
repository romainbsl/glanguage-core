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
 * Formula representing a logical or operation<br>
 * This Formula has exactly two (2) parameters<br>
 * The type of the parameters must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_OR)
public class FormulaOr extends BinaryFormula {

	protected FormulaOr() {
		super();
	}

	public FormulaOr(AbstractFormula child1, AbstractFormula child2) {
		super( child1, child2);
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		return getParameters().get(0).getBooleanValue() || getParameters().get(1).getBooleanValue();
	}

	@Override
	public String operationAsText() {
		return "or";
	}

}
