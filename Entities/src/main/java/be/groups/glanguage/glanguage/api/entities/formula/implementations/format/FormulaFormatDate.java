package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_DATE)
public class FormulaFormatDate extends FormatFormula {
	
	public FormulaFormatDate() {
		super();
	}
	
	public FormulaFormatDate(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description);
		
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

	@JsonIgnore
	@Transient
	@Override
	public String getStringValue() {
		return getParameters().get(0).getDateValue().format(DateTimeFormatter.ofPattern(getParameters().get(1).getStringValue()));
	}
	
	@Override
	public String operationAsText() {
		return "formatDate";
	}
	
}
