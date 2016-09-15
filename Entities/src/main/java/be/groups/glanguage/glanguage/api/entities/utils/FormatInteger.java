package be.groups.glanguage.glanguage.api.entities.utils;



import java.text.DecimalFormat;

/**
 * Copied from be.groups.kernel.utils.format.FormatInteger<br>
 * Integer formatter
 * 
 * @author Agustin BERROCAL MENA
 * @author michotte
 */
public class FormatInteger {
    
    // Width of the field.
    protected int width;
    
    // Padding character.
    protected char fillCharacter;
    
    // Separator between 1000's of numbers.
    protected char separator;
    
    // Where in the field the format goes.
    protected FormatAlignment justification;
    
    // Is the sign at the end ?
    protected boolean trailingSign;
    
    // How the sign is formatted.
    protected FormatIntegerSignVisibility signFormat;
    
    // Formatting details for the sign.
    protected String signString;
    
    //Est ce que la taille du formatage est fixe?
    //Si oui, ex : un entier "123456789" a formater en 6 position retournera "123456".
    protected boolean isStrict;
    
    // Constants Fill Character
    protected static final char BLANK_CHAR = ' ';
    protected static final char ZERO_CHAR = '0';
    protected static final char DOLLAR_CHAR = '$';
    protected static final char ASTERISK_CHAR = '*';
    
    // Constants Separator Character
    protected static final char EMPTY_CHAR = '\0';
    protected static final char DOT_CHAR = '.';
    protected static final char COMMA_CHAR = ',';
    protected static final char UNDERSCORE_CHAR = '_';
    
    protected static final String SIGN_NORMAL = "- +";
    
    /**
     * Constructeur
     * @param width 
     */
    public FormatInteger(int width){
        this.width = width;
        fillCharacter = BLANK_CHAR;
        signString = SIGN_NORMAL;
        signFormat = FormatIntegerSignVisibility.SHOW_NEGATIVE_ONLY;
        justification = FormatAlignment.RIGHT_JUSTIFY;
        isStrict = false;
    }
    
    /**
     * Is there a fill character ?
     * @return 
     */
    public boolean noFill(){
        return fillCharacter == EMPTY_CHAR;
    }
    
    /**
     * Is there a separator?
     * @return 
     */
    public boolean noSeparator(){
        return separator == EMPTY_CHAR;
    }
    
    /**
     * Are numbers to be formatted without alignment justification ?
     * @return 
     */
    public boolean noJustified(){
        return justification.equals(FormatAlignment.NO_JUSTIFY);
    }
    
    /**
     * Are numbers to be formatted with spaces on the right ?
     * @return 
     */
    public boolean leftJustified(){
        return justification.equals(FormatAlignment.LEFT_JUSTIFY);
    }
    
    /**
     * Are numbers to be formatted with spaces on the left ?
     * @return 
     */
    public boolean rightJustified(){
        return justification.equals(FormatAlignment.RIGHT_JUSTIFY);
    }
    
    /**
     * Are numbers to be formatted with spaces on both sides ?
     * @return 
     */
    public boolean centerJustified(){
        return justification.equals(FormatAlignment.CENTER_JUSTIFY);
    }
    
    /**
     * Are numbers to show sign only when negative ?
     * @return 
     */
    public boolean showSignNegative(){
        return signFormat.equals(FormatIntegerSignVisibility.SHOW_NEGATIVE_ONLY);
    }
    
    /**
     * Is the sign leading?
     * @return 
     */
    public boolean leadingSign(){
        return ! trailingSign;
    }    

    public FormatInteger setFill(char charactere) {
        fillCharacter = charactere;
        return this;
    }

    public FormatInteger removeFill() {
        fillCharacter = EMPTY_CHAR;
        return this;
    }
    
    //Fill numbers with blanks.
    public FormatInteger blankFill(){
        fillCharacter = BLANK_CHAR;
        return this;
    }
    
    //Fill numbers with zeros.
    public FormatInteger zeroFill(){
        fillCharacter = ZERO_CHAR;
        return this;
    }
    
    //Fill numbers with dollars.
    public FormatInteger dollarFill(){
        fillCharacter = DOLLAR_CHAR;
        return this;
    }
    
    //Fill numbers with asterisks.
    public FormatInteger asteriskFill(){
        fillCharacter = ASTERISK_CHAR;
        return this;
    }

    public FormatInteger setSeparator(char c) {
        separator = c;
        return this;
    }

    public FormatInteger removeSeparator() {
        separator = EMPTY_CHAR;
        return this;
    }

    public FormatInteger dotSeparate() {
        separator = DOT_CHAR;
        return this;
    }

    public FormatInteger commaSeparate() {
        separator = COMMA_CHAR;
        return this;
    }

    public FormatInteger underscoreSeparate() {
        separator = UNDERSCORE_CHAR;
        return this;
    }

    public FormatInteger noJustify() {
        justification = FormatAlignment.NO_JUSTIFY;
        return this;
    }

    public FormatInteger leftJustify() {
        justification = FormatAlignment.LEFT_JUSTIFY;
        return this;
    }

    public FormatInteger rightJustify() {
        justification = FormatAlignment.RIGHT_JUSTIFY;
        return this;
    }

    public FormatInteger centerJustify() {
        justification = FormatAlignment.CENTER_JUSTIFY;
        return this;
    }

    public FormatInteger signShow() {
        signFormat = FormatIntegerSignVisibility.SHOW_ALL;
        return this;
    }

    public FormatInteger signIgnore() {
        signFormat = FormatIntegerSignVisibility.IGNORE_ALL;
        return this;
    }

