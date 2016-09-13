package be.groups.glanguage.glanguage.api.entities.formula;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public abstract class AbstractNonTerminalFormula extends AbstractFormula {

	/**
	 * Return type
	 */
	private FormulaReturnType returnType;

	protected AbstractNonTerminalFormula() {
		super();
	}

	public AbstractNonTerminalFormula(FormulaDescription description) {
		super(description);
	}

	@Transient
	@Override
	public FormulaReturnType getReturnType() {
		if (returnType == null) {
			returnType = computeReturnType();
		}
		return returnType;
	}

	protected abstract FormulaReturnType computeReturnType();

	/**
	 * Tells if this parameters are authorized (parameters types must be handled
	 * by this)
	 * 
	 * @return true if this parameters are authorized, otherwise false
	 */
	protected boolean areParametersAuthorized() {
		return parameters.stream().filter(p -> !getAuthorizedParametersTypes().contains(p.getReturnType()))
				.count() == 0;
	}

	/**
	 * Gives authorized parameters types for this
	 * 
	 * @return the authorized parameters types
	 */
	@Transient
	protected abstract Set<FormulaReturnType> getAuthorizedParametersTypes();

	/**
	 * Tells if this parameters combination is authorized (some parameters types
	 * can't be combined together by this)
	 * 
	 * @return if this parameters combination is authorized, otherwise false
	 */
	@Transient
	protected abstract boolean isParametersCombinationAuthorized();

	@Transient
	@Override
	public final Integer getIntegerValue() {
		return getValueImpl(this::getIntegerValueImpl);
	}

	@Transient
	@Override
	public final Double getNumericValue() {
		return getValueImpl(this::getNumericValueImpl);
	}

	@Transient
	@Override
	public final String getStringValue() {
		return getValueImpl(this::getStringValueImpl);
	}

	@Transient
	@Override
	public final Boolean getBooleanValue() {
		return getValueImpl(this::getBooleanValueImpl);
	}

	@Transient
	@Override
	public final LocalDate getDateValue() {
		return getValueImpl(this::getDateValueImpl);
	}

	private <T> T getValueImpl(Supplier<T> method) {
		if (areParametersAuthorized()) {
			if (isParametersCombinationAuthorized()) {
				return method.get();
			} else {
				throw new IllegalStateException("Cannot use this formula with this parameters combination");
			}
		} else {
			throw new IllegalStateException("Cannot use this formula for parameters for which type is not in "
					+ getAuthorizedParametersTypes());
		}
	}

	@Transient
	protected Integer getIntegerValueImpl() {
		throw new UnsupportedOperationException(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	protected Double getNumericValueImpl() {
		throw new UnsupportedOperationException(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	protected String getStringValueImpl() {
		throw new UnsupportedOperationException(
				"Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	protected Boolean getBooleanValueImpl() {
		throw new UnsupportedOperationException(
				"Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	protected LocalDate getDateValueImpl() {
		throw new UnsupportedOperationException(
				"Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public boolean isTerminal() {
		return false;
	}

}
