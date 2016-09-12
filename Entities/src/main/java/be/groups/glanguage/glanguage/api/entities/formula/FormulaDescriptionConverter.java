package be.groups.glanguage.glanguage.api.entities.formula;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FormulaDescriptionConverter implements AttributeConverter<FormulaDescription, Integer> {

	@Override
	public Integer convertToDatabaseColumn(FormulaDescription attribute) {
		return attribute == null ? null : attribute.getId();
	}

	@Override
	public FormulaDescription convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		} else {
			for (FormulaDescription description : FormulaDescription.values()) {
				if (description.getId().equals(dbData)) {
					return description;
				}
			}

			throw new IllegalArgumentException("Unknown dbData " + dbData);
		}
	}

}
