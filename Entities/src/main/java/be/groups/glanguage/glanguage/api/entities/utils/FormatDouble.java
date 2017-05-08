package be.groups.glanguage.glanguage.api.entities.utils;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Copied from be.groups.kernel.utils.format.FormatDouble<br>
 * Double formatter
 * 
 * @author Agustin BERROCAL MENA
 * @author michotte
 */
public class FormatDouble extends FormatInteger {
	
	// What character is used for the decimal
	private char decimal;
	
	private boolean decimalVisibility;
	
	// Number of digits after the decimal character
	private int decimals;
	
	// Not Implemented !
	// // Use separators after the decimal ?
	// private boolean afterDecimalSeparate;
	
	// Show 0.5 as .5 or 0.5 ?
	private boolean zeroNotShown;
	
	// Show 0.5000 as 0.5 or 0.5000 ?
	private boolean trailingZerosShown;
	
	public boolean isTrailingZerosShown() {
		return trailingZerosShown;
	}
	
	public FormatDouble setTrailingZerosShown(boolean trailingZerosShown) {
		this.trailingZerosShown = trailingZerosShown;
		return this;
	}
	
	private boolean rounded;
	
	private DecimalFormat df;
	
	public FormatDouble(int width, int decimals) {
		super(width);
		this.decimals = decimals;
		this.decimal = '.';
		this.decimalVisibility = true;
		this.trailingZerosShown = true;
		
		df = new DecimalFormat();
	}
	
	public FormatDouble pointDecimal() {
		decimal = DOT_CHAR;
		return this;
	}
	
	public FormatDouble commaDecimal() {
		decimal = COMMA_CHAR;
		return this;
	}
	
	public FormatDouble showDecimalCharacter() {
		decimalVisibility = true;
		return this;
	}
	
	public FormatDouble hideDecimalCharacter() {
		decimalVisibility = false;
		return this;
	}
	
	public FormatDouble setDecimals(int decimals) {
		this.decimals = decimals;
		return this;
	}
	
	public FormatDouble separateAfterDecimal() {
		// afterDecimalSeparate = true;
		return this;
	}
	
	public FormatDouble noSeparateAfterDecimal() {
		// afterDecimalSeparate = false;
		return this;
	}
	
	@Override
	public FormatDouble removeSeparator() {
		super.removeSeparator();
		noSeparateAfterDecimal();
		return this;
	}
	
	@Override
	public FormatDouble commaSeparate() {
		super.commaSeparate();
		separateAfterDecimal();
		return this;
	}
	
	@Override
	public FormatDouble underscoreSeparate() {
		super.underscoreSeparate();
		separateAfterDecimal();
		return this;
	}
	
	@Override
	public String format(long value) {
		return formatted((double) value);
	}
	
	public String formatted(double value) {
		defineDecimalFormatPatternToApply();
		defineDecimalFormatSymbolsToApply();
		
		value = round(value * Math.pow(10, width - 2)) / Math.pow(10, width - 2);
		
		// Trunc value after x decimals
		value = ((long) (Math.round(value * Math.pow(10, decimals)))) / Math.pow(10, decimals);
		
		// Trunc value before x
		//value = value - ((long) (value / Math.pow(10, width - decimals)) * Math.pow(10, width - decimals));
		
		String Result = df.format(Math.abs(value));
		
		// if ( ! noSeparator()){
		// Result = Result.replace(DOT_CHAR, separator);
		// }
		
		// char[] ResultCharArray = Result.toCharArray();
		// ResultCharArray[Result.length()-(decimals+1)] = decimal;
		// Result = String.valueOf(ResultCharArray);
		
		if (!trailingZerosShown) {
			for (int i = Result.length() - 1; Result.charAt(i) == '0'; i--) {
				Result = Result.substring(0, i);
			}
			if (Result.charAt(Result.length() - 1) == decimal) {
				Result = Result.substring(0, Result.length() - 1);
			}
		}
		
		
		
		if (signMustBeFormated(value)) {
			Result = stringRepresentationWithSign(Result, value);
		}
		
		if (!decimalVisibility) {
			Result = Result.replace(String.valueOf(decimal), "");
		}
		
		if (!justification.equals(FormatAlignment.NO_JUSTIFY) && Result.length() < width) {
			Result = justify(Result);
		}
		
		if (isStrict && Result.length() > width) {
			return Result.substring(0, Math.min(width, Result.length()));
		} else {
			return Result;
		}
	}
	
	private double round(double value) {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		DecimalFormat df = new DecimalFormat();
		
		dfs.setDecimalSeparator(DOT_CHAR);
		dfs.setGroupingSeparator(COMMA_CHAR);
		if (!rounded) {
			df.setRoundingMode(RoundingMode.FLOOR);
		} else {
			df.setRoundingMode(RoundingMode.HALF_EVEN);
		}
		df.setDecimalFormatSymbols(dfs);
		df.setGroupingUsed(false);
		String lvalue = df.format(value);
		return Double.parseDouble(lvalue);
	}
	
