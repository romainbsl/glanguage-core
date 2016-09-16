package be.groups.glanguage.glanguage.api.entities.formula.implementations.instruction;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.I_IF)
public class FormulaInstructionIf extends AbstractNonTerminalFormula {

	public FormulaInstructionIf() {
		super();
	}

	public FormulaInstructionIf(AbstractFormula condition, AbstractFormula ifStatement, AbstractFormula elseStatement) {
		super();
		if (condition == null) {
			throw new IllegalArgumentException("condition must be non-null");
		}
		if (!condition.getReturnType().equals(FormulaReturnType.BOOLEAN)) {
			throw new IllegalArgumentException("condition must be of type BOOLEAN");
		}
		if (ifStatement == null) {
			throw new IllegalArgumentException("ifStatement must be non-null");
		}
		this.parameters = new ArrayList<>();
		parameters.addAll(parameters);
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		if (getParameters().get(0).getBooleanValue()) {
			return getParameters().get(1).getIntegerValue();
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getIntegerValue();
			} else {
				return 0;
			}
		}
	}

	@Transient
	@Override
	public Double getNumericValue() {
		if (getParameters().get(0).getBooleanValue()) {
			return getParameters().get(1).getNumericValue();
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getNumericValue();
			} else {
				return 0.0;
			}
		}
	}

	@Transient
	@Override
	public String getStringValue() {
		if (getParameters().get(0).getBooleanValue()) {
			return getParameters().get(1).getStringValue();
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getStringValue();
			} else {
				return "";
			}
		}
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		if (getParameters().get(0).getBooleanValue()) {
			return getParameters().get(1).getBooleanValue();
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getBooleanValue();
			} else {
				return false;
			}
		}
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		if (getParameters().get(0).getBooleanValue()) {
			return getParameters().get(1).getDateValue();
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getDateValue();
			} else {
				throw new RuntimeException("Else statement needed, no default value of type DATE");
			}
		}
	}

	@Transient
	@Override
	public Duration getDurationValue() {
		if (getParameters().get(0).getBooleanValue()) {
			return getParameters().get(1).getDurationValue();
		} else {
			if (getParameters().size() > 2) {
				return getParameters().get(2).getDurationValue();
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
			if (getParameters().get(2) instanceof FormulaInstructionIf) {
				elseIf = true;
				sb.append(((FormulaInstructionIf) getParameters().get(2)).asText(true));
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
