package be.groups.glanguage.glanguage.api.error.utils;

/**
 * Created by michotte on 25/01/2017.
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