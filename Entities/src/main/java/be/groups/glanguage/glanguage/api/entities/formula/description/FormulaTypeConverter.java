package be.groups.glanguage.glanguage.api.entities.formula.description;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FormulaTypeConverter implements AttributeConverter<FormulaType, Integer> {
	
	@Override
	public Integer convertToDatabaseColumn(FormulaType attribute) {
		return attribute == null ? null : Integer.valueOf(attribute.getValue());
	}
	
	@Override
	public FormulaType convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		} else {
			for (FormulaType type : FormulaType.values()) {
				if (Integer.valueOf(type.getValue()).equals(dbData)) {
					return type;
				}
			}
			
			throw new IllegalArgumentException("Unknown dbData " + dbData);
		}
	}
	
}
