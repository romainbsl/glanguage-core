package be.groups.glanguage.glanguage.api.error;

/**
 * Created by michotte on 20/12/2016.
 */
public enum GlanguageErrorRegistry {

    FORMULA_UNABLE_TO_EVALUATE("FORMULA_UNABLE_TO_EVALUATE", "Unable to evaluate a formula"),
    FORMULA_UNABLE_TO_EVALUATE_TYPE("FORMULA_UNABLE_TO_EVALUATE_TYPE", "Unable to evaluate a formula"),
    FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE("FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE", "The " +
            "referenced rule is not available");

    private String code;
    private String message;

    GlanguageErrorRegistry(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static GlanguageErrorRegistry find(String code) {
        for (GlanguageErrorRegistry v : GlanguageErrorRegistry.values()) {
            if (v.getCode().equals(code)) return v;
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}
