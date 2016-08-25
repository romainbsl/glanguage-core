package be.groups.glanguage.glanguage.api.entities.formula;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FormulaReturnTypeConverter implements AttributeConverter<FormulaReturnType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(FormulaReturnType attribute) {
		if (attribute == null) {
			return null;
		} else {
    		switch (attribute) {
                case UNDEFINED:
                    return 0;
                case INTEGER:
                    return 1;
                case NUMERIC:
                    return 2;
                case STRING:
                	return 3;
                case BOOLEAN:
                	return 4;
                case DATE:
                	return 5;
                default:
                    throw new IllegalArgumentException("Unknown" + attribute);
            }
		}
	}
	
	@Override
	public FormulaReturnType convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		} else {
    		switch (dbData) {
                case 0:
                    return FormulaReturnType.UNDEFINED;
                case 1:
                    return FormulaReturnType.INTEGER;
                case 2:
                    return FormulaReturnType.NUMERIC;
                case 3:
                    return FormulaReturnType.STRING;
                case 4:
                    return FormulaReturnType.BOOLEAN;
                case 5:
                    return FormulaReturnType.DATE;
                default:
                    throw new IllegalArgumentException("Unknown" + dbData);
    		}
		}
	}

}