	private void defineDecimalFormatPatternToApply() {
		String tmpDF;
		if (noSeparator()) {
			if (decimals == 0)
				tmpDF = "#";
			else {
				if (zeroNotShown)
					tmpDF = "#.";
				else
					tmpDF = "#0.";
			}
		} else {
			if (decimals == 0)
				tmpDF = "#,###";
			else {
				if (zeroNotShown)
					tmpDF = "#,###.";
				else
					tmpDF = "#,##0.";
			}
		}
		for (int i = 0; i < decimals; i++) {
			tmpDF += "0";
		}
		df.applyPattern(tmpDF);
	}
	
	private void defineDecimalFormatSymbolsToApply() {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(decimal);
		dfs.setGroupingSeparator(separator);
		if (!rounded) {
			df.setRoundingMode(RoundingMode.FLOOR);
		} else {
			df.setRoundingMode(RoundingMode.UP);
		}
		df.setDecimalFormatSymbols(dfs);
		df.setGroupingUsed(!noSeparator());
	}
	
	@Override
	public FormatDouble asteriskFill() {
		super.asteriskFill();
		return this;
	}
	
	@Override
	public FormatDouble blankFill() {
		super.blankFill();
		return this;
	}
	
	@Override
	public FormatDouble centerJustify() {
		super.centerJustify();
		return this;
	}
	
	@Override
	public FormatDouble dollarFill() {
		super.dollarFill();
		return this;
	}
	
	@Override
	public FormatDouble dotSeparate() {
		super.dotSeparate();
		separateAfterDecimal();
		return this;
	}
	
	@Override
	public FormatDouble leftJustify() {
		super.leftJustify();
		return this;
	}
	
	@Override
	public FormatDouble noJustify() {
		super.noJustify();
		return this;
	}
	
	@Override
	public FormatDouble removeFill() {
		super.removeFill();
		return this;
	}
	
	@Override
	public FormatDouble rightJustify() {
		super.rightJustify();
		return this;
	}
	
	@Override
	public FormatDouble setFill(char charactere) {
		super.setFill(charactere);
		return this;
	}
	
	@Override
	public FormatDouble setSeparator(char c) {
		super.setSeparator(c);
		separateAfterDecimal();
		return this;
	}
	
	@Override
	public FormatDouble signNegativeOnly() {
		super.signNegativeOnly();
		return this;
	}
	
	@Override
	public FormatDouble signPositiveOnly() {
		super.signPositiveOnly();
		return this;
	}
	
	@Override
	public FormatDouble signShow() {
		super.signShow();
		return this;
	}
	
	@Override
	public FormatDouble zeroFill() {
		super.zeroFill();
		return this;
		
	}
	
	public FormatDouble setRounded(boolean rounded) {
		this.rounded = rounded;
		return this;
	}
	
	public static String format(double value, int decimals, boolean showSeparateurMillier) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMAN);
		
		df.setMinimumFractionDigits(decimals);
		df.setMaximumFractionDigits(decimals);
		if (!showSeparateurMillier) {
			df.setGroupingUsed(false);
		} else {
			df.setGroupingUsed(true);
			df.setGroupingSize(3);
		}
		
		String result = df.format(value);
		if (result.equals("-0,00")) {
			result = "0,00";
		}
		return result;
	}
	
	public static String formatSans00(double value, int maxDecimals, boolean showSeparateurMillier) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMAN);
		
		df.setMinimumFractionDigits(0);
		df.setMaximumFractionDigits(maxDecimals);
		if (!showSeparateurMillier) {
			df.setGroupingUsed(false);
		} else {
			df.setGroupingUsed(true);
			df.setGroupingSize(3);
		}
		
		String result = df.format(value);
		/*
		 * if(result.equals("-0,00")){ result = "0,00"; }
		 */
		return result;
	}
	
	public String representation(double value, int minDecimals, int maxDecimals) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMAN);
		
		// Mettre la symbole decimale correcte
		DecimalFormatSymbols customSymbols = new DecimalFormatSymbols();
		customSymbols.setDecimalSeparator(decimal);
		df.setDecimalFormatSymbols(customSymbols);
		
		df.setMinimumFractionDigits(minDecimals);
		df.setMaximumFractionDigits(maxDecimals);
		
		df.setGroupingUsed(false);
		
		String result = df.format(value);
		result = result.replaceAll("^-(?=0(.0*)?$)", ""); // Supprimer symbole '-' pour les valeurs
														  // 0.0
		
		return result;
	}
	
	// TODO check
	public static String formatRepresentation(double value, int minDecimals, int maxDecimals, boolean showSeparateurMillier) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMAN);
		
		df.setMinimumFractionDigits(minDecimals);
		df.setMaximumFractionDigits(maxDecimals);
		if (!showSeparateurMillier) {
			df.setGroupingUsed(false);
		} else {
			df.setGroupingUsed(true);
			df.setGroupingSize(3);
		}
		
		return df.format(value);
	}
	
	public static String formatMontant13(double value) {
		DecimalFormat df = new DecimalFormat("000000000.0000");
		return df.format(value).replaceAll("\\.", "");
	}
	
	public static String formatMontant192(double value) {
		DecimalFormat df = new DecimalFormat("+000000000000000000;-000000000000000000");
		return df.format(value).replaceAll("\\,", "");
	}
	
}
