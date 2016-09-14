package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_FORMAT_DATE)
public class FormulaFormatDate extends FormatFormula {
	
	public FormulaFormatDate() {
		super();
	}
	
	public FormulaFormatDate(LinkedList<AbstractFormula> parameters) {
		if (parameters.get(0) == null) {
			throw new IllegalArgumentException("element must be non-null");
		}
		if (!parameters.get(0).getReturnType().equals(FormulaReturnType.DATE)) {
			throw new IllegalArgumentException("date must of type DATE");
		}
		if (parameters.get(1) == null) {
			throw new IllegalArgumentException("format must be non-null");
		}
		if (!parameters.get(1).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("format must of type STRING");
		}
		this.parameters = new ArrayList<>();
		parameters.stream().forEachOrdered(e -> this.parameters.add(e));
	}
	
	@Override
	public String getStringValueImpl() {
		return getParameters().get(0).getDateValue().format(DateTimeFormatter.ofPattern(getParameters().get(1).getStringValue()));
	}
	
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.DATE, FormulaReturnType.STRING));
	}
	
	@Override
	protected boolean isParametersCombinationAuthorized() {
		return getParameters().get(0).getReturnType().equals(FormulaReturnType.DATE)
				&& getParameters().get(1).getReturnType().equals(FormulaReturnType.STRING);
	}
	
	@Override
	public String operationAsText() {
		return "formatDate";
	}
	
}
