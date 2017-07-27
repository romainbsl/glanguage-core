package be.groups.glanguage.glanguage.api.error;

import be.groups.errorframework.core.validation.AbstractValidationErrorFactory;
import javafx.util.Pair;

/**
 * @author michotte
 */
public class GLanguageErrorFactory extends AbstractValidationErrorFactory {

    @Override
    protected Pair<String, String> findPairCodeMessage(String code) {
        GLanguageErrorRegistry vEnum = GLanguageErrorRegistry.find(code);
        return new Pair<>(vEnum.getCode(), vEnum.getMessage());
    }
}
