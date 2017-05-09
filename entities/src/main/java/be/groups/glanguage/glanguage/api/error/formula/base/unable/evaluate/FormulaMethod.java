package be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate;

/**
 * Created by boissero on 5/9/2017.
 */
public enum FormulaMethod {
    VALUE("getValue"),
    INTEGER("getIntegerValue"),
    NUMERIC("getNumericValue"),
    STRING("getStringValue"),
    BOOLEAN("getBooleanValue"),
    DATE("getDateValue"),
    DURATION("getDurationValue");

    private final String name;

    FormulaMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
