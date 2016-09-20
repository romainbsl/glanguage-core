package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(value = FormulaType.Values.C_PRIMITIVE)
public class FormulaPrimitive extends CallFormula {
	
	public FormulaPrimitive() {
		super();
	}
	
	public FormulaPrimitive(String primitive, List<AbstractFormula> parameters) {
		super();
		
		if (primitive == null || primitive.isEmpty()) {
			throw new IllegalArgumentException("primitive must be a non-null non-empty string");
		}
		setConstantValue(primitive);
		if (parameters != null) {
			this.parameters = new ArrayList<>();
			this.parameters.addAll(parameters);
		}
	}
	
	protected Object getTargetedObject(Object object) {
		AbstractFormula[] parameters = null;
		if (getParameters() != null) {
			parameters = new AbstractFormula[getParameters().size()];
			parameters = getParameters().toArray(parameters);
		}
		return callFunctionAny(object, getConstantValue(), parameters);
	}
	
	@Override
	public String asText() {
		StringBuilder sb = new StringBuilder();
		sb.append(getConstantValue());
		sb.append("(");
		if (getParameters().size() > 0) {
			sb.append(getParameters().get(0).asText());
			Iterator<AbstractFormula> itParameters = getParameters().listIterator(1);
			while (itParameters.hasNext()) {
				sb.append("; ");
				sb.append(itParameters.next().asText());
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
}
