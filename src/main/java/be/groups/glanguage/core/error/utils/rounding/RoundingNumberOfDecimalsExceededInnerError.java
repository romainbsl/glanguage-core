package be.groups.glanguage.core.error.utils.rounding;

import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.GLanguageInnerError;

/**
 * @author michotte
 */
public class RoundingNumberOfDecimalsExceededInnerError extends GLanguageInnerError {

    private Integer actualNumberumberOfDecimals;

    public RoundingNumberOfDecimalsExceededInnerError(Integer actualNumberumberOfDecimals) {
        super(GLanguageErrorRegistry.ROUNDING_NUMBER_OF_DECIMALS_EXCEEDED.getCode());
        setMainMessage(GLanguageErrorRegistry.ROUNDING_NUMBER_OF_DECIMALS_EXCEEDED.getMessage());
        this.actualNumberumberOfDecimals = actualNumberumberOfDecimals;
        setMessage(createMessage());
    }

    public String createMessage() {
        return "Actual number of decimals : " + actualNumberumberOfDecimals + " is not valid";
    }
}
