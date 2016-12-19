package be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@DiscriminatorValue(FormulaType.Values.I_IF)
public class FormulaIfInstruction extends AbstractNonTerminalFormula {
	
	public FormulaIfInstruction() {
		super();
	}
	
	public FormulaIfInstruction(FormulaDescription description, AbstractFormula condition, AbstractFormula ifStatement,
			AbstractFormula elseStatement) {
		super(description);
		
		if (condition == null) {
			throw new IllegalArgumentException("condition must be non-null");
		}
		if (!condition.getReturnType(null).equals(FormulaReturnType.BOOLEAN)) {
			throw new IllegalArgumentException("condition must be of type BOOLEAN");
		}
		if (ifStatement == null) {
			throw new IllegalArgumentException("ifStatement must be non-null");
		}
		this.parameters = new ArrayList<>();
		parameters.add(condition);
		parameters.add(ifStatement);
		parameters.add(elseStatement);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		if (getParameters().get(0).getBooleanValue(evaluator)) {
			return getParameters().get(1).getIntegerValue(evaluator);
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getIntegerValue(evaluator);
			} else {
				return 0;
			}
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue(Evaluator evaluator) {
		if (getParameters().get(0).getBooleanValue(evaluator)) {
			return getParameters().get(1).getNumericValue(evaluator);
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getNumericValue(evaluator);
			} else {
				return 0.0;
			}
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public String getStringValue(Evaluator evaluator) {
		if (getParameters().get(0).getBooleanValue(evaluator)) {
			return getParameters().get(1).getStringValue(evaluator);
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getStringValue(evaluator);
			} else {
				return "";
			}
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue(Evaluator evaluator) {
		if (getParameters().get(0).getBooleanValue(evaluator)) {
			return getParameters().get(1).getBooleanValue(evaluator);
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getBooleanValue(evaluator);
			} else {
				return false;
			}
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue(Evaluator evaluator) {
		if (getParameters().get(0).getBooleanValue(evaluator)) {
			return getParameters().get(1).getDateValue(evaluator);
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getDateValue(evaluator);
			} else {
				throw new RuntimeException("Else statement needed, no default value of type DATE");
			}
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue(Evaluator evaluator) {
		if (getParameters().get(0).getBooleanValue(evaluator)) {
			return getParameters().get(1).getDurationValue(evaluator);
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getDurationValue(evaluator);
			} else {
				return Duration.ZERO;
			}
		}
	}
	
	@Override
	public String asText() {
		return asText(false);
	}
	
	protected String asText(boolean fromIf) {
		boolean elseIf = false;
		StringBuilder sb = new StringBuilder();
		if (fromIf) {
			sb.append("elseif ");
		} else {
			sb.append("if ");
		}
		sb.append(getParameters().get(0).asText());
		sb.append(" then");
		sb.append("\n\t");
		sb.append(getParameters().get(1).asText());
		sb.append("\n");
		if (getParameters().size() > 2) {
			if (getParameters().get(2) instanceof FormulaIfInstruction) {
				elseIf = true;
				sb.append(((FormulaIfInstruction) getParameters().get(2)).asText(true));
			} else {
				sb.append("else");
				sb.append("\n\t");
				sb.append(getParameters().get(2).asText());
				sb.append("\n");
			}
		}
		// If second parameter is another if instruction, "end" has already been appended
		if (!elseIf) {
			sb.append("end");
		}
		return sb.toString();
	}
	
}
