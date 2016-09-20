package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_IN)
public class FormulaIn extends AbstractNonTerminalFormula {
	
	protected FormulaIn() {
		super();
	}
	
	public FormulaIn(FormulaDescription description, AbstractFormula element, List<AbstractFormula> inList) {
		super(description);
		
		if (element == null) {
			throw new IllegalArgumentException("element must be non-null");
		}
		if (inList == null) {
			throw new IllegalArgumentException("inList must be non-null");
		}
		this.parameters = new ArrayList<>();
		this.parameters.add(element);
		this.parameters.addAll(inList);
	}

	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue() {
		AbstractFormula element = getParameters().get(0);
		Iterator<AbstractFormula> itInList = getParameters().listIterator(1);
		boolean result = false;
		switch (getElementsType()) {
			case INTEGER:
				int i = element.getIntegerValue();
				while (!result && itInList.hasNext()) {
					result = i == itInList.next().getIntegerValue();
				}
				return result;
			case NUMERIC:
				double d = element.getNumericValue();
				while (!result && itInList.hasNext()) {
					result = d == itInList.next().getNumericValue();
				}
				return result;
			case DATE:
				LocalDate date = element.getDateValue();
				if (date != null) {
					while (!result && itInList.hasNext()) {
						result = date.equals(itInList.next().getDateValue());
					}
				}
				return result;
			case BOOLEAN:
				boolean b = element.getBooleanValue();
				while (!result && itInList.hasNext()) {
					result = (b == itInList.next().getBooleanValue());
				}
				return result;
			case STRING:
				String s = element.getStringValue();
				if (s != null) {
					while (!result && itInList.hasNext()) {
						result = s.equals(itInList.next().getStringValue());
					}
				}
				return result;
			case UNDEFINED:
				throw new IllegalArgumentException("Cannot compare undefined values in " + this.getClass().getName() + " object");
			default:
				throw new IllegalArgumentException("Cannot compare unknown values in " + this.getClass().getName() + " object");
		}
	}
	
	@Transient
	@Override
	public boolean isValid() {
		FormulaReturnType elementReturnType = parameters.get(0).getReturnType();
		List<FormulaReturnType> listReturnTypes =
				parameters.subList(1, parameters.size()).stream().map(p -> p.getReturnType()).distinct().collect(Collectors.toList());
				
		if (listReturnTypes.size() == 1) {
			return elementReturnType.equals(listReturnTypes.get(0));
		} else {
			List<FormulaReturnType> authorizedParametersTypes = Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC);
			return listReturnTypes.stream().allMatch(t -> authorizedParametersTypes.contains(t))
					&& authorizedParametersTypes.contains(elementReturnType);
		}
	}
	
	@Transient
	@Override
	public FormulaReturnType getReturnType() {
		return FormulaReturnType.BOOLEAN;
	}
	
	/**
	 * Type of the elements<br>
	 * All element's types are supposed to be in accordance<br>
	 * The only remaining case is mixed integer and numeric types (in accordance to each other),
	 * numeric type prevails
	 * 
	 * @return
	 */
	private FormulaReturnType getElementsType() {
		Set<FormulaReturnType> returnTypes = parameters.stream().map(p -> p.getReturnType()).distinct().collect(Collectors.toSet());
		if (returnTypes.size() == 1) {
			return returnTypes.iterator().next();
		} else {
			return FormulaReturnType.NUMERIC;
		}
	}
	
	@Override
	public String asText() {
		AbstractFormula element = getParameters().get(0);
		Iterator<AbstractFormula> itInList = getParameters().listIterator(1);
		StringBuffer sb = new StringBuffer();
		sb.append(element.asText());
		sb.append("in (");
		while (itInList.hasNext()) {
			sb.append(itInList.next().asText());
		}
		sb.append(")");
		return sb.toString();
	}
	
}
