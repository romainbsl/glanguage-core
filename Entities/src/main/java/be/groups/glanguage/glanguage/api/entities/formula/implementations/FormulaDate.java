package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

public class FormulaDate extends AbstractNonTerminalFormula {
	
	public FormulaDate() {
		super();
	}
	
	public FormulaDate(LinkedList<AbstractFormula> parameters) {
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
	public LocalDate getDateValueImpl() {
		LocalDate date = null;
		if (getParameters().size() == 1) {
			try {
				date = LocalDate.parse(getConstantValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
	}
	
	@Override
	public String asText() {
		StringBuilder sb = new StringBuilder();
		sb.append("date(");
		sb.append(getParameters().get(0).asText());
		Iterator<AbstractFormula> itParameters = getParameters().listIterator(1);
		while (itParameters.hasNext()) {
			sb.append(" ; ");
			sb.append(itParameters.next().asText());
		}
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.STRING));
	}
	
	@Override
	protected boolean isParametersCombinationAuthorized() {
		return (getParameters().size() == 1 && getParameters().get(0).getReturnType().equals(FormulaReturnType.STRING))
				|| (getParameters().size() == 3 && getParameters().get(0).getReturnType().equals(FormulaReturnType.INTEGER)
						&& getParameters().get(1).getReturnType().equals(FormulaReturnType.INTEGER)
						&& getParameters().get(2).getReturnType().equals(FormulaReturnType.INTEGER));
	}
	
}
