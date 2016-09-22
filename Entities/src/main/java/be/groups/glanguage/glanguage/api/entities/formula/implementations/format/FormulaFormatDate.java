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
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_DATE)
public class FormulaFormatDate extends FormatFormula {
	
	public FormulaFormatDate() {
		super();
	}
	
	public FormulaFormatDate(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description);
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
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
