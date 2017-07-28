package be.groups.glanguage.glanguage.api.entities.rule.definition;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter class for {@link DefinitionLevel} enum
 *
 * @author michotte
 */
@Converter
public class DefinitionLevelConverter implements AttributeConverter<DefinitionLevel, Integer> {

	@Override
	public Integer convertToDatabaseColumn(DefinitionLevel attribute) {
		switch (attribute) {
		case SOCIAL_SECRETARY:
			return 1;
		case EMPLOYER:
			return 2;
		case JOINT_COMMITTEE:
			return 3;
		case COLLECTIVE_LABOR_AGREEMENT:
			return 4;
		default:
			throw new IllegalArgumentException("Not a persistable attribute " + attribute);
		}
	}

	@Override
	public DefinitionLevel convertToEntityAttribute(Integer dbData) {
		switch (dbData) {
		case 1:
			return DefinitionLevel.SOCIAL_SECRETARY;
		case 2:
			return DefinitionLevel.EMPLOYER;
		case 3:
			return DefinitionLevel.JOINT_COMMITTEE;
		case 4:
			return DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT;
		default:
			throw new IllegalArgumentException("Unknown dbData " + dbData);
		}
	}

}
