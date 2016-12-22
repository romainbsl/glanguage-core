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
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.DurationFormula;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
		.TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal.TerminalFormulaUnableToParseValueInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_DURATION)
public class FormulaTerminalDuration extends AbstractTerminalFormula {
	
	public FormulaTerminalDuration() {
		super();
	}
	
	public FormulaTerminalDuration(FormulaDescription description, String constantValue) {
		super(description, constantValue);
	}
	
	public FormulaTerminalDuration(FormulaDescription description, LocalDate date) {
		super(description, date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageEvaluationException {
		if (getConstantValue() != null) {
			try {
				String text = new String(getConstantValue());
				text = text.substring(1, text.length() - 1);
				if (text.contains("T")) {
					if (text.contains("Y") || (text.contains("M") && text.indexOf("M") < text.indexOf("T"))) {
						String[] split = text.split("T");
						assert(split.length == 2);
						Period period = Period.parse(split[0]);
						int days = (period.getYears() * DurationFormula.YEAR_AVG_DAYS_COUNT)
								+ (period.getMonths() * DurationFormula.MONTH_AVG_DAYS_COUNT) + period.getDays();
						String tmp = "P" + days + "DT" + split[1];
						return Duration.parse(tmp);
					} else {
						return Duration.parse(text);
					}
				} else {
					Period period = Period.parse(text);
					int days = (period.getYears() * DurationFormula.YEAR_AVG_DAYS_COUNT)
							+ (period.getMonths() * DurationFormula.MONTH_AVG_DAYS_COUNT) + period.getDays();
					return Duration.ofDays(days);
				}
			} catch (DateTimeParseException dtpe) {
				RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
				error.setInnererror(new TerminalFormulaUnableToParseValueInnerError(getConstantValue(),
						FormulaReturnType.BOOLEAN,
						"P[nY][nM][nD][T[nH][nM][n][.nS]]",
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
