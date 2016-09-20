/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Formula representing a constant integer value
 * 
 * @author dupirefr
 */
@Entity
@DiscriminatorValue(FormulaType.Values.UNDEFINED)
public class FormulaUndefined extends AbstractTerminalFormula {
	
	public FormulaUndefined() {
		super();
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		return null;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
		return null;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue() {
		return null;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue() {
		return null;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue() {
		return null;
	}
	
}
