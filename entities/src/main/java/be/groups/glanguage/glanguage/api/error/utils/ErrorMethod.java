package be.groups.glanguage.glanguage.api.error.utils;

/**
 * @author michotte
 */
public enum ErrorMethod {
    VALIDATE("validate"),
    VALUE("getValue"),
    INTEGER("getIntegerValue"),
    NUMERIC("getNumericValue"),
    STRING("getStringValue"),
    BOOLEAN("getBooleanValue"),
    DATE("getDateValue"),
    DURATION("getDurationValue");

    private final String name;

    ErrorMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
