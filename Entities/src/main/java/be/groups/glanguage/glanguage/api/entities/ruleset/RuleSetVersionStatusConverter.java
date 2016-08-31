package be.groups.glanguage.glanguage.api.entities.ruleset;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RuleSetVersionStatusConverter implements AttributeConverter<RuleSetVersionStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(RuleSetVersionStatus attribute) {
		switch (attribute) {
            case DRAFT:
                return 0;
            case TEST:
                return 1;
            case PROD:
                return 2;
            default:
                throw new IllegalArgumentException("Unknown attribute " + attribute);
        }
	}
	
	@Override
	public RuleSetVersionStatus convertToEntityAttribute(Integer dbData) {
		switch (dbData) {
            case 0:
                return RuleSetVersionStatus.DRAFT;
            case 1:
                return RuleSetVersionStatus.TEST;
            case 2:
                return RuleSetVersionStatus.PROD;
            default:
                throw new IllegalArgumentException("Unknown dbData " + dbData);
        }
	}

}

