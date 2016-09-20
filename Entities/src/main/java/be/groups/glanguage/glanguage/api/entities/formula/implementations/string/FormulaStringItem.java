package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

public class FormulaStringItem extends AbstractNonTerminalFormula {
	
	public FormulaStringItem() {
		super();
	}
	
	public FormulaStringItem(LinkedList<AbstractFormula> parameters) {
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 3) {
			throw new IllegalArgumentException("there should be 3 parameters but there are " + parameters.size());
		}
		if (parameters.get(0) == null) {
			throw new IllegalArgumentException("first parameter must be non-null");
		}
		if (!parameters.get(0).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("first parameter must of type STRING");
		}
		if (!parameters.get(1).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("second parameter must of type STRING");
		}
		if (!parameters.get(2).getReturnType().equals(FormulaReturnType.INTEGER)) {
			throw new IllegalArgumentException("third parameter must of type INTEGER");
		}
		this.parameters = new ArrayList<>();
		parameters.stream().forEachOrdered(e -> this.parameters.add(e));
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
			if (separatorString.equals("|")) { // '|' is a special character in regex, it must be escaped
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
		return "stringItem(" + getParameters().get(0).asText() + " ; " + getParameters().get(1).asText() + " ; "
				+ getParameters().get(2).asText() + ")";
	}
	
}
