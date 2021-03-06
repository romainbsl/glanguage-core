package be.groups.glanguage.core.entities.rule;

import be.groups.glanguage.core.entities.utils.rounding.Rounder;
import be.groups.glanguage.core.entities.utils.rounding.RoundingType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link Rounder}
 * 
 * @author DUPIREFR
 */
public class RounderTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with lower arithmetic rounding,
	 * precision 10
	 */
	@Test
	public void testRoundIntegerLowerArithmeticTensPrecision() throws GLanguageException {
		Integer value = 111;
		RoundingType roundingType = RoundingType.ARITHMETIC;
		Double precision = 10.0;
		
		assertEquals(Double.valueOf(110), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with lower arithmetic rounding,
	 * precision 10
	 */
	@Test
	public void testRoundIntegerUpperArithmeticTensPrecision() throws GLanguageException {
		Integer value = 118;
		RoundingType roundingType = RoundingType.ARITHMETIC;
		Double precision = 10.0;
		
		assertEquals(Double.valueOf(120), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with lower arithmetic rounding,
	 * precision 100
	 */
	@Test
	public void testRoundIntegerLowerArithmeticHundredsPrecision() throws GLanguageException {
		Integer value = 111;
		RoundingType roundingType = RoundingType.ARITHMETIC;
		Double precision = 100.0;
		
		assertEquals(Double.valueOf(100), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with upper arithmetic rounding,
	 * precision 100
	 */
	@Test
	public void testRoundIntegerUpperArithmeticHundredsPrecision() throws GLanguageException {
		Integer value = 151;
		RoundingType roundingType = RoundingType.ARITHMETIC;
		Double precision = 100.0;
		
		assertEquals(Double.valueOf(200), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with ceil rounding, precision 10
	 */
	@Test
	public void testRoundIntegerCeilTensPrecision() throws GLanguageException {
		Integer value = 111;
		RoundingType roundingType = RoundingType.CEIL;
		Double precision = 10.0;
		
		assertEquals(Double.valueOf(120), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with ceil rounding, precision 100
	 */
	@Test
	public void testRoundIntegerCeilHundredsPrecision() throws GLanguageException {
		Integer value = 111;
		RoundingType roundingType = RoundingType.CEIL;
		Double precision = 100.0;
		
		assertEquals(Double.valueOf(200), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with floor rounding, precision 10
	 */
	@Test
	public void testRoundIntegerFloorTensPrecision() throws GLanguageException {
		Integer value = 118;
		RoundingType roundingType = RoundingType.FLOOR;
		Double precision = 10.0;
		
		assertEquals(Double.valueOf(110), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with floor rounding, precision 100
	 */
	@Test
	public void testRoundIntegerFloorHundredsPrecision() throws GLanguageException {
		Integer value = 151;
		RoundingType roundingType = RoundingType.FLOOR;
		Double precision = 100.0;
		
		assertEquals(Double.valueOf(100), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with trunc rounding, precision 10
	 */
	@Test
	public void testRoundIntegerTrunc() throws GLanguageException {
		Integer value = 118;
		RoundingType roundingType = RoundingType.TRUNC;
		Double precision = 10.0;
		
		assertEquals(Double.valueOf(118), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with trunc rounding, precision 11
	 * (too much decimals)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRoundIntegerTruncTooMuchDecimals() throws GLanguageException {
		Integer value = 118;
		RoundingType roundingType = RoundingType.TRUNC;
		Double precision = 11.0;
		
		Rounder.round(value, roundingType, precision);
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with bankers rounding, precision
	 * 10
	 */
	@Test
	public void testRoundIntegerBankers() throws GLanguageException {
		Integer value = 118;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 10.0;
		
		assertEquals(Double.valueOf(118), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Integer, RoundingType, Double)} with bankers rounding, precision
	 * 11 (too much decimals)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRoundIntegerBankersTooMuchDecimals() throws GLanguageException {
		Integer value = 118;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 11.0;
		
		Rounder.round(value, roundingType, precision);
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with lower arithmetic rounding,
	 * precision 0.01
	 */
	@Test
	public void testRoundNumericLowerArithmetic100thPrecision() throws GLanguageException {
		Double value = 1.564;
		RoundingType roundingType = RoundingType.ARITHMETIC;
		Double precision = 0.01;
		
		assertEquals(Double.valueOf(1.56), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with lower arithmetic rounding,
	 * precision 10
	 */
	@Test
	public void testRoundNumericUpperArithmeticTensPrecision() throws GLanguageException {
		Double value = 1.566;
		RoundingType roundingType = RoundingType.ARITHMETIC;
		Double precision = 0.01;
		
		assertEquals(Double.valueOf(1.57), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with lower arithmetic rounding,
	 * precision 0.1
	 */
	@Test
	public void testRoundNumericLowerArithmetic10thPrecision() throws GLanguageException {
		Double value = 1.544;
		RoundingType roundingType = RoundingType.ARITHMETIC;
		Double precision = 0.1;
		
		assertEquals(Double.valueOf(1.5), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with upper arithmetic rounding,
	 * precision 100
	 */
	@Test
	public void testRoundNumericUpperArithmeticHundredsPrecision() throws GLanguageException {
		Double value = 1.554;
		RoundingType roundingType = RoundingType.ARITHMETIC;
		Double precision = 0.1;
		
		assertEquals(Double.valueOf(1.6), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with ceil rounding, precision 0.01
	 */
	@Test
	public void testRoundNumericCeil100thPrecision() throws GLanguageException {
		Double value = 1.564;
		RoundingType roundingType = RoundingType.CEIL;
		Double precision = 0.01;
		
		assertEquals(Double.valueOf(1.57), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with ceil rounding, precision 0.1
	 */
	@Test
	public void testRoundNumericCeil10thPrecision() throws GLanguageException {
		Double value = 1.544;
		RoundingType roundingType = RoundingType.CEIL;
		Double precision = 0.1;
		
		assertEquals(Double.valueOf(1.6), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with floor rounding, precision 0.01
	 */
	@Test
	public void testRoundNumericFloor100thPrecision() throws GLanguageException {
		Double value = 1.566;
		RoundingType roundingType = RoundingType.FLOOR;
		Double precision = 0.01;
		
		assertEquals(Double.valueOf(1.56), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with floor rounding, precision 0.1
	 */
	@Test
	public void testRoundNumericFloor10thPrecision() throws GLanguageException {
		Double value = 1.566;
		RoundingType roundingType = RoundingType.FLOOR;
		Double precision = 0.1;
		
		assertEquals(Double.valueOf(1.5), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with lower trunc rounding,
	 * precision 2
	 */
	@Test
	public void testRoundNumericLowerTruncTwoPrecision() throws GLanguageException {
		Double value = 1.564;
		RoundingType roundingType = RoundingType.TRUNC;
		Double precision = 2.0;
		
		assertEquals(Double.valueOf(1.56), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with upper trunc rounding,
	 * precision 2
	 */
	@Test
	public void testRoundNumericUpperTruncTwoPrecision() throws GLanguageException {
		Double value = 1.566;
		RoundingType roundingType = RoundingType.TRUNC;
		Double precision = 2.0;
		
		assertEquals(Double.valueOf(1.56), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with lower trunc rounding,
	 * precision 1
	 */
	@Test
	public void testRoundNumericLowerTruncOnePrecision() throws GLanguageException {
		Double value = 1.544;
		RoundingType roundingType = RoundingType.TRUNC;
		Double precision = 1.0;
		
		assertEquals(Double.valueOf(1.5), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with upper trunc rounding,
	 * precision 1
	 */
	@Test
	public void testRoundNumericUpperTruncOnePrecision() throws GLanguageException {
		Double value = 1.566;
		RoundingType roundingType = RoundingType.TRUNC;
		Double precision = 1.0;
		
		assertEquals(Double.valueOf(1.5), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with trunc rounding, precision 11
	 * (too much decimals)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRoundNumericTruncTooMuchDecimals() throws GLanguageException {
		Double value = 1.55;
		RoundingType roundingType = RoundingType.TRUNC;
		Double precision = 11.0;
		
		Rounder.round(value, roundingType, precision);
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with bankers rounding, precision 2,
	 * after last decimal being inferior to 5
	 */
	@Test
	public void testRoundNumericBankersLowAfterLastTwoPrecision() throws GLanguageException {
		Double value = 1.564;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 2.0;
		
		assertEquals(Double.valueOf(1.56), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with bankers rounding, precision 1,
	 * after last decimal being inferior to 5
	 */
	@Test
	public void testRoundNumericBankersLowAfterLastOnePrecision() throws GLanguageException {
		Double value = 1.54;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 1.0;
		
		assertEquals(Double.valueOf(1.5), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with bankers rounding, precision 2,
	 * after last decimal being 5, no trail, last decimal is even
	 */
	@Test
	public void testRoundNumericBankers5AfterLastLastEvenTwoPrecision() throws GLanguageException {
		Double value = 1.565;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 2.0;
		
		assertEquals(Double.valueOf(1.56), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with bankers rounding, precision 2,
	 * after last decimal being 5, no trail, last decimal is odd
	 */
	@Test
	public void testRoundNumericBankers5AfterLastLastOddTwoPrecision() throws GLanguageException {
		Double value = 1.575;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 2.0;
		
		assertEquals(Double.valueOf(1.58), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with bankers rounding, precision 2,
	 * after last decimal being 5, no trail, last decimal is odd, negative number
	 */
	@Test
	public void testRoundNumericBankers5AfterLastLastOddNegativeTwoPrecision() throws GLanguageException {
		Double value = -1.575;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 2.0;
		
		assertEquals(Double.valueOf(-1.58), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with bankers rounding, precision 2,
	 * after last decimal being superior to 5
	 */
	@Test
	public void testRoundNumericBankersHighAfterLastTwoPrecision() throws GLanguageException {
		Double value = 1.5651;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 2.0;
		
		assertEquals(Double.valueOf(1.57), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with bankers rounding, precision 2,
	 * after last decimal being superior to 5, negative number
	 */
	@Test
	public void testRoundNumericBankersHighAfterLastNegativeTwoPrecision() throws GLanguageException {
		Double value = -1.5651;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 2.0;
		
		assertEquals(Double.valueOf(-1.57), Rounder.round(value, roundingType, precision));
	}
	
	/**
	 * Tests {@link Rounder#round(Double, RoundingType, Double)} with bankers rounding, precision 11
	 * (too much decimals)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRoundNumericBankersTooMuchDecimals() throws GLanguageException {
		Double value = 1.564;
		RoundingType roundingType = RoundingType.BANKERS;
		Double precision = 11.0;
		
		Rounder.round(value, roundingType, precision);
	}
	
}
