package be.groups.glanguage.glanguage.api.entities.rule;

/**
 * Class that handles the rounding operation of a value according to a
 * RoundingType and a precision
 * 
 * @author michotte
 */
public class Rounder {

	public static final double TOLERANCE = Math.exp(-6);

	public static Double round(Integer value, RoundingType roundingType, Double precision) {
		switch (roundingType) {
		case ARITHMETIC:
			return round(value.doubleValue(), precision);
		case CEIL:
			return ceil(value.doubleValue(), precision);
		case FLOOR:
			return floor(value.doubleValue(), precision);
		case BANKERS:
			return bankersRound(value.doubleValue(), precision.intValue());
		case TRUNC:
			return trunc(value.doubleValue(), precision.intValue());
		case UNDEFINED:
			// fall through
		default:
			throw new IllegalArgumentException("The RoundingType " + roundingType.name() + " is not a valid one !");
		}
	}

	public static Double round(Double value, RoundingType roundingType, Double precision) {
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
			// fall through
		default:
			throw new IllegalArgumentException("The RoundingType " + roundingType.name() + " is not a valid one !");
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
		double power = Math.pow(10, numberOfDecimals);
		return ((int) (value * power)) / power;
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
		double result;
		double power;
		double l_d;
		int l_i;
		boolean is_negative;

		is_negative = (value < 0.0);
		power = Math.pow(10, numberOfDecimals);
		l_d = value * power;
		l_i = (int) l_d;
		l_d = l_d - l_i;
		l_d = l_d * 10;
		l_d = Math.abs(l_d);
		if (l_d <= 5) {
			result = ((int) (value * power)) / power;
		} else {
			if (is_negative) {
				result = ((int) (value * power) - 1) / power;
			} else {
				result = ((int) (value * power) + 1) / power;
			}
		}
		return result;
	}

}