    public FormatInteger signNegativeOnly() {
        signFormat = FormatIntegerSignVisibility.SHOW_NEGATIVE_ONLY;
        return this;
    }

    public FormatInteger signPositiveOnly() {
        signFormat = FormatIntegerSignVisibility.SHOW_POSITIVE_ONLY;
        return this;
    }
   
    /**
     * Format the integer
     * @param value
     * @return 
     */
    public String format (long value){
        StringBuilder Result = new StringBuilder();
        //if (canFormat(value)){ //TODO maesjasp Controler comportement comme en Eiffel
            if (noSeparator()) {
                Result.append(Math.abs(value));
            } else {
                Result.append(stringRepresentationWithSeparators(Math.abs(value)));
            }
            if (signMustBeFormated(value)) {
                Result = new StringBuilder (stringRepresentationWithSign (Result.toString(), value));
            }
            if ( ! justification.equals(FormatAlignment.NO_JUSTIFY) && Result.length() < width) {
                Result = new StringBuilder (justify (Result.toString()));
            }
            if(isStrict && Result.length() > width){
            	return Result.substring(0,width);
            }
            return Result.toString();
        //}
        //else{
        //    return null;
        //}
    }
    
    /**
     * Should the sign be formatted
     * 
     * @param value
     * @return
     * TODO check value equals 0.0 with tolerance
     */
    protected boolean signMustBeFormated (double value){
        return (!signFormat.equals(FormatIntegerSignVisibility.IGNORE_ALL) &&
               ((signFormat.equals(FormatIntegerSignVisibility.SHOW_NEGATIVE_ONLY) || signFormat.equals(FormatIntegerSignVisibility.SHOW_ALL)) && value < 0.0) ||
               ((signFormat.equals(FormatIntegerSignVisibility.SHOW_POSITIVE_ONLY) || signFormat.equals(FormatIntegerSignVisibility.SHOW_ALL)) && value > 0.0) ||
                (signFormat.equals(FormatIntegerSignVisibility.SHOW_ALL) && value == 0.0));
    }
    
    private String stringRepresentationWithSeparators(long value){
        StringBuilder Result = new StringBuilder(width);
        String s = value + "";
        int count = s.length();
        while (count > 3) {
            for (int sepLength = 0; sepLength < 3; sepLength++) {
                Result.insert(0, s.charAt(--count)); //Pré-Incrementation de count !
            }
            Result.insert(0, separator);
        }
        return Result.insert(0, s.substring(0, count)).toString();
    }
    
    protected String stringRepresentationWithSign(String s, double value) {
        StringBuilder Result = new StringBuilder(s);
        String sstring = stringRepresentationOfSign(value);
        if (sstring != null) {
            if (trailingSign) {
                Result.append(sstring);
            } else {
                Result.insert(0, sstring);
            }
        }
        return Result.toString();
    }

    /**
     * String representation of sign
     * @param value
     * @return 
     * TODO check value equals 0.0 with tolerance
     */
    private String stringRepresentationOfSign(double value) {
        int sign = 0;
        if (value < 0) sign = -1;
        else if (value > 0) sign = 1;
        else if (value == 0.0) sign = 1;
        
	int t = signLength() * (sign + 1);
	return signString.substring (t, t + signLength());
    }

    private int signLength() {
        return (signString.length()/3);
    }

    protected String justify(String s) {
        StringBuilder l, r;
        int i, t;
        String Result = s;
        if (!justification.equals(FormatAlignment.CENTER_JUSTIFY)) {
            // be concerned about filling
            l = new StringBuilder (width - s.length());
            
            for (i = 0; i < l.capacity(); i++) {
                l.append(fillCharacter);
            }
            
            if (justification.equals(FormatAlignment.LEFT_JUSTIFY)) {
                Result = l.insert(0, Result).toString();
            } else {
            	if (fillCharacter == '0' && (Result.charAt(0) == '-' || Result.charAt(0) == '+')) {
            		Result = Result.charAt(0) + l.append(Result.substring(1)).toString();
            	} else {
            		Result = l.append(Result).toString();
            	}
            }
        }else{// centered
            // add spaces both sides, more on left than right though
            // when there is a choice
            t = (width - s.length()) / 2;
            if (2 * t + s.length() < width){
                l = new StringBuilder (t + 1);
            }else{
                l = new StringBuilder (t);
            }
            r = new StringBuilder (t);
            for (i = 0; i < r.capacity(); i++) {
                l.append(fillCharacter);
                r.append(fillCharacter);
            }
            if ((width - s.length()) % 2 != 0)  l.append (fillCharacter);
            Result = l.append(Result).toString();
            Result = r.insert(0, Result).toString();
        }
        return Result;
    }
    
    public static String formatXPositionsSignificatives(int value, int x) {
    	StringBuilder format = new StringBuilder();
    	for (int i = 0; i < x; i++) {
			format.append("0");
		}
    	
    	DecimalFormat df = new DecimalFormat(format.toString());
        return df.format(value);
    }
    
    public static String simpleFormat(int value) {
        DecimalFormat df = new DecimalFormat("#0;-#0");
        return df.format(value);
    }

	public int getWidth() {
		return width;
	}

	public FormatIntegerSignVisibility getSignFormat() {
		return signFormat;
	}

	public char getFillCharacter() {
		return fillCharacter;
	}
    
    public void setStrict(){
    	isStrict = true;
    }
    
    public enum FormatIntegerSignVisibility {    
        SHOW_ALL,
        SHOW_NEGATIVE_ONLY,
        SHOW_POSITIVE_ONLY,
        IGNORE_ALL,
        IGNORE_NEGATIVE_ONLY,
        IGNORE_POSITIVE_ONLY;
    }
    
}