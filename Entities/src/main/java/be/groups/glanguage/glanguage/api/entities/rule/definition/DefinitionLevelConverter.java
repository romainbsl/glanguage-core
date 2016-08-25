package be.groups.glanguage.glanguage.api.entities.rule.definition;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DefinitionLevelConverter implements AttributeConverter<DefinitionLevel, Integer> {

	@Override
	public Integer convertToDatabaseColumn(DefinitionLevel attribute) {
		switch (attribute) {
            case DEFAULT:
                return 0;
            case SOCIAL_SECRETARY:
                return 1;
            case EMPLOYER:
                return 2;
            case JOINT_COMMITTEE:
            	return 3;
            case COLLECTIVE_LABOR_AGREEMENT:
            	return 4;
            case CUSTOM:
            	return 5;
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
	}
	
	@Override
	public DefinitionLevel convertToEntityAttribute(Integer dbData) {
		switch (dbData) {
            case 0:
                return DefinitionLevel.DEFAULT;
            case 1:
                return DefinitionLevel.SOCIAL_SECRETARY;
            case 2:
                return DefinitionLevel.EMPLOYER;
            case 3:
                return DefinitionLevel.JOINT_COMMITTEE;
            case 4:
                return DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT;
            case 5:
                return DefinitionLevel.CUSTOM;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
	}

}

