package be.groups.glanguage.core.entities.utils.rounding;

import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.utils.rounding.RoundingTypeInnerError;

/**
 * Class that handles the rounding operation of a value according to a {@link RoundingType} and a precision
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
     * Multiple of {@code precision} closest to {@code value}
     *
     * @param value     the value to be rounded
     * @param precision the precision to be used to round the {@code value}
     * @return the multiple of {@code precision} closest to {@code value}
     */
    private static Double round(double value, double precision) {
        return (Math.round(value / precision) * precision);
    }

    /**
     * Greatest multiple of {@code precision} no greater than {@code value}
     *
     * @param value     the value to be rounded
     * @param precision the precision to be used to round the {@code value}
     * @return the greatest multiple of {@code precision} no greater than {@code value}
     */
    private static Double floor(double value, double precision) {
        if (precision < 1.0) {
            return (Math.floor(value / precision + TOLERANCE) * precision);
        } else {
            return (Math.floor(value / precision) * precision);
        }
    }

    /**
     * Smallest multiple of 'precision' no smaller than 'value'
     *
     * @param value     the value to be rounded
     * @param precision the precision to be used to round the {@code value}
     * @return the smallest multiple of {@code precision} no smaller than {@code value}
     */
    private static Double ceil(double value, double precision) {
        if (precision < 1) {
            return (Math.ceil(value / precision - TOLERANCE) * precision);
        } else {
            return (Math.ceil(value / precision) * precision);
        }
    }

    /**
     * Truncate {@code value} to {@code numberOfDecimals} decimals
     *
     * @param value            the value to be rounded
     * @param numberOfDecimals the number of decimals of the truncated value to be returned
     * @return the {@code value} truncated to {@code numberOfDecimals} decimals
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
     * Round {@code value} to a {@code numberOfDecimals} applying bankers rounding method <br>
     * <p>
     * <pre>
     * Bankers rounding :
     * if `numberOfDecimals'+1-th digit is <= 4             -> do not change the `numberOfDecimals'-th digit
     * if `numberOfDecimals'+1-th digit is >= 6             -> add 1 the `numberOfDecimals'-th digit
     * if `numberOfDecimals'+1-th digit = 5
     *      if `round'+2-th digit and followers are not 0   -> add 1 the`numberOfDecimals'-th digit
     *      else (no more digits or all = 0)                -> do not change the `numberOfDecimals'-th digit
     * </pre>
     *
     * @param value            the value to be rounded
     * @param numberOfDecimals the number of decimals of the rounded value to be returned
     * @return the {@code value} rounded to {@code numberOfDecimals} decimals applying bankers rounding method
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
