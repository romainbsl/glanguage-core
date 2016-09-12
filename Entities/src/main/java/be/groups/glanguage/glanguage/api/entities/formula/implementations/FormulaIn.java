package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(FormulaDescription.Values.F_IN)
public class FormulaIn extends AbstractNonTerminalFormula {

	protected FormulaIn() {
		super();
	}
	
	public FormulaIn(AbstractFormula element, List<AbstractFormula> inList) {
		super(FormulaDescription.F_IN);
		
		if (element == null) {
			throw new IllegalArgumentException("element must be non-null");
		}
		if (inList == null) {
			throw new IllegalArgumentException("inList must be non-null");
		}
		this.parameters = new ArrayList<>();
		this.parameters.add(element);
		inList.stream().forEach(e -> this.parameters.add(e));
	}

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
			throw new IllegalArgumentException(
					"Cannot compare undefined values in " + this.getClass().getName() + " object");
		default:
			throw new IllegalArgumentException(
					"Cannot compare unknown values in " + this.getClass().getName() + " object");
		}
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		throw new IllegalAccessError(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public Double getNumericValue() {
		throw new IllegalAccessError(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public String getStringValue() {
		throw new IllegalAccessError(
				"Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	/**
	 * Type of the elements<br>
	 * All element's types are supposed to be in accordance<br>
	 * The only remaining case is mixed integer and numeric types (in accordance
	 * to each other), numeric type prevails
	 * 
	 * @return
	 */
	private FormulaReturnType getElementsType() {
		AbstractFormula element = getParameters().get(0);
		Iterator<AbstractFormula> itInList = getParameters().listIterator(1);
		FormulaReturnType returnType = element.getReturnType();
		if (returnType.equals(FormulaReturnType.INTEGER)) {
			while (returnType.equals(FormulaReturnType.INTEGER) && itInList.hasNext()) {
				returnType = itInList.next().getReturnType();
			}
		}
		return returnType;
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
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
