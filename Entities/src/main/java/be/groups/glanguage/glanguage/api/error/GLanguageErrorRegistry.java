package be.groups.glanguage.glanguage.api.error;

/**
 * Created by michotte on 20/12/2016.
 */
public enum GLanguageErrorRegistry {

    PARSER_INNER_ERROR("PARSER_INNER_ERROR", "Unable to parse"),
    PARSER_UNABLE_TO_PARSE_FORMULA("PARSER_UNABLE_TO_PARSE_FORMULA", "Unable to parse a formula"),
    PARSER_UNKNOWN_FORMULA_TYPE("PARSER_UNKNOWN_FORMULA_TYPE", "Formula type is unknown"),

    RULE_VERSION_UNABLE_TO_EVALUATE("RULE_VERSION_UNABLE_TO_EVALUATE", "Unable to evaluate a rule version"),
    RULE_VERSION_UNABLE_TO_EVALUATE_TYPE("RULE_VERSION_UNABLE_TO_EVALUATE_TYPE", "Unable to evaluate a rule version"),

    RULE_VERSION_UNABLE_TO_CHECK_APPLICABILITY("RULE_VERSION_UNABLE_TO_CHECK_APPLICABILITY", "Unable to check " +
            "applicability of a rule version"),

    RULE_UNABLE_TO_GET_RETURN_TYPE("RULE_UNABLE_TO_GET_RETURN_TYPE", "Unable to get the return type of a rule version"),

    FORMULA_UNABLE_TO_INSTANTIATE("FORMULA_UNABLE_TO_INSTANTIATE", "Unable to instantiate a formula"),

    FORMULA_INNER_ERROR("FORMULA_INNER_ERROR", "Error in a formula"),

    FORMULA_UNABLE_TO_EVALUATE("FORMULA_UNABLE_TO_EVALUATE", "Unable to evaluate a formula"),
    FORMULA_UNABLE_TO_EVALUATE_TYPE("FORMULA_UNABLE_TO_EVALUATE_TYPE", "Unable to evaluate a formula"),

    FORMULA_UNABLE_TO_GET_RETURN_TYPE("FORMULA_UNABLE_TO_GET_RETURN_TYPE", "Unable to get the return type of a " +
            "formula"),

    FORMULA_CANNOT_INVOKE_EVALUATION_TYPED_METHOD("FORMULA_CANNOT_INVOKE_EVALUATION_TYPED_METHOD", "Cannot invoke " +
            "evaluation typed method of a formula"),

    FORMULA_CANNOT_INVOKE_TARGET_OBJECT_METHOD("FORMULA_CANNOT_INVOKE_TARGET_OBJECT_METHOD", "Cannot invoke target " +
            "object method of a formula"),

    FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE("FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE", "A " +
            "referenced rule is not available"),

    FORMULA_TERMINAL_MALFORMED_VALUE("FORMULA_TERMINAL_MALFORMED_VALUE", "A terminal value is malformed"),
    FORMULA_TERMINAL_NULL_VALUE("FORMULA_TERMINAL_NULL_VALUE", "A terminal value is null"),
    FORMULA_TERMINAL_UNABLE_TO_PARSE_VALUE("FORMULA_TERMINAL_UNABLE_TO_PARSE_VALUE", "" + "Unable to parse a " +
            "terminal value"),

    FORMULA_PARAMETER_LIST_NULL("FORMULA_PARAMETER_LIST_NULL", "A formula parameter list is null"),
    FORMULA_PARAMETER_NULL("FORMULA_PARAMETER_NULL", "A formula parameter is null"),
    FORMULA_PARAMETER_WRONG_NUMBER("FORMULA_PARAMETER_WRONG_NUMBER", "The number of parameters of a formula is wrong"),
    FORMULA_PARAMETER_WRONG_TYPE("FORMULA_PARAMETER_WRONG_TYPE", "Type of a formula parameter is wrong"),

    FORMULA_PARAMETER_CONBINATION_ITEM_WRONG_TYPE("FORMULA_PARAMETER_CONBINATION_ITEM_WRONG_TYPE", "Type of a " +
            "parameter does not match the type of a conbination parameter"),
    FORMULA_PARAMETER_CONBINATION_ITEM_WRONG_VALUE("FORMULA_PARAMETER_CONBINATION_ITEM_WRONG_VALUE", "Type of a " +
            "parameter does not match the possible values of a conbination parameter"),
    FORMULA_PARAMETER_CONBINATION_ITEM_UNABLE_TO_VALIDATE("FORMULA_PARAMETER_CONBINATION_ITEM_UNABLE_TO_VALIDATE",
                                                      "Unable to validate a conbination parameter"),

    ROUNDING_TYPE_INVALID("ROUNDING_TYPE_INVALID", "A rounding type is invalid");


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
