package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_DATE)
public class FormulaDate extends AbstractNonTerminalFormula {
	
	public FormulaDate() {
		super();
	}
	
	public FormulaDate(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description);
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		this.parameters = new ArrayList<>();
		if (parameters.size() == 1) {
			if (!parameters.get(0).getReturnType().equals(FormulaReturnType.STRING)) {
				throw new IllegalArgumentException("if one parameter, it must be of type STRING");
			}
			this.parameters.add(parameters.get(0));
		} else if (parameters.size() == 3) {
			parameters.stream().forEachOrdered(e -> {
				if (e.getReturnType().equals(FormulaReturnType.INTEGER)) {
					this.parameters.add(e);
				} else {
					throw new IllegalArgumentException(
							(parameters.indexOf(e) + 1) + "-th parameter must be of type INTEGER : " + e.asText());
				}
			});
		} else {
			throw new IllegalArgumentException("there should be 1 or 3 parameters but there are " + parameters.size());
		}
	}
	
	@Override
	public LocalDate getDateValue() {
		LocalDate date = null;
		if (getParameters().size() == 1) {
			try {
				date = LocalDate.parse(getParameters().get(0).getStringValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} catch (DateTimeParseException dtpe) {
				throw new IllegalArgumentException(
						"Parameter must reprensent a date formatted as \"dd/MM/yyyy\" : " + getParameters().get(0).asText());
			}
		} else if (getParameters().size() == 3) {
			try {
				date = LocalDate.of(getParameters().get(2).getIntegerValue(), getParameters().get(1).getIntegerValue(),
						getParameters().get(0).getIntegerValue());
			} catch (DateTimeParseException dtpe) {
				throw new IllegalArgumentException("Parameters must reprensent a valid date : " + getParameters().get(0).asText() + "/"
						+ getParameters().get(1).asText() + "/" + getParameters().get(2).asText());
			}
		} else {
			throw new IllegalArgumentException("there should be 1 or 3 parameters but there are " + parameters.size());
		}
		return date;
	}
	
	@Override
	public String asText() {
		StringBuilder sb = new StringBuilder();
		sb.append("date(");
		sb.append(getParameters().get(0).asText());
		Iterator<AbstractFormula> itParameters = getParameters().listIterator(1);
		while (itParameters.hasNext()) {
			sb.append("; ");
			sb.append(itParameters.next().asText());
		}
		sb.append(")");
		return sb.toString();
	}
	
}
