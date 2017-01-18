package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.utils.Agents;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke.target.FormulaCannotInvokeTargetMethodInnerError;

import javax.persistence.Entity;

@Entity
public abstract class CallFormula extends AbstractNonTerminalFormula {
	
	public CallFormula() {
		super();
	}
	
	public CallFormula(FormulaDescription description) {
		super(description);
	}

	protected Object callFunctionAny(Object anObject, String aMethodName, AbstractFormula[] someMethodParameters,
									 Evaluator evaluator) throws GLanguageException {
		Agents agent;
		Object result;
		if(someMethodParameters == null) {
			agent = new Agents(anObject, aMethodName);
			result = agent.call();
		} else {
			Class<?>[] someMethodParametersType = new Class [someMethodParameters.length];
			Object[] someArguments = new Object [someMethodParameters.length];
			for (int i = 0; i < someMethodParameters.length; i++) {
				someArguments[i] = someMethodParameters[i].getValue(evaluator);
				someMethodParametersType[i] = someArguments[i].getClass();
			}
			agent = new Agents(anObject, aMethodName, someMethodParametersType);
			try {
				result = agent.call(someArguments);
			} catch (Exception e) {
				throw new GLanguageException(new FormulaCannotInvokeTargetMethodInnerError(this, evaluator, anObject,
																						   aMethodName, someMethodParametersType));
			}
		}
		return result;
	}

}
