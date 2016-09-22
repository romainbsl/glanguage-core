package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_STRING_ITEM)
public class FormulaStringItem extends AbstractNonTerminalFormula {
	
	public FormulaStringItem() {
		super();
	}
	
	public FormulaStringItem(FormulaDescription description, List<AbstractFormula> parameters) {
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
		String str, separatorString, separatorRegex;
		int index;
		String[] items;
		
		str = getParameters().get(0).getStringValue();
		separatorString = getParameters().get(1).getStringValue();
		index = getParameters().get(2).getIntegerValue();
		if (separatorString.isEmpty() || index <= 0) {
			return "";
		} else {
			if (separatorString.equals("|")) { // '|' is a special character in regex, it must be
											   // escaped
				separatorRegex = "\\" + separatorString.charAt(0);
			} else {
				separatorRegex = "" + separatorString.charAt(0);
			}
			items = str.split(separatorRegex);
			
			if (items.length <= 0 || !(0 <= (index - 1) && (index - 1) < items.length)) {
				return "";
			} else {
				return items[index - 1];
			}
		}
	}
	
	@Override
	public String asText() {
		return "stringItem(" + getParameters().get(0).asText() + "; " + getParameters().get(1).asText() + "; "
				+ getParameters().get(2).asText() + ")";
	}
	
}
