package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke.evaluation.method.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;

@Entity
public abstract class AbstractNonTerminalFormula extends AbstractFormula {
	
	protected AbstractNonTerminalFormula() {
		super();
	}
	
	protected AbstractNonTerminalFormula(FormulaDescription description) {
		super(description);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationIntegerMethodInnerError(this, evaluator));
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationNumericMethodInnerError(this, evaluator));
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationStringMethodInnerError(this, evaluator));
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationBooleanMethodInnerError(this, evaluator));
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationDateMethodInnerError(this, evaluator));
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationDurationMethodInnerError(this, evaluator));
	}
	
	@JsonIgnore
	@Transient
	@Override
	public boolean isTerminal() {
		return false;
	}
	
}
