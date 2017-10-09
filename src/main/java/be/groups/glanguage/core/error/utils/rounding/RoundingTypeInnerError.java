package be.groups.glanguage.core.error.utils.rounding;

import be.groups.glanguage.core.entities.utils.rounding.RoundingType;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.GLanguageInnerError;

/**
 * @author michotte
 */
public class RoundingTypeInnerError extends GLanguageInnerError {

    private RoundingType roundingType;

    public RoundingTypeInnerError(RoundingType roundingType) {
        super(GLanguageErrorRegistry.ROUNDING_TYPE_INVALID.getCode());
        setMainMessage(GLanguageErrorRegistry.ROUNDING_TYPE_INVALID.getMessage());
        this.roundingType = roundingType;
        setMessage(createMessage());
    }

    public String createMessage() {
        return "Return type " + roundingType.name() + " is not valid";
    }
}
