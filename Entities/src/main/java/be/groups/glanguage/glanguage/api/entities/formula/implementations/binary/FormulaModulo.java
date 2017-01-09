package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_MODULO)
public class FormulaModulo extends BinaryFormula {

	protected FormulaModulo() {
		super();
	}

	public FormulaModulo(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) {
		super(description, child1, child2);
	}

	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
		return getParameters().get(0).getIntegerValue(evaluator) % getParameters().get(1).getIntegerValue(evaluator);
	}

	@Override
	public String operationAsText() {
		return "\\";
	}

}
