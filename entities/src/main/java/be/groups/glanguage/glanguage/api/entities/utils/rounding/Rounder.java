package be.groups.glanguage.glanguage.api.entities.utils.rounding;

import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.utils.rounding.RoundingTypeInnerError;

/**
 * Class that handles the rounding operation of a value according to a RoundingType and a precision
 * 
 * @author michotte
 */
public class Rounder {
	
	public static final double TOLERANCE = Math.exp(-6);
	
	public static Double round(Integer value, RoundingType roundingType, Double precision) throws GLanguageException {
		return round(value.doubleValue(), roundingType, precision);
	}
	
	public static Double round(Double value, RoundingType roundingType, Double precision) throws GLanguageException {
		switch (roundingType) {
			case ARITHMETIC:
				return round(value, precision);
			case CEIL:
				return ceil(value, precision);
			case FLOOR:
				return floor(value, precision);
			case BANKERS:
				return bankersRound(value, precision.intValue());
			case TRUNC:
				return trunc(value, precision.intValue());
			case UNDEFINED:
				return value;
			default:
				throw new GLanguageException(new RoundingTypeInnerError(roundingType));
		}
	}
	
	/**
	 * Multiple of 'precision' closest to 'value'
	 * 
	 * @param value
	 * @param precision
	 * @return
	 */
	private static Double round(double value, double precision) {
		return (Math.round(value / precision) * precision);
	}
	
	/**
	 * Greatest value multiple of 'anOtherDoubleValue' no greater than 'value'
	 * 
	 * @param value
	 * @param precision
	 * @return
	 */
	private static Double floor(double value, double precision) {
		if (precision < 1.0) {
			return (Math.floor(value / precision + TOLERANCE) * precision);
		} else {
			return (Math.floor(value / precision) * precision);
		}
	}
	
	/**
	 * Smallest value multiple of 'precision' no smaller than 'value'
	 * 
	 * @param value
	 * @param precision
	 * @return
	 */
	private static Double ceil(double value, double precision) {
		if (precision < 1) {
			return (Math.ceil(value / precision - TOLERANCE) * precision);
		} else {
			return (Math.ceil(value / precision) * precision);
		}
	}
	
	/**
	 * Truncate 'value' to 'numberOfDecimals' decimals
	 * 
	 * @param value
	 * @param numberOfDecimals
	 * @return
	 */
	private static Double trunc(double value, int numberOfDecimals) {
		if (numberOfDecimals > 10) {
			throw new IllegalArgumentException("Number of decimals cannot exceed 10");
		} else {
			double power = Math.pow(10, numberOfDecimals);
			return ((long) (value * power)) / power;
		}
	}
	
	/**
	 * Round 'value' applying bankers rounding <br>
	 * 
	 * <pre>
	 * Bankers rounding :
	 * if `numberOfDecimals'+1-th digit is <= 4	-> do not change the `numberOfDecimals'-th digit
	 * if `numberOfDecimals'+1-th digit is >= 6	-> add 1 the `numberOfDecimals'-th digit
	 * if `numberOfDecimals'+1-th digit = 5		-> if `round'+2-th digit and followers are not 0	-> add 1 the `numberOfDecimals'-th digit
	 * 											-> else	(no more digits or all = 0)					-> do not change the `numberOfDecimals'-th digit
	 * </pre>
	 * 
	 * @param value
	 * @param numberOfDecimals
	 * @return
	 */
	private static Double bankersRound(double value, int numberOfDecimals) {
		if (numberOfDecimals > 10) {
			throw new IllegalArgumentException("Number of decimals cannot exceed 10");
		} else {
    		double result;
    		
    		boolean isNegative;
    		
    		double power;
    		double poweredValue;
    		long longPart;
    		
    		long lastDecimal;
    		double afterLastDecimal;
    		
    		isNegative = (value < 0.0);
    		
    		power = Math.pow(10, numberOfDecimals);
    		poweredValue = value * power;
    		longPart = (long) poweredValue;
    		
    		afterLastDecimal = Math.abs((poweredValue - longPart) * 10);
    		
    		if (afterLastDecimal < 5) {
    			result = longPart / power;
    		} else if (afterLastDecimal == 5) {
    			lastDecimal = longPart % 10;
    			
    			if (lastDecimal % 2 != 0) {
    				if (isNegative) {
    					result = (longPart - 1) / power;
    				} else {
    					result = (longPart + 1) / power;
    				}
    			} else {
    				result = longPart / power;
    			}
    		} else {
    			if (isNegative) {
    				result = (longPart - 1) / power;
    			} else {
    				result = (longPart + 1) / power;
    			}
    		}
    		
    		return result;
		}
	}
	
}
