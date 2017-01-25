package be.groups.glanguage.glanguage.api.error.utils;

/**
 * Created by michotte on 25/01/2017.
 */
public enum EvaluationMethodName {

    BOOLEAN("getBooleanValue"),
    DATE("getDateValue"),
    DURATION("getDurationValue"),
    INTEGER("getIntegerValue"),
    NUMERIC("getNumericValue"),
    STRING("getStringValue");

    private String methodName;

    EvaluationMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

}
