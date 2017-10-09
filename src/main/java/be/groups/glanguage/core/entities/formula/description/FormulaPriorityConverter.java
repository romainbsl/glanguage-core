package be.groups.glanguage.core.entities.formula.description;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter class for {@link FormulaPriority} enum
 *
 * @author michotte
 * @see FormulaPriority
 */
@Converter
public class FormulaPriorityConverter implements AttributeConverter<FormulaPriority, Integer> {

	@Override
	public Integer convertToDatabaseColumn(FormulaPriority attribute) {
		switch (attribute) {
		case UNDEFINED:
			return 0;
		case INSTRUCTION:
			return 1;
		case OR:
			return 2;
		case AND:
			return 3;
		case COMPARISON:
			return 4;
		case ADDITION:
			return 5;
		case MULTIPLICATION:
			return 6;
		case UNARY:
			return 7;
		case ATOMIC:
			return 8;
		default:
			throw new IllegalArgumentException("Unknown attribute " + attribute);
		}
	}

	@Override
	public FormulaPriority convertToEntityAttribute(Integer dbData) {
		switch (dbData) {
		case 0:
			return FormulaPriority.UNDEFINED;
		case 1:
			return FormulaPriority.INSTRUCTION;
		case 2:
			return FormulaPriority.OR;
		case 3:
			return FormulaPriority.AND;
		case 4:
			return FormulaPriority.COMPARISON;
		case 5:
			return FormulaPriority.ADDITION;
		case 6:
			return FormulaPriority.MULTIPLICATION;
		case 7:
			return FormulaPriority.UNARY;
		case 8:
			return FormulaPriority.ATOMIC;
		default:
			throw new IllegalArgumentException("Unknown dbData " + dbData);
		}
	}

}
