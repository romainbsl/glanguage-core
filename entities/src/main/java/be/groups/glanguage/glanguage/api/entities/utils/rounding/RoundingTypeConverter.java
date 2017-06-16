package be.groups.glanguage.glanguage.api.entities.utils.rounding;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoundingTypeConverter implements AttributeConverter<RoundingType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(RoundingType attribute) {
		switch (attribute) {
		case UNDEFINED:
			return 0;
		case ARITHMETIC:
			return 1;
		case CEIL:
			return 2;
		case FLOOR:
			return 3;
		case TRUNC:
			return 4;
		case BANKERS:
			return 5;
		default:
			throw new IllegalArgumentException("Unknown attribute " + attribute);
		}
	}

	@Override
	public RoundingType convertToEntityAttribute(Integer dbData) {
		switch (dbData) {
		case 0:
			return RoundingType.UNDEFINED;
		case 1:
			return RoundingType.ARITHMETIC;
		case 2:
			return RoundingType.CEIL;
		case 3:
			return RoundingType.FLOOR;
		case 4:
			return RoundingType.TRUNC;
		case 5:
			return RoundingType.BANKERS;
		default:
			throw new IllegalArgumentException("Unknown dbData " + dbData);
		}
	}

}
