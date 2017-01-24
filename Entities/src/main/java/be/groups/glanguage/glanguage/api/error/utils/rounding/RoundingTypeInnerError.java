package be.groups.glanguage.glanguage.api.error.utils.rounding;

import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.GLanguageInnerError;

/**
 * Created by michotte on 18/01/2017.
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
