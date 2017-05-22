package be.groups.glanguage.glanguage.api.error;

import be.groups.errorframework.core.validation.AbstractValidationErrorFactory;
import javafx.util.Pair;

/**
 * Created by michotte on 20/12/2016.
 */
public class GLanguageErrorFactory extends AbstractValidationErrorFactory {

    @Override
    protected Pair<String, String> findPairCodeMessage(String code) {
        GLanguageErrorRegistry vEnum = GLanguageErrorRegistry.find(code);
        return new Pair<>(vEnum.getCode(), vEnum.getMessage());
    }
}