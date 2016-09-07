package be.groups.glanguage.glanguage.api.entities.formula.implementations;


import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

/**
 *
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_PARENTHESIS)
public class FormulaBracket extends AbstractFormula {

    /**
     * {@link AbstractFormula} under brackets
     */
    private AbstractFormula child;

    /**
     * Initialisation de la formule avec la formule attachée {@code formule}.
     *
     * @param Formule formule
     */
    public FormulaBracket(AbstractFormula child) {
        if (child == null) {
        	throw new IllegalArgumentException("Child must be non-null");
        }
    	this.child = child;
    }

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public FormulaReturnType getReturnType() {
		return child.getReturnType();
	}

	@Override
	public Integer getIntegerValue() {
		return child.getIntegerValue();
	}

	@Override
	public Double getNumericValue() {
		return child.getNumericValue();
	}

	@Override
	public String getStringValue() {
		return child.getStringValue();
	}

	@Override
	public Boolean getBooleanValue() {
		return child.getBooleanValue();
	}

	@Override
	public LocalDate getDateValue() {
		return child.getDateValue();
	}

	@Override
	public String asText() {
		return "(" + child.asText() + ")";
	}
}
