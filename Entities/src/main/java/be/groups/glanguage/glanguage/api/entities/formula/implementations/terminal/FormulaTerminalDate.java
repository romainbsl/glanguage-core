/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.errorframework.core.error.ErrorEnum;
import be.groups.errorframework.core.error.RootError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
		.TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal.TerminalFormulaUnableToParseValueInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_DATE)
public class FormulaTerminalDate extends AbstractTerminalFormula {

	protected FormulaTerminalDate() {
		super();
	}

	public FormulaTerminalDate(FormulaDescription description, String constantValue) {
		super(description, constantValue);
	}

	public FormulaTerminalDate(FormulaDescription description, LocalDate constantValue) {
		this(description, constantValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	@JsonIgnore
	@Transient
	@Override
	protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageEvaluationException {
		if (getConstantValue() != null) {
			try {
				return LocalDate.parse(getConstantValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} catch (DateTimeParseException dtpe) {
				RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
				error.setInnererror(new TerminalFormulaUnableToParseValueInnerError(getConstantValue(),
						FormulaReturnType.BOOLEAN,
						"[true;false;TRUE;FALSE]",
						dtpe));
				throw new GLanguageEvaluationException(error);
			}
		} else {
			RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
			error.setInnererror(new TerminalFormulaUnableToInitializeNullValueInnerError());
			throw new GLanguageEvaluationException(error);
		}
	}

}
