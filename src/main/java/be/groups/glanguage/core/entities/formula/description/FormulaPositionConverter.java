package be.groups.glanguage.core.entities.formula.description;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter class for {@link FormulaPosition} enum
 *
 * @author michotte
 * @see FormulaPosition
 */
@Converter
public class FormulaPositionConverter implements AttributeConverter<FormulaPosition, Integer> {

	@Override
	public Integer convertToDatabaseColumn(FormulaPosition attribute) {
		return attribute == null ? null : attribute.getId();
	}

	@Override
	public FormulaPosition convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		} else {
			switch (dbData) {
			case 0:
				return FormulaPosition.NONE;
			case 1:
				return FormulaPosition.PREFIX_BRACKET;
			case 2:
				return FormulaPosition.PREFIX;
			case 3:
				return FormulaPosition.INFIX;
			case 4:
				return FormulaPosition.POSTFIX;
			case 5:
				return FormulaPosition.POSTFIX_DOT;
			case 6:
				return FormulaPosition.CUSTOM;
			case 7:
			default:
				throw new IllegalArgumentException("Unknown dbData " + dbData);
			}
		}
	}

}
