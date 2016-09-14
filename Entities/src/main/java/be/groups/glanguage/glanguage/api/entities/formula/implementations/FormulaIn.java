package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

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
		this.parameters.addAll(inList);
	}

	@Override
	public Boolean getBooleanValueImpl() {
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


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC,
				FormulaReturnType.STRING, FormulaReturnType.BOOLEAN, FormulaReturnType.DATE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isParametersCombinationAuthorized() {
		Set<FormulaReturnType> returnTypes = parameters.stream().map(p -> p.getReturnType()).distinct()
				.collect(Collectors.toSet());

		return returnTypes.size() == 1 || returnTypes.size() == 2
				&& returnTypes.containsAll(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC));
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
		Set<FormulaReturnType> returnTypes = parameters.stream().map(p -> p.getReturnType()).distinct()
				.collect(Collectors.toSet());
		if (returnTypes.size() == 1) {
			return returnTypes.iterator().next();
		} else {
			return FormulaReturnType.NUMERIC;
		}
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
