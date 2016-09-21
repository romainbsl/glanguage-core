/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Formula representing a logical and operation<br>
 * This Formula has exactly two (2) parameters<br>
 * The type of the parameters must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_AND)
public class FormulaAnd extends BinaryFormula {

	protected FormulaAnd() {
		super();
	}

	public FormulaAnd(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) {
		super(description, child1, child2);
	}

	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue() {
		return parameters.get(0).getBooleanValue() && parameters.get(1).getBooleanValue();
	}

	@Override
	public String operationAsText() {
		return "and";
	}

}
