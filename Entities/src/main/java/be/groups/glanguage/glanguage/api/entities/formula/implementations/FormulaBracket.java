package be.groups.glanguage.glanguage.api.entities.formula.implementations;


import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

/**
 *
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_BRACKETS)
public class FormulaBracket extends AbstractFormula {

    public FormulaBracket() {
		super();
	}

	/**
     * Initialisation de la formule avec la formule attachée {@code formule}.
     *
     * @param Formule formule
     */
    public FormulaBracket(AbstractFormula child) {
        if (child == null) {
        	throw new IllegalArgumentException("Child must be non-null");
        }
    	this.parameters = new ArrayList<>();
    	this.parameters.add(child);
    }

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public FormulaReturnType getReturnType() {
		return getParameters().get(0).getReturnType();
	}

	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getIntegerValue();
	}

	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue();
	}

	@Override
	public String getStringValue() {
		return getParameters().get(0).getStringValue();
	}

	@Override
	public Boolean getBooleanValue() {
		return getParameters().get(0).getBooleanValue();
	}

	@Override
	public LocalDate getDateValue() {
		return getParameters().get(0).getDateValue();
	}

	@Override
	public String asText() {
		return "(" + getParameters().get(0).asText() + ")";
	}
}
