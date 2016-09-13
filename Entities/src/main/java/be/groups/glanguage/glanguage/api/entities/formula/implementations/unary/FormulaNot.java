/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

/**
 * Formula representing a logical not operation<br>
 * This Formula has exactly one (1) parameter<br>
 * The type of the parameter must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_NOT)
public class FormulaNot extends UnaryFormula {

	protected FormulaNot() {
		super();
	}

	public FormulaNot(AbstractFormula child) {
		super(FormulaDescription.OP_NOT, child);
	}

	@Transient
	@Override
	public Boolean getBooleanValueImpl() {
		return !getParameters().get(0).getBooleanValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.BOOLEAN));
	}

	@Override
	public String operationAsText() {
		return "not";
	}

}
