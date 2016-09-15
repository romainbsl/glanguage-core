package be.groups.glanguage.glanguage.api.entities.utils;

/**
 * Enumeration for sign formats
 * 
 * @author michotte
 */
public enum FormatSign {
	NONE,
	NEGATIVE_ONLY,
	POSITIVE_ONLY,
	BOTH;
	
	public class Values {
		public static final String NONE = "NONE";
		public static final String NEGATIVE_ONLY = "NEGATIVEONLY";
		public static final String POSITIVE_ONLY = "POSITIVEONLY";
		public static final String BOTH = "BOTH";
	}
}
