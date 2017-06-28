package be.groups.glanguage.glanguage.api.entities.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter class for {@link Language} enum
 *
 * @author michotte
 * @see Language
 */
@Converter
public class LanguageConverter implements AttributeConverter<Language, String> {

	@Override
	public String convertToDatabaseColumn(Language attribute) {
		return attribute == null ? null : attribute.getCode();
	}

	@Override
	public Language convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "EN":
			return Language.EN;
		case "FR":
			return Language.FR;
		case "NL":
			return Language.NL;
		case "X":
			return Language.X;
		default:
			throw new IllegalArgumentException("Unknown dbData " + dbData);
		}
	}

}
