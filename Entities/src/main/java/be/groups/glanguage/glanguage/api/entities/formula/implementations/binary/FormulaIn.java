package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

public class FormulaIn extends AbstractNonTerminalFormula {
	
	private AbstractFormula element;
	
	private List<AbstractFormula> inList;
	
	public FormulaIn(AbstractFormula element, List<AbstractFormula> inList) {
		if (element == null) {
			throw new IllegalArgumentException("element must be non-null");
		}
		if (inList == null) {
			throw new IllegalArgumentException("inList must be non-null");
		}
		this.element = element;
		this.inList = inList;
	}
	
	@Override
	public Boolean getBooleanValue() {
		Iterator<AbstractFormula> itInList = inList.iterator();
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
	public Integer getIntegerValue() {
		throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}
	
	/**
	 * Type of the elements<br>
	 * All element's types are supposed to be in accordance<br>
	 * The only remaining case is mixed integer and numeric types (in accordance to each other), numeric type prevails
	 * 
	 * @return
	 */
	private FormulaReturnType getElementsType() {
    	FormulaReturnType returnType = element.getReturnType();
        Iterator<AbstractFormula> itInList = inList.iterator();
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
		StringBuffer sb = new StringBuffer();
		sb.append(element.asText());
		sb.append("in (");
		Iterator<AbstractFormula> itInList = inList.iterator();
		while (itInList.hasNext()) {
			sb.append(itInList.next().asText());
		}
		sb.append(")");
		return sb.toString();
	}
	
}
