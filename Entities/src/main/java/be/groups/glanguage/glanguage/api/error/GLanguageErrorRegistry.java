package be.groups.glanguage.glanguage.api.error;

/**
 * Created by michotte on 20/12/2016.
 */
public enum GLanguageErrorRegistry {

    RULE_VERSION_UNABLE_TO_EVALUATE("RULE_VERSION_UNABLE_TO_EVALUATE", "Unable to evaluate a rule version"),
    RULE_VERSION_UNABLE_TO_EVALUATE_TYPE("RULE_VERSION_UNABLE_TO_EVALUATE_TYPE", "Unable to evaluate a rule version"),
    RULE_VERSION_UNABLE_TO_CHECK_APPLICABILITY("RULE_VERSION_UNABLE_TO_CHECK_APPLICABILITY", "Unable to check " +
            "applicability of a rule version"),

    FORMULA_UNABLE_TO_EVALUATE("FORMULA_UNABLE_TO_EVALUATE", "Unable to evaluate a formula"),
    FORMULA_UNABLE_TO_EVALUATE_TYPE("FORMULA_UNABLE_TO_EVALUATE_TYPE", "Unable to evaluate a formula"),

    FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE("FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE", "A " +
            "referenced rule is not available"),

    FORMULA_TERMINAL_MALFORMED_VALUE("FORMULA_TERMINAL_MALFORMED_VALUE", "A terminal value is malformed"),
    FORMULA_TERMINAL_NULL_VALUE("FORMULA_TERMINAL_NULL_VALUE", "A terminal value is null"),
    FORMULA_TERMINAL_UNABLE_TO_PARSE_VALUE("FORMULA_TERMINAL_UNABLE_TO_PARSE_VALUE", "" + "Unable to parse a " +
            "terminal value");


    private String code;
    private String message;

    GLanguageErrorRegistry(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static GLanguageErrorRegistry find(String code) {
        for (GLanguageErrorRegistry v : GLanguageErrorRegistry.values()) {
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
