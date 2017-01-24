package be.groups.glanguage.glanguage.api.entities.utils.format;

/**
 * Copied from be.groups.kernel.utils.format.Alignement<br>
 * Enumeration for text alignment types
 * 
 * @author Agustin BERROCAL MENA
 * @author michotte
 */
public enum FormatAlignment {
    NO_JUSTIFY,
    LEFT_JUSTIFY,
    CENTER_JUSTIFY,
    RIGHT_JUSTIFY;
	
	public class Values {
		public static final String NO_JUSTIFY = "NONE";
		public static final String LEFT_JUSTIFY = "LEFT";
		public static final String CENTER_JUSTIFY = "CENTER";
		public static final String RIGHT_JUSTIFY = "RIGHT";
	}
}
