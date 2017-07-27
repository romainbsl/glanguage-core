package be.groups.glanguage.glanguage.api.entities.formula.description;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter class for {@link FormulaReturnType} enum
 *
 * @author michotte
 * @see FormulaReturnType
 */
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
			case DURATION:
				return 6;
			case LIST:
				return 7;
			case PROCEDURE:
				return 8;
			default:
				throw new IllegalArgumentException("Unknown attribute " + attribute);
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
			case 6:
				return FormulaReturnType.DURATION;
			case 7:
				return FormulaReturnType.LIST;
			case 8:
				return FormulaReturnType.PROCEDURE;
			default:
				throw new IllegalArgumentException("Unknown dbData " + dbData);
			}
		}
	}

}
